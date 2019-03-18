<!--
=================================================================
* 시  스  템  명 : PaaS-TA 관리자 포탈
* 업    무    명 : 메뉴
* 프로그램명(ID) : menuMain.jsp(메뉴관리)
* 프로그램  개요 : 메뉴 조회, 등록, 수정, 삭제하는 화면
* 작    성    일 : 2016.09.29
=================================================================
수정자 / 수정일 :
수정사유 / 내역 :
=================================================================
-->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp"%>

<div class="col-sm-6 pt30">
    <h4 class="modify_h4 fwn"> 메뉴 관리 </h4>
</div>

<%--FOR TREE--%>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/dhtmlxtree.css'/>">
<script type="text/javascript" src="<c:url value='/resources/js/dhtmlxtree.js' />"></script>

<div class="panel content-box col-sm-12 col-md-12 mt-50 col-md-offset-13 w98" >
   <%-- <h2> 메뉴 </h2>--%>

    <div class="col-sm-4 fl" style="display: block; position: relative;">
        <div class="fr">
            <button type="button" class="btn btn-point btn-sm" id="btnAddMenuItem"> 메뉴 추가 </button>
        </div>
        <div class="search_box" style="display: block; position: relative; padding: 20px; margin: 50px 0; border: solid 1px #dddddd; min-height: 600px; height: 600px; overflow: hidden;">
            <div id="treeBox"></div>
        </div>
    </div>

    <div class="col-sm-8 fl">
        <form id="menuForm" name="menuForm" role="form">
            <div id="menuDetailArea" style="display: none;">
                <div class="form-group">
                    <label class="control-label col-sm-2 pt10 pb10" for="menuName"> 메뉴 이름 </label>
                    <div class="col-sm-10 pt10 pb10">
                        <div class="col-sm-11 fl">
                            <input type="text" maxlength="100" class="form-control toCheckString" id="menuName" name="menuName">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2 pt10 pb10" for="menuPath"> 메뉴 URL </label>
                    <div class="col-sm-10 pt10 pb10">
                        <div class="col-sm-10 fl">
                            <input type="text" maxlength="200" class="form-control toCheckString" id="menuPath" name="menuPath">
                        </div>
                        <div class="col-sm-1 fl">
                            <label for="openWindowYn"> 새 창 </label>
                        </div><div class="col-sm-1 fr">
                            <input type="checkbox" class="form-control" id="openWindowYn" name="openWindowYn">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2 pt10 pb10" for="sortNo"> 배치 순서 </label>
                    <div class="col-sm-10 pt10 pb10">
                        <div class="col-sm-11 fl">
                            <input type="text" maxlength="100" class="form-control toCheckString" id="sortNo" name="sortNo">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2 pt10 pb10" for="loginYn_Y"> 로그인 여부 </label>
                    <div class="col-sm-10 pt10 pb10">
                        <div class="col-sm-11 fl">
                            <label class="radio-inline">
                                <input type="radio" id="loginYn_Y" name="loginYn" value="<%= Constants.USE_YN_Y %>" checked> Y
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="loginYn_N" name="loginYn" value="<%= Constants.USE_YN_N %>"> N
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2 pt10 pb10" for="useYn_Y"> 사용 여부 </label>
                    <div class="col-sm-10 pt10 pb10">
                        <div class="col-sm-11 fl">
                            <label class="radio-inline">
                                <input type="radio" id="useYn_Y" name="useYn" value="<%= Constants.USE_YN_Y %>" checked> Y
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="useYn_N" name="useYn" value="<%= Constants.USE_YN_N %>"> N
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-12" align="right">
                        <div class="divButtons">
                            <button type="button" class="btn btn_del2 btn-sm" id="btnDelete"> 삭제 </button>
                            <button type="button" class="btn btn-save btn-sm" id="btnSave"> 저장 </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="cl_b"></div>


<%--HIDDEN VALUE--%>
<input type="hidden" id="menuNo" name="menuNo" value="" />
<input type="hidden" id="orgSortNo" name="orgSortNo" value="" />


<%--
====================================================================================================
SCRIPT BEGIN
====================================================================================================
--%>


<script type="text/javascript">

    var LIST_MAX_NO_PROC_URL = "<c:url value='/menu/getMenuMaxNoList' />";
    var LIST_PROC_URL = "<c:url value='/menu/getMenuList' />";
    var DETAIL_PROC_URL = "<c:url value='/menu/getMenuDetail' />";
    var INSERT_PROC_URL = "<c:url value='/menu/insertMenu' />";
    var UPDATE_PROC_URL = "<c:url value='/menu/updateMenu' />";
    var DELETE_PROC_URL = "<c:url value='/menu/deleteMenu' />";
    var MENU_SORT_NO_RESULT_LIST = [];
    var INIT_MENU_NAME = "MENU";
    var adminMenuTree;


    // GET LIST
    var getMenuList = function() {
        procCallAjax(LIST_PROC_URL, {}, procCallbackGetMenuList);
    };


    // GET LIST CALLBACK
    var procCallbackGetMenuList = function(data) {
        if (RESULT_STATUS_FAIL == data.RESULT) return false;
        procSetMenuTree(data.RESULT_DATA);
    };


    // SET MENU TREE
    var procSetMenuTree = function(reqItems) {
        adminMenuTree = new dhtmlXTreeObject({
            parent: "treeBox",
            image_path: "<c:url value='../../../resources/images/dhxtree/imgs/dhxtree_web/' />"
        });

        adminMenuTree.enableDragAndDrop(true);
        adminMenuTree.setDragBehavior('complex');
        adminMenuTree.enableItemEditor(true);
        adminMenuTree.setDataMode("json");
        adminMenuTree.parse(reqItems, "json");

        $('.containerTableStyle').css('height', '');
        $('.containerTableStyle table').css('border', '0');

        // ON SELECT IN TREE
        adminMenuTree.attachEvent("onSelect", function(id) {
            var menuNoObject = document.getElementById('menuNo');
            if (-1 == id) {
                menuNoObject.value = 0;
            } else {
                menuNoObject.value = id;
                getMenuDetail(id);
            }

            $('#menuDetailArea').fadeOut('fast');

            return true;
        });

        // ON EDIT IN TREE
        adminMenuTree.attachEvent("onEdit", function (state, id, tree, value) {
            if (-1 == id) return false;

            if (2 == state && -1 != id) {
                var doc = document;
                doc.getElementById('menuNo').value = id;
                doc.getElementById('menuName').value = value;

                procUpdateTreeMenuName(id, value);
            }

            return true;
        });

        // ON DRAG IN TREE
        adminMenuTree.attachEvent("onDrag", function(sId, tId, id, sObject, tObject){
            if (-1 == id) return false;
            if (0 == tId) {
                procAlert("danger", '<spring:message code="menu.validation.error.message" />');
                return false;
            } else {
                return true;
            }
        });

        // ON DROP IN TREE
        adminMenuTree.attachEvent("onDrop", function(sId, tId, id, sObject, tObject){
            if (-1 == id) return false;
            procSortMenuItems();
            return true;
        });

    };


    // GET DETAIL
    var getMenuDetail = function(reqNo) {
        if (!procCheckValidNull(reqNo)) return false;
        procCallAjax(DETAIL_PROC_URL, {no: reqNo}, procCallbackGetMenuDetail);
    };


    // GET DETAIL CALLBACK
    var procCallbackGetMenuDetail = function(data) {
        if (RESULT_STATUS_FAIL == data.RESULT) return false;
        procSetMenuDetail(data.RESULT_DATA[0]);
    };


    // SET MENU DETAIL AREA
    var procSetMenuDetail = function(reqData) {
        var sortNo = reqData.sortNo;

        $('#menuName').val(reqData.menuName);
        $('#menuPath').val(reqData.menuPath);
        $('#sortNo').val(sortNo);
        $('#openWindowYn').prop('checked', ('<%= Constants.USE_YN_Y %>' ==  reqData.openWindowYn));
        $('#loginYn_' + reqData.loginYn).prop('checked', true);
        $('#useYn_' + reqData.useYn).prop('checked', true);

        document.getElementById('orgSortNo').value = sortNo;

        $('#menuDetailArea').fadeIn('fast');
    };


    // UPDATE
    var procUpdateTreeMenuName = function(reqNo, reqMenuName) {
        if (!procCheckValidNull(reqNo)) return false;

        var tempParentNo = adminMenuTree.getParentId(reqNo);
        var reqParentNo = (-1 == tempParentNo)? 0 : tempParentNo;

        var param = {
            no: reqNo,
            parentNo: reqParentNo,
            menuName: reqMenuName
        };

        procCallAjax(UPDATE_PROC_URL, param, procCallbackUpdateTreeMenuName);
    };


    // UPDATE CALLBACK
    var procCallbackUpdateTreeMenuName = function(data) {
        if (RESULT_STATUS_SUCCESS != data.RESULT) procAlert("danger", data.RESULT_MESSAGE);
    };


    // ADD MENU
    var procAddMenuItem = function() {
        var selectedItemId = adminMenuTree.getSelectedItemId();
        if ('' == selectedItemId || null == selectedItemId) selectedItemId = -1;

        procInsert(selectedItemId);
    };


    // INSERT
    var procInsert = function(reqParentNo) {
        if (!procCheckValidNull(reqParentNo)) return false;

        var parentNo = (-1 == reqParentNo)? 0 : reqParentNo;
        var param = {
            parentNo: parentNo,
            menuName: INIT_MENU_NAME,
            menuPath: "#",
            sortNo: adminMenuTree.hasChildren(reqParentNo) + 1,
            openWindowYn: "<%= Constants.USE_YN_N %>",
            loginYn: "<%= Constants.USE_YN_Y %>",
            useYn: "<%= Constants.USE_YN_Y %>"
        };

        procCallAjax(INSERT_PROC_URL, param, procCallbackInsert);
    };


    // INSERT CALLBACK
    var procCallbackInsert = function(data) {
        if (RESULT_STATUS_SUCCESS == data.RESULT) {
            procAlert("success", '<spring:message code="common.info.result.success" />');
            getMenuMaxNoList();
        } else {
            procAlert("danger", data.RESULT_MESSAGE);
        }
    };


    // GET LIST
    var getMenuMaxNoList = function() {
        procCallAjax(LIST_MAX_NO_PROC_URL, {}, procCallbackGetMenuMaxNoList);
    };


    // GET LIST CALLBACK
    var procCallbackGetMenuMaxNoList = function(data) {
        if (RESULT_STATUS_FAIL == data.RESULT) return false;
        procSetInsertNewItem(data.MAX_NO);
    };


    // SET INSERT NEW ITEM
    var procSetInsertNewItem = function(reqMaxNo) {
        if (!procCheckValidNull(reqMaxNo)) return false;

        var menuTree = adminMenuTree;
        var selectedItemId = menuTree.getSelectedItemId();
        if ('' == selectedItemId || null == selectedItemId) selectedItemId = -1;

        menuTree.insertNewItem(selectedItemId, reqMaxNo, INIT_MENU_NAME, 0, 0, 0, 0, "SELECT,CHILD");

        selectedItemId = menuTree.getSelectedItemId();
        document.getElementById('menuNo').value = selectedItemId;
        menuTree.editItem(selectedItemId);

        getMenuDetail(selectedItemId);
    };


    // UPDATE
    var procUpdate = function(reqNo) {
        if (!procCheckValidStringSpace()) return false;

        var doc = document;
        var menuTree = adminMenuTree;
        var reqOpenWindowYn = ($("input:checkbox[name='openWindowYn']:checked").val() == 'on') ? '<%= Constants.USE_YN_Y %>' : '<%= Constants.USE_YN_N %>';
        var reqLoginYn = $("input:radio[name='loginYn']:checked").val();
        var reqUseYn = $("input:radio[name='useYn']:checked").val();
        var tempParentNo = menuTree.getParentId(reqNo);
        var reqParentNo = (-1 == tempParentNo)? 0 : tempParentNo;
        var reqMenuName = doc.getElementById('menuName').value;

        var param = {
            no: reqNo,
            parentNo: reqParentNo,
            menuName: reqMenuName,
            menuPath: doc.getElementById('menuPath').value,
            sortNo: doc.getElementById('sortNo').value,
            openWindowYn: reqOpenWindowYn,
            loginYn: reqLoginYn,
            useYn: reqUseYn
        };

        menuTree.setItemText(reqNo, reqMenuName);
        procCallAjax(UPDATE_PROC_URL, param, procCallbackUpdate);
    };


    // UPDATE CALLBACK
    var procCallbackUpdate = function(data, reqParam) {
        if (RESULT_STATUS_SUCCESS == data.RESULT) {
            procAlert("success", '<spring:message code="common.info.result.success" />');
            procUpdateSortMenuItems(reqParam.no);
        } else {
            procAlert("danger", data.RESULT_MESSAGE);
        }
    };


    // UPDATE SORT MENU ITEMS
    var procUpdateSortMenuItems = function(reqNo) {
        if (!procCheckValidNull(reqNo)) return false;

        var doc = document;
        var menuTree = adminMenuTree;
        var parentNo = menuTree.getParentId(reqNo);
        var subItemList = menuTree.getSubItems(parentNo);
        var subItemListArray = subItemList.split(',');
        var listLength = subItemListArray.length;
        var sortNo = parseInt(doc.getElementById('sortNo').value, 10);
        var orgSortNo = parseInt(doc.getElementById('orgSortNo').value, 10);

        if (listLength < sortNo) sortNo = listLength;

        for (var i = 0; i < listLength; i++) {
            if ((i + 1) == sortNo) {
                if (orgSortNo > sortNo) {
                    menuTree.moveItem(reqNo, "item_sibling", subItemListArray[i]);
                } else {
                    menuTree.moveItem(reqNo, "item_sibling_next", subItemListArray[i]);
                }
            }
        }
    };


    // CHECK DELETE
    var procCheckDelete = function() {
        $('div.modal').modal('toggle');

        var reqNo = document.getElementById('menuNo').value;
        if (0 < adminMenuTree.hasChildren(reqNo)) {
            procAlert("danger", "하위 메뉴가 존재합니다. 하위 메뉴를 먼저 삭제하십시오.");
        } else{
            procDelete(reqNo);
        }
    };


    // DELETE
    var procDelete = function(reqNo) {
        if (!procCheckValidNull(reqNo)) return false;

        adminMenuTree.deleteItem(reqNo, false);
        $('#menuDetailArea').fadeOut('fast');

        procCallAjax(DELETE_PROC_URL, {no: reqNo}, procCallbackDelete);
    };


    // DELETE CALLBACK
    var procCallbackDelete = function(data) {
        if (RESULT_STATUS_SUCCESS != data.RESULT) procAlert("danger", data.RESULT_MESSAGE);
    };


    // SORT MENU ITEMS
    var procSortMenuItems = function() {
        var resultList = procGetRequestSortMenuList(-1);
        var listLength = resultList.length;
        var resultListArray = [];
        var tempParentNo;
        var reqParentNo;
        var param = {};

        for (var i = 0; i < listLength; i++) {
            resultListArray = resultList[i].split("<%= Constants.STRING_SEPARATOR %>");
            tempParentNo = adminMenuTree.getParentId(resultListArray[0]);
            reqParentNo = (-1 == tempParentNo)? 0 : tempParentNo;

            param = {
                no: resultListArray[0],
                parentNo: reqParentNo,
                sortNo: resultListArray[1]
            };

            procCallAjax(UPDATE_PROC_URL, param, procCallbackUpdateSortNo);
        }

        MENU_SORT_NO_RESULT_LIST = [];
        $('#menuDetailArea').fadeOut('fast');

        getMenuDetail(document.getElementById('menuNo').value);
    };


    // UPDATE CALLBACK
    var procCallbackUpdateSortNo= function(data) {
        if (RESULT_STATUS_SUCCESS != data.RESULT) procAlert("danger", data.RESULT_MESSAGE);
    };


    // GET REQUEST SORT MENU LIST
    var procGetRequestSortMenuList = function(reqNo) {
        var subItemList = adminMenuTree.getSubItems(reqNo);
        var subItemListArray = subItemList.split(',');
        var listLength = subItemListArray.length;

        for (var i = 0; i < listLength; i++) {
            if (0 < adminMenuTree.hasChildren(subItemListArray[i])) procGetRequestSortMenuList(subItemListArray[i]);
            MENU_SORT_NO_RESULT_LIST.push(subItemListArray[i] + "<%= Constants.STRING_SEPARATOR %>" + (i + 1));
        }

        MENU_SORT_NO_RESULT_LIST.join(',');

        return MENU_SORT_NO_RESULT_LIST;
    };


    // BIND :: BUTTON EVENT
    $("#btnAddMenuItem").on("click", function() {
        procAddMenuItem();
    });


    // BIND :: BUTTON EVENT
    $("#btnDelete").on("click", function() {
        procPopup('메뉴', DELETE_MESSAGE, 'procCheckDelete();');

    });


    // BIND :: BUTTON EVENT
    $("#btnSave").on("click", function() {
        procUpdate(document.getElementById('menuNo').value);
    });


    // INIT PAGE
    var procInitPage = function() {
        getMenuList();
    };


    // ON LOAD
    $(document.body).ready(function() {
        procInitPage();

    });

</script>


<%--
====================================================================================================
SCRIPT END
====================================================================================================
--%>


<%--FOOTER--%>
<%@ include file="../common/footer.jsp"%>
