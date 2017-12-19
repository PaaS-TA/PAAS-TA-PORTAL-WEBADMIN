<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp"%>


<script type="text/javascript">

    $(document).ready(function(){
        getValue();
    });

    function getValue(){

        param = {
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
                        $('#'+data.name).val(data.value);
                    });
                }
            }
        });
    }


    function updateValue(name, value){
        if (0 != value.length) {
            if (0 == value.replace(/ /g,"").length) {
                $('#' + name).focus();
                procAlert("warning", "<spring:message code='common.info.empty.req.data' />");
                return false;
            }

            if (value.includes(' ')) {
                $('#' + name).focus();
                procAlert("warning", "<spring:message code='common.url.validation.error.message' />");
                return false;
            }
            if(name!='smtp_url'){
                if (!procCheckValidUrl(value)) {
                    $('#' + name).focus();
                    procAlert("warning", "<spring:message code='common.url.validation.error.message' />");
                    return false;
                }
            }
        } else {
            $('#' + name).focus();
            return false;
        }

        param = {
            name:  name,
            value: value
        }

        $.ajax({
            url: "/configInfo/updateValue",
            method: "POST",
            data: JSON.stringify(param),
            dataType: 'json',
            contentType: "application/json",
            success: function(data){
                if(data){
                    procAlert("success", '설정이 저장되었습니다.');
                }
            }
        });

    }



</script>

<%--<div class="search_box">
    <h2>설정 정보</h2>

</div>


<div class="box">
    <div>
       <h1>API URL</h1><br>
        <table class="table table-striped table-hover t1">
            <tr>
                <td  width="250">
                    API 내용
                </td>
                <td>
                    <input type="text" maxlength="200" name="api_url" id="api_url" size="70">
                </td>
                <td  width="80" align="center">
                    <button type="button" class="btn btn-success  btn-sm" onClick="updateValue('api_url', $('#api_url').val())">저장</button>
                </td>
            </tr>

        </table>

        <br>
        <h1>UAA URL</h1><br>
        <table class="table table-striped table-hover t1">
            <tr>
                <td  width="250">
                    UAA URL
                </td>
                <td>
                    <input type="text" maxlength="200" name="uaa_url" id="uaa_url" size="70">
                </td>
                <td  width="80" align="center">
                    <button type="button" class="btn btn-success  btn-sm" onClick="updateValue('uaa_url', $('#uaa_url').val())">저장</button>
                </td>
            </tr>

        </table>

        <br>
        <h1>사용자포탈 URL</h1><br>
        <table class="table table-striped table-hover t1">
            <tr>
                <td  width="250">
                    사용자포탈 URL
                </td>
                <td>
                    <input type="text" maxlength="200" name="user_portal_url" id="user_portal_url" size="70">
                </td>
                <td  width="80" align="center">
                    <button type="button" class="btn btn-success  btn-sm" onClick="updateValue('user_portal_url', $('#user_portal_url').val())">저장</button>
                </td>
            </tr>
        </table>

        <br>
        <h1>SMTP URL</h1><br>
        <table class="table table-striped table-hover t1">
            <tr>
                <td  width="250">
                    SMTP
                </td>
                <td>
                    <input type="text" maxlength="200" name="smtp_url" id="smtp_url" size="70">
                </td>
                <td  width="80" align="center">
                    <button type="button" class="btn btn-success  btn-sm" onClick="updateValue('smtp_url', $('#smtp_url').val())">저장</button>
                </td>
            </tr>
        </table>

        <br>
        <h1>WEB IDE URL</h1><br>
        <table class="table table-striped table-hover t1">
            <tr>
                <td  width="250">
                    WEB IDE URL<br>
                    <h6> ** 콤마(,)로 각 서버 구분.</h6>
                </td>
                <td>
                    <input type="text" maxlength="500" name="web_ide_url" id="web_ide_url" size="70">
                </td>
                <td  width="80" align="center">
                    <button type="button" class="btn btn-success  btn-sm" onClick="updateValue('web_ide_url', $('#web_ide_url').val())">저장</button>
                </td>
            </tr>
        </table>
    </div>
</div>--%>

<%--/////////////--%>

<div class="col-sm-6 pt30">
    <h4 class="modify_h4 fwn">설정 정보</h4>
</div>

<div class="panel content-box col-sm-12 col-md-12 mt-50 col-md-offset-13 w98">
    <div style="height: 10px;"></div>
    <div class="col-sm-6 pt5">
        <h4 class="modify_h4 fwm"> API URL </h4>
    </div>
    <div style="margin: 45px 0 0 10px;width:98%;">
        <table class="table table-striped table-hover t1">
            <tr>
                <td width="20%">
                    API 내용
                </td>
                <td width="70%">
                    <input type="text" class="form-control" style="margin-top: -10px;" maxlength="200" name="api_url" id="api_url" size="100">
                </td>
                <td  width="10%" align="center">
                    <button type="button" class="btn btn-save btn-sm" onClick="updateValue('api_url', $('#api_url').val())">저장</button>
                </td>
            </tr>
        </table>
    </div>

    <div style="height: 20px;"></div>
    <div class="col-sm-6 pt5">
        <h4 class="modify_h4 fwm"> UAA URL </h4>
    </div>
    <div style="margin: 45px 0 0 10px;width:98%;">
        <table class="table table-striped table-hover t1">
            <tr>
                <td width="20%">
                    UAA URL
                </td>
                <td width="70%">
                    <input type="text" class="form-control" style="margin-top: -10px;" maxlength="200" name="uaa_url" id="uaa_url" size="100">
                </td>
                <td  width="10%" align="center">
                    <button type="button" class="btn btn-save btn-sm" onClick="updateValue('uaa_url', $('#uaa_url').val())">저장</button>
                </td>
            </tr>
        </table>
    </div>

    <div style="height: 20px;"></div>
    <div class="col-sm-6 pt5">
        <h4 class="modify_h4 fwm"> 사용자포탈 URL </h4>
    </div>
    <div style="margin: 45px 0 0 10px;width:98%;">
        <table class="table table-striped table-hover t1">
            <tr>
                <td width="20%">
                    사용자포탈 URL
                </td>
                <td width="70%">
                    <input type="text" class="form-control" style="margin-top: -10px;" maxlength="200" name="user_portal_url" id="user_portal_url" size="100">
                </td>
                <td  width="10%" align="center">
                    <button type="button" class="btn btn-save btn-sm" onClick="updateValue('user_portal_url', $('#user_portal_url').val())">저장</button>
                </td>
            </tr>
        </table>
    </div>

    <div style="height: 20px;"></div>
    <div class="col-sm-6 pt5">
        <h4 class="modify_h4 fwm"> SMTP URLL </h4>
    </div>
    <div style="margin: 45px 0 0 10px;width:98%;">
        <table class="table table-striped table-hover t1">
            <tr>
                <td width="20%">
                    SMTP
                </td>
                <td width="70%">
                    <input type="text" class="form-control" style="margin-top: -10px;" maxlength="200" name="smtp_url" id="smtp_url" size="100">
                </td>
                <td  width="10%" align="center">
                    <button type="button" class="btn btn-save btn-sm" onClick="updateValue('smtp_url', $('#smtp_url').val())">저장</button>
                </td>
            </tr>
        </table>
    </div>

    <div style="height: 20px;"></div>
    <div class="col-sm-6 pt5">
        <h4 class="modify_h4 fwm"> WEB IDE URL </h4>
    </div>
    <div style="margin: 45px 0 0 10px;width:98%;">
        <table class="table table-striped table-hover t1">
            <tr>
                <td width="20%">
                    WEB IDE URL<br>
                    <h6> ** 콤마(,)로 각 서버 구분.</h6>
                </td>
                <td width="70%">
                    <input type="text" class="form-control" style="margin-top: -10px;" maxlength="200" name="web_ide_url" id="web_ide_url" size="100">
                </td>
                <td  width="10%" align="center">
                    <button type="button" class="btn btn-save btn-sm" onClick="updateValue('web_ide_url', $('#web_ide_url').val())">저장</button>
                </td>
            </tr>
        </table>
    </div>

</div>

<%--FOOTER--%>
<%@ include file="../common/footer.jsp"%>
