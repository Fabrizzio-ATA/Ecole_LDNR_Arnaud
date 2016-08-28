<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:import url="../inc/top.jsp" />
<title>Groupe scolaire - Liste des classes</title>
<c:import url="../inc/header.jsp" />

<c:import url="../inc/nav.jsp" />
<main>
<section>
	<h2>Attention !</h2>
	<br>
	<form method="post" action="<c:if test="${param.obj=='el' }">supprimerEleve</c:if><c:if test="${param.obj=='en' }">supprimerEnseignant</c:if><c:if test="${param.obj=='cl' }">supprimerClasse</c:if>">
	<fieldset>
		<legend>Suppression</legend>
		<p>Etes vous sur de vouloir supprimer cet <c:if test="${param.obj=='el' }">Eleve ?</c:if>
												<c:if test="${param.obj=='en' }">Enseignant ?</c:if>
												<c:if test="${param.obj=='cl' }">Classe ?</c:if></p>
		<br>
		<input type="button" onclick="window.location.replace('<c:if test="${param.obj=='el' }">listeEleve</c:if><c:if test="${param.obj=='en' }">listeEnseignant</c:if><c:if test="${param.obj=='cl' }">listeClasse</c:if>')" value="Annuler" class="sansLabel"/>										
		<input type="hidden" name="obj" value="${param.obj}">
		<input type="hidden" name="id" value="${param.id}">
		<input type="submit" value="Confirmer" class="sansLabel" />
	</fieldset>
	</form>
</section>
<c:import url="../inc/erreur.jsp" />
</main>
<c:import url="../inc/footer.jsp" />