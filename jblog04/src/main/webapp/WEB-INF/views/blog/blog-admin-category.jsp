<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.4.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>
<script type="text/javascript">
var listItemTemplate = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/list-item-template.ejs"
});

var listTemplate = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/item-template.ejs"
});

var messageBox = function(title, message, callback){
	$("#dialog-message p").text(message);
	$("#dialog-message")
		.attr("title", title)
		.dialog({
			modal: true,
			buttons: {
				"확인": function() {
					$(this).dialog( "close" );
		        }
			},
			close: callback
		});
};

// 리스트 불러오기
var fetchList = $(function() {
	
	$.ajax({
		url: '${pageContext.request.contextPath }/${authUser.id }/api/category/list',
		async: true,
		type: 'get',
		dataType: 'json',
		data: '',
		success: function(response){
			if(response.result != "success"){
				console.error(response.message);
				return;
			}
			
			response.data.contextPath = '${pageContext.request.contextPath }';
			response.data.userId = '${authUser.id }';
			
			
			var html = listTemplate.render(response);
			$('.admin-cat').append(html);
			
		},
		error: function(xhr, status, e){
			console.log(status + ":" + e);
		}
	});
});

// 메세지 박스
var messageBox = function(title, message, callback){
	$("#dialog-message p").text(message);
	$("#dialog-message")
		.attr("title", title)
		.dialog({
			modal: true,
			buttons: {
				"확인": function() {
					$(this).dialog( "close" );
		        }
			},
			close: callback
		});
}
$(function() {
	// 카테고리 추가
	$('#categoryForm').submit(function(event) {
		event.preventDefault();
		var vo = {};
		vo.name = $('#name').val();
		if(vo.name == ''){
			messageBox("카테고리 추가", "제목은 필수 항목 입니다.", function(){
				$("#name").focus();
			});
			return;	
		}
		vo.desc = $('#desc').val();
		if(vo.desc == ''){
			messageBox("카테고리 추가", "설명은 필수 항목 입니다.", function(){
				$("#desc").focus();
			});
			return;	
		}
		
		$.ajax({
			url: '${pageContext.request.contextPath }/${authUser.id }/api/category/add',
			async: true,
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(vo),
			success: function(response){
				if(response.result != "success"){
					console.error(response.message);
					return;
				}
				// rendering
				// render(response.data, true);
			response.data.contextPath = '${pageContext.request.contextPath }';
				var html = listItemTemplate.render(response);
				$("#title-category").after(html);
				$('#categoryForm')[0].reset();
			},
			error: function(xhr, status, e){
				console.error(status + ":" + e);
			}
		});
	});
	
	// 카테고리 삭제
	$(document).on('click','.delete-category',function(){
		
		var no = $(this).attr('id');
		
		$.ajax({
			url: '${pageContext.request.contextPath }/${authUser.id }/api/category/delete',
			async: true,
			type: 'DELETE',
			dataType: 'json',
			data: 'no=' + no,
			success: function(response){
				if(response.result != "success"){
					console.error(response.message);
					return;
				}
				
			},
			error: function(xhr, status, e){
				console.error(status + ":" + e);
			}
		});
		
		$(this).parents(".tr").remove();
	}); 
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/adminHeader.jsp"/>
      	<table class="admin-cat">
      		<tr id = "title-category">
      			<th>번호</th>
      			<th>카테고리명</th>
      			<th>포스트 수</th>
      			<th>설명</th>
      			<th>삭제</th>      			
      		</tr>
		</table>
  		<h4 class="n-c">새로운 카테고리 추가</h4>
  		<form id = "categoryForm" action="" method="post">
	      	<table id="admin-cat-add">
	      		<tr>
	      			<td class="t">카테고리명</td>
	      			<td><input type="text" id="name"></td>
	      		</tr>
	      		<tr>
	      			<td class="t">설명</td>
	      			<td><input type="text" id="desc"></td>
	      		</tr>
	      		<tr>
	      			<td class="s">&nbsp;</td>
	      			<td><input type="submit" value="카테고리 추가"></td>
	      		</tr>      		      		
	      	</table> 
  		</form>
		<div id="dialog-message" title="" style="display:none;">
			<p></p>
		</div>	
	</div>
	</div>
	<div id="footer">
		<p>
			<strong>Spring 이야기</strong> is powered by JBlog (c)2016
		</p>
	</div>
	</div>
</body>
</html>