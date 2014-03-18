// The root URL for the RESTful services
var rootURL = "http://localhost:4430/hyperqueue/rest/queue";

var currentWine;
var isProducer = true;

hideAlert();

// Register listeners
$('#sendMessageBtn').click(function() {
	var topicName = $('#topicInput').val();
	var message = $('#messageInput').val();
	if(isProducer) {
		if(topicName == '' || message == '') {
			$('#requieredAlert').show();
		}
		else
		{
			sendMessage(topicName, message);
		}
	} else {
		if(topicName == '') {
			$('#requieredAlert').show();
		}
		else
		{
			consumeMessage(topicName);
		}
	}
	return false;
});

$('#changeViewBtn').click(function() {
	hideAlert();
	reset();
	if(isProducer) {
		isProducer = false;
		$("#changeViewBtn").attr('value', 'Change to Producer');
		$("#sendMessageBtn").attr('value', 'Consume Message');
		$('#messageForm').hide();
	} else {
		isProducer = true;
		$("#changeViewBtn").attr('value', 'Change to Consumer');
		$("#sendMessageBtn").attr('value', 'Send Message');
		$('#messageForm').show();
	}
	
	return false;
});

function hideAlert() {
	$('#requieredAlert').hide();
	$('#errorAlert').hide();
	$('#successAlert').hide();
}

function sendMessage(topicName, message) {
	hideAlert();
	console.log('Send Message');
	$.ajax({
		type: 'POST',
		url: rootURL + '/' + topicName + '?message=' + message,
		dataType: "json", // data type of response
		success: parseResponse
	});
}


function consumeMessage(topicName) {
	hideAlert();
	console.log('Consume Message');
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + topicName,
		dataType: "json", // data type of response
		success: parseResponse
	});
}



function parseResponse(data) {
	if(data.control.result == "success"){
		$('#successAlert').show();
		setInterval(function(){$('#successAlert').hide()}, 2000);
	}
	else {
		$('#errorAlert').show();
		setInterval(function(){$('#errorAlert').hide()}, 2000);
	}

	var response = JSON.stringify(data, null, "\t");
	$("#responseArea").attr('value', response);
}

function reset() {
	$("#topicInput").attr('value', '');
	$("#messageInput").attr('value', '');
}

