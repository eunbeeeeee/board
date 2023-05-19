<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="bbs.BbsDAO" %>
<%@ page import="bbs.bbs" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1" >  <!-- 반응형 웹에 사용하는 메타태그 -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/style.css"> <!-- 참조  -->
<title>JSP 게시판 웹 사이트</title>
<style type="text/css">
	a, a:hover{
		color: #000000;
		text-decoration:none;
	}
</style>
</head>
<body>
    <% 
    String userID = null;
    if (session.getAttribute("user") != null){
        userID = (String) session.getAttribute("user");
    }
    int pageNumber = 1;
    if (request.getParameter("pageNumber") != null){
    	pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
    }
    %>
    <div class= "container">
    	<div= "row">
    	    <table class= "table table-stripped" style= "text-align: center; boarder: 1px solid #dddddd">
    	    	<thead>
    	    	<tr>
    	    		<th style= "background-color: #eeeeee; text-align: center;">번호</th>
    	    		<th style= "background-color: #eeeeee; text-align: center;">제목</th>
    	    		<th style= "background-color: #eeeeee; text-align: center;">작성자</th>
    	    		<th style= "background-color: #eeeeee; text-align: center;">작성일</th>
    	    	</tr>
    	    	</thead>
    	    	<tbody>
    	    		<tr>
    	    			<%
    	    				BbsDAO bbsDAO = new BbsDAO();
	    	    				ArrayList<bbs> list =bbsDAO.getList(pageNumber);
    	    				for (int i =0; i<list.size(); i++){
    	    			%>
    	    			<tr>
    	    				<td><%= list.get(i).getBbsid() %></td>
    	    				<td><a href ="view.jsp?bbsID= <%= list.get(i).getBbsid() %>"><%= list.get(i).getBbstitle() %></a></td>
    	    				<td><%= list.get(i).getUserid() %></td>
    	    				<td><%= list.get(i).getBbsdate().substring(0, 11) + list.get(i).getBbsdate().substring(11, 13) + "시" + list.get(i).getBbsdate().substring(14, 16) + "분" %></td>
    	    			</tr>	
    	    			<%
    	    				}
    	    			%>
    	    		</tr>
    	    	</tbody>
    	    </table>
    	     <%
    	    	if(pageNumber != 1){
    	    %>		
    	    		<a href= "bbs.jsp?pageNumber=<%=pageNumber -1%>" class="btn btn-success btn-arraw-left">이전</a>
    	    <% 
    	    	}if(bbsDAO.nextPage(pageNumber + 1)){
    	    %>		
    	    	<a href= "bbs.jsp?pageNumber=<%=pageNumber +1%>" class="btn btn-success btn-arraw-left">다음</a>
    	    <% 
    	    	}
    	    %>
    	    
    	    <a href= "/board/index.jsp?fname=bbs/write" class= "btn btn-primary pull-right">글쓰기</a>
    	</div>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>