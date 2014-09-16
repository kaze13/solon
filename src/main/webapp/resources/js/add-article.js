(function($){
	
	var controller = new Controller();
	
	controller.createEditor = function(){
		
		this.editor = CKEDITOR.replace( 'editor1', {
			height:'550px',
		});
	};

	controller.buildPage = function(){
		this.createEditor();
	};
	
	controller.getArticleData = function(){
		var data = {};
		data.title = $('#article-title').val();
		data.type = $('#article-type-selector').val();
		data.content = this.editor.getData();
		return data;
	};
	
	controller.submitArticle = function(){
		var data = this.getArticleData();
		this.saveToServer('submit-article', data);
		
	};
	
	controller.bindMyPageHander = function(){
		$('#article-submit').on('click', $.proxy(this.submitArticle, this));
	};
	
	controller.initialize();

})(jQuery);
