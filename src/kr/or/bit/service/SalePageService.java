package kr.or.bit.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.model.dao.DAOSalePost;
import kr.or.bit.model.dto.DTOSalePost;

public class SalePageService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		int saleNum = Integer.parseInt(request.getParameter("saleNum"));
		DTOSalePost salePost = DAOSalePost.getSalePostBySaleNum(saleNum);
//		Date dateNow = new Date();
//		System.out.println(dateNow);
//		DTOSalePost salePost = new DTOSalePost(1, 1, 1, "슬랙스", "쌉니다싸요", dateNow);
		request.setAttribute("salePost", salePost);
		
		forward.setRedirect(false);
		forward.setPath("Sale.jsp");
		
		return forward;
	}

}
