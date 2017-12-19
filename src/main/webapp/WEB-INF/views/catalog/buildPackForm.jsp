<!--
=================================================================
* 시  스  템  명 : PaaS-TA 관리자 포탈
* 업    무    명 : 카탈로그
* 프로그램명(ID) : buildPackForm.jsp(앱개발환경카탈로그관리)
* 프로그램  개요 : 앱 개발환경 카탈로그를 등록/수정하는 화면
* 작    성    자 : 김도준
* 작    성    일 : 2016.07.06
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
                <h4 class="modify_h4 fwn">앱 개발환경 상세</h4>
            </c:when>
            <c:when test="${INSERT_FLAG eq Constants.CUD_C}">
                <h4 class="modify_h4 fwn">앱 개발환경 등록</h4>
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
                <label class="control-label col-sm-2" for="buildPackName"> 앱 개발환경 </label>
                <div class="col-sm-9">
                    <select class="form-control" id="buildPackName" name="buildPackName" style="background:url(/resources/images/btn_down.png) no-repeat right; background-color:#fafafa;">
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

            <%-- 앱 샘플 영역 : 디자인 필요 --%>

            <div class="form-group">
                <label class="control-label col-sm-2" for="appSampleFile">앱 샘플</label>

                <div class="col-sm-8">
                    <input type="text" class="form-control" placeholder="파일 첨부는 1개 파일만 가능합니다."  style="width: 102%; background: white; cursor: default" disabled>
                </div>
                <div class="col-sm-1">
                    <button type="button" id="btnSearchAppSampleFile" class="btn btn-cancel btn-sm tar" style="margin-top:11px;margin-left:-5px;" >
                        <span class="glyphicon glyphicon-floppy-disk"></span>&nbsp;파일찾기
                    </button>
                    <%--<input type="file" id="hiddenAppSample" style="display: none">--%>
                </div>

                <div class="col-sm-9" style="margin-left:16.9%;">
                    <div id="divPreviewAppSample" class="divPreview"  style="width: 99.9%;height:60px;">
                        <div class="fl">
                            <span id="appSampleFileNameLabel"></span>
                            <%--<a id="thumbname">앱샘플이미지.png</a>--%>
                        </div>
                        <div class="divImageInfo fl ml20">
                            <%--<label id="labelThumbName"></label>--%>
                            <button id="btnDeleteAppSampleFile" type="button" class="btn-del mt-30 ml3" aria-label="파일 지우기">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </div>

            </div>

<%--//////////////////////////////--%>
            <%--<div class="form-group">
                <label class="control-label col-sm-2" for="appSampleFile"> 앱 샘플 </label>
                <div class="col-sm-8">

                    <div class="custom_panel custom_panel_default"  style="margin-left: -10px">
                        <div class="custom_panel_body pd10" style="min-height: 56px;">
                            <div class="fl" style="margin: 3px 0 0 0;">
                                <span id="appSampleFileNameLabel" class="modify_panel_title"> 파일 첨부는 1개 파일만 가능합니다. </span>
                            </div>
                            <div id="appSampleFileDeleteButtonArea" class="fl">
                                <button id="btnDeleteAppSampleFile" type="button" class="btn btn-default ml5" aria-label="앱 샘플 지우기">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                </button>
                            </div>
                            <div class="fr" style="margin-top: -5px">
                                <button type="button" id="btnSearchAppSampleFile" class="btn fr">
                                    <span class="glyphicon glyphicon-folder-open"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>--%>
<%--//////////////////////////////--%>


            <div class="fl">
                <label for="appSampleFile" class="sr-only"> FILE </label>
                <input type="file" name="files[]" id="appSampleFile" class="app_sample_file_object">
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

            <%--HIDDEN VALUE--%>
            <input type="hidden" id="orgThumbImgPath" name="orgThumbImgPath" value="" />
            <input type="hidden" id="thumbImgPath" name="thumbImgPath" value="" />
            <input type="hidden" id="thumbImgName" name="thumbImgName" value="" />
            <input type="hidden" id="orgName" name="orgName" value="" />
            <input type="hidden" id="no" name="no" value="<c:out value='${REQUEST_NO}' default='' />" />

            <input type="hidden" id="appSampleFileName" name="appSampleFileName" value="" />
            <input type="hidden" id="appSampleFilePath" name="appSampleFilePath" value="" />
            <input type="hidden" id="appSampleFileSize" name="appSampleFileSize" value="" />
            <input type="hidden" id="orgAppSampleFileName" name="orgAppSampleFileName" value="" />
            <input type="hidden" id="orgAppSampleFilePath" name="orgAppSampleFilePath" value="" />
            <input type="hidden" id="orgAppSampleFileSize" name="orgAppSampleFileSize" value="" />


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
        var CATALOG_LIST_PROC_URL = "<c:url value='/catalog/getBuildPackCatalogList' />";
        var CATEGORY_LIST_PROC_URL = "<c:url value='/commonCode/getCommonCode' />";
        var BUILD_PACK_LIST_PROC_URL = "<c:url value='/catalog/getBuildPackList' />";
        var DUPLICATED_PROC_URL = "<c:url value='/catalog/getBuildPackCatalogCount' />";
        var INSERT_PROC_URL = "<c:url value='/catalog/insertBuildPackCatalog' />";
        var UPDATE_PROC_URL = "<c:url value='/catalog/updateBuildPackCatalog' />";
        var DELETE_PROC_URL = "<c:url value='/catalog/deleteBuildPackCatalog' />";
        var CHECK_DELETE_PROC_URL = "<c:url value='/catalog/getCheckDeleteBuildPackCatalogCount' />";
        var UPLOAD_IMAGE_PROC_URL = "<c:url value='/catalog/uploadThumbnailImage'/>";
        var DELETE_IMAGE_PROC_URL = "<c:url value='/catalog/deleteThumbnailImage'/>";
        var UPLOAD_APP_SAMPLE_PROC_URL = "<c:url value='/catalog/uploadAppSampleFile'/>";
        var DELETE_APP_SAMPLE_PROC_URL = "<c:url value='/catalog/deleteAppSampleFile'/>";
        var BUILD_PACK_CATALOG_ID = "<%= Constants.BUILD_PACK_CATALOG_ID %>";
        var INSERT_FLAG = "<c:out value='${INSERT_FLAG}' default='' />";
        var CUD_U = "<%= Constants.CUD_U %>";
        var REQ_NO = "<c:out value='${REQUEST_NO}' default='' />";

        var INIT_MESSAGE = "첨부된 파일이 없습니다.";
        var REQUEST_APP_SAMPLE_FILE;
        var REQUEST_APP_SAMPLE_FILE_OBJECT;
        REQUEST_APP_SAMPLE_FILE_OBJECT = $('#appSampleFile');


        // GET CATEGORY LIST
        var getCategoryList = function() {
            procCallAjax(CATEGORY_LIST_PROC_URL + "/" + BUILD_PACK_CATALOG_ID, null, procCallbackCategoryList);
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

            getBuildPackList();
        };


        // GET BUILD PACK LIST
        var getBuildPackList = function() {
            procCallAjax(BUILD_PACK_LIST_PROC_URL, {}, procCallbackBuildPackList);
        };


        // GET BUILD PACK LIST CALLBACK
        var procCallbackBuildPackList = function(data) {
            if (RESULT_STATUS_FAIL == data.RESULT) return false;

            var objSelectBox = $('#buildPackName');
            var listLength = data.list.length;
            var htmlString = [];

            for (var i = 0; i < listLength; i++) {
                htmlString.push("<option value='" + data.list[i].name + "'>" + data.list[i].name + "</option>");
            }

            objSelectBox.append(htmlString);

            var reqAppSampleFileName = $("#appSampleFileName").val();

            if (reqAppSampleFileName == '' || reqAppSampleFileName == null) {
                $('#appSampleFileNameLabel').text(INIT_MESSAGE);
            }

            var insertFlag = INSERT_FLAG;
            if ("" != insertFlag && CUD_U == insertFlag) procUpdateForm();
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


        // CALL BACK UPDATE FORM
        var procCallbackUpdateForm = function(data) {
            if (RESULT_STATUS_FAIL == data.RESULT) return false;

            if (data.list.length < 1) return false;

            var resultData = data.list[0];

            var reqName = $('#name');
            var reqOrgName = $('#orgName');
            var reqClassification = $('#classification');
            var reqBuildPackName = $('#buildPackName');
            var reqThumbImgPath = $("#thumbImgPath");
            var reqOrgThumbImgPath = $("#orgThumbImgPath");
            var reqThumbImgName = $("#thumbImgName");
            var reqUseYn = $('#useYn_' + resultData.useYn);
            var reqSummary = $('#summary');
            var reqDescription = $('#description');

            reqName.val(resultData.name);
            reqOrgName.val(resultData.name);
            reqClassification.val(resultData.classification);
            reqBuildPackName.val(resultData.buildPackName);
            reqThumbImgPath.val(resultData.thumbImgPath);
            reqOrgThumbImgPath.val(resultData.thumbImgPath);
            reqThumbImgName.val(resultData.thumbImgName);
            reqUseYn.attr('checked', true);
            reqSummary.val(resultData.summary);
            reqDescription.val(resultData.description);

            var tempThumbImgPath = reqThumbImgPath.val();

            if (null != tempThumbImgPath && "" != tempThumbImgPath) {
                $('#divPreview').attr('style', 'display: block; height: 94px;');
            }

            $('#preview').attr('src', IMAGE_PATH_PREFIX+tempThumbImgPath);
            $("#labelThumbName").text(reqThumbImgName.val());

            var appSampleFileName = resultData.appSampleFileName;

            if ('' != appSampleFileName) {
                var appSampleFilePath = resultData.appSampleFilePath;
                var appSampleFileSize = resultData.appSampleFileSize;

                var reqAppSampleFileName = $("#appSampleFileName");
                var reqAppSampleFilePath = $("#appSampleFilePath");
                var reqAppSampleFileSize = $("#appSampleFileSize");
                var reqOrgAppSampleFileName = $("#orgAppSampleFileName");
                var reqOrgAppSampleFilePath = $("#orgAppSampleFilePath");
                var reqOrgAppSampleFileSize = $("#orgAppSampleFileSize");

                reqAppSampleFileName.val(appSampleFileName);
                reqAppSampleFilePath.val(appSampleFilePath);
                reqAppSampleFileSize.val(appSampleFileSize);
                reqOrgAppSampleFileName.val(appSampleFileName);
                reqOrgAppSampleFilePath.val(appSampleFilePath);
                reqOrgAppSampleFileSize.val(appSampleFileSize);

                procSetFileLabel(appSampleFileName, appSampleFileSize, appSampleFilePath);
            } else {
                $('#appSampleFileNameLabel').text(INIT_MESSAGE);
            }
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
                procUploadThumbnailImageFile({RESULT : RESULT_STATUS_SUCCESS});
            } else {
                procCallAjax(DUPLICATED_PROC_URL, {name: reqName}, procUploadThumbnailImageFile);
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

        // UPLOAD THUMBNAIL IMAGE FILE
        var procUploadThumbnailImageFile = function(data) {
            if (RESULT_STATUS_SUCCESS != data.RESULT) {
                $('#name').focus();
                return false;
            }

            var formData = getFileFormData();

            if (formData != undefined) {
                uploadFile(formData, UPLOAD_IMAGE_PROC_URL, procUploadAppSampleFile);
            } else {
                procUploadAppSampleFile();
            }
        };


        // UPLOAD APP SAMPLE FILE
        var procUploadAppSampleFile = function() {
            var formData = getAppSampleFileFormData();

            if (!formData) {
                procInsert();
            } else {
                procUploadFile(formData, UPLOAD_APP_SAMPLE_PROC_URL, procCallbackUploadAppSampleFile);
            }
        };


        // UPLOAD APP SAMPLE FILE CALLBACK
        var procCallbackUploadAppSampleFile = function(data) {
            if (RESULT_STATUS_SUCCESS == data.RESULT) document.getElementById('appSampleFilePath').value = data.path;
            procInsert();
        };


        // APP SAMPLE FILE FORM DATA
        var getAppSampleFileFormData = function() {
            var reqFileObject = REQUEST_APP_SAMPLE_FILE;
            if (null == reqFileObject || undefined === reqFileObject) return false;

            var formData = new FormData();
            formData.append("file", reqFileObject[0]);

            return formData;
        };


        // INSERT
        var procInsert = function() {
            var doc = document;
            var reqUseYn = $("input:radio[name='useYn']:checked").val();
            var param = $("#catalogForm").serializeObject();

            var param2 = {
                useYn: reqUseYn,
                summary: doc.getElementById('summary').value,
                description: doc.getElementById('description').value,
                classification: doc.getElementById('classification').value
            };

            $.extend(param, param2);

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
                procDeleteThumbnailImageFile();
                procAlert("success", '<spring:message code="common.info.result.success" />');

            } else {
                procAlert("danger", data.RESULT_MESSAGE);
            }
        };


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
                procDeleteThumbnailImageFile();
                procAlert("success", '<spring:message code="common.info.result.success" />');
                procMovePage(CATEGORY_HOME_URL);

            } else {
                procAlert("danger", data.RESULT_MESSAGE);
            }
        };


        // DELETE THUMBNAIL IMAGE FILE
        var procDeleteThumbnailImageFile = function() {
            if (procCheckDeleteFileValid('<%= Constants.FILE_TYPE_IMAGE %>')) {
                procCallAjax(DELETE_IMAGE_PROC_URL, {thumbImgPath : document.getElementById('orgThumbImgPath').value}, procDeleteAppSampleFile);
            } else {
                procDeleteAppSampleFile();
            }
        };


        // DELETE APP SAMPLE FILE
        var procDeleteAppSampleFile = function() {
            if (procCheckDeleteFileValid('<%= Constants.FILE_TYPE_APP_SAMPLE %>')) {
                procCallAjax(DELETE_APP_SAMPLE_PROC_URL, {appSampleFilePath : document.getElementById('orgAppSampleFilePath').value}, procCallbackDeleteFile);
            } else {
                procMovePage(CATEGORY_HOME_URL);
            }
        };


        // CHECK DELETE FILE VALID
        var procCheckDeleteFileValid = function(reqFileType) {
            var doc = document;
            var orgFilePath = doc.getElementById('orgThumbImgPath').value;
            var filePath = doc.getElementById('thumbImgPath').value;

            if ('<%= Constants.FILE_TYPE_APP_SAMPLE %>' == reqFileType) {
                orgFilePath = doc.getElementById('orgAppSampleFilePath').value;
                filePath = doc.getElementById('appSampleFilePath').value;
            }

            var insertFlag = INSERT_FLAG;
            if ("" != insertFlag && CUD_U == insertFlag) {
                if (orgFilePath == filePath || orgFilePath == "" || orgFilePath == null) {
                    return false;
                }
            }

            return true;
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


        // SET FILE INFO
        var procSetFileInfo = function(reqFile) {
            var fileName = reqFile[0].name;
            var limitFileSizeMb = '<%= Constants.CATALOG_APP_SAMPLE_LIMIT_FILE_SIZE_MB %>';
            var limitFileSize = parseInt(limitFileSizeMb) << 20;
            var orgFileSize = reqFile[0].size;

            if (limitFileSize < orgFileSize) {
                var alertMessage = '파일 크기는 ' + limitFileSizeMb + ' MB 를 넘을 수 없습니다.';
                procAlert("danger", alertMessage);
                window.scrollTo(0, 0);
                return false;
            }

            REQUEST_APP_SAMPLE_FILE = reqFile;

            procSetFileLabel(fileName, orgFileSize, '<%= Constants.NONE_VALUE %>');
        };


        // SET FILE LABEL
        var procSetFileLabel = function(reqFileName, reqOrgFileSize, reqFilePath) {
            if (null == reqFileName || '' == reqFileName) return false;

            var doc = document;
            var fileSize = procConvertFormatNumber(procCalculateFloor(parseInt(reqOrgFileSize) / 1024, 2));
            var fileName = reqFileName + " (" + fileSize + " KB)";

            doc.getElementById('appSampleFileName').value = reqFileName;
            doc.getElementById('appSampleFileSize').value = reqOrgFileSize;

            if ('<%= Constants.NONE_VALUE %>' != reqFilePath) {
                var linkHtml = '<a href="javascript:void(0);" onclick="procDownload(\'' + reqFilePath + '\', \'' + reqFileName + '\')">' + fileName + '</a>';
                $('#appSampleFileNameLabel').html(linkHtml);

            } else {
                $('#appSampleFileNameLabel').text(fileName);
            }

            $('#appSampleFileDeleteButtonArea').show();

            // BIND :: BUTTON EVENT
            $("#btnDeleteFile").on("click", function() {
                procDeleteAppSampleFileLabel();
            });
        };


        // DELETE FILE LABEL
        var procDeleteAppSampleFileLabel = function() {
            var doc = document;
            var fileDeleteButtonArea = $('#appSampleFileDeleteButtonArea');
            var reqFileLabel = $('#appSampleFileNameLabel');

            fileDeleteButtonArea.hide();
            reqFileLabel.text(INIT_MESSAGE);

            doc.getElementById('appSampleFileName').value = '';
            doc.getElementById('appSampleFilePath').value = '';
            doc.getElementById('appSampleFileSize').value = '';
        };


        // CALCULATE FLOOR
        var procCalculateFloor = function(reqNumber, reqPosition) {
            var digits = Math.pow(10, reqPosition);
            var num = Math.round(reqNumber * digits) / digits;

            return num.toFixed(reqPosition);
        };


        // CONVERT FORMAT NUMBER
        var procConvertFormatNumber = function (reqString) {
            return reqString.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        };


        // INIT
        var procInit = function() {
            getCategoryList();

            $('#name').focus();
            $('#useYn_Y').attr('checked', true);

            var insertFlag = INSERT_FLAG;
            if ("" != insertFlag && CUD_U == insertFlag) procUpdateForm();

            resetThumbnail();

            $("#hiddenThumbnail").on("change", function() {
                setPreView($(this));
            });

            $('#appSampleFileDeleteButtonArea').hide();
            procAlert("info", WELCOME_MESSAGE);
        };


        // BIND :: BUTTON EVENT
        $("#btnResetImg").on("click", function() {
            procResetImage();
        });


        // BIND :: BUTTON EVENT
        $("#btnDeleteAppSampleFile").on("click", function() {
            procDeleteAppSampleFileLabel();
        });


        // BIND :: BUTTON EVENT
        $("#btnSearchAppSampleFile").on("click", function() {
            REQUEST_APP_SAMPLE_FILE_OBJECT.click();
        });


        // BIND :: FILE CHANGE
        REQUEST_APP_SAMPLE_FILE_OBJECT.on("change", function() {
            procSetFileInfo($(this)[0].files);
        });


        // BIND :: BUTTON EVENT
        $("#btnRegist").on("click", function() {
            procCheckDuplicatedName();
        });


        // BIND :: BUTTON EVENT
        $("#btnCancel").on("click", function() {
            procMovePage(CATEGORY_HOME_URL + "/<%= Constants.TAB_NAME_BUILD_PACK %>");
        });


        // BIND :: BUTTON EVENT
        $("#btnDelete").on("click", function() {
            procPopup('카탈로그', DELETE_MESSAGE, 'procCheckDelete();');
        });


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
