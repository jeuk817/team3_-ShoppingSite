package kr.or.bit.model.dao;

import kr.or.bit.model.DBManager;
import kr.or.bit.model.dto.DTOPdInqPost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOInquiryPost {
    private static DBManager instance = DBManager.getInstance();

    private static final String lim_SQL_UPDATE_INQUIRY_POST_PRIVATE = "UPDATE PD_INQ_POST SET INQ_PRIVATE_FLAG = 'Y' INQ_NUM=?";
    private static final String lim_SQL_INSERT_INQUIRY_POST = "INSERT INTO PD_INQ_POST(INQ_TITLE,INQ_CONTENT) VALUES (?,?)";


    public static int lim_inquiryPost_pri(int inqNum ) {
        int resultRow = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = instance.getConnection();
            pstmt = conn.prepareStatement(lim_SQL_UPDATE_INQUIRY_POST_PRIVATE);
            pstmt.setInt(1,inqNum);
            resultRow = pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }finally {
            instance.freeConnection(conn,pstmt);
        }
        return resultRow;
    }

    public static int lim_inquiryPost(DTOPdInqPost pdInqPost) {
        int resultRow = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = instance.getConnection();
            pstmt = conn.prepareStatement(lim_SQL_INSERT_INQUIRY_POST);
            pstmt.setString(1,pdInqPost.getInqTitle());
            pstmt.setString(2,pdInqPost.getInqContent());
            resultRow = pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            instance.freeConnection(conn,pstmt);
        }
        return resultRow;
    }
}
