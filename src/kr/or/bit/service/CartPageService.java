package kr.or.bit.service;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class CartPageService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
//		{pNum: "27", pPrice: 70000, pAmount: 1, saleNum: 34}
		JsonArray products = (JsonArray)request.getAttribute("jsonBody");
		request.setAttribute("products", products);
		
		forward.setRedirect(false);
		forward.setPath("Cart.jsp");
		
		return forward;
	}

}
