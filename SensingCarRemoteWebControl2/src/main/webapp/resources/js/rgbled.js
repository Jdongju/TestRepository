function rgbled(command, red, green, blue) {
	var json = {"command":"change", "red":red, "green":green, "blue":blue};
	$.ajax({
		url:"http://" + location.host + "/SensingCarRemoteWebControl2/rgbled",
		data: json,
		method: "post",
		success: function(data) {
			if(data.result == "success") {
				$("#rgbledStatus").css("background-color", "rgb(" + data.red + "," + data.green + "," + data.blue + ")")
			}
		}
	});
}