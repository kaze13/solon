$(document).ready(function(){
	
	//document.getElementById( 'count' ).innerHTML = window.CKEDITOR_LANGS.length;

	var editor;

	function createEditor( languageCode ) {
		if ( editor )
			editor.destroy();

		// Replace the <textarea id="editor"> with an CKEditor
		// instance, using default configurations.
		editor = CKEDITOR.replace( 'editor1', {
			language: languageCode,

			on: {
				instanceReady: function() {
					// Wait for the editor to be ready to set
					// the language combo.
				
				}
			}
		});
	}


	// At page startup, load the default language:
	createEditor( '' );

});