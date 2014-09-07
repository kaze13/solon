$(document).ready(function(){
	$('#add-product-btn').click(function(){
		$('#add-product-modal').modal('show');
	});
	
	$('#add-product-confirm-btn').click(function(){
		var data = {};
		data.productId = $('#productId').val();
		data.productName = $('#productName').val();
		data.productShortName = $('#productShortName').val();
		data.status = $('#status').val();
		data.strategy = $('#strategy').val();
		data.range = $('#range').val();
		data.manager = $('#manager').val();
		data.minInvest = $('#minInvest').val();
		data.adoptionPeriod = $('#adoptionPeriod').val();
		data.closePeriod = $('#closePeriod').val();
		data.createDate = $('#createDate').val();
		data.openDate = $('#openDate').val();
		data.watchingOrg = $('#watchingOrg').val();
		data.trustee = $('#trustee').val();
		data.bank = $('#bank').val();
		data.borker = $('#borker').val();
		data.conselor = $('#conselor').val();
		data.subscriptionFee = $('#subscriptionFee').val();
		data.annualManageFee = $('#annualManageFee').val();
		data.floatManageFee = $('#floatManageFee').val();
		data.subscriptionAccount = $('#subscriptionAccount').val();
		data.subscriptionBank = $('#subscriptionBank').val();
		data.subscriptionProcess = $('#subscriptionProcess').val();
		
		$.ajax({
			data:data,
			contentType:"application/json",
			type:'POST',
			url:"add_product",
		}).done(function(){
			
		})
	})
});