package com.mycompany.myproject.impl;



import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycompany.myproject.UpdateInfoInterface;


@Component
@Service
public class UpdateService implements UpdateInfoInterface {
	int status_code=0;
	
	Logger log=LoggerFactory.getLogger(getClass());
	public int updateInfo(String Employee_no,String first_name) {
		String accString=AccessToken.access_token;
		String uri="https://ap2.salesforce.com/services/data/v39.0/sobjects/User";
		JSONObject object=new JSONObject();
		HttpClient client=new HttpClient();
		String userid="00528000002qJbs";
		//PostMethod postMethod=new PostMethod(uri+"?__HttpMethod=PATCH");
		PostMethod postMethod=new PostMethod(uri+ "/" + userid + "?_HttpMethod=PATCH");
		
		postMethod.addRequestHeader("Authorization","Bearer "+accString);
		
		log.info("******************Updating Start**********************");
		
		try {
			postMethod.setRequestEntity(new StringRequestEntity("{ \"Employee_Number__c\":\""+Employee_no+"\",\"First_Name__c\":\""+first_name+"\"}","application/json","UTF-8"));
			
			status_code=client.executeMethod(postMethod);			
			log.info("+++++++++++++++++++++++ ExportLead code :"+status_code);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return status_code;
		
		
		
		
	

		
	}

}
