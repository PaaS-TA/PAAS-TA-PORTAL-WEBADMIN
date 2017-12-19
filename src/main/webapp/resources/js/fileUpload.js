var MAX_SIZE = 500; // KB

// FILE FORM DATA
var getFileFormData = function() {
    var reqThumbnail = $("#hiddenThumbnail");

    if (reqThumbnail[0].files[0] === undefined) return undefined;

    var formData = new FormData();

    formData.append("file", reqThumbnail[0].files[0]);

    return formData;
};


// IMAGE UPLOAD FILE
var uploadFile = function(formData, reqUploadUrl, reqProcFunction) {
    $.ajax({
        url : reqUploadUrl
        , method : "POST"
        , processData : false
        , contentType : false
        , data : formData
        , dataType : "json"
        , success : function(data){
            procCallbackUploadFile(data, reqProcFunction);
        },
        error: function(xhr, status, error) {
            procAlert("danger", JSON.parse(xhr.responseText).customMessage);

            console.log("FILE UPLOAD ERROR :: error :: ", error);
        },
        complete : function(data) {
            console.log("FILE UPLOAD COMPLETE :: data :: ", data);
        }
    });
};


// IMAGE UPLOAD FILE CALLBACK
var procCallbackUploadFile = function(data, reqProcFunction) {
    if (RESULT_STATUS_SUCCESS == data.RESULT) {
        $("#thumbImgPath").val(data.path);
        $("#thumbImgName").val($("#labelThumbName").text());

        reqProcFunction(data);

    } else {
        procAlert("danger", data.RESULT_MESSAGE);
    }
};


// PREVIEW
var setPreView = function(objThumbnail) {
    var thisForm = objThumbnail[0];
    var objFile = thisForm.files[0];
    var objPreview = $('#preview');
    var imgSize = 0;

    if (objFile) {
        // image file check
        var pathHeader = thisForm.value.lastIndexOf("\\");
        var pathMiddle = thisForm.value.lastIndexOf(".");
        var pathEnd = thisForm.value.length;
        var fileName = thisForm.value.substring(pathHeader + 1, pathEnd);
        var ext = thisForm.value.substring(pathMiddle + 1, pathEnd).toLowerCase();

        if (ext != "jpg" && ext != "png" && ext != "gif") {
            procAlert('danger', '이미지 파일(jpg, png, gif)만 등록 가능합니다.');
            //jasny bootstrap fileinput을 이용함. 참고는 http://www.jasny.net/bootstrap/javascript/#fileinput 참조바람
            resetThumbnail();
            return false;
        }

        // file 읽어 오기
        var fileReader = new FileReader();

        fileReader.onload = function(e) {
            var objImage = new Image();
            // 파일의 내용을 읽어서 넣어준다.
            objImage.src = e.target.result;
            imgSize = Math.round(e.total / 1024);
        };

        fileReader.onloadend = function (e) {
            var divPreviewMessage = $("#divPreviewMessage");

            if (imgSize > MAX_SIZE) {
                resetThumbnail();

                var alertMessage = '이미지 크기는 ' + MAX_SIZE + 'Kb 를 넘을 수 없습니다.';
                divPreviewMessage.text(alertMessage);
                divPreviewMessage.attr('style', 'display: block;');

                procAlert('danger', alertMessage);
                return false;
            }

            $("#thumbImgPath").val('');
            $("#thumbImgName").val('');

            $('#divPreview').attr('style', 'display: block; height: 94px;');
            divPreviewMessage.attr('style', 'display: none;');
            objPreview.attr("src", e.target.result);

            $("#labelThumbName").text(fileName);
        };

        fileReader.readAsDataURL(objFile);

    } else {
        resetThumbnail();
    }
};


// RESET THUMBNAIL
var resetThumbnail = function() {
    $('#thumbnail').filestyle('clear');
    $('#preview').attr('src', '');
    $("#labelThumbName").text('');
    // $("#labelThumbFileSize").text('');
    $('#divPreview').attr('style', 'display: none;');
    $('#divPreviewMessage').attr('style', 'display: none;');
};



// APP SAMPLE FILE UPLOAD FILE
var procUploadFile = function(formData, reqUploadUrl, reqProcFunction) {
    $.ajax({
        url : reqUploadUrl
        , method : "POST"
        , processData : false
        , contentType : false
        , data : formData
        , dataType : "json"
        , success : function(data){
            if (data) {
                reqProcFunction(data);
            } else {
                var resData = {RESULT : RESULT_STATUS_SUCCESS,
                               RESULT_MESSAGE : "<spring:message code='common.info.result.success' />"};

                reqProcFunction(resData);
            }
        },
        error: function(xhr, status, error) {
            var resData = {RESULT : RESULT_STATUS_FAIL,
                           RESULT_MESSAGE : JSON.parse(xhr.responseText).customMessage};

            reqProcFunction(resData);
            console.log("FILE UPLOAD ERROR :: error :: ", error);
        },
        complete : function(data) {
            console.log("FILE UPLOAD COMPLETE :: data :: ", data);
        }
    });
};
