package kr.or.bit.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.bit.model.DBManager;
import kr.or.bit.model.dto.DTOMember;
import kr.or.bit.utils.c_SHAUtil;
import kr.or.bit.utils.c_Salt;

public class DAOMember {
	private static DBManager instance = DBManager.getInstance();
	private static final String SQL_SELECT_MEMBER_BY_ID = "SELECT * FROM MEMBER WHERE ID = ?";
	private static final String SQL_INSERT_MEMBER = "INSERT INTO MEMBER(ID, PWD, NAME, HP, CARD_NUM, ADDRESS) "
													+ "VALUES(?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_MEMBER = "UPDATE MEMBER "
													+ "SET PWD = ?, HP = ?, CARD_NUM = ?, ADDRESS = ? WHERE ID = ?";
	private static final String SQL_DELETE_MEMBER ="UPDATE MEMBER SET DEL_FLAG = 'Y' WHERE ID = ?";
	private static final String SQL_REGIST_SELLER ="UPDATE MEMBER SET SEL_FLAG = 'Y' WHERE ID =?";
	private static final String SQL_DELETE_SELLER ="UPDATE MEMBER SET SEL_FLAG = 'N' WHERE ID =?";
	
	public static DTOMember getMemberById(String id) {
		DTOMember member = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_MEMBER_BY_ID);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				member = setDTOMember(rs);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt, rs);
		}
		
		return member;
	}
	
	public static int insertMember(DTOMember member) {
	
		int resultRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(SQL_INSERT_MEMBER);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getHp());
			pstmt.setString(5, member.getCardNum());
			pstmt.setString(6, member.getAddress());			
			resultRow = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt);
		}
		
		return resultRow;
	}
	
	public static int updateMember(DTOMember member) {
		int resultRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(SQL_UPDATE_MEMBER);
			pstmt.setString(1, member.getPwd());
			pstmt.setString(2, member.getHp());
			pstmt.setString(3, member.getCardNum());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getId());
			
			resultRow = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt);
		}
		
		return resultRow;
	}

	public static int lim_DeleteMember(String id) {
		int resultRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = instance.getConnection();
			pstmt =conn.prepareStatement(SQL_DELETE_MEMBER);
			pstmt.setString(1,id);

			resultRow = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultRow;
	}
	public static int lim_RegistSeller(String id) {
		int resultRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = instance.getConnection();
			pstmt =conn.prepareStatement(SQL_REGIST_SELLER);
			pstmt.setString(1,id);

			resultRow = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultRow;
	}

	public static int lim_DeleteSeller(String id) {
		int resultRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = instance.getConnection();
			pstmt =conn.prepareStatement(SQL_DELETE_SELLER);
			pstmt.setString(1,id);

			resultRow = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultRow;
	}
	
	public static DTOMember setDTOMember(ResultSet rs) throws SQLException {
		String id = rs.getString("ID").trim();
		String pwd = rs.getString("PWD").trim();
		String name = rs.getString("NAME").trim();
		String hp = "";
		String cardNum = "";
		String address = "";
		String selFlag = rs.getString("SEL_FLAG").trim();
		
		if(rs.getString("HP") != null) hp = rs.getString("HP").trim();
		if(rs.getString("CARD_NUM") != null) cardNum = rs.getString("CARD_NUM").trim();
		if(rs.getString("ADDRESS") != null) address = rs.getString("ADDRESS").trim();
		
		DTOMember member = new DTOMember(id, pwd, name, hp, cardNum, address, selFlag);
		return member;
	}

}
