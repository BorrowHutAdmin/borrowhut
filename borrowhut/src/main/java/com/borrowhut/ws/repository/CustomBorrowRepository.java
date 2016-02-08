package com.borrowhut.ws.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.borrowhut.ws.domain.BorrowLifecycleEvent;
import com.borrowhut.ws.domain.BorrowLog;
import com.borrowhut.ws.domain.BorrowStatus;

@Repository
public class CustomBorrowRepository {
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	public BorrowStatus getBorrowStatusBybolidAndEnddate(int bloid,Date date){
		return	jdbcTemplate.queryForObject("SELECT * FROM BORROW_STATUS WHERE BOL_ID="+bloid+"  AND BST_ENDDATE IS "+ date,new BorrowStatusMapper());
	/*return	jdbcTemplate.queryForList("SELECT * FROM BORROW_STATUS WHERE BOL_ID="+bloid+"  AND BST_ENDDATE IS NULL");*/
	}
	private static final class BorrowStatusMapper implements RowMapper<BorrowStatus> {

		@Override
		public BorrowStatus mapRow(ResultSet resultset, int rowNum) throws SQLException {
			System.out.println("event created");
			BorrowStatus borrowStatus = new BorrowStatus();
			borrowStatus.setId(resultset.getInt("BS_ID"));
			BorrowLog borrowLog = new BorrowLog();
			borrowLog.setBolId(resultset.getInt("BOL_ID"));
			borrowStatus.setBorrowLog(borrowLog);
			BorrowLifecycleEvent bleevent = new BorrowLifecycleEvent();
			
			bleevent.setBleEvent(resultset.getString("BLE_EVENT"));
			borrowStatus.setBstStartdate(resultset.getDate("BST_STARTDATE"));
			borrowStatus.setBstEnddate(resultset.getDate("BST_ENDDATE"));
			

			borrowStatus.setBorrowLifecycleEvent(bleevent);
			return borrowStatus;
		}
		
	}
}
