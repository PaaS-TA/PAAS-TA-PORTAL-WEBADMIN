<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div class="col-sm-12 ml-10 tar">
    <button type="button" class="btn btn-point btn-sm" onclick="procMovePage(STARTER_INSERT_FORM_URL, '<%= Constants.CUD_C %>');" >
        앱 템플릿 등록
    </button>
</div>
<div style="margin: 45px 0 0 10px;width:98%;">
    <div id="starterListMessageArea"></div>
    <table id="starterListTableArea"  class="table table-striped">
        <thead>
        <tr>
            <th>이름</th>
            <th>요약</th>
            <th>분류</th>
            <th>공개</th>
        </tr>
        </thead>
        <tbody id="starterList">
        </tbody>
    </table>
</div>
<form id="starterHiddenForm">
    <input type="hidden" id="starterHiddenFormNo" name="no" value="" />
</form>


<%--
====================================================================================================
SCRIPT BEGIN
====================================================================================================
--%>

<script type="text/javascript">

    var STARTER_LIST_PROC_URL = "<c:url value='/catalog/getStarterNamesList' />";
    var STARTER_INSERT_FORM_URL = "<c:url value='/catalog/starterForm' />";

    // MOVE PAGE
    var procMoveStarterInsertForm = function(reqParam) {
        var hiddenForm = $('#starterHiddenForm');
        $('#starterHiddenFormNo').val(reqParam);
        hiddenForm.attr({action:STARTER_INSERT_FORM_URL, method:"POST"}).submit();
    };


    // GET LIST
    var getStarterList = function(reqParam) {
        var param = {};
        if (null != reqParam && "" != reqParam) param = reqParam;
        procCallAjax(STARTER_LIST_PROC_URL, param, procCallbackGetStarterList);
    };


    // GET LIST CALLBACK
    var procCallbackGetStarterList = function (data) {
        var objTable = $("#starterList");
        var objTableArea = $('#starterListTableArea');
        var objMessageArea = $('#starterListMessageArea');
        var listLength = data.list.length;
        var htmlString = [];

        objTable.html('');
        objMessageArea.html('');

        if (listLength < 1) {
            objTableArea.hide();

            objMessageArea.append('<spring:message code="common.info.empty.data" />');
            objMessageArea.show();

        } else {
            var catalogList = data.list;
            var reqParam = {};

            for (var i = 0; i < listLength; i++) {
                reqParam = {name: catalogList[i].name};
                htmlString.push('<tr style="cursor:pointer;" onclick="procMoveStarterInsertForm(\'' + catalogList[i].no + '\')">'
                        + '<td class="col-md-3">' + catalogList[i].name + '</td>'
                        + '<td class="col-md-4">' + catalogList[i].summary + '</td>'
                        + '<td class="col-md-2 tac">' + catalogList[i].classificationValue + '</td>'
                        + '<td class="col-md-1 tac">' + catalogList[i].useYn + '</td>'
                        + '</tr>');
            }
            objMessageArea.hide();
            objTable.append(htmlString);
            objTableArea.show();
        }
    };

</script>

<%--
====================================================================================================
SCRIPT END
====================================================================================================
--%>