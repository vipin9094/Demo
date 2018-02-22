package com.mycompany.myproject.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Update information fatched from USER SOBJECT (hardcoded)......

@SlingServlet(paths="/bin/service/updateInfo",methods="GET")
public class UpdateInformation extends SlingAllMethodsServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7284579393214549762L;

	Logger log=LoggerFactory.getLogger(getClass());
    @Override
	protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException,IOException {
		
		String accString=AccessToken.access_token;
		
		
		doPost(request, response,accString);
	}
	
	protected void doPost(SlingHttpServletRequest request,SlingHttpServletResponse response,String accString) throws ServletException,IOException {
		String uri="https://ap2.salesforce.com/services/data/v39.0/sobjects/User";
		JSONObject object=new JSONObject();
		HttpClient client=new HttpClient();
		String userid="00528000002qJbs";
		//PostMethod postMethod=new PostMethod(uri+"?__HttpMethod=PATCH");
		PostMethod postMethod=new PostMethod(uri+ "/" + userid + "?_HttpMethod=PATCH");
		
		postMethod.addRequestHeader("Authorization","Bearer "+accString);
		
		log.info("******************Updating Start**********************");
		
		postMethod.setRequestEntity(new StringRequestEntity("{ \"Employee_Number__c\":\"123456\",\"First_Name__c\":\"Michael_updated\"}","application/json","UTF-8"));
		
		
		
		PrintWriter out=response.getWriter();
		
		int status_code=client.executeMethod(postMethod);
		
		out.write(""+status_code);
		

		log.info("+++++++++++++++++++++++ ExportLead code :"+status_code+postMethod.getResponseBodyAsString());

		

	}
}
