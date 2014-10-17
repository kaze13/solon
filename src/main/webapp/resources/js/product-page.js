(function($){
	var controller = new Controller();
	controller.buildPage = function(){
		$('#myTab a:last').tab('show');
		$('.collapsible').collapsible({
		      defaultOpen: 'nav-section1,nav-section3'
		});
		$('.init-open').collapsible('open');
		this.drawNetValueGraph();
		if(window.location.hash){
			$('.nav-tabs [href=' + window.location.hash + ']').tab('show')
		
		}
		
		$('table.netvalue').dataTable( {
	        "pagingType": "full_numbers",
	        "oLanguage": {
	            "oPaginate": {
	              "sNext": ">",
	              "sLast":">>",
	              "sPrevious":"<",
	              "sFirst":"<<"
	              
	            }
	          }
	    } );
		
		
	};
	
	controller.drawNetValueGraph = function(){
		var $widget = $('.value-graph-container');
		var productId = $widget.data('id');
		var series = [ {
			name : '净值',
			data : [],
			 color: '#FF0000',
		},
		{
			name : 'HS300',
			data : [],
			 color: '#00a2e8',
		}
		];
		
		$('.net-value-item-' + productId).each(
			function() {
				var newItem = [];
				newItem.push($(this).find('.evalue-date').text());
				newItem.push(parseFloat($(this).find('.net-value').text()));
				series[0].data.push(newItem);
				
				var hs300 = [];
				hs300.push($(this).find('.evalue-date').text());
				hs300.push(parseFloat($(this).find('.hs300').text()));
				series[1].data.push(hs300);
				
		});
		if(series[0] && series[0].data){
			series[0].data.reverse();
		}
		if(series[1] && series[1].data){
			series[1].data.reverse();
		}
		$widget.highcharts({
			title : {
				text : '',
				x : -20
				// center
			},

			xAxis : {
				categories : [],
				type: "datetime",    
		       
		        tickInterval: series[0].data.length / 6,
		        labels: {
	                rotation: -45,
	                style: {
	                    fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif'
	                }
	            }
			},
			credits: {
			      enabled: false
			},
			yAxis : {
				title : {
					text : ''
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