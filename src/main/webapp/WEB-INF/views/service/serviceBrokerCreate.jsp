<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp" %>


<script type="text/javascript">

    var webIdeUrl;
    var webIdeUrlOption;


    function createServiceBroker() {

        if (!procCheckValidStringSpace()) return false;

        param = {
            name: $("#name").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            url: $("#url").val()
        }

        $.ajax({
            url: "/service/createServiceBroker",
            method: "POST",
            data: JSON.stringify(param),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                if (data) {
                    location.href = "/service/serviceBrokerMain";
                    procAlert("success", '서비스 브로커 정보가 저장되었습니다.');
                }
            },
            error: function (xhr, status, error) {
                procAlert('warning', JSON.parse(xhr.responseText).message);
            }

        });

    }


</script>


<div class="col-sm-6 pt30">
    <h4 class="modify_h4 fwn">서비스 브로커 등록</h4>
</div>

<div class="col-md-11 col-md-offset-14">
    <form class="form-horizontal" role="form" >

        <div class="form-group">
            <label class="control-label col-sm-2" for="name">서비스 브로커 이름</label>

            <div class="col-sm-7" >
                <input type="text" class="form-control" name="name" id="name" >
            </div>
            <div class="col-sm-2" style="margin-top: 15px">
                <span> ex) rabbitmq-service-broker</span>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="username">사용자 이름</label>
            <div class="col-sm-7" >
                <input type="text" class="form-control" name="username" id="username" >
            </div>
            <div class="col-sm-2" style="margin-top: 15px">
                <span> ex) admin </span>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="password">사용자 패스워드</label>
            <div class="col-sm-7" >
                <input type="text" class="form-control" name="password" id="password" >
            </div>
            <div class="col-sm-2" style="margin-top: 15px">
                <span> ex) admin </span>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="url">URL 정보</label>
            <div class="col-sm-7" >
                <input type="text" class="form-control" name="url" id="url" >
            </div>
            <div class="col-sm-2" style="margin-top: 15px">
                <span> ex) http://10.30.40.81:4567 </span>
            </div>
        </div>



        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-9" align="right" id = "statusActivity">
                <div class="divButtons" style="width:97.8%;">
                    <button type="button" class="btn btn-cancel2 btn-sm" onClick="location.href='/service/serviceBrokerMain'">목록</button>
                    <button type="button" class="btn btn-save btn-sm" onClick="createServiceBroker()">등록</button>
                </div>
            </div>
        </div>

    </form>
</div>

<div class="row">
</div>


<%--


<div class="search_box">
    <h2>서비스 브로커</h2>

</div>


<div class="box">
    <div>
        <table class="table table-striped table-hover t1">

            <tr>
                <td width="30%">서비스 브로커 이름</td>
                <td width="70%"><input type="text" maxlength="100" name="name" id="name" size="40" class="toCheckString"> ex) rabbitmq-service-broker</td>
            </tr>
            <tr>
                <td width="30%">사용자 이름</td>
                <td width="70%"><input type="text" maxlength="100" name="username" id="username" size="40" class="toCheckString"> ex) admin</td>
            </tr>
            <tr>
                <td width="30%">사용자 패스워드</td>
                <td width="70%"><input type="password" maxlength="100" name="password" id="password" size="40" class="toCheckString"> ex) admin</td>
            </tr>

            <tr>
                <td width="30%">URL 정보</td>
                <td width="70%"><input type="text" maxlength="200" name="url" id="url" size="40" class="toCheckString"> ex) http://10.30.40.81:4567</td>
            </tr>

        </table>

        <table width="100%" border="0">
            <tr>

                <td width="100%" align="right">
                    <button type="button" class="btn btn-normal  btn-sm "
                            onClick="location.href='/service/serviceBrokerMain'">목록
                    </button>
                    <button type="button" class="btn btn-success  btn-sm" onClick="createServiceBroker()">저장</button>
                </td>

            </tr>
        </table>


    </div>
</div>
--%>

<%--FOOTER--%>
<%@ include file="../common/footer.jsp" %>
