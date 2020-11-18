package kr.or.bit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import kr.or.bit.model.DBManager;
import kr.or.bit.model.dto.DTOProduct;
import kr.or.bit.model.dto.DTOReview;

public class DAOReview {
	private static DBManager instance = DBManager.getInstance();
	
	private static final String ryu_SQL_INSERT_REVIEW = "INSERT INTO REVIEW(SALE_NUM, ID, REV_CONTENT, REV_STARS) "
														+ "VALUES(?, ?, ?, ?)";
	private static final String ryu_SELECT_ALL_REVIEW_BY_SALE_NUM = "SELECT * FROM REVIEW WHERE SALE_NUM = ?";
	
	public static List<DTOReview> ryu_getReviewListBySaleNum(int saleNum){
		List<DTOReview> reviewList = new ArrayList<DTOReview>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(ryu_SELECT_ALL_REVIEW_BY_SALE_NUM);
			pstmt.setInt(1, saleNum);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DTOReview review = DAOReview.setDTOReview(rs);
				reviewList.add(review);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt, rs);
		}
		
		return reviewList;
	}
	
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
	
	private static DTOReview setDTOReview(ResultSet rs) throws SQLException {
		int revNum = rs.getInt("REV_NUM");
		int saleNum = rs.getInt("SALE_NUM");
		String id = rs.getString("ID");
		String revContent = rs.getString("REV_CONTENT");
		int revStars = rs.getInt("REV_STARS");
		Date revCreatedAt = rs.getDate("REV_CREATED_AT");
		
		DTOReview review = new DTOReview(revNum, saleNum, id, revContent, revStars, revCreatedAt);
		
		return review;
	}
}
