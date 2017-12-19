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

    <h4 class="modify_h4 fwn">게시판 상세</h4>

</div>

<div class="col-md-11 col-md-offset-14">
<form class="form-horizontal" role="form" id="boardForm" name="boardForm">

    <div class="form-group">
        <label class="control-label col-sm-2" for="title">제목:</label>
        <div class="col-sm-9">
            <input type="text" maxlength="100" class="form-control" id="title" disabled="disabled">
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
            <textarea class="form-control" rows="5" id="content"></textarea>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-2"></div>

        <div class="col-sm-9">
            <div id="divFileArea-board">
                <div>
                    <span id="attachedFileLabel-board" class="modify_panel_title" ></span>
                    <%--<a href="" id="attachedFileLink-board" download><label id="attachedFileLabel-board"></label></a>--%>
                </div>
            </div>

        </div>

    </div>


    <hr>
    <div class="form-group">
        <label class="control-label col-sm-2" for="boardCommentList">댓글</label>
        <div class="col-sm-9">

            <table class="table table-striped table-hover t1">
                <tbody id="boardCommentList">

                </tbody>
            </table>

        </div>
    </div>




    <div class="form-group">
        <div class="col-sm-offset-1 col-sm-10 " align="right">
            <div class="divButtons">

                <button type="button" class="btn btn_del2 fl ml-22" id="btnDelete">삭제</button>
                <button type="button" class="btn btn-default btn-sm" id="btnCancel">목록</button>

            </div>
        </div>
    </div>


    <input type="hidden" id="oldFilePath" name="oldFilePath" value="" />
    <input type="hidden" id="filePath" name="filePath" value="" />
    <input type="hidden" id="fileName" name="fileName" value="" />
    <input type="hidden" id="fileSize" name="fileSize" value="" />

    <input type="hidden" id="no" name="no" value="<c:out value='${REQUEST_NO}' default='' />" />

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

    var TO_LIST = "/support/supportMain/<%= Constants.TAB_NAME_BOARD %>";

    var GET_BOARD_PROC_URL = "<c:url value='/support/getBoard' />";
    var GET_BOARD_COMMENT_LIST_PROC_URL = "<c:url value='/support/getBoardCommentList' />";

    var DELETE_BOARD_PROC_URL = "<c:url value='/support/deleteBoard' />";
    var DELETE_BOARD_COMMENT_PROC_URL = "<c:url value='/support/deleteBoardCommentList' />";
    var UPDATE_BOARD_COMMENT_PROC_URL = "<c:url value='/support/updateBoardCommentList' />";

    var FILE_DELETE_PROC_URL = "<c:url value='/support/deleteFile'/>";

    var DELETE_FLAG = false;


    $(document).ready(function() {

        procGetBoard();
        procGetBoardComment();

     //   resetPreview();

        $("#attachedFile").on("change", function(){
            setPreView($(this));
        });

    });


    function procGetBoard() {
        var no = ${REQUEST_NO};

        var param = {
            no : no
        }

        procCallAjax(GET_BOARD_PROC_URL, param, procCallbackGetBoard);
    }

    var procCallbackGetBoard = function (data) {

        $("#title").val(data.info.title);
        $("#userId").val(data.info.userId);

        $("#content").val(data.info.content);
        $("#created").val(extractDate(data.info.created));

        var fileName = data.info.fileName;
        var filePath = data.info.filePath;

        if (fileName == null || fileName == '') {
            $("#attachedFileLabel-board").text("첨부 파일 없음");
        }
        else {
            procSetFileLabel(fileName, filePath);

        //    $("#attachedFileLabel-board").text(fileName);
        //    $("#attachedFileLabel-board").attr("style", "cursor:pointer");
        }

        $("#fileSize").val(data.info.fileSize);

        //style='cursor: pointer;'
  //      $("#attachedFileLink-board").attr("href", data.info.filePath);

        $("#oldFilePath").val(data.info.filePath);

    }

    // SET FILE LABEL
    var procSetFileLabel = function(reqFileName,reqFilePath) {
        if (null == reqFileName || '' == reqFileName) return false;

        $('#attachedFileLabel-board').val(reqFileName);
        $('#attachedFileLabel-board').text(reqFileName);

        if (reqFilePath != null || reqFilePath !='') {
            var linkHtml = '<a href="javascript:void(0);"  onclick="procDownload(\'' + reqFilePath + '\', \'' + reqFileName + '\')">' + reqFileName + '</a>';

            $('#attachedFileLabel-board').html(linkHtml);

        } else {
            $('#attachedFileLabel-board').text('첨부파일없음');
        }
    };

    function procGetBoardComment() {
        var boardNo = ${REQUEST_NO};

     //   console.log("boardNo : " + boardNo);

        var param = {
            boardNo : boardNo
        }

        procCallAjax(GET_BOARD_COMMENT_LIST_PROC_URL, param, procCallbackGetBoardComment);
    }

    var procCallbackGetBoardComment = function (data) {
        console.log("procCallbackGetBoardComment : " + data.list)

        var objTable = $("#boardCommentList");
        var htmlString = [];
        var getList = data.list;

        objTable.empty();
        objTable.html('');

        for (var i = 0; i < getList.length; i++) {

            if (getList[i] != undefined) {

                var thisBlank_info="";
                var thisBlank_content="";
                var textIndent=1;

                if (getList[i].level > 0) { // level == depth, 0: 원글, 1~: 답글
                    textIndent = getList[i].level * 30;
//                    for (var j = 0; j < getList[i].level; j++) {
//                        thisBlank_info = thisBlank_info + "　";
//                    }
                    thisBlank_content = thisBlank_info + "　";
                    thisBlank_info = thisBlank_info + "└ ";

                }

                htmlString.push(
                        "<tr>"
                        + "<td style='padding-left: " + textIndent + "px;'>"
                             //   + "<p style='padding-left: '" + textIndent + "px'>"
                                + thisBlank_info + "<span style='color:gray'>" + getList[i].userId + "　　　　" + extractDate(getList[i].created) + "</span>"  // + "<div id='delComment'> × </div>"    //+ " <span style='font-weight: bold' id='delComment'> × </span>"
                                + " <span style='font-weight: bold' onclick='deleteBoardComment("+ getList[i].no +", " + getList[i].level + ")'> × </span></span>"
                              //  + "</p>"
                                + "<br>"
                               // + thisBlank_content
                                + getList[i].content
                        + "</td>"

                        + "</tr>"

                );//onclick='procMoveBoardForm
            }
        }

        objTable.append(htmlString);

    }

    /*************************************************************************************************/


    function deleteBoardComment(bcNo, bcLevel) {
        console.log("deleteBoardComment " + bcNo);
        console.log("deleteBoardComment " + bcLevel);



    }
    function deleteBoardComment() {

        var no = ${REQUEST_NO};

        var param = {
            no : no
        }

        procCallAjax(DELETE_BOARD_COMMENT_PROC_URL, param, procCallbackDeleteBoardComment);

    }
    var procCallbackDeleteBoardComment = function(data) {

        if (RESULT_STATUS_SUCCESS == data.RESULT) {
            console.log("CHECK:: 06 :: procCallbackDeleteNotice :: SUCESS");
            procAlert("success", '<spring:message code="common.info.result.success" />');

            procGetBoardComment();



        } else {
            console.log("CHECK:: 06 :: procCallbackDeleteNotice :: FAIL");
            procAlert("danger", data.RESULT_MESSAGE);

        }
    };


    function deleteBoard() {

        var no = ${REQUEST_NO};

        var param = {
            no : no
        }

        procCallAjax(DELETE_BOARD_PROC_URL, param, procCallbackDeleteBoard);

    }


    // DELETE CALLBACK
    var procCallbackDeleteBoard = function(data) {

        console.log("CHECK:: 05 :: procCallbackDeleteNotice");

        if (RESULT_STATUS_SUCCESS == data.RESULT) {
            console.log("CHECK:: 06 :: procCallbackDeleteNotice :: SUCESS");
            procAlert("success", '<spring:message code="common.info.result.success" />');

            procMovePage(TO_LIST);



        } else {
            console.log("CHECK:: 06 :: procCallbackDeleteNotice :: FAIL");
            procAlert("danger", data.RESULT_MESSAGE);

        }
    };




    /*************************************************************************************************/

    var deleteFile = function() {
        console.log("CHECK:: 03 :: deleteFile");

        var oldFilePath = $("#oldFilePath").val();

        if (oldFilePath == null || oldFilePath == "") {
            console.log("CHECK:: 03 :: deleteFile :: IF :: 기존 file path가 null => 파일 삭제 x");
            deleteBoard();

        } else {
            console.log("CHECK:: 03 :: deleteFile :: IF :: 기존 file path가 not null => 파일 삭제");

            var param = {
                filePath : oldFilePath
            };

            procCallAjax(FILE_DELETE_PROC_URL, param, procCallbackDeleteFile);

        }
    };

    var procCallbackDeleteFile = function(data) {


        console.log("CHECK:: 04 :: procCallbackDeleteFile");

        if (RESULT_STATUS_SUCCESS == data.RESULT) {
            console.log("CHECK:: 05 :: procCallbackDeleteFile :: SUCESS");

            procAlert("success", '<spring:message code="common.info.result.success" />');
            $("#fileName").val('');
            $("#filePath").val('');

            // DELETE
            if (DELETE_FLAG) {
                console.log("CHECK:: 05-01 :: DELETE FLAG TRUE => DELETE");

                deleteBoard();
            }



        } else {
            console.log("CHECK:: 05 :: procCallbackDeleteFile :: FAIL");
            procAlert("danger", data.RESULT_MESSAGE);
        }


    };
    /*************************************************************************************************/



    function extractDate(str) {
        var newStr = str.substring(0, 10);
        return newStr;
    }

    // BIND :: BUTTON EVENT
    $("#btnCancel").on("click", function() {
        procMovePage(TO_LIST);
    });

    // BIND :: BUTTON EVENT
    $("#btnDelete").on("click", function() {
        //    deleteAnswer();
        procPopup('게시글', DELETE_MESSAGE, 'setDeleteFlag();');

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
