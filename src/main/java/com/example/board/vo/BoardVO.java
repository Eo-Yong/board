package com.example.board.vo;

public class BoardVO {
    /**게시글 구분번호**/
    private Integer num;
    /**게시자**/
    private String name;
    /**제목**/
    private String title;
    /**내용**/
    private String content;
    /**조회수**/
    private Integer readCount;
    /**작성일**/
    private String writeDate;
    /**회원 유형**/
    private String userType;
    /**게시자 아이디**/
    private String userId;

    /**게시판유형 구분번호**/
    private String typeNum;
    /**게시판유형**/
    private String boardType;
    /**게시판등록일**/
    private String boardRegDate;
    /**사용여부**/
    private String useYn;
    /**게시판유형명**/
    private String boardTypeNm;

    /**파일리스트**/
    private String[] files;
    /**파일수**/
    private int fileCnt;

    private int loginCheck;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public int getLoginCheck() {
        return loginCheck;
    }

    public void setLoginCheck(int loginCheck) {
        this.loginCheck = loginCheck;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(String typeNum) {
        this.typeNum = typeNum;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getBoardRegDate() {
        return boardRegDate;
    }

    public void setBoardRegDate(String boardRegDate) {
        this.boardRegDate = boardRegDate;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getBoardTypeNm() {
        return boardTypeNm;
    }

    public void setBoardTypeNm(String boardTypeNm) {
        this.boardTypeNm = boardTypeNm;
    }

    public String[] getFiles() {
        return files;
    }

    /*
    * BoardVO의 인스턴스가 스스로 게시글의 첨부파일 개수의 상태를 가질 수 있게
    * setFiles()메서드를 아래와 같이 작성해준다. 이렇게 되면 외부에서 setFiles()를
    * 호출하여 게시글의 첨부파일 개수를 넣어주지 않아도 된다.
    **/
    public void setFiles(String[] files) {
        this.files = files;
        setFileCnt(files.length);
    }

    public int getFileCnt() {
        return fileCnt;
    }

    public void setFileCnt(int fileCnt) {
        this.fileCnt = fileCnt;
    }
}
