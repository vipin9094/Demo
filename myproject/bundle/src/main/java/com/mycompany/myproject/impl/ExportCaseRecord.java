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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SlingServlet(paths="/bin/service/exportCaseRecord",methods="GET")
public class ExportCaseRecord extends SlingAllMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4532360122379318986L;
	
	Logger logger=LoggerFactory.getLogger(getClass());
	@Override
	protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException,IOException {
			
		String accString=AccessToken.access_token;
		
		doPost(request, response,accString);
		
		
	}
	
	protected void doPost(SlingHttpServletRequest request,	SlingHttpServletResponse response,String access_token) throws ServletException,IOException{
		
		String uri="https://ap2.salesforce.com/services/data/v39.0/sobjects/Case/";
		
		
		HttpClient client=new HttpClient();
		PostMethod postMethod=new PostMethod(uri);
		
		postMethod.addRequestHeader("Authorization","Bearer "+access_token);
		
		postMethod.setRequestEntity(new StringRequestEntity("{ \"Origin\":\"Phone\",\"Status\":\"New\"}","application/json","UTF-8"));

		

		PrintWriter out=response.getWriter();
		
		int status_code=client.executeMethod(postMethod);
		
		out.write(""+status_code);
		

		logger.info("+++++++++++++++++++++++ ExportCase code :"+status_code);
		
	}
	
	

}
