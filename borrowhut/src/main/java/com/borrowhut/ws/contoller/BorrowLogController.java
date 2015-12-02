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

import com.borrowhut.ws.domain.BorrowLifecycleEvent;
import com.borrowhut.ws.domain.BorrowLog;
import com.borrowhut.ws.domain.BorrowStatus;
import com.borrowhut.ws.domain.Party;
import com.borrowhut.ws.domain.ProductListing;
import com.borrowhut.ws.helper.ProductHelper;
import com.borrowhut.ws.repository.BorrowLogRepository;
import com.borrowhut.ws.repository.BorrowStatusRepository;
import com.borrowhut.ws.repository.ProductListingRepository;
import com.borrowhut.ws.service.BorrowService;

@Component
@Path("/borrow")
public class BorrowLogController {

	private final BorrowService borrowService;

	@Inject
	public BorrowLogController(final BorrowService borrowService) {
		// TODO Auto-generated constructor stub
		this.borrowService = borrowService;
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

		if (BORROW_EVENT == null || BORROW_EVENT.trim().equals("")) {
			throw new IllegalArgumentException("BORROW_EVENT cannot be null or empty");
		} else if (!(BORROW_EVENT.equals("RESERVE") || BORROW_EVENT.equals("BORROWED"))) {
			throw new IllegalArgumentException("incorrect parameter");
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
			throw new IllegalArgumentException("incorrect parameter");
		if (!ProductHelper.validateEvents(bleevent) )
			throw new IllegalArgumentException("incorrect parameter(s)");
		Boolean b = borrowService.progressBorrowTXNStatus(bolid, bleevent);
		JSONObject result = new JSONObject();
		result.put("result", b == true ? "success" : "failure");
		return result;
	}
}
