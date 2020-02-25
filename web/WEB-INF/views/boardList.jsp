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
    <h3>게시판 목록</h3>
    <p style="width:75%; margin-bottom:20px; text-align:-webkit-right;">${userName}님 환영합니다. <a href="#" id="logout" name="logout">로그아웃</a></p>
    <div>
      <table class="table table-striped table-hover" style="width:50%;">
        <thead>
          <tr>
            <th>번호</th>
            <th>이름</th>
            <th>제목</th>
            <th>조회수</th>
            <th>게시판유형</th>
            <th>작성일</th>
          </tr>
        </thead>
        <tbody id="tListFrm">
          <c:forEach var="item" items="${list}">
            <tr style="cursor: pointer;" data-seq="${item.num}">
              <input type="hidden" name="boardUserId" value="${item.userId}"/>
              <td>${item.num}</td>
              <td>${item.name}</td>
              <td>${item.title}</td>
              <td>${item.readCount}</td>
              <td>${item.boardTypeNm}</td>
              <td>${item.writeDate}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

    <div class="text-center" id="pagination"></div>

    <div style="width:75%; text-align:-webkit-right;">
      <button type="button" id="goBoardInsert" name="goBoardInsert">글쓰기</button>
      <button type="button" id="goBoardManagement" name="goBoardManagement">게시판관리</button>
    </div>

    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script>

      const userId = "${userId}";
      const userType = "${userType}";

      $(document).ready(function() {

          if(userType != 'adm') {
            $('#goBoardManagement').remove();
          };

          $("#tListFrm").delegate('tr', 'click', function() {
              let dataSeq = $(this).attr("data-seq");
              //alert(dataSeq);

              let url = "/boardDetail.do?num="+dataSeq;
              location.href=url;
              //alert('Click successful!');
          });

          $("input[name=boardUserId]").each(function(idx) {
            if($(this).val() == userId) {
              $(this).parent('tr').css('color', 'green');
            }
          });


          $("#goBoardInsert").click(function () {
              let url = "/boardInsert.do";
              location.href=url;
          });

          $("#logout").click(function() {

            let logout_check = confirm("로그아웃 하시겠습니까?");

            if(logout_check) {
              let url = "/logout.do";
              location.href=url;
            }
          })

          $("#goBoardManagement").click(function() {
              let url = "/boardManagement.do"
              location.href=url;
          });
      });
    </script>
  </body>
</html>
