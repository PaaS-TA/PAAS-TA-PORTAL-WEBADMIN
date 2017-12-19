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
    <button type="button" class="btn btn-point btn-sm fr mb10" onClick="procMovePage(FORM_URL_NOTICE, '<%= Constants.CUD_C %>');" >
        공지 등록
    </button>
</div>

<div style="margin: 45px 0px 0px 10px;width:98%;">
    <table class="table table-hover" id="noticeTable">
        <thead>
        <tr>
            <th>번호</th>
            <th>분류</th>
            <th>제목</th>
            <th>게시일</th>
            <th>공개</th>
        </tr>
        </thead>
        <tbody id="noticeList">

        </tbody>
    </table>
</div>

<div class="text-center">
    <ul id="noticePagination" class="pagination-sm"></ul>
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
    var LIST_PROC_URL_NOTICE = "<c:url value='/support/getNoticeList' />";
    var FORM_URL_NOTICE = "<c:url value='/support/noticeForm' />";

     $(document.body).ready(function () {
        procAlert("info", WELCOME_MESSAGE);

        // GET LIST
        getNoticeList();

    });

    function noticeListHide() {
        $("#noticeList").hide();
    }

    function noticeListShow() {
        $("#noticeList").show();
    }

    /****************************************************************************************************** get list */



    // GET LIST
    var getNoticeList = function(reqParam) {

        var reqSearchStartDate = $('#startDateInput').val();
        var reqSearchEndDate = $('#endDateInput').val();

        var param = {
            searchStartDate : reqSearchStartDate,
            searchEndDate : reqSearchEndDate
        };

        if (null != reqParam && "" != reqParam) {
       //     $.extend(param, reqParam);
            param = reqParam;
        }

        noticeListHide();
        //$.extend(param, param2);
        procCallAjax(LIST_PROC_URL_NOTICE, param, procCallbackGetNoticeList);
    };

    // GET LIST CALLBACK
    var procCallbackGetNoticeList = function(data) {
        $("#noticeList").empty();
        noticeListShow();

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

    //    $('#noticePagination').twbsPagination('destroy');
     //   $('#noticePagination').twbsPagination({totalPages:5});

        if($('#noticePagination').data("twbs-pagination")){
            $('#noticePagination').twbsPagination('destroy');
        }

        if (pages < 1) {
            $("#noticeList").append('<tr><td colspan="5">' + '<spring:message code="common.info.empty.data" />' + '</td></tr>');

        } else {

            $('#noticePagination').twbsPagination({
                totalPages: pages,  // 전체 page 수
                visiblePages: 5,  // 출력될 page 수

                onPageClick: function (event, page) {
                    $("#noticeList").empty();

                    var firstIndex = (page - 1) * per; // s = (page-1)*per
                    var lastIndex = firstIndex + per;  // e = s + (per-1);

                    for (var i = firstIndex; i < lastIndex; i++) {
                        if (getList[i] != undefined) {
                            //    var extractedStartDate = extractDate(getList[i].startDate);
                            //    var extractedEndDate = extractDate(getList[i].endDate);
                            var extractedStartDate = getList[i].startDate;
                            var extractedEndDate = getList[i].endDate;

                            $("#noticeList").append(
                                    "<tr style='cursor: pointer;' onclick='procMoveNoticeForm(" + getList[i].no + ")'>"
                                    + "<td class='col-md-1 tac' style='text-align: center;'>" + getList[i].no + "</td>"
                                    + "<td class='col-md-2'>" + getList[i].classificationValue + "</td>"
                                    + "<td class='col-md-4'>" + getList[i].title + "</td>"
                                    + "<td class='col-md-3 tac' style='text-align: center;'>" + extractedStartDate + ' ~ ' + extractedEndDate + "</td>"
                                    + "<td class='col-md-1 tac' style='text-align: center;'>"
                                    + getList[i].useYn + "</td>"
                                    + "</tr>"
                            );
                        }
                    }
                }
            });
        }
    }


    // MOVE PAGE
    var procMoveNoticeForm = function(reqParam) {
        var hiddenForm = $('#hiddenForm');
        $('#no').val(reqParam);
        hiddenForm.attr({action:FORM_URL_NOTICE, method:"POST"}).submit();
    };

    function settingDate(data) {
        var forSetting = new Date();

        forSetting.setYear(data.substring(0,4));
        forSetting.setMonth((data.substring(5,7)-1));
        forSetting.setDate(data.substring(8,10));

        return forSetting;
    }

    function dateLimit(start, end) {
        var stdStart = settingDate($("#startDateInput").val());
        var stdEnd = settingDate($("#endDateInput").val());
        var forStart = settingDate(start);
        var forEnd = settingDate(end);


        if (stdStart.getFullYear() > forStart.getFullYear())
            return false;
        else if ((stdStart.getMonth()+1) > (forStart.getMonth()+1))
            return false;
        else if (stdStart.getDate() > forStart.getDate())
            return false;

        if (stdEnd.getFullYear() < forEnd.getFullYear())
            return false;
        else if ((stdEnd.getMonth()+1) < (forEnd.getMonth()+1))
            return false;
        else if (stdEnd.getDate() < forEnd.getDate())
            return false;

        return true;

    }

    function extractDate(str) { // 필요없을수도
        var newStr = str.substring(0, 10);
        return newStr;
    }

</script>


