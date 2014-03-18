package mate.hq.service.responsebuilders;

import mate.hq.messaging.ResponseBodyDefinition;
import mate.hq.messaging.SendMessageResponseBodyDefinition;
import mate.hq.messaging.SendMessageResponseElementDefinition;

public final class SendMessageServiceResponseBuilder {
	
	private SendMessageServiceResponseBuilder() {

        super();
    }

    /**
     * Creates a complete JSON response object for send Message service
     * 
     * @author Badr El Kirdi
     * 
     * @return a complete JSON response object for send Message service
     * @param message - a message to be converted into JSON format
     * */
    public static ResponseBodyDefinition buildDataResponse(
        final String message) {
    	
    	final SendMessageResponseElementDefinition element = SendMessageResponseElementDefinition.create(message);

        final SendMessageResponseBodyDefinition responseBody =
        		SendMessageResponseBodyDefinition.create(element);

        return responseBody;
    }

}
