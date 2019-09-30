package com.qualtech.karza.api.utils;
import org.springframework.beans.factory.annotation.Autowired;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.karza.api.request.FacePayload;
import com.qualtech.karza.api.request.KarzaInfo;

public class CommonPerfios 
{
	//private static Logger logger = Logger.getLogger(CommonPerfios.class);

	//**private static ResourceBundle res = PropertyFile.getInstance().getResourceBundel();
	@Autowired PropertyFile res;
	
	
	/*public KarzaInfo upload(){
		KarzaInfo info=new KarzaInfo();
		// File to be read from the Path where it has been uploaded
		// Need to specify the fileName along with its type, only one value will go out of these following options image.jpeg/abc.pdf/ima.png/imag.jpg
		File fileName = new File("E:\\dbsnri\\");

		okhttp3.OkHttpClient client = new OkHttpClient();
		okhttp3.RequestBody requestBody = new MultipartBody.Builder().
		setType(MultipartBody.FORM).addFormDataPart("file", "one.jpg/two.jpg",
				//setType(MultipartBody.FORM).addFormDataPart("file", "image.jpeg/abc.pdf/ima.png/imag.jpg",
		 okhttp3.RequestBody.create("", fileName)).build();
		okhttp3.Request request = new okhttp3.Request.Builder()
		 .url("http://ocr.karza.in:8888/face")
		 .post(requestBody)
		 .addHeader("x-karza-key", "td89e4TCG76nd7gz9llh")
		 .build();
		Response response = client.newCall(request).execute();
		String responseMessage = response.body().string();
		return info;
	}
	*/
	@SuppressWarnings("null")
	public static KarzaInfo uploadFiles(FacePayload faceRequest, String url)
	{
		return null;
		/*
		MultiPart multiPart = null;
		String contentType = "multipart/mixed";
		String filePath=res.getString("com.perfios.temp.filelocation");
		KarzaInfo info = new KarzaInfo();
		String docName="";
		String fileFormat="jpg";
		String responseData="";
		File jpgFile=null;
		try 
		{
			Client client = ClientBuilder.newBuilder()
					.register(MultiPartFeature.class)
					.build();
			WebTarget server = client.target(url);
			
			
			File dir = new File(filePath);
			dir.mkdirs();
			logger.debug("File path :" +dir);

			if (dir.exists())
			{
				logger.debug("Exists Path is :"+filePath);
				if (dir.canWrite() )
				{
					byte[] bFile1 = Base64ArrayUtility.decodeToByteArray(faceRequest.getFile1());
					if(bFile1!=null)
					{
						docName=fileFormat;
						FileOutputStream fileOuputStream = new FileOutputStream(""+dir+File.separator+docName);
						fileOuputStream.write(bFile1);
						fileOuputStream.close();
					}
					byte[] bFile2 =	Base64ArrayUtility.decodeToByteArray(faceRequest.getFile2());
					if(bFile2!=null)
					{
						docName=fileFormat;
						FileOutputStream fileOuputStream = new FileOutputStream(""+dir+File.separator+docName);
						fileOuputStream.write(bFile2);
						fileOuputStream.close();
					}
				}
			}
			jpgFile = new File(filePath+docName);
			
			FileDataBodyPart pdfBodyPart1 = new FileDataBodyPart("file", jpgFile,MediaType.APPLICATION_OCTET_STREAM_TYPE);
			multiPart.bodyPart(pdfBodyPart1);
			FileDataBodyPart pdfBodyPart2 = new FileDataBodyPart("file", jpgFile,MediaType.APPLICATION_OCTET_STREAM_TYPE);
			multiPart.bodyPart(pdfBodyPart2);
			Response response = server.request(MediaType.APPLICATION_JSON).post(Entity.entity(multiPart, contentType));
			if (response.getStatus() == 200)
			{
				responseData = response.readEntity(String.class);
			}
			else
			{
				responseData = response.readEntity(String.class);
			}
			info.setResponse(responseData);
			if(res.getString("com.perfios.temp.filelocation.data.delete").equalsIgnoreCase("Y"))
			{
				try
				{
					if(jpgFile!=null)jpgFile.delete();
				}
				catch(Exception ex)
				{
					logger.error("ErrorInfo While Deleting Uploaded File ::"+ex);
				}
			}
		} 
		catch (Exception e)
		{
			logger.error("Exception has occured "+ e);
		} 
		finally
		{
			if (null != multiPart)
			{
				try 
				{
					multiPart.close();
				} 
				catch (IOException e) 
				{
					logger.error("Exception has occured while closing resources : "+ e);
				}
			}
		}
		return info;
	*/}
}
