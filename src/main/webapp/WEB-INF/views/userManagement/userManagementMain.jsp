<!--
=================================================================
* 시  스  템  명 : PaaS-TA 관리자 포탈
* 업    무    명 : 사용자 관리
* 프로그램명(ID) : userManagementMain.jsp(사용자관리)
* 프로그램  개요 : 사용자 관리 화면
* 작    성    자 : 김도준
* 작    성    일 : 2016.08.31
=================================================================
수정자 / 수정일 :
수정사유 / 내역 :
=================================================================
-->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp"%>

<%--TITLE--%>
<div class="col-sm-6 pt30">
    <h4 class="modify_h4 fwn">사용자 관리</h4>
</div>

<%--SEARCH FORM--%>
<div class="content-box2 col-md-12 col-md-offset-13">
    <div class="box">
            <div class="input-group col-sm-12 ml20">

                <div class="input-group-btn">
                    <button type="button" id="btnSearch" class="btn" style="margin: 0 0 0 0; color: #c4c3c3; background-color: #f7f7f9; height: 30px;">
                        <span class="glyphicon glyphicon-search" style="top: -1px; left: 4px;"></span>
                    </button>
                </div>
                <input type="text" id="searchKeyword" class="form-control ml3" style="top: -6px; left: -3px; width: 99%;" maxlength="100" placeholder="검색어를 입력하세요." onkeypress="procCheckSearchFormKeyEvent(event);">
            </div>
    </div>
</div>

<div class="row">
<%--******************** 사용자 관리 ********************--%>
<div class="panel content-box col-sm-12 col-md-12 mt-50 col-md-offset-13 w98" id="userInfoViewArea">
    <div style="margin: 20px 0 0 10px;width:98%;">

        <div id="userInfoMessageArea" class="ml10 mt30 fl"></div>
        <table id="userInfoTableArea"  class="table table-striped">
            <thead>
            <tr>
                <th> 사용자 계정 </th>
                <th> 이름 </th>
                <th> 전화번호 </th>
                <th> 관리자 유무 </th>
                <th> 상태 </th>
                <th> 관리 </th>
            </tr>
            </thead>
            <tbody id="userInfoTable">
            </tbody>
        </table>
    </div>
</div>
</div>


<%--HIDDEN VALUE--%>
<input type="hidden" id="currentPageNo" name="currentPageNo" value="" />


<%--
====================================================================================================
SCRIPT BEGIN
====================================================================================================
--%>


<script type="text/javascript">

    var LIST_PROC_URL = "<c:url value='/userManagement/getUserInfoList' />";
    var PROC_RESET_PASSWORD_URL = "<c:url value='/userManagement/setResetPassword' />";
    var PROC_UPDATE_OPERATING_AUTHORITY_URL = "<c:url value='/userManagement/updateOperatingAuthority' />";
    var PROC_DELETE_USER_ACCOUNT_URL = "<c:url value='/userManagement/deleteUserAccount' />";
    var LOADED_LIST_COUNT = 0;

    var REQUEST_TYPE_RESET_PASSWORD = 'RESET_PASSWORD';
    var REQUEST_TYPE_UPDATE_OPERATING_AUTHORITY = 'UPDATE_OPERATING_AUTHORITY';
    var REQUEST_TYPE_DELETE_USER_ACCOUNT = 'DELETE_USER_ACCOUNT';


    // GET LIST
    var getUserInfoList = function(reqPageNo) {
        $('#buttonGetMoreList').hide();

        var param = {searchKeyword : document.getElementById('searchKeyword').value.toLowerCase(),
                     pageSize : '<%= Constants.USER_MANAGEMENT_PAGE_SIZE %>',
                     pageNo : reqPageNo
        };

        procCallAjax(LIST_PROC_URL, param, procCallbackGetUserInfoList);
    };


    // GET LIST CALLBACK
    var procCallbackGetUserInfoList = function(data, reqParam) {
        if (RESULT_STATUS_FAIL == data.RESULT) return false;

        var objTableArea = $('#userInfoTableArea');
        var objTable = $('#userInfoTable');
        var objMessageArea = $('#userInfoMessageArea');
        var listLength = data.list.length;
        var htmlString = [];

        var reqPageNo = reqParam.pageNo;
        document.getElementById('currentPageNo').value = reqPageNo;

        if ('<%= Constants.USER_MANAGEMENT_PAGE_NO %>' == reqPageNo) {
            LOADED_LIST_COUNT = 0;
            objTable.html('');
        }

        objMessageArea.html('');

        if (listLength < 1) {
            objTableArea.hide();
            objTable.hide();
            objMessageArea.append('<spring:message code="common.info.empty.data" />');
            objMessageArea.show();

        } else {
            var resultList = data.list;
            var totalListCount = resultList[0].totalCount;
            var boldClass = '';
            var menuText = '';

            for (var i = 0; i < listLength; i++) {
                boldClass = '<%= Constants.USE_YN_Y %>' == resultList[i].adminYn ? 'fwb' : '';
                menuText = '운영권한 부여';

                if ('<%= Constants.USE_YN_Y %>' == resultList[i].adminYn) menuText = '운영권한 삭제';

                if ('<%= Constants.USER_MANAGEMENT_STATUS_APPROVAL %>' == resultList[i].status) {
                    htmlString.push('<tr>'
                            + '<td class="col-md-4 ' + boldClass + '">' + resultList[i].userId + '</td>'
                            + '<td class="col-md-2 ' + boldClass + '">' + resultList[i].userName + '</td>'
                            + '<td class="col-md-2 ' + boldClass + '">' + resultList[i].tellPhone + '</td>'
                            + '<td class="col-md-1 tac ' + boldClass + '">' + resultList[i].adminYn + '</td>'
                            + '<td class="col-md-1 tac ' + boldClass + '">' + resultList[i].statusValue + '</td>'
                            + '<td class="col-md-2 tac ' + boldClass + '">'
                            + '<div class="dropdown">'
                            + '<label class="glyphicon glyphicon-cog dropdown-toggle" id="dropdownMenu' + i + '" ' + 'data-toggle="dropdown" aria-expanded="false" aria-hidden="true" style="cursor: pointer;"></label>'
                            + '<ul class="dropdown-menu dropdown-menu-right" role="menu" aria-labelledby="dropdownMenu' + i + '">'
                            + '<li role="presentation"><a role="menuitem" href="javascript:void(0);" onclick="procUserManagementPopup(\'' + REQUEST_TYPE_RESET_PASSWORD + '\', \'' + resultList[i].userId + '\')"> 비밀번호 초기화 </a></li>'
                            + '<li role="presentation"><a role="menuitem" href="javascript:void(0);" onclick="procUserManagementPopup(\'' + REQUEST_TYPE_UPDATE_OPERATING_AUTHORITY + '\', \'' + resultList[i].userId + '\')"> ' + menuText + ' </a></li>'
                            + '<li role="presentation"><a role="menuitem" href="javascript:void(0);" onclick="procUserManagementPopup(\'' + REQUEST_TYPE_DELETE_USER_ACCOUNT + '\', \'' + resultList[i].userId + '\')"> 계정 삭제 </a></li>'
                            + '</ul></div>');
                } else {
                    htmlString.push('<tr>'
                            + '<td class="col-md-4 ' + boldClass + '">' + resultList[i].userId + '</td>'
                            + '<td class="col-md-3 ' + boldClass + '">' + resultList[i].userName + '</td>'
                            + '<td class="col-md-2 ' + boldClass + '">' + resultList[i].tellPhone + '</td>'
                            + '<td class="col-md-1 tac ' + boldClass + '">' + resultList[i].adminYn + '</td>'
                            + '<td class="col-md-1 tac ' + boldClass + '">' + resultList[i].statusValue + '</td>'
                            + '<td class="col-md-1 tac ' + boldClass + '">'
                            + '<div class="dropdown"><label class="glyphicon glyphicon-cog dropdown-toggle" style="color: #dddddd;"></label>'
                            + '</div>');
                }

                htmlString.push('</td></tr>');

            }

            LOADED_LIST_COUNT += listLength;

            if (totalListCount > LOADED_LIST_COUNT) {
                $('#buttonGetMoreList').animate({
                    opacity: "show"
                });
            }

            objMessageArea.hide();
            objTable.append(htmlString);
            objTable.show();
            objTableArea.show();
        }

        $('#userInfoViewArea').show();
    };


    // GET MORE LIST
    var procGetMoreList = function() {
        getUserInfoList(parseInt(document.getElementById('currentPageNo').value) + 1);
    };


    // USER MANAGEMENT POPUP
    var procUserManagementPopup = function (reqType, reqUserId) {
        if (!procCheckEmailValidation(reqUserId)) return false;

        var reqMessage = '';
        var reqFunction = '';
        var reqButton = '';

        if (REQUEST_TYPE_RESET_PASSWORD == reqType) {
            reqMessage = "<spring:message code='common.info.popup.reset.password.message' />";
            reqFunction = 'resetPassword("' + reqUserId + '");';
            reqButton = '전송';
        }

        if (REQUEST_TYPE_UPDATE_OPERATING_AUTHORITY == reqType) {
            reqMessage = "<spring:message code='common.info.popup.update.operating.authority.message' />";
            reqFunction = 'updateOperatingAuthority("' + reqUserId + '");';
            reqButton = '실행';
        }

        if (REQUEST_TYPE_DELETE_USER_ACCOUNT == reqType) {
            reqMessage = "<spring:message code='common.info.popup.delete.user.account.message' />";
            reqFunction = 'deleteUserAccount("' + reqUserId + '");';
            reqButton = '삭제';
        }

        procPopup('사용자 관리', reqMessage, reqFunction, reqButton);
    };


    // RESET PASSWORD
    var resetPassword = function (reqUserId) {
        $('div.modal').modal('toggle');

        procCallAjax(PROC_RESET_PASSWORD_URL, {userId : reqUserId}, procCallbackResetPassword);
    };


    // RESET PASSWORD CALLBACK
    var procCallbackResetPassword = function(data) {
        if (RESULT_STATUS_FAIL == data.RESULT) return false;

        procAlert("success", '<spring:message code="common.info.result.success" />');
    };


    // UPDATE OPERATING AUTHORITY
    var updateOperatingAuthority = function (reqUserId) {
        $('div.modal').modal('toggle');

        procCallAjax(PROC_UPDATE_OPERATING_AUTHORITY_URL, {userId : reqUserId}, procCallbackUpdateOperatingAuthority);
    };


    // UPDATE OPERATING AUTHORITY CALLBACK
    var procCallbackUpdateOperatingAuthority = function(data) {
        if (RESULT_STATUS_FAIL == data.RESULT) return false;

        procInitPage();
        procAlert("success", '<spring:message code="common.info.result.success" />');
    };


    // DELETE USER ACCOUNT
    var deleteUserAccount = function (reqUserId) {
        $('div.modal').modal('toggle');

        procCallAjax(PROC_DELETE_USER_ACCOUNT_URL, {userId : reqUserId}, procCallbackDeleteUserAccount);
    };


    // DELETE USER ACCOUNT CALLBACK
    var procCallbackDeleteUserAccount = function(data) {
        if (RESULT_STATUS_FAIL == data.RESULT) return false;

        procInitPage();
        procAlert("success", '<spring:message code="common.info.result.success" />');
    };


    // CHECK EMAIL VALIDATION
    var procCheckEmailValidation = function (reqUserId) {
        if (!procCheckValidNull(reqUserId)) return false;

        var regExpPattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

        if (!regExpPattern.test(reqUserId)) {
            procAlert("danger", '<spring:message code="common.email.validation.error.message" />');
            return false;
        }

        return true;
    };


    // CHECK SEARCH FORM KEY EVENT
    var procCheckSearchFormKeyEvent = function(e) {
        if (e.keyCode==13 && e.srcElement.type != 'textarea') {
            getUserInfoList(<%= Constants.USER_MANAGEMENT_PAGE_NO %>);
        }
    };


    // INIT PAGE
    var procInitPage = function() {
        procAlert("info", WELCOME_MESSAGE);
        $('#userInfoViewArea').hide();

        getUserInfoList(<%= Constants.USER_MANAGEMENT_PAGE_NO %>);
    };


    // ON LOAD
    $(document.body).ready(function() {
        procInitPage();
    });

</script>


<%--
====================================================================================================
SCRIPT END
====================================================================================================
--%>


<%--FOOTER--%>
<%@ include file="../common/footer.jsp"%>
