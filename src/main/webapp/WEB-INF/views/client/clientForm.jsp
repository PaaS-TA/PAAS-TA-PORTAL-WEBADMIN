<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp"%>

<%--
  Created by IntelliJ IDEA.
  User: YJKim
  Date: 2016-07-25
  Time: 오후 4:06
  To change this template use File | Settings | File Templates.
--%>

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<%--CODE :: BIGIN--%>
<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<div class="col-sm-6 pt30">
    <c:choose>
        <c:when test="${INSERT_FLAG eq Constants.CUD_U}">
            <h4 class="modify_h4 fwn">클라이언트 상세</h4>
        </c:when>
        <c:when test="${INSERT_FLAG eq Constants.CUD_C}">
            <h4 class="modify_h4 fwn">클라이언트 등록</h4>
        </c:when>
    </c:choose>
</div>

<!--인스턴스 설정-->
<div class="col-md-11 col-md-offset-14">
    <form class="form-horizontal" role="form" id="clientForm" name="clientForm">
        <%--<input type="hidden" id="thumb_image_path" value="${thumb_image_path}">--%>

        <div class="form-group">
            <label class="control-label col-sm-2" for="uaacClientId">아이디</label>
            <div class="col-sm-9">
                <input type="text" maxlength="100" class="form-control" id="uaacClientId"
                        <c:choose>
                            <c:when test="${INSERT_FLAG eq Constants.CUD_U}"> style="background: white; cursor: default;" disabled </c:when>
                        </c:choose>
                />
            </div>
        </div>

        <c:choose>
            <c:when test="${INSERT_FLAG eq Constants.CUD_C}">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="uaacClientPw">패스워드</label>
                    <div class="col-sm-9">
                        <input type="text" maxlength="100" class="form-control" id="uaacClientPw" />
                    </div>
                </div>
            </c:when>
        </c:choose>

        <div class="form-group">
            <label class="control-label col-sm-2" for="clientOption">옵션</label>
            <div class="col-sm-9">
                <textarea class="form-control" rows="5" id="clientOption"></textarea>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="clientGuide">옵션 가이드</label>
            <div class="col-sm-9">
                <c:choose>
                    <c:when test="${INSERT_FLAG eq Constants.CUD_C}">
            <textarea rows="13" class="form-control" id="clientGuide"  style="background: white; cursor: text" readonly>

새 클라이언트 등록시 옵션 설정 예제)
    --authorized_grant_types client_credentials

---------------------------------------------------------------------------------------------------------------------------
클라이언트 주요 옵션 설명

  --authorities
                :       클라이언트에 대한 접근 권한 설정
                        (참고 : https://github.com/cloudfoundry/uaa/blob/master/docs/UAA-Security.md#security-metadata)
  --scope
                :       유저의 역할에 대한 설정
                        (참고 : https://github.com/cloudfoundry/uaa/blob/master/docs/UAA-Security.md#token-scope-rules)
  --authorized_grant_types
                :       OAuth2 grant type들의 목록. 클라이언트 등록시 필수 옵션
                        client_credentials, password, implicit, refresh_token, authorization_code


클라이언트 등록시 사용 가능한 옵션 목록

   --name &lt;string&gt;
   --scope &lt;list&gt;
   --authorized_grant_types &lt;list&gt;
   --authorities &lt;list&gt;
   --access_token_validity &lt;seconds&gt;
   --refresh_token_validity &lt;&lt;seconds&gt;
   --redirect_uri &lt;list&gt;
   --autoapprove &lt;&lt;list&gt;
   --signup_redirect_url &lt;url&gt;
   --clone &lt;other&gt;, get default settings from other
   -s | --secret &lt;secret&gt;, client secret
   -i | --[no-]interactive, interactively verify all values

---------------------------------------------------------------------------------------------------------------------------

More Information : # https://sks.gitbooks.io/my-road-2-industrial-applications/content/en/Security/registering_client_in_uaa.html
                   # https://github.com/cloudfoundry/uaa/blob/master/docs/UAA-APIs.rst#id85
            </textarea>
                    </c:when>

                    <c:when test="${INSERT_FLAG eq Constants.CUD_U}">
            <textarea rows="13" class="form-control" id="clientGuide"  style="background: white; cursor: text" readonly>

클라이언트 주요 옵션 설명

  --authorities
                :       클라이언트에 대한 접근 권한 설정
                        (참고 : https://github.com/cloudfoundry/uaa/blob/master/docs/UAA-Security.md#security-metadata)
  --scope
                :       유저의 역할에 대한 설정
                        (참고 : https://github.com/cloudfoundry/uaa/blob/master/docs/UAA-Security.md#token-scope-rules)
  --authorized_grant_types
                :       OAuth2 grant type들의 목록. 클라이언트 등록시 필수 옵션
                        client_credentials, password, implicit, refresh_token, authorization_code


클라이언트 수정시 사용 가능한 옵션 목록

   --name &lt;string&gt;
   --scope &lt;list&gt;
   --authorized_grant_types &lt;list&gt;
   --authorities &lt;list&gt;
   --access_token_validity &lt;seconds&gt;
   --refresh_token_validity &lt;seconds&gt;
   --redirect_uri &lt;list&gt;
   --autoapprove &lt;list&gt;
   --signup_redirect_url &lt;url&gt;
   --del_attrs &lt;attr_names&gt;, list of attributes to delete
   -i | --[no-]interactive, interactively verify all values

---------------------------------------------------------------------------------------------------------------------------

More Information : # https://sks.gitbooks.io/my-road-2-industrial-applications/content/en/Security/registering_client_in_uaa.html
                   # https://github.com/cloudfoundry/uaa/blob/master/docs/UAA-APIs.rst#id85
            </textarea>
                    </c:when>
                </c:choose>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-9" align="right" id = "statusActivity">
                <div class="divButtons" style="width:97.8%;">
                    <c:set var="insertFlag" value="${INSERT_FLAG}" />
                    <c:set var="checkCudU" value="<%= Constants.CUD_U %>" />
                    <c:choose>
                        <c:when test="${insertFlag eq checkCudU}">
                            <button type="button" class="btn btn_del2 fl ml-22" id="btnDelete">삭제</button>
                            <button type="button" class="btn btn-cancel2 btn-sm" id="btnCancel">취소</button>
                            <button type="button" class="btn btn-save btn-sm" id="btnRegist">저장</button>
                        </c:when>
                        <c:otherwise>
                            <button type="button" class="btn btn-cancel2 btn-sm" id="btnCancel">취소</button>
                            <button type="button" class="btn btn-save btn-sm" id="btnRegist">등록</button>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>

        <%-- Hidden values --%>

        <input type="hidden" id="resInsertFlag" name="resInsertFlag" value="<c:out value='${INSERT_FLAG}' default='' />" />
        <input type="hidden" id="resCudU" name="resCudU" value="<c:out value='${CONSTANT_CUD}' default='' />" />

        <input type="hidden" id="oldFilePath" name="oldFilePath" value="" />
        <input type="hidden" id="filePath" name="filePath" value="" />
        <input type="hidden" id="fileName" name="fileName" value="" />
        <input type="hidden" id="fileSize" name="fileSize" value="" />

        <input type="hidden" id="clientId" name="clientId" value="<c:out value='${CLIENT_ID}' default='' />" />

    </form>
</div>
<!--//인스턴스 설정-->
<div class="row">
</div>

<%--<div id="statusTitle" class="ml10 mb20">
    <c:choose>
        <c:when test="${INSERT_FLAG eq Constants.CUD_U}">
            <h1>클라이언트 상세</h1>
        </c:when>
        <c:when test="${INSERT_FLAG eq Constants.CUD_C}">
            <h1>클라이언트 등록</h1>
        </c:when>
    </c:choose>
</div>--%>
<%--
<form class="form-horizontal" role="form" id="clientForm" >

    <div class="form-group">
        <label class="control-label col-sm-2" for="uaacClientId"> 아이디 :</label>
        <div class="col-sm-9">
            <input type="text" maxlength="100" class="form-control" id="uaacClientId"
                <c:choose>
                    <c:when test="${INSERT_FLAG eq Constants.CUD_U}"> disabled </c:when>
                </c:choose>
            />
        </div>
    </div>

    <c:choose>
    <c:when test="${INSERT_FLAG eq Constants.CUD_C}">
    <div class="form-group">
        <label class="control-label col-sm-2" for="uaacClientPw"> 패스워드 :</label>
        <div class="col-sm-9">
            <input type="text" maxlength="100" class="form-control" id="uaacClientPw" />
        </div>
    </div>
    </c:when>
    </c:choose>

    <div class="form-group">
        <label class="control-label col-sm-2" for="clientOption"> 옵션:</label>
        <div class="col-sm-9">
            <textarea rows="10" class="form-control" id="clientOption" ></textarea>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="clientGuide">옵션 가이드:</label>
        <div class="col-sm-9">
        <c:choose>
            <c:when test="${INSERT_FLAG eq Constants.CUD_C}">
            <textarea rows="13" class="form-control" id="clientGuide"  style="background: white; cursor: text" readonly>

새 클라이언트 등록시 옵션 설정 예제)
    --authorized_grant_types client_credentials

---------------------------------------------------------------------------------------------------------------------------
클라이언트 주요 옵션 설명

  --authorities
                :       클라이언트에 대한 접근 권한 설정
                        (참고 : https://github.com/cloudfoundry/uaa/blob/master/docs/UAA-Security.md#security-metadata)
  --scope
                :       유저의 역할에 대한 설정
                        (참고 : https://github.com/cloudfoundry/uaa/blob/master/docs/UAA-Security.md#token-scope-rules)
  --authorized_grant_types
                :       OAuth2 grant type들의 목록. 클라이언트 등록시 필수 옵션
                        client_credentials, password, implicit, refresh_token, authorization_code


클라이언트 등록시 사용 가능한 옵션 목록

   --name &lt;string&gt;
   --scope &lt;list&gt;
   --authorized_grant_types &lt;list&gt;
   --authorities &lt;list&gt;
   --access_token_validity &lt;seconds&gt;
   --refresh_token_validity &lt;&lt;seconds&gt;
   --redirect_uri &lt;list&gt;
   --autoapprove &lt;&lt;list&gt;
   --signup_redirect_url &lt;url&gt;
   --clone &lt;other&gt;, get default settings from other
   -s | --secret &lt;secret&gt;, client secret
   -i | --[no-]interactive, interactively verify all values

---------------------------------------------------------------------------------------------------------------------------

More Information : # https://sks.gitbooks.io/my-road-2-industrial-applications/content/en/Security/registering_client_in_uaa.html
                   # https://github.com/cloudfoundry/uaa/blob/master/docs/UAA-APIs.rst#id85
            </textarea>
            </c:when>

            <c:when test="${INSERT_FLAG eq Constants.CUD_U}">
            <textarea rows="13" class="form-control" id="clientGuide"  style="background: white; cursor: text" readonly>


클라이언트 주요 옵션 설명

  --authorities
                :       클라이언트에 대한 접근 권한 설정
                        (참고 : https://github.com/cloudfoundry/uaa/blob/master/docs/UAA-Security.md#security-metadata)
  --scope
                :       유저의 역할에 대한 설정
                        (참고 : https://github.com/cloudfoundry/uaa/blob/master/docs/UAA-Security.md#token-scope-rules)
  --authorized_grant_types
                :       OAuth2 grant type들의 목록. 클라이언트 등록시 필수 옵션
                        client_credentials, password, implicit, refresh_token, authorization_code


클라이언트 수정시 사용 가능한 옵션 목록

   --name &lt;string&gt;
   --scope &lt;list&gt;
   --authorized_grant_types &lt;list&gt;
   --authorities &lt;list&gt;
   --access_token_validity &lt;seconds&gt;
   --refresh_token_validity &lt;seconds&gt;
   --redirect_uri &lt;list&gt;
   --autoapprove &lt;list&gt;
   --signup_redirect_url &lt;url&gt;
   --del_attrs &lt;attr_names&gt;, list of attributes to delete
   -i | --[no-]interactive, interactively verify all values

---------------------------------------------------------------------------------------------------------------------------

More Information : # https://sks.gitbooks.io/my-road-2-industrial-applications/content/en/Security/registering_client_in_uaa.html
                   # https://github.com/cloudfoundry/uaa/blob/master/docs/UAA-APIs.rst#id85
            </textarea>
            </c:when>
        </c:choose>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-1 col-sm-10 " align="right">
            <div class="divButtons">
                <c:set var="insertFlag" value="${INSERT_FLAG}" />
                <c:set var="checkCudU" value="<%= Constants.CUD_U %>" />
                <c:choose>
                    <c:when test="${insertFlag eq checkCudU}">
                        <button type="button" class="btn btn-danger fl" id="btnDelete">삭제</button>
                        <button type="button" class="btn btn-default" id="btnCancel">취소</button>
                        <button type="button" class="btn btn-success" id="btnRegist">저장</button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-default" id="btnCancel">취소</button>
                        <button type="button" class="btn btn-success" id="btnRegist">등록</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>


    &lt;%&ndash; Hidden values &ndash;%&gt;

    <input type="hidden" id="resInsertFlag" name="resInsertFlag" value="<c:out value='${INSERT_FLAG}' default='' />" />
    <input type="hidden" id="resCudU" name="resCudU" value="<c:out value='${CONSTANT_CUD}' default='' />" />

    <input type="hidden" id="oldFilePath" name="oldFilePath" value="" />
    <input type="hidden" id="filePath" name="filePath" value="" />
    <input type="hidden" id="fileName" name="fileName" value="" />
    <input type="hidden" id="fileSize" name="fileSize" value="" />

    <input type="hidden" id="clientId" name="clientId" value="<c:out value='${CLIENT_ID}' default='' />" />

</form>--%>



<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<%--CODE :: END--%>
<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--FOOTER--%>
<%@ include file="../common/footer.jsp"%>

<%--
====================================================================================================
SCRIPT BEGIN
====================================================================================================
--%>

<%-- datetimepicker library --%>
<link href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.13.0/moment.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>


<script type="text/javascript">

    var INSERT_FLAG = "<c:out value='${INSERT_FLAG}' default='' />";
    var CUD_U = "<%= Constants.CUD_U %>";
    var TO_LIST = "/client/clientMain";

    var GET_PROC_URL = "<c:url value='/client/getClient' />";
    var REGISTER_PROC_URL = "<c:url value='/client/registerClient' />";
    var UPDATE_PROC_URL = "<c:url value='/client/updateClient' />";
    var DELETE_PROC_URL = "<c:url value='/client/deleteClient' />";

    $(document).ready(function(){

        if (INSERT_FLAG != "" && INSERT_FLAG == CUD_U) { // get
            procGetClient();
        } else {
            $("#clientOption").val("--authorized_grant_types AUTHORIZED_GRANT_TYPES");
        }

    });


    function procGetClient() {
        var client_id = "${CLIENT_ID}";
        var param = {
            client_id : client_id
        }
        procCallAjax(GET_PROC_URL, param, procCallbackGetClient);
    }

    var procCallbackGetClient = function (data) {

        var options = "";

        var name = data.info.name;
        if (name != undefined && name != "") if (name != "") options += "--name " + name + " ";

        var scope = data.info.scope;
        if (scope != undefined) if (scope != "") options += "--scope " + scope + " ";

        var authorized_grant_types = data.info.authorized_grant_types;
        if (authorized_grant_types != undefined) if (authorized_grant_types != "") options += "--authorized_grant_types " + authorized_grant_types + " ";

        var authorities = data.info.authorities;
        if (authorities != undefined) if (authorities != "") options += "--authorities " + authorities + " ";

        var access_token_validity = data.info.access_token_validity;
        if (access_token_validity != undefined) if (access_token_validity != "") options += "--access_token_validity " + access_token_validity + " ";

        var refresh_token_validity = data.info.refresh_token_validity;
        if (refresh_token_validity != undefined) if (refresh_token_validity != "") options += "--refresh_token_validity " + refresh_token_validity + " ";

        var redirect_uri = data.info.redirect_uri;
        if (redirect_uri != undefined) if (redirect_uri != "") options += "--redirect_uri " + redirect_uri + " ";

        var autoapprove = data.info.autoapprove;
        if (autoapprove != undefined) if (autoapprove != "") options += "--autoapprove " + autoapprove + " ";

        var signup_redirect_url = data.info.signup_redirect_url;
        if (signup_redirect_url != undefined) if (signup_redirect_url != "") options += "--signup_redirect_url " + signup_redirect_url + " ";

        var clone = data.info.clone;
        if (clone != undefined) if (clone != "") options += "--clone " + clone + " ";


        $("#uaacClientId").val(data.info.client_id);
        $("#clientOption").val(options);

    }


    function parsingClientOptions () {
        var client_id  = $("#uaacClientId").val();
        var clientOption = $("#clientOption");
        var checkString = '-';
        var checkEmptyString = ' ';

        var param = {
            client_id : client_id
        };

        if (!procCheckValidation(param)) return false;

        if (clientOption.val().indexOf(checkString) == -1) {
            procAlert("warning", '<spring:message code="common.client.option.validation.error.message" />');
            clientOption.focus();
            return false;
        }

        var clientOptions = clientOption.val().trim();

        // parsing client options
        var optionLine =  clientOptions.split(checkString);
        var tempOptionLine = '';

        for (var i = 0; i < optionLine.length; i++) {
            if (optionLine[i] != "") {

                tempOptionLine = optionLine[i].trim();

                if (tempOptionLine.indexOf(checkEmptyString) == -1) {
                    procAlert("warning", '<spring:message code="common.client.option.validation.error.message" />');
                    clientOption.focus();
                    return false;
                }

                var option = tempOptionLine.split(checkEmptyString);
                var optionLength = option.length;
                var optionName = option[0];
                var optionContent;

                if (optionLength > 2) { // name, String
                    optionContent = "";
                    for (var j = 1; j < option.length; j++) {
                        optionContent =  optionContent + option[j].toString() + checkEmptyString;
                    }

                } else {
                    var contents = option[1].split(',');
                    optionContent = [];
                    for (var k = 0; k < contents.length; k++) {
                        optionContent[k] = contents[k];
                    }
                }

                var renew = paramSetting(param, optionName, optionContent);
                $.extend(param, renew);

            }
        }

        // call ajax
        if (INSERT_FLAG != "" && INSERT_FLAG == CUD_U) { // update
            procCallAjax(UPDATE_PROC_URL, param, procCallbackRegisterUpdateClient);
        } else { // register
            $.extend(param, {client_secret : $("#uaacClientPw").val()});
            procCallAjax(REGISTER_PROC_URL, param, procCallbackRegisterUpdateClient);
        }
    }

    function procCheckValidation(param) {

        if (param.client_id == '') {
            procAlert("warning", '<spring:message code="common.info.empty.req.data" />');
            $("#uaacClientId").focus();
            return false;
        }

        if ($("#uaacClientPw").val()  == '' || $("#uaacClientPw").val() == "") {
            procAlert("warning", '<spring:message code="common.info.empty.req.data" />');
            $("#uaacClientPw").focus();
            return false;
        }

        if (param.authorized_grant_types  == '') {
            procAlert("warning", '--authorized_grant_types 옵션은 필수 입니다.');
            $("#clientOption").focus();
            return false;
        }

        return true;
    }

    // Register, Update CALLBACK
    var procCallbackRegisterUpdateClient = function(data) {

        if (RESULT_STATUS_SUCCESS == data.RESULT) {
            procAlert("success", '<spring:message code="common.info.result.success" />');
            procMovePage(TO_LIST);

        } else {
            procAlert("danger", data.RESULT_MESSAGE);
        }
    };

    function paramSetting(param, optionName, optionContent) {

        if (optionName == "name") {
            return newParam = {name : optionContent};

        } else if (optionName == "scope" ) {
            return newParam = {scope : optionContent};

        } else if (optionName == "authorized_grant_types") {
            return newParam = {authorized_grant_types : optionContent};

        } else if (optionName == "authorities") {
            return newParam = {authorities : optionContent};

        } else if (optionName == "access_token_validity") {
            return newParam = {access_token_validity : optionContent};

        } else if (optionName == "refresh_token_validity") {
            return newParam = {refresh_token_validity : optionContent};

        } else if (optionName == "redirect_uri") {
            return newParam = {redirect_uri : optionContent};

        } else if (optionName == "autoapprove") {
            return newParam = {autoapprove : optionContent};

        } else if (optionName == "signup_redirect_url" ) {
            return newParam = {signup_redirect_url : optionContent};

        } else if (optionName == "clone") {
            return newParam = {clone : optionContent};

        } else if (optionName == "s") {
            return newParam = {client_secret : optionContent.toString()};
        } else if (optionName == "i") {
            return newParam = {client_secret : optionContent.toString()};
        }


    }

    // DELETE CLIENT
    var procCallbackDeleteClient = function(data) {

        if (RESULT_STATUS_SUCCESS == data.RESULT) {
            procAlert("success", '<spring:message code="common.info.result.success" />');
            procMovePage(TO_LIST);
        } else {
            procAlert("danger", data.RESULT_MESSAGE);
        }
    };

    function procDeleteClient() {

        var client_id = "${CLIENT_ID}";

        var param = {
            client_id : client_id
        }
        procCallAjax(DELETE_PROC_URL, param, procCallbackDeleteClient);

    }


    /*************************************************************************** Buttons *****/
    // BIND :: BUTTON EVENT
    $("#btnRegist").on("click", function() {
        parsingClientOptions();
    });

    // BIND :: BUTTON EVENT
    $("#btnCancel").on("click", function() {
        procMovePage(TO_LIST);
    });

    // BIND :: BUTTON EVENT
    $("#btnDelete").on("click", function() {

        procPopup('클라이언트', '\"' + $("#uaacClientId").val() + '\" ' + DELETE_MESSAGE, 'procDeleteClient();');
        $('#popupButtonText').html("삭제");
    });


</script>
<%--
====================================================================================================
SCRIPT END
====================================================================================================
--%>
