(function($){
	var controller = new Controller();
	
	controller.buildPage = function(){
		// get the first page
		var param = this.getConditon();
    	param.page = 1;
		this.queryData(param, 'get-article-list', $.proxy(this.creatArticleList, this));
	
		var href = "'sl-news" + window.location.search + "'";
		var activeA = $('a[href=' + href + ']');
		activeA.parent().addClass('active');
	};
	controller.getConditon = function(){
		var param = {};
		var search = window.location.search;
		if(search && search.indexOf('type') != -1){
			//var key = 'type';
			var val = search.substring(6, 7);
			param['article_type'] = val; 
		}
		return param;
	};
	controller.bindMyPageHander = function(){
		var self = this;
		var pages = $('#article-paging').attr('page-number');
	    pages = pages == '0' ? 1 : pages;
	    
		 $('#article-paging').twbsPagination({
		     
		     
			 totalPages: pages,
			 first:'<<',
			 prev:'<',
			 next:'>',
			 last:'>>',
			 
		     
		     onPageClick: function (event, page) {
		    	 var param = self.getConditon();
		    	 param.page = page;
		    	 self.queryData(param, 'get-article-list', $.proxy(self.creatArticleList, self));
		     }
		 });
		 $('#memu-bar-container li').on('click', function(){
			 $('#memu-bar-container li').removeClass('active');
			 $(this).addClass('active');
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
			else if(data[i].type==2){
				data[i].typeClass="article-type-media";
				data[i].typeName = "媒体报告"
			}
			else{
				data[i].typeClass="article-type-society";
				data[i].typeName = "双隆江湖"
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