package com.qualtech.crif.api.utils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;

public class ConvertPDFToByteArray 
{
	static Logger logger = Logger.getLogger(ConvertPDFToByteArray.class.getName());
	static ResourceBundle env = ResourceBundle.getBundle("application");

	public  String pdfByteCaller(String fileLocation) throws IOException
	{
		logger.info("Came Inside pdfByteCaller with FileLocation : "+fileLocation);
		byte data[] = convertPDFToByteArray(fileLocation);
		String filedata=Base64.encode(data);
		return filedata;
	}
	private  byte[] convertPDFToByteArray(String fileLocation)
	{
		logger.info("Came Inside convertPDFToByteArray :");
		InputStream inputStream = null;
		ByteArrayOutputStream baos = null;//new ByteArrayOutputStream();
		byte[] byteArray= null;
		try 
		{
			File pdffile= new File(fileLocation);
			if(pdffile.exists())
			{
				logger.info("Pdf found at Location :"+fileLocation);		
				inputStream = new FileInputStream(fileLocation);
				byte[] buffer = new byte[1024];
				baos = new ByteArrayOutputStream();

				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1)
				{
					baos.write(buffer, 0, bytesRead);
				}
				byteArray =  baos.toByteArray();
			}
			else
			{
				logger.info("Pdf not saved at Location :"+fileLocation);

			}

		} 
		catch (FileNotFoundException e)
		{
			logger.error("File not found--"+e);
		}
		catch (IOException e)
		{
			logger.error("IO exception --"+e);
		}
		finally 
		{
			if (inputStream != null)
			{
				try {inputStream.close();} catch (IOException e) {	}
			}
			if(baos!=null)
			{
				try {baos.close();} catch (IOException e) {	}
			}
		}
		logger.info("Pdf byte array--"+ byteArray);
		return byteArray;
	}

}


/*// -----------For decode------------------
Base64 b = new Base64();
byte[] bytes = b.decode(filedata); 
FileOutputStream fos = new FileOutputStream("D:\\AadharWebService\\AadhaarKycFiles\\New\\999929989823.pdf");
fos.write(bytes);
fos.close();
 */


