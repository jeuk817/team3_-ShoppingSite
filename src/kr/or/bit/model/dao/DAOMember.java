package kr.or.bit.model.dao;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.bit.model.DBManager;
import kr.or.bit.model.dto.DTOMember;
import kr.or.bit.utils.c_AES256Util;
import kr.or.bit.utils.c_SHAUtil;
import kr.or.bit.utils.c_Salt;


public class DAOMember {
	private static DBManager instance = DBManager.getInstance();
	private static final String SQL_SELECT_MEMBER_BY_ID = "SELECT ID, PWD, NAME, HP, CARD_NUM, ADDRESS, DEL_FLAG"
													+ "FROM MEMBER WHERE ID = ?";
	private static final String SQL_INSERT_MEMBER = "INSERT INTO MEMBER(ID, PWD, NAME, HP, CARD_NUM, ADDRESS) "
													+ "VALUES(?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_MEMBER = "UPDATE MEMBER "
													+ "SET PWD = ?, HP = ?, CARD_NUM = ?, ADDRESS = ? WHERE ID = ?";
	
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
	
	/**
	 * 키파일 생성하지 않고 기존 것 사용
	 * @param member
	 * @return
	 */

	public static int c_insertMember(DTOMember member) {	
		c_SHAUtil sha = new c_SHAUtil();
		c_Salt salt = new c_Salt();		

		
		int resultRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(SQL_INSERT_MEMBER);
			pstmt.setString(1, member.getId());
			String s =salt.readSalt("key.txt");
			System.out.println("소금추가:"+s);

			pstmt.setString(2, sha.getSha512(s+member.getPwd()));
			
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getHp());
			pstmt.setString(5, member.getCardNum());
			pstmt.setString(6, member.getAddress());
			System.out.println(member.toString());		

			System.out.println("1:"+sha.getSha512(member.getPwd()));
			System.out.println("2:"+sha.getSha512(s+member.getPwd()));
			resultRow = pstmt.executeUpdate();
			System.out.println("final:"+member.toString());	
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt);
		}
		
		return resultRow;
	}
	

	public static int c_updateMember(DTOMember member) {
		c_SHAUtil sha = new c_SHAUtil();
		c_Salt salt = new c_Salt();

		
		int resultRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = instance.getConnection();
			pstmt = conn.prepareStatement(SQL_UPDATE_MEMBER);
			String s =salt.readSalt("key.txt");
			pstmt.setString(1, sha.getSha512(s+member.getPwd()));
			pstmt.setString(2, member.getHp());
			pstmt.setString(3, member.getCardNum());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getId());
			System.out.println(sha.getSha512(s+member.getPwd()));
			
			resultRow = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			instance.freeConnection(conn, pstmt);
		}
		
		return resultRow;
	}
	
	private static DTOMember setDTOMember(ResultSet rs) throws SQLException {
		String id = rs.getString("ID").trim();
		String pwd = rs.getString("PWD").trim();
		String name = rs.getString("NAME").trim();
		String hp = rs.getString("HP").trim();
		String cardNum = rs.getString("CARD_NUM").trim();
		String address = rs.getString("ADDRESS").trim();
		
		DTOMember member = new DTOMember(id, pwd, name, hp, cardNum, address);
		return member;
	}
}
