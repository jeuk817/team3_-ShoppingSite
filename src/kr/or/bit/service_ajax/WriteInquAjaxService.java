package kr.or.bit.service_ajax;

import kr.or.bit.action.ActionAjax;
import kr.or.bit.action.ActionAjaxData;
import kr.or.bit.model.dao.DAOInquiryPost;
import kr.or.bit.model.dao.DAOMember;
import kr.or.bit.model.dto.DTOMember;
import kr.or.bit.model.dto.DTOPdInqPost;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteInquAjaxService implements ActionAjax {
    @Override
    public ActionAjaxData execute(HttpServletRequest request, HttpServletResponse response) {
       ActionAjaxData ajaxData = new ActionAjaxData();
        DTOPdInqPost dtoPdInqPost = new DTOPdInqPost();
        DAOInquiryPost daoInquiryPost = new DAOInquiryPost();
        int resultRow = 0;
        int resultRow1 = 0;
        /*
        json
        {salePostnum:num
         title:string,
         content:string,
         private:string
          }
         */
        dtoPdInqPost.setId(request.getParameter("id"));
        dtoPdInqPost.setInqContent(request.getParameter("InqContent"));
        dtoPdInqPost.setInqTitle(request.getParameter("Title"));
        dtoPdInqPost.setSaleNum(Integer.parseInt((String) request.getSession().getAttribute("saleNum")));

        resultRow  = daoInquiryPost.lim_inquiryPost_pri(dtoPdInqPost.getInqNum());
        resultRow1 = daoInquiryPost.lim_inquiryPost(dtoPdInqPost);
        if (resultRow != 0 && resultRow1 != 0) {
            ajaxData.setData("success");
        } else {
            ajaxData.setData("fail");
        }
        ajaxData.setContentType("text/plain");

        return ajaxData;
    }
}

