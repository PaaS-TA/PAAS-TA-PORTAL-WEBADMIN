<!--
=================================================================
* 시  스  템  명 : PaaS-TA 관리자 포탈
* 업    무    명 : 인덱스
* 프로그램명(ID) : index.jsp(인덱스)
* 프로그램  개요 : 인덱스 화면
* 작    성    자 : 김도준
* 작    성    일 : 2016.04.25
=================================================================
수정자 / 수정일 :
수정사유 / 내역 :
=================================================================
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>PaaS-TA ADMIN</title>

	<!-- BOOTSTRAP CORE CSS -->
	<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet">
	<link href="<c:url value='/resources/css/common.css' />" rel="stylesheet">

</head>
<body onload="window.document.getElementById('id').focus();">
<div class="w800px text-center">
	<form name='login' action="<c:url value='/index' />" method='POST'>
	<div class="mt250">
		<span class="login-icons"><img src="<c:url value='/resources/images/mainuser.png' />"></span>
	</div>
	<div class="form-group text-center mt65 ">
	    <div><span class="login-font">MEMBER LOGIN</span></div>
		<div><span class="login-font2">운영자 포탈에 오신 것을 환영합니다.</span></div>
    </div>
	<div class="text-center mt15">
		<input class="login-form"  name="id" id="id" placeholder="yourmail@example.com" type="text" value="">
	</div>
	<div class="text-center">
		<input class="login-form" name="password" type="password" placeholder="Password" value="">
	</div>
	<c:choose>
		<c:when test="${not empty error}">
			<div class="text-center mt15 alert alert-danger" style="width:45%;height:28px;"> <span class="glyphicon glyphicon-warning-sign"></span><font style="padding-left:7px;font-size:13px;">${error}</font></div>
			<%--<div class="custom-login-form" style="color: red">${error}</div>--%>
		</c:when>
		<c:when test="${not empty message}">
			<div class="custom-login-form">${message}</div>
		</c:when>
		<c:otherwise><div>&nbsp</div></c:otherwise>
	</c:choose>
	<div class="text-center mt30">
		<button type="submit" name="submit" class="btn btn-primary btn-lg" style="width:180px; background-color:#a1a4ad; border-color:#a1a4ad; font-weight: normal;">LOGIN</button>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</div>
	</form>
</div>
</body>
</html>


