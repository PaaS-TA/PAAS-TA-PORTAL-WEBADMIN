<!--
=================================================================
* 시  스  템  명 : PaaS-TA 관리자 포탈
* 업    무    명 : 카탈로그
* 프로그램명(ID) : servicePackForm.jsp(서비스카탈로그관리)
* 프로그램  개요 : 서비스 카탈로그를 등록/수정하는 화면
* 작    성    자 : 김도준
* 작    성    일 : 2016.07.05
=================================================================
수정자 / 수정일 :
수정사유 / 내역 :
=================================================================
-->
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript" src="<c:url value='/resources/js/fileUpload.js' />"></script>

    <div class="col-sm-6 pt30">
        <c:choose>
            <c:when test="${INSERT_FLAG eq Constants.CUD_U}">
                <h4 class="modify_h4 fwn">서비스 상세</h4>
            </c:when>
            <c:when test="${INSERT_FLAG eq Constants.CUD_C}">
                <h4 class="modify_h4 fwn">서비스 등록</h4>
            </c:when>
        </c:choose>

    </div>
    <!--인스턴스 설정-->
    <div class="col-md-11 col-md-offset-14">
        <form class="form-horizontal" role="form" id="catalogForm" name="catalogForm">
            <input type="hidden" id="thumb_image_path" value="${thumb_image_path}">
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">이름</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="name" name="name">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="classification">분류</label>
                <div class="col-sm-9">
                    <select class="form-control" id="classification" style="background:url(/resources/images/btn_down.png) no-repeat right; background-color:#fafafa;">
                        <%-- Category Area --%>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="servicePackName">서비스</label>
                <div class="col-sm-9">
                    <select class="form-control" id="servicePackName" name="servicePackName" style="background:url(/resources/images/btn_down.png) no-repeat right; background-color:#fafafa;">
                        <%-- build pack area --%>
                    </select>
                </div>
            </div>


            <div class="form-group">
                <label class="control-label col-sm-2" for="thumbnail">썸네일<br><font style="font-size:13px;color:#ababab;">(50X50)</font></label>

                <div class="col-sm-8">
                    <input type="text" class="form-control" id="thumbnail" style="width: 102%; background: white; cursor: default" disabled>
                </div>
                <div class="col-sm-1">
                    <button type="button" class="btn btn-cancel btn-sm tar" style="margin-top:11px;margin-left:-5px;" onclick="$('#hiddenThumbnail').click()">
                        <span class="glyphicon glyphicon-floppy-disk"></span>&nbsp;파일찾기
                    </button>
                    <input type="file" id="hiddenThumbnail" style="display: none">
                </div>
                <div>
                    <div class="col-sm-9">
                        <div id="divPreview" class="divPreview"  style="width: 99.9%;">
                            <div class="fl">
                                <a href="javascript:void(0);" class="custom-thumbnail">
                                    <img id="preview" src="" >
                                </a>
                            </div>
                            <div class="fl" style="padding:15px 10px;">
                                <a id="thumbname"></a>
                            </div>
                            <div class="divImageInfo fl ml20">
                                <label id="labelThumbName" style="padding-right: 20px"></label>
                                <button id="btnResetImg" type="button" class="btn-del" aria-label="썸네일 지우기">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="servicePackName">서비스 생성 파라미터</label>
                <button type='button' class='btn btn-save btn-sm' style='margin-top:10px;margin-right:100px;' id='addServiceParameter'>추가</button>
                <div id="serviceParameterArea" class="col-sm-7" style="width: 60%" align="left">
                </div>
                <%--<div class="col-sm-3" style="text-align: center; width: 20%; padding-right: 10px">
                    <div style="margin-top:15px;margin-left:-20px;">
                        ex) {"permissions":"read-only"}
                    </div>
                </div>--%>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="appBindYn_Y">앱 바인드 사용</label>
                <div class="col-sm-9 mt10">
                    <label class="radio-inline">
                        <input type="radio" onclick="javascript:yesNoCheck();" id="appBindYn_Y" name="appBindYn" value="<%= Constants.APP_BIND_YN_Y %>"> Y
                    </label>
                    <label class="radio-inline">
                        <input type="radio" onclick="javascript:yesNoCheck();" id="appBindYn_N" name="appBindYn" value="<%= Constants.APP_BIND_YN_N %>"> N
                    </label>
                </div>
            </div>
            <div id="app_bind_parameter_form">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="servicePackName">앱 바인드 파라미터</label>
                    <button type='button' class='btn btn-save btn-sm' style='margin-top:10px;margin-right:100px;' id='addAppBindParameter'>추가</button>
                    <%--<div class="col-sm-6" style="width: 55%">
                        <input type="text" class="form-control" id="app_bind_parameter">
                    </div>--%>
                    <div id="appBindParameterArea" class="col-sm-7" style="width: 60%" align="left">
                    </div>
                    <%--<div class="col-sm-3" style="text-align: center; width: 20%; padding-right: 10px">
                        <div style="margin-top:15px;margin-left:-20px;">
                            ex) {"test":"test"}
                        </div>
                    </div>--%>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="useYn_Y">공개</label>
                <div class="col-sm-9 mt10">
                    <label class="radio-inline">
                        <input type="radio" id="useYn_Y" name="useYn" value="<%= Constants.USE_YN_Y %>"> Y
                    </label>
                    <label class="radio-inline">
                        <input type="radio" id="useYn_N" name="useYn" value="<%= Constants.USE_YN_N %>"> N
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="dashboardUseYn_Y">대시보드 사용</label>
                <div class="col-sm-9 mt10">
                    <label class="radio-inline">
                        <input type="radio" id="dashboardUseYn_Y" name="dashboardUseYn" value="<%= Constants.DASHBOARD_YN_Y %>"> Y
                    </label>
                    <label class="radio-inline">
                        <input type="radio" id="dashboardUseYn_N" name="dashboardUseYn" value="<%= Constants.DASHBOARD_YN_N %>"> N
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="summary">요약</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="summary" name="summary">
                </div>

            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="description">설명</label>
                <div class="col-sm-9">
                    <textarea class="form-control" rows="5" id="description" name="description"></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-9 " align="right" id = "statusActivity">
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

            <input type="hidden" id="orgThumbImgPath" name="orgThumbImgPath" value="" />
            <input type="hidden" id="thumbImgPath" name="thumbImgPath" value="" />
            <input type="hidden" id="thumbImgName" name="thumbImgName" value="" />
            <input type="hidden" id="orgName" name="orgName" value="" />
            <input type="hidden" id="no" name="no" value="<c:out value='${REQUEST_NO}' default='' />" />

        </form>
    </div>
    <!--//인스턴스 설정-->
    <div class="row">
    </div>

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

    <script type="text/javascript">
        var CATEGORY_HOME_URL = "<c:url value='/catalog/catalogMain' />";
        var CATALOG_LIST_PROC_URL = "<c:url value='/catalog/getServicePackCatalogList' />";
        var CATEGORY_LIST_PROC_URL = "<c:url value='/commonCode/getCommonCode' />";
        var SERVICE_PACK_LIST_PROC_URL = "<c:url value='/catalog/getServicePackList' />";
        var DUPLICATED_PROC_URL = "<c:url value='/catalog/getServicePackCatalogCount' />";
        var INSERT_PROC_URL = "<c:url value='/catalog/insertServicePackCatalog' />";
        var UPDATE_PROC_URL = "<c:url value='/catalog/updateServicePackCatalog' />";
        var DELETE_PROC_URL = "<c:url value='/catalog/deleteServicePackCatalog' />";
        var CHECK_DELETE_PROC_URL = "<c:url value='/catalog/getCheckDeleteServicePackCatalogCount' />";
        var UPLOAD_IMAGE_PROC_URL = "<c:url value='/catalog/uploadThumbnailImage'/>";
        var DELETE_IMAGE_PROC_URL = "<c:url value='/catalog/deleteThumbnailImage'/>";
        var SERVICE_PACK_CATALOG_ID = "<%= Constants.SERVICE_PACK_CATALOG_ID %>";
        var INSERT_FLAG = "<c:out value='${INSERT_FLAG}' default='' />";
        var CUD_U = "<%= Constants.CUD_U %>";
        var REQ_NO = "<c:out value='${REQUEST_NO}' default='' />";

        // GET CATEGORY LIST
        var getCategoryList = function() {
            procCallAjax(CATEGORY_LIST_PROC_URL + "/" + SERVICE_PACK_CATALOG_ID, null, procCallbackCategoryList);
        };


        // GET CATEGORY LIST CALLBACK
        var procCallbackCategoryList = function(data) {
            if (RESULT_STATUS_FAIL == data.RESULT) return false;

            var objSelectBox = $('#classification');
            var listLength = data.list.length;
            var htmlString = [];

            for (var i = 0; i < listLength; i++) {
                htmlString.push("<option value='" + data.list[i].key + "'>" + data.list[i].value + "</option>");
            }

            objSelectBox.append(htmlString);

            getServicePackList();
        };


        // GET SERVICE PACK LIST
        var getServicePackList = function() {
            procCallAjax(SERVICE_PACK_LIST_PROC_URL, {}, procCallbackServicePackList);
        };


        // GET SERVICE PACK LIST CALLBACK
        var procCallbackServicePackList = function(data) {
            if (RESULT_STATUS_FAIL == data.RESULT) return false;

            var objSelectBox = $('#servicePackName');
            var listLength = data.list.length;
            var htmlString = [];

            for (var i = 0; i < listLength; i++) {
                htmlString.push("<option value='" + data.list[i].name + "'>" + data.list[i].name + "</option>");
            }

            objSelectBox.append(htmlString);

            var insertFlag = INSERT_FLAG;
            if ("" != insertFlag && CUD_U == insertFlag) procUpdateForm();
        };


        // CHECK DUPLICATED NAME
        var procCheckDuplicatedName = function() {
            if (!procCheckValidStringSpace()) return false;
            if (!procCheckValid()) return false;

            var doc = document;
            var reqName = doc.getElementById('name').value;
            var orgName = doc.getElementById('orgName').value;

            // CHECK DUPLICATED NAME
            var insertFlag = INSERT_FLAG;
            if ("" != insertFlag && CUD_U == insertFlag && reqName == orgName) {
                procUploadFile({RESULT : RESULT_STATUS_SUCCESS});

            } else {
                procCallAjax(DUPLICATED_PROC_URL, {name: reqName}, procUploadFile);
            }
        };

        // PROCESS CHECK VALID
        var procCheckValid = function() {

            // name
            var name = $('#name').val();
            if (name == '') {
                procAlert("warning", '<spring:message code="common.info.empty.req.data" />');
                $('#name').focus();
                return false;
            }

            // summart
            var summary = $('#summary').val();
            if (summary == '') {
                procAlert("warning", '<spring:message code="common.info.empty.req.data" />');
                $('#summary').focus();
                return false;
            }

            return true;
        };

        // UPLOAD FILE
        var procUploadFile = function(data) {
            if (RESULT_STATUS_SUCCESS != data.RESULT) {
                $('#name').focus();
                return false;
            }

            var formData = getFileFormData();

            if (formData != undefined) {
                uploadFile(formData, UPLOAD_IMAGE_PROC_URL, procInsert);
            } else {
                procInsert();
            }
        };


        // INSERT
        var procInsert = function(data) {
            var doc = document;
            var reqUseYn = $("input:radio[name='useYn']:checked").val();
            var reqDashboardUseYn = $("input:radio[name='dashboardUseYn']:checked").val();
            var reqAppBindYn = $("input:radio[name='appBindYn']:checked").val();
            var param = $("#catalogForm").serializeObject();
            //var reqAppBindParameter = doc.getElementById('app_bind_parameter').value;

            if(reqAppBindYn == 'N'){
                completeParameter2 = '';
            }else{
                completeParameter2;
            }

            var param2 = {
                useYn: reqUseYn,
                dashboardUseYn : reqDashboardUseYn,
                classification: doc.getElementById('classification').value,
                summary: doc.getElementById('summary').value,
                description: doc.getElementById('description').value,
                parameter: completeParameter,
                app_bind_parameter: completeParameter2,
                appBindYn : reqAppBindYn
            };

            $.extend(param, param2);
            //return false;
            // UPDATE
            var insertFlag = INSERT_FLAG;
            if ("" != insertFlag && CUD_U == insertFlag) {
                procCallAjax(UPDATE_PROC_URL, param, procCallbackUpdate);

                // INSERT
            } else {
                procCallAjax(INSERT_PROC_URL, param, procCallbackInsert);
            }
        };


        // INSERT CALLBACK
        var procCallbackInsert = function(data) {
            if (RESULT_STATUS_SUCCESS == data.RESULT) {
                procAlert("success", '<spring:message code="common.info.result.success" />');
                procMovePage(CATEGORY_HOME_URL);

            } else {
                procAlert("danger", data.RESULT_MESSAGE);
            }
        };


        // UPDATE CALLBACK
        var procCallbackUpdate = function(data) {
            if (RESULT_STATUS_SUCCESS == data.RESULT) {
                procDeleteFile();
                procAlert("success", '<spring:message code="common.info.result.success" />');

            } else {
                procAlert("danger", data.RESULT_MESSAGE);
            }
        };


        // UPDATE FORM
        var procUpdateForm = function() {
            var reqNo = REQ_NO;
            if (null == reqNo || "" == reqNo) {
                procAlert("danger", '<spring:message code="common.system.error.message" />');
                return false;
            }

            procCallAjax(CATALOG_LIST_PROC_URL, {no: reqNo}, procCallbackUpdateForm);
        };

        var splitSign;
        var count;
        var str;
        var appParameterStr;
        var splitSign2;
        var count2;

        // CALL BACK UPDATE FORM
        var procCallbackUpdateForm = function(data) {
            if (RESULT_STATUS_FAIL == data.RESULT) return false;

            if (data.list.length < 1) return false;

            var checkedAppBindValue = $("input:radio[name=appBindYn]:checked").val();

            if(checkedAppBindValue == 'N'){
                $('#app_bind_parameter_form').hide();
            }else{
                $('#app_bind_parameter_form').show();
            }

            var resultData = data.list[0];

            var reqName = $('#name');
            var reqOrgName = $('#orgName');
            var reqClassification = $('#classification');
            var reqServicePackName = $('#servicePackName');
            var reqThumbImgPath = $("#thumbImgPath");
            var reqOrgThumbImgPath = $("#orgThumbImgPath");
            var reqThumbImgName = $("#thumbImgName");
            var reqUseYn = $('#useYn_' + data.list[0].useYn);
            var reqDashboardUseYn = $('#dashboardUseYn_' + data.list[0].dashboardUseYn);
            var reqSummary = $('#summary');
            var reqDescription = $('#description');
            /*var reqParameterName = $('#parameterName' + cnt);
            var reqParameterType = $('#serviceParameterSelectType' + cnt);*/
            //var reqAppBindParameter = $('#app_bind_parameter');
            var reqAppBindYn = $('#appBindYn_' + data.list[0].appBindYn);
            reqName.val(resultData.name);
            reqOrgName.val(resultData.name);
            reqClassification.val(resultData.classification);
            reqServicePackName.val(resultData.servicePackName);
            reqThumbImgPath.val(resultData.thumbImgPath);
            reqOrgThumbImgPath.val(resultData.thumbImgPath);
            reqThumbImgName.val(resultData.thumbImgName);
            reqUseYn.attr('checked', true);
            reqDashboardUseYn.attr('checked', true);
            reqSummary.val(resultData.summary);
            reqDescription.val(resultData.description);
            reqAppBindYn.attr('checked', true);


            // 서비스 생성 파라미터 값 쪼개서 각 input box에 값 넣는 부분
            if(resultData.parameter != null || resultData.parameter != '' || resultData.parameter != undefined)
            $("#serviceParameterArea").empty();
            str = resultData.parameter.replace("}", "");
            var str2 = str.replace("{", "");
            var split = str2.split(",");
            count = split.length;
            var htmlString2 = [];

            if(count > 0){
                for(var j = 1; j < count + 1; j++){
                    var html2 = "<div class=" + "service_parameter_line" + j + ">"
                            + "<input type='text' class='form-control-parameter1' id=" + "parameterName"+ j + ">"
                            +"<select class='form-control-parameter2' id=" + "serviceParameterSelectType" + j + " " +"style='background:url(/resources/images/btn_down.png) no-repeat right; background-color:#fafafa;'>"+
                            "<option value='text'>텍스트형</option><option value='password'>패스워드형</option><option value='default'>자동입력</option><option value='userAppointed'>관리자 지정</option></select>";
                    html2 += "<button type='button' class='btn delete-service-parameter' style='margin-top:11px; margin-bottom:10px; margin-left:0px;' id='deleteServiceParameter' onclick='deleteLine(" + j + ")'>삭제</button></div>";

                    htmlString2.push(html2);
                }
                    var parameterInputBoxArea2 = $("#serviceParameterArea");
                    parameterInputBoxArea2.append(htmlString2);
            }else{
                var html2 = "<div class='service_parameter_line1'><input type='text' class='form-control-parameter1' id=" + "parameterName1" + ">"
                        +"<select class='form-control-parameter2' id=" + "serviceParameterSelectType1" + " " +"style='background:url(/resources/images/btn_down.png) no-repeat right; background-color:#fafafa;'>"+
                        "<option value='text'>텍스트형</option><option value='password'>패스워드형</option><option value='default'>자동입력</option><option value='userAppointed'>관리자 지정</option></select>"
                        +"<button type='button' class='btn btn-save btn-sm' style='margin-top:-9px;margin-left:-6px;' id='addServiceParameter'>추가</button>";
                html2 += "<button type='button' class='btn delete-service-parameter' style='margin-top:11px; margin-bottom:10px; margin-left:0px;' id='deleteServiceParameter' onclick='deleteLine(1)'>삭제</button></div>";

                htmlString2.push(html2);
            }
            for(var i = 0; i < split.length; i++) {
                var deleteSign = split[i].replace(/"/g, "");

                splitSign = deleteSign.split(":");
                if(splitSign != null && splitSign != "undefined" && splitSign != ""){
                var reqParameterName = $('#parameterName' + (i+1));
                var reqParameterType = $('#serviceParameterSelectType' + (i+1));

                    reqParameterName.val(splitSign[0]);
                    if(splitSign[1].length > 0){
                        if(splitSign[1].trim() == "text"){
                            reqParameterType.val(splitSign[1]);
                        }
                        if(splitSign[1].trim() == "password"){
                            reqParameterType.val(splitSign[1]);
                        }
                        if(splitSign[1].trim() == "default") {
                            reqParameterType.val(splitSign[1]);
                        }
                        if(splitSign[1].trim() == "userAppointed") {
                            reqParameterType.val(splitSign[1]);
                        }
                    }
                }
            }


            // 앱 바인드 사용 유무에 따라 앱 바인드 파라미터 입력 창 show / hide 여부
            $('input[name="appBindYn"]').click(function(){
                if($(this).attr('id') == 'appBindYn_Y') {
                    $('#app_bind_parameter_form').show();
                }else {
                    $('#app_bind_parameter_form').hide();
                }
            });



            // 앱 바인드 생성 파라미터 값 쪼개서 각 input box에 값 넣는 부분
            if(resultData.app_bind_parameter != null || resultData.app_bind_parameter != '' || resultData.app_bind_parameter != undefined)
                $("#appBindParameterArea").empty();
            appParameterStr = resultData.app_bind_parameter.replace("}", "");
            var appParameterStr2 = appParameterStr.replace("{", "");
            var split = appParameterStr2.split(",");
            count2 = split.length;
            var htmlString2 = [];

            if(count2 > 0){
                for(var a = 1; a < count2 + 1; a++){
                    var html2 = "<div class=" + "app_bind_parameter_line" + a + ">"
                            + "<input type='text' class='form-control-appBindParameter1' id=" + "appBindParameterName"+ a + ">"
                            +"<select class='form-control-parameter2' id=" + "appBindParameterSelectType" + a + " " +"style='background:url(/resources/images/btn_down.png) no-repeat right; background-color:#fafafa;'>"+
                            "<option value='text'>텍스트형</option><option value='password'>패스워드형</option><option value='default'>자동입력</option><option value='userAppointed'>관리자 지정</option></select>";
                    html2 += "<button type='button' class='btn delete-appBind-parameter' style='margin-top:11px; margin-bottom:10px; margin-left:0px;' id='deleteAppBindParameter' onclick='appBindParameterDeleteLine(" + a + ")'>삭제</button></div>";

                    htmlString2.push(html2);
                }
                var appBindParameterInputBoxArea = $("#appBindParameterArea");
                appBindParameterInputBoxArea.append(htmlString2);
            }else{
                var html2 = "<div class='app_bind_parameter_line1'><input type='text' class='form-control-appBindParameter1' id=" + "appBindParameterName1" + ">"
                        +"<select class='form-control-parameter2' id=" + "appBindParameterSelectType1" + " " +"style='background:url(/resources/images/btn_down.png) no-repeat right; background-color:#fafafa;'>"+
                        "<option value='text'>텍스트형</option><option value='password'>패스워드형</option><option value='default'>자동입력</option><option value='userAppointed'>관리자 지정</option></select>"
                        +"<button type='button' class='btn btn-save btn-sm' style='margin-top:-9px;margin-left:-6px;' id='addAppBindParameter'>추가</button>";
                html2 += "<button type='button' class='btn delete-appBind-parameter' style='margin-top:11px; margin-bottom:10px; margin-left:0px;' id='deleteAppBindParameter' onclick='appBindParameterDeleteLine(1)'>삭제</button></div>";

                htmlString2.push(html2);
            }
            for(var b = 0; b < split.length; b++) {
                var deleteSign = split[b].replace(/"/g, "");

                splitSign2 = deleteSign.split(":");
                if(splitSign2 != null && splitSign2 != "undefined" && splitSign2 != ""){
                    var reqParameterName = $('#appBindParameterName' + (b+1));
                    var reqParameterType = $('#appBindParameterSelectType' + (b+1));

                    reqParameterName.val(splitSign2[0]);
                    if(splitSign2[1].length > 0){
                        if(splitSign2[1].trim() == "text"){
                            reqParameterType.val(splitSign2[1]);
                        }
                        if(splitSign2[1].trim() == "password"){
                            reqParameterType.val(splitSign2[1]);
                        }
                        if(splitSign2[1].trim() == "default") {
                            reqParameterType.val(splitSign2[1]);
                        }
                        if(splitSign2[1].trim() == "userAppointed") {
                            reqParameterType.val(splitSign2[1]);
                        }
                    }
                }
            }


            var tempThumbImgPath = reqThumbImgPath.val();

            if (tempThumbImgPath == null || tempThumbImgPath == '') {
                $('#divPreview').hide();
            } else {
                $('#divPreview').attr('style', 'display: block; height: 94px;');
                $('#preview').attr('src', IMAGE_PATH_PREFIX+tempThumbImgPath);
                $("#labelThumbName").text(reqThumbImgName.val());
            }
        };


        // 서비스 생성 파라미터 행 삭제
        function deleteLine(number){
            var serviceParameterCount = $('.form-control-parameter1').length;
            if(serviceParameterCount > 1){
                var serviceParameterLine = "service_parameter_line" + number;
                $('.' + serviceParameterLine).remove();
            }else{
                $("#parameterName" + number).val("");
            }
        }


        // 앱 바인드 생성 파라미터 행 삭제
        function appBindParameterDeleteLine(number){
            var appBindParameterCount = $('.form-control-appBindParameter1').length;
            if(appBindParameterCount > 1){
                var appBindParameterLine = "app_bind_parameter_line" + number;
                $('.' + appBindParameterLine).remove();
            }else{
                $("#appBindParameterName" + number).val("");
            }
        }

        // CHECK DELETE
        var procCheckDelete = function() {
            $('div.modal').modal('toggle');

            var reqNo = REQ_NO;
            if (null == reqNo || "" == reqNo) return false;

            procCallAjax(CHECK_DELETE_PROC_URL, {no: reqNo}, procDelete);
        };


        // DELETE
        var procDelete = function(data) {
            if (RESULT_STATUS_SUCCESS != data.RESULT) {
                procAlert("danger", data.RESULT_MESSAGE);
                return false;
            }

            document.getElementById('thumbImgPath').value = '';

            procCallAjax(DELETE_PROC_URL, {no: REQ_NO}, procCallbackDelete);
        };


        // DELETE CALLBACK
        var procCallbackDelete = function(data) {
            if (RESULT_STATUS_SUCCESS == data.RESULT) {
                procDeleteFile();
                procAlert("success", '<spring:message code="common.info.result.success" />');

            } else {
                procAlert("danger", data.RESULT_MESSAGE);
            }
        };


        // DELETE FILE CHECK VALID
        var procDeleteFileCheckValid = function() {
            var doc = document;
            var orgThumbImgPath = doc.getElementById('orgThumbImgPath').value;
            var thumbImgPath = doc.getElementById('thumbImgPath').value;
            var insertFlag = INSERT_FLAG;

            if ("" != insertFlag && CUD_U == insertFlag) {
                if (orgThumbImgPath == thumbImgPath || orgThumbImgPath == "" || orgThumbImgPath == null) {
                    return false;
                }
            }

            return true;
        };


        // DELETE FILE
        var procDeleteFile = function() {
            if (procDeleteFileCheckValid()) {
                procCallAjax(DELETE_IMAGE_PROC_URL, {thumbImgPath : document.getElementById('orgThumbImgPath').value}, procCallbackDeleteFile);
            } else {
                procMovePage(CATEGORY_HOME_URL);
            }
        };


        // DELETE FILE CALLBACK
        var procCallbackDeleteFile = function() {
            procMovePage(CATEGORY_HOME_URL);
        };


        // RESET IMAGE
        var procResetImage = function() {
            var doc = document;
            doc.getElementById('thumbImgPath').value = '';
            doc.getElementById('thumbImgName').value = '';

            resetThumbnail();
        };


        // INIT
        var procInit = function() {
            getCategoryList();

            $('#name').focus();
            $('#useYn_Y').attr('checked', true);
            $('#dashboardUseYn_N').attr('checked', true);
            $('#appBindYn_Y').attr('checked', true);

            $('input[name="appBindYn"]').click(function(){
                if($(this).attr('id') == 'appBindYn_Y') {
                    $('#app_bind_parameter_form').show();
                }else {
                    $('#app_bind_parameter_form').hide();
                }
            });
            var insertFlag = INSERT_FLAG;
            if ("" != insertFlag && CUD_U == insertFlag) {
                procUpdateForm();
            }else{
                var parameterInputBoxArea2 = $("#serviceParameterArea");
                var html3 = "<div class='service_parameter_line1'><input type='text' class='form-control-parameter1' id=" + "parameterName1" + ">"
                        +"<select class='form-control-parameter2' id=" + "serviceParameterSelectType1" + " " +"style='background:url(/resources/images/btn_down.png) no-repeat right; background-color:#fafafa;'>"+
                        "<option value='text'>텍스트형</option><option value='password'>패스워드형</option><option value='default'>자동입력</option><option value='userAppointed'>사용자 지정</option></select>";
                html3 += "<button type='button' class='btn delete-service-parameter' style='margin-top:11px; margin-bottom:10px; margin-left:0px;' id='deleteServiceParameter' onclick='deleteLine(1)'>삭제</button></div>";

                parameterInputBoxArea2.append(html3);


                var appBindParameterInputBoxArea = $("#appBindParameterArea");
                var appBindHtml = "<div class='app_bind_parameter_line1'><input type='text' class='form-control-appBindParameter1' id=" + "appBindParameterName1" + ">"
                        +"<select class='form-control-parameter2' id=" + "appBindParameterSelectType1" + " " +"style='background:url(/resources/images/btn_down.png) no-repeat right; background-color:#fafafa;'>"+
                        "<option value='text'>텍스트형</option><option value='password'>패스워드형</option><option value='default'>자동입력</option><option value='userAppointed'>사용자 지정</option></select>";
                appBindHtml += "<button type='button' class='btn delete-appBind-parameter' style='margin-top:11px; margin-bottom:10px; margin-left:0px;' id='deleteAppBindParameter' onclick='appBindParameterDeleteLine(1)'>삭제</button></div>";

                appBindParameterInputBoxArea.append(appBindHtml);
            }

            resetThumbnail();

            $("#hiddenThumbnail").on("change", function() {
                setPreView($(this));
            });

            procAlert("info", WELCOME_MESSAGE);
        };


        // BIND :: BUTTON EVENT
        $("#btnResetImg").on("click", function() {
            procResetImage();
        });


        //var parameterName;
        //var parameterType;
        //var mediumParameter;
        var medium2Parameter = '';
        var completeParameter;
        //var deleteComma;
        var appBindMedium2Parameter = '';
        var completeParameter2;

        // BIND :: BUTTON EVENT
        $("#btnRegist").on("click", function() {
            // 서비스 생성 파라미터 합치는 부분
            for(var i = 0; i < 100; i++){
                var parameterName = $("#parameterName"+ (i+1)).val();
                if(parameterName != null && parameterName != '' && parameterName != undefined){
                    if($("#serviceParameterSelectType" + (i+1)).val() != null){
                        var parameterType = $("#serviceParameterSelectType" + (i+1)).val();
                        var mediumParameter = '"' + parameterName + '"' + ":" + '"' + parameterType + '"';
                        medium2Parameter += "," + mediumParameter;
                    }
                }
            }
                var deleteComma = medium2Parameter.replace(",", "");
                deleteComma = deleteComma.replace(/(\s*)/g, "");

            if(deleteComma != null && deleteComma != '' && deleteComma != undefined){
                completeParameter = "{" + deleteComma + "}";
            }else{
                completeParameter = '';
            }


            // 앱 바인드 파라미터 합치는 부분
            for(var i = 0; i < 100; i++){
                var appBindParameterName = $("#appBindParameterName"+ (i+1)).val();
                if(appBindParameterName != null && appBindParameterName != '' && appBindParameterName != undefined){
                    if($("#appBindParameterSelectType" + (i+1)).val() != null){
                        var appBindParameterType = $("#appBindParameterSelectType" + (i+1)).val();
                        var appBindMediumParameter = '"' + appBindParameterName + '"' + ":" + '"' + appBindParameterType + '"';
                        appBindMedium2Parameter += "," + appBindMediumParameter;
                    }
                }
            }
            var deleteComma = appBindMedium2Parameter.replace(",", "");
            deleteComma = deleteComma.replace(/(\s*)/g, "");

            if(deleteComma != null && deleteComma != '' && deleteComma != undefined){
                completeParameter2 = "{" + deleteComma + "}";
            }else{
                completeParameter2 = '';
            }

            procCheckDuplicatedName();
        });


        // BIND :: BUTTON EVENT
        $("#btnCancel").on("click", function() {
            procMovePage(CATEGORY_HOME_URL + "/<%= Constants.TAB_NAME_SERVICE_PACK %>");
        });


        // BIND :: BUTTON EVENT
        $("#btnDelete").on("click", function() {
            procPopup('카탈로그', DELETE_MESSAGE, 'procCheckDelete();');
        });


        $("#addServiceParameter").on("click", function(){
            var cnt = $(".form-control-parameter1").length;
            cnt++;
            var htmlString = [];
            var html = "<div class=" + "service_parameter_line" + cnt + "><input type='text' class='form-control-parameter1' id=" + "parameterName"+ cnt + ">"
                    +"<select class='form-control-parameter2' id=" + "serviceParameterSelectType" + cnt + " " +"style='background:url(/resources/images/btn_down.png) no-repeat right; background-color:#fafafa;'>"+
                    "<option value='text'>텍스트형</option><option value='password'>패스워드형</option><option value='default'>자동입력</option><option value='userAppointed'>사용자 지정</option></select>";
            html += "<button type='button' class='btn delete-service-parameter' style='margin-top:11px; margin-bottom:10px; margin-left:0px;' id='deleteServiceParameter' onclick='deleteLine(" + cnt + ")'>삭제</button></div>";

            htmlString.push(html);

            var parameterInputBoxArea = $("#serviceParameterArea");
            parameterInputBoxArea.append(htmlString);

        });


        $("#addAppBindParameter").on("click", function(){
            var cnt = $(".form-control-appBindParameter1").length;
            cnt++;
            var htmlString = [];
            var html = "<div class=" + "app_bind_parameter_line" + cnt + "><input type='text' class='form-control-appBindParameter1' id=" + "appBindParameterName"+ cnt + ">"
                    +"<select class='form-control-parameter2' id=" + "appBindParameterSelectType" + cnt + " " +"style='background:url(/resources/images/btn_down.png) no-repeat right; background-color:#fafafa;'>"+
                    "<option value='text'>텍스트형</option><option value='password'>패스워드형</option><option value='default'>자동입력</option><option value='userAppointed'>사용자 지정</option></select>";
            html += "<button type='button' class='btn delete-appBind-parameter' style='margin-top:11px; margin-bottom:10px; margin-left:0px;' id='deleteAppBindParameter' onclick='appBindParameterDeleteLine(" + cnt + ")'>삭제</button></div>";

            htmlString.push(html);

            var parameterInputBoxArea = $("#appBindParameterArea");
            parameterInputBoxArea.append(htmlString);

        });


        // 앱 바인드 사용 onclick 시 앱 바인드 파라미터 show / hide
        function yesNoCheck() {
            if (document.getElementById('appBindYn_Y').checked) {
                $(".app_bind_parameter_form").show();
            } else {
                $(".app_bind_parameter_form").hide();
            }
        }

        // ON LOAD
        $(document.body).ready(function() {
            procInit();
        });
    </script>
    <%--
    ====================================================================================================
    SCRIPT END
    ====================================================================================================
    --%>
