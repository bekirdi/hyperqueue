package mate.hq.service;

import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;

import mate.hq.messaging.ServiceResponseDefinition;

public interface HQRestFulWebService {
	
	ServiceResponseDefinition sendMessage(
			@PathParam("topicName") String topicName,
	        @QueryParam("message") String message);

	ServiceResponseDefinition consumeMessage(@PathParam("topicName") String topicName);
}
