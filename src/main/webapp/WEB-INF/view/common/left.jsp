<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li class="active"><a href="/main">메인</a></li>
		<li class="active"><a href="/board/detailBoard">게시판 관리</a></li>
		<c:forEach items="${boardList}" var="board">
		<c:if test="${board.board_avail == 0 }">
			<li class="active"><a href="/post/postPageList?page=1&pageSize=10&board_id=${board.board_id}" >${board.board_subject}</a></li>
		</c:if>
		</c:forEach>
	</ul>
</div>