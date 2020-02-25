<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 작성</title>
    <jsp:include page="head.jsp"></jsp:include>
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
    <h3>게시글 작성</h3>
        <table class="table table-striped table-hover" style="width:55%;">
        <form action="<c:url value="/doInsert.do"/>" id="boardFrm" name="boardFrm" method="post" enctype="multipart/form-data">
            <colgroup>
                <col style="width:15%">
            </colgroup>
            <tbody id="tListFrm">
                <tr>
                    <th scope="row"><label for="num">제목</label></th>
                    <input type="hidden" id="userId" name="userId" value="${userId}"/>
                    <input type="hidden" id="userType" name="userType" value="${userType}"/>
                    <td colspan="1">
                        <input type="text" id="num" name="title" style="width:154px;"/>
                    </td>
                    <th scope="row"><label for="boardType">게시글 유형</label></th>
                    <td colspan="1">
                        <select id="boardType" name="boardType" style="width:154px;height:28px;">
                            <option value="">선택</option>
                            <c:forEach var="item" items="${list}">
                                <option value="${item.boardType}">${item.boardTypeNm}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th scope="row"><label for="name">작성자</label></th>
                    <td colspan="2">
                        <input type="text" id="name" name="name" value="${userName}" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <th scope="row"><label for="content">내용</label></th>
                    <td colspan="6">
                        <textarea style="width:692px; height:265px; resize:none;" id="content" name="content"></textarea>
                    </td>
                </tr>
            </tbody>
        </form>
        </table>

        <div style="width:77%; text-align:-webkit-right;">
            <button type="button" id="saveContent" name="saveContent">저장</button>
            <button type="button" id="goBoardList" name="goBoardList">목록</button>
        </div>



    <jsp:include page="plugin_js.jsp"></jsp:include>
    <script id="fileTemplate" type="text/x-handlebars-template">
        <li>
        <span class="mailbox-attachment-icon has-img">
            <img src="{{imgSrc}}" alt="Attachment">
        </span>
            <div class="mailbox-attachment-info">
                <a href="{{originalFileUrl}}" class="mailbox-attachment-name">
                    <i class="fa fa-paperclip"></i> {{originalFileName}}
                </a>
                <a href="{{fullName}}" class="btn btn-default btn-xs pull-right delBtn">
                    <i class="fa fa-fw fa-remove"></i>
                </a>
            </div>
        </li>
    </script>
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <script src="/resources/js/article_file_upload.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function() {
            $("#content").val('');


            $("#goBoardList").click(function () {
                location.href= "/boardList.do";
            });

            $("#saveContent").click(function() {

                if($("#title").val() == '') {
                    alert("제목을 입력하여 주십시오.");
                    $("#title").focus();
                    return false;
                } else if($("#name").val() == '') {
                    alert("작성자명을 입력하여 주십시오.");
                    $("#name").focus();
                    return false;
                } else if($("#content").val() == '') {
                    alert("내용을 입력하여 주십시오.");
                    $("#content").focus();
                    return false;
                }

                var check_submit = confirm("작성된 내용을 저장하시겠습니까?");

                if(check_submit) {
                    $("#boardFrm").submit();
                    alert("정상처리되었습니다.");
                }
            });

            $("#boardType").delegate($(this), 'change', function() {
                if($(this).val()=="nor") {
                    $("#filePart").empty();
                    $("#galleryPart").empty();
                    alert("nor");
                } else if($(this).val()=="notice") {
                    $("#filePart").empty();
                    $("#galleryPart").empty();

                    let addElement = null;

                    addElement += '<tr id="filePart">';
                    addElement += '<th>';
                    addElement += '<label>';
                    addElement += '파일첨부';
                    addElement += '</label>';
                    addElement += '<td colspan="6">';
                    addElement += '<input type="file" id="file" name="file1" class="file"/>';
                    addElement += '<input type="file" id="file1" name="file1" class="file"/>';
                    addElement += '<input type="file" id="file2" name="file1" class="file"/>';
                    addElement += '</td>';
                    addElement += '</th>';
                    addElement += '</tr>';

                    $("#tListFrm").append(addElement);
                    $(".file").css("margin-bottom", "5px");
                } else if($(this).val()=="gallery") {
                    $("#filePart").empty();
                    $("#galleryPart").empty();

                    let addElement = null;

                    addElement += '<tr id="galleryPart">';
                    addElement += '<th>';
                    addElement += '<label>';
                    addElement += '갤러리';
                    addElement += '</label>';
                    addElement += '<td colspan="6">';
                    addElement += '<div class="form-group">';
                    addElement += '<div class="fileDrop">';
                    addElement += '<br/>';
                    addElement += '<br/>';
                    addElement += '<br/>';
                    addElement += '<br/>';
                    addElement += '<br/>';
                    addElement += '<p id="pTag" class="text-center">';
                    addElement += '첨부파일을 드래그해주세요.';
                    addElement += '</p>';
                    addElement += '</div>';
                    addElement += '</div>';
                    addElement += '<div class="box-footer">';
                    addElement += '<ul class="mailbox-attachments clearfix uploadedFileList">';
                    addElement += '</ul>'
                    addElement += '</div>';
                    addElement += '</td>';
                    addElement += '</th>';
                    addElement += '</tr>';

                    $("#tListFrm").append(addElement);
                    $(".fileDrop").css("width", "100%");
                    $(".fileDrop").css("height", "200px");
                    $(".fileDrop").css("border", "2px dotted #0b58a2");


                } else if($(this).val()=="video") {
                    $("#filePart").empty();
                    $("#galleryPart").empty();

                    alert("video");
                }
            })
        });
    </script>


</body>
</html>
