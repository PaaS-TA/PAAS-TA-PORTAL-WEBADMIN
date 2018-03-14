<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page import="org.openpaas.paasta.portal.web.admin.common.Constants" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | Simple Tables</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="../../resources/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../../resources/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../../resources/bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../../resources/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="../../resources/dist/css/skins/_all-skins.min.css">

    <%--JQUERY--%>
    <script type="text/javascript" src="<c:url value='/resources/axisj/jquery/jquery.min.js' />"></script>
    <%--COMMON--%>
    <script type="text/javascript" src="<c:url value='/resources/js/common.js' />"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">

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

<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <a href="../../index2.html" class="logo"><!-- link home -->
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>P</b></span><!-- 파스타 이미지로 대체 -->
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>PaaS-TA</b></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button"></a>
            <span>알림사항 환영합니다. Open PaaS PaaS-TA 운영자 포탈입니다.</span>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">



















