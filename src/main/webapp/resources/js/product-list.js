(function($){
	var controller = new Controller();
	
	controller.buildPage = function(){
		// get the first page
		this.queryData({'page':1}, 'get-product-list', $.proxy(this.creatProductList, this));
	};
	controller.intializePaganitor = function(pageNumber){
		var self = this;
		$('#article-paging').twbsPagination({
	        
			 totalPages: pageNumber,
			 first:'<<',
			 prev:'<',
			 next:'>',
			 last:'>>',
			 
		      
		     onPageClick: function (event, page) {
		    	 var param = self.getConditions();
		    	 param.page = page;
		    	 self.queryData(param, 'get-product-list', $.proxy(self.creatProductList, self));
		     }
		 });
	};
	controller.bindMyPageHander = function(){
		var self = this;
		this.intializePaganitor($('#article-paging').attr('page-number'));
		 $('#menu2').slicknav({
				label: '',
				duration: 1000,
				easingOpen: "easeOutBounce", //available with jQuery UI
				prependTo:'#demo2'
			});
		 $('#menu2').slicknav('open');
		 $('#menu2').slicknav('openHot');
		 $('.slicknav_nav a:first').toggleClass('active');
		 $('.slicknav_nav a[role=menuitem]').on('click', function(e){
			 if($(this).hasClass('active')){
				 return ;
			 }
			 
			 $('.slicknav_nav a').removeClass('active');
			 
			 var $el = $(this);
			 if($el.find('a').length > 0){
				$el = $el.find('a').first();
			 }
			 
			 var href = $el.attr('href');
			 var param = {'page':1};
			 if(href.indexOf('status') != -1){
				 var idx = href.indexOf('status');
				 var val = href.substring(idx + 7, idx+8);
				 param.status = val;
			 }
			 if(href.indexOf('strategy') != -1){
				 var idx = href.indexOf('strategy');
				 var val = href.substring(idx + 9, idx+10);
				 param.strategy = val;
			 }
			 
			 $(this).toggleClass('active');
			 
			 self.queryData(param, 'get-product-list', $.proxy(self.creatProductList, self));
		     //return false;
		 });
		
	};
	controller.getConditions = function(){
		var $el = $('.slicknav_nav a.active');
		var param = {};
		if($el.length == 0){
			return param;
		}
		
		if($el.find('a').length > 0){
			$el = $el.find('a').first();
		}
		var href = $el.attr('href');
		if(href && href.indexOf('status') != -1){
			 var idx = href.indexOf('status');
			 var val = href.substring(idx + 7, idx+8);
			 param.status = val;
	    }
		if(href && href.indexOf('strategy') != -1){
			 var idx = href.indexOf('strategy');
			 var val = href.substring(idx + 9, idx+10);
			 param.strategy = val;
		}
		return param;
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
				data[i].statusClass="product-hot";
				data[i].statusName="火热销售";
				
			}
			else if(data[i].status == 1){
				data[i].statusClass="product-to-open";
				data[i].statusName="即将推出";
				data[i].buyDisabled = "disabled";
			}
			else if(data[i].status== 2){
				data[i].statusClass="product-closed";
				data[i].statusName="封闭运行";
				data[i].buyDisabled = "disabled";
			}
			else{
				data[i].statusClass="product-closed";
				data[i].statusName="往期产品";
				data[i].buyDisabled = "disabled";
			}
			
			
			if(!data[i].hasNetValue){
				data[i].newestNetVal = "N/A";
				data[i].totalNetVal = "N/A";
			}
			else{
				
				data[i].totalNetVal = Math.round( data[i].totalNetVal * 100 )/ 100;
				data[i].newestNetVal = Math.round( data[i].newestNetVal * 100) /100;
			
			}
			
			
		}
		var self = this;
		$.get('/resources/template/product-list.tmpl').done(function(html){
		
		    var template = Hogan.compile(html);
		    data.auth = $('#ul-list').attr('auth') == "true" ? true : false;
		    
		    html = template.render({products: data});
		   // $('#ul-list').empty();
		    $('#table-header').nextAll().remove();
		    $('#table-header').after(html);
		    if(data.auth == true){
		    	$('#ul-list').append('<div class="add-article-btn"><a class="btn btn-default btn-xl" href="add-product">添加产品</a>'+
		    			'<form id="file-form" method="POST" action="uploadFile" enctype="multipart/form-data"> '+
		    			'<input id="file-uploader" name="file" type="file" style="display:none"> <input type="text" name="name" id="file-name" style="display:none">'+
		    			'<div class="btn btn-default btn-xl" id="upload-excel">更新净值</div></form></div>');
		    	$('#upload-excel').on('click', function(){
		    		$('#file-uploader').trigger('click');
		    	});
		    	$('#file-uploader').on('change', function(){
		    		$('#file-name').val(this.value);
		    		$.blockUI({ message: '更新中...' });
		    		$('#file-form').submit();
		    	});
		    	
		    	
		    }
		    
		 
		    $.proxy(self.bindDeleteEvents, self)();
		});
		
	
	};
	
	controller.bindDeleteEvents = function(){
		$("[action=delete]").on('click', $.proxy(this.deleteProduct, this));
	};
	
	
	controller.deleteProduct = function(e){
		var id = $(e.target).data('id');
		bootbox.confirm("确定删除吗？", $.proxy(function(result){
			if(result){
				this.saveToServer('remove_product?id='+id);
			}
		},this)); 
	};
	
	controller.initialize();
	
	
})(jQuery);