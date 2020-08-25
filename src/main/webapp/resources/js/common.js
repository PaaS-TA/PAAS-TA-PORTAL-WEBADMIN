// CALL AJAX FOR GRID
var procGridCallAjax = function(reqUrl, objGrid, procType, param, callback) {
    var reqIndex = 0;
    if (param != null) reqIndex = param.index;

    $.ajax({
        url: reqUrl,
        method: "POST",
        data: JSON.stringify(param),
        dataType: 'json',
        contentType: "application/json",
        success: function(data) {
            if (data) {
                callback(objGrid, procType, data, reqIndex);

            } else {
                var resData = {RESULT : RESULT_STATUS_SUCCESS,
                               RESULT_MESSAGE : RESULT_STATUS_SUCCESS_MESSAGE};

                callback(objGrid, procType, resData, reqIndex);
            }
        },
        error: function(xhr, status, error) {
            var resData = {RESULT : RESULT_STATUS_FAIL,
                            RESULT_MESSAGE : JSON.parse(xhr.responseText).message};

            callback(objGrid, procType, resData, reqIndex);

            console.log("ERROR GIRD :: error :: ", error);
            procAlert("danger", JSON.parse(xhr.responseText).message);
        },
        complete : function(data) {
            console.log("COMPLETE GIRD :: data :: ", data);
        }
    });
};

// Http Method , loadingBar Element Argument 추가
var procCallAjax = function(reqUrl, reqMethod, param, callback, $targetLoadingBarElement) {
    var reqData = "";

    if (param != null) {
        reqData = param;
    }
    $.ajax({
        url: reqUrl,
        method: reqMethod,
        data: reqData,
        dataType: 'json',
        contentType: "application/json",
        beforeSend: function(){
            if($targetLoadingBarElement !== null && $targetLoadingBarElement !== undefined) {
                $targetLoadingBarElement.removeClass("hide");
            }
        },
        success: function(data) {

            if(data.message){
                notifyAlert('info','',data.message);
                return false;
            }

            if (data) {
                callback(data, param);

                if($targetLoadingBarElement !== null && $targetLoadingBarElement !== undefined){
                    switch (reqMethod) {
                        case "PUT" :
                            notifyAlert('success',"",'수정 완료 되었습니다.');
                            break;
                        case "POST" :
                            notifyAlert('success',"",'생성 완료 되었습니다.');
                            break;
                        case "DELETE" :
                            notifyAlert('success',"",'삭제 완료 되었습니다.');
                            break;
                        default :
                            break;
                    }
                }
            } else {
                var resData = {RESULT : RESULT_STATUS_SUCCESS,
                    RESULT_MESSAGE : RESULT_STATUS_SUCCESS_MESSAGE};

                callback(resData, param);
            }
        },
        error: function(xhr, status, error) {
            //var resData = {RESULT : RESULT_STATUS_FAIL,
            //    RESULT_MESSAGE : JSON.parse(xhr.responseText).message};
            //callback(resData, param);
            console.log("ERROR :: error :: ", error);
            notifyAlert('danger','',JSON.parse(xhr.responseText).message);
        },
        complete : function(data) {
            if($targetLoadingBarElement !== null && $targetLoadingBarElement !== undefined) {
                $targetLoadingBarElement.addClass('hide');
            }
            console.log("COMPLETE :: data :: ", data);
        }
    });
};
// Http Method , loadingBar Element Argument 추가
var procCallAjax5 = function(reqUrl, reqMethod, param, callback, $targetLoadingBarElement) {
    var reqData = "";

    if (param != null) {
        reqData = param;
    }
    $.ajax({
        url: reqUrl,
        method: reqMethod,
        data: reqData,
        dataType: 'json',
        contentType: "application/json",
        beforeSend: function(){
            if($targetLoadingBarElement !== null && $targetLoadingBarElement !== undefined) {
                $targetLoadingBarElement.removeClass("hide");
            }
        },
        success: function(data) {


            if (data.result == true) {
                callback(data, param);

                if($targetLoadingBarElement !== null && $targetLoadingBarElement !== undefined){
                    switch (reqMethod) {
                        case "PUT" :
                            notifyAlert('success',"",'수정 완료 되었습니다.');
                            break;
                        case "POST" :
                            notifyAlert('success',"",'생성 완료 되었습니다.');
                            break;
                        case "DELETE" :
                            notifyAlert('success',"",'삭제 완료 되었습니다.');
                            break;
                        default :
                            break;
                    }
                }
            } else {
                notifyAlert('danger','',"작업중에 오류가 발생하였습니다.");
                return false;
            }
        },
        error: function(xhr, status, error) {
            //var resData = {RESULT : RESULT_STATUS_FAIL,
            //    RESULT_MESSAGE : JSON.parse(xhr.responseText).message};
            //callback(resData, param);
            console.log("ERROR :: error :: ", error);
            notifyAlert('danger','',JSON.parse(xhr.responseText).message);
        },
        complete : function(data) {
            if($targetLoadingBarElement !== null && $targetLoadingBarElement !== undefined) {
                $targetLoadingBarElement.addClass('hide');
            }
            console.log("COMPLETE :: data :: ", data);
        }
    });
};

// Http Method , loadingBar Element Argument 추가
var procCallAjax4 = function(reqUrl, reqMethod, param, callback, $targetLoadingBarElement) {
    var reqData = "";

    if (param != null) {
        reqData = param;
    }
    $.ajax({
        url: reqUrl,
        method: reqMethod,
        data: reqData,
        dataType: 'json',
        contentType: "application/json",
        beforeSend: function(){
            if($targetLoadingBarElement !== null && $targetLoadingBarElement !== undefined) {
                $targetLoadingBarElement.removeClass("hide");
            }
        },
        success: function(data) {
            console.log("DATA :: " + data);
            console.log("DATA2 :: " + data.result);


            if (data.result == true) {
                callback(data, param);

                if($targetLoadingBarElement !== null && $targetLoadingBarElement !== undefined){
                    switch (reqMethod) {
                        case "PUT" :
                            notifyAlert('success',"",'수정 완료 되었습니다.');
                            break;
                        case "POST" :
                            notifyAlert('success',"",'생성 완료 되었습니다.');
                            break;
                        case "DELETE" :
                            notifyAlert('success',"",'삭제 완료 되었습니다.');
                            break;
                        default :
                            break;
                    }
                }
            } else {
                if(data.msg){
                    notifyAlert('danger','',data.msg);
                    return false;
                }
                callback(resData, param);
            }
        },
        error: function(xhr, status, error) {
            //var resData = {RESULT : RESULT_STATUS_FAIL,
            //    RESULT_MESSAGE : JSON.parse(xhr.responseText).message};
            //callback(resData, param);
            console.log("ERROR :: error :: ", error);
            notifyAlert('danger','',JSON.parse(xhr.responseText).message);
        },
        complete : function(data) {
            if($targetLoadingBarElement !== null && $targetLoadingBarElement !== undefined) {
                $targetLoadingBarElement.addClass('hide');
            }
            console.log("COMPLETE :: data :: ", data);
        }
    });
};

var procCallAjaxAsyncFalse = function(reqUrl, reqMethod, param, callback, $targetLoadingBarElement) {
    var reqData = "";

    if (param != null) {
        reqData = param;
    }
    $.ajax({
        url: reqUrl,
        method: reqMethod,
        data: reqData,
        async: false,
        dataType: 'json',
        contentType: "application/json",
        beforeSend: function(){
            if($targetLoadingBarElement !== null && $targetLoadingBarElement !== undefined) {
                $targetLoadingBarElement.removeClass("hide");
            }
        },
        success: function(data) {

            if(data.message){
                notifyAlert('info','',data.message);
                return false;
            }

            if (data) {
                callback(data, param);

                if($targetLoadingBarElement !== null && $targetLoadingBarElement !== undefined){
                    switch (reqMethod) {
                        case "PUT" :
                            notifyAlert('success',"",'수정 완료 되었습니다.');
                            break;
                        case "POST" :
                            notifyAlert('success',"",'생성 완료 되었습니다.');
                            break;
                        case "DELETE" :
                            notifyAlert('success',"",'삭제 완료 되었습니다.');
                            break;
                        default :
                            break;
                    }
                }
            } else {
                var resData = {RESULT : RESULT_STATUS_SUCCESS,
                    RESULT_MESSAGE : RESULT_STATUS_SUCCESS_MESSAGE};

                callback(resData, param);
            }
        },
        error: function(xhr, status, error) {
            //var resData = {RESULT : RESULT_STATUS_FAIL,
            //    RESULT_MESSAGE : JSON.parse(xhr.responseText).message};
            //callback(resData, param);
            console.log("ERROR :: error :: ", error);
            notifyAlert('danger','',JSON.parse(xhr.responseText).message);
        },
        complete : function(data) {
            if($targetLoadingBarElement !== null && $targetLoadingBarElement !== undefined) {
                $targetLoadingBarElement.addClass('hide');
            }
            console.log("COMPLETE :: data :: ", data);
        }
    });
};

var procCallAjaxForSyncDelete = function(reqUrl, reqMethod, param, callback, $targetLoadingBarElement) {
    var reqData = "";

    if (param != null) {
        reqData = param;
    }
    $.ajax({
        url: reqUrl,
        method: reqMethod,
        data: reqData,
        dataType: 'json',
        //async: false,
        contentType: "application/json",
        beforeSend: function(){
            if($targetLoadingBarElement !== null && $targetLoadingBarElement !== undefined) {
                $targetLoadingBarElement.removeClass("hide");
            }
        },
        success: function(data) {

        },
        error: function(xhr, status, error) {

        },
        complete : function(data) {

            callback();

            if($targetLoadingBarElement !== null && $targetLoadingBarElement !== undefined){
                $targetLoadingBarElement.addClass('hide');
            }
            notifyAlert('success',"",'삭제 완료 되었습니다.');
        }
    });
};

// CALL AJAX  reqMethod PUT, DELETE 추가
var procCallAjax2 = function(reqUrl, reqMethod, param, callback) {
    var reqData = "";
    //var reqMethod = "POST";

    if (param != null) {
        reqData = param;
    }
    $.ajax({
        url: reqUrl,
        method: reqMethod,
        data: reqData,
        dataType: 'json',
        contentType: "application/json",
        success: function(data) {

            if (data) {
                callback(data, param);

            } else {
                var resData = {RESULT : RESULT_STATUS_SUCCESS,
                    RESULT_MESSAGE : RESULT_STATUS_SUCCESS_MESSAGE};

                callback(resData, param);
            }
        },
        error: function(xhr, status, error) {
            var resData = {RESULT : RESULT_STATUS_FAIL,
                RESULT_MESSAGE : JSON.parse(xhr.responseText).message};

            callback(resData, param);

            console.log("ERROR :: error :: ", error);
            procAlert("danger", JSON.parse(xhr.responseText).message);
        },
        complete : function(data) {
            console.log("COMPLETE :: data :: ", data);
        }
    });
};

// AJAX 전처리 Argument 추가 - beforeProc
var procCallAjax3 = function(reqUrl, reqMethod, param, beforeProc, callback, $targetLoadingBarElement) {
    var reqData = "";

    if (param != null) {
        reqData = param;
    }
    $.ajax({
        url: reqUrl,
        method: reqMethod,
        data: reqData,
        dataType: 'json',
        contentType: "application/json",
        beforeSend: function(){
            if($targetLoadingBarElement !== null && $targetLoadingBarElement !== undefined) {
                $targetLoadingBarElement.removeClass("hide");
            }
            beforeProc();
        },
        success: function(data) {

            if (data) {
                callback(data, param);
                //procAlert('success', RESULT_STATUS_SUCCESS_MESSAGE);
                if(reqMethod != "GET"){
                    notifyAlert('success','','SUCCESS!!');
                }
            } else {
                var resData = {RESULT : RESULT_STATUS_SUCCESS,
                    RESULT_MESSAGE : RESULT_STATUS_SUCCESS_MESSAGE};

                callback(resData, param);
            }
        },
        error: function(xhr, status, error) {
            var resData = {RESULT : RESULT_STATUS_FAIL,
                RESULT_MESSAGE : JSON.parse(xhr.responseText).message};

            //callback(resData, param);

            console.log("ERROR :: error :: ", error);
            //procAlert("danger", JSON.parse(xhr.responseText).message);
            notifyAlert('danger','',JSON.parse(xhr.responseText).message);
        },
        complete : function(data) {
            if($targetLoadingBarElement !== null && $targetLoadingBarElement !== undefined) {
                $targetLoadingBarElement.addClass('hide');
            }
            console.log("COMPLETE :: data :: ", data);
        }
    });
};

// Bootstrap notification used
var notifyAlert = function(alertType, alertTitle ,alertMessage){
    $.notify({
        title: '<strong>'+alertTitle+'</strong>',
        message: alertMessage
    },{
        type: alertType,
        z_index: 9999
    });
};

// ALARM
var timeoutHandler = null;
var procAlert = function(alertType, alertMessage, duplicatedValue) {

    if(alertType == "success"){
        color = "blue";
    }else if(alertType == "danger"){
        color = "red";
    }else if(alertType == "warning"){
        color = "#ffc61a";
    }else if(alertType == "info"){
        color = "#bfbfc0";
    }else{
        color = alertType;
    }

    $("#notice").show();


    $("#alertMsg").fadeOut(500);
    $("#alertMsg").html("<font color='"+ color +"'>" + alertMessage + "</font>");
    $('#alertMsg').fadeIn(1000);

    // RESET TIMER
  //  if (timeoutHandler) clearTimeout(timeoutHandler);

    // var alertDiv = $('div#alertDiv.alert');
    //
    // alertDiv.fadeOut("fast");
    //
    // if (alertDiv.hasClass('alert-success')) alertDiv.removeClass('alert-success');
    // if (alertDiv.hasClass('alert-danger')) alertDiv.removeClass('alert-danger');
    // if (alertDiv.hasClass('alert-info')) alertDiv.removeClass('alert-info');
    // if (alertDiv.hasClass('alert-warning')) alertDiv.removeClass('alert-warning');
    //
    // if (duplicatedValue != null && duplicatedValue != '') alertMessage += " :: (" + duplicatedValue + ")";
    //
    // alertDiv.addClass("alert-" + alertType);
    // alertDiv.html("[<strong>" + alertType.toUpperCase() + "</strong>] " + alertMessage);
    // alertDiv.fadeIn("fast");

    // timeoutHandler = window.setTimeout(function () {
    //     procResetAlert("info", WELCOME_MESSAGE);
    // }, 10000);
};


// RESET ALERT
// var procResetAlert = function(alertType, alertMessage) {
//     var alertDiv = $('div#alertDiv.alert');
//
//     alertDiv.fadeOut("fast");
//
//     if (alertDiv.hasClass('alert-success')) alertDiv.removeClass('alert-success');
//     if (alertDiv.hasClass('alert-danger')) alertDiv.removeClass('alert-danger');
//     if (alertDiv.hasClass('alert-info')) alertDiv.removeClass('alert-info');
//     if (alertDiv.hasClass('alert-warning')) alertDiv.removeClass('alert-warning');
//
//     alertDiv.addClass("alert-" + alertType);
//     alertDiv.html("[<strong>" + alertType.toUpperCase() + "</strong>] " + alertMessage);
//
//     alertDiv.fadeIn("fast");
// };


// POPUP
var procPopup = function(reqTitle, reqMessage, procFunction, reqButtonText) {
    var objButtonText = $('#popupButtonText');

    if (reqTitle == null || reqTitle.length < 1) return false;
    if (reqMessage == null || reqMessage.length < 1) return false;
    if (procFunction == null) return false;

    var buttonText = null == reqButtonText || '' == reqButtonText ? reqMessage.split(' ')[0] : reqButtonText;

    $('#popupTitle').html(reqTitle);
    $('#popupMessage').html(reqMessage);

    objButtonText.html(buttonText);
    objButtonText.attr('onclick', procFunction);

    $('div.modal').modal('toggle');
};


// CLOSE POPUP
var procClosePopup = function() {
    // modal을 닫을때 ID 지정 사용하고자 할때 사용 - Argument(ID) 수용하도록 수정
    if(arguments.length > 0){
        $('#'+arguments[0]).modal('hide');
    }else{
        $('div.modal').modal('hide');
    }
};

// SET LEFT MENU
var procSetLeftMenu = function() {

    var currentPage = document.location.href;

    if (currentPage.indexOf("http://") > -1) {
        currentPage = currentPage.substr(7);
        var tempArr = currentPage.split('/');

        //
        //var tempObj = $('#' + tempArr[1]);
        //var cssString = "display: block; position: relative; line-height: 22px; " +
        //    "color: #696e7e; background: #fff url(/resources/images/groupsmenu03.png) 0px 0px no-repeat; " +
        //    "font-weight: bold;";
        //tempObj.attr('style', cssString);
        //
        //$('.panel-heading').attr('aria-expanded', false);
        //$('.panel-collapse .collapse').removeClass('in');
        //
        //tempObj.parent().parent().parent().prev().attr('aria-expanded', true);
        //tempObj.parent().parent().parent().addClass('in');
        //
        var currentPath = "";
        for(var i = 1; i < tempArr.length; i++){
            currentPath += "/"+tempArr[i];
        }

        $('a[href="'+currentPath+'"]').parent().addClass("active");
        $('a[href="'+currentPath+'"]').parent().parent().css("display","block");
        $('a[href="'+currentPath+'"]').parent().parent().parent().addClass("menu-open");

    }
};


// SERIALIZE OBJECT OF FORM
jQuery.fn.serializeObject = function() {
    var jsonObj = null;

    try {
        if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
            var arr = this.serializeArray();

            if (arr) {
                jsonObj = {};
                jQuery.each(arr, function() {
                    jsonObj[this.name] = this.value;
                });
            }
        }
    } catch(e) {
        console.log(e.message);
    }

    return jsonObj;
};


// CONVERT OBJECT TO JSON
var objToJson = function(formData) {
    var data = formData;
    var jsonObj = {};

    $.each(data, function(idx, ele) {
        jsonObj[ele.name] = ele.value;
    });

    return jsonObj;
};


// MOVE PAGE
var procMovePage = function(pageUrl) {
    if (pageUrl == null || pageUrl.length < 1){
        return false;
    }
    console.log(pageUrl);
    location.href = pageUrl;
};


// MOVE PAGE
var procDownload = function(filePath, fileName) {
    if (filePath == null || filePath.length < 1) return false;
    if (fileName == null || fileName.length < 1) return false;

    var reqDownloadUrl = "/download/url";
    reqDownloadUrl += "?url=" + filePath + "&originalFileName=" + fileName;
    reqDownloadUrl = encodeURI(reqDownloadUrl);

    // window.open('about:blank').location.href = reqDownloadUrl;
    location.href = reqDownloadUrl;
};


// CHECK VALID NULL OR EMPTY
var procCheckValidNull = function(reqValue) {
    if (typeof reqValue === 'object') return null != reqValue;
    if (typeof reqValue === 'boolean') return null != reqValue;
    if (typeof reqValue === 'string') return !(null == reqValue || '' == reqValue);
    if (typeof reqValue === 'number') return !(null == reqValue || '' == reqValue);
};


// CHECK VALID STRING SPACE
var procCheckValidStringSpace = function(reqString) {
    if (undefined != reqString && null != reqString) {
        return (0 != reqString.replace(/ /g,"").length);
    }

    var objString = $('.toCheckString');
    if (undefined == objString || null == objString) {
        procAlert("warning", RESULT_STATUS_FAIL_MESSAGE);
        return false;
    }

    var objStringLength = objString.length;
    for (var i = 0; i < objStringLength; i++) {
        if (0 == objString[i].value.replace(/ /g,"").length) {
            objString.eq(i).focus();
            //procAlert("warning", INFO_EMPTY_REQUEST_DATA);
            notifyAlert('warning','',INFO_EMPTY_REQUEST_DATA);
            return false;
        }
    }

    return true;
};

// CHECK VALID STRING SPACE
var procCheckValidStringSpaceClass = function(classString) {

    var objString = eval("$('."+classString+"')");  //$('.toCheckString');

    console.log(objString);
    if (undefined == objString || null == objString) {
        //procAlert("warning", RESULT_STATUS_FAIL_MESSAGE);
        notifyAlert('warning','',RESULT_STATUS_FAIL_MESSAGE);
        return false;
    }

    var objStringLength = objString.length;
    for (var i = 0; i < objStringLength; i++) {

        // SELECT 일시 조건 VALIDATION 추가
        if(objString[i].nodeName == "SELECT"){
            if(objString[i].options.length < 1){
                objString.eq(i).focus();
                notifyAlert('warning','',INFO_EMPTY_REQUEST_DATA);
                return false;
            }
        }else if (0 == objString[i].value.replace(/ /g,"").length) {
            objString.eq(i).focus();
            //procAlert("warning", INFO_EMPTY_REQUEST_DATA);
            notifyAlert('warning','',INFO_EMPTY_REQUEST_DATA);
            return false;
        }
    }

    return true;
};


// CHECK VALID URL
var procCheckValidUrl = function(reqUrl) {
    if (!procCheckValidNull(reqUrl)) return false;

    var urlExp = /http:\/\/([\w\-]+\.)+/g;
    var urlExp2 = /https:\/\/([\w\-]+\.)+/g;
    return urlExp.test(reqUrl) || urlExp2.test(reqUrl);
};


function SelectMoveRows(SS1,SS2)
{
    var SelID='';
    var SelText='';
    // Move rows from SS1 to SS2 from bottom to top
    for (i=SS1.options.length - 1; i>=0; i--)
    {
        if (SS1.options[i].selected == true)
        {
            SelID=SS1.options[i].value;
            SelText=SS1.options[i].text;
            var newRow = new Option(SelText,SelID);
            SS2.options[SS2.length]=newRow;
            SS1.options[i]=null;
        }
    }
    SelectSort(SS2);
}

function SelectSort(SelList)
{
    var ID='';
    var Text='';
    for (var x=0; x < SelList.length - 1; x++)
    {
        for (var y=x + 1; y < SelList.length; y++)
        {
            if (SelList[x].value > SelList[y].value)
            {
                // Swap rows
                ID=SelList[x].value;
                Text=SelList[x].text;
                SelList[x].value=SelList[y].value;
                SelList[x].text=SelList[y].text;
                SelList[y].value=ID;
                SelList[y].text=Text;
            }
        }
    }
}

var LIST_DETAIL_PROC_URL = "/v2/codedetail";


function getInitMarketPlaceURL() {
    var param = {"searchKeyword": "MARKET_ADMIN_URL"};
    procCallAjax(LIST_DETAIL_PROC_URL+"?key="+key, "GET", param, procCallbackGetCodeDetailList, $('#serviceModalLoadingBar'));
}

function procCallbackGetCodeDetailList(data) {
    var market_html = "";
    for(var i = 0; i < data.list.length; i++){
        if(data.list[i].groupId == "MARKET_ADMIN_URL" && data.list[i].useYn == "Y"){
            market_html += "<li><a onclick=\"goMarketPlace('" + data.list[i].value + "')\" style='cursor: pointer'><i class=\"fa fa-circle-o\" style='cursor: pointer'></i>"+data.list[i].key+"</a></li>";
        }
    }
    if(market_html == ""){
        $('#market_head').remove();
    }else{
        $('#market_place').html(market_html);
    }
}

function goMarketPlace(no){
    window.open(no);
}
