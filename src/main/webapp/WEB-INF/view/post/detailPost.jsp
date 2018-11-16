<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
$(document).ready(function(){
	var userId = document.getElementById("userId").value;
	var post_id = document.getElementById("post_id").value;
	
	//console.log("post_id는 "+post_id);
	
	getCommHtml(post_id, userId);
	
	
}); 
 
function getCommHtml(post_id, userId){
	//console.log("post_id 값 왔나" + post_id);
	
	$.ajax({
		contentType: "application/json;charset=utf-8",
		url: "/post/getComm",
		type: "get",   	
		data: "post_id="+post_id,
		dataType: "json",
		success: function(dt){
			var html = "";
			//console.log("dt가 뭘까?" + dt);
			
			$.each(dt.commList, function(idx, comm){
			console.log("comm이 왔나?" + comm);
				if(comm.comm_del == 0){
					/* 댓글  */
					html +='<div style="display:block; width: 100%; height: 40px; margin-bottom: 2px;">';
					html +='<form action="/comm/deleteComm" method="post" class="pull-right">';
					html +='<label class="commL">'+comm.comm_note+'</label>';
					html +='<label class="commL2">'+comm.comm_userid+'</label>';
					html +='<label class="commL3">'+comm.formatDate+'</label>';
					html +='<input type="hidden" name="comm_id" value="'+ comm.comm_id +'" /> ';
					html +='<input type="hidden" name="post_id" value="'+ comm.post_id +'" />';
					html +='</form>';
					
					
					/* 댓글 삭제  버튼 */
					if(userId == comm.comm_userid){
						html +='<input type="submit" class="btn btn-default noTopMarg deleteComm" value="삭제" />';
					}
					html +='</div>';
					
				}else{
					html +='<label class="deleted">삭제된 댓글입니다.</label>';
				}
			});
			$(".commAjax").html(html);
		}
			
	});
} 
</script>
<input type="hidden" id="userId" value="${userVo.userId }"/>
<input type="hidden" id="post_id" value="${detailPost.post_id }"/>

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">게시글 내용</h2>
		<br />
		<div class="thinDiv">
			<a class="btn btn-default pull-right"
				href="/post/insertReplyPost?post_id=${detailPost.post_id}&post_groupid=${detailPost.post_groupid}">
				답글</a>
		</div>
		<div class="thinDiv">
			<label class="conlabel">제목 </label>
			<label class="form-control title detailTitle" id="">${detailPost.post_title }</label>
		</div>
		<div class="thinDiv">
			<label class="conlabel">작성자 </label>
			<label class="form-control title detailTitle" id="">${detailPost.post_userid }</label>
		</div>
		<!-- 작성 SmartEditor -->
		<div id="article">
			<p>${detailPost.post_article }</p>
		</div>
		<div class="fileBox">
			<div class="pull-left">
			<!-- 첨부파일 -->
				<label class="conlabel filelabel">첨부파일 </label>
			</div>
			<div class="pull-left">
				<c:forEach items="${filesList }" var="file">
					<div class="fileDiv">
						<img src="${file.file_path }" width="200" class="fileA" />
						<%-- <img src="/fileDownload?userId=${userDetail.userId }" width="216" /> --%> 
						<a href="${file.file_path}" class="fileA">${file.org_file_name }</a>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="btnBlock col-sm-8 thinDiv">
			<c:if test="${userVo.userId == detailPost.post_userid }">
				<form method="post" action="/post/deletePost" id="form">
					<button id="deletePost" class="btn btn-default pull-right">삭제</button>
					<input type="hidden" name="post_id" value="${detailPost.post_id }" />
					<input type="hidden" name="board_id" value="${detailPost.board_id }" />
				</form>
				<a href="/post/updatePost?post_id=${detailPost.post_id }"
					class="btn btn-default pull-right">수정</a>
			</c:if>
		</div>
		<div class="commDiv">
			<hr />
			<label class="conlabel">댓글 </label>
			<div class="commDiv commAjax">
			</div>
			
			
			<%-- <c:forEach items="${commList }" var="comm">
				<div class="commDiv">
					<c:choose>
						<c:when test="${comm.comm_del == 0 }">
							<label class="commL">${comm.comm_note }</label>
							<label class="commL2">${comm.comm_userid }</label>
							<label class="commL3"><fmt:formatDate value="${comm.comm_date }" pattern="yyyy/MM/dd" /></label>
						</c:when>
						<c:otherwise>
							<label class="deleted">삭제된 댓글입니다.</label>
						</c:otherwise>
					</c:choose>
					<!-- 댓글 삭제 -->
					<c:if test="${userVo.userId == comm.comm_userid && comm.comm_del < 1}">
						<form action="/comm/deleteComm" method="post" class="pull-right" id="commForm">
							<input type="hidden" name="comm_id" value="${comm.comm_id }" /> 
							<input type="hidden" name="post_id" value="${comm.post_id }" />
						</form>
					</c:if>
				</div>
			</c:forEach> --%>
			
			
			
			
		</div>
		<!-- 댓글 등록 -->
		<form action="/comm/insertComm" method="post">
			<div class="commDiv commInDiv thinDiv ">
				<input type="hidden" name="post_id" value="${detailPost.post_id }" />
				<input type="hidden" name="comm_userid" value="${userVo.userId }" />
				<input type="text" name="comm_note" id="comm_note" class="form-control commIn" maxlength="500" /> 
				<input type="submit" class="btn btn-default pull-right noTopMarg" id="commbutton" value="댓글등록" />
			</div>
		</form>

	</div>
</div>









