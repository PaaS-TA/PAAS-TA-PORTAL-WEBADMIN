<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div class="col-sm-12 ml-10 tar">
    <button type="button" class="btn btn-point btn-sm" onclick="procMovePage(BUILD_PACK_INSERT_FORM_URL, '<%= Constants.CUD_C %>');" >
        앱 개발환경 등록
    </button>
</div>
<div style="margin: 45px 0 0 10px;width:98%;">
    <div id="buildPackMessageArea">	</div>
    <table id="buildPackTableArea" class="table table-striped">
        <thead>
        <tr>
            <th>이름</th>
            <th>요약</th>
            <th>분류</th>
            <th>공개</th>
        </tr>
        </thead>
        <tbody id="buildPackListTable">
        </tbody>

    </table>
</div>
<form id="BuildPackHiddenForm">
    <input type="hidden" id="BuildPackHiddenFormNo" name="no" value="" />
</form>


<%--
====================================================================================================
SCRIPT BEGIN
====================================================================================================
--%>


<script type="text/javascript">

    var BUILD_PACK_LIST_PROC_URL = "<c:url value='/catalog/getBuildPackCatalogList' />";
    var BUILD_PACK_INSERT_FORM_URL = "<c:url value='/catalog/buildPackForm' />";

    // GET LIST
    var getBuildPackList = function(reqParam) {
        var param = {};

        if (null != reqParam && "" != reqParam) param = reqParam;

        procCallAjax(BUILD_PACK_LIST_PROC_URL, param, procCallbackGetBuildPackList);
    };


    // GET LIST CALLBACK
    var procCallbackGetBuildPackList = function(data) {
        var objTable = $('#buildPackListTable');
        var objTableArea = $('#buildPackTableArea');
        var objMessageArea = $('#buildPackMessageArea');
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

            for (var i = 0; i < listLength; i++) {
                htmlString.push('<tr style="cursor:pointer;" onclick="procMoveBuildPackInsertForm(\'' + catalogList[i].no + '\')">'
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


    // MOVE PAGE
    var procMoveBuildPackInsertForm = function(reqParam) {
        var hiddenForm = $('#BuildPackHiddenForm');
        $('#BuildPackHiddenFormNo').val(reqParam);
        hiddenForm.attr({action:BUILD_PACK_INSERT_FORM_URL, method:"POST"}).submit();
    };


</script>


<%--
====================================================================================================
SCRIPT END
====================================================================================================
--%>
