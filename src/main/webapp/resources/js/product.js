$(document).ready(function() {
	$('body').scrollspy({
		target : '.bs-docs-sidebar',
		offset : 40
	});

	$('.value-graph-container').each(function() {
		var productId = $(this).data('id');
		var series = [ {
			name : $(this).data('name'),
			data : []
		} ];
		$('.net-value-item-' + productId).each(function() {
			var newItem = [];
			newItem.push($(this).find('.evalue-date').text());
			newItem.push(parseFloat($(this).find('.net-value').text()));
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

})
