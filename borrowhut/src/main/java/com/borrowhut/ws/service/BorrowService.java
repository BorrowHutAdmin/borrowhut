package com.borrowhut.ws.service;

import org.json.simple.JSONObject;

public interface BorrowService {
public JSONObject createBorrowTxn(int plsid,int lenderptyid,int borrowerptyid,String startDate,String endDate,String bleEvent);
public Boolean progressBorrowTXNStatus(int bolid,String bleEvent);
public boolean isLenderOwnsProduct(int plsid, int lenderptyid);
//comment
}
