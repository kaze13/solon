(function($){
	
	var controller = new Controller();
	
	controller.bindMyPageHander = function(){
		$('.modify-product-confirm-btn').on('click', $.proxy(this.saveProductData, this));
		$('.delete-product-btn').on('click', $.proxy(this.deleteProductData, this));
		$('.add-product-confirm-btn').on('click', $.proxy(this.addProduct, this));
		this.mode = $('[mapping=productId]').data('mode');
		
		$('table.netvalue').on( 'page.dt',   $.proxy(this.initializeTable, this) );
		$('table.netvalue').on( 'order.dt',   $.proxy(this.initializeTable, this) );
		
		
		this.initializeTable();
	}
	
	controller.initializeTable = function(){
		var self = this;
		setTimeout(function(){
			self.createDatepicker($( ".datepicker" ));
			$('.remove-value-btn').on('click', $.proxy(self.deleteNetValue, self));
			$('.netvalue input').on('change', $.proxy(self.changeNetValue, self));
			$('.add-value-btn').on('click', $.proxy(self.addNewNetValue, self));
		}, 20);
		
	};
	
	controller.getNetValuesToSave = function(){
		var $table = $('.netvalue tbody');
		var $trs = $table.find('tr');
		
		var data = {
			toInsert:[],
			toDelete:[],
			toModify:[]
		};
		
		$trs.each(function(idx, tr){
			var $tr = $(tr);
			
			if($tr.hasClass('new-value')){
				data.toInsert.push(getLineValue($tr));
			}
			else if($tr.hasClass('delete-line')){
				data.toDelete.push(getLineValue($tr));
			}
			else if($tr.hasClass('modified')){
				data.toModify.push(getLineValue($tr));
			}
			
			function getLineValue($tr){
				var data = {};
				$tr.find('[net-mapping]').each(function(idx, input){
					var $input = $(input);
					var mappedCol = $input.attr('net-mapping');
					data[mappedCol] = $input.val();
				});
				data.productId = $('[mapping=productId]').val();
				return data;
			}
			
		});
		
		return data;
	};
	controller.deleteNetValue = function(e){
		var $tr = $(e.target).parent().parent();
		if($tr.hasClass('new-value')){
			$tr.remove();
			return ;
		}
		$tr.toggleClass('delete-line');
		$(e.target).toggleClass('delete-line');
		if($(e.target).hasClass('delete-line')){
			$(e.target).text('(将删除)撤销');
		}
		else{
			$(e.target).text('删除');
		}
	};
	controller.addNewNetValue = function(e){
		var $tableHeader = $('.netvalue tbody');
		$.get('/resources/template/add-new-netvalue.tmpl').done($.proxy(function(html){
			var $html = $(html);
			$tableHeader.prepend($html);
			this.createDatepicker($html.find('.datepicker'));
			$html.find('.remove-value-btn').on('click', $.proxy(this.deleteNetValue, this));
		}, this));
		
	};
	controller.changeNetValue = function(e){
		$(e.target).parent().parent().addClass('modified');
	};
	
	controller.addProduct = function(){
		bootbox.confirm("确定添加产品吗？", $.proxy(this.executeAddProduct, this)); 
	};
	
    controller.createEditor = function(){
		this.editor = CKEDITOR.replace( 'editor1', {
			height:'550px',
		});
	};
	controller.afterSave = function(){
		if(this.mode == "add" || this.mode == "del"){
			window.location.href="product-list";	
		}
		else if(this.mode == "modify"){
			window.location.reload();
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
			this.mode = "del";
			var id = $('[mapping=productId]').val();;
			this.saveToServer('remove_product?id=' + id);
		}
	};
	controller.afterDelete = function(){
		window.location.href="product-list";
	};
	controller.executeAddProduct = function(result){
		if(result){
			var toSave = {};
			var netValueData = this.getNetValuesToSave();
			toSave.netValue = netValueData;
			toSave.productValue = this.getDataToSave();
			this.saveToServer('add_product', toSave);
		}
	};
	controller.executeSave = function(result){
		if(result){
		
			var toSave = {};
			var netValueData = this.getNetValuesToSave();
			toSave.netValue = netValueData;
			toSave.productValue = this.getDataToSave();
			this.saveToServer('update_product1', toSave);
		}
	};
	
	controller.createDatepicker = function($widget){
		$widget.datepicker({
		      showOn: "button",
		      buttonImage: "/resources/img/calendar.png",
		      buttonImageOnly: true,
		      dateFormat:"yy-mm-dd",
		      buttonText: "Select date"
		});
	};
	controller.buildPage = function(){
		
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