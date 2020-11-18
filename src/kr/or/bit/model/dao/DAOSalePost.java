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
import kr.or.bit.model.dto.DTOSalePost;

public class DAOSalePost {
	private static DBManager instance = DBManager.getInstance();
	
	private static final String SQL_SELECT_SALEPOSTS_BY_CATEGORY = "SELECT * FROM SALE_POST WHERE CATEGORY_NUM = ? ORDER BY SALE_CREATED_AT DESC";
	private static final String SQL_SELECT_IMAGES_BY_SALE_NUM = "SELECT IMAGE_ADR FROM IMAGES WHERE SALE_NUM = ?";
	private static final String SQL_SELECT_SALEPOST_BY_SALE_NUM = "SELECT * FROM SALE_POST WHERE SALE_NUM = ?";
	private static final String SQL_SELECT_SALEPOSTS_BY_SEL_NUM = "SELECT * FROM SALE_POST WHERE SEL_NUM = ?";
	private static final String ryu_SQL_INSERT_SALE_POST = "INSERT INTO SALE_POST(SEL_NUM, CATEGORY_NUM, SALE_TITLE, SALE_CONTENT) "
															+ "VALUES(?, ?, ?, ?)";
	private static final String ryu_SQL_SELECT_LAST_ROW_FROM_SALE_POST_BY_SEL_NUM = 
			"SELECT * FROM (SELECT * FROM SALE_POST WHERE SEL_NUM = ? ORDER BY SALE_NUM DESC) WHERE ROWNUM = 1";
	private static final String ryu_SELECT_ALL_ORDER_BY_CREATED_AT_DESC = "SELECT * FROM SALE_POST ORDER BY SALE_CREATED_AT DESC";
	private static final String ryu_SELECT_P_NUM_FROM_PD_SALE_POST = "SELECT P_NUM FROM PD_SALE_POST WHERE SALE_NUM = ?";
	
	private static String ryu_getInsertAllPdSalePostQuery(int length) {
		String insertQuery = "INSERT INTO PD_SALE_POST(SALE_NUM, P_NUM) ";
		String selectFronDual = "SELECT ?, ? FROM DUAL";
		for(int i = 0; i <length - 1; i++) {
			insertQuery += selectFronDual + " UNION ALL ";
		}
		
		return insertQuery + selectFronDual;
	}
	
	private static String ryu_getInsertAllImagesQuery(int length) {
		String insertQuery = "INSERT INTO IMAGES(SALE_NUM, IMAGE_ADR, IMAGE_MAIN_FLAG) ";
		String selectFronDual = "SELECT ?, ?, ? FROM DUAL";
		for(int i = 0; i <length - 1; i++) {
			insertQuery += selectFronDual + " UNION ALL ";
		}
		
		return insertQuery + selectFronDual;
	}
	
	public static List<DTOSalePost> getSalePostListDesc(){
		List<DTOSalePost> salePostList = new ArrayList<DTOSalePost>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(ryu_SELECT_ALL_ORDER_BY_CREATED_AT_DESC);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DTOSalePost salePost = DAOSalePost.setDTOSalePost(rs);
				DAOSalePost.setDTOSalePostImages(salePost, conn);
				int selNum = salePost.getSelNum();
				pstmt2 = conn.prepareStatement("select p_num from product where sel_num = ? and rownum = 1");
				pstmt2.setInt(1, selNum);
				
				rs2 = pstmt2.executeQuery();
				rs2.next();
				int pNum = rs2.getInt("p_num");
				
				pstmt2 = conn.prepareStatement("select p_price from product where p_num = ?");
				pstmt2.setInt(1, pNum);
				rs2 = pstmt2.executeQuery();
				rs2.next();
				int pPrice = rs2.getInt("p_price");
				salePost.setSalPrice(pPrice);
				
				salePostList.add(salePost);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt, rs);
		}
		return salePostList;
	}
	
	public static List<DTOSalePost> getSalePostListByCategory(int categoryNum){
		List<DTOSalePost> salePostList = new ArrayList<DTOSalePost>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_SALEPOSTS_BY_CATEGORY);
			pstmt.setInt(1, categoryNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DTOSalePost salePost = DAOSalePost.setDTOSalePost(rs);
				DAOSalePost.setDTOSalePostImages(salePost, conn);
				int selNum = salePost.getSelNum();
				pstmt2 = conn.prepareStatement("select p_num from product where sel_num = ? and rownum = 1");
				pstmt2.setInt(1, selNum);
				
				rs2 = pstmt2.executeQuery();
				rs2.next();
				int pNum = rs2.getInt("p_num");
				
				pstmt2 = conn.prepareStatement("select p_price from product where p_num = ?");
				pstmt2.setInt(1, pNum);
				rs2 = pstmt2.executeQuery();
				rs2.next();
				int pPrice = rs2.getInt("p_price");
				salePost.setSalPrice(pPrice);
				
				salePostList.add(salePost);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt, rs);
		}
		return salePostList;
	}
	
	public static DTOSalePost getSalePostBySaleNum(int saleNum) {
		DTOSalePost salePost = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_SALEPOST_BY_SALE_NUM);
			pstmt.setInt(1, saleNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				salePost = DAOSalePost.setDTOSalePost(rs);
				DAOSalePost.setDTOSalePostImages(salePost, conn);
				DAOSalePost.setDTOSalePostPNums(salePost, conn);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt);
		}
		
		return salePost;
	}
	
	public static List<DTOSalePost> ryu_getSalePostListBySelNum(int selNum) {
		List<DTOSalePost> salePostList = new ArrayList<DTOSalePost>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_SALEPOSTS_BY_SEL_NUM);
			pstmt.setInt(1, selNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DTOSalePost salePost = DAOSalePost.setDTOSalePost(rs);
				DAOSalePost.setDTOSalePostImages(salePost, conn);
				salePostList.add(salePost);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt, rs);
		}
		
		return salePostList;
	}
	
	public static int ryu_insertSalePostAndAll(DTOSalePost salePost) {
		int resultRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(ryu_SQL_INSERT_SALE_POST);
			pstmt.setInt(1, salePost.getSelNum());
			pstmt.setInt(2, salePost.getCategoryNum());
			pstmt.setString(3, salePost.getSaleTitle());
			pstmt.setString(4, salePost.getSaleContent());
			
			resultRow = pstmt.executeUpdate();
//			instance.freeConnection(pstmt);
			
			pstmt = conn.prepareStatement(ryu_SQL_SELECT_LAST_ROW_FROM_SALE_POST_BY_SEL_NUM);
			pstmt.setInt(1, salePost.getSelNum());
			rs = pstmt.executeQuery();
			rs.next();
			int saleNum = rs.getInt("SALE_NUM");
			
			List<Integer> pNums = salePost.getpNums();
			String insertAllPdSalePostQuery = ryu_getInsertAllPdSalePostQuery(pNums.size());
			pstmt = conn.prepareStatement(insertAllPdSalePostQuery);
			ryu_setInsertAllPdSalePostQuery(pstmt, saleNum, pNums);
			
			resultRow = pstmt.executeUpdate();
			
			List<String> imageAddrs = salePost.getImageAddrs();
			String insertAllImagesQuery = ryu_getInsertAllImagesQuery(imageAddrs.size());
			pstmt = conn.prepareStatement(insertAllImagesQuery);
			ryu_setInsertAllImagesQuery(pstmt, saleNum, imageAddrs);
			
			resultRow = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt, rs);
		}
		
		return resultRow;
	}
	
	private static void ryu_setInsertAllImagesQuery(PreparedStatement pstmt, int saleNum, List<String> imageAddrs) throws SQLException {
		Iterator<String> it = imageAddrs.iterator();
		int i = 1;
		while(it.hasNext()){
			String imageAddr = it.next();
			pstmt.setInt(i++, saleNum);
			pstmt.setString(i++, imageAddr);
			pstmt.setString(i++, i == 4 ? "Y" : "N");			
		}
	}
	
	private static void ryu_setInsertAllPdSalePostQuery(PreparedStatement pstmt, int saleNum, List<Integer> pNums) throws SQLException {
		Iterator<Integer> it = pNums.iterator();
		int i = 1;
		while(it.hasNext()){
			int pNum = it.next();
			pstmt.setInt(i++, saleNum);
			pstmt.setInt(i++, pNum);
		}
	}
	
	private static DTOSalePost setDTOSalePost(ResultSet rs) throws SQLException {
		int saleNum = rs.getInt("SALE_NUM");
		int selNum = rs.getInt("SEL_NUM");
		int categoryNum = rs.getInt("CATEGORY_NUM");
		String saleTitle = "";
		String saleContent = "";
		if(rs.getString("SALE_TITLE") != null) saleTitle = rs.getString("SALE_TITLE").trim();
		if(rs.getString("SALE_CONTENT") != null) saleContent = rs.getString("SALE_CONTENT").trim();
		Date saleCreatedAt = rs.getDate("SALE_CREATED_AT");
		
		DTOSalePost salePost = new DTOSalePost(saleNum, selNum, categoryNum, saleTitle, saleContent, saleCreatedAt);
		return salePost;
	}
	
	private static void setDTOSalePostImages(DTOSalePost salePost, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(SQL_SELECT_IMAGES_BY_SALE_NUM);
			pstmt.setInt(1, salePost.getSaleNum());
			rs = pstmt.executeQuery();
			List<String> imageAddrs = new ArrayList<String>();
			while(rs.next()) {
				String imageAdr = rs.getString("IMAGE_ADR").trim();
				imageAddrs.add(imageAdr);
			}
			salePost.setImageAddrs(imageAddrs);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(pstmt);
			instance.freeConnection(rs);
		}
	}
	
	private static void setDTOSalePostPNums(DTOSalePost salePost, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(ryu_SELECT_P_NUM_FROM_PD_SALE_POST);
			pstmt.setInt(1, salePost.getSaleNum());
			rs = pstmt.executeQuery();
			List<Integer> pNums = new ArrayList<Integer>();
			while(rs.next()) {
				int pNum = rs.getInt("P_NUM");
				pNums.add(pNum);
			}
			salePost.setpNums(pNums);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(pstmt);
			instance.freeConnection(rs);
		}
	}
}
