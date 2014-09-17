(function($){
	
	var controller = new Controller();
	
	controller.bindMyPageHander = function(){
		$('.modify-product-confirm-btn').on('click', $.proxy(this.saveProductData, this));
		$('.delete-product-btn').on('click', $.proxy(this.deleteProductData, this));
		$('.add-product-confirm-btn').on('click', $.proxy(this.addProduct, this));
	}
	
	controller.addProduct = function(){
		bootbox.confirm("确定添加产品吗？", $.proxy(this.executeAddProduct, this)); 
	};
	
    controller.createEditor = function(){
		this.editor = CKEDITOR.replace( 'editor1', {
			height:'550px',
		});
	};
	controller.afterSave = function(){
		if($('[mapping=productId]').data('mode') == "add"){
			window.location.href="product-list";
			
		}
	};
	controller.saveProductData = function(){
		bootbox.confirm("确定保存修改吗？", $.proxy(this.executeSave, this)); 
	};
	
	controller.deleteProductData = function(){
		bootbox.confirm("确定删除产品吗？", $.proxy(this.executeDelete, this)); 
	};
	controller.executeDelete = function(result){
		if(result){
			var id = $('[mapping=productId]').val();;
			this.saveToServer('remove_product?id=' + id);
			
		}
	};
	controller.executeAddProduct = function(result){
		if(result){
			var data = this.getDataToSave();
			this.saveToServer('add_product', data);
		}
	};
	controller.executeSave = function(result){
		if(result){
			var data = this.getDataToSave();
			this.saveToServer('update_product', data);
		}
	};
	controller.buildPage = function(){
		$( "#datepicker" ).datepicker({
		      showOn: "button",
		      buttonImage: "/resources/img/calendar.png",
		      buttonImageOnly: true,
		      dateFormat:"yy-mm-dd",
		      buttonText: "Select date"
		    });
		
		this.createEditor();
	};
	
	controller.getDataToSave = function(){
		
		var $inputs = $('[mapping]');
		var data = {};
		$inputs.each(function(index, value){
			var mappingCol = $(value).attr('mapping');
			data[mappingCol] = $(value).val();
		});
		data.subscriptionProcess = this.editor.getData();
		return data;
	};
	controller.initialize();
})(jQuery);