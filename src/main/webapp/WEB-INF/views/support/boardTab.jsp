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

<div class="text-center">

    <div style="margin: 70px 0px 0px 10px;width:98%;">
        <table class="table table-hover" >
        <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>게시일</th>
        </tr>
        </thead>
        <tbody id="boardList">

        </tbody>
    </table>

    <%--<div class="text-center">--%>
        <ul id="boardPagination" class="pagination-sm"></ul>
    <%--</div>--%>
    </div>
</div>

<form id="boardHiddenForm">
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
    var LIST_PROC_URL_BOARD = "<c:url value='/support/getBoardList' />";
    var FORM_URL_BOARD = "<c:url value='/support/boardForm' />";
    var TOTAL_LENGTH = 1;
    var PER = <%= Constants.PAGE_LIMIT %>;
    var PAGES = 0;




    $(document.body).ready(function () {
//        procAlert("info", "BOARD :: BEGIN");

        // GET INIT LIST
        var param = {
            pageOffset : 0,
            pageLimit : PER
        }
        //getBoardInitList(param);
        getBoardInitList(param);

    });

    function boardListHide() {
        $("#boardList").hide();
    }

    function boardListShow() {
        $("#boardList").show();
    }



    // GET INIT LIST
    var getBoardInitList = function(reqParam) {

        var reqSearchStartDate = $('#startDateInput-board').val();
        var reqSearchEndDate = $('#endDateInput-board').val();
        var reqSearchTypeColumn = $('#searchTypeColumn-board').val();
        var reqSearchKeyword = $('#searchKeyword-board').val();



        var param = {
            searchStartDate : reqSearchStartDate,
            searchEndDate : reqSearchEndDate,
            searchKeyword : reqSearchKeyword,
            searchTypeColumn : reqSearchTypeColumn,
            pageLimit : PER,
            pageOffset : 0
        };

        if (null != reqParam && "" != reqParam) //param = reqParam;
            $.extend(param, reqParam);

        boardListHide();
        procCallAjax(LIST_PROC_URL_BOARD, param, procCallbackGetBoardInitList);
    };

    // GET INIT LIST CALLBACK
    var procCallbackGetBoardInitList = function(data) {
        $("#boardList").empty();
        boardListShow();
        PAGES = 0;

        if (data.list.length < 1) {
            PAGES = 0;
            $("#boardList").append('<tr><td colspan="4">' + '<spring:message code="common.info.empty.data" />' + '</td></tr>');

            if ($('#boardPagination').data("twbs-pagination")) {
                $('#boardPagination').twbsPagination('destroy');
            }


        } else {
            TOTAL_LENGTH = data.list[0].listLength;
            PAGES = Math.ceil(TOTAL_LENGTH/PER);

            printData(data);

            paging();
        }


    }

    function printData(data) {
        $("#boardList").empty();
        boardListShow();


        if (data.list.length < 1) {
            PAGES = 0;
            $("#boardList").empty();
            $("#boardList").append('<tr><td colspan="4">' + '<spring:message code="common.info.empty.data" />' + '</td></tr>');

            if ($('#boardPagination').data("twbs-pagination")) {
                $('#boardPagination').twbsPagination('destroy');
            }

        } else {
            TOTAL_LENGTH = data.list[0].listLength;
            PAGES = Math.ceil(TOTAL_LENGTH/PER);

            var objTable = $("#boardList");
            var htmlString = [];
            var getList = data.list;

            objTable.empty();
            objTable.html('');

            for (var i = 0; i < PER; i++) {

                if (getList[i] != undefined) {


                    var thisTitle="";

                    if (getList[i].level > 0) { // level == depth, 0: 원글, 1~: 답글
                        for (var j = 0; j < getList[i].level; j++) {
                            thisTitle = thisTitle + "　";
                        }
                        thisTitle = thisTitle + "└ ";
                    }
                    thisTitle = thisTitle + getList[i].title + "　";;

                    var commentNum = getList[i].commentNum;
                    if(commentNum > 0) {
                        thisTitle = thisTitle + "[" + commentNum + "]";
                    }



                    htmlString.push(
                            "<tr style='cursor: pointer;' onclick='procMoveBoardForm(" + getList[i].no + ")'>"   //getList[i].status
                            + "<td class='col-md-1 tac' style='text-align: center;'>" + getList[i].no + "</td>" // rownum
                            + "<td class='col-md-6'>" + thisTitle  +"</td>"
                            + "<td class='col-md-1 tac' style='text-align: center;'>" + getList[i].userId + "</td>"
                            + "<td class='col-md-2 tac' style='text-align: center;'>" + extractDate(getList[i].created) + "</td>"
                            + "</tr>"

                    );

                }
            }

            objTable.append(htmlString);

        }

    }


    function paging() {

        if ($('#boardPagination').data("twbs-pagination")) {
            $('#boardPagination').twbsPagination('destroy');
        }

        $('#boardPagination').twbsPagination({
            totalPages: PAGES,  // 전체 page 수
            visiblePages: 5,  // 출력될 page 수

            onPageClick: function (event, page) {

                var offSet = (page-1)*PER;
                var param = {
                    pageOffset : offSet,
                    pageLimit : PER

                }

                getBoardList(param);

            }
        });

    }

    var getBoardList = function(reqParam) {

        var reqSearchStartDate = $('#startDateInput-board').val();
        var reqSearchEndDate = $('#endDateInput-board').val();
        var reqSearchTypeColumn = $('#searchTypeColumn-board').val();
        var reqSearchKeyword = $('#searchKeyword-board').val();


        var param = {
            searchKeyword : reqSearchKeyword,
            searchTypeColumn : reqSearchTypeColumn,
            searchStartDate : reqSearchStartDate,
            searchEndDate : reqSearchEndDate,
            pageLimit : PER
        };

        if (null != reqParam && "" != reqParam)
            $.extend(param, reqParam);// param = reqParam;

        boardListHide();
        procCallAjax(LIST_PROC_URL_BOARD, param, printData);
    };


    // MOVE PAGE
    var procMoveBoardForm = function(reqParam) {

        var hiddenForm = $('#boardHiddenForm');
        $('#boardHiddenForm #no').val(reqParam);


        hiddenForm.attr({action:FORM_URL_BOARD, method:"POST"}).submit();
    }

</script>
