<!--
=================================================================
* 시  스  템  명 : PaaS-TA 관리자 포탈
* 업    무    명 : 관리자 대시보드
* 프로그램명(ID) : main.jsp(관리자대시보드조회)
* 프로그램  개요 : 관리자 대시보드를 조회하는 화면
* 작    성    자 : 김도준
* 작    성    일 : 2016.04.26
=================================================================
수정자 / 수정일 :
수정사유 / 내역 :
=================================================================
-->
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp"%>

<%--FOR CHARTS--%>
<script type="text/javascript" src="<c:url value='/resources/js/_highcharts.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/js/_exporting.js' />"></script>

<%--TITLE--%>
<div class="col-sm-6 pt30">
	<h4 class="modify_h4 fwn">대시보드</h4>
</div>

<div class="row">
	<!--그래프-->
	<div class="panel content-box col-sm-3 col-md-10 col-md-offset-13" ;>
		<ul class="chart-list" style="margin: 0 auto;">
			<div id="chartContainer" style="width: 100%; min-height: 400px; margin: 0 auto; display: none;"></div>
			<div id="emptySpaceMessageArea" style="display: none;">
				<h4 class="modify_h4 tac" style="min-height: 200px; line-height: 200px;"> 생성된 공간이 없습니다. </h4>
			</div>
		</ul>
	</div>
	<!--그래프-->

	<!--카운트-->
	<div class="panel-navy content-box col-sm-3 col-md-1 col-md-offset-13">
		<div class="col-sm-12 pt30 tar">
			<h1 id="organizationCount" style="color:#fff; display: none;">0</h1>
			<p class="mt15" style="color:#e7e7e7; margin-right:3px">조직 수</p>
			<hr>
			<h1 id="spaceCount" style="color:#fff; display: none;">0</h1>
			<p class="mt15" style="color:#e7e7e7; margin-right:3px">공간 수</p>
			<hr>
			<h1 id="applicationCount"  style="color:#fff; display: none;">0</h1>
			<p class="mt15" style="color:#e7e7e7; margin-right:3px">APP 수</p>
			<hr>
			<h1 id="userCount" style="color:#fff; display: none;">0</h1>
			<p class="mt15" style="color:#e7e7e7; margin-right:3px">사용자 수</p>
		</div>
	</div>
	<!--카운트-->
</div>

<%--HIDDEN VALUE--%>
<input type="hidden" id="organizationId" name="organizationId" value="<c:out value='${ORGANIZATION_ID}' default='' />" />


<%--
====================================================================================================
SCRIPT BEGIN
====================================================================================================
--%>


<script type="text/javascript">

    var MAIN_DASH_BOARD_URL = "<c:url value='/main' />";
    var MAIN_DASH_BOARD_ORGANIZATION_URL = "<c:url value='/main/organization' />";
    var TOTAL_COUNT_LIST_PROC_URL = "<c:url value='/main/getTotalCountList' />";
    var TOTAL_ORGANIZATION_LIST_PROC_URL = "<c:url value='/main/getTotalOrganizationList' />";
    var TOTAL_SPACE_LIST_PROC_URL = "<c:url value='/main/getTotalSpaceList' />";


    // GET LIST
    var getTotalCountList = function() {
        procCallAjax(TOTAL_COUNT_LIST_PROC_URL, {}, procCallbackGetTotalCountList);
    };


    // GET LIST CALLBACK
    var procCallbackGetTotalCountList = function(data) {
        var resultList = data.list[0];
        var organizationCount = $('#organizationCount');
        var spaceCount = $('#spaceCount');
        var applicationCount = $('#applicationCount');
        var userCount = $('#userCount');

        organizationCount.text(resultList.organizationCount);
        spaceCount.text(resultList.spaceCount);
        applicationCount.text(resultList.applicationCount);
        userCount.text(resultList.userCount);

        organizationCount.fadeIn();
        spaceCount.fadeIn();
        applicationCount.fadeIn();
        userCount.fadeIn();

        var organizationId = $('#organizationId').val();

        if ('' == organizationId) {
            getTotalOrganizationList();
        } else {
            getTotalSpaceList(organizationId);
        }
    };


    // GET LIST
    var getTotalOrganizationList = function() {
        procCallAjax(TOTAL_ORGANIZATION_LIST_PROC_URL, {}, procCallbackGetTotalOrganizationList);
    };


    // GET LIST CALLBACK
    var procCallbackGetTotalOrganizationList = function(data) {
        setOrganizationChart(data);
    };


    // GET LIST
    var getTotalSpaceList = function(reqOrganizationId) {
        procCallAjax(TOTAL_SPACE_LIST_PROC_URL, {organizationId : reqOrganizationId}, procCallbackGetTotalSpaceList);
    };


    // GET LIST CALLBACK
    var procCallbackGetTotalSpaceList = function(data) {
        setSpaceChart(data);
    };


    // SET MAIN CHART
    var setOrganizationChart = function(data) {
        var resultList = data.list;
        var chartContainer = $('#chartContainer');
        var listLength = resultList.length;

        // SET OPTIONS
        var chartOptions = setOrganizationChartOptions(resultList);

        // EXECUTE CHART
        chartContainer.css('height', listLength * 80 + 'px');
        chartContainer.fadeIn();
        chartContainer.highcharts(chartOptions);

        // SET LINK
        var arrayOrganizationName = $('.highcharts-xaxis-labels text');

        for (var i = 0; i < listLength; i++) {
            arrayOrganizationName[i].onclick = function() {
                procMovePage(MAIN_DASH_BOARD_ORGANIZATION_URL + "/" + getOrganizationId($(this).children().text(), resultList));
            };
        }

        $('.highcharts-xaxis-labels tspan').on("mouseover", function() {
            $(this).attr('style', 'cursor: pointer;');
        });
    };


    // GET ORGANIZATION ID
    var getOrganizationId = function(reqOrganizationName, reqList) {
        var resultList = reqList;
        var listLength = resultList.length;
        var resultOrganizationId = '';

        for (var i = 0; i < listLength; i++) {
            if (reqOrganizationName == resultList[i].organizationName) {
                resultOrganizationId = resultList[i].organizationId;
            }
        }

        return resultOrganizationId;
    };


    // SET CHART OPTIONS
    var setOrganizationChartOptions = function(resultList) {
        var listLength = resultList.length;
        var arrayOrganizationName = [];
        var arraySpaceCount = [];
        var arrayApplicationCount = [];
        var arrayUserCount = [];
        var organizationName = '';

        for (var i = 0; i < listLength; i++) {
            organizationName = resultList[i].organizationName;
            arrayOrganizationName.push(organizationName);

            for (var j = 0; j < listLength; j++) {
                if (organizationName == resultList[j].organizationName) {
                    arraySpaceCount.push(resultList[j].spaceCount);
                    arrayApplicationCount.push(resultList[j].applicationCount);
                    arrayUserCount.push(resultList[j].userCount);
                }
            }
        }

        arrayOrganizationName.join(',');
        arraySpaceCount.join(',');
        arrayApplicationCount.join(',');
        arrayUserCount.join(',');

        return {
            legend: {
                verticalAlign: 'top',
                align: 'left',
                itemStyle: {
                    'font-size': '16px', 'font-family':'"Spoqa Han Sans","Helvetica Neue",Helvetica,Arial,sans-serif', 'color':'#494747'
                }
            },
            colors: ['#77807e', '#c0c0c0', '#738de0'],
            chart: {
                type: 'bar'
            },
            title: {
                text: ''
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: arrayOrganizationName,
                labels: {
                    style: {'font-size': '16px', 'font-family':'"Spoqa Han Sans","Helvetica Neue",Helvetica,Arial,sans-serif', 'color':'#494747'}
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                }
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            credits: {
                enabled: false
            },
            series: [{
                name: 'APP',
                data: arrayApplicationCount
            }, {
                name: '공간',
                data: arraySpaceCount
            }, {
                name: '사용자',
                data: arrayUserCount
            }]
        };
    };


    // SET MAIN CHART
    var setSpaceChart = function(data) {
        var resultList = data.list;
        var chartContainer = $('#chartContainer');
        var listLength = resultList.length;

        // CHECK EMPTY SPACE
        if (0 == listLength) {
            setViewEmptySpaceMessageArea();
            return false;
        }

        // SET OPTIONS
        var chartOptions = setSpaceChartOptions(resultList);

        // EXECUTE CHART
        chartContainer.css('height', listLength * 80 + 'px');
        chartContainer.fadeIn();
        chartContainer.highcharts(chartOptions);

        // VIEW BUTTON
        $('#btnMoveMainArea').fadeIn();

        // BIND :: BUTTON EVENT
        $("#btnMoveMain").on("click", function() {
            procMovePage(MAIN_DASH_BOARD_URL);
        });
    };


    // VIEW EMPTY SPACE MESSAGE AREA
    var setViewEmptySpaceMessageArea = function () {
        $('#emptySpaceMessageArea').fadeIn();
        $('#btnMoveMainArea').fadeIn();

        $("#btnMoveMain").on("click", function() {
            procMovePage(MAIN_DASH_BOARD_URL);
        });
    };


    // SET CHART OPTIONS
    var setSpaceChartOptions = function(resultList) {
        var listLength = resultList.length;
        var arraySpaceName = [];
        var arrayApplicationCount = [];
        var spaceName = '';

        for (var i = 0; i < listLength; i++) {
            spaceName = resultList[i].spaceName;
            arraySpaceName.push(spaceName);

            for (var j = 0; j < listLength; j++) {
                if (spaceName == resultList[j].spaceName) {
                    arrayApplicationCount.push(resultList[j].applicationCount);
                }
            }
        }

        arraySpaceName.join(',');
        arrayApplicationCount.join(',');

        return {
            colors: ['#77807e', '#c0c0c0', '#738de0'],

            chart: {
                type: 'bar'
            },
            title: {
                text: ''
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: arraySpaceName,
                labels: {
                    style: {'font-size': '16px', 'font-family':'"Spoqa Han Sans","Helvetica Neue",Helvetica,Arial,sans-serif', 'color':'#494747'}
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                }
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            legend: {
                verticalAlign: 'top',
                align: 'left',
                itemStyle: {
                    'font-size': '16px', 'font-family':'"Spoqa Han Sans","Helvetica Neue",Helvetica,Arial,sans-serif', 'color':'#494747'
                }
            },
            credits: {
                enabled: false
            },
            series: [{
                name: 'APP',
                data: arrayApplicationCount
            }]
        };
    };


    // INIT PAGE
    var procInitPage = function() {
        getTotalCountList();
    };


    // ON LOAD
    $(document.body).ready(function() {
        procInitPage();
        procAlert("normal", '<spring:message code="common.system.welcome.message" />');
    });

</script>


<%--
====================================================================================================
SCRIPT END
====================================================================================================
--%>


<%--FOOTER--%>
<%@ include file="../common/footer.jsp"%>
