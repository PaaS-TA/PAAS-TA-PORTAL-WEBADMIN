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



    <div style="margin: 70px 0px 0px 10px;width:98%;">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>번호</th>
                <th>분류</th>
                <th>제목</th>
                <th>작성자</th>
                <th>게시일</th>
                <th>답변</th>
            </tr>
            </thead>
            <tbody id="qnaList">

            </tbody>
        </table>
    </div>

<div class="text-center">
    <ul id="qnaPagination" class="pagination-sm"></ul>
</div>


<form id="qnaHiddenForm">
    <input type="hidden" id="no" name="no" value="" />
    <input type="hidden" id="status" name="status" value="" />

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
    var LIST_PROC_URL_QNA = "<c:url value='/support/getQnAList' />";
    var FORM_URL_QNA = "<c:url value='/support/qnaForm' />";


    $(document.body).ready(function () {
        procAlert("info", WELCOME_MESSAGE);

        // GET LIST
        getQnAList();

    });

    function qnaListHide() {
        $("#qnaList").hide();
    }

    function qnaListShow() {
        $("#qnaList").show();
    }

    // GET LIST
    var getQnAList = function(reqParam) {

        var reqSearchStartDate = $('#startDateInput-qna').val();
        var reqSearchEndDate = $('#endDateInput-qna').val();

        var param = {
            searchStartDate : reqSearchStartDate,
            searchEndDate : reqSearchEndDate
        };

        if (null != reqParam && "" != reqParam) param = reqParam;

        qnaListHide();
        procCallAjax(LIST_PROC_URL_QNA, param, procCallbackGetQnAList);
    };

    // GET LIST CALLBACK
    var procCallbackGetQnAList = function(data) {
        $("#qnaList").empty();////
        qnaListShow();

        var listLength = data.list.length;
        var per = 10;
        var pages = Math.ceil(listLength/per);
        var getList = data.list;

        /*
         *  page 1 : 0 ~ 9
         *  page 2 : 10 ~ 19
         *  page 3 : 20 ~ 29
         *
         * s = (page-1)*per
         * e = s + (per-1);
         *
         * */

        if($('#qnaPagination').data("twbs-pagination")){
            $('#qnaPagination').twbsPagination('destroy');
        }

        if (pages < 1) {
            $("#qnaList").append('<tr><td colspan="6">' + '<spring:message code="common.info.empty.data" />' + '</td></tr>');

        } else {

            $('#qnaPagination').twbsPagination({
                totalPages: pages,  // 전체 page 수
                visiblePages: 5,  // 출력될 page 수

                onPageClick: function (event, page) {
                    $("#qnaList").empty();

                    var firstIndex = (page - 1) * per; // s = (page-1)*per
                    var lastIndex = firstIndex + per;  // e = s + (per-1);

                    for (var i = firstIndex; i < lastIndex; i++) {

                        if (getList[i] != undefined) {

                            $("#qnaList").append(
                                    "<tr style='cursor: pointer;' onclick='procMoveQNAForm(" + getList[i].no + ", \"" + getList[i].status + "\")'>"   //getList[i].status
                                    + "<td class='col-md-1 tac' style='text-align: center;'>" + getList[i].no + "</td>"
                                    + "<td class='col-md-1 tac'>" + getList[i].classificationValue + "</td>"
                                    + "<td class='col-md-4 tac'>" + getList[i].title + "</td>"
                                    + "<td class='col-md-2 tac'>" + getList[i].userId + "</td>"
                                    + "<td class='col-md-2 tac' style='text-align: center;'>" + extractDate(getList[i].created) + "</td>"
                                    + "<td class='col-md-1 tac' style='text-align: center;'>" + getList[i].statusValue + "</td>"
                                    + "</tr>"

                            );

                        }

                    }

                }
            });
        }





    }


    // MOVE PAGE
    var procMoveQNAForm = function(reqParam, reqParam2) {

        var hiddenForm = $('#qnaHiddenForm');
        $('#qnaHiddenForm #no').val(reqParam);
        $('#qnaHiddenForm #status').val(reqParam2);


        hiddenForm.attr({action:FORM_URL_QNA, method:"POST"}).submit();
    }

    function extractDate(str) { // 필요없을수도
        var newStr = str.substring(0, 10);
        return newStr;
    }

    function settingDate(data) {
        var forSetting = new Date();

        forSetting.setYear(data.substring(0,4));
        forSetting.setMonth((data.substring(5,7)-1));
        forSetting.setDate(data.substring(8,10));

        return forSetting;
    }

    function dateLimit(date) {
        var stdStart = settingDate($("#startDateInput-qna").val());
        var stdEnd = settingDate($("#endDateInput-qna").val());
        var forDate = settingDate(date);
/*

        if (stdStart.getFullYear() > forDate.getFullYear()){
            return false;
        } else if ((stdStart.getMonth()+1) > (forDate.getMonth()+1)) {
            return false;
        } else if (stdStart.getDate() > forDate.getDate()) {
            return false;
        }
*/

/*

        if (stdEnd.getFullYear() < forDate.getFullYear()) {
            return false;
        } else if ((stdEnd.getMonth()+1) < (forDate.getMonth()+1)) {
            return false;
        } else if (stdEnd.getDate() < forDate.getDate()) {
            return false;
        }
*/
        return true;

    }


</script>
