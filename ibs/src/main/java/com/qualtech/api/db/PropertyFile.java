package com.qualtech.api.db;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertyFile
{
	@Autowired
	private DBConnectionInt connectionInt;
	
	private static PropertyFile propertyFile = null;
	private static ResourceBundle rb = null;
	private PropertyFile() { }
	public static PropertyFile getInstance( ) 
	{
		if(propertyFile == null) 
		{
			propertyFile = new PropertyFile();
		}
		return propertyFile;
	}
	public void getResourceBundle()
	{
		if(rb == null) 
		{
			try
			{
				String filePath = "";
				@SuppressWarnings("deprecation")
				String fileName = "QC-IB-"+new Date().getDate()+"-"+UniqueId.getUniqueId();
				Properties prop = new Properties();
				List<List<String>> propertyList = connectionInt.getPropertyFileData();
				if(propertyList!=null && propertyList.size()>1)
				{
					for(int i =0; i<propertyList.size();i++)
					{
						if(propertyList.get(i).get(0).equalsIgnoreCase("PropertyFilePath"))
						{
							filePath=propertyList.get(i).get(1);
							
						}
						prop.setProperty(propertyList.get(i).get(0), propertyList.get(i).get(1));
					}

				}
				OutputStream os = null;
				try 
				{
					os = new FileOutputStream(filePath+fileName+".properties");
					prop.store(os, "Dynamic Property File For QC IB");
				}
				catch (FileNotFoundException e)
				{
				}
				try 
				{
					File file = new File(filePath);
					URL[] urls = {file.toURI().toURL()};
					ClassLoader loader = new URLClassLoader(urls);
					rb = ResourceBundle.getBundle(fileName, Locale.getDefault(), loader);
				}
				catch (Exception e)
				{
				}
			}
			catch (Exception ex)
			{

			}
		}
	}
	
	public  String getString(String key) {
		
		if(rb==null) {
			this.getResourceBundle();
		}
		
		
        return rb.getString(key);
    }

	public static void main(String a[]) throws IOException
	{
		PropertyFile.getInstance().getResourceBundle();
	}


	//	public static void main(String a[]) throws IOException
	//	{
	//		String filePath = "D:\\opt\\Logger\\";
	//		String fileName = "QC-IB-"+new Date().getDate()+"-"+UniqueId.getUniqueId();
	//		OutputStream os = null;
	//		Properties prop = new Properties();
	//		prop.setProperty("name", "java2novice");
	//		prop.setProperty("domain", "www.java2novice.com");
	//		prop.setProperty("email", "java2novice@gmail.com");
	//		try 
	//		{
	//			os = new FileOutputStream(filePath+fileName+".properties");
	//			prop.store(os, "Dynamic Property File");
	//		}
	//		catch (FileNotFoundException e)
	//		{
	//			e.printStackTrace();
	//		}
	//		try 
	//		{
	//			File file = new File(filePath);
	//			URL[] urls = {file.toURI().toURL()};
	//			ClassLoader loader = new URLClassLoader(urls);
	//			ResourceBundle rb = ResourceBundle.getBundle(fileName, Locale.getDefault(), loader);
	//		}
	//		catch (Exception e)
	//		{
	//			e.printStackTrace();
	//		}
	//	}

}