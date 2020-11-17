package kr.or.bit.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.model.dao.DAOProduct;
import kr.or.bit.model.dao.DAOSalePost;
import kr.or.bit.model.dao.DAOSeller;
import kr.or.bit.model.dto.DTOMember;
import kr.or.bit.model.dto.DTOProduct;
import kr.or.bit.model.dto.DTOSalePost;
import kr.or.bit.model.dto.DTOSeller;

public class SalePageService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		int saleNum = Integer.parseInt(request.getParameter("saleNum"));
		DTOSalePost salePost = DAOSalePost.getSalePostBySaleNum(saleNum);
		int selNum = salePost.getSelNum();
		DTOMember selMember = DAOSeller.ryu_getMemberBySelNum(selNum);
		DTOSeller seller = DAOSeller.ryu_getSellerBySelNum(selNum);
		List<Integer> pNums = salePost.getpNums();
		List<DTOProduct> productList = DAOProduct.getProductListByPNum(pNums);
		request.setAttribute("selMember", selMember);
		request.setAttribute("seller", seller);
		request.setAttribute("salePost", salePost);
		request.setAttribute("productList", productList);
		
		forward.setRedirect(false);
		forward.setPath("Sale.jsp");
		
		return forward;
	}

}
