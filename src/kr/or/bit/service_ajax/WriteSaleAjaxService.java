package kr.or.bit.service_ajax;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import kr.or.bit.action.ActionAjax;
import kr.or.bit.action.ActionAjaxData;
import kr.or.bit.model.dao.DAOSalePost;
import kr.or.bit.model.dto.DTOSalePost;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class WriteSaleAjaxService implements ActionAjax {

	@Override
	public ActionAjaxData execute(HttpServletRequest request, HttpServletResponse response) {
		ActionAjaxData ajaxData = new ActionAjaxData();
//		{categoryNum:num,
//			title:string,
//			content:string,
//			image:file
//			pNums:[num]
//			}
		
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("/upload");
		System.out.println(path);
		int size = 1024*1024*10;
		try {
			MultipartRequest multiReq = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
			int selNum = Integer.parseInt((String)request.getSession().getAttribute("sellerNum"));
			int categoryNum = Integer.parseInt(multiReq.getParameter("categoryNum"));
			String saleTitle = multiReq.getParameter("title");
			String saleContent = multiReq.getParameter("content");
			
			List<Integer> pNums = new ArrayList<Integer>();
			String[] pNumsStr = multiReq.getParameter("pNums").split(",");
			for(int i = 0; i < pNumsStr.length; i++) {
				pNums.add(Integer.parseInt(pNumsStr[i]));
			}
			
			List<String> imageAddrs = new ArrayList<String>();
			Enumeration<String> fileNames = multiReq.getFileNames();
			while(fileNames.hasMoreElements()) {
				String file = fileNames.nextElement();
				String fileSysName = multiReq.getFilesystemName(file);
				imageAddrs.add(path + "/" + fileSysName);
			}
			
			DTOSalePost salePost = new DTOSalePost(selNum, categoryNum, saleTitle, saleContent, pNums, imageAddrs);
			int resultRow = DAOSalePost.ryu_insertSalePostAndAll(salePost);
			if(resultRow == 1) {
				ajaxData.setData("success");				
			} else {
				ajaxData.setData("fail");				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		ajaxData.setContentType("text/plain");
		
		return ajaxData;
	}

}
