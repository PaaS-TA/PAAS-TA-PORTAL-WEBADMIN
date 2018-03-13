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
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="./resource/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="./resource/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="./resource/bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="./resource/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="./resource/dist/css/skins/_all-skins.min.css">

    <!-- Google Font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <%--JQUERY--%>
    <script type="text/javascript" src="<c:url value='/resources/axisj/jquery/jquery.min.js' />"></script>

    <script type="text/javascript" src="<c:url value='/resources/js/common.js' />"></script>
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
        <a href="#" class="logo"><!-- link home -->
            <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini">
        <b>P</b>
          <!-- 파스타 이미지로 대체 -->
          <!--<img src="http://portal-web-admin.115.68.46.187.xip.io./resources/images/logo.png" width="50" height="50"> -->
      </span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>PaaS-TA</b></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button"></a>
            <h4 style="font-size: 16px; text-align: left; padding: 7px 10px; margin-top: 7;">
                <i class="fa fa-fw fa-volume-up"></i> <b>알림사항</b> 환영합니다. Open PaaS PaaS-TA 운영자 포탈입니다.
            </h4>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="./resource/dist/img/credit/mastercard.png" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>NUGU</p>
                </div>
            </div>

            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu tree" data-widget="tree">
                <!-- <li> 1depth 만 사용할 경우 -->
                <!--<li class="header">운영관리</li>
                <li>
                  <a href="#">
                    <i class="fa fa-circle-o text-red"></i> <span>설정 정보</span>
                  </a>
                </li>
                -->
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-dashboard"></i> <span>운영관리</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="#"><i class="fa fa-circle-o"></i> 설정 정보</a></li>
                        <li><a href="#"><i class="fa fa-circle-o"></i> 코드 관리</a></li>
                        <li><a href="#"><i class="fa fa-circle-o"></i> 조직 및 공간 조회</a></li>
                        <li><a href="#"><i class="fa fa-circle-o"></i> 클라이언트</a></li>
                        <li><a href="#"><i class="fa fa-circle-o"></i> 권한 관리</a></li>
                        <li><a href="#"><i class="fa fa-circle-o"></i> 사용자 관리</a></li>
                        <li><a href="#"><i class="fa fa-circle-o"></i> WEB IDE 유저 관리</a></li>
                        <li><a href="#"><i class="fa fa-circle-o"></i> 모니터링 사이트</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-files-o"></i>
                        <span>포탈 관리</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="#"><i class="fa fa-circle-o"></i> 메뉴</a></li>
                        <li><a href="#"><i class="fa fa-circle-o"></i> 카탈로그</a></li>
                        <li><a href="#"><i class="fa fa-circle-o"></i> 지원</a></li>
                        <li><a href="#"><i class="fa fa-circle-o"></i> 문서</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-pie-chart"></i>
                        <span>서비스 관리</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="#"><i class="fa fa-circle-o"></i> 빌드팩</a></li>
                        <li><a href="#"><i class="fa fa-circle-o"></i> 서비스 브로커</a></li>
                    </ul>
                </li>

            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">

<%--&lt;%&ndash;POPUP&ndash;%&gt;--%>
<%--<%@ include file="../common/popup.jsp"%>--%>

<%--&lt;%&ndash;LEFT MENU&ndash;%&gt;--%>
<%--<aside class="main-sidebar">--%>
<%--<%@ include file="../common/leftMenu.jsp"%>--%>
<%--</aside>--%>
<%--&lt;%&ndash;ALERT&ndash;%&gt;--%>
<%--<%@ include file="../common/alert.jsp"%>--%>