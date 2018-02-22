package com.mycompany.myproject.impl;

import java.util.ArrayList;
import java.util.List;







import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycompany.myproject.AccessTokenInterface;

@Component
@Service
public class AccessToken implements AccessTokenInterface {
	
	@Reference
	private ResourceResolverFactory resolverFactory;
	public static String access_token=null;
                public void oAuthSessionProvider(String loginHost, String username,String password, String clientId, String secret)
                      {
                			
                			Logger logger = LoggerFactory.getLogger(AccessToken.class);
                                // Set up an HTTP client that makes a connection to REST API.
                                HttpClient httpClient = new HttpClient();
                               // JSONObject object=new JSONObject();
                                PostMethod postMethod = new PostMethod(loginHost+ "/services/oauth2/token");
                                postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                               
                                
                                List<NameValuePair> parametersBody = new ArrayList<NameValuePair>();
                                
                                //parametersBody.add(new NameValuePair("response_type", "token"));
                                parametersBody.add(new NameValuePair("grant_type", "password"));
                                parametersBody.add(new NameValuePair("username", username));
                                parametersBody.add(new NameValuePair("password", password));
                                parametersBody.add(new NameValuePair("client_id", clientId));
                                parametersBody.add(new NameValuePair("client_secret", secret));
                                //parametersBody.add(new NameValuePair("redirect_uri","https://localhost:4502/services/test/oauth"));
                                NameValuePair[] nameValuePairs = new NameValuePair[parametersBody.size()];
                                
                                for (int i = 0; i < parametersBody.size(); i++) {
                                                nameValuePairs[i] = parametersBody.get(i);
                                }
                                postMethod.setRequestBody(nameValuePairs);
                                
                                int statusCode = 0;
								try {
									statusCode = httpClient.executeMethod(postMethod);
									
									
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

                                if (statusCode != HttpStatus.SC_OK) {
                                                System.out.println(("HTTP Method failed: " + postMethod
                                                                                .getStatusLine()));
                                }
                                JSONObject object=null;
								try {
									object = new JSONObject(postMethod.getResponseBodyAsString());
									access_token= (String) object.get("access_token");
									
									  logger.info(access_token + "   "+postMethod.getResponseBodyAsString());
								} catch (Exception e) {
									
									e.printStackTrace();
								}
                                
                                logger.info("******************************************************************************");
                               
                             
                                                                   
                         }
}

                
