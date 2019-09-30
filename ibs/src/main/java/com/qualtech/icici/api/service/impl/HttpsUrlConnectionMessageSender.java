package com.qualtech.icici.api.service.impl;

import java.io.IOException;
import java.net.*;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.ws.transport.WebServiceConnection;
import org.springframework.ws.transport.http.HttpTransportException;
import org.springframework.ws.transport.http.HttpUrlConnection;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;

public class HttpsUrlConnectionMessageSender extends HttpUrlConnectionMessageSender {

    public WebServiceConnection createConnection(URI uri) throws IOException {

        URL url = uri.toURL();
        URLConnection connection = url.openConnection();
        if (!(connection instanceof HttpsURLConnection)) {
            throw new HttpTransportException("URI [" + uri + "] is not an HTTPS URL");
        } else {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) connection;
            httpsURLConnection.setSSLSocketFactory(new TLSSocketConnectionFactory());
            prepareConnection(httpsURLConnection);
            return (WebServiceConnection) new HttpsUrlConnection(httpsURLConnection);
        }
    }
    
    private void prepareConnection(HttpsURLConnection httpsURLConnection) {
		// TODO Auto-generated method stub
		
	}

	public HttpsURLConnection  createConnection1(URI uri) throws IOException {
        URL url = uri.toURL();
        URLConnection connection = url.openConnection();
        if (!(connection instanceof HttpsURLConnection)) {
            throw new HttpTransportException("URI [" + uri + "] is not an HTTPS URL");
        } else {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) connection;
            httpsURLConnection.setSSLSocketFactory(new TLSSocketConnectionFactory());
            prepareConnection(httpsURLConnection);
            return  httpsURLConnection;
        }
    }

    private static class HttpsUrlConnection extends HttpUrlConnection {
        private HttpsUrlConnection(HttpsURLConnection connection) {
            super(connection);
        }
    }
}