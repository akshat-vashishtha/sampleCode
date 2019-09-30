package com.qualtech.crif.api.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/**
 * Add a certificate to the cacerts keystore if it's not already included
 */
public class SslUtil {
    private static final String CACERTS_PATH = "/lib/security/cacerts";
   // private static final String CACERTS_PASSWORD = "changeit";
    private static final String CACERTS_PASS = "changeit";
//    private static final String CACERTS_PASSWORD = "pass@123";

  
    public static void main(String[] args) {
    	try {
			SslUtil.ensureSslCertIsInKeystore("startssl", new FileInputStream("/opt/CMS/max.crt"));
		} catch (Exception e) {
		}
	}
    
    
    public static void ensureSslCertIsInKeystore(String alias, InputStream certInputStream)
            throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
       
    	
    	//get default cacerts file
        final File cacertsFile = new File(System.getProperty("java.home") + CACERTS_PATH);
        FileInputStream cacertsIs = null;
        try {
        	  if (!cacertsFile.exists()) {
                  throw new FileNotFoundException(cacertsFile.getAbsolutePath());
              }

              //load cacerts keystore
              cacertsIs = new FileInputStream(cacertsFile);
              final KeyStore cacerts = KeyStore.getInstance(KeyStore.getDefaultType());
              cacerts.load(cacertsIs, CACERTS_PASS.toCharArray());
             

              //load certificate from input stream
              final CertificateFactory cf = CertificateFactory.getInstance("X.509");
              final Certificate cert = cf.generateCertificate(certInputStream);
              certInputStream.close();

              //check if cacerts contains the certificate
              if (cacerts.getCertificateAlias(cert) == null) {
                  //cacerts doesn't contain the certificate, add it
                  cacerts.setCertificateEntry(alias, cert);
                  //write the updated cacerts keystore
                  FileOutputStream cacertsOs = new FileOutputStream(cacertsFile);
                  cacerts.store(cacertsOs, CACERTS_PASS.toCharArray());
                  cacertsOs.close();
              }
		} catch (Exception e) {
		}finally {
			if(cacertsIs!=null) {
				 cacertsIs.close();
			}
		}
      
    }
}