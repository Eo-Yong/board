<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <style>
        table {
            margin-left: auto;
            margin-right: auto;
            width:50%;
        }

        h3 {
            height: 30px;
            margin-top: 30px;
            text-align: center;
        }
    </style>
</head>
<body>
<h3>공통 게시판 로그인</h3>
<table class="table table-striped table-hover" style="width:20%;">
    <form action="<c:url value="/loginCheck.do"/>" id="loginFrm" name="loginFrm" method="post">
        <colgroup>
            <col style="width:15%">
        </colgroup>
        <tbody>
        <tr>
            <th scope="row"><label for="userId" style="width: 65px;">아이디</label></th>
            <td>
                <input type="text" id="userId" name="userId" value=""/>
            </td>
        </tr>
        <tr>
            <th scope="row"><label for="password" style="width: 65px;">비밀번호</label></th>
            <td>
                <input type="password" id="password" name="password" value=""/>
            </td>
        </tr>
        </tbody>
    </form>
</table>

<div style="text-align: center;">
    <button type="button" id="btnLogin" name="btnLogin" style="width:65px;">로그인</button>
    <button type="button" id="btnJoin" name="btnJoin" style="width:75px;">회원가입</button>
</div>

<script src="/resources/js/jquery-3.4.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function() {
        $("#btnLogin").click(function() {
            let mberId = $("#userId").val();
            let mberPw = $("#password").val();

            if(mberId == "") {
                alert("아이디를 입력하세요.");
                $("#mberId").focus();
                return false;
            }
            if(mberPw == "") {
                alert("비밀번호를 입력하세요.");
                $("#mberPw").focus();
                return false;
            }
            $("#loginFrm").submit();
        });

        <c:if test="${msg == 'failure'}">
            alert("아이디 또는 비밀번호가 일치하지 않습니다.");
        </c:if>
    });
</script>
</body>
</html>
