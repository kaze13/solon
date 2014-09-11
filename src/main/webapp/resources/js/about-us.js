$(document).ready(function(){
	
	$('#memu-bar-container').affix({
	    offset: {
	      top: 50
	    }
	  });
	$('body').scrollspy({ target: '.navbar-example' })
	
});