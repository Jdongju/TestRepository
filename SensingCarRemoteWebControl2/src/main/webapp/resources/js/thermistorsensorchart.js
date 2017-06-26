var thermistorSensorChart;
$(function() {
	thermistorSensorChart = new Highcharts.Chart({
		chart: {
			renderTo:"thermistorSensorChartContainer",
			type:"column",
			defaultSeriesType:"spline",
			events: {
				load: requestThermistorSensorData
			}
		},
		colors: ['cyan'],
		title: {
			text: "ThermistorSensor(온도센서)"
		},
		xAxis: {
			type: "datetime",
			tickPixelInterval: 100,
			minRange: 20*1000
		},
		yAxis: {
			
			title: {
				text: "온도",
				margin: 30
			}
		},
		series: [{
			name: "온도",
			data: []
		}]
	});
});

function requestThermistorSensorData() {
	var ws=new WebSocket("ws://"+location.host+"/SensingCarRemoteWebControl2/websocket/thermistorsensor");
	ws.onmessage=function(event){
		var data=JSON.parse(event.data);
		var sereis=thermistorSensorChart.series[0];
		var shift=sereis.data.length>20;
		sereis.addPoint([data.time, data.temperature, true, shift]);
	};
}