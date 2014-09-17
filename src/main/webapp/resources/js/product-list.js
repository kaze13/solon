(function($){
	var controller = new Controller();
	
	controller.buildPage = function(){
		// get the first page
		this.queryData({'page':1}, 'get-product-list', this.creatProductList);
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
		    	 self.queryData({'page':page}, 'get-product-list', self.creatProductList);
		     }
		 });
	};
	controller.creatProductList = function(data){
	
		
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
				data[i].statusClass="product-close";
				data[i].statusName="关闭";
				data[i].buyDisabled = "disabled";
				
			}
			else if(data[i].status == 1){
				data[i].statusClass="product-open";
				data[i].statusName="开放";
				data[i].buyDisabled = "";
			}
			else{
				data[i].statusClass="product-future";
				data[i].statusName="即将开放";
				data[i].buyDisabled = "disabled";
			}
			
			if(!data[i].hasNetValue){
				data[i].newestNetVal = "N/A";
				data[i].totalNetVal = "N/A";
			}
			else{
				data[i].totalNetVal += " %";
				data[i].newestNetVal +=  " %";
			}
			
			
		}
		
		$.get('/resources/template/product-list.tmpl').done(function(html){
		
		    var template = Hogan.compile(html);
		    html = template.render({products: data});
		    $('#ul-list').empty();
		    $('#ul-list').append(html);
		    if($('#ul-list').attr('auth') == "true"){
		    	$('#ul-list').append('<div class="add-article-btn"><a class="btn btn-default btn-xl" href="add-product">添加产品</a></div>');
		    }
		});
		
	
	};
	controller.initialize();
	
	
})(jQuery);