<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp"%>
<script>
    var GET_AUTHORITY_GROUPS_URL = "<c:url value='/authority/getAuthorityGroups'/>";
    var GET_GROUP_MEMBERS_INFO_URL = "<c:url value='/authority/getGroupMembersInfo'/>";
    var CREATE_AUTHORITY_GROUP_URL = "<c:url value='/authority/createAuthorityGroup'/>";
    var DELETE_AUTHORITY_GROUP_URL = "<c:url value='/authority/deleteAuthorityGroup'/>";
    var ADD_AUTHORITY_GROUP_MEMBERS_URL = "<c:url value='/authority/addGroupMembers'/>";
    var DELETE_AUTHORITY_GROUP_MEMBERS_URL = "<c:url value='/authority/deleteGroupMembers'/>";
    var GET_USER_INFO = "<c:url value='/authority/getUserInfo'/>";

    var userInfo;
    var autoCompleteIndex;

    $(document).ready(function(){
        getAuthGroups(setAuthGroupTable)
        getUserInfo(setUserInfo)

        $("#user-add-name-input").keyup(function(event) {
            switch (event.which){
                case 8: var isDeletingInput = true; userNameAutoComplete(isDeletingInput);break; //백스페이스
                case 13: pushEnter(); break; //엔터
                case 38: break; //방향키(상)
                case 40: break; //방향키(하)
                default: var isDeletingInput = false; delayAutoComplete(isDeletingInput); break;
            }
        });

        $("#user-add-name-input").keydown(function(event) {
            switch (event.which){
                case 38: moveAutoCompleteKey("up"); break; //방향키(상)
                case 40: moveAutoCompleteKey("down"); break; //방향키(하)
                default: break;
            }
        });

    });





    function getAuthGroups(callback){
        procCallAjax(GET_AUTHORITY_GROUPS_URL, {}, callback)
    }

    function getGroupMembersInfo(groupGuid, callback){
        procCallAjax(GET_GROUP_MEMBERS_INFO_URL, {"groupGuid":groupGuid}, callback)
    }

    function createAuthorityGroup(groupToCreate, callback) {
        procCallAjax(CREATE_AUTHORITY_GROUP_URL, groupToCreate, callback);
    }

    function deleteAuthorityGroup() {
        var groupGuid = getCheckedGroup().val().groupGuid;
        procCallAjax(DELETE_AUTHORITY_GROUP_URL, {"groupGuid":groupGuid}, refreshGroupArea);
        procClosePopup()
    }

    function addAuthorityGroupMembers(groupGuid, memberUserNameList, callback){
        procCallAjax(ADD_AUTHORITY_GROUP_MEMBERS_URL, {"groupGuid":groupGuid, "memberUserNameList":memberUserNameList}, callback);
    }

    function deleteAuthorityGroupMembers(){
        var groupGuid = getCheckedGroup().val().groupGuid;
        var memberUserNameList = getCheckedMemberList();
        procCallAjax(DELETE_AUTHORITY_GROUP_MEMBERS_URL, {"groupGuid":groupGuid, "memberUserNameList":memberUserNameList}, refreshUserArea);
        procClosePopup()
    }

    function getUserInfo(callback){
        procCallAjax(GET_USER_INFO, {}, callback);
    }

    function refreshGroupArea(){
        $("#group-searchKeyword").val("");
        $("#group-create-name-input").val("");
        $("#createAuthorityGroupBox").hide();
        getAuthGroups(setAuthGroupTable)
    }

    function refreshUserArea(){
        $("#user-add-name-input").val("");
        $("#addAuthorityGroupMemeberBox").hide();
        refreshUserTable()
    }

    function refreshUserTable(){
        getAuthGroups(refreshGroupData)
    }

    function getGroupWithGuid(data, groupGuid){
        if(data.groups.length && data.groups.length > 0){
            $.each(data.groups, function (eventId, group) {
                if(group.id === groupGuid){
                    setGroupData($(":checkbox[name='groupTable-checkbox']:checked").val(), group);
                }
            });
        }
        setUserTable();
    }

    function refreshGroupData(data){
        var groupGuid = getCheckedGroup().val().groupGuid;
        getGroupWithGuid(data, groupGuid);
    }


    function setAuthGroupTable(data) {
        $("#groupTable-contents *").remove()

        if(data.groups.length && data.groups.length > 0) {
            $.each(data.groups, function (eventId, group) {
                $("#groupTable-contents").append(
                    "<div id='groupTable-group-no-" + eventId + "' class='mainViewArea-table-content' name='" + group.displayName + "' onclick=selectGroup('" + eventId + "')>" +
                    "<div class='table-content'>" +
                    "<div class='form-group'>" +
                    "<label><input name='groupTable-checkbox' id='groupTable-checkbox-no-"+eventId+"' value='"+eventId+"' class='checkbox-inline' type='checkbox' style='display: none'></label>"+
                    "<span style='margin-left: 10px'>" + group.displayName + "</span>"+
                    "</div>"+
                    "</div>"+
                    "</div>"
                )
                setGroupData(eventId,group)
            });
            showViewAreaContent("group","groupTable")
        } else {
            showViewAreaContent("group","noGroupMessage")
        }
        disableBtn("groupArea-delete-group-btn",true)
    }

    function setGroupData(eventId, group){
        var groupData = {"groupGuid":group.id, "groupName":group.displayName, "groupMembers": group.members}
        $("#groupTable-group-no-"+eventId).val(groupData)
    }


    function selectGroup(groupNo) {
        clickAddAuthorityGroupMembersBtn('HIDE');
        contentSelectControl("group", groupNo);

        if($("#groupTable-checkbox-no-"+groupNo).is(":checked") === true){
            disableBtn("groupArea-delete-group-btn", false)
            setUserTable(groupNo)

        } else {
            disableBtn("groupArea-delete-group-btn", true)
            showViewAreaContent("user","noContent");
        }
    }


    function selectUser(userNo) {

        contentSelectControl("user", userNo);
        if($(":checkbox[name='userTable-checkbox']:checked").length > 0){
            disableBtn("userArea-delete-user-btn", false)
        } else{
            disableBtn("userArea-delete-user-btn", true)
        }


    }

    function searchAuthorityGroup() {
        var input = $("#group-searchKeyword").val().toLowerCase();
        if(input === ""){
            $("#groupTable div[id*='groupTable-group-no-']").show()
            showViewAreaContent("group","groupTable")
        } else if($("#groupTable div[name*='"+input+"']").length > 0) {
            $("#groupTable div[id*='groupTable-group-no-']:not([name*='"+input+"'])").hide()
            $("#groupTable div[id*='groupTable-group-no-'][name*='"+input+"']").show()
            showViewAreaContent("group","groupTable")
        } else if($("#groupTable div[name*='"+input+"']").length === 0) {
            showViewAreaContent("group","noSearchResult")
        }
    }

    
    function setUserTable() {
        $("#userTable-contents *").remove();
        $("#user-add-name-input").val("");
        userNameAutoComplete()

        var currentCheckedGroup = getCheckedGroup();
        var memberNameList = filteredGroupMembers(currentCheckedGroup)

        if(memberNameList.length > 0) {
            $.each(memberNameList, function (eventId, member) {
                $("#userTable-contents").append(
                    "<div id='userTable-user-no-" + eventId + "' class='mainViewArea-table-content' name='" + member.userName + "' onclick=selectUser('" + eventId + "')>" +
                    "<div class='table-content'>"+
                    "<div class='form-group'>" +
                    "<label><input name='userTable-checkbox' id='userTable-checkbox-no-"+eventId+"' value='"+eventId+"' class='checkbox-inline' type='checkbox' style='width:15px; height:15px;'></label>"+
                    "<span style='margin-left: 15px'>" + member.userName + "</span>"+
                    "</div>"+
                    "</div>"+
                    "</div>"
                )
                var userData = {"userName":member.userName}
                $("#userTable-user-no-"+eventId).val(userData)
            });
            setCheckboxClickEvent()
            showViewAreaContent("user","userTable")
        } else {
            showViewAreaContent("user","noUser")
            disableBtn("userArea-delete-user-btn", true);
        }
    }

    function setCheckboxClickEvent(){
        $("input[id*='userTable-checkbox-no-']").click(function(e) {
            var elementNo = this.id.split("-")[3];
            selectUser(elementNo)
        });
    }

    function filteredGroupMembers(currentCheckedGroup){
        var groupMembers =  currentCheckedGroup.val().groupMembers
        var filteredGroupMembers = new Array();

        if(groupMembers){
            $.each(userInfo, function (eventId, member) {
                $.each(groupMembers, function(memberNo, groupMember) {

                    if(member.userguid === groupMember.value){
                        filteredGroupMembers.push({"userName" : member.username, "userGuid": groupMember.value})
                    }
                });
            });
        }

        return filteredGroupMembers;
    }


    function clickCreateAuthorityGroupBtn() {
        var objName = $("#group-create-name-input");
        var reqName = objName.val();
        if (!procCheckValidStringSpace(reqName)) {
            inputBoxControl("group-create-name-input", "warning", "warning");
            procAlert("danger", '<spring:message code="common.info.empty.req.data" />');
            objName.focus();
            return false;
        }

        createAuthorityGroup({"displayName":reqName}, refreshGroupArea)
    }


    function clickAddAuthorityGroupMembersBtn(reqStatus){
        if (undefined != reqStatus || null != reqStatus) {
            $('#addAuthorityGroupMemeberBox').hide();
        } else {
            showAddBox('addAuthorityGroupMemeberBox')
        }

        $("#userName-auto-complete-area *").remove();

        $.each(userInfo, function (eventId, member) {
            $("#userName-auto-complete-area").append(
                    "<div id='userName-auto-complete-element-'"+member.username+" class='auto-complete-element' value='"+member.username+"' >"+member.username+"</div>"
            )
        });
    }


    function createGroupValidation() {
        createGroupDuplicationCheck()
    }


    function createGroupDuplicationCheck() {
        var input = $("#group-create-name-input").val();
        if(!procCheckValidStringSpace(input)){
            //값이 빈값인 경우
            inputBoxControl("group-create-name-input", "warning", "warning");
        } else if($("#groupTable div[id*='groupTable-group-no-'][name='"+input+"']").length > 0) {
            //중복되는 이름이 있는 경우
            inputBoxControl("group-create-name-input", "error", "duplicated");
        } else {
            //중복되는 이름이 없는 경우
            inputBoxControl("group-create-name-input", "ok", "ok");
        }
    }




    function clickDeleteAuthorityGroupBtn(){
        if($(":checkbox[name='groupTable-checkbox']:checked").length === 1){

            procPopup("그룹 삭제","UAA 권한 그룹을 삭제하면 문제가 생길 수 있습니다. "+getCheckedGroup().val().groupName+"그룹을 삭제 하시겠습니까?",
                    "deleteAuthorityGroup()","삭제")
        }
    }


    function contentSelectControl(table, elementNo){
        if($("#"+table+"Table-checkbox-no-"+elementNo).is(":checked") === true) {
            $("#"+table+"Table-checkbox-no-"+elementNo).prop ("checked", false)
            if(table === "user"){
                var contentId = table+"Table-"+table+"-no-"+elementNo
                contentClassNameCategorize(elementNo ,contentId)
            } else {
                changeTableContentColor(table)
            }

        } else if($("#"+table+"Table-checkbox-no-"+elementNo).is(":checked") === false) {
            if(table === "group") {
                $(":checkbox[name='"+table+"Table-checkbox']").prop("checked", false)
                changeTableContentColor(table)
            }
            $("#"+table+"Table-checkbox-no-"+elementNo).prop ("checked",true)
            document.getElementById(table+"Table-"+table+"-no-"+elementNo).className = "mainViewArea-table-content content-selected"
        }
    }


    function inputBoxControl(element, status, errorDetail){
        document.getElementById(element).className = "form-control form-control-"+status;
        document.getElementById(element+"-icon").className = "form-control-feedback glyphicon status-icon-"+status;
        disableBtnWithInputBox(element, status);
        errorMessageControl(element,status, errorDetail)
    }


    function errorMessageControl(element,status, errorDetail) {
        if(status == "error") {
            if(errorDetail === "duplicated"){
                var errorMessage="이미 존재하는 그룹 이름입니다."
            }

            if($("#"+element+"-statusMessage").is(":hidden")) {
                document.getElementById(element+"-statusMessage").textContent = errorMessage;
                $("#"+element+"-statusMessage").slideDown();
            }

        } else {
            if(!$("#"+element+"-statusMessage").is(":hidden")) {
                $("#"+element+"-statusMessage").slideUp();
            }
        }
    }


    function setUserInfo(data){
        userInfo = data.userInfo
    }


    function showViewAreaContent(area, toExpose) {
        showViewAreaContent_userContent("noContent");
        switch (area, toExpose){
            case "group","groupTable":
                $("#noGroupMessage").hide();
                $("#noSearchGroupMessage").hide();
                changeTableContentColor("group");
                $("#groupTable").show();
                break;
            case "group","noGroupMessage":
                $("#groupTable").hide();
                $("#noSearchGroupMessage").hide();
                $("#noGroupMessage").show();
                break;
            case "group","noSearchResult":
                $("#groupTable").hide();
                $("#noSearchGroupMessage").show();
                break;
            case "user", toExpose:
                showViewAreaContent_userContent(toExpose);
                break;
        }
    }


    function showViewAreaContent_userContent(toExpose) {
        switch (toExpose) {
            case "loadingImage":
                $("#userTable").hide();
                $("#noUserMessage").hide();
                $("#loadingImage").show();
                $("#userArea").show();
                break;
            case "userTable":
                $("#loadingImage").hide();
                $("#noUserMessage").hide();
                changeTableContentColor("user");
                $("#userTable").show();
                $("#userArea").show();
                break;
            case "noUser":
                $("#loadingImage").hide();
                $("#userTable").hide();
                $("#noUserMessage").show();
                $("#userArea").show();
                break;
            case "noContent":
                $("#loadingImage").hide();
                $("#userTable").hide();
                $("#noUserMessage").hide();
                $("#userArea").hide();
                break;
        }
    }


    function showAddBox(id) {
        $("#"+id).slideToggle();
    }


    function changeTableContentColor(groupOrUser) {
        var listToShow = $('#'+groupOrUser+'Table div[id*="'+groupOrUser+'Table-'+groupOrUser+'-no-"]:not([style*="display: none"])')
        $.each(listToShow, function (eventId, eventData) {
            contentClassNameCategorize(eventId, eventData.id)
        });
    }


    function contentClassNameCategorize(contentNo, contnetId) {
        if(contentNo%2 === 1) {
            document.getElementById(contnetId).className = "mainViewArea-table-content content-v2"
        } else {
            document.getElementById(contnetId).className = "mainViewArea-table-content"
        }
    }

    function clickAddUsersBtn(){
        //한명의 유저만 등록
        var memberList = [$("#user-add-name-input").val()];
        addAuthorityGroupMembers(getCheckedGroup().val().groupGuid, memberList, refreshUserTable);
    }


    function getCheckedGroup(){
        var checkedGroupNo = $(":checkbox[name='groupTable-checkbox']:checked").val();
        var checkedGroup = $("#groupTable-group-no-"+checkedGroupNo);
        return checkedGroup
    }

    function getCheckedMemberList(){
        var checkedElements = $(":checkbox[name='userTable-checkbox']:checked");

        var checkedMemberList = [];
        //var checkedMemberList = $(":checkbox[name='userTable-checkbox']:checked")
        $.each(checkedElements, function (eventId, checkedUser) {
            checkedMemberList.push($("#userTable-user-no-"+checkedUser.value).val().userName)
        });

        return checkedMemberList;
    }

    function clickDeleteAuthorityGroupMembersBtn(){
        if($(":checkbox[name='userTable-checkbox']:checked").length >= 1){

            procPopup("유저 삭제","UAA 권한 그룹을 삭제하면 문제가 생길 수 있습니다. 유저를 삭제 하시겠습니까?",
                    "deleteAuthorityGroupMembers()","삭제")
        }
    }

    function disableBtnWithInputBox(element, status){
        var buttonId = element+"-save-btn"
        if(status === "ok"){
            $("#"+buttonId).attr("disabled",false)
        } else if(status === "warning"){
            $("#"+buttonId).attr("disabled",true)
        } else if(status === "error"){
            $("#"+buttonId).attr("disabled",true)
        }
    }

    function disableBtn(buttonId, status){
        $("#"+buttonId).attr("disabled",status)
    }

    /**
     *
     * 유저 테이블의 자동완성 관련
     *
     **/

    function moveAutoCompleteKey(direction){
        $("#userName-auto-complete-area div.auto-complete-key-on").removeClass("auto-complete-key-on");
        var exposedDivs = $("#userName-auto-complete-area div:not([style*='display: none'])")
        if(exposedDivs.length > 0){
            //현재 화면에 노출되는 자동완성 요소가 있는 경우
            if(direction == "up"){
                autoCompleteIndex = autoCompleteIndex-1;
            } else if(direction === "down"){
                autoCompleteIndex = autoCompleteIndex+1;
            }
            moveInto(exposedDivs)
        }
    }

    function moveInto(exposedDivs) {
        if(autoCompleteIndex > exposedDivs.length-1){
            autoCompleteIndex = 0
        } else if(autoCompleteIndex < 0){
            autoCompleteIndex = exposedDivs.length-1;
        }

        $("#userName-auto-complete-area div[value='"+exposedDivs.eq(autoCompleteIndex).html()+"']").addClass("auto-complete-key-on")
    }

    function pushEnter() {
        if($(".auto-complete-key-on").length === 0){
            //자동완성 요소중 선택된 값이 없는 경우 ==> 저장
            if($("#user-add-name-input-save-btn").is(':enabled')){
                clickAddUsersBtn();
            }
            autoCompleteIndex = -1;
        } else {
            //자동완성 요소중 선택된 값이 있는 경우 ==> 선택된 값을 검색창에 삽입
            chooseUserName()
        }
    }

    function chooseUserName() {
        var selectedUserName = $("#userName-auto-complete-area div.auto-complete-key-on").html();
        $("#user-add-name-input").val(selectedUserName)
        inputBoxControl("user-add-name-input", "ok", "ok");
        autoCompleteAreaInit()
    }

    function autoCompleteAreaInit() {
        $("#userName-auto-complete-area div.auto-complete-key-on").removeClass("auto-complete-key-on");
        $("#userName-auto-complete-area div").hide();
        autoCompleteIndex = -1;
    }

    function userNameAutoComplete(isDeletingInput) {
        autoCompleteAreaInit()
        var input = $("#user-add-name-input").val();

        if(input === "") {
            //검색어가 없는 경우
            inputBoxControl("user-add-name-input", "warning", "warning");
            $("#userName-auto-complete-area div").hide();

        } else {
            var elements = $("#userName-auto-complete-area div[value*='"+input+"']");
            if(elements.length === 1) {
                var completedUserName = elements.html();
                oneUserNameFound(input,completedUserName,isDeletingInput);
            } else if (elements.length > 1) {
                manyUserNameFound(input, elements)
            } else {
                noUserNameFound();
            }
        }
    }

    function oneUserNameFound(input, completedUserName, isDeletingInput){
        //검색결과가 한개인 경우
        $("#userName-auto-complete-area div[value*='"+input+"']").show();

        if(isDeletingInput){
            //검색결과가 한개만 있더라도 텍스트를 삭제하는 중에는 자동완성을 하지 않는다.
            if($("#userName-auto-complete-area div[value='"+input+"']").length === 1){
                //검색어와 완전히 일치하는 값이 있는 경우
                inputBoxControl("user-add-name-input", "ok", "ok");
            } else {
                inputBoxControl("user-add-name-input", "error", "error");
            }
        } else {
            var inputField = document.getElementById("user-add-name-input")
            inputField.value = completedUserName
            //input 값이 전체 유저네임의 앞부분이 아닐때는 자동완성을 하되 남은 유저네임에 블럭처리 하지 않음
            if(completedUserName.substr(0,input.length) === input){
                inputField.setSelectionRange(input.length, completedUserName.length);
            }
            inputBoxControl("user-add-name-input", "ok", "ok");
        }
    }

    function manyUserNameFound(input, elements){
        //검색결과가 2개 이상인 경우
        if($("#userName-auto-complete-area div[value='"+input+"']").length === 1){
            //검색결과 중 현재 입력된 값과 완전히 일치하는 값이 있는 경우
            inputBoxControl("user-add-name-input", "ok", "ok");
        } else {
            //현재 입력된 값을 가지고 있는 결과값이 2개 이상인 경우
            inputBoxControl("user-add-name-input", "warning", "warning");
        }
        elements.show();
    }

    function noUserNameFound(){
        //검색결과가 없는 경우
        inputBoxControl("user-add-name-input", "error", "error");
        $("#userName-auto-complete-area div").hide();
    }

    function delayAutoComplete(isDeletingInput){
        var currentInput = $("#user-add-name-input").val();
        setTimeout(function () {
            var futureInput = $("#user-add-name-input").val();
            if(currentInput == futureInput){
                userNameAutoComplete(isDeletingInput)
            }
        }, 150)
    }

</script>

<%--TITLE--%>
<div class="col-sm-6 pt30">
    <h4 class="modify_h4 fwn">권한 관리</h4>
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
            <input type="text" id="group-searchKeyword" class="form-control ml3" style="top: -6px; left: -3px; width: 99%;" maxlength="100" placeholder="검색어를 입력하세요." onkeyup="searchAuthorityGroup()">
        </div>
    </div>
</div>

<div class="row">
    <div class="panel content-box col-sm-12 col-md-12 mt-50 col-md-offset-13 w98">
        <div class="col-sm-6 pt5"> </div>

        <div style="margin: 10px 0 0 10px; width:98%; height: 60%;">
            <div class="box" align="center">

                <div class="custom-boxL" style="width: 49.5%; margin: 0 0 0 0;">
                    <div style="width:100%; overflow: hidden; margin-top: 10px">
                        <button type="button" id="groupArea-create-group-btn" class="btn btn-primary btn-sm" style="float:right; margin: 3px" onclick="showAddBox('createAuthorityGroupBox')">
                            그룹 생성
                        </button>
                        <button type="button" id="groupArea-delete-group-btn" class="btn btn-primary btn-sm" style="float:right; margin: 3px;" disabled="true" onclick="clickDeleteAuthorityGroupBtn()">
                            그룹 삭제
                        </button>
                    </div>
                    <div id="createAuthorityGroupBox" class="addBox" style="display: none; width:100%">
                        <div style="float: left; width: 70%;">
                            <div class="form-group has-feedback has-feedback-right" style="margin: 7px">
                                <input id="group-create-name-input" type="text"
                                       class="form-control form-control-warning" placeholder="그룹 이름을 입력하세요."
                                       onkeyup="createGroupValidation()" style="margin-top: -3px">
                                <span id="group-create-name-input-icon" class="form-control-feedback glyphicon status-icon-warning"></span>
                                <div id="group-create-name-input-statusMessage"
                                     style="display: none; font-size:10pt; color: #ca0020; margin-top: 2px; padding-left: 12px">
                                </div>
                            </div>
                        </div>
                        <button type="button" id="group-create-name-input-save-btn" class="btn btn-primary btn-sm" style="margin: 7px; float: left; width: 10%;" disabled="true" onclick="clickCreateAuthorityGroupBtn()">
                            저장
                        </button>
                    </div>

                    <div class="mainViewArea-table" id="groupTable" style="width:100%; display: none; margin-top: 2px">
                        <div id="group-table-title" class="mainViewArea-table-title">
                            <div class="table-content">
                                <span>권한 그룹</span>
                            </div>
                        </div>
                        <div id="groupTable-contents"></div>
                    </div>
                    <div id="noGroupMessage" class='mainViewArea-message' style="display: none; ">생성된 그룹이 없습니다.</div>
                    <div id="noSearchGroupMessage" class='mainViewArea-message' style="display: none;">검색된 그룹이 없습니다.</div>

                </div>

                <div class="custom-boxR" style="width: 49.5%">

                    <div id="userArea" class="userArea" style="display: none">
                        <div style="width:100%; overflow: hidden; margin-top: 10px">
                            <button type="button" id="userArea-add-user-btn" class="btn btn-primary btn-sm" style="float:right; margin: 3px" onclick="clickAddAuthorityGroupMembersBtn()">
                                유저 등록
                            </button>
                            <button type="button" id="userArea-delete-user-btn" class="btn btn-primary btn-sm" style="float:right; margin: 3px" disabled="true" onclick="clickDeleteAuthorityGroupMembersBtn()">
                                유저 삭제
                            </button>
                        </div>

                        <div id="addAuthorityGroupMemeberBox" class="addBox" style="display: none; width:100%">
                            <div style="float: left; width: 70%;">
                                <div class="form-group has-feedback has-feedback-right" style="margin: 7px">
                                    <input id="user-add-name-input" type="text"
                                           class="form-control form-control-warning" style="margin-top: -3px"
                                           placeholder="유저이름을 입력하세요.">
                                    <span id="user-add-name-input-icon" class="form-control-feedback glyphicon status-icon-warning"></span>
                                    <div id="userName-auto-complete-area" class="auto-complete-area"></div>
                                </div>
                            </div>
                            <button type="button" id="user-add-name-input-save-btn" class="btn btn-primary btn-sm" style="margin:7px; float: left; width: 10%;" disabled="true" onclick="clickAddUsersBtn()">
                                저장
                            </button>
                        </div>

                        <div id="userTable" class="mainViewArea-table" style="display: none; width:100%; margin-top: 2px;">
                            <div class="mainViewArea-table-title">
                                <div class="table-content">
                                    <span>사용자</span>
                                </div>
                            </div>

                            <div id="userTable-contents"></div>
                        </div>
                        <div id="noUserMessage" class='mainViewArea-message' style="width: 100%; overflow:hidden; display:none;">등록된 사용자가 없습니다.</div>
                    </div>

                </div>
            </div>

        </div>
    </div>
</div>

<%--
<div class="mainViewArea" id="authorityViewArea">
    <h2> 권한 관리 </h2>
    <div class="authorityArea">
        <label class="control-label sr-only" for="group-searchKeyword"></label>
        <div class="input-group">
            <span class="input-group-addon">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
            </span>
            <input type="text" class="form-control custom-input-text" id="group-searchKeyword" maxlength="50" placeholder="검색어를 입력하세요." onkeyup="searchAthorityGroup()">
        </div>

        <div style="width:100%; overflow: hidden; margin-top: 10px">
            <button type="button" id="groupArea-create-group-btn" class="btn btn-primary btn-sm" style="float:right; margin: 3px" onclick="showAddBox('createAuthorityGroupBox')">
                그룹 생성
            </button>
            <button type="button" id="groupArea-delete-group-btn" class="btn btn-primary btn-sm" style="float:right; margin: 3px;" disabled="true" onclick="clickDeleteAuthorityGroupBtn()">
                그룹 삭제
            </button>
        </div>

        <div id="createAuthorityGroupBox" class="addBox" style="display: none; width:100%">
            <div style="float: left; width: 70%;">
                <div class="form-group has-feedback has-feedback-right" style="margin: 7px">
                    <input id="group-create-name-input" type="text" class="form-control form-control-warning" placeholder="그룹 이름을 입력하세요." onkeyup="createGroupValidation()">
                    <span id="group-create-name-input-icon" class="form-control-feedback glyphicon status-icon-warning"></span>
                    <div id="group-create-name-input-statusMessage"
                         style="display: none; font-size:10pt; color: #ca0020; margin-top: 2px; padding-left: 12px">
                    </div>
                </div>
            </div>
            <button type="button" id="group-create-name-input-save-btn" class="btn btn-primary btn-sm" style="margin: 7px; float: left; width: 10%;" disabled="true" onclick="clickCreateAuthorityGroupBtn()">
                저장
            </button>
        </div>
        <div class="mainViewArea-table" id="groupTable" style="width:100%; display: none; margin-top: 2px">
            <div id="group-table-title" class="mainViewArea-table-title">
                <div class="table-content">
                    <span>권한 그룹</span>
                </div>
            </div>
            <div id="groupTable-contents"></div>
        </div>
        <div id="noGroupMessage" class='mainViewArea-message' style="display: none; ">생성된 그룹이 없습니다.</div>
        <div id="noSearchGroupMessage" class='mainViewArea-message' style="display: none;">검색된 그룹이 없습니다.</div>
    </div>


    <div id="userArea" class="userArea" style="display: none">
        <div style="width:100%; overflow: hidden; margin-top: 10px">
            <button type="button" id="userArea-add-user-btn" class="btn btn-primary btn-sm" style="float:right; margin: 3px" onclick="clickAddAuthorityGroupMembersBtn()">
                유저 등록
            </button>
            <button type="button" id="userArea-delete-user-btn" class="btn btn-primary btn-sm" style="float:right; margin: 3px" disabled="true" onclick="clickDeleteAuthorityGroupMembersBtn()">
                유저 삭제
            </button>
        </div>

        <div id="addAuthorityGroupMemeberBox" class="addBox" style="display: none; width:100%">
            <div style="float: left; width: 70%;">
                <div class="form-group has-feedback has-feedback-right" style="margin: 7px">
                    <input id="user-add-name-input" type="text" class="form-control form-control-warning" placeholder="유저이름을 입력하세요.">
                    <span id="user-add-name-input-icon" class="form-control-feedback glyphicon status-icon-warning"></span>
                    <div id="userName-auto-complete-area" class="auto-complete-area"></div>
                </div>
            </div>
            <button type="button" id="user-add-name-input-save-btn" class="btn btn-primary btn-sm" style="margin:7px; float: left; width: 10%;" disabled="true" onclick="clickAddUsersBtn()">
                저장
            </button>
        </div>

        <div id="userTable" class="mainViewArea-table" style="display: none; width:100%; margin-top: 2px;">
            <div class="mainViewArea-table-title">
                <div class="table-content">
                    <span>사용자</span>
                </div>
            </div>

            <div id="userTable-contents"></div>
        </div>
        <div id="noUserMessage" class='mainViewArea-message' style="width: 100%; display:none;">등록된 사용자가 없습니다.</div>
    </div>
</div>--%>

<%@ include file="../common/footer.jsp"%>












