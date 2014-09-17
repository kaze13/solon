(function($){
	var controller = new Controller();
	
	controller.buildPage = function(){
		// get the first page
		this.queryData({'page':1}, 'get-article-list', this.creatArticleList);
	};
	
	controller.bindMyPageHander = function(){
		var self = this;
		 $('#article-paging').twbsPagination({
		        
			 totalPages: $('#article-paging').attr('page-number'),
			 first:'<<',
			 prev:'<',
			 next:'>',
			 last:'>>',
			 
		      
		     onPageClick: function (event, page) {
		    	 self.queryData({'page':page}, 'get-article-list', self.creatArticleList);
		     }
		 });
	};
	controller.creatArticleList = function(data){
	
		
		for(i = 0; i < data.length; i++){
			if(data[i].type == 1){
				data[i].typeClass="article-type-solon";
				data[i].typeName = "双隆公告";

			}
			else{
				data[i].typeClass="article-type-media";
				data[i].typeName = "媒体报告"
			}
		}
		
		$.get('/resources/template/article-list.tmpl').done(function(html){
		
		    var template = Hogan.compile(html);
		    html = template.render({articles: data});
		    $('#ul-list').empty();
		    $('#ul-list').append(html);
		    if($('#ul-list').attr('auth') == "true"){
		    	$('#ul-list').append('<div class="add-article-btn"><a class="btn btn-default btn-xl" href="add-article">新建文章</a></div>');
		    }
			 
			
			
		});
		
	
	};
	controller.initialize();
	
	
})(jQuery);