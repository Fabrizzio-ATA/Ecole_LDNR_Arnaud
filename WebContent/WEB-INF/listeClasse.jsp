<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:import url="../inc/top.jsp" />
<title>Groupe scolaire - Liste des classes</title>
<c:import url="../inc/header.jsp" />

<c:import url="../inc/nav.jsp" />
<main>
<section>
	<h2>Liste des classes</h2>
	<p>Vous êtes dans l'environnement de gestion des classes. Vous
		pouvez consulter la liste des classes...</p>
	<a href="<c:url value="inscriptionClasse" />"><button type="button"
			class="addButton">Ajouter une classe</button></a>
</section>
<section class="list clear">
	<table>
		<thead>
			<tr>
				<th><strong>Classe </strong></th>
				<th><strong>Niveau </strong></th>
				<th><strong>Professeur </strong></th>
				<th><strong>Voir / Modifier / Supprimer</strong></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.listClasse}" var="classe">
				<tr>
					<td class="nom"><c:out value="${classe.nom}"></c:out></td>
					<td class="Niveau"><c:out value="${classe.niveaux}"></c:out></td>
					<td class="Prof"><c:out
							value="${classe.enseignant.civ.prenom}"></c:out> <c:out
							value="${classe.enseignant.civ.nom}"></c:out></td>
					<td><button class="buttonInfo">i</button> <a
						href="<c:url value="inscriptionClasse?id=${classe.id}" />"><button
								class="buttonModif">M</button></a>
						<a href="<c:url value="supprimerClasse?id=${classe.id}&obj=cl" />"><button class="buttonDelete">X</button></a></td>
				</tr>
				<tr class="info">
					<td colspan=4><ul><c:forEach items="${classe.listEl}" var="el">
						<li>	<a href="<c:url value="inscriptionEleve?id=${el.id}" />"> <c:out
									value="${el.civ.prenom}"></c:out> <c:out value="${el.civ.nom}"></c:out></a> </li>
							
						</c:forEach></ul>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="<c:url value="inscriptionClasse" />"><button type="button"
			class="addButton">Ajouter une classe</button></a> <br>
</section>
<c:import url="../inc/erreur.jsp" />
</main>
<c:import url="../inc/footer.jsp" />