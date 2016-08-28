<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:import url="../inc/top.jsp" />
<title>Groupe scolaire - Accueil</title>
<c:import url="../inc/header.jsp" />

<c:import url="../inc/nav.jsp" />
<main>
<section>
	<!-- Contient la présentation du groupe scolaire et le formulaire de recherche du statut d'inscription d'un élève -->
	<h2>Présentation du groupe scolaire</h2>
	<p>Le groupe scolaire Alphonse Daudet est un établissement public,
		accueillant les élèves depuis la petite section de maternelle jusqu'au
		CM2.</p>
	<hr>
</section>

<section>
	<h2>Année scolaire 2016/2017</h2>
	<h3>Inscription au CP</h3>
	<p>L'inscription des élèves de grande section de l'école maternelle
		n'est pas automatique vers le CP.</p>
	<p>Les familles doivent passer en mairie pour faire une nouvelle
		inscription à l'école élémentaire. Il est important de le faire au
		plus vite afin de permettre la composition des classes pour l'an
		prochain.</p>
	<p>Par ailleurs, la réunion de présentation de l'école élémentaire,
		de son fonctionnement, de ses projets, pour les familles des nouveaux
		entrants (CP, nouvelles arrivées) aura lieu le vendredi 27 mai à 18h,
		dans la salle de jeux de l'école élémentaire.</p>
</section>
<hr>

<section>
	<h2>Mon enfant est-il inscrit au groupe scolaire Alphonse Daudet ?</h2>
	<form method="post" action="index.html">
		<fieldset>
			<legend>Rechercher mon enfant</legend>
			<label for="nom">Nom </label><input type=text id="nom" name="nom"
				value="" size="20" maxlength="20" placeholder="Nom"> <label>Prénom
			</label><input type=text id="prenom" name="prenom" value="" size="20"
				maxlength="20" placeholder="Prénom"> <input type=submit class="sansLabel">
		</fieldset>
	</form>
	<p>
	<c:if test="${!(empty param)}">
	    <c:if test="${!(empty listEleve) }"> 
		    <c:forEach items="${listEleve}" var="el">
			    <c:out value="${el.civ.prenom} ${el.civ.nom}"/> est inscrit<c:if test="${el.civ.sexe == 'F' }">e</c:if> <c:if test="${el.nomClasse !=''}"> en  <c:out value="${el.nomClasse}"/></c:if>.		
		    </c:forEach>
	    </c:if>
	    <c:if test="${empty listEleve}"> 
		    Aucun élève inscrit ne porte ce nom.
	    </c:if>
	</c:if>
	</p>
</section>
</main>

<c:import url="../inc/footer.jsp" />
