package com.borrowhut.ws.service;

import org.json.simple.JSONArray;

import com.borrowhut.ws.exception.UiCardNotFoundException;

public interface UicardService {
public JSONArray  getUicard(int paryId, float latittude,float longitude) throws UiCardNotFoundException;
}
