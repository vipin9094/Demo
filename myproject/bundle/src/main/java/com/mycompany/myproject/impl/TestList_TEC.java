package com.mycompany.myproject.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import com.mycompany.myproject.TECInterface;

@Component
@Service
public class TestList_TEC implements TECInterface  {
	Map<String, String> tecMap=new HashMap<String, String>();
	
	
	
	@Activate
	public void testList() {
					
		tecMap.put("name","sarang");
		tecMap.put("company","capgemini");
	
		}
	
	public Map<String,String> getTecMap(){
				
		return tecMap;
	}

	
}
