package com.borrowhut.ws.service;

import org.json.simple.JSONArray;

public interface BorrowhutLookupService {

	JSONArray getLookupdatawithCond(String tablename, String filtercond);

	JSONArray getLookupdata(String tablename);
}
