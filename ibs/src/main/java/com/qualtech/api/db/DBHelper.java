/*package com.qualtech.api.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

//import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class DBHelper 
{
	private static DBHelper instance=null;
	private DataSource ds=null;
	static Logger logger = Logger.getLogger(DBHelper.class.getName());
	static ResourceBundle res = ResourceBundle.getBundle("application");
	static String providerUrl=res.getString("providerUrl");
	static String dataSource=res.getString("data_source_name");
	static String tomcatDataSource=null;
	static String wasDataSource=null;
	@SuppressWarnings("unused")
	public void init() 
	{
		try
		{
			wasDataSource= "jdbc/"+dataSource;
			tomcatDataSource="java:/comp/env/"+dataSource;
			InitialContext cxt = new InitialContext();
			if ( cxt == null ) 
			{
				logger.error("Error no Context found:-");
			}
			try
			{
				try{
					logger.info("Looking for WAS Data Source:- "+wasDataSource);
					ds = (DataSource) cxt.lookup(wasDataSource);
				}catch(Exception ee)
				{
					if(ds==null){
					logger.info("Looking for tomcat or weblogic Data Source:- "+tomcatDataSource);
					ds = (DataSource) cxt.lookup(tomcatDataSource);
					}
				}
				logger.info("Data Source Found "+ds);
				
			}
			catch(Exception e)
			{
	////////////Added by Apurva//////////////////////////////////////////////////////////////
				
					try
					{
						if(ds == null)
						{
							logger.info("Looking for weblogic Data Source:- "+dataSource);
							   Hashtable env = new Hashtable();
							   env.put( InitialContext.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
							   env.put(InitialContext.PROVIDER_URL, providerUrl);
							   ds=(javax.sql.DataSource) cxt.lookup (dataSource);

							//ds = (DataSource) cxt.lookup("QC_LOS_DS1");
							logger.info("Data Source Found "+ds);
						}
					}
					catch(Exception e1)
					{			
						logger.error("Error while getting data source "+e1,new Throwable());
						e1.printStackTrace();
					}		
			////////////////////////////////////////////////////////////////////////////////////////////////////	
			}	
			try
			{
				Connection con = ds.getConnection();
				if(con!=null)
				{				
					con = (oracle.jdbc.OracleConnection)con;
					logger.info("Connection created..........");							
				}
				else
				{				
					logger.error("Connection isn't created..........");
				}
			}
			catch(Exception e)
			{			
				logger.error("Error while getting Connection from DataSource:- "+e,new Throwable());
				e.printStackTrace();	
			}
			if ( ds == null ) 
			{
			   logger.error("Data source not found!!!");
			   throw new Exception("Data source not found!!!");
			}
		}
		catch(Exception e)
		{
			logger.error("Error while initializing DB Helper "+e,new Throwable());
			e.printStackTrace();			
		}		
	}
	
	public Connection getSourceConnection() throws SQLException
	{
		Connection con = null;		
		try
		{
			con = ds.getConnection();
			con.getMetaData();
		}
		catch(Exception e)
		{ 
			logger.error("Error while getting connection from data source "+e,new Throwable());
			e.printStackTrace();
		}		
		return con;
	}
	
	public static synchronized DBHelper getInstance()
	{
		if(instance == null)
		{
			try
			{
				logger.info("Creating DBSQLHelper Instance.");
				instance = new DBHelper();
				logger.info("Initializing Instance.");
				instance.init();
			}
			catch(Exception e)
			{
				logger.error("Error in database Instance Initialization:-"+e,new Throwable());
				e.printStackTrace();
			}
		}
		return instance;
	}
}*/