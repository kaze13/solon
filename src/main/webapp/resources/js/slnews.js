(function($){
	var controller = new Controller();
	
	controller.buildPage = function(){
		// get the first page
		this.queryData({'page':1}, 'get-article-list', $.proxy(this.creatArticleList, this));
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
		    	 self.queryData({'page':page}, 'get-article-list', $.proxy(self.creatArticleList, self));
		     }
		 });
	};
	controller.bindDeleteEvents = function(){
		$("[action=delete]").on('click', $.proxy(this.deleteArticle, this));
	};
	
	
	controller.deleteArticle = function(e){
		var id = $(e.target).data('id');
		bootbox.confirm("确定删除文章吗？", $.proxy(function(result){
			if(result){
				this.saveToServer('delete-article?id='+id);
			}
		},this)); 
	};
	
	controller.creatArticleList = function(data){
		
		if($('#ul-list').attr('auth') == "true"){
		     data.auth = true;	 
		}
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
		var self = this;
		$.get('/resources/template/article-list.tmpl').done(function(html){
		
		    var template = Hogan.compile(html);
		    html = template.render({articles: data});
		    $('#ul-list').empty();
		    $('#ul-list').append(html);
		    if(data.auth){
		    	$('#ul-list').append('<div class="add-article-btn"><a class="btn btn-default btn-xl" href="add-article">新建文章</a></div>');
		    }
			 
		    $.proxy(self.bindDeleteEvents, self)();
			
		});
		
	};
	
	
	controller.initialize();
	
	
})(jQuery);