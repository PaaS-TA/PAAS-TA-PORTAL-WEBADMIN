package org.openpaas.paasta.portal.web.admin.common;

/**
 * Constants 클래스
 *
 * @version 1.0
 * @since 2016.06.21 최초작성
 */
public class Constants {

    /**
     * 생성자
     */
    public Constants(){};

    /**
     * 검색 타입 이름
     */
    public static final String SEARCH_TYPE_NAME = "name";
    /**
     * 검색 타입 요약
     */
    public static final String SEARCH_TYPE_SUMMARY = "summary";
    /**
     * 검색 타입 값
     */
    public static final String SEARCH_TYPE_VALUE = "value";
    /**
     * 검색 타입 구분
     */
    public static final String SEARCH_TYPE_CLASSIFICATION = "classification";
    /**
     * 공통코드 페이지 번호
     */
    public static final int COMMON_CODE_PAGE_NO = 1;
    /**
     * 공통코드 페이지 크기
     */
    public static final int COMMON_CODE_PAGE_SIZE = 10;
    /**
     * 공지 아이디
     */
    public static final String SUPPORT_NOTICE_ID = "SUPPORT_NOTICE";
    /**
     * QnA 아이디
     */
    public static final String SUPPORT_QNA_ID = "QUESTION_CLASSIFICATION"; //"SUPPORT_QNA";
    /**
     * 탭 이름 스타터
     */
    public static final String TAB_NAME_STARTER = "STARTER";
    /**
     * 탭 이름 빌드팩
     */
    public static final String TAB_NAME_DEVELOP_PACK = "DEVELOP_PACK";
    /**
     * 탭 이름 서비스팩
     */
    public static final String TAB_NAME_SERVICE_PACK = "SERVICE_PACK";
    /**
     * 탭 이름 공지
     */
    public static final String TAB_NAME_NOTICE = "NOTICE";
    /**
     * 탭 이름 QNA.
     */
    public static final String TAB_NAME_QNA = "QNA";
    /**
     * 탭 이름 게시판
     */
    public static final String TAB_NAME_BOARD = "BOARD";
    /**
     * 상태 대기
     */
    public static final String STATUS_WAITING = "waiting";
    /**
     * 상태 답변 완료
     */
    public static final String STATUS_ANSWERED = "answered";
    /**
     * 사용자 관리 페이지 번호
     */
    public static final int USER_MANAGEMENT_PAGE_NO = 1;
    /**
     * 사용자 관리 페이지 크기
     */
    public static final int USER_MANAGEMENT_PAGE_SIZE = 1000;
    /**
     * 사용자 관리 상태 승인
     */
    public static final String USER_MANAGEMENT_STATUS_APPROVAL = "1";
    /**
     * 카탈로그 애플리케이션 샘플 파일 크기 제한
     */
    public static final int CATALOG_APP_SAMPLE_LIMIT_FILE_SIZE_MB = 50;
    /**
     * 파일 타입 이미지
     */
    public static final String FILE_TYPE_IMAGE = "IMAGE";
    /**
     * 파일 타입 애플리케이션 샘플
     */
    public static final String FILE_TYPE_APP_SAMPLE = "APP_SAMPLE";
    /**
     * 공통코드 그룹 처리명
     */
    public static final String PROC_NAME_COMMON_CODE_GROUP = "CommonCodeGroup";
    /**
     * 공통코드 상세 처리명
     */
    public static final String PROC_NAME_COMMON_CODE_DETAIL = "CommonCodeDetail";
    /**
     * 스타터 카탈로그 아이디
     */
    public static final String STARTER_CATALOG_ID = "STARTER_CATALOG";
    /**
     * 빌드팩 카탈로그 아이디
     */
    public static final String BUILD_PACK_CATALOG_ID = "BUILD_PACK_CATALOG";
    /**
     * 서비스팩 카탈로그 아이디
     */
    public static final String SERVICE_PACK_CATALOG_ID = "SERVICE_PACK_CATALOG";
    /**
     * 구분 문자
     */
    public static final String STRING_SEPARATOR = " :: ";
    /**
     * 문서 구분
     */
    public static final String DOCUMENTS_CLASSIFICATION = "DOCUMENTS_CLASSIFICATION";
    /**
     * 페이지 제한
     */
    public static final int PAGE_LIMIT = 20;
    /**
     * 페이지 제한
     */
    public static final int PAGE_OFFSET = 0;
    /**
     * 검색 타입 전체
     */
    public static final String SEARCH_TYPE_ALL = "ALL";
    /**
     * 검색 타입 제목
     */
    public static final String SEARCH_TYPE_TITLE = "title";
    /**
     * 검색 타입 사용자 아이디
     */
    public static final String SEARCH_TYPE_USERID = "userid";
    /**
     * 사용유무 사용
     */
    public static final String USE_YN_Y = "Y";
    /**
     * 사용유무 미사용
     */
    public static final String USE_YN_N = "N";
    /**
     * 결과 코드 성공
     */
    public static final String RESULT_STATUS_SUCCESS = "SUCCESS";
    /**
     * 결과 코드 실패
     */
    public static final String RESULT_STATUS_FAIL = "FAIL";
    /**
     * 결과 코드 실패(중복)
     */
    public static final String RESULT_STATUS_FAIL_DUPLICATED = "DUPLICATED";
    /**
     * 등록/수정/삭제 상태 등록
     */
    public static final String CUD_C = "C";
    /**
     * 등록/수정/삭제 상태 수정
     */
    public static final String CUD_U = "U";
    /**
     * 값 없음
     */
    public static final String NONE_VALUE = "NONE";
    /**
     * 내부망의 이미지 자원에 접근할 수 있도록 이미지 주소 앞에 삽입하는 문자열
     */
    public static final String IMAGE_PATH_PREFIX = "/download/getImage?imgPath=";

    /**
     * 대시보드 사용유무 사용
     */
    public static final String DASHBOARD_YN_Y = "Y";
    /**
     * 대시보드 사용유무 미사용
     */
    public static final String DASHBOARD_YN_N = "N";

    /**
     * 앱 바인드 사용유무 사용
     */
    public static final String APP_BIND_YN_Y = "Y";

    /**
     * 앱 바인드 사용유무 미사용
     */
    public static final String APP_BIND_YN_N = "N";

    /**
     * API REST URL prefix
     */
    public static final String V2_URL = "/v2";
    public static final String V3_URL = "/v3";
    /**
     * 카탈로그 관리 화면 API URL
     */
    public static final String STARTER_PACK_PROC_URL = V2_URL + "/starterpacks";
    public static final String DEVELOP_PACK_PROC_URL = V2_URL + "/developpacks";
    public static final String SERVICE_PACK_PROC_URL = V2_URL + "/servicepacks";



}
