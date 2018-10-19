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

    <h4 class="modify_h4 fwn">문의 상세</h4>

</div>


<div class="col-md-11 col-md-offset-14">
<form class="form-horizontal" role="form" id="qnaForm" name="qnaForm">

    <div class="form-group">
        <label class="control-label col-sm-2" for="title">제목:</label>
        <div class="col-sm-9">
            <input type="text" maxlength="100" class="form-control" id="title" disabled="disabled">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="classification">분류:</label>
        <div class="col-sm-9">
            <select class="form-control" id="classification" disabled="disabled">

            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="userId">작성자:</label>
        <div class="col-sm-4" style="width: 29%">
            <input type="text" maxlength="100" class="form-control" id="userId" disabled="disabled">
        </div>

        <label class="control-label col-sm-2" for="created">게시일:</label>
        <div class="col-sm-4" style="width: 29%">
            <input type="text" maxlength="100" class="form-control" id="created" disabled="disabled">
        </div>

    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="content">내용:</label>
        <div class="col-sm-9">
            <textarea class="form-control" rows="5" id="content" disabled="disabled"></textarea>
        </div>
    </div>

    <%-- file area for Queation --%>
    <div class="form-group">
        <div class="col-sm-2"></div>

        <div class="col-sm-9">
            <div id="divFileArea-question">
                <div>
                    <span id="attachedFileLabel-question" class="modify_panel_title" ></span>
                    <%--<a href="" id="attachedFileLink-question" download><label id="attachedFileLabel-question"></label></a>--%>
                </div>
            </div>

        </div>

    </div>


    <%-------------------------- ANSWER ------------------------%>

    <hr>

    <div class="form-group">
        <c:set var="REQUEST_STATUS" value="${REQUEST_STATUS}" />
        <c:set var="checkStatus" value="<%= Constants.STATUS_ANSWERED %>" />
        <c:choose>
            <c:when test="${REQUEST_STATUS eq checkStatus}">
                <label class="control-label col-sm-2" for="answerer">답변자:</label>
                <div class="col-sm-4" style="width: 37.5%">
                    <input type="text" maxlength="100" class="form-control" id="answerer" disabled="disabled">
                </div>

                <label class="control-label col-sm-2" for="answer-created"> 답변 등록일:</label>
                <div class="col-sm-4" style="width: 37.5%">
                    <input type="text" maxlength="100" class="form-control" id="answer-created" disabled="disabled">
                </div>
                </c:when>
            </c:choose>

    </div>


    <div class="form-group">
        <label class="control-label col-sm-2" for="answer">답변:</label>
        <div class="col-sm-9">
            <textarea class="form-control" rows="5" id="answer"></textarea>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="attachedFile">파일첨부:</label>
        <div class="col-sm-9" style="margin-top: 0px">
            <input type="file" class="filestyle" data-buttonName="btn-default btn-sm"  data-buttonText="파일 찾기" id="attachedFile" >
        </div>
    </div>

    <%-- file area for Answer --%>
    <div class="form-group">
        <div class="col-sm-1"></div>

        <div class="col-sm-10" >
            <div id="divFileArea">
                <div>
                    <span id="attachedFileLabel" class="modify_panel_title" ></span>
                    <%--<a href="" id="attachedFileLink" download><label id="attachedFileLabel"></label></a>--%>
                    <span id="attachedFileSize"></span>
                    <span class="glyphicon glyphicon-remove" aria-hidden="true" id="delFIleImg"></span>
                </div>

            </div>

        </div>

        <div class="col-sm-9">
            <div id="divPreviewMessage" class="divPreviewMessage"> </div>
        </div>

    </div>



    <div class="form-group">
        <div class="col-sm-offset-1 col-sm-10 " align="right">
            <div class="divButtons">

                <c:set var="REQUEST_STATUS" value="${REQUEST_STATUS}" />
                <c:set var="checkStatus" value="<%= Constants.STATUS_ANSWERED %>" />

                <c:choose>
                    <c:when test="${REQUEST_STATUS eq checkStatus}">
                        <button type="button" class="btn btn-danger fl" id="btnDelete">삭제</button>
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

    <%--<input type="hidden" id="resInsertFlag" name="resInsertFlag" value="<CatalogController:out value='${INSERT_FLAG}' default='' />" />--%>
    <input type="hidden" id="resCudU" name="resCudU" value="<c:out value='${CONSTANT_CUD}' default='' />" />

    <input type="hidden" id="oldFilePath" name="oldFilePath" value="" />
    <input type="hidden" id="filePath" name="filePath" value="" />
    <input type="hidden" id="fileName" name="fileName" value="" />
    <input type="hidden" id="fileSize" name="fileSize" value="" />

    <input type="hidden" id="no" name="no" value="<c:out value='${REQUEST_NO}' default='' />" />
    <input type="hidden" id="status" name="status" value="<c:out value='${REQUEST_STATUS}' default='' />" />

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

    var REQUEST_STATUS = "<c:out value='${REQUEST_STATUS}' default='' />";
    var STATUS_WAITING = "<%= Constants.STATUS_WAITING %>";
    var STATUS_ANSWERED = "<%= Constants.STATUS_ANSWERED %>";

    var TO_LIST = "/support/supportMain/<%= Constants.TAB_NAME_QNA %>";

    var GET_QUESTION_PROC_URL = "<c:url value='/support/getQuestion' />";
    var GET_ANSWER_PROC_URL = "<c:url value='/support/getAnswer' />";

    var INSERT_PROC_URL = "<c:url value='/support/insertAnswer' />";
    var UPDATE_PROC_URL = "<c:url value='/support/updateAnswer' />";
    var DELETE_PROC_URL = "<c:url value='/support/deleteAnswer' />";

    var FILE_UPLOAD_PROC_URL = "<c:url value='/support/uploadFile'/>";
    var FILE_DELETE_PROC_URL = "<c:url value='/support/deleteFile'/>";
    var MAX_SIZE = 500; // MB

    var DELETE_FLAG = false;

    var CATEGORY_LIST_PROC_URL = "<c:url value='/commonCode/getCommonCode' />";
    var SUPPORT_QNA_ID = "<%= Constants.SUPPORT_QNA_ID %>";


    $(document).ready(function() {

        procGetQuestion();

        if (REQUEST_STATUS != "" && REQUEST_STATUS == STATUS_ANSWERED) { // get answer
            procGetAnswer(${REQUEST_NO});
        }

        resetPreview();

        $("#attachedFile").on("change", function(){
            setPreView($(this));
        });

    });


    function procGetQuestion() {
        var no = ${REQUEST_NO};
    //    var no = $("#no");

        var param = {
            no : no
        }

        procCallAjax(GET_QUESTION_PROC_URL, param, procCallbackGetQuestion);
    }

    var procCallbackGetQuestion = function (data) {

        $("#title").val(data.info.title);
        $("#classificationValue").val(data.info.classificationValue);
   //     $("#classification").val(data.info.classification).attr("selected", "selected");

        $("#userId").val(data.info.userId);

        $("#content").val(data.info.content);
        $("#created").val(data.info.created);

        var fileName = data.info.fileName;
        var filePath = data.info.filePath;
/*
        if (fileName == null || fileName == '') {
            $("#attachedFileLabel-question").text("첨부 파일 없음");
        }
        else {
            $("#attachedFileLabel-question").text(fileName);
            $("#attachedFileLabel-question").attr("style", "cursor:pointer");
        }

        $("#attachedFileLink-question").attr("href", data.info.filePath);
*/


        procSetFileLabel(fileName, filePath, $('#attachedFileLabel-question') );

        procCallAjax(CATEGORY_LIST_PROC_URL + "/" + SUPPORT_QNA_ID, null, procCallbackCategoryList);

    }

    // SET FILE LABEL
    var procSetFileLabel = function(reqFileName,reqFilePath, target) {
      //  console.log("procSetFileLabel:::");
        if (null == reqFileName || '' == reqFileName) return false;

        target.val(reqFileName);
        target.text(reqFileName);

        if (reqFilePath != null || reqFilePath !='') {
            var linkHtml = '<a href="javascript:void(0);"  onclick="procDownload(\'' + reqFilePath + '\', \'' + reqFileName + '\')">' + reqFileName + '</a>';

            target.html(linkHtml);

        } else {
            target.text('첨부파일없음');
        }

        //console.log('target ::: ' + target.html());
    };


    // GET CATEGORY LIST CALLBACK
    var procCallbackCategoryList = function(data) {

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


    function procGetAnswer(qno) {
        var DELETE_FLAG = false;


        var param = {
            questionNo : qno
        }

        procCallAjax(GET_ANSWER_PROC_URL, param, procCallbackGetAnswer);

    }

    var procCallbackGetAnswer = function (data) {

        $("#answerer").val(data.info.answerer);
        $("#answer-created").val(data.info.created);
        $("#answer").val(data.info.content);

        $("#filePath").val(data.info.filePath);
        $("#fileName").val(data.info.fileName);
        $("#fileSize").val(data.info.fileSize);

        setAttachedFileSize(data.info.fileSize);

//        $('#attachedFile').attr('href', data.info.filePath);
        $("#attachedFileLabel").text(data.info.fileName);
//        $("#oldFilePath").val(data.info.filePath);
//
//        $("#attachedFileLink").attr("href", data.info.filePath);

        var fileName = data.info.fileName;
        var filePath = data.info.filePath;
        procSetFileLabel(fileName, filePath, $('#attachedFileLabel') );

        if ($('#attachedFileLabel').text() == "" || $('#attachedFileLabel').text() == null)
            $('#delFIleImg').attr('style', 'display: none;');
        else
            $('#delFIleImg').attr('style', 'display: show;');




    }

    function setAttachedFileSize(fs) {

        $("#fileSize").text(fs);

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

    function procRegistAnswer() {

        var no = ${REQUEST_NO};

        var reqContent = $("#answer").val();
        var reqAnswerer = $("#answerer").val();

        var reqFileName = $("#attachedFileLabel").text();
        var reqFilePath = $("#filePath").val();
        var reqFileSize = $("#fileSize").text();

        var param = {
            content : reqContent,
            fileName : reqFileName,
            filePath : reqFilePath,
            fileSize : reqFileSize,
            answerer : reqAnswerer,
            status : STATUS_ANSWERED,
            questionNo : no
        };

        if (REQUEST_STATUS != "" && REQUEST_STATUS == STATUS_ANSWERED) { // update
            procCallAjax(UPDATE_PROC_URL, param, procCallbackUpdateAnswer);

        } else { // insert
            procCallAjax(INSERT_PROC_URL, param, procCallbackInsertAnswer);

        }

    }


    // UPDATE CALLBACK
    var procCallbackUpdateAnswer = function(data) {

        if (RESULT_STATUS_SUCCESS == data.RESULT) {
            procAlert("success", '<spring:message code="common.info.result.success" />');
            procMovePage(TO_LIST);

        } else {
            procAlert("danger", data.RESULT_MESSAGE);
        }
    };

    // INSERT CALLBACK
    var procCallbackInsertAnswer = function(data) {

        if (RESULT_STATUS_SUCCESS == data.RESULT) {
            procAlert("success", '<spring:message code="common.info.result.success" />');
            procMovePage(TO_LIST);

        } else {
            procAlert("danger", data.RESULT_MESSAGE);
        }
    };


    function deleteAnswer() {

        var no = ${REQUEST_NO};

        var param = {
            questionNo : no,
            status : STATUS_WAITING
        }

        procCallAjax(DELETE_PROC_URL, param, procCallbackDeleteAnswer);

    }


    // DELETE CALLBACK
    var procCallbackDeleteAnswer = function(data) {

        if (RESULT_STATUS_SUCCESS == data.RESULT) {
            procAlert("success", '<spring:message code="common.info.result.success" />');

            procMovePage(TO_LIST);

        } else {
            procAlert("danger", data.RESULT_MESSAGE);

        }
    };


    /************************************************************ FIle Upload *******/

    //attachedFile

    function checkFileUpload() { // 파일 업로드 결정
        var reqFile = $("#attachedFile");

        if (reqFile[0].files[0] != undefined) { // new file! -> file upload

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
                procRegistAnswer();
            } else {

                if (fileLabel == "" || fileLabel == '') {
                    deleteFile();
                } else {
                    procRegistAnswer();
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

            if (DELETE_FLAG) { // 없어도 될걸?
                deleteAnswer();
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
            $("#fileName").val('');
            $("#filePath").val('');
            $("#fileSize").val('');
            $("#fileSize").text('');
            // DELETE
            if (DELETE_FLAG) {
                deleteAnswer();
            }
            // UPDATE
            else {// 삭제 후 새파일 업로드! // true check?
                var reqFile = $("#attachedFile");

                if (reqFile[0].files[0] != undefined) {
                    callFileUpload();
                } else {
                    procRegistAnswer();
                }

            }


        } else {
            procAlert("danger", data.RESULT_MESSAGE);
        }


    };


    // FILE FORM DATA
    var getFileFormData = function() {
        var reqFile = $("#attachedFile");
        if (reqFile[0].files[0] === undefined) return undefined;

        var formData = new FormData();
        formData.append("file", reqFile[0].files[0]);

        return formData;
    };


    // UPLOAD FILE
    var uploadFile = function(formData) {
     //   console.log("CHECK:: 04 (07) :: uploadFile ");

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

            $("#filePath").val(data.path);
            $("#fileName").val($("#attachedFileLabel").text());

            procRegistAnswer();

        } else if (RESULT_STATUS_FAIL == data.RESULT) {
            procAlert("danger", data.RESULT_MESSAGE);
        }
    };

    // PREVIEW
    var setPreView = function(objFile) {
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

            fileSize = Math.round(fs/byteUnit);
            $("#attachedFileSize").empty();
            $("#attachedFileSize").append("(" + fileSize + printUnit + ")");

        }


    }

    // RESET FILE
    var resetPreview = function() {
        $('#attachedFile').filestyle('clear');
        $("#attachedFileLabel").text('');
        $("#attachedFileSize").text('');

        $("#fileSize").val(0);


        $('#divPreview').attr('style', 'display: none;');
        $('#divPreviewMessage').attr('style', 'display: none;');
        $('#delFIleImg').attr('style', 'display: none;');


    };

    /********************************************************************************/
    $(function () {

        $("#delFIleImg").on("click", function() {

            resetPreview();

            $('#delFIleImg').attr('style', 'display: none;');
        });


    });
    /*************************************************************************** Buttons *****/

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
        procPopup('답변', DELETE_MESSAGE, 'setDeleteFlag();');

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
