package com.borrowhut.ws.contoller;

import java.util.Date;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.borrowhut.ws.domain.BorrowLog;



import com.borrowhut.ws.domain.Party;
import com.borrowhut.ws.domain.ProductListing;
import com.borrowhut.ws.repository.BorrowLogRepository;

@Component
@Path("/createBorrowTXN")
public class BorrowLogController {
	private BorrowLogRepository borrowLogRepository;
	@Inject
	 public BorrowLogController(BorrowLogRepository borrowLogRepository) {
		// TODO Auto-generated constructor stub
		this.borrowLogRepository = borrowLogRepository;
	}
	
	/*  @POST
		@Produces("application/json")
	    public JSONObject save(@Valid BorrowLog borrowlog) {
		  System.out.println("borrowlog  "+borrowlog.toString());
		 
			  BorrowStatus borrowlogstatus = borrowlog.getBorrowStatuses().get(0);
			  
			if(borrowlogstatus==null && borrowlogstatus.getBorrowLifecycleEvent()==null){
				 throw new IllegalArgumentException("incorrect parameter please check json request");
			}else if(borrowlogstatus.getBorrowLifecycleEvent().getBleEvent()==null || borrowlogstatus.getBorrowLifecycleEvent().getBleEvent().trim().equals("")){
				throw new IllegalArgumentException("incorrect parameter(s) BLEEVENT -bleEvent cannot be empty or null");
			}else if(!borrowlogstatus.getBorrowLifecycleEvent().getBleEvent().equalsIgnoreCase("RESERVE") || !borrowlogstatus.getBorrowLifecycleEvent().getBleEvent().equalsIgnoreCase("BORROWED")){
				throw new IllegalArgumentException("incorrect parameter(s),cannot start borrowTXN for other than RESERVE and BORROWED");
			}
		if(	borrowLogRepository.exists(borrowlog.getId()))
		{
			throw new IllegalArgumentException("Already record exist");
			
		}
		borrowlog.setBorrowStatuses(borrowStatuses);
	
		
		
	borrowlog =	borrowLogRepository.save(borrowlog);
		BorrowStatusPK borrowStatusPK = new BorrowStatusPK();
		borrowStatusPK.setBolId(borrowlog.getId().getBolId());
		borrowStatusPK.setBolPtyId(borrowlog.getId().getBolPtyId());
	
		 BorrowStatus b = new BorrowStatus();
		  
	        return new JSONObject();
	        
	        
	    }
*/
	@Transactional
	  @POST
		@Produces("application/json")
	    public JSONObject save(@Valid JSONObject borrowlogreq) throws IllegalAccessException {
	String PLS_ID = 	  borrowlogreq.get("PLS_ID").toString();
	String LENDER_PTY_ID = 	  borrowlogreq.get("LENDER_PTY_ID").toString();
	String BORROWER_PTY_ID = 	  borrowlogreq.get("BORROWER_PTY_ID").toString();
	String START_DATE = 	  borrowlogreq.get("START_DATE").toString();
	String END_DATE = 	  borrowlogreq.get("END_DATE").toString();
	String BORROW_EVENT = 	  borrowlogreq.get("BORROW_EVENT").toString();
	System.out.println("PLS_ID "+PLS_ID);
	
	System.out.println("LENDER_PTY_ID "+LENDER_PTY_ID);
	
	System.out.println("BORROWER_PTY_ID "+BORROWER_PTY_ID);
	System.out.println("START_DATE "+START_DATE);
	System.out.println("END_DATE "+END_DATE);
	System.out.println("BORROW_EVENT "+BORROW_EVENT);
	
	if(BORROW_EVENT==null ||BORROW_EVENT.trim().equals("") ){
		throw new IllegalArgumentException("BORROW_EVENT cannot be null or empty");
	}
	else if( !(BORROW_EVENT.equals("RESERVED") || BORROW_EVENT.equals("BORROWED")) ){
		throw new IllegalArgumentException("incorrect parameter");
	}
	/*BorrowLogPK borrowLogPK =new BorrowLogPK();
	
	borrowLogPK.setBolPtyId(Integer.parseInt(BORROWER_PTY_ID));
	BorrowLog borrowLog =new BorrowLog();
	
	borrowLog.setId(borrowLogPK);
	borrowLog.setBolStart(START_DATE);
	borrowLog.setBolEnd(END_DATE);
	ProductListing p	=new ProductListing();
	p.setPlsId(Integer.parseInt(PLS_ID));
	p.setPtyId(Integer.parseInt(LENDER_PTY_ID));
	borrowLog.setProductListing(p);
	BorrowLog borrowed =borrowLogRepository.save(borrowLog);
int bolid =	borrowed.getId().getBolId();

System.out.println("bolid "+bolid);

BorrowStatusPK bStatusPK = new BorrowStatusPK();
bStatusPK.setBolId(bolid);
bStatusPK.setBleEvent(BORROW_EVENT);
bStatusPK.setBolPtyId(borrowed.getParty().getPtyId());
BorrowStatus borrowStatus = new BorrowStatus();
borrowStatus.setId(bStatusPK);
borrowStatus.setBstStartdate(new Date());*/
	
BorrowLog borrowLog =new BorrowLog();
Party p =	new Party();
p.setPtyId(Integer.parseInt(BORROWER_PTY_ID));
borrowLog.setParty(p);
	borrowLog.setBolStart(START_DATE);
	borrowLog.setBolEnd(END_DATE);
	
	ProductListing pl	= new ProductListing();
	pl.setPlsId(Integer.parseInt(PLS_ID));
	pl.setPtyId(Integer.parseInt(LENDER_PTY_ID));
	borrowLog.setProductListing(pl);
	BorrowLog borrowed =borrowLogRepository.save(borrowLog);
	
System.out.println(borrowed.getBolId());
	        return new JSONObject();
	        
	        
	    }
}
