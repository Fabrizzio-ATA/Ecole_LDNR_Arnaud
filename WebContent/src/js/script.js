$(document).ready(function(){
	
	// Charger "list.html"
	//alert("CHARGEMENT DE LA PAGE"); 
	$("#list").load("list.html"); 
	
	// Cache les classes <info>
	$(".info").hide(); 
	// quand on clique sur le bouton "buttonInfo"	
	$(".buttonInfo").click(function(){
		$(".info").hide(); 
		// on toggle : le grand-parent (donc le tr) suivant le bouton
		$(this).parent().parent().next("tr").show(); // toggle : quand c'est cache ca le montre et inversement
	}); 

}); 
