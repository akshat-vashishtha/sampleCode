package com.qualtech.api.crons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.pdf.codec.Base64;
import com.qualtech.api.db.ServiceMaster;
import com.qualtech.api.ibs.util.FibitBankingRequest;
import com.qualtech.api.ibs.util.FibitRequest;
import com.qualtech.api.ibs.util.FinbitFormReq;
import com.qualtech.api.ibs.util.Header;
import com.qualtech.api.ibs.util.RequestIBs;
import com.qualtech.experiankickoff.api.utils.UniqueId;

@Controller
@RequestMapping("ibs/api")
public class IbsRestController {
	static Logger logger = Logger.getLogger(IbsCronController.class.getName());
	@Autowired CronsDBConnection cronDb;
	@Autowired CronService service;
	
	@RequestMapping(value ="/hello", method = RequestMethod.GET)
	public void hello() 
	{
		String uniqueId = UniqueId.getUniqueId();
	
		try {
		
			NDC.push("IBS Cron : "+uniqueId);
			logger.info(" IBS Cron :: STARTS");
			service.cronService();
		} catch (Exception e) {
			logger.error(" IBS Cron unable to process :: "+e);
		}finally {
			logger.info("IBS Cron :: END");
			NDC.pop();
		}
		
	}
	
	@RequestMapping(value = "getRequest", method = RequestMethod.POST)
	   public void getRequest( String request) {
		logger.info("IBS getRequest() :: Start");
	      service.ibsService(request);
	      new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					service.cronService();
				}

			}).start();
	      logger.info("IBS getRequest() :: END");
	   }
	
	

	@RequestMapping(value = "pdfdownload", method = RequestMethod.GET)
	public void downloadPDFResource(HttpServletResponse response, @RequestParam("dc1Servers") String dc1Servers) 
	{
		String fileSeperator=File.separator;
		if(dc1Servers.contains("~~"))
		{
			dc1Servers=dc1Servers.replaceAll("~~","/");
		}
		else
		{
			dc1Servers=dc1Servers.replaceAll("@@","\\\\");	
		}
		String pdfname = dc1Servers.substring(dc1Servers.lastIndexOf(fileSeperator) + 1, dc1Servers.length());
		File file=new File(dc1Servers);
		FileInputStream fileInputStream =null;
		if (file.exists()) {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=" + pdfname);
			try {
				fileInputStream = new FileInputStream(dc1Servers);
				int i;
				while ((i = fileInputStream.read()) != -1) {
					response.getOutputStream().write(i);
				}
				
			} catch (IOException ex) {
				logger.error(" IBS Cron Error Occur to reading fileInputStream resources :: "+ex);
			}finally {
				try {
					if(fileInputStream!=null) {
					fileInputStream.close();
					}
					
					response.getOutputStream().close();
					response.getOutputStream().flush();
					
				} catch (IOException e) {
					logger.error(" IBS Cron Error Occur to closing resources :: "+e);
				}
				
			}
		}
	}
	@RequestMapping(value = "/v1/intergationServiceList", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<ServiceMaster> ibsServiceNameList() {
		List<ServiceMaster> sername = cronDb.getServiceList();
		return sername;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/v1/ibsCredentials")
	@ResponseBody
	public String getIbsCredentials(@RequestBody String request) {
		logger.info(" inside getIbsCredentials() :: Start");
		Header req = new Header();
		ObjectMapper om = new ObjectMapper();
		try {
			logger.info(" inside mapping request :: "+request);
			req = om.readValue(request, Header.class);
		} catch (Exception e1) {
			logger.error(" Error Occured inside getIbsCredentials() mapping  OBjMapper :: "+e1);
		} 
		
		String tid = req.getTid();
		String list = cronDb.retriveCredential(tid);
		logger.info(" inside getIbsCredentials() :: END");
		return list;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/v1/ibsHistory", consumes = "application/x-www-form-urlencoded", produces = "application/json")
	@ResponseBody
	public List<HistoryCridential> getIbsHistory(HttpServletRequest request, HttpSession session) {
		logger.info(" inside getIbsHistory() :: Start");
		List<HistoryCridential> history=null;
		String request_json = (String) session.getAttribute("request_json");
		if (request_json != null) 
		{
			String tid =request.getParameter("tid");// req.getTid();

			try {
				history = cronDb.retriveHistory(tid);
			} catch (Exception e) {
				logger.error(" unable to retriveHistory :: "+e);
			}
		}
		else
		{
			return null;
		}

		logger.info(" inside getIbsHistory() :: END");
		return history;
	}

	@RequestMapping(value = "holdRequest", method = RequestMethod.POST)
	public ModelAndView holdRequest(String request, HttpSession session) {
		ObjectMapper om = new ObjectMapper();
		ModelAndView modelAndView = null;
		
		// String request_json=(String)session.getAttribute("request_json");
		try {
			RequestIBs request1=om.readValue(request, RequestIBs.class);
			session.setAttribute("request_json", om.writeValueAsString(request1));
			modelAndView = new ModelAndView("welcome", "transactionId", request1.getHeader()!=null?request1.getHeader().getTid():null );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "finbitRequest",  method = RequestMethod.POST)
	@ResponseBody public String finbitRequest(@ModelAttribute FinbitFormReq request,HttpSession session) {
		
		logger.info(" inside finbitRequest() :: Start");
		logger.info(" FinbitFormReq :: "+request);
		ObjectMapper om = new ObjectMapper();
		
		try {
			String request_json=(String)session.getAttribute("request_json");
			RequestIBs requestIBS=om.readValue(request_json, RequestIBs.class);
			if(requestIBS!=null && requestIBS.getPayload()!=null) {
				if(request!=null && request.getBankStatement()!=null) {
					FibitBankingRequest banking=new FibitBankingRequest();
					int i=0;
					List<FibitRequest> accountDetail=new ArrayList<FibitRequest>();
					
					for(MultipartFile bankStatement:request.getBankStatement()) {
						FibitRequest account=new FibitRequest();
						
						account.setBankstatement(Base64.encodeBytes(bankStatement.getBytes()));
						account.setAccounttype(request.getAccountType().get(i));
						account.setBankname(request.getBankName().get(i));
						account.setEmailaddress(request.getEmail().get(i));
						account.setPassword(request.getPsw().get(i));
						accountDetail.add(account);
						i++;
					}
					banking.setAccountDetail(accountDetail);
					requestIBS.getPayload().setBanking(banking);
				}else {
					requestIBS.getPayload().setBanking(null);
				}
			}
			logger.info(" complete json with finbitRequest :: "+om.writeValueAsString(requestIBS));
			session.setAttribute("request_json", om.writeValueAsString(requestIBS));
		} catch (Exception e) {
			logger.error(" Exception occured : "+e);
		}
		logger.info(" inside finbitRequest() :: END");
		return "Done";
	}
	
	@RequestMapping(value = "/v1/ibs", method = RequestMethod.POST)
	@ResponseBody public String ibs(HttpServletRequest request, HttpSession session) {
		logger.info(" inside ibs() :: Start");
		ObjectMapper om = new ObjectMapper();
		String request_json=(String)session.getAttribute("request_json");
		try {
			RequestIBs request1=om.readValue(request_json, RequestIBs.class);
			Header header= om.readValue(request.getParameter("requestData"), Header.class);
			request1.getHeader().setTid(header.getTid()!=null?header.getTid():"");
			request1.getHeader().setServicename(header.getServicename()!=null?header.getServicename():null);
			String serviceList="";
			int i=0;
			if(header.getServicename()!=null){
				for(String s:header.getServicename()) {
					if(i!=0) {
						serviceList=serviceList+", "+s;
					}else {
						serviceList=s;
						i=1;
					}
				}
			}
			getRequest(om.writeValueAsString(request1));
			return "true"/*"Request Initiated for " + serviceList*/;
		} catch (Exception e) {
			logger.error("exception inside ibs() :: "+e);
		}
		logger.info(" inside ibs() :: END");
		return "false";
	}
	@RequestMapping(value = "/SessionClose", method = RequestMethod.GET)
	@ResponseBody public void sessionClose(HttpSession session) {
		
		session.invalidate();
	}
	@RequestMapping(value = "/SessionClosed", method = RequestMethod.GET)
	@ResponseBody public ModelAndView sessionClosed() {
		
		return new ModelAndView("closedSession", "msg", "Kindly initiate request with new transaction Id.");
	}
	
}
