<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bbs.BbsDAO" %>
<%@ page import = "java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
 
<jsp:useBean id="bbs" class="bbs.bbs" scope="page"/>
<jsp:setProperty name="bbs" property="bbstitle"/>
<jsp:setProperty name="bbs" property="bbscontent"/>

<%String bbstitle = request.getParameter("bbsTitle"); 
	String bbscontent = request.getParameter("bbsContent");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP BBS</title>
</head>
<body>
    <%
    	String userID = null;
    	if (session.getAttribute("user") != null){
            userID = (String) session.getAttribute("user");
    	}
    	if (userID == null){
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('로그인하세요.')");
            script.println("location.href = '/board/index.jsp?fname=member/login'");    // 메인 페이지로 이동
            script.println("</script>");
    	}else{
    		if (bbstitle==null||bbscontent==null||bbstitle==""||bbscontent==""){
        		PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('모든 문항을 입력해주세요.')");
                script.println("history.back()");    // 이전 페이지로 사용자를 보냄
                script.println("</script>");
        	}else{
        		BbsDAO bbsDAO = new BbsDAO();
                int result = bbsDAO.write(bbstitle, userID, bbscontent);
                if (result == -1){ // 글쓰기 실패시
                    PrintWriter script = response.getWriter();
                    script.println("<script>");
                    script.println("alert('글쓰기에 실패했습니다.')");
                    script.println("history.back()");    // 이전 페이지로 사용자를 보냄
                    script.println("</script>");
                }else{ // 글쓰기 성공시
                	PrintWriter script = response.getWriter();
                    script.println("<script>");
                    script.println("location.href = '/board/index.jsp?fname=bbs/bbs'");    // 메인 페이지로 이동
                    script.println("</script>");    
                }
        	}	
    	}
    %>
 
</body>
</html>