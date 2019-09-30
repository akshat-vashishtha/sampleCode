/*package com.qualtech.karza.api.servlet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.karza.api.utils.XTrustProvider;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Commons;



*//**
 * Servlet implementation class KarzaServlet
 *//*
public class KarzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(KarzaServlet.class.getName());
	//**ResourceBundle env = PropertyFile.getInstance().getResourceBundel();
	@Autowired PropertyFile resProp;
	
    public KarzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			URL url = null;
			HttpURLConnection httpsConnection = null;

			String strURL =resProp.getString("com.qc.criff.liveURL") + "/ib/karza/api/v2/requestKarzaFaceMatch";
		
			String param = null;
			String requestJson = request.getParameter("requestJson");

			if (null != requestJson && !"".equals(requestJson)) {
				param = requestJson;
			}
			if (param == null) {
				try {
					InputStream ins = request.getInputStream();
					InputStreamReader insreader = new InputStreamReader(ins);
					BufferedReader breader = new BufferedReader(insreader);
					List uriResult = URLEncodedUtils.parse(new URI(breader.readLine()), "UTF-8");
					//					List uriResult = URLEncodedUtils.parse(breader.readLine(), Charset.defaultCharset());
					String completeRequest = uriResult.get(0).toString();
					StringTokenizer stk = new StringTokenizer(completeRequest, "=");
					stk.nextToken();
					param = stk.nextToken();

				} catch (Exception ec) {
					logger.error("Error in Exception " + ec);
				}
			}

			XTrustProvider xTrustProvider = new XTrustProvider();
			xTrustProvider.install();
			url = new URL(strURL);
			// String DevMode =
			// env.getProperty("spring.enable.proxy.development");
			// Proxy proxy;
			// if(DevMode!=null && !DevMode.equalsIgnoreCase("") &&
			// DevMode.equalsIgnoreCase("Y"))
			// {
			// logger.info("We are running in Development Mode So Proxy
			// Enabled");
			// proxy = new Proxy(Proxy.Type.HTTP, new
			// InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
			// conn = (HttpURLConnection) url.openConnection(proxy);
			// }
			// else
			// {
			// conn = (HttpURLConnection) url.openConnection();
			// }
			httpsConnection = (HttpURLConnection) url.openConnection();
			HttpsURLConnection.setFollowRedirects(true);
			httpsConnection.setRequestMethod("POST");
			httpsConnection.setDoInput(true);
			httpsConnection.setDoOutput(true);
			httpsConnection.setRequestProperty("Content-Type", "application/json");
			DataOutputStream wr = new DataOutputStream(httpsConnection.getOutputStream());
			wr.writeBytes(param);
			wr.flush();
			wr.close();
			logger.info("content type -->" + httpsConnection.getContentType());
			logger.info("http status code " + httpsConnection.getResponseCode());
			try {
				InputStream inputStream = httpsConnection.getInputStream();
				logger.info("inputStream ==" + inputStream.toString());
				byte[] _extData = null;
				_extData = Commons.getBytesFromInputStream(inputStream);
				String responseFromService = new String(_extData, "UTF-8");
				try {
					Map json = Commons.getGsonData(responseFromService);
					String printablereport = ((Map) ((Map) ((Map) json.get("payload")).get("transaction"))
							.get("printablereport")).get("content").toString();
					request.setAttribute("htmlformat", printablereport);
				} catch (Exception e) {
					request.setAttribute("Error-description", e.getMessage());
					e.printStackTrace();
				}
			} catch (IOException io) {
				request.setAttribute("Error-description", io.getMessage());
				io.printStackTrace();
				logger.error("error-->" + httpsConnection.getResponseMessage());
				InputStream inputStream = httpsConnection.getErrorStream();
				logger.error("error stream-->" + httpsConnection.getErrorStream());

			}
			httpsConnection.disconnect();
		} catch (Exception ec) {
			logger.error(ec);
		}
	}

}
*/