package kr.or.bit.service_ajax;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import kr.or.bit.action.ActionAjax;
import kr.or.bit.action.ActionAjaxData;
import kr.or.bit.model.dao.DAOProduct;
import kr.or.bit.model.dao.DAOPurchase;
import kr.or.bit.model.dto.DTOProduct;
import kr.or.bit.model.dto.DTOPurchase;

public class PurchaseAjaxService implements ActionAjax {

	@Override
	public ActionAjaxData execute(HttpServletRequest request, HttpServletResponse response) {
		ActionAjaxData ajaxData = new ActionAjaxData();
		
//		imageAddr: "/upload/DAAC4B5B-6D2E-4785-9827-B9ABDDF14066.jpeg"
//		pAmount: 1
//		pName: "초록 바지"
//		pNum: "69"
//		pPrice: 100000
//		pSize: "M"
//		saleNum: 73
//		saleTitle: "두번째 판매글"
		
		JsonArray orders = (JsonArray)request.getAttribute("jsonBody");
		Iterator<JsonElement> iter = orders.iterator();
		
		synchronized (this) {
			
			while(iter.hasNext()) {
				JsonObject order = iter.next().getAsJsonObject();
				int pNum = order.get("pNum").getAsInt();
				int pAmount = order.get("pAmount").getAsInt();
				DTOProduct product = DAOProduct.getDTOProductByPNum(pNum); // 수량만 가져오는 로직으로 바꿔야함
				if(product == null) { // 상품이 더 이상 존재하지 않음
					ajaxData.setData("fail");
					break;
				}
				if(pAmount > product.getpAmount()) { // 수량부족
					ajaxData.setData("fail");
					break;
				} // 추가할 예외: 판매자 탈퇴
			}
			if(ajaxData.getData() == null) { // 구매가능
				iter = orders.iterator();
				while(iter.hasNext()) {
					JsonObject order = iter.next().getAsJsonObject();
					int pNum = order.get("pNum").getAsInt();
					int pAmount = order.get("pAmount").getAsInt();
					int pPrice = order.get("pPrice").getAsInt();
					int saleNum = order.get("saleNum").getAsInt();
					String pName = order.get("pName").getAsString();
					String pSize = order.get("pSize").getAsString();
					String saleTitle = order.get("saleTitle").getAsString();
					String imageAddr = order.get("imageAddr").getAsString();
					
					int resultRow = DAOProduct.decreaseProduct(pNum, pAmount); // resultRow 어떻게 처리할것인지 생각해봐야함..
					String id = (String)request.getSession().getAttribute("memberId");
					DTOPurchase purchase = new DTOPurchase(id, pName, pSize, saleTitle, imageAddr, pNum, pPrice, pAmount, saleNum);
					int resultRow2 = DAOPurchase.insertPurchase(purchase);
				}
				ajaxData.setData("success");
			}
			
		}
		
		ajaxData.setContentType("text/plain");
		
		return ajaxData;
	}

}
