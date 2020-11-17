package kr.or.bit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import kr.or.bit.model.DBManager;
import kr.or.bit.model.dto.DTOProduct;

public class DAOProduct {
	private static DBManager instance = DBManager.getInstance();
	
	private static final String SQL_SELECT_PRODUCT_BY_PNUM = "SELECT * FROM PRODUCT WHERE P_NUM = ?";
	private static final String SQL_UPDATE_PRODUCT_P_AMOUNT = "UPDATE PRODUCT "
															+ "SET P_AMOUNT = P_AMOUNT - ? WHERE P_NUM = ?";
	private static final String SQL_SELECT_PRODUCTS_BY_SEL_NUM = "SELECT * FROM PRODUCT WHERE SEL_NUM =?";
	private static final String SQL_INSERT_PRODUCT = "INSERT INTO PRODUCT(SEL_NUM, P_NAME, P_PRICE, P_SIZE, P_AMOUNT, P_DESCRIPTION) "
													+ "VALUES(?, ?, ?, ?, ?, ?)";
	
	private static String getSQL_SELECT_PRODUCTS_BY_PNUMS(int num) {
		String sql = "SELECT * FROM PRODUCT WHERE P_NUM = ?";
		for(int i = 0; i < num; i++) {
			sql += " OR P_NUM = ?";
		}
		return sql;
	}


//	private static final String SQL_SELECT_PRODUCTS_BY_PNUM = "SELECT * FROM PRODUCT WHERE P_NUM = ?";
	
	public static List<DTOProduct> getProductListByPNum(List<Integer> pNums){
		List<DTOProduct> productList = new ArrayList<DTOProduct>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			int sizeProducts = pNums.size();
			String sql = DAOProduct.getSQL_SELECT_PRODUCTS_BY_PNUMS(sizeProducts - 1);
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(sql);
			Iterator<Integer> iter = pNums.iterator();
			
			int i = 1;
			while(iter.hasNext()) {
				int pNum = iter.next();
				pstmt.setInt(i++, pNum);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DTOProduct product = DAOProduct.setDTOProduct(rs);
				productList.add(product);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt, rs);
		}
		
		return productList;
	}


	public static DTOProduct getDTOProductByPNum(int pNum) {
		DTOProduct product = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_PRODUCT_BY_PNUM);
			pstmt.setInt(1, pNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				product = DAOProduct.setDTOProduct(rs);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt, rs);
		}
		
		return product;
	}
	
	public static int decreaseProduct(int pNum, int pAmount) {
		int resultRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(SQL_UPDATE_PRODUCT_P_AMOUNT);
			pstmt.setInt(1, pAmount);
			pstmt.setInt(2, pNum);
			
			resultRow = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt);
		}
		
		return resultRow;
	}
	
	public static List<DTOProduct> ryu_getProductListBySelNum(int selNum) {
		List<DTOProduct> productList = new ArrayList<DTOProduct>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_PRODUCTS_BY_SEL_NUM);
			pstmt.setInt(1, selNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DTOProduct product = DAOProduct.setDTOProduct(rs);
				productList.add(product);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt, rs);
		}
		
		return productList;
	}
	
	public static int ryu_insertProduct(DTOProduct product) {
		int resultRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(SQL_INSERT_PRODUCT);
			pstmt.setInt(1, product.getSelNum());
			pstmt.setString(2, product.getpName());
			pstmt.setInt(3, product.getpPrice());
			pstmt.setString(4, product.getpSize());
			pstmt.setInt(5, product.getpAmount());
			pstmt.setString(6, product.getpDescription());
			
			resultRow = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt);
		}
		
		return resultRow;
	}
	
	private static DTOProduct setDTOProduct(ResultSet rs) throws SQLException {
		int pNum = rs.getInt("P_NUM");
		int selNum = rs.getInt("SEL_NUM");
		String pName = rs.getString("P_NAME").trim();
		int pPrice = rs.getInt("P_PRICE");
		String pSize = rs.getString("P_SIZE").trim();
		int pAmount = rs.getInt("P_AMOUNT");
		String pDescription = rs.getString("P_DESCRIPTION").trim();
		
		DTOProduct product = new DTOProduct(pNum, selNum, pName, pPrice, pSize, pAmount, pDescription);
		
		return product;
	}
	
}
