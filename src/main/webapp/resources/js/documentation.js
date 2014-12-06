$(document).ready(function(){
	
	$('#memu-bar-container').affix({
	    offset: {
	      top: 50
	    }
	  });
	$('body').scrollspy({ target: '.navbar-example' })
	
	$('.bar-container a').on('click', function(){
		$('.bar-container a').parent().removeClass('active');
		$(this).parent().addClass('active');

		//return false;
	});
	
});