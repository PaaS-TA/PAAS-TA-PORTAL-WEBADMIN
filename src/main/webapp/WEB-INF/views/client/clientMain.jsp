<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp"%>


<%--TITLE--%>
<div class="col-sm-6 pt30">
    <h4 class="modify_h4 fwn">클라이언트 목록</h4>
</div>

<%--SEARCH FORM--%>
<div class="content-box2 col-md-12 col-md-offset-13">
    <div class="box">
        <div class="input-group col-sm-12 ml20">
            <div class="input-group-btn">
                <button type="button" id="btnSearch" class="btn" style="margin: 0 0 0 0; color: #c4c3c3; background-color: #f7f7f9; height: 30px;">
                    <span class="glyphicon glyphicon-search" style="top: -1px; left: 4px;"></span>
                </button>
            </div>
            <input type="text" id="searchKeyword" class="form-control ml3" style="top: -6px; left: -3px; width: 99%;" maxlength="100" placeholder="검색어를 입력하세요." onkeyup="procSearchByKeyword(event);">
        </div>
    </div>
</div>

<div class="row">
    <div class="panel content-box col-sm-12 col-md-12 mt-50 col-md-offset-13 w98">
        <div class="col-sm-6 pt5"> </div>
        <div class="col-sm-6 ml-10 tar">
            <button type="button" class="btn btn-point btn-sm" onclick="procMovePage(GET_CLIENT_FORM);" >
                클라이언트 등록
            </button>
        </div>
        <div style="margin: 40px 0 0 10px;width:98%;">

            <div id="clientInfoMessageArea" class="ml10 mt30 fl"></div>
            <table id="clientInfoTableArea"  class="table table-striped">
                <thead id="clientInfoTableTitle">
                <tr>
                    <th > 클라이언트 ID </th>
                </tr>
                </thead>
                <tbody id="clientInfoTable">
                </tbody>
            </table>
            <div id="buttonGetMoreList" class="mt30 tac">
                <button type="button" class="btn btn-primary btn-sm" onclick="procGetMoreList();">
                    <span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span>
                </button>
            </div>
        </div>

    </div>
</div>


<%--/////--%>
<%--
<div id="clientInfoViewArea">
    <div class="search_box" style="min-height: 200px;">
        <h2> 클라이언트 목록 </h2>
        <label class="control-label sr-only" for="searchKeyword"></label>
        <div class="input-group">
            <span class="input-group-addon">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
            </span>
            <input type="text" class="form-control custom-input-text" id="searchKeyword"
                   maxlength="100" placeholder="검색어를 입력하세요." onkeyup="procSearchByKeyword(event);" />
        </div>

        <div class="mt20">
            <div id="clientInfoMessageArea" class="ml10 mt30 fl">
            </div>

            <div style="float: right; padding: 0 0 10px 0;">
                <button type="button" class="btn btn-primary btn-sm" onclick="procMovePage(GET_CLIENT_FORM);"> 클라이언트 등록 </button>
            </div>

            <table id="clientInfoTableArea" class="table table-striped table-hover t1" style="display: none;">
                <thead id="clientInfoTableTitle">
                <tr>
                    <th > 클라이언트 ID </th>
                </tr>
                </thead>
                <tbody id="clientInfoTable">
                </tbody>
            </table>
            <div id="buttonGetMoreList" class="mt30 tac" style="display: none;">
                <button type="button" class="btn btn-primary btn-sm" onclick="procGetMoreList();">
                    <span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span>
                </button>
            </div>
        </div>
    </div>
</div>
--%>

<form id="hiddenForm">
    <input type="hidden" id="clientId" name="clientId" value="" />
</form>

<%--
====================================================================================================
SCRIPT BEGIN
====================================================================================================
--%>


<script type="text/javascript">
    var GET_CLIENT_FORM = "<c:url value='/client/clientForm' />";
    var LOADED_LIST_COUNT = 0;
    var GET_CLIENT_LIST = "<c:url value='/client/getClientList'/>";
    var CURRENT_OFFSET =  <%= Constants.PAGE_OFFSET %>;
    var LOAD_UNIT = 10;
    var CLIENT_LIST = new Array();
    var CURRENT_LIST;

    $(document).ready(function(){
        getClientList();
    });

    var getClientList = function () {
        var param = {};
        procCallAjax(GET_CLIENT_LIST, param, procCallbackGetClientList)
    };

    var procCallbackGetClientList = function (data) {
        if (RESULT_STATUS_FAIL == data.RESULT) return false;

        for (var i = 0; i < data.list.length; i++) {
            CLIENT_LIST[i] = data.list[i].client_id;
        }

        CURRENT_LIST = CLIENT_LIST;
        printData(CLIENT_LIST);
    }

    function printData(data) {

        var objTableArea = $('#clientInfoTableArea');
        var objTableTitle = $('#clientInfoTableTitle');
        var objTable = $('#clientInfoTable');
        var objMessageArea = $('#clientInfoMessageArea');
        var listLength = data.length;
        var htmlString = [];

        if ('<%= Constants.PAGE_OFFSET %>' == CURRENT_OFFSET) {
            LOADED_LIST_COUNT = 0;
            objTable.html('');
        }

        objMessageArea.html('');

        if (listLength < 1) {
            objTableArea.hide();
            objTableTitle.hide();
            objTable.hide();
            objMessageArea.append('<spring:message code="common.info.empty.data" />');
            objMessageArea.show();

        } else {
            var resultList = data;

            var loopEnd = CURRENT_OFFSET + LOAD_UNIT;
            if (loopEnd > listLength)
                loopEnd = listLength;

            var idx;
            for (idx = CURRENT_OFFSET; idx < loopEnd; idx++) {
                htmlString.push('<tr><td class="col-md-4 " style="cursor:pointer;" '
                        + 'onclick="procMoveClientForm(\'' + resultList[idx] + '\')">' + resultList[idx] + '</td></tr>');
            }

            LOADED_LIST_COUNT += idx;

            if (listLength > LOADED_LIST_COUNT) {
                $('#buttonGetMoreList').animate({
                    opacity: "show"
                });
            } else {
                $('#buttonGetMoreList').hide();
            }

            objMessageArea.hide();
            objTable.append(htmlString);
            objTable.show();
            objTableTitle.show();
            objTableArea.show();
        }
    };


    // MOVE PAGE
    var procMoveClientForm = function(reqParam) {
        var hiddenForm = $('#hiddenForm');
        $('#clientId').val(reqParam);
        hiddenForm.attr({action:GET_CLIENT_FORM, method:"POST"}).submit();
    };

    // GET MORE LIST
    var procGetMoreList = function() {
        CURRENT_OFFSET = CURRENT_OFFSET+10;
        printData(CURRENT_LIST);

    }

    function procSearchByKeyword(event) {
        $('#buttonGetMoreList').hide();
        CURRENT_OFFSET = <%= Constants.PAGE_OFFSET %>;
        LOADED_LIST_COUNT = 0;

        $('#clientInfoTableTitle').hide();
        $('#clientInfoTable').empty();

        var searchKeyword = document.getElementById('searchKeyword').value;
        var searchResult = new Array();
        var newIdx = 0;
        for (var i=0; i < CLIENT_LIST.length; i++ ){

            if (CLIENT_LIST[i].includes(document.getElementById('searchKeyword').value)) {
                searchResult[newIdx++] = CLIENT_LIST[i];
            }
        }

        CURRENT_LIST = searchResult;
        printData(searchResult);

    }


</script>


<%--
====================================================================================================
SCRIPT END
====================================================================================================
--%>


<%--FOOTER--%>
<%@ include file="../common/footer.jsp"%>
