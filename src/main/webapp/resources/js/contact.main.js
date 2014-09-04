$(document)
		.ready(
				function() {

					var map = new BMap.Map("map-container");

					var point = new BMap.Point(121.528988, 31.229916);
					map.centerAndZoom(point, 15);

					var marker1 = new BMap.Marker(new BMap.Point(121.538988,
							31.229916));
					map.addOverlay(marker1);
					map.enableScrollWheelZoom();
					var infoWindow1 = new BMap.InfoWindow(
							"双隆投资<br>上海市浦东新区竹林路101号陆家嘴基金大厦1604室");
					marker1.addEventListener("click", function() {
						this.openInfoWindow(infoWindow1);
					});

				});