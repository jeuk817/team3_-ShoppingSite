package kr.or.bit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.or.bit.model.DBManager;
import kr.or.bit.model.dto.DTOPurchase;

public class DAOPurchase {
	private static DBManager instance = DBManager.getInstance();
	
	private static final String SQL_INSERT_INTO_PURCHASE = "INSERT INTO "
			+ "PURCHASE(ID, P_NUM, P_NAME, P_PRICE, P_SIZE, O_AMOUNT, SALE_NUM, SALE_TITLE) "
															+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_ALL_BY_MEMBER_ID = "SELECT * FROM PURCHASE WHERE ID = ?";
	
	public static int insertPurchase(DTOPurchase purchase) {
		int resultRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(SQL_INSERT_INTO_PURCHASE);
			pstmt.setString(1, purchase.getId());
			pstmt.setInt(2, purchase.getpNum());
			pstmt.setString(3, purchase.getpName());
			pstmt.setInt(4, purchase.getpPrice());
			pstmt.setString(5, purchase.getpSize());
			pstmt.setInt(6, purchase.getoAmount());
			pstmt.setInt(7, purchase.getSaleNum());
			pstmt.setString(8, purchase.getSaleTitle());
			
			resultRow = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt);
		}
		
		return resultRow;
	}
	
	public static List<DTOPurchase> getPurchaseListByMemberId(String id) {
		List<DTOPurchase> purchaseList = new ArrayList<DTOPurchase>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_ALL_BY_MEMBER_ID);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DTOPurchase purchase = DAOPurchase.setDTOPurchase(rs);
				purchaseList.add(purchase);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt, rs);
		}
		
		return purchaseList;
	}
	
	private static DTOPurchase setDTOPurchase(ResultSet rs) throws SQLException {
		int oNum = rs.getInt("O_NUM");
		String id = rs.getString("ID").trim();
		int pNum = rs.getInt("P_NUM");
		int oAmount = rs.getInt("O_NUM");
		Date oCreatedAt = rs.getDate("O_CREATED_AT");
		
		DTOPurchase purchase = new DTOPurchase(oNum, id, pNum, oAmount, oCreatedAt);
		
		return purchase;
	}
}
