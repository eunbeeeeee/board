<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%! String message = null; %>
<%
	message = (String) request.getAttribute("message");
	if(message != null){
%>
	<script>
		alert("<%= message%>");
	</script>
<%
	}
%>
<h1 class="my-5 text-center">Eun Bee 로그인</h1>
<div class="container mb-5">
	<form name="loginform" class="loginform" action="/board/memberslogin" method="post">
		<div class="mb-3 mt-3">
			<div class="row">
				<label for="userid" class="form-label col-md-3">아이디 :</label>
				<div class="col-md-9">
					<input type="text" class="form-control" id="userid" placeholder="아이디" name="userid" />
				</div>
			</div>
		</div>
		
		<div class="mb-3 mt-3">
			<div class="row">
				<label for="userpass" class="form-label col-md-3">비밀번호 :</label>
				<div class="col-md-9">
					<input type="password" class="form-control" id="userpass" placeholder="비밀번호" name="userpass" />
				</div>
			</div>
		</div>
		<div class="mb-3 mt-3">
			<div class="row">
				<div class="offset-md-8 col-md-4 text-end">
					<label><input type="checkbox" name="huid" id="huid" value="ok" onclick="isChecked()"/>아이디 저장</label>
				</div>
			</div>
		</div>
		<div class="mb-3 mt-3">
			<div class="row">
				<div class="col-md-6 text-end">
					<button type="reset" class="btn btn-warning mx-2 px-5"> 취소 </button>
				</div>
				<div class="col-md-6 text-start">
					<button type="button" class="btn btn-primary mx-2 px-5" onclick="loginSubmit()"> 로그인 </button>
				</div>
			</div>
		</div>
		<div class="mb-3 mt-3">
			<div class="row">
				<div class="offset-md-5 col-md-7 text-end">
					<a href="?fname=member/register" class="me-3">회원가입</a>
					<a href="javascript:void(0);" onclick="findIdPw()">아이디/패스워드 찾기</a>
				</div>
			</div>
		</div>
	</form>
</div>

<div class="container wh600 mb-5">
	<div class="row">
		<div class="col-md-6">
			<div class="d-grid">
				<a href="#" class="btn btn-block naver-btn">네이버 로그인</a>
			</div>
		</div>
		<div class="col-md-6">
			<div class="d-grid">
				<a href="#" class="btn btn-block kakao-btn">카카오 로그인</a>
			</div>
		</div>
	</div>
</div>
<script src="js/cookie.js"></script>
<script src="js/form.js"></script>