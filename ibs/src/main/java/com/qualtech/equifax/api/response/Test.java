package com.qualtech.equifax.api.response;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class Test {

	public static void main(String[] args) {
		FileReader reader=null;
		FileInputStream in=null;
		BufferedReader bufferreader=null;
		FileWriter writer =null;
		try {
			reader=new FileReader(new File("/home/qualtech/EquifaxUserFriendlyOutPutForVID.jsp"));
			
			in=new FileInputStream(new File("/home/qualtech/EquifaxUserFriendlyOutPutForVID.jsp"));
			
			bufferreader=new BufferedReader(new InputStreamReader(in));
			/*int i=0;
			while((i=in.read())!=-1) {
				System.out.println((char)i);
			}*/
		//	BufferedReader bufferreader = new BufferedReader();

			StringBuilder htmlData=new StringBuilder("");
			String line="";
	        while ( (line=bufferreader.readLine() )!= null) {     
	         
	        	//System.out.println(line+"\n");
	        	htmlData.append(line+"\n");
	        }
	        
	        writer=new FileWriter(new File("/home/qualtech/kk.html"));
	        writer.write(htmlData.toString());
			
			
		} catch (Exception e) {
			
		}
		finally {
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
            if(in!=null) {
            	try {
            		in.close();
				} catch (IOException e) {
				}
			}
            if(bufferreader!=null) {
				try {
					bufferreader.close();
				} catch (Exception e) {
				}
			}
            if(writer!=null) {
				try {
					writer.close();
				} catch (IOException e) {
				}
			}
		}
		
		
	}

}
