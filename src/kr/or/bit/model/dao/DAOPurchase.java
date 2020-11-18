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
			+ "PURCHASE(ID, P_NUM, P_NAME, P_PRICE, P_SIZE, O_AMOUNT, SALE_NUM, SALE_TITLE, IMAGE_ADDR) "
															+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_ALL_BY_MEMBER_ID = "SELECT * FROM PURCHASE WHERE ID = ?";
	private static final String SQL_SELECT_PURCHASE_BY_SALE_NUM_AND_ID = "SELECT * FROM PURCHASE "
																		+ "WHERE SALE_NUM = ? AND ID = ?";
	
	
	public static DTOPurchase ryu_checkPurchaseBySaleNumAndId(int saleNum, String id) {
		DTOPurchase purchase = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_PURCHASE_BY_SALE_NUM_AND_ID);
			pstmt.setInt(1, saleNum);
			pstmt.setString(2, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				purchase = DAOPurchase.setDTOPurchase(rs);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt, rs);
		}
		
		return purchase;
	}
	
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
			pstmt.setString(9, purchase.getImageAddr());
			
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
//	private int oNum;
//	private String id;
//	private String pName;
//	private String pSize;
//	private String saleTitle;
//	private String imageAddr;
//	private int pNum;
//	private int pPrice;
//	private int oAmount;
//	private int saleNum;
//	private Date oCreatedAt;
	
	private static DTOPurchase setDTOPurchase(ResultSet rs) throws SQLException {
		int oNum = rs.getInt("O_NUM");
		int pNum = rs.getInt("P_NUM");
		int pPrice = rs.getInt("P_PRICE");
		int oAmount = rs.getInt("O_AMOUNT");
		int saleNum = rs.getInt("SALE_NUM");
		String id = rs.getString("ID").trim();
		String pName = rs.getString("P_NAME").trim();
		String pSize = rs.getString("P_SIZE").trim();
		String saleTitle = rs.getString("SALE_TITLE").trim();
		String imageAddr = rs.getString("IMAGE_ADDR").trim();
		Date oCreatedAt = rs.getDate("O_CREATED_AT");
		
		DTOPurchase purchase = new DTOPurchase(oNum, id, pName, pSize, saleTitle, imageAddr, pNum, pPrice, oAmount, saleNum, oCreatedAt);
		
		return purchase;
	}
}
