<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:import url="../inc/top.jsp" />
<title>Groupe scolaire - Fiche de création ou de modification
	d'un élève</title>
<c:import url="../inc/header.jsp" />

<c:import url="../inc/nav.jsp" />
<main>
<section>
	<h2>Fiche de création ou de modification d'un élève</h2>
	<form method="post" action="inscriptionEleve">
		<fieldset>
			<legend>Fiche de création ou de modification d'un élève </legend>
			<p>Vous pouvez inscrire un elève ou modifier sa fiche</p>

			<label for="prenom">Prénom de l'élève : <span class="requis">*</span></label>
			<input type="text" id="prenom" name="prenom"
				value="<c:out value='${eleveBean.civ.prenom}'/>" size="20"
				maxlength="64" placeholder="Prénom" /> <span class="erreur">${form.erreurs['prenom']}</span>
			<br /> 
			
			<label for="nom">Nom : <span class="requis">*</span></label>
			<input type="text" id="nom" name="nom"
				value="<c:out value='${eleveBean.civ.nom}'/>" size="20"
				maxlength="64" placeholder="Nom" /> <span class="erreur">${form.erreurs['nom']}</span>
			<br /> 
			
			<label for="dob">Date de naissance :<span class="requis">*</span> </label>
			 <input type="date" id="dob" name="dob"
				value="<c:out value='${eleveBean.civ.date}'/>" size="10" /> 
				<span class="erreur">${form.erreurs['dob']}</span>
			<br /> 
			
			
			<label for="sexe">Sexe: <span class="requis">*</span></label>
			<select  id="sexe" name="sexe">
				<option value="H" <c:if test="${eleveBean.civ.sexe=='H' || eleveBean.civ.sexe=='h'}">selected</c:if>>H</option>
				<option value="F" <c:if test="${eleveBean.civ.sexe=='F' || eleveBean.civ.sexe=='f'}">selected</c:if>>F</option>
			</select> 
			<span class="erreur">${form.erreurs['sexe']}</span>
			<br>
			
			
			<label for="classe">Classe à choisir : </label>
			 <select id="classe" name="clas">
				<option value="0">-------</option>
				<c:forEach items="${listeclasse}" var="cl">
					<option value="${cl.id }"
						<c:if test="${cl.nom == eleveBean.nomClasse}">selected</c:if>>
						<c:out value="${cl.nom}" />
					</option>
				</c:forEach>
			</select> <span class="erreur">${form.erreurs['classC']}</span> <br />


			<p>
				<strong>1<sup>ère</sup> adresse :
				</strong>
			</p>

			<label for="adr1">Voie : <span class="requis">*</span> </label> 
			<input type="text" id="adr1" name="adr1"
				value="<c:out value='${eleveBean.adr[0].voie}'/>" size="40"
				maxlength="64" /> <span class="erreur">${form.erreurs['adr1']}</span>
			<br />
			
			<label for="cp1">Code Postal : <span class="requis">*</span></label>
			<input type="text" id="cp1" name="cp1"
				value="<c:out value='${eleveBean.adr[0].cp}'/>" size="5"
				maxlength="5" /> <span class="erreur">${form.erreurs['cp1']}</span>
			<br /> 
			
			<label for="ville1">Ville : <span class="requis">*</span></label>
			<input type="text" id="ville1" name="ville1"
				value="<c:out value='${eleveBean.adr[0].ville}'/>" size="20"
				maxlength="45" /> <span class="erreur">${form.erreurs['ville1']}</span>
			<br />

			<p>
				<strong>2<sup>ème</sup> adresse :
				</strong>
			</p>

			<label for="adr2">Voie : </label> 
			<input type="text" id="adr2" name="adr2"
				value="<c:out value='${eleveBean.adr[1].voie}'/>" size="40"
				maxlength="64" /> <span class="erreur">${form.erreurs['adr2']}</span>
			<br />
			
			 <label for="cp2">Code Postal : </label>
			<input type="text" id="cp2" name="cp2"
				value="<c:out value='${eleveBean.adr[1].cp}'/>" size="5"
				maxlength="5" /> <span class="erreur">${form.erreurs['cp2']}</span>
			<br />
			
			<label for="ville2">Ville : </label>
			<input type="text" id="ville2" name="ville2"
				value="<c:out value='${eleveBean.adr[1].ville}'/>" size="20"
				maxlength="45" /> <span class="erreur">${form.erreurs['ville2']}</span>
				
				
			<input type="hidden" name="id" value="${param.id}">
			<br /> <br /> 
			
			<input type="button" onclick="window.location.replace('listeEleve')" value="Annuler" class="sansLabel"/>	
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