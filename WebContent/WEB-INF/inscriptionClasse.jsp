<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:import url="../inc/top.jsp" />
<title>Groupe scolaire - Fiche de création ou de modification
	d'une classe</title>
<c:import url="../inc/header.jsp" />

<c:import url="../inc/nav.jsp" />
<main>
<section>
	<h2>Fiche de création ou de modification d'une classe</h2>
	<form method="post" action="inscriptionClasse">
		<fieldset>
			<legend>Fiche de création ou de modification d'une classe </legend>
			<p>Vous pouvez créer une classe ou modifier sa fiche</p>

			<label for="nom">Nom : <span class="requis">*</span></label> <input
				type="text" id="nom" name="nom"
				value="<c:out value='${classe.nom}'/>" size="16" maxlength="16"
				placeholder="Nom" /> <span class="erreur">${form.erreurs['nom']}</span>
			<br />
						
			
			<label for="niveau">Niveau : <span class="requis">*</span></label>
			 <select id="niveau" name="niveau">
				<c:forEach items="${listeNiveaux}" var="niv">	
					<option value="${niv}"
						<c:if test="${niv==classe.niveaux}">selected</c:if>><c:out
							value="${niv}" />
					</option>
				</c:forEach>
			</select> 
			<br>
			<br>
			
			<label for="enseignant">Enseignant : </label>
			 <select id="enseignant" name="ens">
				<option value="0">-------</option>
				<c:forEach items="${liste_enseignant}" var="en">	
					<option value="${en.id}"
						<c:if test="${en.civ.prenom==classe.enseignant.civ.prenom && en.civ.nom==classe.enseignant.civ.nom}">selected</c:if>><c:out
							value="${en.civ.prenom} ${en.civ.nom }" />
					</option>
				</c:forEach>
			</select> 
			
			 <input type="hidden" name="id" value="${param.id}">
			<span class="erreur">${form.erreurs['ens']}</span> <br /> <br /> 
			<input type="button" onclick="window.location.replace('listeClasse')" value="Annuler" class="sansLabel"/>	
			<input type="submit" value="Valider" class="sansLabel" />
			<p>
				Les champs marqués d'un <span class="requis">*</span> sont
				obligatoires.
			</p>
			<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.rapport}</p>
		</fieldset>
	</form>
</section>
<br>
<c:import url="../inc/erreur.jsp" />
</main>

<c:import url="../inc/footer.jsp" />