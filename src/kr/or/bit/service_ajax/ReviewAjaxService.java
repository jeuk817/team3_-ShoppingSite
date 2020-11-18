package kr.or.bit.service_ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import kr.or.bit.action.ActionAjax;
import kr.or.bit.action.ActionAjaxData;
import kr.or.bit.model.dao.DAOPurchase;
import kr.or.bit.model.dao.DAOReview;
import kr.or.bit.model.dto.DTOPurchase;
import kr.or.bit.model.dto.DTOReview;

public class ReviewAjaxService implements ActionAjax {

	@Override
	public ActionAjaxData execute(HttpServletRequest request, HttpServletResponse response) {
		ActionAjaxData ajaxData = new ActionAjaxData();
		
		String id = (String)request.getSession().getAttribute("memberId");
		JsonObject body = (JsonObject)request.getAttribute("jsonBody");
		int saleNum = body.get("saleNum").getAsInt();
		int stars = body.get("stars").getAsInt();
		String content = body.get("content").getAsString();
		DTOPurchase purchase = DAOPurchase.ryu_checkPurchaseBySaleNumAndId(saleNum, id);
		System.out.println("purchase: " + purchase);
		if(purchase != null) {
			DTOReview review = new DTOReview(saleNum, id, content, stars);
			int resultRow = DAOReview.ryu_insertReview(review);
			System.out.println("resultRow: " + resultRow);
			if(resultRow == 1) ajaxData.setData("success");
			else ajaxData.setData("fail");			
		} else {
			ajaxData.setData("fail");
		}
		ajaxData.setContentType("text/plain");
		
		return ajaxData;
	}

}
