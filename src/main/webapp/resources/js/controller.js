
(function($){
	
	var Controller = function(emptyCheckFields){
		
		this.emptyCheckFields = emptyCheckFields;
		
		var proto = Controller.prototype;
		if(!proto.ini){
			proto.ini = true;
			
			proto.initialize = function(){
				
				$(document).on('ready', $.proxy(this.afterDocReady, this));				
			};

			proto.afterDocReady = function(){
				this.buildPage();
				this.bindMyPageHander();
				
			};
			proto.buildPage = function(){
				
			};
			
			proto.bindMyPageHander = function(){
				
			};
			
			proto.checkEmptyValidate = function(){
				if(this.emptyCheckFields){
					for(var i = 0; i < this.emptyCheckFields.length; i++){
						if(emptyCheckFields[i].val() === ""){
							emptyCheckFields[i].focus();
							return false;
						}
					}
					
				}
				return true;
			};
			proto.validate = function(data){
				if(this.checkEmptyValidate()){
					return true;
				}
				return false;
			};
			
			proto.processBeforeSave = function(data){
				//Extend if you like.
			};
			
			proto.showSuccessAlert = function(){
				bootbox.alert("操作成功");
			};
			
			proto.showFailAlert = function(){
				bootbox.alert("操作失败");
			};
			proto.defaultOperationSuccess = function(){
				if(this.appendResModal){
					this.showSuccessAlert();
				}
				else{
					var $def = this.appendResultModal();
					$.when(this.appendResultModal()).done(function(){
						this.showSuccessAlert();
					});
				}
			};
			proto.defaultOperationFail = function(){
				if(this.appendResModal){
					this.showFailAlert();
				}
				else{
					var $def = this.appendResultModal();
					$.when(this.appendResultModal()).done(function(){
						this.showFailAlert();
					});
				}
			};
			proto.saveToServer = function(url, data){
				if(this.validate(data)){
					//save to server
					this.processBeforeSave(data);
					var self = this;
					$.ajax({
						data : JSON.stringify(data),
						contentType : "application/json",
						type : 'POST',
						url : url,
						beforeSend : function() {
				               $.blockUI({ message: 'OK' });
				        }, 
				        complete: function () {
				                bc.find('.submit').removeClass('lock');
				                 $.unblockUI();
				        }
				            
					}).done(function() {
					
						$.proxy(self.defaultOperationSuccess, self)();
						
						window.location.reload();
					}).fail(function(){
						
						$.proxy(self.defaultOperationFail, self)();
						
					})
				}
			};
			
			proto.queryData = function(param, url, callback){
				
			    var self = this;
			    
				$.ajax({
					data : JSON.stringify(param),
					contentType : "application/json",
					type : 'POST',
					url : url,
					beforeSend : function() {
			               $.blockUI({ message: 'LOADING' });
			        }, 
			        complete: function () {
			               // bc.find('.submit').removeClass('lock');
			               $.unblockUI();
			        }
				}).done(function(data) {
					callback(data);
					
				}).fail(function(){
					
				});
				
			};
			
			proto.renderData = function(){
				
			};
			
		}
		
	};
	window.Controller = Controller; //export the controller
	
})(jQuery);