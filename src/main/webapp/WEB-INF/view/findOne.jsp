<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<div id="findOne">
<h3>상세보기</h3>
<ul>
<li style="list-style: none"><img src="/img/pbimg.jpg"  title="사진" style="width: 100px; height: 100px;"></li>
</ul>
<label>이름 : </label>
<input type="text" id="name2" name="name" value="${list.name}">
<label>번호 : </label> 
<input type="text" id="hp2" name="hp" value="${list.hp}">
<label>메모 : </label>
<input type="text" id="memo2" name="memo" value="${list.memo}">
</div>
<div id="btn">
<input type="button" value="수정" onclick="update(${list.idx})">
<input type="button" value="삭제" onclick="deletelist(${list.idx})">
</div>


