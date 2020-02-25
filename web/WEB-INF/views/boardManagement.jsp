<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <style>
        /*table {
          width:60%;
          height: 100px;
          margin: auto;
          text-align: center;
        }*/

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
<h3>게시판 분류</h3>
<div>
    <table class="table table-striped table-hover" style="width:50%;">
        <thead>
        <tr>
            <th>번호</th>
            <th>게시판유형</th>
            <th style="width: 166px;">게시판등록일</th>
            <th style="width: 166px;">사용여부</th>
        </tr>
        </thead>
        <tbody id="tListFrm">
        <c:forEach var="item" items="${list}">
            <tr style="cursor: pointer;" data-seq="${item.typeNum}">
                <td>${item.typeNum}</td>
                <td>${item.boardTypeNm}</td>
                <td>${item.boardRegDate}</td>
                <td>${item.useYn}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div style="width:75%; text-align:-webkit-right;">
    <button type="button" id="goBoardList" name="goBoardList">목록</button>
</div>

<script src="/resources/js/jquery-3.4.1.min.js"></script>
<script src="/resources/js/json2.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>

    $(document).ready(function() {
        $("#goBoardList").click(function() {
            location.href= "/boardList.do";
        });

        $("#tListFrm").delegate('tr', 'click', function() {
            let typeNum = $(this).attr("data-seq");

            if($(this).children().last().text() == "Y") {
                let check_useYn = confirm("해당 게시글 유형을 사용하지 않으시겠습니까?");

                if(check_useYn) {
                    $.ajax({
                        type:'POST',
                        url:'/changeUseYnN.do',
                        data: {typeNum: typeNum},
                        async: false,
                        dataType:'json',
                        success:function (result) {
                            let addElement = "";
                            $("#tListFrm").empty();

                            if(result.length > 0) {
                                for (let i = 0; i < result.length; i++) {
                                    addElement += '<tr data-seq="'+result[i].typeNum+'">';
                                    addElement += '<td>' + result[i].typeNum + '</td>';
                                    addElement += '<td>' + result[i].boardTypeNm + '</td>';
                                    addElement += '<td>' + result[i].boardRegDate + '</td>';
                                    addElement += '<td>' + result[i].useYn + '</td>';
                                    addElement += '</tr>'
                                }
                                addElement += result.length;
                            }
                            $("#tListFrm").append(addElement);
                            $("#tListFrm").children("tr").css("cursor", "pointer");
                        },
                        error: function (request, status, error) {
                            alert("에러가 발생하였습니다.");
                        }
                    });
                }
            }

            if($(this).children().last().text() == "N") {
                let check_useYn = confirm("해당 게시글 유형을 사용하시겠습니까?");

                if(check_useYn) {
                    $.ajax({
                        type:'POST',
                        url:'/changeUseYnY.do',
                        data: {typeNum: typeNum},
                        async: false,
                        dataType:'json',
                        success:function (result) {
                            let addElement = "";
                            $("#tListFrm").empty();

                            if(result.length > 0) {
                                for (let i = 0; i < result.length; i++) {
                                    addElement += '<tr data-seq="'+result[i].typeNum+'">';
                                    addElement += '<td>' + result[i].typeNum + '</td>';
                                    addElement += '<td>' + result[i].boardTypeNm + '</td>';
                                    addElement += '<td>' + result[i].boardRegDate + '</td>';
                                    addElement += '<td>' + result[i].useYn + '</td>';
                                    addElement += '</tr>'
                                }
                                addElement += result.length;
                            }
                            $("#tListFrm").append(addElement);
                            $("#tListFrm").children("tr").css("cursor", "pointer");
                        },
                        error: function (request, status, error) {
                            alert("에러가 발생하였습니다.");
                        }
                    });
                }
            }
        });
    });
</script>
</body>
</html>
