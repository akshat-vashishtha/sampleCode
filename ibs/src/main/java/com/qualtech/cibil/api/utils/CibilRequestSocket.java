package com.qualtech.cibil.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.cibil.api.interfaces.CibilRequestSocketInt;
@Service
public class CibilRequestSocket implements CibilRequestSocketInt
{
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CibilRequestSocket.class);
	@Autowired PropertyFile resource;
	public  Map<String,String> connectSocket(String tuef){
		OutputStream out=null;
		InputStream in=null;
		Socket client=null;
		String ipAddress=resource.getString("cibil.socket.ipAddress");;
		int portNo=Integer.valueOf(resource.getString("cibil.socket.port"));
		String turf="";	
		logger.info("RequestSocket | connectSocket Method - START | requestData="+tuef +" |-:-| prospectId=");
		try
		{                
			//client=new Socket("172.29.29.6",8506);
			client=new Socket(ipAddress,portNo);
			out = client.getOutputStream();
			in = client.getInputStream();
			String byteDataStream = "";
			String xx = tuef;
			int i = 19;
			char c = (char) i;
			xx = xx + c;
			byte[] byteBuffer = xx.getBytes();
			//out.write(byteBuffer.length-1);
			out.write(byteBuffer, 0, byteBuffer.length);  // Send the encoded string to the server
			out.flush();
			// Receive the same string back from the server
			//int totalBytesRcvd = 0;  // Total bytes received so far
			int bytesRcvd;           // Bytes received in last read
			byte[] byteBuffer1 = new byte[200242];
			while ((bytesRcvd = in.read(byteBuffer1)) != -1) {
				byteDataStream=new String(byteBuffer1);
				byteDataStream=byteDataStream.substring(0,bytesRcvd);
				turf=turf+byteDataStream;
			}
			if(!resource.getString("cibil.OutPutFormat").equals("02"))
			{
				turf=turf.substring(0,turf.indexOf("**")+2);
			}
		}
		catch(Exception e)
		{
			logger.error("RequestSocket | RequestSocket Method | Error |-:-|"+e.getMessage());
		}
		finally{
			try {
				if(in!=null){
					in.close();
				}
				if(out!=null){
					out.close();
				}
				if(client!=null){
					client.close();
				}
			} catch (IOException e) {
				logger.error("RequestSocket | RequestSocket Method | Error |-:-|"+e.getMessage());
			}
		}
		Map<String,String> map=new HashMap<String,String>();
		map.put("TURF", turf);
		logger.info("RequestSocket | RequestSocket Method - END | responseData = "+map);
		return map;

	}
	/*public static Map<String,String>  main(String tuef,String prospectId)
	{
		log.info("main Start");
		String score="";
		List addressList = new ArrayList();
		//tuef="TUEF121895054                    XXXXXXXXXX                    XXXXX                         51000010000   02011CCLPN03N010111CHETANKUMAR0201M0306JADHAV0400050007081508197608011ID03I010102010210AETPJ9143EID03I020102020208E9048374PT03T0101000200030200PT03T0201000200030201PA03A010137C-7 B WING 4TH FLOOR, ROOM NO 79,CHAR0235KOP GURUKRIPA, KANDIVLI (W), MUMBAI030004000506MUMBAI0602270706400067080202090201ES05004190102**";

		int length=tuef.length();
		//  tuef=tuefPart1+tuefPart2+tuefPart3;
		Map<String,String>  resMap= new RequestSocket().connectSocket(tuef, prospectId);
		log.info("main End");	
		return resMap;
	}*/
}
