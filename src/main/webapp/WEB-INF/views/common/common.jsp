<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%--CONSTANT--%>
<%@ page import="org.openpaas.paasta.portal.web.admin.common.Constants" %>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title> PaaS-TA ADMIN</title>

    <%--JQUERY--%>
    <script type="text/javascript" src="<c:url value='/resources/axisj/jquery/jquery.min.js' />"></script>

    <!-- BOOTSTRAP -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.css'/>">
    <script type="text/javascript" src="<c:url value='/resources/js/bootstrap.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/bootstrap-filestyle.min.js' />"></script>

    <%--COMMON--%>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/common.css'/>">
    <script type="text/javascript" src="<c:url value='/resources/js/common.js' />"></script>
</head>

<body>

<script type="text/javascript">
    /*COMMON VALUE*/
    var WELCOME_MESSAGE = "<spring:message code='common.system.welcome.message' />";
    var UPDATE_MESSAGE = "<spring:message code='common.info.popup.update.message' />";
    var DELETE_MESSAGE = "<spring:message code='common.info.popup.delete.message' />";

    var RESULT_STATUS_SUCCESS = "<%= Constants.RESULT_STATUS_SUCCESS %>";
    var RESULT_STATUS_FAIL = "<%= Constants.RESULT_STATUS_FAIL %>";
    var CUD_C = "<%= Constants.CUD_C%>";
    var CUD_U = "<%= Constants.CUD_U%>";

    var RESULT_STATUS_SUCCESS_MESSAGE = "<spring:message code='common.info.result.success' />";
    var RESULT_STATUS_FAIL_MESSAGE = "<spring:message code='common.system.error.message' />";
    var INFO_EMPTY_REQUEST_DATA = "<spring:message code='common.info.empty.req.data' />";
    var IMAGE_PATH_PREFIX = "<%= Constants.IMAGE_PATH_PREFIX%>";
</script>

<%--POPUP--%>
<%@ include file="../common/popup.jsp"%>

<%--LEFT MENU--%>
<%@ include file="../common/leftMenu.jsp"%>

<%--ALERT--%>
<%@ include file="../common/alert.jsp"%>