(function($){
	var controller = new Controller();
	
	controller.buildPage = function(){
		// get the first page
		this.queryData({'page':1}, 'get-product-list', this.creatArticleList);
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
		    	 self.queryData({'page':page}, 'get-product-list', self.creatArticleList);
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
		
		$.get('/resources/template/product-list.tmpl').done(function(html){
		
		    var template = Hogan.compile(html);
		    html = template.render({products: data});
		    $('#article-list').empty();
		    $('#article-list').append(html);
			 
			
			
		});
		
	
	};
	controller.initialize();
	
	
})(jQuery);