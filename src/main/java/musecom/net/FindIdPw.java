package musecom.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import board.MemberDDL;
import board.MembersDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class FindIdPw extends HttpServlet {

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       MemberDDL ddl = new MemberDDL();
       int opt = Integer.parseInt(request.getParameter("opt"));
       String val1=null, val2=null, rs1=null, rs2=null; 
            
       if(opt==1) {
          val1 = request.getParameter("username");
 
       }else {
          val1 = request.getParameter("userid");
       }
          val2 = request.getParameter("useremail");
          
      Vector data = ddl.getSelect(val1, val2, opt);    
      int size = data.size();
      for(int i=0; i<size; i++) {
         MembersDTO dt = (MembersDTO) data.elementAt(i);
         rs1 = dt.getUserid();
         rs2 = dt.getUserpass();   
      }
      
      
      response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		out.print("<html><head><title>아이디/패스워드검색 </title>");
		out.print("<link rel=\"stylesheet\" href=\"css/bootstrap.css\"/>");
		out.print("</head><body><div class=\"container text-center mt-3 pt-2\">");
		if(size == 0) {
			out.println("<div class=text-danger>아이디를 찾을 수 없습니다.</div>");
			out.println("<a href=\"/board/member/findIdPw.jsp\">다시시도</a>");
			out.println("<a href=\"javascript:void(0)\"onclick=\"javascript:self.close()\">닫기</a>");
		}else {
			if(opt == 1) {
				out.print("<p>고객님의 아이디는 ");
				out.print(rs1);
				out.println("입니다 ");
				out.println("   <a href=\"javascript:void(0)\" onclick=\"opener.document.getElementById('userid').value='"+rs1+"';self.close();\">");
				out.println("</br>아이디 사용</a></p>");
			}else {
				out.print("<p>고객님의 비밀번호는  ");
				out.print(rs2);
				out.println("입니다 ");
				out.println("   <a href=\"javascript:void(0)\" onclick=\"opener.document.getElementById('userpass').value='"+rs2+"';self.close();\">");
				out.println("</br>비밀번호  사용</a></p>");
			}
		}
		out.println("</body></html>");
		
	
      
   }


}