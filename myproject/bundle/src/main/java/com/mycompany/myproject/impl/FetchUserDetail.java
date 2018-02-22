package com.mycompany.myproject.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SlingServlet(paths="/bin/fetch/userInfo",methods="GET")
public class FetchUserDetail extends SlingAllMethodsServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Logger log=LoggerFactory.getLogger(getClass());
	
	
	@Override
	protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException,IOException {
		String accString=AccessToken.access_token;
		
		try {
			
		
					doPost(request,response,accString);
		
			
			} catch (JSONException e) {
				
				
				e.printStackTrace();
			}
	}
	
	
	protected void doPost(SlingHttpServletRequest request,SlingHttpServletResponse response,String accString) throws ServletException,IOException, JSONException {
		
		String responseString=null;int responseCode=0;
		PrintWriter out=response.getWriter();
		
		
		
		String uri="https://ap2.salesforce.com/services/data/v20.0/query";
		
		HttpClient client=new HttpClient();
		
		GetMethod gm=new GetMethod(uri);
		
		gm.setRequestHeader("Authorization","Bearer "+accString);//set access_code in header, cannot pass as parameter
		
		NameValuePair[] pairs=new NameValuePair[1];
		
			
		pairs[0]=new NameValuePair("q","SELECT Employee_Number__c,First_Name__c,Id,LastName,Name,Position_Classification__c,Position_Title__c,School_Group__c,Surname__c,Work_Extension__c FROM User WHERE Id='00528000002qJbs'");
				
		
		gm.setQueryString(pairs);
		try {
			
			client.executeMethod(gm);
			
			responseString=gm.getResponseBodyAsString();
			responseCode=gm.getStatusCode();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		log.info(responseString+" "+responseCode);
		JSONObject object=new JSONObject(responseString);
		
		
		
		JSONArray queryRecords=object.getJSONArray("records");
		
		
		
		out.write(queryRecords.toString());
		log.info(queryRecords.toString());
	
	}

		
	}


