<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
	$(document).ready(function(){
		
		$(".post_id").hide();
		
		//tr에 select (class="postClick")
		$(".postClick").on("click", function(){
			
			var post_id = this.firstElementChild.firstElementChild.innerHTML;

			$("#post_id").val(post_id);
			$("#form").submit();
		});
		
	});
</script>

<!-- hidden으로 form을 넣는 것은 실무에서도 사용합니다! -->
<form action="/post/detailPost" method="get" id="form">
	<input type="hidden" id="post_id" name="post_id"/>
</form>
<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">${boardVo.board_subject }</h2>
		<div class="table-responsive">

			<a class="btn btn-default pull-right" href="/post/insertPost">글쓰기</a>
			<table class="table table-striped table-hover">
				<tr>
					<th>no.</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일자</th>
				</tr>

				<c:forEach items="${postPageList }" var="post">
					<c:choose>
						<c:when test="${post.post_del == 0 }">
							<tr class="postClick">
								<td><label class="post_id">${post.post_id }</label>
									${post.rnum }</td>
								<td>${post.post_title }</td>
								<td>${post.post_userid }</td>
								<td><fmt:formatDate value="${post.post_date }" pattern="yyyy/MM/dd" /></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr class="deleted">
								<td>${post.rnum }</td>
								<td colspan="3">삭제된 게시글 입니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>

		</div>

		<div class="text-center">
			<ul class="pagination">
				<li><a href="/post/postPageList?page=1&pageSize=10&board_id=${board_id}"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<li><a href="/post/postPageList?page=1&pageSize=10&board_id=${board_id}"
					aria-label="Previous"> <span aria-hidden="true">&lsaquo;</span>
				</a></li>
				<c:forEach begin="1" end="${postPageCnt}" var="pageNum">
					<li><a href="/post/postPageList?page=${pageNum }&pageSize=10&board_id=${board_id}">${pageNum }</a></li>
				</c:forEach>
				<li><a href="/post/postPageList?page=${postPageCnt}&pageSize=10&board_id=${board_id}"
					aria-label="Next"> <span aria-hidden="true">&rsaquo;</span>
				</a></li>
				<li><a href="/post/postPageList?page=${postPageCnt }&pageSize=10&board_id=${board_id}"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</div>
		<!-- 검색박스  -->
		<form action="/post/postPageList" method="get">
		<input type="hidden" name="page" value="1"/>
		<input type="hidden" name="pageSize" value="10"/>
		<input type="hidden" name="board_id" value="${board_id}"/>
		<div class="text-center">
			제목 &nbsp;&nbsp; <input type="text" name="searchBox" class="searchBox" />
			<input type="submit" class="goSearch btn btn-default" value="검색"/>
		</div>
		</form>
	</div>
</div>





















