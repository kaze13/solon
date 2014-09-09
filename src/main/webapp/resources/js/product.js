$(document).ready(
		function() {
			$('body').scrollspy({
				target : '.bs-docs-sidebar',
				offset : 40
			});

			$('.value-graph-container').each(
					function() {
						var productId = $(this).data('id');
						var series = [ {
							name : $(this).data('name'),
							data : []
						} ];
						$('.net-value-item-' + productId).each(
								function() {
									var newItem = [];
									newItem.push($(this).find('.evalue-date')
											.text());
									newItem.push(parseFloat($(this).find(
											'.net-value').text()));
									series[0].data.push(newItem);
								});
						$(this).highcharts({
							title : {
								text : '净值走势',
								x : -20
							// center
							},

							xAxis : {
								categories : []
							},
							yAxis : {
								title : {
									text : '净值（元）'
								},
								plotLines : [ {
									value : 0,
									width : 1,
									color : '#808080'
								} ]
							},
							tooltip : {
								valueSuffix : ''
							},
							legend : {
								layout : 'vertical',
								align : 'right',
								verticalAlign : 'middle',
								borderWidth : 0
							},
							series : series
						});
					})

			$('#add-product-btn').click(function() {
				$('#add-product-section').slideToggle();
			});

			$('#add-product-confirm-btn').click(function() {
				var data = {};
				data.productName = $('#productName').val();
				data.productShortName = $('#productShortName').val();
				data.status = parseInt($('#status').val());
				data.strategy = parseInt($('#strategy').val());
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
				data.subscriptionFee = parseFloat($('#subscriptionFee').val());
				data.annualManageFee = parseFloat($('#annualManageFee').val());
				data.floatManageFee = parseFloat($('#floatManageFee').val());
				data.subscriptionAccount = $('#subscriptionAccount').val();
				data.subscriptionBank = $('#subscriptionBank').val();
				data.subscriptionProcess = $('#subscriptionProcess').val();

				$.ajax({
					data : JSON.stringify(data),
					contentType : "application/json",
					type : 'POST',
					url : "add_product",
				}).done(function() {
					window.location.reload();
				})
			})

			$('.modify-product-btn').click(function() {
				$('.modify-td').show();
				$('.display-td').hide();
				$(this).hide();
				$(this).next().show();

			})
			
			$('.delete-product-btn').click(function(){
				$.ajax({
					contentType : "application/json",
					type : 'POST',
					url : "remove_product?id=" + $(this).data('id'),
				}).done(function() {
					window.location.reload();
				})	
			})

			$('.modify-product-confirm-btn').click(
					function() {
						$('.modify-td').hide();
						$('.display-td').show();
						$(this).hide();
						$(this).prev().show();
						var id = $(this).data('id');
						var data = {};
						data.productId = parseInt(id);
						data.productName = $('#productName' + '-' + id).val();
						data.productShortName = $(
								'#productShortName' + '-' + id).val();
						data.status = parseInt($('#status' + '-' + id).val());
						data.strategy = parseInt($('#strategy' + '-' + id)
								.val());
						data.range = $('#range' + '-' + id).val();
						data.manager = $('#manager' + '-' + id).val();
						data.minInvest = $('#minInvest' + '-' + id).val();
						data.adoptionPeriod = $('#adoptionPeriod' + '-' + id)
								.val();
						data.closePeriod = $('#closePeriod' + '-' + id).val();
						data.createDate = $('#createDate' + '-' + id).val();
						data.openDate = $('#openDate' + '-' + id).val();
						data.watchingOrg = $('#watchingOrg' + '-' + id).val();
						data.trustee = $('#trustee' + '-' + id).val();
						data.bank = $('#bank' + '-' + id).val();
						data.borker = $('#borker' + '-' + id).val();
						data.conselor = $('#conselor' + '-' + id).val();
						data.subscriptionFee = parseFloat($(
								'#subscriptionFee' + '-' + id).val());
						data.annualManageFee = parseFloat($(
								'#annualManageFee' + '-' + id).val());
						data.floatManageFee = parseFloat($(
								'#floatManageFee' + '-' + id).val());
						data.subscriptionAccount = $(
								'#subscriptionAccount' + '-' + id).val();
						data.subscriptionBank = $(
								'#subscriptionBank' + '-' + id).val();
						data.subscriptionProcess = $(
								'#subscriptionProcess' + '-' + id).val();

						$.ajax({
							data : JSON.stringify(data),
							contentType : "application/json",
							type : 'POST',
							url : "update_product",
						}).done(function() {
							window.location.reload();
						})
					})

		})
