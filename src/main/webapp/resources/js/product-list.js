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
			
			if(data[i].strategy == 1){
				data[i].strategyClass="article-type-solon";
				data[i].strategyName = "ALPHA策略";

			}
			else{
				data[i].strategyClass="article-type-media";
				data[i].strategyName = "CTA策略"
			}
			
			if(data[i].status == 0){//close
				data[i].statusClass="open";
				data[i].statusName="开放";
				
			}
			else{
				data[i].statusClass="open";
				data[i].statusName="开放";
			}
			
		}
		
		$.get('/resources/template/product-list.tmpl').done(function(html){
		
		    var template = Hogan.compile(html);
		    html = template.render({products: data});
		    $('#ul-list').empty();
		    $('#ul-list').append(html);
			 
			
			
		});
		
	
	};
	controller.initialize();
	
	
})(jQuery);