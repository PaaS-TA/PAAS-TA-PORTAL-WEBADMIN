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



<div id="statusTitle" class="col-sm-6 pt30">
    <c:set var="insertFlag" value="${INSERT_FLAG}" />
    <c:set var="checkCudU" value="<%= Constants.CUD_U %>" />
    <c:choose>
        <c:when test="${insertFlag eq checkCudU}">
            <h4 class="modify_h4 fwn">문서 상세</h4>
        </c:when>
        <c:otherwise>
            <h4 class="modify_h4 fwn">문서 등록</h4>
        </c:otherwise>
    </c:choose>
</div>


<!--인스턴스 설정-->
<div class="col-md-11 col-md-offset-14">
    <form class="form-horizontal" role="form" id="noticeForm" name="documentsForm">
        <%--<input type="hidden" id="thumb_image_path" value="${thumb_image_path}">--%>

        <div class="form-group">
            <label class="control-label col-sm-2" for="title">제목</label>
            <div class="col-sm-9">
                <input type="text" maxlength="100" class="form-control toCheckString" id="title">
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
                        <span id="attachedFileSize"></span>
                    </div>
                    <div class="divImageInfo fl ml20">
                        <button id="delFIleImg" type="button" class="btn-del mt-30 ml3" aria-label="첨부파일 지우기">
                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                        </button>
                    </div>
                </div>
            </div>

        </div>



        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-9" align="right">
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

        <input type="hidden" id="no" name="no" value="<c:out value='${REQUEST_NO}' default='' />" />
        <input type="hidden" id="classificationValue" name="classificationValue" value="" />


    </form>
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

    var INSERT_FLAG = "<c:out value='${INSERT_FLAG}' default='' />";
    var TO_LIST = "/documents/documentsMain";

    var GET_PROC_URL = "<c:url value='/documents/getDocument' />";
    var INSERT_PROC_URL = "<c:url value='/documents/insertDocument' />";
    var UPDATE_PROC_URL = "<c:url value='/documents/updateDocument' />";
    var DELETE_PROC_URL = "<c:url value='/documents/deleteDocument' />";

    var FILE_UPLOAD_PROC_URL = "<c:url value='/support/uploadFile'/>";
    var FILE_DELETE_PROC_URL = "<c:url value='/support/deleteFile'/>";
    var MAX_SIZE = 500; // MB

    var DELETE_FLAG = false;

    var CATEGORY_LIST_PROC_URL = "<c:url value='/commonCode/getCommonCode' />";
    var DOCUMENTS_CLASSIFICATION = "<%= Constants.DOCUMENTS_CLASSIFICATION %>";



    $(document).ready(function(){

        if (INSERT_FLAG != "" && INSERT_FLAG == CUD_U) { // get
            procGetDocument();
        } else {
            $('#classificationValue').val('');
            procCallAjax(CATEGORY_LIST_PROC_URL + "/" + DOCUMENTS_CLASSIFICATION, null, procCallbackCategoryList);
        }

        resetPreview();

        $("#hiddenFile").on("change", function(){
            setPreView($(this));
        });

    });

    function procGetDocument() {
        var no = ${REQUEST_NO};

        var param = {
            no : no
        }
        procCallAjax(GET_PROC_URL, param, procCallbackGetDocument);
    }

    var procCallbackGetDocument = function (data) {
        DELETE_FLAG = false;

        $("#title").val(data.info.title);

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

        $('#hiddenFile').attr('href', data.info.filePath);
        $("#attachedFileLabel").text(data.info.fileName);
        $("#attachedFileLabel").attr("style", "cursor:pointer");


        var fname = $("#fileName").val();
        var fpath = $("#filePath").val();

        procSetFileLabel(fname, fpath);
        setAttachedFileSize(data.info.fileSize);


        $("#oldFilePath").val(data.info.filePath);

        $("#attachedFileLink").attr("href", $("#filePath").val());


        if ($('#attachedFileLabel').text() == "" || $('#attachedFileLabel').text() == null)
            $('#delFIleImg').attr('style', 'display: none;');
        else
            $('#delFIleImg').attr('style', 'display: show;');


        $("#classificationValue").val(data.info.classificationValue);
        procCallAjax(CATEGORY_LIST_PROC_URL + "/" + DOCUMENTS_CLASSIFICATION, null, procCallbackCategoryList);
    }

    // SET FILE LABEL
    var procSetFileLabel = function(reqFileName,reqFilePath) {
        if (null == reqFileName || '' == reqFileName) return false;

        $('#attachedFileLabel').val(reqFileName);
        $('#attachedFileLabel').text(reqFileName);

        if (reqFilePath != null || reqFilePath !='') {
            var linkHtml = '<a href="javascript:void(0);"  onclick="procDownload(\'' + reqFilePath + '\', \'' + reqFileName + '\')">' + reqFileName + '</a>';

            $('#attachedFileLabel').html(linkHtml);

        } else {
            $('#attachedFileLabel').text('첨부파일없음');
        }
    };

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


    function procRegistDocument() {
        if (!procCheckValidStringSpace()) return false;
        if (!procCheckValid()) return false;

        //console.log("CHECK:: 07 (03) :: procRegistDocument");

        var reqTitle = $("#title").val();
        var reqClassification = $("#classification").val();
        var reqUseYn = $("input:radio[name='useYn']:checked").val();  // Y
        var reqContent = $("#content").val();
        var reqFileName = $("#attachedFileLabel").text();
        var reqFileSize = $("#fileSize").text();
        var reqFilePath = $("#filePath").val();

        var param = {
            title : reqTitle,
            classification : reqClassification,
            useYn : reqUseYn,
            content : reqContent,
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

            procCallAjax(UPDATE_PROC_URL, param, procCallbackUpdateDocument);


        } else { // insert

            procCallAjax(INSERT_PROC_URL, param, procCallbackInsertDocument);
        }
    }


    // UPDATE CALLBACK
    var procCallbackUpdateDocument = function(data) {

        if (RESULT_STATUS_SUCCESS == data.RESULT) {
            procAlert("success", '<spring:message code="common.info.result.success" />');
            procMovePage(TO_LIST);

        } else {
            procAlert("danger", data.RESULT_MESSAGE);
        }
    };

    // INSERT CALLBACK
    var procCallbackInsertDocument = function(data) {

        if (RESULT_STATUS_SUCCESS == data.RESULT) {
            procAlert("success", '<spring:message code="common.info.result.success" />');
            procMovePage(TO_LIST);

        } else {
            procAlert("danger", data.RESULT_MESSAGE);
        }
    };

    // DELETE CALLBACK
    var procCallbackDeleteDocument = function(data) {

        if (RESULT_STATUS_SUCCESS == data.RESULT) {
            procAlert("success", '<spring:message code="common.info.result.success" />');
            procMovePage(TO_LIST);

        } else {
            procAlert("danger", data.RESULT_MESSAGE);
        }
    };

    function deleteDocument() {

        var no = ${REQUEST_NO};

        var param = {
            no : no
        }

        procCallAjax(DELETE_PROC_URL, param, procCallbackDeleteDocument);

    }


    /************************************************************ FIle Upload *******/

    function checkFileUpload() { // 파일 업로드 결정

        var reqFile = $("#hiddenFile");

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

            var fileLabel = $("#attachedFileLabel").text();
            var fileName = $("#fileName").val();


            if (fileName == "" || fileName == '') {
                procRegistDocument();
            } else {

                if (fileLabel == "" || fileLabel == '') {
                    deleteFile();
                } else {
                    procRegistDocument();
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
            // 기존 file path가 null => 파일 삭제 x
            if (DELETE_FLAG) {
                deleteDocument();
            }

        } else {
            // 기존 file path가 not null => 파일 삭제
            var param = {
                filePath : oldFilePath
            };
            procCallAjax(FILE_DELETE_PROC_URL, param, procCallbackDeleteFile);
        }

    };

    var procCallbackDeleteFile = function(data) {

        if (RESULT_STATUS_SUCCESS == data.RESULT) {
            procAlert("success", '<spring:message code="common.info.result.success" />');
            $("#fileName").val('');
            $("#filePath").val('');
            $("#fileSize").val('');
            $("#fileSize").text('');

            // DELETE
            if (DELETE_FLAG) {
                // DELETE FLAG TRUE => DELETE
                deleteDocument();
            }
            // UPDATE
            else {// 삭제 후 새파일 업로드! // true check?
                //DELETE FLAG FALSE => GO UPDATE
                var reqFile = $("#hiddenFile");
                setPreView(reqFile);

                if (reqFile[0].files[0] != undefined) {
                    callFileUpload();
                } else {
                    procRegistDocument();
                }
            }

        } else {
            procAlert("danger", data.RESULT_MESSAGE);
        }
    };


    // FILE FORM DATA
    var getFileFormData = function() {
        var reqFile = $("#hiddenFile");
        if (reqFile[0].files[0] === undefined) return undefined;

        var formData = new FormData();
        formData.append("file", reqFile[0].files[0]);

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

                //console.log("ERROR :: data :: ", error);
            },
            complete : function(data) {
                //console.log("COMPLETE :: data :: ", data);
            }
        });
    };

    // UPLOAD FILE CALLBACK
    var procCallbackUploadFile = function(data) {

        if (RESULT_STATUS_SUCCESS == data.RESULT) {

            $("#filePath").val(data.path);
            $("#fileName").val($("#attachedFileLabel").text());

            procRegistDocument();

        } else if (RESULT_STATUS_FAIL == data.RESULT) {
            procAlert("danger", data.RESULT_MESSAGE);
        }
    };

    // PREVIEW
    var setPreView = function(objFile) {
        console.log("setPreView::::" + objFile);

        var this_form = objFile[0];
        var file    = this_form.files[0];
        var fileSize = 0;

        if (file) {

            var pathHeader = this_form.value.lastIndexOf("\\");
            var pathMiddle = this_form.value.lastIndexOf(".");
            var pathEnd = this_form.value.length;
            var filename = this_form.value.substring(pathHeader + 1, pathEnd);
            var ext = this_form.value.substring(pathMiddle + 1, pathEnd);

            $("#attachedFileLabel").text(filename);
            $('#delFIleImg').attr('style', 'display: show;');

            $("#fileSize").text(file.size);
            setAttachedFileSize(file.size);

        } else {

        }
    };

    function setAttachedFileSize(fs) {

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

            fileSize = Math.round(fs / byteUnit);
            $("#attachedFileSize").empty();
            $("#attachedFileSize").append("(" + fileSize + printUnit + ")");
        }
    }

    // RESET FILE
    var resetPreview = function() {
     //   $('#attachedFile').filestyle('clear');
        $("#attachedFileLabel").text('');
        $("#attachedFileSize").text('');

        $("#fileSize").val(0);

        $('#divPreview').attr('style', 'display: none;');
        $('#divPreviewMessage').attr('style', 'display: none;');
        $('#delFIleImg').attr('style', 'display: none;');

    };

    // CHECK VALID
    var procCheckValid = function() {
        var reqContent = $('#content');
        if (reqContent.val() == '') {
            procAlert("warning", INFO_EMPTY_REQUEST_DATA);
            reqContent.focus();
            return false;
        }

        var reqFile = $("#attachedFileLabel").text();
        if (reqFile == '') {
            procAlert("warning", "첨부된 파일이 없습니다.");
            reqFile.focus();
            return false;
        }

        return true;
    };

    /********************************************************************************/
    // calendar, tooltip
    $(function () {

        $("#delFIleImg").on("click", function() {
            resetPreview();

            $('#delFIleImg').attr('style', 'display: none;');
        });



    });


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
        procPopup('문서', DELETE_MESSAGE, 'setDeleteFlag();');
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
