/*package com.qualtech.api.db;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.RefAddr;
import javax.naming.Reference;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.log4j.Logger;

//import com.qualtech.api.db.StringEncrypter;

public class DataSource extends BasicDataSourceFactory
{
	
	
	static Logger logger=Logger.getLogger(DataSource.class.getName());
	
	public static String getPassword(String encPassword)
    {
        String password=null;        
        try
        {
         //  StringEncrypter encrypter=new StringEncrypter();	
           password=null;//encrypter.getPassword(encPassword);
        }
        catch(Exception e)
        {
        	logger.error("Error in Password Decryption:-"+e,new Throwable());
            e.printStackTrace();
        }       
        return password;        
    }
	
	public Object getObjectInstance(Object obj, Name name, Context nameCtx,	@SuppressWarnings("rawtypes") Hashtable environment) throws Exception 
	{    	
 		if (obj == null || !(obj instanceof Reference))
 		{
 			return null;
 		}
 		Reference ref = (Reference) obj;
 		if (!"javax.sql.DataSource".equals(ref.getClassName()))
 		{
 			return null;
 		}
 		BasicDataSource dataSource = new BasicDataSource();
 		RefAddr ra = null;
 		ra = ref.get("defaultAutoCommit");
 		if (ra != null)
 		{
 			dataSource.setDefaultAutoCommit(Boolean.valueOf(ra.getContent().toString()).booleanValue());
 		}
 		ra = ref.get("defaultReadOnly");
 		if (ra != null)
 		{
 			dataSource.setDefaultReadOnly(Boolean.valueOf(ra.getContent().toString()).booleanValue());
 		}
 		ra = ref.get("driverClassName");
 		if (ra != null)
 		{
 			dataSource.setDriverClassName(ra.getContent().toString());
 		}
 		ra = ref.get("maxActive");
 		if (ra != null)
 		{
 			dataSource.setMaxActive(Integer.parseInt(ra.getContent().toString()));
 		}
 		ra = ref.get("maxIdle");
 		if (ra != null)
 		{
 			dataSource.setMaxIdle(Integer.parseInt(ra.getContent().toString()));
 		}
 		ra = ref.get("maxWait");
 		if (ra != null)
 		{
 			dataSource.setMaxWait(Long.parseLong(ra.getContent().toString()));
 		}
 		ra = ref.get("password");
 		if (ra != null)
 		{
 			String value=getPassword(ra.getContent().toString());
 			dataSource.setPassword(value);
 		}
 		ra = ref.get("url");
 		if (ra != null)
 		{
 			dataSource.setUrl(ra.getContent().toString());
 		}
 		ra = ref.get("username");
 		if (ra != null)
 		{
 			dataSource.setUsername(ra.getContent().toString());
 		}
 		ra = ref.get("validationQuery");
 		if (ra != null)
 		{
 			dataSource.setValidationQuery(ra.getContent().toString());
 		}
 		ra = ref.get("removeAbandoned");
 		if (ra != null)
 		{
 			dataSource.setRemoveAbandoned(Boolean.valueOf(ra.getContent().toString()).booleanValue());
 		}
 		ra = ref.get("removeAbandonedTimeout");
 		if (ra != null)
 		{
 			dataSource.setRemoveAbandonedTimeout(Integer.parseInt(ra.getContent().toString()));
 		}
 		ra = ref.get("logAbandoned");
 		if (ra != null)
 		{
 			dataSource.setLogAbandoned(Boolean.valueOf(ra.getContent().toString()).booleanValue());
 		}
 		return dataSource;
 	} 
}
*/