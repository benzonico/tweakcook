var tweakCook = tweakCook || {};

$(document).ready(function() {
	$.getJSON('/recipe', function(data) {
		var $ulRecipe = $('<ul>');
		for(var i=0;i<data.length;i++){
			$ulRecipe.append('<li>'+data[i].title+'</li>');
		}
		$('#recipes').append($ulRecipe);
	});
});