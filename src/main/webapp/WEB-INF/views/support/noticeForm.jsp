<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript" src="<c:url value='/resources/js/fileUpload.js' />"></script>

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


<div id="statusTitle" class="col-sm-6 pt30">
    <c:choose>
        <c:when test="${INSERT_FLAG eq Constants.CUD_U}">
        <h4 class="modify_h4 fwn">공지 상세</h4>
        </c:when>
        <c:when test="${INSERT_FLAG eq Constants.CUD_C}">
        <h4 class="modify_h4 fwn">공지 등록</h4>
        </c:when>
    </c:choose>
</div>



<!--인스턴스 설정-->
<div class="col-md-11 col-md-offset-14">
    <form class="form-horizontal" role="form" id="noticeForm" name="noticeForm">
        <%--<input type="hidden" id="thumb_image_path" value="${thumb_image_path}">--%>

        <div class="form-group">
            <label class="control-label col-sm-2" for="title">제목</label>
            <div class="col-sm-9">
                <input type="text" maxlength="100" class="form-control toCheckString" id="title">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="classification">분류</label>
            <div class="col-sm-7">
                <select class="form-control" id="classification" style="background:url(/resources/images/btn_down.png) no-repeat right; background-color:#fafafa;">
                    <%-- Category Area --%>
                </select>
            </div>
            <div class="col-sm-2" style="padding-top: 15px">
                <input type="checkbox" id="important"/> &nbsp; 중요 공지
                &nbsp;&nbsp;&nbsp;
                <a href="#" data-toggle="tooltip" data-placement="bottom" title="중요 공지 체크시 최상단에 노출됩니다.">
                    <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                </a>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="useYn">공개</label>
            <div class="col-sm-9 mt10">
                <label class="radio-inline"><input type="radio" id="useYn" name="useYn" value="Y" checked="checked">Y</label>
                <label class="radio-inline"><input type="radio" name="useYn" value="N">N</label>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="content">내용</label>
            <div class="col-sm-9">
                <textarea class="form-control" rows="5" id="content"></textarea>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="attachedFile">첨부 파일</label>

            <div class="col-sm-8">
                <input type="text" class="form-control" placeholder="파일 첨부는 1개 파일만 가능합니다."  style="width: 102%; background: white; cursor: default" disabled>
            </div>
            <div class="col-sm-1">
                <button type="button" id="attachedFile" class="btn btn-cancel btn-sm tar" style="margin-top:11px;margin-left:-5px;" onclick="$('#hiddenFile').click()">
                    <span class="glyphicon glyphicon-floppy-disk"></span>&nbsp;파일찾기
                </button>
                <input type="file" id="hiddenFile" style="display: none">

            </div>

            <div class="col-sm-9" style="margin-left:16.9%;">
                <div id="divFileArea" class="divPreview"  style="width: 99.9%;height:60px;">
                    <div class="fl">
                        <span id="attachedFileLabel"></span>
                        <%--<a id="thumbname">앱샘플이미지.png</a>--%>
                    </div>
                    <div class="divImageInfo fl ml20">
                        <%--<label id="labelThumbName"></label>--%>
                        <button id="delFIleImg" type="button" class="btn-del mt-30 ml3" aria-label="첨부파일 지우기">
                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                        </button>
                    </div>
                </div>
            </div>

        </div>

        <%--<div class="form-group">
            <label class="control-label col-sm-2" for="thumbnail">첨부파일</label>

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
        </div>--%>




        <div class="form-group">
            <label class="control-label col-sm-2" >게시일</label>
            <div class="col-sm-3">
                <div class="input-group date" id="startDate" >
                    <input type="text" class="form-control" style="background:url(/resources/images/btn_cal.png) no-repeat right; background-color: white; border-radius: 5px 5px 5px 5px;" id="startDateInput"/>
                </div>
            </div>

            <div class="col-sm-1" style="margin-left: -20px">
            <label class="control-label-gray col-sm-1 mt13 ml-25"> ~ </label>
            </div>
            <div class="col-sm-3">
            <div class="input-group date" id="endDate" >
                <input type="text" class="form-control" style=" background:url(/resources/images/btn_cal.png) no-repeat right; background-color: white; border-radius: 5px 5px 5px 5px;" id="endDateInput"/>
            </div>

            </div>
            <div class="col-sm-2" style="padding-top: 15px">
                <input type="checkbox" id="checkMaxDate"/> max date
            </div>

        </div>



        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-9" align="right">
                <div class="divButtons" style="width:97.8%;">
                    <c:set var="insertFlag" value="${INSERT_FLAG}" />
                    <c:set var="checkCudU" value="<%= Constants.CUD_U %>" />
                    <c:choose>
                        <c:when test="${INSERT_FLAG eq CONSTANT_CUD}">
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

        <input type="hidden" id="no" name="no" value="<c:out value='${REQUEST_NO}' default='' />" />

        <input type="hidden" id="classificationValue" name="classificationValue" value="" />

    </form>
</div>
<!--//인스턴스 설정-->
<%--
<div class="row">
</div>
--%>






<%--



<form class="form-horizontal" role="form" id="noticeForm" >

    <div class="form-group">
        <label class="control-label col-sm-1" for="title">제목:</label>
        <div class="col-sm-10">
            <input type="text" maxlength="100" class="form-control toCheckString" id="title"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="classification">분류:</label>
        <div class="col-sm-8">
            <select class="form-control" id="classification">
                               &lt;%&ndash; <option> 공지 </option>
                                <option> 장애 </option>
                                <option> 이벤트 </option>&ndash;%&gt;
            </select>
        </div>
        <div class="col-sm-2">
            <input type="checkbox" id="important"/> &nbsp; 중요 공지
            &nbsp;&nbsp;&nbsp;
            <a href="#" data-toggle="tooltip" data-placement="bottom" title="중요 공지 체크시 최상단에 노출됩니다.">
                <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
            </a>
        </div>

    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="useYn">공개:</label>
        <div class="col-sm-10">
            <label class="radio-inline"><input type="radio" id="useYn" name="useYn" value="Y" checked="checked">Y</label>
            <label class="radio-inline"><input type="radio" name="useYn" value="N">N</label>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-1" for="content">내용:</label>
        <div class="col-sm-10">
            <textarea class="form-control" rows="5" id="content"></textarea>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-1" for="attachedFile">첨부파일:</label>
        <div class="col-sm-10">
            <input type="file" class="filestyle" data-buttonName="btn-default btn-sm"  data-buttonText="파일 찾기" id="attachedFile"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-1"></div>

        <div class="col-sm-10" >
            <div id="divFileArea">
                <div>
                    <span id="attachedFileLabel" class="modify_panel_title" ></span>
                    &lt;%&ndash;<a href="" id="attachedFileLink" download><label id="attachedFileLabel"></label></a>&ndash;%&gt;
                    <span id="attachedFileSize"></span>
                    <span class="glyphicon glyphicon-remove" aria-hidden="true" id="delFIleImg"></span>
                </div>

            </div>

        </div>

        <div class="col-sm-10">
            <div id="divPreviewMessage" class="divPreviewMessage"> </div>
        </div>

    </div>



    <div class="form-group">
        <label class="control-label col-sm-1" for="startDate">게시일 설정:</label>
        <div class="col-sm-4">

            <div class='input-group date' id='startDate' >
                <input type='text' maxlength='100' class="form-control" id='startDateInput' />
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>

        </div>
        <div class="col-sm-1" align="center">
            ~
        </div>
        <div class="col-sm-4">

            <div class='input-group date'  id='endDate'>
                <input type='text' maxlength='100' class="form-control"  id='endDateInput'/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>

        </div>
        <div class="col-sm-1">
            <input type="checkbox" id="checkMaxDate"/> max date
        </div>

    </div>

    <div class="form-group">
        <div class="col-sm-offset-1 col-sm-10 " align="right">
            <div class="divButtons">
                <CatalogController:set var="insertFlag" value="${INSERT_FLAG}" />
                <CatalogController:set var="checkCudU" value="<%= Constants.CUD_U %>" />
                <CatalogController:choose>
                    <CatalogController:when test="${insertFlag eq checkCudU}">
                        <button type="button" class="btn btn-danger fl" id="btnDelete">삭제</button>
                        <button type="button" class="btn btn-default" id="btnCancel">취소</button>
                        <button type="button" class="btn btn-success" id="btnRegist">저장</button>
                    </CatalogController:when>
                    <CatalogController:otherwise>
                        <button type="button" class="btn btn-default" id="btnCancel">취소</button>
                        <button type="button" class="btn btn-success" id="btnRegist">등록</button>
                    </CatalogController:otherwise>
                </CatalogController:choose>
            </div>
        </div>
    </div>


    &lt;%&ndash; Hidden values &ndash;%&gt;

    <input type="hidden" id="resInsertFlag" name="resInsertFlag" value="<CatalogController:out value='${INSERT_FLAG}' default='' />" />
    <input type="hidden" id="resCudU" name="resCudU" value="<CatalogController:out value='${CONSTANT_CUD}' default='' />" />

    <input type="hidden" id="oldFilePath" name="oldFilePath" value="" />
    <input type="hidden" id="filePath" name="filePath" value="" />
    <input type="hidden" id="fileName" name="fileName" value="" />
    <input type="hidden" id="fileSize" name="fileSize" value="" />

    <input type="hidden" id="no" name="no" value="<CatalogController:out value='${REQUEST_NO}' default='' />" />

    <input type="hidden" id="classificationValue" name="classificationValue" value="" />

</form>
--%>



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
    var TO_LIST = "/support/supportMain/<%= Constants.TAB_NAME_NOTICE %>";

    var GET_PROC_URL = "<c:url value='/support/getNotice' />";
    var INSERT_PROC_URL = "<c:url value='/support/insertNotice' />";
    var UPDATE_PROC_URL = "<c:url value='/support/updateNotice' />";
    var DELETE_PROC_URL = "<c:url value='/support/deleteNotice' />";

    var FILE_UPLOAD_PROC_URL = "<c:url value='/support/uploadFile'/>";
    var FILE_DELETE_PROC_URL = "<c:url value='/support/deleteFile'/>";
    var MAX_SIZE = 500; // MB

    var DELETE_FLAG = false;

    var CATEGORY_LIST_PROC_URL = "<c:url value='/commonCode/getCommonCode' />";
    var SUPPORT_NOTICE_ID = "<%= Constants.SUPPORT_NOTICE_ID %>";
    var INIT_MESSAGE = "첨부된 파일이 없습니다.";

    $(document).ready(function(){

        if (INSERT_FLAG != "" && INSERT_FLAG == CUD_U) { // get
            procGetNotice();
        } else {
            $('#classificationValue').val('');
            procCallAjax(CATEGORY_LIST_PROC_URL + "/" + SUPPORT_NOTICE_ID, null, procCallbackCategoryList);
        }

        /* 오늘 ~ 30일 뒤 */
        var now = new Date();
        var today = now.getFullYear() + "/" + (now.getMonth()+1) + "/" + now.getDate();
        now.setDate(now.getDate()+30);
        var next30day = now.getFullYear() + "/" + (now.getMonth()+1) + "/" + now.getDate();

        //    console.log("today : " + today);
        //    console.log("next30day : " + next30day);
        $("#startDateInput").val(today.toString());
        $("#endDateInput").val(next30day.toString());



        resetPreview();

        /*@@attachedFile@*/
        $("#hiddenFile").on("change", function(){
          //  console.log("call setPreView")
            setPreView($(this));
        });
    });


    function procGetNotice() {
        var no = ${REQUEST_NO};

        var param = {
            no : no
        }
        procCallAjax(GET_PROC_URL, param, procCallbackGetNotice);
    }

    var procCallbackGetNotice = function (data) {
        DELETE_FLAG = false;

        $("#title").val(data.info.title);
        $("#classificationValue").val(data.info.classificationValue);

        if (data.info.important == true || data.info.important == "true") $("#important").prop('checked', true);
        else $("#important").prop('checked', false);

        var useYnValue = data.info.useYn;
        if (useYnValue == "Y" || useYnValue == "y") $("input:radio[name='useYn']:radio[value='Y']").attr("checked", true);
        else if (useYnValue == "N" || useYnValue == "n") $("input:radio[name='useYn']:radio[value='N']").attr("checked", true);

        $("#content").val(data.info.content);
        $("#startDateInput").val(data.info.startDate);
        var endDateValue = data.info.endDate;
        $("#endDateInput").val(endDateValue);
        if (endDateValue == "9999/12/31") $("#checkMaxDate").prop('checked', true);


        $("#filePath").val(data.info.filePath);
        $("#fileName").val(data.info.fileName);
        $("#fileSize").val(data.info.fileSize);


        var filePath = data.info.filePath;
        var fileName = data.info.fileName;

        procSetFileLabel(fileName, filePath);
//
//        $('#attachedFile').attr('href', data.info.filePath);
//        $("#attachedFileLabel").text(data.info.fileName);
//        $("#attachedFileLabel").attr("style", "cursor:pointer");

        setAttachedFileSize(data.info.fileSize);

        $("#oldFilePath").val(data.info.filePath);

       // $("#attachedFileLink").attr("href", $("#filePath").val());


        /*@attachedFileLabel@*/
        if (filePath == "" || filePath == null) {
            $('#delFIleImg').attr('style', 'display: none;');
            $('#attachedFileLabel').text(INIT_MESSAGE);
        //    $('#divPreview').attr('style', 'display: none;');
        }
        else {
            $('#delFIleImg').attr('style', 'display: show;');
        //    $('#attachedFileLabel').text(INIT_MESSAGE);
        }

//        if (filePath == '' || filePath == null) {
//
//        }


        procCallAjax(CATEGORY_LIST_PROC_URL + "/" + SUPPORT_NOTICE_ID, null, procCallbackCategoryList);


    }

    // SET FILE LABEL
    var procSetFileLabel = function(reqFileName,reqFilePath) {
        if (null == reqFileName || '' == reqFileName) return false;

        /*@attachedFileLabel@*/
        $('#attachedFileLabel').val(reqFileName);
        $('#attachedFileLabel').text(reqFileName);

        if (reqFilePath != null || reqFilePath !='') {
            var linkHtml = '<a href="javascript:void(0);"  onclick="procDownload(\'' + reqFilePath + '\', \'' + reqFileName + '\')">' + reqFileName + '</a>';
            $('#attachedFileLabel').html(linkHtml);

        } else {
            $('#attachedFileLabel').text('첨부파일없음');
        }
    };

    // GET CATEGORY LIST CALLBACK
    var procCallbackCategoryList = function(data) {

        if (RESULT_STATUS_FAIL == data.RESULT) return false;

        var objSelectBox = $('#classification');
        var listLength = data.list.length;
        var htmlString = [];
        var classificationValue = $('#classificationValue').val();

        for (i = 0; i < listLength; i++) {
            if (classificationValue == data.list[i].value) {
                htmlString.push("<option value='" + data.list[i].key + "' selected>" + data.list[i].value + "</option>");
            }
            else {
                htmlString.push("<option value='" + data.list[i].key + "'>" + data.list[i].value + "</option>");

            }

        }

        objSelectBox.append(htmlString);


    };




    function procRegistNotice() {

        if (!procCheckValidStringSpace()) return false;
        if (!procCheckValid()) return false;

        var reqTitle = $("#title").val();
        var reqClassification = $("#classification").val();
        var reqImportant = $("#important").is(":checked");       // true, false
        var reqUseYn = $("input:radio[name='useYn']:checked").val();  // Y
        var reqContent = $("#content").val();
        var reqStartDate = $("#startDateInput").val();
        var reqEndDate = $("#endDateInput").val();
        var reqFileName = $("#attachedFileLabel").text();
        var reqFilePath = $("#filePath").val();

        var reqFileSize = $("#fileSize").text();

        if (reqFilePath == '' || reqFilePath == null) {
            reqFileName = '';
            reqFileSize = 0;
        }


        var param = {
            title : reqTitle,
            classification : reqClassification,
            important : reqImportant,
            useYn : reqUseYn,
            content : reqContent,
            startDate : reqStartDate,
            endDate : reqEndDate,
            fileName : reqFileName,
            filePath : reqFilePath,
            fileSize : reqFileSize
        };

        if (INSERT_FLAG != "" && INSERT_FLAG == CUD_U) { // update

            var no = ${REQUEST_NO};

            var param2 = {
                no : no
            }
            $.extend(param, param2);

            procCallAjax(UPDATE_PROC_URL, param, procCallbackUpdateNotice);


        } else { // insert

            procCallAjax(INSERT_PROC_URL, param, procCallbackInsertNotice);

        }

    }


    // UPDATE CALLBACK
    var procCallbackUpdateNotice = function(data) {

        if (RESULT_STATUS_SUCCESS == data.RESULT) {
             procAlert("success", '<spring:message code="common.info.result.success" />');
            procMovePage(TO_LIST);

        } else {
             procAlert("danger", data.RESULT_MESSAGE);
        }
    };

    // INSERT CALLBACK
    var procCallbackInsertNotice = function(data) {

        if (RESULT_STATUS_SUCCESS == data.RESULT) {
             procAlert("success", '<spring:message code="common.info.result.success" />');
            procMovePage(TO_LIST);

        } else {
            procAlert("danger", data.RESULT_MESSAGE);
        }
    };

    // DELETE CALLBACK
    var procCallbackDeleteNotice = function(data) {

        if (RESULT_STATUS_SUCCESS == data.RESULT) {
            procAlert("success", '<spring:message code="common.info.result.success" />');

            procMovePage(TO_LIST);



        } else {
             procAlert("danger", data.RESULT_MESSAGE);

        }
    };

    function deleteNotice() {

        var no = ${REQUEST_NO};

        var param = {
            no : no
        }

        procCallAjax(DELETE_PROC_URL, param, procCallbackDeleteNotice);

    }


    /************************************************************ FIle Upload *******/

    //attachedFile

    function checkFileUpload() { // 파일 업로드 결정

        /*@@attachedFile@*/
        var reqFile = $("#hiddenFile");

        console.log("test :" + reqFile[0].files[0]);

        if (reqFile[0].files[0] != undefined) { // new file! -> file upload

            // 기존파일이 있으면 삭제 후 파일 업, 없으면 바로 파일 업

            var fileName = $("#fileName").val();

            if (fileName ==  "" || fileName == null) { // 바로 파일 업
                  callFileUpload();
            }
            else {// 파일 삭제 후 파일 업
                deleteFile();
            }


        } else {
            /*@attachedFileLabel@*/

            var fileLabel = $("#attachedFileLabel").text();
            var fileName = $("#fileName").val();


            if (fileName == "" || fileName == '') {
                 procRegistNotice();
            } else {
                if (fileLabel == "" || fileLabel == '') {
                    deleteFile();
                } else {
                     procRegistNotice();
                }

            }

        }

    }

    function callFileUpload() {
        var formData = getFileFormData();
        if (formData != undefined) uploadFile(formData);
    }


    var deleteFile = function() {

        var oldFilePath = $("#oldFilePath").val();

        if (oldFilePath == null || oldFilePath == "") {

            if (DELETE_FLAG) {
                deleteNotice();
            }

        } else {

            var param = {
                filePath : oldFilePath
            };
            procCallAjax(FILE_DELETE_PROC_URL, param, procCallbackDeleteFile);

        }



    };

    var procCallbackDeleteFile = function(data) {

        if (RESULT_STATUS_SUCCESS == data.RESULT) {

            procAlert("success", '<spring:message code="common.info.result.success" />');

            resetPreview();

            // DELETE
            if (DELETE_FLAG) {

                deleteNotice();


            }
            // UPDATE
            else {// 삭제 후 새파일 업로드! // true check?

                /*@@attachedFile@*/
                var reqFile = $("#hiddenFile");

                if (reqFile[0].files[0] != undefined) {
                    callFileUpload();
                } else {
                    procRegistNotice();
                }

            }


        } else {
             procAlert("danger", data.RESULT_MESSAGE);
        }


    };


    // FILE FORM DATA
    var getFileFormData = function() {
        /*@@attachedFile@*/
        var reqFile = $("#hiddenFile");
        if (reqFile[0].files[0] === undefined) return undefined;

        var formData = new FormData();
        formData.append("file", reqFile[0].files[0]);
        setPreView(reqFile);

        return formData;
    };


    // UPLOAD FILE
    var uploadFile = function(formData) {

        $.ajax({
            url : FILE_UPLOAD_PROC_URL
            , method : "POST"
            , processData : false
            , contentType : false
            , data : formData
            , dataType : "json"
            , success : function(data){
                procCallbackUploadFile(data);
            },
            error: function(xhr, status, error) {
                procAlert("danger", JSON.parse(xhr.responseText).customMessage);

            },
            complete : function(data) {

            }
        });
    };

    // UPLOAD FILE CALLBACK
    var procCallbackUploadFile = function(data) {


        if (RESULT_STATUS_SUCCESS == data.RESULT) {

            /*@attachedFileLabel@*/

            $("#filePath").val(data.path);
            $("#fileName").val($("#attachedFileLabel").text());


            procRegistNotice();

        } else if (RESULT_STATUS_FAIL == data.RESULT) {

            procAlert("danger", data.RESULT_MESSAGE);
        }
    };

    // PREVIEW
    var setPreView = function(objFile) {
        var this_form = objFile[0];
        var file    = this_form.files[0];
        var fileSize = file.size;

        if (file) {

            var pathHeader = this_form.value.lastIndexOf("\\");
            var pathMiddle = this_form.value.lastIndexOf(".");
            var pathEnd = this_form.value.length;
            var filename = this_form.value.substring(pathHeader + 1, pathEnd);
            var ext = this_form.value.substring(pathMiddle + 1, pathEnd);

            $("#attachedFileLabel").text(filename);
            $('#delFIleImg').attr('style', 'display: show;');

            setAttachedFileSize(file.size);

        } else {

        }
    };

    function setAttachedFileSize(fs) {
        console.log("file size setting :" + fs);



        $("#fileSize").text(fs);

        if (fs > 0) {
            var kb = 1024;
            var mb = 1048576;
            var byteUnit;
            var printUnit;

            if (fs > mb) {
                byteUnit = mb;
                printUnit = "mb";
            }
            else if (fs > kb) {
                byteUnit = kb;
                printUnit = "kb";
            }

            fileSize = Math.round(fs/byteUnit);
            $("#attachedFileSize").empty();
            $("#attachedFileSize").append("(" + fileSize + printUnit + ")");

        }


    }

    // RESET FILE
    var resetPreview = function() {
        /*@@attachedFile@*/
        /*@attachedFileLabel@*/
        //$('#attachedFile').filestyle('clear');
        $("#attachedFileLabel").text('');
        $("#attachedFileSize").text('');

  //      $("#fileSize").val(0);
        $("#fileSize").text(0);
        $("#filePath").val('');


        $('#divPreview').attr('style', 'display: none;');
        $('#divPreviewMessage').attr('style', 'display: none;');
        $('#delFIleImg').attr('style', 'display: none;');

        $('#attachedFileLabel').text(INIT_MESSAGE);
   //     $('#delFIleImg').attr('style', 'display: none;');

//        var filePath = $('#filePath').val();
//        if (filePath == '' || filePath == null) {
//            $('#attachedFileLabel').text(INIT_MESSAGE);
//            $('#delFIleImg').attr('style', 'display: none;');
//        }


    };





    /********************************************************************************/
    // calendar, tooltip
    $(function () {
        // Calendar setting
        $('#startDateInput').datetimepicker({
            format: 'YYYY/MM/DD'

        });
        $('#endDateInput').datetimepicker({
            format: 'YYYY/MM/DD',
            useCurrent: false //Important! See issue #1075

        });
        $("#startDateInput").on("dp.change", function (e) {
            $('#endDateInput').data("DateTimePicker").minDate(e.date);
        });
        $("#endDateInput").on("dp.change", function (e) {
            $('#startDateInput').data("DateTimePicker").maxDate(e.date);
        });

        // Check box event  - max date
        $('#noticeForm').on('change', ':checkbox', function () {

            if ($("#checkMaxDate").is(':checked')) {
                $("#endDateInput").val("9999/12/31");

            } else  {
                $("#endDateInput").val($("#startDateInput").val());
            }
        });

        // Check box event  - important notice
        $('[data-toggle="tooltip"]').tooltip();


        $("#delFIleImg").on("click", function() {

            resetPreview();


       //     $('#delFIleImg').attr('style', 'display: none;');
        });



    });

    // CHECK VALID
    var procCheckValid = function() {
        var reqContent = $('#content');
        if (reqContent.val() == '') {
            procAlert("warning", INFO_EMPTY_REQUEST_DATA);
            reqContent.focus();
            return false;
        }


        return true;
    };

    /*************************************************************************** Buttons *****/
    // BIND :: BUTTON EVENT
    $("#btnResetImg").on("click", function() {
        procResetImage();
    });

    // BIND :: BUTTON EVENT
    $("#btnRegist").on("click", function() {
        checkFileUpload();
    });

    // BIND :: BUTTON EVENT
    $("#btnCancel").on("click", function() {
        procMovePage(TO_LIST);
    });

    // BIND :: BUTTON EVENT
    $("#btnDelete").on("click", function() {
        procPopup('공지', DELETE_MESSAGE, 'setDeleteFlag();');
    });

    function setDeleteFlag() {
        DELETE_FLAG = true;
        deleteFile();
    }

</script>
<%--
====================================================================================================
SCRIPT END
====================================================================================================
--%>
