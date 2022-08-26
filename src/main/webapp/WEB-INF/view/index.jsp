<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="/css/style.css"> 금요일 평가에서는 여기에 css 넣자.-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">

header{height: 130px; width:100%; border: 1px solid black;}
h3{text-align: center;font-size: 24px;}
#insertForm{font-size: 22px;text-align: center;}
input{outline: blue;border: 2px solid rgb(120, 120, 223);font-size: 24px;}
input:focus{ box-shadow: 0 0 5px #1670BE;  outline-offset: 0px; outline: none; padding-left: 5px;}
input[type=submit]{width:150px; cursor: pointer; border-radius:10px;font-size: 22px; background-color: fuchsiagreen;color:blue; font-weight: bold;}
input[type=button]{width:150px; cursor: pointer; border-radius:10px;font-size: 22px; background-color: silver;color: white; font-weight: bold;}
main{display:flex; height: 600px; width:100%; border: 1px solid blue;}
aside{height: 600px; width:30%; border: 1px solid green; overflow: scroll;}
#content{height: 600px; width:80%; border: 1px solid olive;}
#lists{text-align: center;}
#img{display: inline;}
#img img{margin-top: 10px;}
#list{border-bottom: 2px solid gray; text-align: center;}
#findOne{text-align: center;font-size: 24px;}
#findOne h3{margin-top: 120px;}
#updateForm{font-size: 24px; text-align: center;}
#search{margin: 30px 0;text-align: center;}
footer{height: 100px; width:100%; border: 1px solid black;}
span{font-size: 18px; line-height: 600px;}
#btn{display: flex; justify-content: center; } 
#btn input[type=button]{margin: 10px;}
</style>
<script type="text/javascript">
function insert() {
	var name=document.getElementById("name").value;
	var hp=document.getElementById("hp").value;
	var memo=document.getElementById("memo").value;
	
	$.ajax(	{url : "/insert",
			type : "post",
			dataType : "text",
			data : {"name" : name,"hp" : hp,"memo" : memo},
			success : function(result){$('#list').html(result);}
			}); 
	
	document.getElementById("name").value="";
	document.getElementById("hp").value="";
	document.getElementById("memo").value="";
}
function getList() {
	$.ajax(	{url:"/list",success:function(result){$('#list').html(result);}}); 
}
function search(search) {
	$.ajax({url:"/find?search="+search,	success:function(result){$('#list').html(result);}});
}
function getPhonebook(idx) {
	$.ajax({url:"/findOne?idx="+idx, success:function(result){$('#content').html(result);}});
}
function update(idx) {
	var name=document.getElementById("name2").value;
	var hp=document.getElementById("hp2").value;
	var memo=document.getElementById("memo2").value;
	$.ajax(	{url:"/update?idx="+idx+"&name="+name+"&hp="+hp+"&memo="+memo
		,success:function(result){
			$('#list').html(result);
			location.href="/";	
		}}); 
}
function deletelist(idx) {
	var result;
	result=confirm("삭제하시겠습니까?");
	if(result==true){
		$.ajax({url:"/deletelist?idx="+idx,success:function(){alert('삭제되었습니다.'); location.href='/';}});
	}else{
		$.ajax({url:"/findOne?idx="+idx, success:function(result){alert('취소되었습니다.');$('#content').html(result);}});
	}
}
</script>
</head>
<body onload="getList()">
<header>
<h3>전화번호입력</h3>
<form  method="post" id="insertForm">
<label>이름 : </label>
<input type="text" id="name" name="name">
<label>전화번호 : </label>
<input type="text" id="hp" name="hp">
<label>메모 : </label>
<input type="text" id="memo" name="memo">
<input type="button" value="등록" onclick="insert()" >
</form>
</header>

<main>
<aside>
<div id="search">
	<label>검색</label>
	<input type="text" name="search" onkeyup="search(this.value)">
</div>
<div id="list" >
	전체 리스트 , 검색 리스트 결과
</div>
</aside>
<div id="content">
<h3>
<span>[사진을 클릭하세요.]</span>
</h3>
</div>
</main>
<footer></footer>
</body>
</html>