<!--
=================================================================
* 시  스  템  명 : PaaS-TA 관리자 포탈
* 업    무    명 : 카탈로그
* 프로그램명(ID) : catalogMain.jsp(카탈로그조회)
* 프로그램  개요 : 카탈로그 목록을 조회하는 화면
* 작    성    자 : 김도준
* 작    성    일 : 2016.07.04
=================================================================
수정자 / 수정일 :
수정사유 / 내역 :
=================================================================
-->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp"%>

<%--TITLE--%>
<div class="col-sm-6 pt30">
	<h4 class="modify_h4 fwn">카탈로그 관리</h4>
</div>

<%--SEARCH FORM--%>
<div class="content-box2 col-md-12 col-md-offset-13">
	<div class="box">
		<div class="form-group col-sm-2 mr-25">
			<label for="searchType" class="control-label-white col-sm-6 mt13"> 검색유형 </label>
			<div class="col-sm-6">
				<select id="searchType" name="searchType" class="form-control" style="background:url(/resources/images/btn_down.png) no-repeat right; background-color:#fafafa;">
					<option value="<%= Constants.SEARCH_TYPE_ALL %>"> 전체 </option>
					<option value="<%= Constants.TAB_NAME_STARTER %>"> 앱 템플릿 </option>
					<option value="<%= Constants.TAB_NAME_BUILD_PACK %>"> 앱 개발환경 </option>
					<option value="<%= Constants.TAB_NAME_SERVICE_PACK %>"> 서비스 </option>
				</select>
			</div>
		</div>
		<div class="form-group col-sm-2 mr-25">
			<label for="searchTypeColumn" class="control-label-white col-sm-6 mt13 ml-10"> 검색항목 </label>
			<div class="col-sm-6">
				<select id="searchTypeColumn" name="searchTypeColumn" class="form-control" style="background:url(/resources/images/btn_down.png) no-repeat right; background-color:#fafafa;">
					<option value="<%= Constants.SEARCH_TYPE_ALL %>"> 전체 </option>
					<option value="<%= Constants.SEARCH_TYPE_NAME %>"> 이름 </option>
					<option value="<%= Constants.SEARCH_TYPE_SUMMARY %>"> 요약 </option>
				</select>
			</div>
		</div>
		<div class="form-group col-sm-2 mr-25">
			<label for="searchTypeUseYn" class="control-label-white col-sm-6 mt13 ml-10"> 공개여부 </label>
			<div class="col-sm-6">
				<select id="searchTypeUseYn" name="searchTypeUseYn" class="form-control" style="background:url(/resources/images/btn_down.png) no-repeat right; background-color:#fafafa;">
					<option value="<%= Constants.SEARCH_TYPE_ALL %>"> 전체 </option>
					<option value="<%= Constants.USE_YN_Y %>"> 공개 </option>
					<option value="<%= Constants.USE_YN_N %>"> 비공개 </option>
				</select>
			</div>
		</div>
		<div class="form-group col-sm-3">
			<label for="searchKeyword" class="control-label-white col-sm-3 mt13 ml-10"> 검색어 </label>
			<div class="input-group col-sm-8 ml20">
				<input type="text" id="searchKeyword" class="form-control2 ml10" placeholder="검색어를 입력하세요." onkeypress="procCheckSearchFormKeyEvent(event);">
				<div class="input-group-btn">
					<button type="button" id="btnSearch" class="btn" style="margin: 11px 0 0 0; color: #c4c3c3; background-color: #f7f7f9; height: 30px;">
						<span class="glyphicon glyphicon-search" style="top: -1px; left: 4px;"></span>
					</button>
				</div>
			</div>
		</div>
	</div>
</div>


<!--토글 탭-->
<div class="panel content-box col-sm-12 col-md-12 mt-50 col-md-offset-13 w98">
	<ul class="nav nav-tabs">
		<li id="catalogTabs_<%= Constants.TAB_NAME_STARTER %>" class="active">
			<a href="#<%= Constants.TAB_NAME_STARTER %>" data-toggle="tab" onClick="procSelectTab('STARTER')">앱 템플릿</a>
		</li>
		<li id="catalogTabs_<%= Constants.TAB_NAME_BUILD_PACK %>">
			<a href="#<%= Constants.TAB_NAME_BUILD_PACK %>" data-toggle="tab" onClick="procSelectTab('BUILD_PACK')">앱 개발환경</a>
		</li>
		<li id="catalogTabs_<%= Constants.TAB_NAME_SERVICE_PACK %>">
			<a href="#<%= Constants.TAB_NAME_SERVICE_PACK %>" data-toggle="tab" onClick="procSelectTab('SERVICE_PACK')">서비스</a>
		</li>
	</ul>

	<div class="tab-content">
		<div class="tab-pane active" id="<%= Constants.TAB_NAME_STARTER %>" >
			<%@ include file="../catalog/starterTab.jsp"%>
		</div>
		<div class="tab-pane" id="<%= Constants.TAB_NAME_BUILD_PACK %>">
			<%@ include file="../catalog/buildPackTab.jsp"%>
		</div>
		<div class="tab-pane" id="<%= Constants.TAB_NAME_SERVICE_PACK %>">
			<%@ include file="../catalog/servicePackTab.jsp"%>
		</div>

	</div>
</div>


<script type="text/javascript">

    var TAB_NAME = "<c:out value='${TAB_NAME}' default='' />";

    $(document.body).ready(function() {

        procSelectTab(TAB_NAME);
    });


    // SELECT TAB
    var procSelectTab = function(reqTabName) {
        if ("" == reqTabName) return false;

        $('.tab-pane.fade').removeClass('active in');
        $('#' + reqTabName).addClass('active in');
        $('#catalogTabs_' + reqTabName).tab("show");

        $("#STARTER").hide();
        $("#BUILD_PACK").hide();
        $("#SERVICE_PACK").hide();
        $("#" + reqTabName).show();
    };


    // CHECK SEARCH FORM KEY EVENT
    var procCheckSearchFormKeyEvent = function(e) {
        if (e.keyCode == 13 && e.srcElement.type != 'textarea') {
            procGetSearchList();
        }
    };


    // GET SEARCH LIST
    var procGetSearchList = function() {
        var doc = document;
        var reqSearchType = doc.getElementById('searchType').value;
        var reqSearchTypeColumn = doc.getElementById('searchTypeColumn').value;
        var reqSearchTypeUseYn = doc.getElementById('searchTypeUseYn').value;
        var reqSearchKeyword = doc.getElementById('searchKeyword').value.toLowerCase();

        var param = {
            searchKeyword : reqSearchKeyword,
            searchTypeColumn : reqSearchTypeColumn,
            searchTypeUseYn : reqSearchTypeUseYn
        };

        var objDivStarter = $("#divStarter");
        var objDivBuildPack = $("#divBuildPack");
        var objDivServicePack = $("#divServicePack");

        objDivStarter.show();
        objDivBuildPack.show();
        objDivServicePack.show();

        if ("<%= Constants.TAB_NAME_BUILD_PACK %>" == reqSearchType) {
            getBuildPackList(param);
            objDivStarter.hide();
            objDivServicePack.hide();

        } else if ("<%= Constants.TAB_NAME_SERVICE_PACK %>" == reqSearchType) {
            getServicePackList(param);
            objDivStarter.hide();
            objDivBuildPack.hide();

        } else if ("<%= Constants.TAB_NAME_STARTER %>" == reqSearchType) {
            getStarterList(param);
            objDivBuildPack.hide();
            objDivServicePack.hide();

        } else {
            getStarterList(param);
            getBuildPackList(param);
            getServicePackList(param);
        }
    };

    // BIND :: BUTTON EVENT
    $("#btnSearch").on("click", function() {
        procGetSearchList();
    });


    $(document.body).ready(function () {
        getStarterList();
        getBuildPackList();
        getServicePackList();
    });

</script>


<%--FOOTER--%>
<%@ include file="../common/footer.jsp"%>
