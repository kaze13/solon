(function($){
	var controller = new Controller();
	controller.buildPage = function(){
		$('#myTab a:last').tab('show');
		$('.collapsible').collapsible({
		      defaultOpen: 'nav-section1,nav-section3'
		});
		$('.init-open').collapsible('open');
		this.drawNetValueGraph();
	};
	
	controller.drawNetValueGraph = function(){
		var $widget = $('.value-graph-container');
		var productId = $widget.data('id');
		var series = [ {
			name : $widget.data('name'),
			data : []
		}];
		
		$('.net-value-item-' + productId).each(
			function() {
				var newItem = [];
				newItem.push($(this).find('.evalue-date').text());
				newItem.push(parseFloat($(this).find('.net-value').text()));
				series[0].data.push(newItem);
		});
		if(series[0] && series[0].data){
			series[0].data.reverse();
		}
		$widget.highcharts({
			title : {
				text : '',
				x : -20
				// center
			},

			xAxis : {
				categories : []
			},
			credits: {
			      enabled: false
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
				align: 'top',
		        verticalAlign: 'top',
				borderWidth : 0,
				 y: 50
			},
			series : series
		});
	};

	controller.initialize();


})(jQuery);