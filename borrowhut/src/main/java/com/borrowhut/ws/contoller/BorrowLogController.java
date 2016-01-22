package com.borrowhut.ws.contoller;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.borrowhut.ws.helper.BorrowhutConstant;
import com.borrowhut.ws.helper.ProductHelper;
import com.borrowhut.ws.service.BorrowService;

@Component
@Path("/")
public class BorrowLogController {

	private final BorrowService borrowService;

	@Inject
	public BorrowLogController(final BorrowService borrowService) {
		// TODO Auto-generated constructor stub
		this.borrowService = borrowService;

		System.out.println("controller intialized");
	}

	@Path("/createBorrowTXN")
	@POST
	@Produces("application/json")
	public JSONObject createBorrowTXN(@Valid JSONObject borrowlogreq) throws IllegalAccessException {
		
		String PLS_ID = borrowlogreq.get("PLS_ID").toString();
		String LENDER_PTY_ID = borrowlogreq.get("LENDER_PTY_ID").toString();
		String BORROWER_PTY_ID = borrowlogreq.get("BORROWER_PTY_ID").toString();
		String START_DATE = borrowlogreq.get("START_DATE").toString();
		String END_DATE = borrowlogreq.get("END_DATE").toString();
		String BORROW_EVENT = borrowlogreq.get("BORROW_EVENT").toString();

		if (BORROW_EVENT == null || PLS_ID == null || LENDER_PTY_ID == null || BORROWER_PTY_ID == null
				|| START_DATE == null || END_DATE == null) {
			throw new IllegalArgumentException(" input parameter(s) cannot be null or empty");
		} else if (!(BORROW_EVENT.equals(BorrowhutConstant.RESERVED)
				|| BORROW_EVENT.equals(BorrowhutConstant.BORROWED))) {
			throw new IllegalArgumentException("incorrect parameter,only " + BorrowhutConstant.RESERVED + " and "
					+ BorrowhutConstant.BORROWED + " are allowed");
		}else if(!borrowService.isLenderOwnsProduct(Integer.parseInt(PLS_ID), Integer.parseInt(LENDER_PTY_ID))){
			throw new IllegalArgumentException("Product id "+PLS_ID+" is not owned by Party id "+LENDER_PTY_ID);
			
		}
		
		return borrowService.createBorrowTxn(Integer.parseInt(PLS_ID), Integer.parseInt(LENDER_PTY_ID),
				Integer.parseInt(BORROWER_PTY_ID), START_DATE, END_DATE, BORROW_EVENT);

	}

	@Path("/progressBorrowTXNStatus")
	@POST
	@Produces("application/json")
	public JSONObject progressBorrowTXNStatus(@Valid JSONObject progressBorrowTXNStatusreq) {
		int bolid = Integer.parseInt(progressBorrowTXNStatusreq.get("BOL_ID").toString());
		String bleevent = progressBorrowTXNStatusreq.get("BLE_EVENT").toString();
		if (bleevent == null || bolid == 0)
			throw new IllegalArgumentException("input parameter(s) cannot be null or empty");
		if (!ProductHelper.validateEvents(bleevent))
			throw new IllegalArgumentException("incorrect parameter(s),event passed doesnt match with allowed events");
		Boolean b = borrowService.progressBorrowTXNStatus(bolid, bleevent);
		JSONObject result = new JSONObject();
		result.put("result", b == true ? "success" : "failure");
		return result;
	}
}
