<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
   <div class="row">
      <div class="col-md-6 col-6 mb-3 p-5">
           <h2 class="text-center mb-4">공지사항</h2>
           <ol class="list-group list-group-numbered">
           <%
             for(int i=0; i< 5; i++){
           %>
             <li class="list-group-item list-group-item-action">
                 <a href="#"> 공지사항 1입니다. </a>
             </li>    
           <%
             }
           %>  
           </ol>
           
          <h2 class="text-center mb-4 mt-4">자유게시판</h2>
           <ol class="list-group list-group-numbered">
           <%
             for(int i=0; i< 5; i++){
           %>
             <li class="list-group-item list-group-item-action">
                 <a href="/board/index.jsp?fname=bbs/bbs"> 공지사항 1입니다. </a>
             </li>    
           <%
             }
           %>  
           </ol>
      </div>
      <div class="col-md-6 col-6 my-5">
          <h2 class="text-center mb-4">갤러리</h2>
          <div class="row">
              <div class="col-md-4 col-4">
<figure class="card card-product">
      <div class="img-wrap"><img src="images/111.png" class="img-thumbnail"></div>
      <figcaption class="info-wrap p-3">
            <h5 class="title">Another name of item</h5>
            <p class="desc">Some small description goes here</p>
      </figcaption>
   </figure>                  

              </div>
              <div class="col-md-4 col-4">
<figure class="card card-product">
      <div class="img-wrap"><img src="images/111.png"  class="img-thumbnail"></div>
      <figcaption class="info-wrap p-3">
            <h5 class="title">Another name of item</h5>
            <p class="desc">Some small description goes here</p>
      </figcaption>
   </figure>                  

              </div>
              <div class="col-md-4 col-4">
<figure class="card card-product">
      <div class="img-wrap"><img src="images/111.png"  class="img-thumbnail"></div>
      <figcaption class="info-wrap p-3">
            <h5 class="title">Another name of item</h5>
            <p class="desc">Some small description goes here</p>
      </figcaption>
   </figure>                  

              </div>
          </div>
      </div>

   </div>
</div>

</body>
</html>