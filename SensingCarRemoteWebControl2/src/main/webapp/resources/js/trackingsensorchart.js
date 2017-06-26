var trackingSensorChart;
$(function() {
	trackingSensorChart = new Highcharts.Chart({
		chart: {
			renderTo:"trackingSensorChartContainer",
			type:"spline",
			events: {
				load: requestTrackingSensorData
			}
		},
		plotOptions:{
			series: {
	            allowPointSelect: true,
	            negativeColor: '#0088FF'
	        }
		},
		colors: ['green'],
		title: {
			text: "TrackingSensor(라인감지센서)"
		},
		xAxis: {
			type: "datetime",
			tickPixelInterval: 100,
			minRange: 20*1000
		},
		yAxis: {
			title: {
				text: "감지",
				margin: 30
			}
		},
		series: [{
			name: "감지",
			data: [],
			threshold:1,
		negativeColor: '#FF0000'
		}]
	});
});

function requestTrackingSensorData() {
	var ws = new WebSocket("ws://" + location.host + "/SensingCarRemoteWebControl2/websocket/trackingsensor");
	ws.onmessage = function(event) {
		var data = JSON.parse(event.data);
		var series = trackingSensorChart.series[0];
		var shift = series.data.length > 20;
		series.addPoint([data.time, data.tracking], true, shift);
	};
}






