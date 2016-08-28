<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:import url="../inc/top.jsp" />
<title>Groupe scolaire - Liste des enseignants</title>
<c:import url="../inc/header.jsp" />

<c:import url="../inc/nav.jsp" />
<main>
<section>
	<h2>Liste des enseignants</h2>
	<p>Vous êtes dans l'environnement de gestion des enseignants. Vous
		pouvez consulter la liste des enseignants...</p>
	<a href="<c:url value="inscriptionEnseignant" />"><button
			type="button" class="addButton">Ajouter un(e) enseignant(e)</button></a>
</section>
<section class="list clear">
	<table>
		<thead>
			<tr>
				<th><strong>Nom </strong></th>
				<th><strong>Prenom </strong></th>
				<th><strong>Voir / Modifier / Supprimer</strong></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.listEns}" var="ens">
				<tr>
					<td class="prenom"><c:out value="${ens.civ.prenom}"></c:out></td>
					<td class="nom"><c:out value="${ens.civ.nom}"></c:out></td>
					<td><button class="buttonInfo">i</button> <a
						href="<c:url value="inscriptionEnseignant?id=${ens.id}" />"><button
								class="buttonModif">M</button></a>
						<a href="<c:url value="supprimerEnseignant?id=${ens.id}&obj=en" />"><button class="buttonDelete">X</button></a></td>
				</tr>
				<tr class="info">
					<td colspan=4>né le <c:out value="${ens.civ.dateDisplay}"></c:out> <br>
						<br> Adresse : <c:out value="${ens.adr.voie}"></c:out> <br>
						<c:out value="${ens.adr.cp}"></c:out> <c:out
							value="${ens.adr.ville}"></c:out> <br>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="<c:url value="inscriptionEnseignant" />"><button
			type="button" class="addButton">Ajouter un(e) enseignant(e)</button></a>
	<br>
</section>
<c:import url="../inc/erreur.jsp" />
</main>
<c:import url="../inc/footer.jsp" />