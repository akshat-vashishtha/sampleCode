package com.qualtech.karza.api.utils;

import java.util.Iterator;


import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.qualtech.karza.api.response.KycOcrResBack;
import com.qualtech.karza.api.response.KycOcrResFront;
import com.qualtech.karza.api.response.KycOcrResFrontTop;
import com.qualtech.karza.api.response.KycOcrResResult;
import com.qualtech.karza.api.response.KycOcrResponsePayload;

public class KarzaUtils {

	static Logger logger = Logger.getLogger(KarzaUtils.class.getName());

	 public static KycOcrResponsePayload kycOcrJsonToPayload(String response) {

		// response="{   \"status-code\": \"101\",   \"request_id\": \"l7le0ee3-j3ao-VNDG-ukCy-VQP3B8IB56AB\",   \"result\": [     {       \"details\": {         \"country_code\": \"IND\",         \"dob\": \"17/08/1987\",         \"doe\": \"10/07/2017\",         \"doi\": \"11/07/2007\",         \"gender\": \"M\",         \"given_name\": \"OMKAR MILIND\",         \"mrz\": [           \"P<INDSHIRHATTI<<OMKAR<MILIND<<<<<<<<<<<<<<<<\",           \"G3850120<9IND8708173M1707100<<<<<<<<<<<<<<<0\"         ],         \"nationality\": \"INDIAN\",         \"passport_num\": \"G3850120\",         \"place_of_birth\": \"DOMBIVLI\",         \"place_of_issue\": \"THANE\",         \"surname\": \"SHIRHATTI\",         \"type\": \"P\"       },       \"type\": \"passport_front\"     }   ] }";
			//voter
			//response="{   \"request_id\": \"cnwgy18c-sw3b-HG15-2Tzo-JSWSRZ18H53V\",   \"result\": [     {       \"details\": {         \"age\": \"24\",         \"dob-calculated\": \"XX/XX/1982\",         \"doc\": \"1.1.2007\",         \"father\": \"Ram Lal\",         \"gender\": \"M\",         \"name\": \"Arvind Kumar\",         \"voterid\": \"MPD3636432\"       },       \"type\": \"voterid_front\"     },     {       \"details\": {         \"address\": \"H.No., 43, Strt/Mohalla: Belagadi\",         \"type\": \"Old\",         \"voterid\": \"MPD3636432\"       },       \"type\": \"voterid_back\"     }   ],   \"status-code\": \"101\" }";
			//pan
			//response="{   \"status-code\": \"101\",   \"request_id\": \"67tgecsd-53jz-LUNE-DElR-K3786C1WL0JV\",   \"result\": [     {       \"details\": {         \"date\": \"17/08/1987\",         \"father\": \"MILIND RAGHAVENDRA SHIRHATTI\",         \"name\": \"OMKAR MILIND SHIRHATTI\",         \"pan_no\": \"BVZPS1846R\"       },       \"type\": \"pan\"     }   ] }";
			//adhar
		 response="{   \"status-code\": \"101\",   \"request_id\": \"i5zx25oq-opy6-HZFN-Po5R-W34QIE0SVJKW\",   \"result\": [     {       \"details\": {         \"aadhaar\": \"630711930807\",         \"address\": \"PUSHPA BUNGLOW, PLOT NO 125, AJANTHA CHS,  NANDVALI ROAD, NR. TELEPHONE EXCHANGE, DOMBIVLIEAST, Thane, Maharashtra - 421201 \",         \"name\": \"Omkar Milind Shirhatti \",         \"phone\": \"9819574650\",         \"pin\": \"421201\"       },       \"type\": \"aadhaar_front_top\"     },     {       \"details\": {         \"aadhaar\": \"630711930807\",         \"dob\": \"17/08/1987\",         \"gender\": \"MALE\",         \"name\": \"Omkar Milind Shirhatti\"       },       \"type\": \"aadhaar_front_bottom\"     },     {       \"details\": {         \"aadhaar\": \"630711930807\",         \"address\": \"PUSHPA BUNGLOW, PLOT NO 125, AJANTHA CHS, NANDIVALI ROAD, NR: TELEPHONE EXCHANGE, DOMBIVLI EAST, Thane, Maharashtra - 421201 \",         \"pin\": \"421201\"       },       \"type\": \"aadhaar_back\"     }   ] }";
			
			KycOcrResponsePayload payload=new KycOcrResponsePayload();
			KycOcrResResult ocrResult=new KycOcrResResult();
			ObjectMapper om=new ObjectMapper();
			try {
				JSONObject json=new JSONObject(response);
				
				payload.setStatus_code(Integer.parseInt(json.getString("status-code")));
				
				JSONArray result= json.getJSONArray("result");
				for(int i=0;i<result.length();i++){
					JSONObject jsonObj=result.getJSONObject(i);
					if(jsonObj.getString("type").contains("top")){
						ocrResult.setFront_top(om.readValue(jsonObj.get("details").toString(), KycOcrResFrontTop.class));
						
					}else if(jsonObj.getString("type").contains("pan") || jsonObj.getString("type").contains("front")){
						
						ocrResult.setFront(om.readValue(jsonObj.get("details").toString().replaceAll("\"dob-calculated\":", "\"dob\":"), KycOcrResFront.class));
						
					}else if(jsonObj.getString("type").contains("back")){
						
						ocrResult.setBack(om.readValue(jsonObj.get("details").toString(), KycOcrResBack.class));
						
					}
				}
				payload.setResult(ocrResult);
				
			} catch (Exception e) {
				logger.error("Error in mapping third party responce to entity : " + e);
			}
		return payload;
	}

/*	public static Form16OcrResponsePayload form16OcrJsonToPayload(String response) {
		ObjectMapper om=new ObjectMapper();

		Form16OcrResponsePayload payload=new Form16OcrResponsePayload();
		try {
			
		payload.setResult(
				om.readValue(keysToLowerCase(new JSONObject(response).getJSONObject("result")).toString(), Form16OcrResResult.class));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in mapping third party responce to entity : " + e);
		}
		return payload;
	}*/
	public  static JSONObject keysToLowerCase(JSONObject object) throws JSONException {
		JSONObject jsonObject=new JSONObject();
		
		@SuppressWarnings("unchecked")
		Iterator<String> keysItr = object.keys();
		while(keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);
/*
			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else */if(value instanceof JSONObject) {
				value = keysToLowerCase((JSONObject) value);
			}
			key=key.trim().replaceAll(" ","_").toLowerCase();
			key=key.replaceAll("certificate_no.", "certificate_no");
			jsonObject.put(key, value);
		}
		return jsonObject;
	}
	
}
