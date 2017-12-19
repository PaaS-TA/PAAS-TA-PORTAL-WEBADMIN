<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: YJKim
  Date: 2016-07-25
  Time: 오후 3:51
  To change this template use File | Settings | File Templates.
--%>

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<%--CODE :: BIGIN--%>
<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>


<div class="col-sm-12 ml-10">
    <button type="button" class="btn btn-point btn-sm fr mb10" onClick="procMovePage(FORM_URL_DOCUMENTS, '<%= Constants.CUD_C %>');" >
        문서 등록
    </button>
</div>

<div style="margin: 45px 0px 0px 10px;width:98%;">
    <table class="table table-hover" id="documentsTable">
        <thead>
        <tr>
            <th>번호</th>
            <th>분류</th>
            <th>제목</th>
            <th>작성자</th>
            <th>게시일</th>
            <th>공개</th>
        </tr>
        </thead>
        <tbody id="documentsList">

        </tbody>
    </table>
</div>

<div class="text-center">
    <ul id="documentsPagination" class="pagination-sm"></ul>
</div>


<form id="hiddenForm">
    <input type="hidden" id="no" name="no" value="" />
</form>


<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<%--CODE :: END--%>
<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>


<%--
====================================================================================================
SCRIPT BEGIN
====================================================================================================
--%>


<%-- twbs-pagination library --%>
<script type="text/javascript" src="/resources/js/lib/jquery.twbsPagination.js"></script>

<script type="text/javascript">
    var LIST_PROC_URL_DOCUMENTS = "<c:url value='/documents/getDocumentsList' />";
    var FORM_URL_DOCUMENTS = "<c:url value='/documents/documentsForm' />";
    var TOTAL_LENGTH = 1;
    var PER = 10;
    var PAGES = 0;

     $(document.body).ready(function () {
        procAlert("info", WELCOME_MESSAGE);
         getDocumentsInitList();
    });

    function documentsListHide() {
        $("#documentsList").hide();
    }
    function documentsListShow() {
        $("#documentsList").show();
    }

    /****************************************************************************************************** get list */

    // GET INIT LIST
    var getDocumentsInitList = function(reqParam) {

        var reqSearchStartDate = $('#startDateInput-documents').val();
        var reqSearchEndDate = $('#endDateInput-documents').val();
        var reqSearchTypeColumn = $('#searchTypeColumn').val();
        var reqSearchKeyword = $('#searchKeyword').val();
        var reqSearchTypeUseYn = $('#searchTypeUseYn').val();

        var param = {
            searchStartDate : reqSearchStartDate,
            searchEndDate : reqSearchEndDate,
            searchKeyword : reqSearchKeyword,
            searchTypeColumn : reqSearchTypeColumn,
            searchTypeUseYn : reqSearchTypeUseYn,
            pageOffset : 0
        };

        if (null != reqParam && "" != reqParam) //param = reqParam;
            $.extend(param, reqParam);

        documentsListHide();
        procCallAjax(LIST_PROC_URL_DOCUMENTS, param, procCallbackGetDocumentsInitList);
    };

    // GET INIT LIST CALLBACK
    var procCallbackGetDocumentsInitList = function(data) {

        $("#documentsList").empty();
        documentsListShow();
        PAGES = 0;

        if (data.list.length < 1) {
            PAGES = 0;
            $("#documentsList").append('<tr><td colspan="6">' + '<spring:message code="common.info.empty.data" />' + '</td></tr>');
            if ($('#documentsPagination').data("twbs-pagination")) {
                $('#documentsPagination').twbsPagination('destroy');
            }
        } else {
            TOTAL_LENGTH = data.list[0].listLength;
            PAGES = Math.ceil(TOTAL_LENGTH/PER);

            printDocumentsData(data);
            paging();
        }
    }


    function printDocumentsData(data) {
        $("#documentsList").empty();
        documentsListShow();

        if (data.list.length < 1) {
            PAGES = 0;
            $("#documentsList").empty();
            $("#documentsList").append('<tr><td colspan="6">' + '<spring:message code="common.info.empty.data" />' + '</td></tr>');
            if ($('#documentsPagination').data("twbs-pagination")) {
                $('#documentsPagination').twbsPagination('destroy');
            }

        } else {
            TOTAL_LENGTH = data.list[0].listLength;
            PAGES = Math.ceil(TOTAL_LENGTH/PER);

            var objTable = $("#documentsList");
            var htmlString = [];
            var getList = data.list;

            objTable.empty();
            objTable.html('');

            var index = 0;

            for (var i = 0; i < PER; i++) {

                if (getList[i] != undefined) {

                    htmlString.push(
                            "<tr style='cursor: pointer;' onclick='procMoveDocumentsForm(" + getList[i].no + ")'>"
                            + "<td class='col-md-1 tac' style='text-align: center;'>" + getList[i].no + "</td>" //rownum
                            + "<td class='col-md-2'>" + getList[i].classificationValue + "</td>"
                            + "<td class='col-md-4'>" + getList[i].title + "</td>"
                            + "<td class='col-md-1'>" + getList[i].userId + "</td>"
                            + "<td class='col-md-2 tac' style='text-align: center;'>" + extractDate(getList[i].created) + "</td>"
                            + "<td class='col-md-1 tac' style='text-align: center;'>"
                            + getList[i].useYn + "</td>"
                            + "</tr>"
                    );
                }
            }
            objTable.append(htmlString);
        }
    }


    function paging() {

        if ($('#documentsPagination').data("twbs-pagination")) {
            $('#documentsPagination').twbsPagination('destroy');
        }

        $('#documentsPagination').twbsPagination({
            totalPages: PAGES,  // 전체 page 수
            visiblePages: 5,  // 출력될 page 수

            onPageClick: function (event, page) {

                var offSet = (page-1)*10;
                var param = {
                    pageOffset : offSet
                }
                getDocumentsList(param);
            }
        });

    }

    var getDocumentsList = function(reqParam) {

        var reqSearchTypeUseYn = $('#searchTypeUseYn').val();
        var reqSearchStartDate = $('#startDateInput-documents').val();
        var reqSearchEndDate = $('#endDateInput-documents').val();
        var reqSearchTypeColumn = $('#searchTypeColumn').val();
        var reqSearchKeyword = $('#searchKeyword').val();

        var param = {
            searchTypeUseYn : reqSearchTypeUseYn,
            searchKeyword : reqSearchKeyword,
            searchTypeColumn : reqSearchTypeColumn,
            searchStartDate : reqSearchStartDate,
            searchEndDate : reqSearchEndDate
        };

        if (null != reqParam && "" != reqParam)
            $.extend(param, reqParam);// param = reqParam;

        documentsListHide();
        procCallAjax(LIST_PROC_URL_DOCUMENTS, param, printDocumentsData);
    };

    ///////////////////////////////////////////////////////////////////////////////

    // MOVE PAGE
    var procMoveDocumentsForm = function(reqParam) {
        var hiddenForm = $('#hiddenForm');
        $('#no').val(reqParam);
        hiddenForm.attr({action:FORM_URL_DOCUMENTS, method:"POST"}).submit();
    }

    function extractDate(str) { // 필요없을수도
        var newStr = str.substring(0, 10);
        return newStr;
    }

</script>
