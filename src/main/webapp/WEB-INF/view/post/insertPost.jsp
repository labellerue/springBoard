<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico" />
<script src="/SE2/js/HuskyEZCreator.js"></script> <!-- 이 라이브러리 필~요! -->

<script type="text/javascript">
var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

$(document).ready(function() {
	// Editor Setting
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors, // 전역변수 명과 동일해야 함.
		elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
		sSkinURI : "/SE2/SmartEditor2Skin.html", // Editor HTML
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true, 
		}
	});

	// 전송버튼 클릭이벤트
	$("#savebutton").click(function(){
		if(confirm("저장하시겠습니까?")) {
			// id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

			// 이부분에 에디터 validation 검증
			if(validation()) {
				$("#frm").submit();
			}
		}
		
	});
	
	
	var i = 1;
	//파일 추가하기 이벤트
	$("#moreFiles").click(function(){
		
		if(i < 6){
			$("#fileTagUl").append("<li><input type='file' name='files' /></li>");
			i++;
		}else{
			alert("파일첨부는 최대 5개 까지 가능합니다.");
		}
		
	});
	
	
});

// 필수값 Check
function validation(){
	var contents = $.trim(oEditors[0].getContents());
	if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
		alert("내용을 입력하세요.");
		oEditors.getById['smarteditor'].exec('FOCUS');
		return false;
	}
	return true;
}

</script>

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">${title}</h2>
		<br />

		<form action="/post/insertPost" method="post" id="frm"
			enctype="multipart/form-data">
			<input type="hidden" id="userId" name="post_userid" value="${userVo.userId }" /> 
			<input type="hidden" name="board_id" value="${board_id }" />
			<input type="hidden" name="post_pid" value="${post_pid == null ? 0:post_pid }" />
			<input type="hidden" name="post_groupid" value="${post_groupid == null ? 0:post_groupid}" />
			<c:forEach items="${postIds}" var="ids">
				<input type="hidden" id="${ids.key }" name="${ids.key }" value="${ids.value }" />
			</c:forEach>
			<div>
				<label class="conlabel">제목 </label>
				<input type="text" class="form-control title" name="post_title" />
			</div>
			<!-- 작성 SmartEditor -->
			<div>
				<textarea name="post_article" id="smarteditor" rows="10" cols="100"></textarea>
			</div>
			<div>
				<div class="pull-left">
					<label class="conlabel">첨부파일 </label>
				</div>
				<div class="pull-left">
					<ul id="fileTagUl">
						<!-- 스크립트로 추가될 파일 input -->
					</ul>
					<a href="#" id="moreFiles" class="btn btn-default pull-right">╋</a>
				</div>
			</div>
			<div class="btnBlock col-sm-8">
				<a href="/postPageList?page=1&pageSize=10&board_id=${board_id }"
					class="btn btn-default pull-right" id="cancelbutton">취소</a> 
				<input type="button" class="btn btn-default pull-right" id="savebutton"
					value="저장" />
			</div>
		</form>

	</div>
</div>










