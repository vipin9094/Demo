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
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

@SlingServlet(paths="/services/exportLeads/oauth",methods="POST")
public class ExportLeadToSalesforceServlet extends SlingAllMethodsServlet {


	/**
	 *
	 */
	private static final long serialVersionUID = 8803040804349637256L;

	org.slf4j.Logger logger=LoggerFactory.getLogger(ExportLeadToSalesforceServlet.class);
	@Override
	protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException,IOException {
		String accString=AccessToken.access_token;


		try {


			doPost(request, response,accString);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void doPost(SlingHttpServletRequest request,SlingHttpServletResponse response, String accString)throws ServletException,IOException, JSONException {

		String uri="https://ap5.salesforce.com/services/data/v20.0/sobjects/Lead/";
		String access_token=AccessToken.access_token;
		JSONObject object=new JSONObject();
		HttpClient client=new HttpClient();
		PostMethod postMethod=new PostMethod(uri);

		postMethod.addRequestHeader("Authorization","Bearer "+access_token);


		 String first_name=request.getParameter("fname");
	  	 String last_name=request.getParameter("lname");
		 String company_name=request.getParameter("company");
		 String title=request.getParameter("title");
	     String dob=request.getParameter("dob");
	     String email=request.getParameter("email");
	     String product=request.getParameter("product");
	     String designation=request.getParameter("designation");
	     String country=request.getParameter("country");




		//--------------------------------------------------------
		object.put("Company", company_name);
		//object.put("LastName__c",last_name);
		//object.put("FirstName_c",first_name);
		object.put("Title", title);
		//object.put("Date_of_Birth__c", dob);
		//object.put("Email", email);
		//object.put("Product__c", product);
		//object.put("Designation__c", designation);
		object.put("Country", country);

          logger.info(first_name+last_name+title+company_name+access_token);

		postMethod.setRequestEntity(new StringRequestEntity("{ \"Salutation\":\""+title+"\",\"FirstName\":\""+first_name+"\",\"Company\":\""+company_name+"\",\"title\":\""+title+"\",\"lastName\":\""+last_name+"\"}","application/json","UTF-8"));

		PrintWriter out=response.getWriter();

		int status_code=client.executeMethod(postMethod);

		out.write(""+status_code);


		logger.info("+++++++++++++++++++++++ ExportLead code :"+status_code);
	}
}
