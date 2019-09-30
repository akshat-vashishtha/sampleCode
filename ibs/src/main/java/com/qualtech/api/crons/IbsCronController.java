package com.qualtech.api.crons;


import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

//@Controller
//@RequestMapping("ibs/api")
public class IbsCronController 
{
	static Logger logger = Logger.getLogger(IbsCronController.class.getName());
	@Autowired CronsDBConnection cronDb;
	@Autowired CronService service;
	//@Scheduled(cron = "*/30 * * * * ?")
	//@Scheduled(cron = "*/60 * * * * ?")
	@Scheduled(fixedDelay = 1800000)
	public void ServiceMethod() 
	{
		try 
		{
			NDC.push("IBS_Cron");
			logger.info("IbsCronController || ServiceMethod() || IBS Cron :: STARTS : "+ new Date());
			//ResourceBundle res=ResourceBundle.getBundle("application");
			service.cronService();
			/*try {
				String ip = InetAddress.getLocalHost().getHostAddress().toString();
				if(ip.equals(res.getString("ibs.app.server.ip"))){
					service.cronService();
				}else {
					//service.cronService();
		        }
			} catch (UnknownHostException e) {
				logger.error("Exception from live url:: " + e);
			}*/
		}
		catch (Exception e)
		{
			logger.error("Exception in IbsCronController || ServiceMethod() || IBS Cron unable to process :: "+e);
		}
		finally
		{
			logger.info("IbsCronController || ServiceMethod() || IBS Cron :: END");
			NDC.pop();
		}
	}
		
}
