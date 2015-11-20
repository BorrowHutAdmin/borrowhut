package com.borrowhut.ws.service;

import org.json.simple.JSONArray;

public interface UicardService {
public JSONArray  getUicard(int paryId, String pamAuthId,String userLocation);
}
