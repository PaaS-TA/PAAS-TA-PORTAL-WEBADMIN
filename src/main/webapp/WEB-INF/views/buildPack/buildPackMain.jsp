<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp"%>


<script type="text/javascript">

    $(document).ready(function(){
        getList();

    });

    function getList(){

        param = {
        }

        $.ajax({
            url: "/buildPack/getBuildPacks",
            method: "POST",
            data: JSON.stringify(param),
            dataType: 'json',
            contentType: "application/json",
            success: function(data){

                var n = 0;
                if(data.resources){ //
                    totalCount = data.resources.length;
                    $.each(data.resources, function (id, data) {
                        n++;
                        var positionOption = "";
                        for(var i=1; i <= totalCount; i++){
                            if(i ==  data.entity.position) {
                                positionOption += "<option value='" + i + "' selected>" + i + "</option>";
                            }else{
                                positionOption += "<option value='" + i + "'>" + i + "</option>";
                            }
                        }

                        var enabled = "";
                        if(data.entity.enabled){
                            enabled = "checked";
                        }

                        var locked = "";
                        if(data.entity.locked){
                            locked = "checked";
                        }

                        $("#buildPackList").append(" <tr class='table table-striped table-hover t1'>" +
                                "<td class='col-md-1' align='left'> " + data.entity.name + "</td>" +
                                "<td class='col-md-1' align='left'> " + data.entity.filename + "</td>" +
                                "<td class='col-md-1' align='center'><select id='position"+ n +"' style='width:50px;' onChange=updateBuildPack('"+ data.metadata.guid +"'," + n + ",true); >" + positionOption + "</select></td>" +
                                "<td class='col-md-1' align='center'><input type='checkbox' id='enabled"+ n +"' "+ enabled + " onChange=updateBuildPack('"+ data.metadata.guid +"'," + n + ",false); ></td>" +
                                "<td class='col-md-1' align='center'><input type='checkbox' id='locked"+ n +"' "+ locked + " onChange=updateBuildPack('"+ data.metadata.guid +"'," + n + ",false); ></td>"
                        );

                    });
                }
            }
        });
    }


    function updateBuildPack(guid, n, refresh){
        param = {
            guid:  guid,
            position: $('#position'+ n).val(),
            enable : $('#enabled'+ n).is(":checked"),
            lock : $('#locked'+ n).is(":checked")
        }

        $.ajax({
            url: "/buildPack/updateBuildPack",
            method: "POST",
            data: JSON.stringify(param),
            dataType: 'json',
            contentType: "application/json",
            success: function(data){
                if(data){//
                    procAlert("success", '빌드팩 정보가 저장되었습니다.');
                    if(refresh) {
                        $("#buildPackList").html("");
                        getList();
                    }
                }
            }
        });

    }



</script>

<%--******************** 빌드팩 ********************--%>
<div class="panel content-box col-sm-12 col-md-12 mt-50 col-md-offset-13 w98">
    <div class="col-sm-6 pt5">
        <h4 class="modify_h4 fwm"> 빌드팩 </h4>
    </div>

    <div style="margin: 45px 0 0 10px;width:98%;">
        <table class="table table-striped">
            <thead>
            <tr>
                <th width="30%">이름</th>
                <th width="30%">파일명</th>
                <th width="13%">순서</th>
                <th width="13%">사용유무</th>
                <th width="13%">Lock유무</th>
            </tr>
            </thead>
            <tbody id="buildPackList">
            </tbody>
        </table>
    </div>

</div>





<%--
<div class="box">
    <div>
        <table class="table table-striped table-hover t1" >
            <thead>
            <tr>
                <th width="30%">이름</th>
                <th width="30%">파일명</th>
                <th width="13%">순서</th>
                <th width="13%">사용유무</th>
                <th width="13%">Lock유무</th>
            </tr>
            </thead>
            <tbody id="buildPackList">

            </tbody>
        </table>
    </div>
</div>--%>

<%--FOOTER--%>
<%@ include file="../common/footer.jsp"%>
