<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:forEach var="pb" items="${list}">
<div id="lists">
<div id="img">
<img src="/img/pbimg.jpg" alt="사진클릭" title="사진클릭" onclick="getPhonebook(${pb.idx})" style="width: 50px; height: 50px; border: 1px solid gray; border-radius: 10px; ">
</div>
<div id="list">
<p>이름 : <c:out value="${pb.name}"/></p>
<p>번호 : <c:out value="${pb.hp}"/></p>
<p>메모 : <c:out value="${pb.memo}"/></p>
</div>
</div>
</c:forEach>
