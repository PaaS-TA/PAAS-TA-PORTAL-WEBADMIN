<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp" %>


<script type="text/javascript">

    var serviceBrokerName = "<%=request.getParameter("name")%>";


    $(document).ready(function () {
        getServiceBroker();
    });


    function getServiceBroker() {

        param = {
            name: serviceBrokerName
        }

        $.ajax({
            url: "/service/getServiceBroker",
            method: "POST",
            data: JSON.stringify(param),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {

                if (data.servicebroker) {

                    $("#name").val(data.servicebroker.name);
                    $("#username").val(data.servicebroker.username);
                    $("#password").val(data.servicebroker.password);
                    $("#url").val(data.servicebroker.url);

                }
            }
        });
    }


    function updateServiceBroker() {

        if (!procCheckValidStringSpace()) return false;

        param = {
            name: serviceBrokerName,
            username: $("#username").val(),
            password: $("#password").val(),
            url: $("#url").val()
        }

        $.ajax({
            url: "/service/updateServiceBroker",
            method: "POST",
            data: JSON.stringify(param),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                if (data) {
                    procAlert("success", '서비스 브로커 정보가 저장되었습니다.');
                    location.href = "/service/serviceBrokerMain";
                }
            },
            error: function (xhr, status, error) {
                procAlert('warning', "서비스 브로커 정보가 유효하지 않습니다.");
            }

        });

    }


    function renameServiceBroker() {

        var objName = $("#name");
        var reqName = objName.val();
        if (!procCheckValidStringSpace(reqName)) {
            procAlert('warning', "서비스 브로커 이름이 유효하지 않습니다.");
            objName.focus();
        } return false;

        param = {
            name: serviceBrokerName,
            newName: reqName
        }

        $.ajax({
            url: "/service/renameServiceBroker",
            method: "POST",
            data: JSON.stringify(param),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                if (data) {
                    serviceBrokerName = reqName;
                    procAlert("success", '서비스 브로커 이름이 변경되었습니다.');
                }
            },
            error: function (xhr, status, error) {
                procAlert('warning', JSON.parse(xhr.responseText).message);
            }

        });

    }


    function deleteServiceBrokerModal() {

        $("#popupTitle").html("서비스 브로커 삭제");
        $("#popupMessage").html("" + serviceBrokerName + " 서비스 브로커를 삭제하시겠습니까?");
        $("#popupButtonText").text("삭제");
        $("#popupButtonText").show();
        $('#popupButtonText').attr('onclick', "deleteServiceBroker();");

        $('div.modal').modal('toggle');
    }

    function deleteServiceBroker() {

        param = {
            name: serviceBrokerName
        }

        $.ajax({
            url: "/service/deleteServiceBroker",
            method: "POST",
            data: JSON.stringify(param),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                if (data) {
                    procAlert('success', "서비스 브로커가 삭제되었습니다.");
                    location.href = "/service/serviceBrokerMain";
                }
            },

            error: function (xhr, status, error) {
                procAlert('warning', JSON.parse(xhr.responseText).message);
            },

            complete: function (data) {
                $('div.modal').modal('hide');
            }
        });
    }


</script>

<%--
<div class="search_box">
    <h2>서비스 브로커</h2>

</div>


<div class="box">
    <div>
        <table class="table table-striped table-hover t1">

            <tr>
                <td width="30%">서비스 브로커 이름</td>
                <td width="70%"><input type="text" maxlength="100" name="name" id="name" size="50" class="toCheckString">
                    <button type="button" class="btn btn-success  btn-sm" onClick="renameServiceBroker()">이름변경</button>
                </td>
            </tr>
            <tr>
                <td width="30%">사용자 이름</td>
                <td width="70%"><input type="text" maxlength="100" name="username" id="username" size="50" class="toCheckString"></td>
            </tr>
            <tr>
                <td width="30%">사용자 패스워드</td>
                <td width="70%"><input type="password" maxlength="100" name="password" id="password" size="50" class="toCheckString"></td>
            </tr>

            <tr>
                <td width="30%">URL 정보</td>
                <td width="70%"><input type="text" maxlength="200" name="url" id="url" size="50" class="toCheckString"></td>
            </tr>

        </table>

        <table width="100%" border="0">
            <tr>
                <td width="70%">
                    <button type="button" class="btn btn-danger btn-sm " onClick="deleteServiceBrokerModal()">삭제
                    </button>
                </td>
                <td width="30%" align="right">
                    <button type="button" class="btn btn-normal  btn-sm "
                            onClick="location.href='/service/serviceBrokerMain'">목록
                    </button>
                    <button type="button" class="btn btn-success  btn-sm" onClick="updateServiceBroker()">저장</button>
                </td>

            </tr>
        </table>


    </div>
</div>
--%>

<div class="col-sm-6 pt30">
    <h4 class="modify_h4 fwn">서비스 브로커 상세</h4>
</div>

<div class="col-md-11 col-md-offset-14">
    <form class="form-horizontal" role="form" >

        <div class="form-group">
            <label class="control-label col-sm-2" for="name">서비스 브로커 이름</label>

            <div class="col-sm-8"  style="margin-left: -7px; width: 70%;">
                <input type="text" class="form-control" name="name" id="name" >
            </div>
            <div class="col-sm-1">
                <button type="button" class="btn btn-cancel btn-sm tar" style="margin-top:11px;margin-left:-20px;" onClick="renameServiceBroker()">
                    이름변경
                </button>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="username">사용자 이름</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="username" id="username">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="password">사용자 패스워드</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="password" id="password">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="url">URL 정보</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control"  name="url" id="url">
                </div>
            </div>



        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-9" align="right" id = "statusActivity">
                <div class="divButtons" style="width:97.8%;">
                    <button type="button" class="btn btn_del2 fl ml-22" onClick="deleteServiceBrokerModal()">삭제</button>
                    <button type="button" class="btn btn-cancel2 btn-sm" onClick="location.href='/service/serviceBrokerMain'">목록</button>
                    <button type="button" class="btn btn-save btn-sm" onClick="updateServiceBroker()">저장</button>
                </div>
            </div>
        </div>

    </form>
</div>

<div class="row">
</div>






<%--FOOTER--%>
<%@ include file="../common/footer.jsp" %>
