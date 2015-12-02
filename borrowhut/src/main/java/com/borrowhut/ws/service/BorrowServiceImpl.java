package com.borrowhut.ws.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.borrowhut.ws.domain.BorrowLifecycleEvent;
import com.borrowhut.ws.domain.BorrowLog;
import com.borrowhut.ws.domain.BorrowStatus;
import com.borrowhut.ws.domain.Party;
import com.borrowhut.ws.domain.ProductListing;
import com.borrowhut.ws.repository.BorrowLogRepository;
import com.borrowhut.ws.repository.BorrowStatusRepository;
import com.borrowhut.ws.repository.CustomBorrowRepository;
import com.borrowhut.ws.repository.ProductListingRepository;

@Service
@Validated
public class BorrowServiceImpl implements BorrowService{
	private BorrowLogRepository borrowLogRepository;
	private ProductListingRepository productListingRepository;
	private BorrowStatusRepository borrowStatusRepository;
@Autowired
private CustomBorrowRepository customBorrowRepository;
	@Inject
	public BorrowServiceImpl(BorrowLogRepository borrowLogRepository,
			ProductListingRepository productListingRepository, BorrowStatusRepository borrowStatusRepository) {
		// TODO Auto-generated constructor stub
		this.borrowLogRepository = borrowLogRepository;
		this.productListingRepository = productListingRepository;
		this.borrowStatusRepository = borrowStatusRepository;
	}
	@Transactional
	@Override
	public JSONObject createBorrowTxn(int plsid, int lenderptyid, int borrowerptyid, String startDate, String endDate,String bleEvent) {
		// TODO Auto-generated method stub
		
		BorrowLog borrowLog = new BorrowLog();
		Party p = new Party();
		p.setPtyId(borrowerptyid);
		borrowLog.setParty(p);
		borrowLog.setBolStart(startDate);
		borrowLog.setBolEnd(endDate);
		/* ProductListing pl =productListingRepository.getOne(); */
		ProductListing pl = new ProductListing();
		pl.setPlsId(plsid);
		pl.setPtyId(lenderptyid);
		borrowLog.setProductListing(pl);
		BorrowLog borrowed = borrowLogRepository.save(borrowLog);
		borrowLogRepository.flush();
		int bol_id=borrowed.getBolId(); 
		System.out.println(borrowed.getBolId());

		BorrowStatus borrowStatus = new BorrowStatus();
		BorrowLifecycleEvent bleevent = new BorrowLifecycleEvent();
		System.out.println("event created");
		bleevent.setBleEvent(bleEvent);
		borrowStatus.setBstStartdate(new Date());
		borrowStatus.setBorrowLog(borrowed);

		borrowStatus.setBorrowLifecycleEvent(bleevent);
		borrowStatusRepository.save(borrowStatus);
		borrowStatusRepository.flush();
		System.out.println("entered");
		JSONObject result = new JSONObject();
		result.put("BOL_ID", bol_id);
		return result;
		
		
		
	}
	@Transactional
	@Override
	public Boolean progressBorrowTXNStatus(int bolid, String event) {

		// TODO Auto-generated method stub
	List borrowStatus=	customBorrowRepository.getBorrowStatusBybolidAndEnddate(bolid, null);
	
	if(borrowStatus!=null && borrowStatus.size()>0 ){
		
	Map<String,Object>	borrowstatus=(Map)borrowStatus.get(0);
	String eventExisting= borrowstatus.get("BLE_EVENT").toString();
	    switch(event.trim()){
	    case "BORROWED":
	    	if(!eventExisting.equals("RESERVE"))
	    		throw new IllegalArgumentException("invalid event given as input");
	    	break;
	    case "CHECKEDIN":
	    	if(!eventExisting.equals("CHECKDOUT"))
	    		throw new IllegalArgumentException("invalid event given as input");
	    	break;
	    case "CHECKEDOUT":
	    	if(!eventExisting.equals("BORROWED"))
	    		throw new IllegalArgumentException("invalid event given as input");
	    	break;
	    	default:
	    		System.out.println("default");
	    		break;
	    }
	    int borrowStatusId =Integer.parseInt(borrowstatus.get("ID").toString()) ;
	  BorrowStatus borrowstatusrec = borrowStatusRepository.findOne(borrowStatusId);
	  BorrowLog borrowLog =borrowstatusrec.getBorrowLog();
	  borrowstatusrec.setBstEnddate(new Date());
	  borrowStatusRepository.saveAndFlush(borrowstatusrec);
	  
	  BorrowStatus newBorrowrec = new BorrowStatus();
	  newBorrowrec.setBorrowLog(borrowLog);
	  newBorrowrec.setBstStartdate(new Date());
	  BorrowLifecycleEvent ble = new BorrowLifecycleEvent();
	  ble.setBleEvent(event);
	  newBorrowrec.setBorrowLifecycleEvent(ble);
	  borrowStatusRepository.saveAndFlush(newBorrowrec);
	}else {
		return false;
	}
		
		return true;
	
	}
	

}
