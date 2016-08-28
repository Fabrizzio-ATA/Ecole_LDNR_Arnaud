<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<br><p class="error_msg"> <c:if test="${!empty(msg_erreur)}"> <c:out value="${msg_erreur}"  escapeXml="false"></c:out></c:if></p>