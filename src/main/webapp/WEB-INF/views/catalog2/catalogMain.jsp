<%--CONSTANT--%>
<%@ page import="org.openpaas.paasta.portal.web.admin.common.Constants" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common_LTE.jsp" %>
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
                                <select id="searchType" name="searchType"
                                        class="form-control select2 select2-hidden-accessible" style="width: 100%;"
                                        tabindex="-1" aria-hidden="true">
                                    <option selected="selected" value="<%= Constants.SEARCH_TYPE_ALL %>"> 전체</option>
                                    <option value="<%= Constants.TAB_NAME_STARTER %>"> 앱 템플릿</option>
                                    <option value="<%= Constants.TAB_NAME_BUILD_PACK %>"> 앱 개발환경</option>
                                    <option value="<%= Constants.TAB_NAME_SERVICE_PACK %>"> 서비스</option>
                                </select>
                                <span class="select2 select2-container select2-container--default select2-container--below"
                                      dir="ltr" style="width: 100%;">
                                    <span class="selection"></span>
                                    <span class="dropdown-wrapper" aria-hidden="true"></span>
                                </span>
                            </div>
                            <!-- /.form-group -->
                            <div class="selectbox select2">
                                <label for="searchTypeColumn">검색항목</label>
                                <select id="searchTypeColumn" name="searchTypeColumn"
                                        class="form-control select2 select2-hidden-accessible" style="width: 100%;"
                                        tabindex="-1" aria-hidden="true">
                                    <option selected="selected" value="<%= Constants.SEARCH_TYPE_ALL %>"> 전체</option>
                                    <option value="<%= Constants.SEARCH_TYPE_NAME %>"> 이름</option>
                                    <option value="<%= Constants.SEARCH_TYPE_SUMMARY %>"> 요약</option>
                                </select>
                                <span class="select2 select2-container select2-container--default select2-container--below"
                                      dir="ltr" style="width: 100%;">
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
                                <select id="searchTypeUseYn" name="searchTypeUseYn"
                                        class="form-control select2 select2-hidden-accessible" style="width: 100%;"
                                        tabindex="-1" aria-hidden="true">
                                    <option selected="selected" value="<%= Constants.SEARCH_TYPE_ALL %>"> 전체</option>
                                    <option value="<%= Constants.USE_YN_Y %>"> 공개</option>
                                    <option value="<%= Constants.USE_YN_N %>"> 비공개</option>
                                </select>
                                <span class="select2 select2-container select2-container--default select2-container--below"
                                      dir="ltr" style="width: 100%;">
                                    <span class="selection"></span>
                                    <span class="dropdown-wrapper" aria-hidden="true"></span>
                                </span>
                            </div>
                            <!-- /.form-group -->
                            <div class="select select4">
                                <label for="searchTypeColumn">검색어</label>
                                <div class="box-tools">
                                    <div class="input-group input-group-sm">
                                        <input type="text" id="searchKeyword" class="form-control pull-right"
                                               placeholder="검색어를 입력하세요"
                                               onkeypress="procCheckSearchFormKeyEvent(event);">
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
                    <!-- [_]창)-->
                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse">
                            <a href="#STARTER" data-toggle="tab" onclick="procSelectTab('STARTER')"
                               aria-expanded="true"></a>
                            <i class="fa fa-minus"></i>
                        </button>
                    </div>
                </div>
                <!-- /.box-header -->
                <%@ include file="../catalog2/starterTab.jsp" %>
                <!-- /.box-body -->

                <!-- /.box-footer -->
            </div>

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">앱 개발환경</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse">
                            <a href="#BUILD_PACK" data-toggle="tab" onclick="procSelectTab('BUILD_PACK')"
                               aria-expanded="true"></a>
                            <i class="fa fa-minus"></i>
                        </button>
                    </div>
                </div>
                <!-- /.box-header -->
                <%@ include file="../catalog2/buildPackTab.jsp" %>
                <!-- /.box-body -->
                <!-- /.box-footer -->
            </div>

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">서비스</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse">
                            <a href="#SERVICE_PACK" data-toggle="tab" onclick="procSelectTab('SERVICE_PACK')"
                               aria-expanded="true"></a>
                            <i class="fa fa-minus"></i>
                        </button>
                    </div>
                </div>
                <div class="box-body" style="">
                    <!-- /.box-header -->
                    <%@ include file="../catalog2/servicePackTab.jsp" %>
                    <!-- /.table-responsive -->
                </div>
                <!-- /.box-body -->
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
    </div>
</section>
<!-- /.content -->
<!-- ===================================================================================== -->

<script type="text/javascript">

</script>


<%@ include file="../common/footer_LTE.jsp" %>
