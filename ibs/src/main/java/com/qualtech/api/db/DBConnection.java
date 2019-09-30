package com.qualtech.api.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class DBConnection implements DBConnectionInt
{
	private static final Logger logger = Logger.getLogger(DBConnection.class.getName());
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	

	@Override
	public List<List<String>> getPropertyFileData()
	{
		logger.info("Going to getSystemConfigurationData inside USERDAOIMPL");
		
		List<List<String>> propertyList = new ArrayList<List<String>>();
		
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Sys_Configuration.class);
		
		criteria.add(Restrictions.eq("status", "Y")).add(Restrictions.in("applicationname", new String[]{"IB_HDFC","IB_CIBIL_V2","IB_PERFIOS","IB_CRIF","IB","IB_EQUIFAX","IB_CIBIL","IB_KOTAK","IB_KICKOFF","IB_EXPERIAN","IB_FUSION","IB_KARZA","IB_CREDIT","IB_HARDCODE","IB_FINBIT","IB_MULTI_BUREAU","IB_ICICI"}));
		
		logger.info(criteria);
		try
		{
		@SuppressWarnings("unchecked")
		
		List<Sys_Configuration> results = criteria.list();
	
			logger.info("Size of proc list : " + results.size());
			if (results != null && results.size() > 0)
			{

				Iterator<Sys_Configuration> it = results.iterator();
				while(it.hasNext())
				{
					Sys_Configuration row = it.next();
				     List<String> ll = new ArrayList<String>();
				     ll.add(row.getParamname());
				     ll.add(row.getParamvalue());
				     propertyList.add(ll);
				}
			}
		} 
		catch (Exception e) 
		{
			logger.error("Exception occured while executing db script:: " + e);
		}
		return propertyList;
	}
	
	
	/*public static List<Map<String , String>> getCustomClass(List<Object[]> objectList) 
	{
		List<Map<String , String>> pojoList = new ArrayList<Map<String , String>>();
		if(objectList != null && objectList.size()>0)
		{
			for( Object[] obj : objectList)
			{
				int key = 0;	
				Map<String , String> hashMap = new HashMap<String , String>();
				for(Object oo : obj)
				{
					hashMap.put("key"+key, (oo == null || oo == "")?"":oo.toString());
					key++;
				}
				pojoList.add(hashMap);	
			}
		}
		return pojoList;
	}*/


	
	
	/*	
	static Logger logger = Logger.getLogger(DBConnection.class);
	public String getSeqNextValue(String seqName)
	{
		logger.debug("Comes to DBConnection in getSeqNextValue Method: ");        
		ResultSet rs=null;
		Connection con=null;
		Statement stmt =null;
		String result=null;
		try
		{
			con=DBHelper.getInstance().getSourceConnection();		
			if(con!=null)
			{
				String query="SELECT "+seqName+".NEXTVAL FROM DUAL";
				logger.info("SQL query to be executed : "+query);
				stmt = con.createStatement();	
				stmt.setQueryTimeout(0);
				rs=stmt.executeQuery(query);
				if(rs.next())
				{
					result=rs.getString(1);
				}			
			}
			else
			{
				logger.error("Connection is Not Available Service unavailable.");				
			}
		}
		catch(Exception e)
		{			
			logger.error("Some exception occured while fetching next Sequence Value : "+e,new Throwable());

		}		
		finally
		{
			try
			{
				logger.debug("Trying to close Resources DBConnection in getSeqNextValue Method:- ");     
				if(rs!=null)
				{
					rs.close();
					rs=null;
				}
				if(stmt!=null)
				{
					stmt.close();
					stmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
				logger.debug("Successfully closed Resources DBConnection in getSeqNextValue Method:- ");
			}
			catch(Exception e)
			{				
				logger.error("Exception while closing resources DBConnection in getSeqNextValue Method: "+e,new Throwable());

			}
		}
		logger.debug("Getting out from DBConnection in getSeqNextValue Method : ");
		return result;
	}

	public List<List<String>> getMetaData(String query)
	{        
		logger.debug("Comes to DBConnection in getMetaData Method: ");        
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement stmt =null;
		List<List<String>> result=new ArrayList<List<String>>();    
		String message="";
		try
		{
			con=DBHelper.getInstance().getSourceConnection();		
			if(con!=null)
			{
				try
				{
					stmt = con.prepareStatement(query);		  
					stmt.setQueryTimeout(0);
					logger.info("SQL query to be executed : "+query);
					rs=stmt.executeQuery();
				}
				catch(Exception e)
				{
					message="Table Doesn't Exist.";
				}
				if(rs!=null)
				{
					ResultSetMetaData resultSetMetaData = rs.getMetaData();
					int noOfColumns = resultSetMetaData.getColumnCount();
					List<String> colName=new ArrayList<String>();
					for (int i = 1; i <= noOfColumns; i++)
					{
						colName=new ArrayList<String>();
						colName.add(resultSetMetaData.getColumnName(i));
						colName.add(""+resultSetMetaData.getColumnTypeName(i));
						colName.add(""+resultSetMetaData.getScale(i));
						colName.add(""+resultSetMetaData.getPrecision(i));
						colName.add(""+resultSetMetaData.isNullable(i));
						result.add(colName);
					}			        
					logger.info("total No of Columns fetch are:-"+noOfColumns);			       
				}			
			}
			else
			{
				logger.error("Connection is Not Available Service unavailable.");				
				message="Connection is Not Available.";
			}
		}
		catch(Exception e)
		{			
			logger.error("Some exception occured while fetching records : "+e,new Throwable());

			message="Error occured while fetching records.";
		}		
		finally
		{
			try
			{
				logger.debug("Trying to close Resources DBConnection in getMetaData Method:- ");  
				if(rs!=null)
				{
					rs.close();
					rs=null;
				}
				if(stmt!=null)
				{
					stmt.close();
					stmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
				logger.debug("Successfully closed Resources DBConnection in getMetaData Method:- ");  
			}
			catch(Exception e)
			{				
				logger.error("SQL exception while closing resources DBConnection in getMetaData Method: "+e,new Throwable());

			}
		}
		if(result!=null && result.size()==0 && !message.equalsIgnoreCase(""))
		{
			List<String> colName=new ArrayList<String>();
			colName.add(message);
			result.add(colName);
		}
		logger.debug("Getting out from DBConnection in getMetaData Method : ");
		return result;
	}

	public String executeUpdate(String query,String[] param)
	{
		logger.debug("Comes to DBConnection in ExecuteUpdate Method : ");
		Connection con=null;
		PreparedStatement stmt =null;
		String message="";
		int i=0;
		int count=-999;
		try
		{
			con=DBHelper.getInstance().getSourceConnection();		
			if(con!=null)
			{
				try
				{
					con.setAutoCommit(true);
					stmt = con.prepareStatement(query);		  
					if(param.length>0)
					{
						logger.info("SQL query to be executed : "+query +" with Parameters :-"+Arrays.toString(param));
						while(i<param.length)
						{
							stmt.setString(i+1,param[i]);
							i++;
						}
					}
					else
					{
						logger.info("SQL query to be executed : "+query);
					}
					count=stmt.executeUpdate();
					logger.debug("After Executing query total change count is:-"+count);
				}
				catch(Exception e)
				{
					logger.error("Error while executing query:"+e,new Throwable());

					message=e+" Error occured while operating records.";
				}       		        		  	
			}
			else
			{
				logger.error("Connection is Not Available Service unavailable.");		
				message="Connection is Not Available.";
			}
		}
		catch(Exception e)
		{			
			logger.error("Some exception occured while fetching records : "+e,new Throwable());

			message="Error occured while fetching records.";
		}		
		finally
		{
			try
			{
				logger.debug("Trying to close Resources DBConnection in ExecuteUpdate Method:- ");   
				if(stmt!=null)
				{
					stmt.close();
					stmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
				logger.debug("Successfully closed Resources DBConnection in ExecuteUpdate Method:- ");   
			}
			catch(Exception e)
			{				
				logger.error("SQL exception while closing resources DBConnection in ExecuteUpdate Method: "+e,new Throwable());

			}
		}
		//if(count!=-999)
		{
			message=String.valueOf(count);
		}
		logger.debug("Getting out from DBConnection in ExecuteUpdate Method : ");      
		return message;
	}

	public boolean executeUpdate(String selectQuery,String optype,String param[],List<?> param1)
	{
		logger.debug("Comes to DBConnection in ExecuteUpdate Method : ");
		Connection con=null;
		PreparedStatement stmt =null;
		ResultSet rs=null;
		Clob clob[]=null;
		Blob blob[]=null;
		int count=0;
		boolean flag=false;
		try
		{
			con=DBHelper.getInstance().getSourceConnection();		
			if(con!=null)
			{      		
				stmt=con.prepareStatement(selectQuery,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				if(param.length>0)
				{
					logger.info("SQL query to be executed : "+selectQuery +" with Parameters :-"+param);
					while(count<param.length)
					{
						stmt.setString(count+1,param[count]);
						count++;
					}
				}
				else
				{
					logger.info("SQL query to be executed : "+selectQuery);
				}
				rs=stmt.executeQuery();	  
				if(optype!=null && optype.equalsIgnoreCase("clob"))
				{
					count=0;
					clob=new java.sql.Clob[rs.getMetaData().getColumnCount()];
					if(rs.next())
					{
						while(count<param1.size()&& rs!=null)
						{ 
							clob[count] = rs.getClob(count+1);	    				
							clob[count].setString(1,param1.get(count).toString());
							stmt.setObject(count+1,clob[count]);
							count++;
						}
						rs.updateRow();
					}
				}
				if(optype!=null && optype.equalsIgnoreCase("blob"))
				{
					count=0;
					blob=new Blob[rs.getMetaData().getColumnCount()];
					if(rs.next())
					{
						while(count<param1.size()&& rs!=null)
						{ 
							blob[count] = rs.getBlob(count+1);	
							blob[count].setBytes((long)count+1,(byte[])param1.get(count));		    				
							count++;
						}
						rs.updateRow();
					}

				}
				//count=1;
				con.commit();
				if(count>0)
				{
					flag=true;
				}
			}
			else
			{
				logger.error("Connection is Not Available Service unavailable.");				
			}
		}
		catch(Exception e)
		{			
			logger.error("Some exception occured while fetching records : "+e,new Throwable());

		}		
		finally
		{
			try
			{
				logger.debug("Trying to close Resources DBConnection in ExecuteUpdate Method:- ");
				if(rs!=null)
				{
					rs.close();
					rs=null;
				}
				if(stmt!=null)
				{
					stmt.close();
					stmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
				clob=null;
				blob=null;
				logger.debug("Successfully closed Resources DBConnection in ExecuteUpdate Method:- ");   
			}
			catch(Exception e)
			{				
				logger.error("SQL exception while closing resources DBConnection in ExecuteUpdate Method: "+e,new Throwable());

			}
		}
		logger.debug("Getting out from DBConnection in ExecuteUpdate Method : ");			     
		return flag;
	}

	public List<List<String>> executeQuery(String query,String[] param)
	{        
		logger.debug("Comes to DBConnection in ExecuteQuery Method: ");        
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement stmt =null;
		int count=0;
		List<List<String>> result=null;
		try
		{
			con=DBHelper.getInstance().getSourceConnection();	
			if(con!=null)
			{
				stmt = con.prepareStatement(query);		  
				stmt.setQueryTimeout(0);
				if(param.length>0)
				{
					logger.info("SQL query to be executed : "+query +" with Parameters :-"+Arrays.toString(param));
					while(count<param.length)
					{
						stmt.setString(count+1,param[count]);
						count++;
					}
				}
				else
				{
					logger.info("SQL query to be executed : "+query);
				}
				rs=stmt.executeQuery();
				if(rs!=null)
				{
					result=new ArrayList<List<String>>();
					ResultSetMetaData resultSetMetaData = rs.getMetaData();
					int noOfColumns = resultSetMetaData.getColumnCount();
					List<String> colName=new ArrayList<String>();
					for (int i = 1; i <= noOfColumns; i++)
					{
						colName.add(resultSetMetaData.getColumnName(i));
					}
					result.add(colName);
					count=0;
					logger.info("total No of Columns fetch are:-"+noOfColumns);
					while(rs.next())
					{
						List<String> data=new ArrayList<String>();
						count++;
						for(int i=1;i<=noOfColumns;i++)
						{	
							data.add(rs.getObject(i)!=null?rs.getObject(i).toString():"");
						}
						result.add(data);
					}
					logger.info("total No of Records fetch are:-"+count);
				}			
			}
			else
			{
				logger.error("Connection is Not Available Service unavailable.");				
			}
		}
		catch(Exception e)
		{			
			logger.error("Some exception occured while fetching records : "+e,new Throwable());

		}		
		finally
		{
			try
			{
				logger.debug("Trying to close Resources DBConnection in ExecuteQuery Method:- ");  
				if(rs!=null)
				{
					rs.close();
					rs=null;
				}
				if(stmt!=null)
				{
					stmt.close();
					stmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
				logger.debug("Successfully closed Resources DBConnection in ExecuteQuery Method:- ");  
			}
			catch(Exception e)
			{				
				logger.error("SQL exception while closing resources DBConnection in ExecuteQuery Method: "+e,new Throwable());

			}
		}
		logger.debug("Getting out from DBConnection in ExecuteQuery Method : ");
		return result;
	}

	public List<List<String>> executeDataLobQuery(String query,String[] param,String dbType)
	{        
		logger.debug("Comes to DBConnection in ExecuteDataLobQuery Method: ");        
		ResultSet rs=null;
		Connection con=null;
		PreparedStatement stmt =null;
		ResultSetMetaData resultSetMetaData=null;
		Clob clob=null;
		int noOfColumns=0;
		int count=0;
		int i=0;
		List<String> data=new ArrayList<String>();
		List<List<String>> result=new ArrayList<List<String>>();
		try
		{
			if(dbType!=null && dbType.trim().equalsIgnoreCase("ORACLE"))
			{
				con=DBHelper.getInstance().getSourceConnection();
			}
			else
			{
				con=DBHelper.getInstance().getSourceConnection();
			}
			if(con!=null)
			{
				stmt = con.prepareStatement(query);
				stmt.setQueryTimeout(0);
				if(param.length>0)
				{
					logger.info("SQL query to be executed : "+query +" with Parameters :-"+Arrays.toString(param));
					while(count<param.length)
					{
						stmt.setString(count+1,param[count]);
						count++;
					}
				}
				else
				{
					logger.info("SQL query to be executed : "+query);
				}
				rs=stmt.executeQuery();
				if(rs!=null)
				{
					resultSetMetaData=rs.getMetaData();
					noOfColumns = resultSetMetaData.getColumnCount();
					List<String> colName=new ArrayList<String>();
					for (i = 1; i <= noOfColumns; i++)
					{
						colName.add(resultSetMetaData.getColumnName(i));			        	
					}
					result.add(colName);
					count=0;
					logger.info("total No of Columns fetch are:-"+noOfColumns);
					while(rs.next())
					{
						data=new ArrayList<String>();
						count++;
						for(i=1;i<=noOfColumns;i++)
						{	
							try
							{				        		
								if(resultSetMetaData.getColumnTypeName(i)!=null && resultSetMetaData.getColumnTypeName(i).equalsIgnoreCase("CLOB"))
								{
									try
									{
										clob=rs.getClob(i);	 
										data.add(clob != null ? clob.getSubString(1L, (int)clob.length()) : "");
									}
									catch(Exception e)
									{
										logger.error("Error in reteriving CLOB Value:-"+e,new Throwable());

									}
								}
								else if(resultSetMetaData.getColumnTypeName(i)!=null && resultSetMetaData.getColumnTypeName(i).equalsIgnoreCase("RAW"))
								{
									data.add(rs.getString(i)!=null?rs.getString(i):"");
								}
								else
								{
									data.add(rs.getObject(i)!=null?rs.getObject(i).toString():"");
								}
							}
							catch(Exception e)
							{
								logger.error("Error in Getting Data from coloum"+e,new Throwable());
							}
						}
						result.add(data);
					}
					logger.info("total No of Records fetch are:-"+count);
				}			
			}
			else
			{
				logger.error("Connection is Not Available Service unavailable.");				
			}
		}
		catch(Exception e)
		{			
			logger.error("Some exception occured while fetching records : "+e,new Throwable());

		}		
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
					rs=null;
				}
				if(stmt!=null)
				{
					stmt.close();
					stmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
				resultSetMetaData=null;
				clob=null;
				data=null;
			}
			catch(Exception e)
			{				
				logger.error("SQL exception while closing resources : "+e,new Throwable());

			}
		}
		logger.debug("Getting out from DBConnection in ExecuteDataLobQuery Method : ");
		return result;
	}

	public void procCall(String procName,String[] param) throws SQLException
	{
		logger.debug("Getting in DBConnection in procCall Method : ");   
		Connection con = null;
		CallableStatement proc = null;
		int i=0;
		try
		{
			con=DBHelper.getInstance().getSourceConnection();		
			if(con!=null)
			{
				proc = con.prepareCall("{ call "+procName+" }");
				proc.setQueryTimeout(0);
				if(param.length>0)
				{
					logger.info("procCall Method with procName : "+procName +" with Parameters :-"+Arrays.toString(param));
					while(i<param.length)
					{
						proc.setString(i+1,param[i]);
						i++;
					}
				}
				else
				{
					logger.info("procCall Method with procName : "+procName);
				}		        
				proc.execute();
			}
			else
			{
				logger.error("Connection is Not Available Service unavailable.");				
			}
		}
		catch(Exception e)
		{			
			logger.error("Some exception occured while Executing Proc : "+e,new Throwable());

		}		
		finally
		{
			try
			{
				logger.debug("Trying to close Resources DBConnection in procCall Method:- ");   
				if(proc!=null)
				{
					proc.close();
					proc=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
				logger.debug("Successfully closed Resources DBConnection in procCall Method:- ");   
			}
			catch(Exception e)
			{				
				logger.error("SQL exception while closing resources DBConnection in procCall Method: "+e,new Throwable());

			}
		}
		logger.debug("Getting out from DBConnection in procCall Method : ");       	      
	}

	public String[] procWithReturnValues(String procName,String[] param,int count) throws SQLException
	{
		logger.debug("Getting in DBConnection in procWithReturnValues Method : ");   
		Connection con = null;
		CallableStatement proc = null;
		String[] output=new String[count];
		int i=0;
		int j=0;
		try
		{
			con=DBHelper.getInstance().getSourceConnection();		
			if(con!=null)
			{
				proc = con.prepareCall("{ call "+procName+" }");
				proc.setQueryTimeout(0);
				if(param.length>0)
				{
					logger.info("procCall Method with procName : "+procName +" with Parameters :-"+Arrays.toString(param));
					while(i<param.length)
					{
						proc.setString(i+1,param[i]);
						i++;
					}
				}
				else
				{
					logger.info("procCall Method with procName : "+procName);
				}
				while(j<count)
				{
					proc.registerOutParameter(++i,Types.VARCHAR);
					j++;
				}
				proc.execute();
				if(j>0)
				{
					i=i-count;
					j=0;
					while(j<count)
					{
						output[j++]=proc.getString(++i);
					}
				}
			}
			else
			{
				logger.error("Connection is Not Available Service unavailable.");				
			}
		}
		catch(Exception e)
		{			
			logger.error("Some exception occured while Executing procWithReturnValues : "+e,new Throwable());

		}		
		finally
		{
			try
			{
				logger.debug("Trying to close Resources DBConnection in procWithReturnValues Method:- ");   
				if(proc!=null)
				{
					proc.close();
					proc=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
				logger.debug("Successfully closed Resources DBConnection in procWithReturnValues Method:- ");   
			}
			catch(Exception e)
			{				
				logger.error("SQL exception while closing resources DBConnection in procWithReturnValues Method: "+e,new Throwable());

			}
		}
		logger.debug("Getting out from DBConnection in procWithReturnValues Method : ");
		return output;
	}

	public String insertData(String tableName,String headerString,ArrayList<ArrayList> dataList)
	{
		logger.debug("Comes to DBConnection in insertData Method : ");
		Connection con=null;
		PreparedStatement stmt =null;
		String query="";
		String message=""; 
		String status="";
		int count=0; 
		logger.info("Inserting data in table --> "+tableName);
		query="INSERT INTO "+tableName+" ( "+headerString+" ) VALUES ";
		try
		{
			con=DBHelper.getInstance().getSourceConnection();		
			if(con!=null)
			{
				try
				{
					con.setAutoCommit(true);       					  
					if(dataList.size()>0)
					{
						logger.info("SQL query to be executed : "+query +" with Parameters :-"+dataList.toString());
						Iterator itr=dataList.iterator();
						while(itr.hasNext()) 
						{
							String dataStr=null;
							dataStr=itr.next().toString();	
							dataStr=dataStr.replace("[", "'").replace("]", "'").replaceAll(", ", "','");
							dataStr=query+" ( "+dataStr+" ) " ;     
							dataStr=dataStr.replaceAll("'@@"," ").replaceAll("@@'", " ");
							stmt = con.prepareStatement(dataStr);
							stmt.setQueryTimeout(0);
							count=stmt.executeUpdate(); 
							status="S";
						}
						logger.debug("After Executing query total change count is:-"+count);
					}
					else
					{
						logger.info("Datalist is null");
					}

				}
				catch(Exception e)
				{
					logger.error("Error while executing query:"+e,new Throwable());

					message=e+" "+"Connection is Not Available.";  
					status="F";
				}       		        		  	
			}
			else
			{
				logger.error("Connection is Not Available Service unavailable.");		
				message="Connection is Not Available.";
				status="F";
			}
		}
		catch(Exception e)
		{			
			logger.error("Some exception occured while fetching records : "+e,new Throwable());

			message="Error occured while fetching records.";
			status="F";
		}		
		finally
		{
			try
			{
				logger.debug("Trying to close Resources DBConnection in insertData Method:- ");   
				if(stmt!=null)
				{
					stmt.close();
					stmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
				logger.debug("Successfully closed Resources DBConnection in insertData Method:- ");   
			}
			catch(Exception e)
			{				
				logger.error("SQL exception while closing resources DBConnection in insertData Method: "+e,new Throwable());

			}
		}		
		logger.debug("Getting out from DBConnection in insertData Method : ");      
		return status;
	}

	public boolean isAvailabel(String agentid, String month,String year)
	{
		logger.debug("Comes to DBConnection in isAvailabel Method: ");        
		ResultSet rs=null;
		Connection con=null;
		Statement stmt =null;
		boolean result=false;
		try
		{
			con=DBHelper.getInstance().getSourceConnection();		
			if(con!=null)
			{
				String query="SELECT agentid, month, year from MS_BUSINESS_GOALS_MST WHERE agentid='"+agentid+"' and month='"+month+"' and year='"+year+"'";
				logger.info("SQL query to be executed : "+query);
				stmt = con.createStatement();	
				stmt.setQueryTimeout(0);
				rs=stmt.executeQuery(query);
				if(rs.next())
				{
					result=true;
				}			
			}
			else
			{
				logger.error("Connection is Not Available Service unavailable.");				
			}
		}
		catch(Exception e)
		{			
			logger.error("Some exception occured  "+e,new Throwable());

		}		
		finally
		{
			try
			{
				logger.debug("Trying to close Resources DBConnection in isAvailabel Method:- ");     
				if(rs!=null)
				{
					rs.close();
					rs=null;
				}
				if(stmt!=null)
				{
					stmt.close();
					stmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
				logger.debug("Successfully closed Resources DBConnection in isAvailabel Method:- ");
			}
			catch(Exception e)
			{				
				logger.error("Exception while closing resources DBConnection in isAvailabel Method: "+e,new Throwable());

			}
		}
		logger.debug("Getting out from DBConnection in isAvailabel Method : ");
		return result;
	}

	public String exceuteUpdaSQLSERVER(String query,String[] param)
	{
		logger.debug("Comes to DBConnection in ExecuteUpdate Method : ");
		Connection con=null;
		PreparedStatement stmt =null;
		String message="";
		int i=0;
		int count=-999;
		try
		{
			con=DBHelper.getInstance().getSourceConnection();		
			if(con!=null)
			{
				try
				{
					con.setAutoCommit(true);
					stmt = con.prepareStatement(query);		  
					if(param.length>0)
					{
						logger.info("SQL query to be executed : "+query +" with Parameters :-"+Arrays.toString(param));
						while(i<param.length)
						{
							stmt.setString(i+1,param[i]);
							i++;
						}
					}
					else
					{
						logger.info("SQL query to be executed : "+query);
					}
					count=stmt.executeUpdate();
					logger.debug("After Executing query total change count is:-"+count);
				}
				catch(Exception e)
				{
					logger.error("Error while executing query:"+e,new Throwable());
					message=e+" Error occured while operating records.";
				}       		        		  	
			}
			else
			{
				logger.error("Connection is Not Available Service unavailable.");		
				message="Connection is Not Available.";
			}
		}
		catch(Exception e)
		{			
			logger.error("Some exception occured while fetching records : "+e,new Throwable());
			message="Error occured while fetching records.";
		}		
		finally
		{
			try
			{
				logger.debug("Trying to close Resources DBConnection in ExecuteUpdate Method:- ");   
				if(stmt!=null)
				{
					stmt.close();
					stmt=null;
				}
				if(con!=null)
				{
					con.close();
					con=null;
				}
				logger.debug("Successfully closed Resources DBConnection in ExecuteUpdate Method:- ");   
			}
			catch(Exception e)
			{				
				logger.error("SQL exception while closing resources DBConnection in ExecuteUpdate Method: "+e,new Throwable());
			}
		}
		message=String.valueOf(count);
		logger.debug("Getting out from DBConnection in ExecuteUpdate Method : ");      
		return message;
	}




	public int insertCrifData(CriffDetailLogs  crifResponseEntity)
	{
		int count = 0;
		Connection con=null;
		PreparedStatement pstmt =null;
		try
		{
			StringBuilder sb = new StringBuilder();
			sb.append(" 	INSERT	 ");
			sb.append(" 	INTO CRIFF_DETAIL_LOGS	 ");
			sb.append(" 	  (	 ");
			sb.append(" 	    REQUEST_UNIQUE_ID,	 ");
			sb.append(" 	    TRACKER_ID,	 ");
			sb.append(" 	    CREATED_TIME,	 ");
			sb.append(" 	    UPDATED_TIME,	 ");
			sb.append(" 	    REMARKS,	 ");
			sb.append(" 	    SERVICETYPE	 ");
			sb.append(" 	  )	 ");
			sb.append(" 	  VALUES	 ");
			sb.append(" 	  (	 ");
			sb.append(" 	    ?,	 ");
			sb.append(" 	    ?,	 ");
			sb.append(" 	    systimestamp,	 ");
			sb.append(" 	    systimestamp,	 ");
			sb.append(" 	    ?,	 ");
			sb.append(" 	    ?	 ");
			sb.append(" 	  )	 ");

			con=DBHelper.getInstance().getSourceConnection();
			if(con!=null)
			{
				Long CRIF_UNIQUE_ID=0L;
				try
				{
					CRIF_UNIQUE_ID = crifResponseEntity.getRequest_unique_id();
					pstmt = con.prepareStatement(sb.toString());
					pstmt.setQueryTimeout(0);	
					pstmt.setLong(1,CRIF_UNIQUE_ID);
					pstmt.setString(2,crifResponseEntity.getTracker_id());
					pstmt.setString(3,crifResponseEntity.getRemarks());
					pstmt.setString(4,crifResponseEntity.getServiceType());
					count=pstmt.executeUpdate();
					logger.debug("After Executing query ID -:- "+CRIF_UNIQUE_ID+" -:- total change count is -:- "+count);
					try 
					{
						if(pstmt!=null)
						{
							pstmt.close();
						}
					}
					catch (Exception e)
					{
						logger.info("Error while closing PreparedStatement : "+e);
					}
				}
				catch(Exception e)
				{
					logger.error("Error while executing query:"+e);
				}  
				if(count>0)
				{
					if(crifResponseEntity.getCriffScoreStatus()!=null && !crifResponseEntity.getCriffScoreStatus().isEmpty())
					{
						sb.setLength(0);
						sb.append(" 	INSERT	 ");
						sb.append(" 	INTO CRIFF_SCORE_STATUS	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    CRIFF_SCORE_STATUS_ID,	 ");
						sb.append(" 	    ERRORS,	 ");
						sb.append(" 	    OPTIONS,	 ");
						sb.append(" 	    OPTION_SATUS,	 ");
						sb.append(" 	    REQUEST_UNIQUE_ID	 ");
						sb.append(" 	  )	 ");
						sb.append(" 	  VALUES	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?	 ");
						sb.append(" 	  )	 ");

						pstmt=con.prepareStatement(sb.toString());
						final int batchSize1 = 500;
						int innerCount = 0;
						int tempCount=0;
						for (CriffScoreStatus CriffScoreStatus: crifResponseEntity.getCriffScoreStatus()) 
						{

							pstmt.setLong(1, CriffScoreStatus.getCriff_score_status_id());
							pstmt.setString(2, CriffScoreStatus.getErrors());
							pstmt.setString(3, CriffScoreStatus.getOptions());
							pstmt.setString(4, CriffScoreStatus.getOption_satus());
							pstmt.setLong(5,CRIF_UNIQUE_ID);
							pstmt.addBatch();
							++innerCount;
							if(innerCount % batchSize1 == 0) 
							{
								int []batchCount = pstmt.executeBatch();
								tempCount+= batchCount!=null?batchCount.length:0;
							}
						}
						int []batchCount = pstmt.executeBatch(); // insert remaining records
						tempCount+= batchCount!=null?batchCount.length:0;
						logger.info("Total Crif score status Records Insertion Against ID -:- "+CRIF_UNIQUE_ID+" -:-  is : "+tempCount);
						try 
						{
							if(pstmt!=null)
							{
								pstmt.close();
							}
						}
						catch (Exception e)
						{
							logger.info("Error while closing PreparedStatement : "+e);
						}
					}

					if(crifResponseEntity.getCriffscore()!=null && !crifResponseEntity.getCriffscore().toString().isEmpty())
					{
						sb.setLength(0);
						sb.append(" 	INSERT	 ");
						sb.append(" 	INTO CRIF_SCORE	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    CRIFF_SCORES_ID,	 ");
						sb.append(" 	    SCORE_COMMENTS,	 ");
						sb.append(" 	    SCORE_TYPE,	 ");
						sb.append(" 	    SCORE_VALUE,	 ");
						sb.append(" 	    REQUEST_UNIQUE_ID	 ");
						sb.append(" 	  )	 ");
						sb.append(" 	  VALUES	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ? ");
						sb.append(" 	  )	 ");


						CriffScore criffScore=crifResponseEntity.getCriffscore();

						pstmt=con.prepareStatement(sb.toString());
						pstmt.setLong(1,criffScore.getCriff_scores_id());
						pstmt.setString(2,criffScore.getScore_comments());
						pstmt.setString(3,criffScore.getScore_type());
						pstmt.setString(4, criffScore.getScore_value());
						pstmt.setLong(5, CRIF_UNIQUE_ID);

						pstmt.executeUpdate();

						logger.info("Total Crif Score Records Insertion Against ID -:- "+CRIF_UNIQUE_ID+" -:-  is : ");
						try 
						{
							if(pstmt!=null)
							{
								pstmt.close();
							}
						}
						catch (Exception e)
						{
							logger.info("Error while closing PreparedStatement : "+e);
						}
					}


					if(crifResponseEntity.getGrpResponseNormalSummary()!=null && !crifResponseEntity.getGrpResponseNormalSummary().toString().isEmpty())
					{
						sb.setLength(0);
						sb.append(" 	INSERT	 ");
						sb.append(" 	INTO GRP_RESPONSE_NORMAL_SUMMARY	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    GRPUMMARY_ID,	 ");
						sb.append(" 	    NUMBER_OF_ACTIVE_ACCOUNTS,	 ");
						sb.append(" 	    NUMBER_OF_CLOSED_ACCOUNTS,	 ");
						sb.append(" 	    NUMBER_OF_DEFAULT_ACCOUNT,	 ");
						sb.append(" 	    NUMBER_OF_OTHER_MFIS,	 ");
						sb.append(" 	    NUMBER_OF_OWN_MFIS,	 ");
						sb.append(" 	    STATUS,	 ");
						sb.append(" 	    TOTAL_RESPONSES,	 ");
						sb.append(" 	    REQUEST_UNIQUE_ID ");
						sb.append(" 	  )	 ");
						sb.append(" 	  VALUES	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?	 ");
						sb.append(" 	  )	 ");


						GrpResponseSummary grpResponseSummary=crifResponseEntity.getGrpResponseNormalSummary();

						pstmt=con.prepareStatement(sb.toString());
						pstmt.setLong(1,grpResponseSummary.getGrpummary_id());
						pstmt.setString(2,grpResponseSummary.getNumber_of_active_accounts());
						pstmt.setString(3,grpResponseSummary.getNumber_of_closed_accounts());
						pstmt.setString(4,grpResponseSummary.getNumber_of_default_account());
						pstmt.setString(5, grpResponseSummary.getNumber_of_other_mfis());
						pstmt.setString(6, grpResponseSummary.getNumber_of_own_mfis());
						pstmt.setString(7,grpResponseSummary.getStatus());
						pstmt.setString(8, grpResponseSummary.getTotal_responses());
						pstmt.setLong(9, CRIF_UNIQUE_ID);

						pstmt.executeUpdate();

						logger.info("Total GRP RESPONSE NORMAL SUMMARY Records Insertion Against ID -:- "+CRIF_UNIQUE_ID+" -:-  is : ");
						try 
						{
							if(pstmt!=null)
							{
								pstmt.close();
							}
						}
						catch (Exception e)
						{
							logger.info("Error while closing PreparedStatement : "+e);
						}
					}




					if(crifResponseEntity.getIndvNormalSummary()!=null && !crifResponseEntity.getIndvNormalSummary().toString().isEmpty())
					{
						sb.setLength(0);
						sb.append(" 	INSERT	 ");
						sb.append(" 	INTO INDV_REPONSE_NORMAL_SUMMARY	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    INDV_SUMMARY_ID,	 ");
						sb.append(" 	    NUMBER_OF_ACTIVE_ACCOUNTS,	 ");
						sb.append(" 	    NUMBER_OF_CLOSED_ACCOUNTS,	 ");
						sb.append(" 	    NUMBER_OF_DEFAULT_ACCOUNT,	 ");
						sb.append(" 	    NUMBER_OF_OTHER_MFIS,	 ");
						sb.append(" 	    NUMBER_OF_OWN_MFIS,	 ");
						sb.append(" 	    STATUS,	 ");
						sb.append(" 	    REQUEST_UNIQUE_ID ");
						sb.append(" 	  )	 ");
						sb.append(" 	  VALUES	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?	 ");
						sb.append(" 	  )	 ");


						IndvResponseNormalSummary indvResponseNormalSummary=crifResponseEntity.getIndvNormalSummary();

						pstmt=con.prepareStatement(sb.toString());
						pstmt.setLong(1,indvResponseNormalSummary.getIndv_summary_id());
						pstmt.setString(2,indvResponseNormalSummary.getNumber_of_active_accounts());
						pstmt.setString(3,indvResponseNormalSummary.getNumber_of_closed_accounts());
						pstmt.setString(4,indvResponseNormalSummary.getNumber_of_default_account());
						pstmt.setString(5, indvResponseNormalSummary.getNumber_of_other_mfis());
						pstmt.setString(6,indvResponseNormalSummary.getNumber_of_own_mfis());
						pstmt.setString(7,indvResponseNormalSummary.getStatus());
						pstmt.setLong(8, CRIF_UNIQUE_ID);
						pstmt.executeUpdate();

						logger.info("Total INDV SUMMARY ID Records Insertion Against ID -:- "+CRIF_UNIQUE_ID+" -:-  is : ");
						try 
						{
							if(pstmt!=null)
							{
								pstmt.close();
							}
						}
						catch (Exception e)
						{
							logger.info("Error while closing PreparedStatement : "+e);
						}
					}



					if(crifResponseEntity.getCriffLoanDetails()!=null && !crifResponseEntity.getCriffLoanDetails().isEmpty())
					{
						sb.setLength(0);
						sb.append(" 	INSERT	 ");
						sb.append(" 	INTO CRIFF_LOAN_DETAILS	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    LOAN_DETAIL_ID,	 ");
						sb.append(" 	    ACCOUNT_NUMBER,	 ");
						sb.append(" 	    ACCOUNT_STATUS,	 ");
						sb.append(" 	    ACCT_TYPE,	 ");
						sb.append(" 	    CREDIT_GUARANATOR,	 ");
						sb.append(" 	    CURRENT_BALANCE,	 ");
						sb.append(" 	    DATE_REPORTED,	 ");
						sb.append(" 	    DISPURSE_AMOUNT,	 ");
						sb.append(" 	    DISPURSED_DATE,	 ");
						sb.append(" 	    INTEREST_RATE,	 ");
						sb.append(" 	    LINKED_ACCOUNTS,	 ");
						sb.append(" 	    MATCHED_TYPE,	 ");
						sb.append(" 	    OVER_DUE_AMOUNT,	 ");
						sb.append(" 	    OWNER_SHIP_IND,	 ");
						sb.append(" 	    SECURITY_DETAILS,	 ");
						sb.append(" 	    WRITE_OFF_AMOUNT,	 ");
						sb.append(" 	    REQUEST_UNIQUE_ID	 ");
						sb.append(" 	  )	 ");
						sb.append(" 	  VALUES	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?	 ");
						sb.append(" 	  )	 ");

						pstmt=con.prepareStatement(sb.toString());
						final int batchSize1 = 500;
						int innerCount = 0;
						int tempCount=0;
						for (CriffLoanDetails criffLoanDetails: crifResponseEntity.getCriffLoanDetails()) 
						{
							pstmt.setLong(1, criffLoanDetails.getLoan_detail_id());
							pstmt.setString(2, criffLoanDetails.getAccount_number());
							pstmt.setString(3, criffLoanDetails.getAccount_status());
							pstmt.setString(4, criffLoanDetails.getAcct_type());
							pstmt.setString(5, criffLoanDetails.getCredit_guaranator());
							pstmt.setString(6, criffLoanDetails.getCurrent_balance());
							pstmt.setString(7, criffLoanDetails.getDate_reported());
							pstmt.setString(8, criffLoanDetails.getDispurse_amount());
							pstmt.setString(9, criffLoanDetails.getDispursed_date());
							pstmt.setString(10, criffLoanDetails.getInterest_rate());
							pstmt.setString(11, criffLoanDetails.getLinked_accounts());
							pstmt.setString(12, criffLoanDetails.getMatched_type());
							pstmt.setString(13,criffLoanDetails.getOver_due_amount());
							pstmt.setString(14,criffLoanDetails.getOwner_ship_ind());
							pstmt.setString(15,criffLoanDetails.getSecurity_details());
							pstmt.setString(16,criffLoanDetails.getWrite_off_amount());
							pstmt.setLong(17, CRIF_UNIQUE_ID);
							pstmt.addBatch();
							++innerCount;
							if(innerCount % batchSize1 == 0) 
							{
								int []batchCount = pstmt.executeBatch();
								tempCount+= batchCount!=null?batchCount.length:0;
							}
						}
						int []batchCount = pstmt.executeBatch(); // insert remaining records
						tempCount+= batchCount!=null?batchCount.length:0;
						logger.info("Total CRIFF LOAN DETAILS Records Insertion Against ID -:- "+CRIF_UNIQUE_ID+" -:-  is : "+tempCount);
						try 
						{
							if(pstmt!=null)
							{
								pstmt.close();
							}
						}
						catch (Exception e)
						{
							logger.info("Error while closing PreparedStatement : "+e);
						}
					}





					if(crifResponseEntity.getCriffDerivedAttributes()!=null && !crifResponseEntity.getCriffDerivedAttributes().toString().isEmpty())
					{
						sb.setLength(0);
						sb.append(" 	INSERT	 ");
						sb.append(" 	INTO CRIFF_DERIVED_ATTRIBUTE	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    DERIVED_ATTRIBUTE_ID,	 ");
						sb.append(" 	    AVERAGE_ACCOUNT_AGE_MONTH,	 ");
						sb.append(" 	    ACCOUNT_AGE_YEAR,	 ");
						sb.append(" 	    DEL_IN_SIX_MONTHS,	 ");
						sb.append(" 	    LAST_SIX_MONTHS,	 ");
						sb.append(" 	    CREDIT_HISTORY_MONTH,	 ");
						sb.append(" 	    CREDIT_HISTORY_YEAR,	 ");
						sb.append(" 	    NEW_ACCOUNTS_SIX_MONTHS,	 ");
						sb.append(" 	    REQUEST_UNIQUE_ID	 ");
						sb.append(" 	  )	 ");
						sb.append(" 	  VALUES	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?	 ");
						sb.append(" 	  )	 ");


						CriffDrivedAttribute criffDrivedAttribute=crifResponseEntity.getCriffDerivedAttributes();

						pstmt=con.prepareStatement(sb.toString());
						pstmt.setLong(1,criffDrivedAttribute.getDerived_attribute_id());
						pstmt.setString(2,criffDrivedAttribute.getAverage_account_age_month());
						pstmt.setString(3,criffDrivedAttribute.getAverage_account_age_year());
						pstmt.setString(4,criffDrivedAttribute.getDel_in_last_six_months());
						pstmt.setString(5,"");//confusition 
						pstmt.setString(6,criffDrivedAttribute.getLength_of_credit_history_month());
						pstmt.setString(7,criffDrivedAttribute.getLength_of_credit_history_year());
						pstmt.setString(8,criffDrivedAttribute.getNew_accounts_in_last_six_months());
						pstmt.setLong(9, CRIF_UNIQUE_ID);
						pstmt.executeUpdate();

						logger.info("Total CRIFF DERIVED ATTRIBUTE Records Insertion Against ID -:- "+CRIF_UNIQUE_ID+" -:-  is : ");
						try 
						{
							if(pstmt!=null)
							{
								pstmt.close();
							}
						}
						catch (Exception e)
						{
							logger.info("Error while closing PreparedStatement : "+e);
						}
					}



					if(crifResponseEntity.getPrimaryaccountsummary()!=null && !crifResponseEntity.getPrimaryaccountsummary().toString().isEmpty())
					{
						sb.setLength(0);
						sb.append(" 	INSERT	 ");
						sb.append(" 	INTO PRIMARY_ACCOUNT_SUMMARY	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    PRIMARY_ACCOUNT_ID,	 ");
						sb.append(" 	    ACTIVE_ACCOUNTS,	 ");
						sb.append(" 	    CURRENT_BALANCE,	 ");
						sb.append(" 	    DISBURSED_AMOUNT,	 ");
						sb.append(" 	    NUMBER_OF_ACCOUNTS,	 ");
						sb.append(" 	    OVERDUE_ACCOUNTS,	 ");
						sb.append(" 	    SANCTIONED_AMOUNT,	 ");
						sb.append(" 	    SECURED_ACCOUNTS,	 ");
						sb.append(" 	    UNSECURED_ACCOUNTS,	 ");
						sb.append(" 	    UNTAGGED_ACCOUNTS,	 ");
						sb.append(" 	    REQUEST_UNIQUE_ID ");
						sb.append(" 	  )	 ");
						sb.append(" 	  VALUES	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?	 ");
						sb.append(" 	  )	 ");


						PrimaryAccountSummary primaryAccountSummary=crifResponseEntity.getPrimaryaccountsummary();

						pstmt=con.prepareStatement(sb.toString());
						pstmt.setLong(1,primaryAccountSummary.getPrimary_account_id());
						pstmt.setString(2,primaryAccountSummary.getActive_accounts());
						pstmt.setString(3,primaryAccountSummary.getCurrent_balance());
						pstmt.setString(4,primaryAccountSummary.getDisbursed_amount());
						pstmt.setString(5,primaryAccountSummary.getNumber_of_accounts()); 
						pstmt.setString(6,primaryAccountSummary.getOverdue_accounts());
						pstmt.setString(7,primaryAccountSummary.getSanctioned_amount());
						pstmt.setString(8,primaryAccountSummary.getSecured_accounts());
						pstmt.setString(9, primaryAccountSummary.getUnsecured_accounts());
						pstmt.setString(10,primaryAccountSummary.getUntagged_accounts());
						pstmt.setLong(11,CRIF_UNIQUE_ID);
						pstmt.executeUpdate();

						logger.info("Total PRIMARY ACCOUNT SUMMARY Records Insertion Against ID -:- "+CRIF_UNIQUE_ID+" -:-  is : ");
						try 
						{
							if(pstmt!=null)
							{
								pstmt.close();
							}
						}
						catch (Exception e)
						{
							logger.info("Error while closing PreparedStatement : "+e);
						}
					}


					if(crifResponseEntity.getIndvResponseDetails()!=null && !crifResponseEntity.getIndvResponseDetails().isEmpty())
					{
						sb.setLength(0);
						sb.append(" 	INSERT	 ");
						sb.append(" 	INTO INDV_RESPONSE_DETAILS	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    INDV_RSP_DTL_ID,	 ");
						sb.append(" 	    INDV_BRANCH,	 ");
						sb.append(" 	    INDV_CNSMBRID,	 ");
						sb.append(" 	    INDV_DOB,	 ");
						sb.append(" 	    INDV_INSERTED_DATE,	 ");
						sb.append(" 	    INDV_KENDRA,	 ");
						sb.append(" 	    INDV_MATCHED_TYPE,	 ");
						sb.append(" 	    INDV_MFI,	 ");
						sb.append(" 	    INDV_MFIID,	 ");
						sb.append(" 	    INDV_NAME,	 ");
						sb.append(" 	    REQUEST_UNIQUE_ID	 ");
						sb.append(" 	  )	 ");
						sb.append(" 	  VALUES	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ? ");

						sb.append(" 	  )	 ");


						pstmt=con.prepareStatement(sb.toString());
						final int batchSize1 = 500;
						int innerCount = 0;
						int tempCount=0;
						for (IndvResponseDetails indvResponseDetails: crifResponseEntity.getIndvResponseDetails()) 
						{
							pstmt.setLong(1,indvResponseDetails.getIndv_rsp_dtl_id());
							pstmt.setString(2, indvResponseDetails.getIndv_branch());
							pstmt.setString(3, indvResponseDetails.getIndv_cnsmbrid());
							pstmt.setString(4, indvResponseDetails.getIndv_dob());
							pstmt.setString(5, indvResponseDetails.getIndv_inserted_date());
							pstmt.setString(6, indvResponseDetails.getIndv_kendra());
							pstmt.setString(7, indvResponseDetails.getIndv_matched_type());
							pstmt.setString(8,indvResponseDetails.getIndv_mfi());
							pstmt.setString(9,indvResponseDetails.getIndv_mfiid());
							pstmt.setString(10, indvResponseDetails.getIndv_name());
							pstmt.setLong(11, CRIF_UNIQUE_ID);




							pstmt.addBatch();
							++innerCount;
							if(innerCount % batchSize1 == 0) 
							{
								int []batchCount = pstmt.executeBatch();
								tempCount+= batchCount!=null?batchCount.length:0;
							}
						}
						int []batchCount = pstmt.executeBatch(); // insert remaining records
						tempCount+= batchCount!=null?batchCount.length:0;
						logger.info("Total INDV RESPONSE DETAILS Records Insertion Against ID -:- "+CRIF_UNIQUE_ID+" -:-  is : "+tempCount);
						try 
						{
							if(pstmt!=null)
							{
								pstmt.close();
							}
						}
						catch (Exception e)
						{
							logger.info("Error while closing PreparedStatement : "+e);
						}
					}


					IndvResponseDetails indvResponseDetails=new IndvResponseDetails();

					if(indvResponseDetails.getIndvResponseAddress()!=null && !indvResponseDetails.getIndvResponseAddress().toString().isEmpty())
					{
						sb.setLength(0);
						sb.append(" 	INSERT	 ");
						sb.append(" 	INTO INDV_RESPONSE_ADDRESS	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    INDVADDRESSID,	 ");
						sb.append(" 	    ADDRESS,	 ");
						sb.append(" 	    INDV_RSP_DTL_ID	 ");
						sb.append(" 	  )	 ");
						sb.append(" 	  VALUES	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ? ");
						sb.append(" 	  )	 ");



						IndvResponseAddress indvResponseAddress=new IndvResponseAddress();//need to changes

						pstmt=con.prepareStatement(sb.toString());
						pstmt.setLong(1,indvResponseAddress.getIndvaddressid());
						pstmt.setString(2,indvResponseAddress.getAddress());
						pstmt.setLong(3,CRIF_UNIQUE_ID);

						pstmt.executeUpdate();

						logger.info("Total INDV RESPONSE ADDRESS Records Insertion Against ID -:- "+CRIF_UNIQUE_ID+" -:-  is : ");
						try 
						{
							if(pstmt!=null)
							{
								pstmt.close();
							}
						}
						catch (Exception e)
						{
							logger.info("Error while closing PreparedStatement : "+e);
						}
					}


					if(indvResponseDetails.getIndvids()!=null && !indvResponseDetails.getIndvids().isEmpty())
					{
						sb.setLength(0);
						sb.append(" 	INSERT	 ");
						sb.append(" 	INTO INDV_IDS	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    INDV_UQ_ID,	 ");
						sb.append(" 	    ID_TYPE,	 ");
						sb.append(" 	    ID_VALUE,	 ");
						sb.append(" 	    INDV_RSP_DTL_ID	 ");
						sb.append(" 	  )	 ");
						sb.append(" 	  VALUES	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?	 ");
						sb.append(" 	  )	 ");


						pstmt=con.prepareStatement(sb.toString());
						final int batchSize1 = 500;
						int innerCount = 0;
						int tempCount=0;
						for (IndvIds indvIds: indvResponseDetails.getIndvids()) 
						{
							pstmt.setLong(1,indvIds.getIndv_uq_id());
							pstmt.setString(2, indvIds.getId_type());
							pstmt.setString(3,indvIds.getId_value());
							pstmt.setLong(4, CRIF_UNIQUE_ID);


							pstmt.addBatch();
							++innerCount;
							if(innerCount % batchSize1 == 0) 
							{
								int []batchCount = pstmt.executeBatch();
								tempCount+= batchCount!=null?batchCount.length:0;
							}
						}
						int []batchCount = pstmt.executeBatch(); // insert remaining records
						tempCount+= batchCount!=null?batchCount.length:0;
						logger.info("Total INDV IDS Records Insertion Against ID -:- "+CRIF_UNIQUE_ID+" -:-  is : "+tempCount);
						try 
						{
							if(pstmt!=null)
							{
								pstmt.close();
							}
						}
						catch (Exception e)
						{
							logger.info("Error while closing PreparedStatement : "+e);
						}
					}

					if(indvResponseDetails.getIndvrelations()!=null && !indvResponseDetails.getIndvrelations().isEmpty())
					{
						sb.setLength(0);
						sb.append(" 	INSERT	 ");
						sb.append(" 	INTO INDV_RELATION	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    INDV_RELATION_ID,	 ");
						sb.append(" 	    RELATION_NAME,	 ");
						sb.append(" 	    RELATION_TYPE,	 ");
						sb.append(" 	    INDV_RSP_DTL_ID	 ");
						sb.append(" 	  )	 ");
						sb.append(" 	  VALUES	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?	 ");
						sb.append(" 	  )	 ");

						pstmt=con.prepareStatement(sb.toString());
						final int batchSize1 = 500;
						int innerCount = 0;
						int tempCount=0;
						for (IndvRelation indvRelation: indvResponseDetails.getIndvrelations()) 
						{
							pstmt.setLong(1,indvRelation.getIndv_relation_id());
							pstmt.setString(2, indvRelation.getRelation_name());
							pstmt.setString(3, indvRelation.getRelation_type());
							pstmt.setLong(4, CRIF_UNIQUE_ID);

							pstmt.addBatch();
							++innerCount;
							if(innerCount % batchSize1 == 0) 
							{
								int []batchCount = pstmt.executeBatch();
								tempCount+= batchCount!=null?batchCount.length:0;
							}
						}
						int []batchCount = pstmt.executeBatch(); // insert remaining records
						tempCount+= batchCount!=null?batchCount.length:0;
						logger.info("Total INDV RELATION Records Insertion Against ID -:- "+CRIF_UNIQUE_ID+" -:-  is : "+tempCount);
						try 
						{
							if(pstmt!=null)
							{
								pstmt.close();
							}
						}
						catch (Exception e)
						{
							logger.info("Error while closing PreparedStatement : "+e);
						}
					}




					if(indvResponseDetails.getIndvloandetails()!=null && !indvResponseDetails.getIndvloandetails().toString().isEmpty())
					{
						sb.setLength(0);
						sb.append(" 	INSERT	 ");
						sb.append(" 	INTO INDV_LOAN_DETAILS	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    INDV_LOAN_DTL_ID,	 ");
						sb.append(" 	    ACCOUNT_NUMBER,	 ");
						sb.append(" 	    ACCT_TYPE,	 ");
						sb.append(" 	    COMBINED_PAYMENT_HISTORY,	 ");
						sb.append(" 	    CURRENT_BALANCE,	 ");
						sb.append(" 	    DISPURSED_AMOUNT,	 ");
						sb.append(" 	    DISPURSED_DATE,	 ");
						sb.append(" 	    DPD,	 ");
						sb.append(" 	    FREQUENCY,	 ");
						sb.append(" 	    INFOSAN,	 ");
						sb.append(" 	    INQ_CNT,	 ");
						sb.append(" 	    INSTALLMENT_AMOUNT,	 ");
						sb.append(" 	    LOANCYCLE_ID,	 ");
						sb.append(" 	    OVERDUE_AMOUNT,	 ");
						sb.append(" 	    STATUS,	 ");
						sb.append(" 	    WRITE_OFF_AMOUNT,	 ");
						sb.append(" 	    INDV_RSP_DTL_ID ");

						sb.append(" 	  )	 ");
						sb.append(" 	  VALUES	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?	 ");
						sb.append(" 	  )	 ");



						IndvLoanDetails indvLoanDetails=new IndvLoanDetails();//need to ask direct created bean obj

						pstmt=con.prepareStatement(sb.toString());
						pstmt.setLong(1,indvLoanDetails.getIndv_loan_dtl_id());
						pstmt.setString(2,indvLoanDetails.getAccount_number());
						pstmt.setString(3,indvLoanDetails.getAcct_type());
						pstmt.setString(4,indvLoanDetails.getCombined_payment_history());
						pstmt.setString(5,indvLoanDetails.getCurrent_balance());
						pstmt.setString(6,indvLoanDetails.getDispursed_amount());
						pstmt.setString(7,indvLoanDetails.getDispursed_date());
						pstmt.setString(8,indvLoanDetails.getDpd());
						pstmt.setString(9,indvLoanDetails.getFrequency());
						pstmt.setString(10,indvLoanDetails.getInfosan());
						pstmt.setString(11,indvLoanDetails.getInq_cnt());
						pstmt.setString(12,indvLoanDetails.getInstallment_amount());
						pstmt.setString(13,indvLoanDetails.getLoancycle_id());
						pstmt.setString(14,indvLoanDetails.getOverdue_amount());
						pstmt.setString(15,indvLoanDetails.getStatus());
						pstmt.setString(16,indvLoanDetails.getWrite_off_amount());
						pstmt.setLong(17,CRIF_UNIQUE_ID);

						pstmt.executeUpdate();

						logger.info("Total INDV LOAN DETAILS Records Insertion Against ID -:- "+CRIF_UNIQUE_ID+" -:-  is : ");
						try 
						{
							if(pstmt!=null)
							{
								pstmt.close();
							}
						}
						catch (Exception e)
						{
							logger.info("Error while closing PreparedStatement : "+e);
						}
					}



					if(indvResponseDetails.getIndvgrpdetails()!=null && !indvResponseDetails.getIndvgrpdetails().toString().isEmpty())
					{
						sb.setLength(0);
						sb.append(" 	INSERT	 ");
						sb.append(" 	INTO INDV_GROUP_DETAILS	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    INDV_GRP_DTL_ID,	 ");
						sb.append(" 	    INDV_GRP_ID,	 ");
						sb.append(" 	    INDV_TOATL_ACCOUNT,	 ");
						sb.append(" 	    INDV_TOATL_DP_30,	 ");
						sb.append(" 	    INDV_TOATL_DP_60,	 ");
						sb.append(" 	    INDV_TOATL_DP_90,	 ");
						sb.append(" 	    INDV_TOTAL_BALANCE,	 ");
						sb.append(" 	    INDV_TOTAL_DISBURSED_AMOUNT,	 ");
						sb.append(" 	    INDV_RSP_DTL_ID	 ");
						sb.append(" 	  )	 ");
						sb.append(" 	  VALUES	 ");
						sb.append(" 	  (	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?,	 ");
						sb.append(" 	    ?	 ");
						sb.append(" 	  )	 ");

						pstmt=con.prepareStatement(sb.toString());
						IndvGrpDetails indvGrpDetails=new IndvGrpDetails();
						pstmt.setLong(1,indvGrpDetails.getIndv_grp_dtl_id());
						pstmt.setString(2,indvGrpDetails.getIndv_grp_id());
						pstmt.setString(3,indvGrpDetails.getIndv_toatl_account());
						pstmt.setString(4,indvGrpDetails.getIndv_toatl_dp_30());
						pstmt.setString(5,indvGrpDetails.getIndv_toatl_dp_60());
						pstmt.setString(6,indvGrpDetails.getIndv_toatl_dp_90());
						pstmt.setString(7,indvGrpDetails.getIndv_total_balance());
						pstmt.setString(8,indvGrpDetails.getIndv_total_disbursed_amount());
						pstmt.setLong(9,CRIF_UNIQUE_ID);
						pstmt.executeUpdate();

						logger.info("Total INDV GROUP DETAILS Records Insertion Against ID -:- "+CRIF_UNIQUE_ID+" -:-  is : ");
						try 
						{
							if(pstmt!=null)
							{
								pstmt.close();
							}
						}
						catch (Exception e)
						{
							logger.info("Error while closing PreparedStatement : "+e);
						}
					}

				}
			}
			else
			{
				logger.error("Connection is Not Available Service unavailable.");		
			}
		}
		catch(Exception e)
		{			
			logger.error("Some exception occured while fetching records : "+e);
		}		
		finally
		{
			try
			{
				logger.debug("Trying to close Resources DBConnection in cleanExcelErrorMstTable Method:- ");  

				if(pstmt!=null)
				{
					pstmt.close();
				}
				if(con!=null)
				{
					con.close();
				}
				logger.debug("Successfully closed Resources DBConnection in cleanExcelErrorMstTable Method:- ");  
			}
			catch(Exception e)
			{				
				logger.error("SQL exception while closing resources DBConnection in cleanExcelErrorMstTable Method: "+e,new Throwable());
			}
		}
		return count;
	}

*/}
