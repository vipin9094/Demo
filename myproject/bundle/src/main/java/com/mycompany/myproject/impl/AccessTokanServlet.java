package com.mycompany.myproject.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SlingServlet(paths = "/services/token/oauth", methods = "GET")
public class AccessTokanServlet extends SlingAllMethodsServlet {
                /**
	 * 
	 */
	private static final long serialVersionUID = 7280432694361110858L;
				private Logger logger = LoggerFactory.getLogger(AccessTokanServlet.class);

                protected void doGet(SlingHttpServletRequest request, final SlingHttpServletResponse response) throws ServletException,IOException {
                	
                	AccessToken accessToken=new AccessToken();
                	
                	
                	
                                logger.info("-----inside servlet-------");
                               
                                                     
                                
                                
                                logger.info("-----------exit--------------");
                               if((AccessToken.access_token)!=null){ 
                                PrintWriter out=response.getWriter();
                                out.write("done");
                               }
                }
}

