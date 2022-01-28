<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp"%>

<div class="col-sm-6 pt30">
    <h4 class="modify_h4 fwn">Support management</h4>
</div>

<!--토글 탭-->
<div class="panel content-box col-sm-12 col-md-12 mt-50 col-md-offset-13 w98">
    <ul class="nav nav-tabs">
        <li id="supportTabs_<%= Constants.TAB_NAME_NOTICE %>" class="active">
            <a href="#<%= Constants.TAB_NAME_NOTICE %>" data-toggle="tab" onClick="procSelectTab('NOTICE')">Notice</a>
        </li>
        <li id="supportTabs_<%= Constants.TAB_NAME_QNA %>">
            <a href="#<%= Constants.TAB_NAME_QNA %>" data-toggle="tab" onClick="procSelectTab('QNA')">Question</a>
        </li>
        <li id="supportTabs_<%= Constants.TAB_NAME_BOARD %>">
            <a href="#<%= Constants.TAB_NAME_BOARD %>" data-toggle="tab" onClick="procSelectTab('BOARD')">Notice</a>
        </li>
    </ul>

    <div class="tab-content">
        <%--<div class="tab-pane active" id="home">--%>

        <div class="tab-pane active" id="<%= Constants.TAB_NAME_NOTICE %>" >
            <%@ include file="../support/noticeSearch.jsp"%>
            <%@ include file="../support/noticeTab.jsp"%>
        </div>
        <div class="tab-pane" id="<%= Constants.TAB_NAME_QNA %>">
            <%@ include file="../support/qnaSearch.jsp"%>
            <%@ include file="../support/qnaTab.jsp"%>
        </div>
        <div class="tab-pane" id="<%= Constants.TAB_NAME_BOARD %>">
            <%@ include file="../support/boardSearch.jsp"%>
            <%@ include file="../support/boardTab.jsp"%>
        </div>

        </div>
</div>



<%--FOOTER--%>
<%@ include file="../common/footer.jsp"%>

<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>
<%--CODE :: END--%>
<%--////////////////////////////////////////////////////////////////////////////////////////////////////--%>

<%--
====================================================================================================
SCRIPT BEGIN
====================================================================================================
--%>




<script type="text/javascript">

    var TAB_NAME = "<c:out value='${TAB_NAME}' default='' />";

    $(document).ready(function(){


    });


    // SELECT TAB
    var procSelectTab = function(reqTabName) {
        if ("" == reqTabName) return false;

        $('.tab-pane.fade').removeClass('active in');
        $('#' + reqTabName).addClass('active in');
        $('#supportTabs_' + reqTabName).tab("show");

        $("#BOARD").hide();
        $("#QNA").hide();
        $("#NOTICE").hide();
        $("#" + reqTabName).show();
    };


    $(document.body).ready(function() {

        procSelectTab(TAB_NAME);
    });


    /********************************************************************************************/



</script>
<%--
====================================================================================================
SCRIPT END
====================================================================================================
--%>





