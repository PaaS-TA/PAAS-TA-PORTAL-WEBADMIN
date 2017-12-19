<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp" %>


<script type="text/javascript">

    var webIdeUrl;
    var webIdeUrlOption;


    $(document).ready(function () {
        getServiceBrokerList();

    });


    function getServiceBrokerList() {

        param = {}

        $.ajax({
            url: "/service/getServiceBrokers",
            method: "POST",
            data: JSON.stringify(param),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {

                if (data.list) {
                    var n = 0;
                    $.each(data.list, function (id, data) {

                        $("#serviceBrokerList").append(" <tr class='table table-striped table-hover t1' style='cursor:pointer;'" +
                                " onclick='window.location=\"/service/serviceBrokerDetail?name=" + data.name + "\"'>" +
                                "<td class='col-md-1' align='left'>" + data.name + "</td>" +
                                "<td class='col-md-1' align='left'>" + data.url + "</td>"
                        );

                    });
                }
            }
        });
    }


    function updateUser(userId, orgName, url, useYn) {

        param = {
            userId: userId,
            orgName: orgName,
            url: $("#" + url).val(),
            useYn: $("#" + useYn).val()
        }

        $.ajax({
            url: "/webIdeUser/updateUser",
            method: "POST",
            data: JSON.stringify(param),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                if (data) {
                    procAlert("success", 'WEB IDE 유저 정보가 저장되었습니다.');
                }
            }
        });

    }


    function deleteWebIdeModal(orgName) {

        $("#popupTitle").html("WEB IDE 유저 삭제");
        $("#popupMessage").html("" + orgName + " 조직의 WEB IDE 유저를 삭제하시겠습니까?");
        $("#popupButtonText").text("삭제");
        $("#popupButtonText").show();
        $('#popupButtonText').attr('onclick', "deleteUser('" + orgName + "');");

        $('div.modal').modal('toggle');
    }

    function deleteUser(orgName) {

        param = {
            orgName: orgName
        }

        $.ajax({
            url: "/webIdeUser/deleteUser",
            method: "POST",
            data: JSON.stringify(param),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                if (data) {
                    procAlert('success', "WEB IDE 사용자가 삭제되었습니다.");
                    $("#serviceBrokerList").html("");
                    getConfigInfoValue();
                }
            },
            complete: function (data) {
                $('div.modal').modal('hide');
            }
        });
    }


</script>

<%--******************** 서비스 브로커 ********************--%>
<div class="panel content-box col-sm-12 col-md-12 mt-50 col-md-offset-13 w98">
    <div class="col-sm-6 pt5">
        <h4 class="modify_h4 fwm"> 서비스 브로커 </h4>
    </div>
    <div class="col-sm-6 ml-10 tar">
        <button type="button" class="btn btn-point btn-sm" onClick="location.href='/service/serviceBrokerCreate'" >
            등록
        </button>
    </div>
    <div style="margin: 45px 0 0 10px;width:98%;">
        <div></div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th width="50%">이름</th>
                <th width="50%">URL</th>
            </tr>
            </thead>
            <tbody id="serviceBrokerList">
            </tbody>
        </table>
    </div>

</div>


<%--
<div class="search_box">
    <h2>서비스 브로커</h2>

</div>


<div class="box">
    <div>
        <table width="100%" border="0" style="margin-bottom: 5px">
            <tr>

                <td width="100%" align="right">
                    <button type="button" class="btn btn-success  btn-sm "
                            onClick="location.href='/service/serviceBrokerCreate'">등록
                    </button>

                </td>

            </tr>
        </table>
        <table class="table table-striped table-hover t1">
            <thead>
            <tr>
                <th width="50%">이름</th>
                <th width="50%">URL</th>
            </tr>
            </thead>
            <tbody id="serviceBrokerList">

            </tbody>
        </table>
    </div>
</div>--%>

<%--FOOTER--%>
<%@ include file="../common/footer.jsp" %>
