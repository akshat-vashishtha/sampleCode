package com.qualtech.equifax.api.utils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.qualtech.api.db.PropertyFile;
public class ConvertPDFToByteArray 
{
	private static Logger logger = Logger.getLogger(ConvertPDFToByteArray.class);
	//private static ResourceBundle resProp = PropertyFile.getInstance().getResourceBundel();
	@Autowired PropertyFile resProp;
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
