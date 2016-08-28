<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:import url="../inc/top.jsp" />
<title>Groupe scolaire - Fiche de création ou de modification
	d'un(e) enseignant(e)</title>
<c:import url="../inc/header.jsp" />

<c:import url="../inc/nav.jsp" />
<main>
<section>
	<h2>Fiche de création ou de modification d'un enseignant</h2>
	<form method="post" action="inscriptionEnseignant">
		<fieldset>
			<legend>Fiche de création ou de modification d'un enseignant </legend>
			<p>Vous pouvez inscrire un enseignant ou modifier sa fiche</p>
			
			<label for="prenom">Prénom : <span class="requis">*</span></label>
			<input type="text" id="prenom" name="prenom" 
				value="<c:out value='${enseignant.civ.prenom}'/>" 
				size="20" maxlength="64" placeholder="Prénom" 
			/>
			<span class="erreur">${form.erreurs['prenom']}</span>
			<br />

			<label for="nom">Nom : <span class="requis">*</span></label>
			<input type="text" id="nom" name="nom" 
				value="<c:out value='${enseignant.civ.nom}'/>" 
				size="20" maxlength="64"  placeholder="Nom"
			/>
			<span class="erreur">${form.erreurs['nom']}</span>
			<br />

			<label for="dob">Date de naissance :<span class="requis">*</span> </label>
			<input type="date" id="dob" name="dob" 
				value="<c:out value='${enseignant.civ.date}'/>" size="10"/>
			<span class="erreur">${form.erreurs['dob']}</span>
			<br />

			<label for="sexe">Sexe: <span class="requis">*</span></label>
			<select  id="sexe" name="sexe">
				<option value="H" <c:if test="${enseignant.civ.sexe=='H' || enseignant.civ.sexe=='h'}">selected</c:if>>H</option>
				<option value="F" <c:if test="${enseignant.civ.sexe=='F' || enseignant.civ.sexe=='f'}">selected</c:if>>F</option>
			</select> 
			<br>
			<span class="erreur">${form.erreurs['sexe']}</span>
			<br />


			<p><strong>Adresse : </strong> </p>

			<label for="voie">Voie : <span class="requis">*</span> </label>
			<input type="text" id="voie" name="voie" 
				value="<c:out value='${enseignant.adr.voie}'/>" 
				size="40" maxlength="64" 
			/>
			<span class="erreur">${form.erreurs['voie']}</span>
			<br />

			<label for="cp">Code Postal : <span class="requis">*</span></label>
			<input type="text" id="cp" name="cp" 
				value="<c:out value='${enseignant.adr.cp}'/>" 
				size="5" maxlength="5" 
			/>
			<span class="erreur">${form.erreurs['cp']}</span>
			<br />

			<label for="ville">Ville : <span class="requis">*</span></label>
			<input type="text" id="ville" name="ville" 
				value="<c:out value='${enseignant.adr.ville}'/>" 
				size="20" maxlength="45" 
			/>
			<span class="erreur">${form.erreurs['ville']}</span>
			<br />

			<br />
			<input type="hidden" name="id" value="${param.id}">
			 
			<input type="button" onclick="window.location.replace('listeEnseignant')" value="Annuler" class="sansLabel"/>	
			<input type="submit" value="Valider" class="sansLabel" />
			<p>Les champs marqués d'un <span class="requis">*</span>
			sont obligatoires.</p>
			<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.rapport}</p>
			
			</fieldset>
	</form>
</section>
<br>
<c:import url="../inc/erreur.jsp" />
</main>

<c:import url="../inc/footer.jsp" />