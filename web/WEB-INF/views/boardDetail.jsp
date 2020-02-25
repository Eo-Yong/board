<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 상세보기</title>
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
    <h3>게시판 상세</h3>
    <div id="board">
        <table class="table table-striped table-hover" style="width:55%;">
            <form action="<c:url value="/doUpdate.do"/>" id="detailFrm" name="detailFrm" method="post">
            <%--<caption>게시판 상세내용을 확인합니다.</caption>--%>
            <colgroup>
                <col style="width:15%">
                <%--<col style="width:19%">
                <col style="width:10%">
                <col style="width:20%">
                <col style="width:10%">
                <col style="width:20%">--%>
            </colgroup>
            <tbody>
                <tr>
                    <th scope="row"><label for="num">게시글 번호</label></th>
                    <input type="hidden" id="userId" name="userId" value="${detail.userId}"/>
                    <input type="hidden" id="userType" name="userType" value="${detail.userType}"/>
                    <td colspan="1">
                        <input type="text" id="num" name="num" value="${detail.num}" readonly="readonly" style="width: 29px;"/>
                    </td>
                    <th scope="row"><label for="readCount">조회수</label></th>
                    <td colspan="1">
                        <input type="text" id="readCount" name="readCount" value="${detail.readCount}" readonly="readonly" style="width: 29px;"/>
                    </td>
                    <th scope="row"><label for="name">작성자</label></th>
                    <td colspan="2">
                        <input type="text" id="name" name="name" value="${detail.name}" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <th scope="row"><label for="title">제목</label></th>
                    <td colspan="3">
                        <input type="text" id="title" name="title" value="${detail.title}"/>
                    </td>
                    <th scope="row"><label for="writeDate">작성일자</label></th>
                    <td colspan="3">
                        <input type="text" id="writeDate" name="writeDate" value="${detail.writeDate}" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <th scope="row"><label for="content">내용</label></th>
                    <td colspan="6">
                        <textarea style="width:692px; height:265px; resize:none;" id="content" name="content">${detail.content}</textarea>
                    </td>
                </tr>
            </tbody>
            </form>
        </table>

        <%--<table class="table table-striped table-hover" style="width:55%;">
            &lt;%&ndash;<caption>게시판 상세내용을 확인합니다.</caption>&ndash;%&gt;
            <colgroup>
                <col style="width:15%">
                &lt;%&ndash;<col style="width:19%">
                <col style="width:10%">
                <col style="width:20%">
                <col style="width:10%">
                <col style="width:20%">&ndash;%&gt;
            </colgroup>
            <tbody>
            <tr>
                <th scope="row">기업명</th>
                <td colspan="1">
                </td>
                <th scope="row">사업자등록번호</th>
                <td colspan="1">
                </td>
                <th scope="row">대표자명</th>
                <td colspan="2">
                </td>
            </tr>
            <tr>
                <th scope="row">회원직책</th>
                <td colspan="3">
                </td>
                <th scope="row">회원부서</th>
                <td colspan="3">
                </td>
            </tr>

            </tbody>
        </table>--%>

        <div id="btnGroup" style="width:77%; text-align:-webkit-right;">
            <button type="button" id="saveContent" name="saveContent">저장</button>
            <button type="button" id="deleteContent" name="deleteContent">삭제</button>
            <button type="button" id="goBoardList" name="goBoardList">목록</button>
        </div>
    </div>

    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <script>
        const userId = "${userId}";
        const userType = "${userType}";

        $(document).ready(function() {
            if(userType != "adm" && $("#userId").val() != userId) {
                $("#saveContent").hide();
                $("#deleteContent").hide();
            }

            $("#goBoardList").click(function() {
                location.href= "/boardList.do";
            });

            $("#saveContent").click(function() {

                if($("#title").val() == '') {
                    alert("제목을 입력하여 주십시오.");
                    $("#title").focus();
                    return false;
                } else if ($("#content").val() == '') {
                    alert("내용을 입력하여 주십시오.");
                    $("#content").focus();
                    return false;
                }
                let check_submit = confirm("작성된 내용을 저장하시겠습니까?");

                if(check_submit) {
                    $("#detailFrm").submit();
                    alert("정상처리되었습니다.");
                }
            });

            $("#deleteContent").click(function() {

               let num = $("#num").val();
               let url = "/doDelete.do?num="+num;

               let check_delete = confirm("해당 게시글을 삭제하시겠습니까?");

               if(check_delete) {
                   location.href=url;
                   alert("정상처리되었습니다.");
               }
            });
        });
    </script>
</body>
</html>
