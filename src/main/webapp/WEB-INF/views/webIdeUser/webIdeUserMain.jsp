<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp"%>


<script type="text/javascript">

    var webIdeUrl;
    var webIdeUrlOption;


    $(document).ready(function(){
        getConfigInfoValue();

    });


    function getConfigInfoValue(){

        param = {
            name: "web_ide_url"
        }

        $.ajax({
            url: "/configInfo/getValue",
            method: "POST",
            data: JSON.stringify(param),
            dataType: 'json',
            contentType: "application/json",
            success: function(data){

                if(data.list){
                    $.each(data.list, function (id, data) {
                        if(data.name == "web_ide_url"){
                            webIdeUrl = data.value.split(",");
                        }
                    });

                    //alert(webIdeUrlOption);
                    getList();
                }
            }
        });
    }


    function getList(){

        param = {
        }

        $.ajax({
            url: "/webIdeUser/getList",
            method: "POST",
            data: JSON.stringify(param),
            dataType: 'json',
            contentType: "application/json",
            success: function(data){

                if(data.list){
                    var n = 0;
                    $.each(data.list, function (id, data) {
                        n++;
                        use_y = "";
                        use_n = "";
                        if(data.useYn=='Y') {
                            use_y = "selected";
                        }else{
                            use_n = "selected";
                        }

                        url = "";
                        if(data.url == null ) {
                            url = "";
                        }else{
                            url = data.url;
                        }

                        webIdeUrlOption = "<option value=''> -- URL선택 --</option>";


                        for(var i=0; webIdeUrl[i] != null; i++){
                            //alert(url + " " + webIdeUrl[i]);
                            if(url ==  webIdeUrl[i]) {
                                webIdeUrlOption += "<option value='" + webIdeUrl[i] + "' selected>" + webIdeUrl[i] + "</option>";
                            }else{
                                webIdeUrlOption += "<option value='" + webIdeUrl[i] + "'>" + webIdeUrl[i] + "</option>";
                            }
                        }

                        $("#webIdeUserList").append(" <tr class='table table-striped table-hover t1'>" +
                                "<td class='col-md-1' align='left'> " + data.userId + "</td>" +
                                "<td class='col-md-1' align='left'> " + data.orgName + "</td>" +
                                "<td class='col-md-1' ><select id=url"+ n +" style='width:400px;'>" + webIdeUrlOption + "</select></td>" +
                                "<td class='col-md-1' align='center'> " +
                                "<select id=useYn"+ n +">" +
                                "<option " + use_y + ">Y</option>" +
                                "<option " + use_n + ">N</option>" +
                                "</select>"+
                                "</td>" +
                                "<td class='col-md-1' align='center'> " +
                                        "<button type='button' class='btn btn-save btn-sm' onClick='updateUser(\""+ data.userId  +"\", \""+ data.orgName  +"\" , \"url"+ n +"\" , \"useYn"+ n +"\")'>저장</button>" +
                                        "<button type='button' class='btn btn_del2' onClick='deleteWebIdeModal(\""+ data.orgName  +"\")'>삭제</button></td>" +
                                "</tr>");

                    });
                }
            }
        });
    }


    function updateUser(userId, orgName, url, useYn){

        var objUrl = $("#" + url);
        var reqUrl = objUrl.val();
        var reqUseYn = $("#" + useYn).val();

        if ('Y' == reqUseYn && '' == reqUrl) {
            procAlert("danger", '<spring:message code="common.web.ide.url.validation.error.message" />');
            objUrl.focus();
            return false;
        }

        var param = {
            userId:  userId,
            orgName: orgName,
            url : reqUrl,
            useYn : reqUseYn
        };

        $.ajax({
            url: "/webIdeUser/updateUser",
            method: "POST",
            data: JSON.stringify(param),
            dataType: 'json',
            contentType: "application/json",
            success: function(data){
                if(data){
                    procAlert("success", 'WEB IDE 유저 정보가 저장되었습니다.');
                }
            }
        });

    }


    function deleteWebIdeModal(orgName) {

        $("#popupTitle").html("WEB IDE 유저 삭제");
        $("#popupMessage").html(""+orgName+" 조직의 WEB IDE 유저를 삭제하시겠습니까?");
        $("#popupButtonText").text("삭제");
        $("#popupButtonText").show();
        $('#popupButtonText').attr('onclick', "deleteUser('"+orgName+"');");

        $('div.modal').modal('toggle');
    }

    function deleteUser(orgName){

        param = {
            orgName:  orgName
        }

        $.ajax({
            url: "/webIdeUser/deleteUser",
            method: "POST",
            data: JSON.stringify(param),
            dataType: 'json',
            contentType: "application/json",
            success: function(data){
                if(data){
                    procAlert('success',"WEB IDE 사용자가 삭제되었습니다.");
                    $("#webIdeUserList").html("");
                    getConfigInfoValue();
                }
            },
            complete : function(data){
                $('div.modal').modal('hide');
            }
        });
    }




</script>

<%--
<div class="search_box">
    <h2>WEB IDE 유저 관리</h2>

</div>


<div class="box">
    <div>
        <table class="table table-striped table-hover t1" >
            <thead>
            <tr>
                <th width="20%">아이디</th>
                <th width="20%">조직</th>
                <th width="38%">URL</th>
                <th width="10%">사용유무</th>
                <th width="12%"></th>
            </tr>
            </thead>
            <tbody id="webIdeUserList">

            </tbody>
        </table>
    </div>
</div>
--%>



<%--******************** WEB IDE 유저 관리 ********************--%>
<div class="col-sm-6 pt30">
    <h4 class="modify_h4 fwm"> WEB IDE 유저 관리 </h4>
</div>

<div class="panel content-box col-sm-12 col-md-12 mt-50 col-md-offset-13 w98" >

    <div style="margin: 10px 0 0 10px;width:98%;">
        <table class="table table-striped">
            <thead>
            <tr>
                <th width="20%">아이디</th>
                <th width="20%">조직</th>
                <th width="38%">URL</th>
                <th width="10%">사용유무</th>
                <th width="12%"></th>
            </tr>
            </thead>
            <tbody id="webIdeUserList">
            </tbody>
        </table>
    </div>

</div>



<%--FOOTER--%>
<%@ include file="../common/footer.jsp"%>
