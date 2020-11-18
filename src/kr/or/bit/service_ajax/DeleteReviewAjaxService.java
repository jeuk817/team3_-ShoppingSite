package kr.or.bit.service_ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.ActionAjax;
import kr.or.bit.action.ActionAjaxData;
import kr.or.bit.model.dao.DAOReview;

public class DeleteReviewAjaxService implements ActionAjax {

	@Override
	public ActionAjaxData execute(HttpServletRequest request, HttpServletResponse response) {
		ActionAjaxData ajaxData = new ActionAjaxData();
		
		String id = (String)request.getSession().getAttribute("memberId");
		int revNum = Integer.parseInt(request.getParameter("revNum"));
		String revId = DAOReview.ryu_getIdByRevNum(revNum);
		if(id.equals(revId)) {
			int resultRow = DAOReview.ryu_deleteReviewByRevNum(revNum);
			if(resultRow > 0) ajaxData.setData("success");
			else ajaxData.setData("fail");
		} else {
			ajaxData.setData("fail");
		}
		ajaxData.setContentType("text/plain");
		
		return ajaxData;
	}

}
