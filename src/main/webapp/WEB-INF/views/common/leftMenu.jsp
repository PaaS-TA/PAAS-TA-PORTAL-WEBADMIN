<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%--CONSTANT--%>
<%@ page import="org.openpaas.paasta.portal.web.admin.common.Constants" %>

<%--LEFT BAR--%>
<div class="col-sm-3 col-md-2 sidebar">
    <%--LOGO--%>
    <div class="logo">
        <a class="" href="javascript:procMovePage('/main');"><img src="<c:url value='/resources/images/logo.png'/>"></a>
    </div>

    <%--USER INFORMATION--%>
    <div class="userinfo">
        <%--<sec:authentication property="principal.imgPath" var="imgPath"/>--%>
        <c:choose>
            <c:when test="${not empty imgPath}"><img class="circle" src="<c:url value='${Constants.IMAGE_PATH_PREFIX}${imgPath}'/>" alt="사용자"/></c:when>
            <c:otherwise><img class="circle" src="<c:url value='/resources/images/userpic.png'/>" alt="사용자"/></c:otherwise>
        </c:choose>

        <%--<div class="name"><sec:authentication property="principal.name" /></div>--%>
        <div class="dropdown">
            <a class="mail dropdown-toggle" data-toggle="dropdown" id="dropdownTopMenu" href="javascript:void(0);">
                <%--<sec:authentication property="principal.username" /><span class="caret"></span>--%>
            </a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownTopMenu" style="width: 100%;">
                <li role="presentation"><a href="javascript:document.getElementById('logout').submit()">로그아웃</a></li>
            </ul>

            <c:url value="/logout" var="logoutUrl"/>
            <form id="logout" action="${logoutUrl}" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>


    <%--LEFT MENU--%>
    <div class="group_btn">
        <span><a href="#" class="">운영관리</a></span>
    </div>

    <!--앱템플릿 서브메뉴-->
    <ul class="smenu">
        <li id="configInfo" class="on">
            <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/configInfo/configInfoMain'/>')"><span>설정 정보</span></a>
        </li>
        <li id="commonCode">
            <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/commonCode/commonCodeMain'/>')"><span>코드 관리</span></a>
        </li>
        <li id="orgSpaceList">
            <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/orgSpaceList/orgSpaceListMain'/>')"><span>조직 및 공간 조회</span></a>
        </li>
        <li id="client">
            <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/client/clientMain'/>')"><span>클라이언트</span></a>
        </li>
        <li id="authority">
            <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/authority/authorityMain'/>')"><span>권한 관리</span></a>
        </li>
        <li id="userManagement">
            <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/userManagement/userManagementMain'/>')"><span>사용자 관리</span></a>
        </li>
        <li id="webIdeUser">
            <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/webIdeUser/webIdeUserMain'/>')"><span>WEB IDE 유저 관리</span></a>
        </li>
        <li id="linkMonitoringSite">
            <a href="<spring:eval expression="@environment.getProperty('monitoringSite.url')" />" target="_blank"><span>모니터링 사이트</span></a>
        </li>
    </ul>
    <!--앱템플릿 서브메뉴-->
    <div class="group_btn">
        <span><a href="#" class="">포탈관리</a></span>
    </div>
    <!--앱개발환경 서브메뉴-->
    <ul class="smenu">
        <li id="menu" class="on">
            <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/menu/menuMain'/>')"><span>메뉴</span></a>
        </li>
        <li id="catalog">
            <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/catalog/catalogMain'/>')"><span>카탈로그</span></a>
        </li>
        <li id="support">
            <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/support/supportMain'/>')"><span>지원</span></a>
        </li>
        <li id="documents">
            <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/documents/documentsMain'/>')"><span>문서</span></a>
        </li>
    </ul>
    <!--앱개발환경 서브메뉴-->
    <div class="group_btn">
        <span><a href="#" class="">서비스관리</a></span>
    </div>
    <!--앱개발환경 서브메뉴-->
    <ul class="smenu">
        <li id="buildPack" class="on">
            <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/buildpack/buildPackMain'/>')"><span>빌드팩</span></a>
        </li>
        <li id="service">
            <a href="javascript:void(0);" onclick="procMovePage('<c:url
                    value='/service/serviceBrokerMain'/>')"><span>서비스 브로커</span></a>
        </li>
    </ul>
    <!--앱개발환경 서브메뉴-->

</div>




<%--
&lt;%&ndash;LEFT BAR&ndash;%&gt;
<div class="sidebar">
    &lt;%&ndash;LOGO&ndash;%&gt;
    <div style="text-align: center;">
        <a class="" href="javascript:procMovePage('/main');">
            <img src="<c:url value='/resources/images/logo.png'/>">
        </a>
    </div>

    &lt;%&ndash;USER INFORMATION&ndash;%&gt;
    <div class="userinfo">
        <sec:authentication property="principal.imgPath" var="imgPath"/>
        <c:choose>
            <c:when test="${not empty imgPath}"><img src="<c:url value='${imgPath}'/>" alt="사용자"/></c:when>
            <c:otherwise><img src="<c:url value='/resources/images/userpic.png'/>" alt="사용자"/></c:otherwise>
        </c:choose>
        <div class="name"><sec:authentication property="principal.name" /></div>
        <div class="dropdown">
            <a class="mail dropdown-toggle" data-toggle="dropdown" id="dropdownTopMenu" href="javascript:void(0);">
                <sec:authentication property="principal.username" /><span class="caret"></span>
            </a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownTopMenu" style="width: 100%;">
                <li role="presentation"><a href="javascript:document.getElementById('logout').submit()">로그아웃</a></li>
            </ul>

            <c:url value="/logout" var="logoutUrl"/>
            <form id="logout" action="${logoutUrl}" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>

    &lt;%&ndash;LEFT MENU&ndash;%&gt;
    <div class="row-fluid">
        <div class="panel-group" id="leftMenu" role="tablist" aria-multiselectable="false">
            <div class="panel panel-default">
                <div class="panel-heading" id="leftMenuHead1" data-toggle="collapse"
                     data-parent="#leftMenu" href="#leftMenu1" aria-expanded="true" aria-controls="leftMenu1" role="button">
                    운영 관리
                </div>
                <div id="leftMenu1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="leftMenuHead1">
                    <div class="panel-body">
                        <ul id="leftMenu-01-01" class="nav navbar-tabs nav-stacked">
                            <li id="configInfo">
                                <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/configInfo/configInfoMain'/>')"><span>설정 정보</span></a>
                            </li>
                            <li id="commonCode">
                                <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/commonCode/commonCodeMain'/>')"><span>코드 관리</span></a>
                            </li>
                            <li id="orgSpaceList">
                                <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/orgSpaceList/orgSpaceListMain'/>')"><span>조직 및 공간 조회</span></a>
                            </li>
                            <li id="client">
                                <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/client/clientMain'/>')"><span>클라이언트</span></a>
                            </li>
                            <li id="authority">
                                <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/authority/authorityMain'/>')"><span>권한 관리</span></a>
                            </li>
                            <li id="userManagement">
                                <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/userManagement/userManagementMain'/>')"><span>사용자 관리</span></a>
                            </li>
                            <li id="webIdeUser">
                                <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/webIdeUser/webIdeUserMain'/>')"><span>WEB IDE 유저 관리</span></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" id="leftMenuHead2" data-toggle="collapse" data-parent="#leftMenu" href="#leftMenu2" aria-expanded="false" aria-controls="leftMenu2" role="button">
                    포탈 관리
                </div>
                <div id="leftMenu2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="leftMenuHead2">
                    <div class="panel-body">
                        <ul id="leftMenu-02-01" class="nav navbar-tabs nav-stacked">
                            <li id="menu">
                                <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/menu/menuMain'/>')"><span>메뉴</span></a>
                            </li>
                            <li id="catalog">
                                <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/catalog/catalogMain'/>')"><span>카탈로그</span></a>
                            </li>
                            <li id="support">
                                <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/support/supportMain'/>')"><span>지원</span></a>
                            </li>
                            <li id="documents">
                                <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/documents/documentsMain'/>')"><span>문서</span></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" id="leftMenuHead3" data-toggle="collapse" data-parent="#leftMenu" href="#leftMenu3" aria-expanded="false" aria-controls="leftMenu3" role="button">
                   서비스 관리
                </div>
                <div id="leftMenu3" class="panel-collapse collapse" role="tabpanel" aria-labelledby="leftMenuHead2">
                    <div class="panel-body">
                        <ul id="leftMenu-03-01" class="nav navbar-tabs nav-stacked">
                            <li id="buildPack">
                                <a href="javascript:void(0);" onclick="procMovePage('<c:url value='/buildPack/buildPackMain'/>')"><span>빌드팩</span></a>
                            </li>

                            <li id="service">
                                <a href="javascript:void(0);" onclick="procMovePage('<c:url
                                        value='/service/serviceBrokerMain'/>')"><span>서비스 브로커</span></a>
                            </li>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>--%>



<%-- 이건 원래 있던거...--%>
<%--<div class="content_wf">--%>