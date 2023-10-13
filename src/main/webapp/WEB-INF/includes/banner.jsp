<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!DOCTYPE html>

<c:set var="pageText" value=""/>
<c:choose>
	<c:when test="${page eq 'post_detail' }">
		<c:set var='pageText' value='게시글 정보'/>
	</c:when>
	
	<c:when test="${page eq 'post_form' }">
		<c:set var='pageText' value='게시글 작성'/>
	</c:when>
	
	<c:when test="${page eq 'notice_list' }">
		<c:set var='pageText' value='알림 정보'/>
	</c:when>
	
	<c:when test="${page eq 'my_page' }">
		<c:set var='pageText' value='회원 정보'/>
	</c:when>

	<c:when test="${page eq 'introduce_form' }">
		<c:set var='pageText' value='소개글'/>
	</c:when>

	<c:when test="${page eq 'notice_list' }">
		<c:set var='pageText' value='알림 정보'/>
	</c:when>
	<c:when test="${page eq 'pw_change' }">
		<c:set var='pageText' value='비밀번호 수정'/>
	</c:when>
	<c:when test="${page eq 'registed_post' }">
		<c:set var='pageText' value='등록된 게시글'/>
	</c:when>
	<c:when test="${page eq 'applied_post' }">
		<c:set var='pageText' value='신청한 게시글'/>
	</c:when>
	<c:when test="${page eq 'chat' }">
		<c:set var='pageText' value='채팅방'/>
	</c:when>
</c:choose>

<nav>
	<div id="banner">
		<h3>
			<span>${loginInfo.nickName} 님</span> ${pageText}
		</h3>
	</div>
</nav>