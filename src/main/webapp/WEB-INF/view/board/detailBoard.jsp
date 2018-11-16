<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
 #subject{
 	margin: 0;
 	width: 200px;
 }
</style>
<script>

	$(document).ready(function() {
		$(".boardClick").on("click", function() {

			var board = this.parentElement.parentElement;
			var board_id = board.children[0].firstChild.value;
			var board_subject = board.children[1].firstChild.value;
			var board_avail = board.children[2].firstChild.value;

			if(board_id == "")
				$("#board_id").val(0);
			else
				$("#board_id").val(board_id);
			$("#board_subject").val(board_subject);
			$("#board_avail").val(board_avail);
			$("#form").submit();
		});

	});
</script>
<!-- 변화가 있는 board 내용을 script에서 이곳에 담아줍니다. form은 그 내용들을 /updateBoard서블렛으로 parameter로 보냅니다. -->
<form action="/board/updateBoard" method="post" id="form">
	<input type="hidden" id="board_id" name="board_id"/>
	<input type="hidden" id="board_subject" name="board_subject"/>
	<input type="hidden" id="board_avail" name="board_avail"/>
	<input type="hidden" id="userId" name="userId" value="${userVo.userId}"/>
</form>

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">게시판 관리</h2>
		<div class="table-responsive">
			<table class="table table-striped">
				<tr>
					<th>no.</th>
					<th>게시판명</th>
					<th>사용여부</th>
					<th>작성자</th>
					<th>날짜</th>
					<th></th>
				</tr>

				<!-- forEach -->
				<c:forEach items="${boardList }" var="board" varStatus="status">
					<tr>
						<td><input type="hidden" value="${board.board_id }" />${status.index + 1 }</td>
						<td><input type="text" class="form-control subject"
							value="${board.board_subject }" /></td>
						<td><select class="form-control">
								<option value="0" ${board.board_avail == 0 ? "selected" : ""}>사용</option>
								<option value="1" ${board.board_avail == 1 ? "selected" : ""}>미사용</option>
						</select></td>
						<td>${board.board_userid }</td>
						<td><fmt:formatDate value="${board.board_date }"
								pattern="yyyy-MM-dd" /></td>
						<td><input type="submit" class="boardClick btn btn-default" value="수정" /></td>
					</tr>
				</c:forEach>

				<tr>
					<td><input type="hidden" />*</td>
					<td><input type="text" class="form-control subject"
						placeholder="새로운 게시판 추가" /></td>
					<td><select class="form-control">
							<option value="0" selected>사용</option>
							<option value="1">미사용</option>
					</select></td>
					<td></td>
					<td></td>
					<td><input type="submit" value="추가" class="btn btn-default boardClick" /></td>
				</tr>
			</table>
		</div>

	</div>
</div>
