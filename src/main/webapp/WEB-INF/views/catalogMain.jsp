<%--CONSTANT--%>
<%@ page import="org.openpaas.paasta.portal.web.admin.common.Constants" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="./common/common_LTE.jsp" %>
<!-- ===================================================================================== -->
<!-- Content Header (Page header) -->
<section class="content-header">
    <h1>카탈로그 관리</h1>
</section>

<!-- Main content -->
<!-- 검색유형/검색항목/공개여뷰/검색어-->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box box-default">
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="selectbox select1">
                                <label for="searchType">검색유형</label>
                                <select id="searchType" name="searchType" class="form-control select2 select2-hidden-accessible" style="width: 100%;"
                                        tabindex="-1" aria-hidden="true">
                                    <option selected="selected" value="<%= Constants.SEARCH_TYPE_ALL %>"> 전체 </option>
                                    <option value="<%= Constants.TAB_NAME_STARTER %>"> 앱 템플릿 </option>
                                    <option value="<%= Constants.TAB_NAME_BUILD_PACK %>"> 앱 개발환경 </option>
                                    <option value="<%= Constants.TAB_NAME_SERVICE_PACK %>"> 서비스 </option>
                                </select>
                                <span class="select2 select2-container select2-container--default select2-container--below" dir="ltr" style="width: 100%;">
                                    <span class="selection"></span>
                                    <span class="dropdown-wrapper" aria-hidden="true"></span>
                                </span>
                            </div>
                            <!-- /.form-group -->
                            <div class="selectbox select2">
                                <label for="searchTypeColumn">검색항목</label>
                                <select id="searchTypeColumn" name="searchTypeColumn" class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true">
                                    <option  selected="selected" value="<%= Constants.SEARCH_TYPE_ALL %>"> 전체 </option>
                                    <option value="<%= Constants.SEARCH_TYPE_NAME %>"> 이름 </option>
                                    <option value="<%= Constants.SEARCH_TYPE_SUMMARY %>"> 요약 </option>
                                </select>
                                <span class="select2 select2-container select2-container--default select2-container--below" dir="ltr" style="width: 100%;">
                                    <span class="selection"></span>
                                    <span class="dropdown-wrapper" aria-hidden="true"></span>
                                </span>
                            </div>
                            <!-- /.form-group -->
                        </div>
                        <!-- /.col -->
                        <div class="col-md-6">
                            <div class="selectbox select3">
                                <label for="searchTypeUseYn">공개여부</label>
                                <select id="searchTypeUseYn" name="searchTypeUseYn" class="form-control select2 select2-hidden-accessible" style="width: 100%;" tabindex="-1" aria-hidden="true">
                                    <option  selected="selected" value="<%= Constants.SEARCH_TYPE_ALL %>"> 전체 </option>
                                    <option value="<%= Constants.USE_YN_Y %>"> 공개 </option>
                                    <option value="<%= Constants.USE_YN_N %>"> 비공개 </option>
                                </select>
                                <span class="select2 select2-container select2-container--default select2-container--below" dir="ltr" style="width: 100%;">
                                    <span class="selection"></span>
                                    <span class="dropdown-wrapper" aria-hidden="true"></span>
                                </span>
                            </div>
                            <!-- /.form-group -->
                            <div class="select select4">
                                <label for="searchTypeColumn">검색어</label>
                                <div class="box-tools">
                                    <div class="input-group input-group-sm">
                                        <input type="text" id="searchKeyword" class="form-control pull-right" placeholder="검색어를 입력하세요" onkeypress="procCheckSearchFormKeyEvent(event);">
                                        <div class="input-group-btn">
                                            <button type="submit" class="btn btn-default"><i class="fa fa-search"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <!-- /btn-group -->
                            </div>
                            <!-- /.세트 -->
                            <!-- /.form-group -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /검색유형/검색항목/공개여뷰/검색어-->
            <!-- 탭 구현설정 -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">앱 템플릿</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" id="AppTemplate" data-widget="collapse"><i
                                class="fa fa-minus"></i>
                        </button>
                    </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="">
                    <div id="starterListMessageArea" class="table-responsive">
                        <table id="starterListTableArea" class="table no-margin">
                            <thead>
                            <tr>
                                <th>이름</th>
                                <th>요약</th>
                                <th>분류</th>
                                <th>공개</th>
                            </tr>
                            </thead>
                            <tbody id="starterList"></tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                </div>
                <form id="starterHiddenForm">
                    <input type="hidden" id="starterHiddenFormNo" name="no" value=""/>
                </form>
                <!-- /.box-body -->
                <div class="box-footer clearfix" style="">
                    <button type="button" class="btn btn-sm btn-primary btn-flat pull-left"
                            onclick="procMovePage(STARTER_INSERT_FORM_URL, '<%= Constants.CUD_C %>');">
                        앱 템플릿 등록
                    </button>
                </div>
                <!-- /.box-footer -->
            </div>

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">앱 개발환경</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" id="AppDevelopmentEnvironment"
                                data-widget="collapse"><i class="fa fa-minus"></i>
                        </button>
                    </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="">
                    <div class="table-responsive">
                        <table class="table no-margin">
                            <thead>
                            <tr>
                                <th>이름</th>
                                <th>요약</th>
                                <th>분류</th>
                                <th>공개</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                </div>
                <!-- /.box-body -->
                <div class="box-footer clearfix" style="">
                    <a href="#" class="btn btn-sm btn-primary btn-flat pull-left">앱 개발환경 등록</a>
                </div>
                <!-- /.box-footer -->
            </div>

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">서비스</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" id="AppService" data-widget="collapse"><i
                                class="fa fa-minus"></i>
                        </button>
                    </div>
                </div>
                <div class="box-body" style="">
                    <!-- /.box-header -->
                    <div class="table-responsive">
                        <table class="table no-margin">
                            <thead>
                            <tr>
                                <th>이름</th>
                                <th>요약</th>
                                <th>분류</th>
                                <th>공개</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                </div>
                <!-- /.box-body -->
                <div class="box-footer clearfix" style="">
                    <a href="#" class="btn btn-sm btn-primary btn-flat pull-left">서비스 등록</a>
                </div>
                <!-- /탭 설정 -->
            </div>
            <!-- /.box -->
        </div>
        <!-- Modal Layer-->
        <div class="modal fade" id="modal-default" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span></button>
                        <h4 class="modal-title">Default Modal</h4>
                    </div>
                    <div class="modal-body">
                        <p>One fine body…</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- Modal Layer-->
</section>
<!-- /.content -->
<!-- ===================================================================================== -->

<script type="text/javascript">

    var TAB_NAME = "<c:out value='${TAB_NAME}' default='' />";

    $(document.body).ready(function () {

        procSelectTab(TAB_NAME);
    });


    // SELECT TAB
    var procSelectTab = function (reqTabName) {
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
    var procCheckSearchFormKeyEvent = function (e) {
        if (e.keyCode == 13 && e.srcElement.type != 'textarea') {
            procGetSearchList();
        }
    };


    // GET SEARCH LIST
    var procGetSearchList = function () {
        var doc = document;
        var reqSearchType = doc.getElementById('searchType').value;
        var reqSearchTypeColumn = doc.getElementById('searchTypeColumn').value;
        var reqSearchTypeUseYn = doc.getElementById('searchTypeUseYn').value;
        var reqSearchKeyword = doc.getElementById('searchKeyword').value.toLowerCase();

        var param = {
            searchKeyword: reqSearchKeyword,
            searchTypeColumn: reqSearchTypeColumn,
            searchTypeUseYn: reqSearchTypeUseYn
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
    $("#btnSearch").on("click", function () {
        procGetSearchList();
    });


    $(document.body).ready(function () {
        getStarterList();
        getBuildPackList();
        getServicePackList();
    });

</script>

<%@ include file="./common/footer_LTE.jsp" %>
