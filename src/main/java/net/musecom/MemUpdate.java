package net.musecom;

import java.io.IOException;
import java.io.PrintWriter;

import board.MemberDDL;
import board.MembersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class MemUpdate extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MembersDTO dto = new MembersDTO();
		MemberDDL ddl = new MemberDDL();
		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("user");
		
		
		dto.setUserid(req.getParameter("userid"));
		dto.setUserpass(req.getParameter("userpass"));
		dto.setUsername(req.getParameter("username"));
		dto.setUseremail(req.getParameter("useremail"));
		dto.setPostcode(Integer.parseInt(req.getParameter("postcode")));
		dto.setAddr(req.getParameter("addr"));
		dto.setDetailaddr(req.getParameter("detailaddr"));
		dto.setTel(req.getParameter("tel"));
		dto.setUip();
		dto.setWdate();
		boolean isSuccess = ddl.update(dto, user);
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("UTF-8");
		String script = null;
		if(isSuccess) {
		   script = "<script>alert('수정했습니다.');"
						+ "document.location.href='/board';"
						+ "</script>";
		}else {
			 script = "<script>alert('문제가 발생했습니다. \n 관리자에게 문의해봤자 관리자가 뭘 알겠습니까 .');"
						+ "document.location.href='/board';"
						+ "</script>";
		}
		out.println("<html><head></head><body>");
		out.println(script);
		out.println("</body></html>");
		
		doGet(req, res);
	}

}
