<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:import url="../inc/top.jsp" />
<title>Groupe scolaire - Liste des élèves</title>
<c:import url="../inc/header.jsp" />

<c:import url="../inc/nav.jsp" />
<main>
<section>
	<h2>Liste des élèves</h2>
	<p>Vous êtes dans l'environnement de gestion des élèves. Vous
		pouvez consulter la liste des élèves...</p>
	<a href="<c:url value="inscriptionEleve" />"><button type="button"
			class="addButton">Ajouter un(e) élève</button></a>
</section>
<section class="list clear">
	<table>
		<thead>
			<tr>
				<th><strong>Prénom </strong></th>
				<th><strong>Nom </strong></th>
				<th><strong>Classe </strong></th>
				<th><strong>Voir / Modifier / Supprimer</strong></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.listEleve}" var="eleve">
				<tr>
					<td class="prenom"><c:out value="${eleve.civ.prenom}"></c:out>
					</td>
					<td class="nom"><c:out value="${eleve.civ.nom}"></c:out></td>
					<td class="classe"><c:out value="${eleve.nomClasse}"></c:out>
					</td>
					<td><button class="buttonInfo">i</button> <a
						href="<c:url value="inscriptionEleve?id=${eleve.id}" />"><button
								class="buttonModif">M</button></a>
						<a href="<c:url value="supprimerEleve?id=${eleve.id}&obj=el" />"><button class="buttonDelete">X</button></a></td>
				</tr>
				<tr class="info">
					<td colspan=4>né le <c:out value="${eleve.civ.dateDisplay}"></c:out>
						<br> <c:forEach items="${eleve.adr}" var="adr" varStatus="vs">
							<br> Adresse n°${vs.count} : <c:out value="${adr.voie}"></c:out>
							<br>
							<c:out value="${adr.cp}"></c:out>
							<c:out value="${adr.ville}"></c:out>
							<br>
						</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="<c:url value="inscriptionEleve" />"><button type="button"
			class="addButton">Ajouter un(e) élève</button></a> <br>
</section>
<c:import url="../inc/erreur.jsp" />
</main>
<c:import url="../inc/footer.jsp" />