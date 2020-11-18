package kr.or.bit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.or.bit.model.DBManager;
import kr.or.bit.model.dto.DTOReview;

public class DAOReview {
	private static DBManager instance = DBManager.getInstance();
	
	private static final String ryu_SQL_INSERT_REVIEW = "INSERT INTO REVIEW(SALE_NUM, ID, REV_CONTENT, REV_STARS) "
														+ "VALUES(?, ?, ?, ?)";
	
	public static int ryu_insertReview(DTOReview review) {
		int resultRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(ryu_SQL_INSERT_REVIEW);
			pstmt.setInt(1, review.getSaleNum());
			pstmt.setString(2, review.getId());
			pstmt.setString(3, review.getRevContent());
			pstmt.setInt(4, review.getRevStars());
			
			resultRow = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt);
		}
		
		return resultRow;
	}
}
