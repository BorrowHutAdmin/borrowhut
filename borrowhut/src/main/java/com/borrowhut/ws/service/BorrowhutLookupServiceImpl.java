package com.borrowhut.ws.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.borrowhut.ws.repository.CustomBorrowhutLookupRepository;

@Service
@Validated
public class BorrowhutLookupServiceImpl implements BorrowhutLookupService {

	@Autowired
	private CustomBorrowhutLookupRepository customBorrowhutLookupRepository;
	
	@Override
	public JSONArray getLookupdatawithCond(String tablename, String filtercond) {
		// TODO Auto-generated method stub
		JSONArray getLkpdatawithCond = new JSONArray();
		JSONObject obj;
		Map<String, Object> record;
		List lookupDataList = customBorrowhutLookupRepository.getLookupdatawithCond(tablename,filtercond);		
		for (Iterator itr = lookupDataList.iterator(); itr.hasNext();) {
			record = (Map) itr.next();
			obj = new JSONObject();
			for (Map.Entry<String, Object> entry : record.entrySet()) {
				System.out.println(entry.getKey() + "/" + entry.getValue());
				obj.put(entry.getKey(), entry.getValue());				
			}
			getLkpdatawithCond.add(obj);		
	    }
		return getLkpdatawithCond;
    }

	@Override
	public JSONArray getLookupdata(String tablename) {
		JSONArray getLkpdata = new JSONArray();
		JSONObject obj;
		Map<String, Object> record;
		List lookupDataList = customBorrowhutLookupRepository.getLookupdatawithCond(tablename,"");		
		for (Iterator itr = lookupDataList.iterator(); itr.hasNext();) {
			record = (Map) itr.next();
			obj = new JSONObject();
			for (Map.Entry<String, Object> entry : record.entrySet()) {
				System.out.println(entry.getKey() + "/" + entry.getValue());
				obj.put(entry.getKey(), entry.getValue());				
			}
			getLkpdata.add(obj);		
	    }
		return getLkpdata;
	}
	
}