package com.example.board.vo;

public class UserVO {
    /**사용자 아이디**/
    private String userId;
    /**비밀번호**/
    private String password;
    /**사용자 이름**/
    private String userName;
    /**가입일**/
    private String userRegDate;
    /**수정일**/
    private String userUdptDate;
    /**회원유형**/
    private String userType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRegDate() {
        return userRegDate;
    }

    public void setUserRegDate(String userRegDate) {
        this.userRegDate = userRegDate;
    }

    public String getUserUdptDate() {
        return userUdptDate;
    }

    public void setUserUdptDate(String userUdptDate) {
        this.userUdptDate = userUdptDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
