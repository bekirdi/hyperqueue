package mate.hq.service.responsebuilders;

import mate.hq.messaging.ConsumeMessageResponseBodyDefinition;
import mate.hq.messaging.ConsumeMessageResponseElementDefinition;

public final class ConsumeMessageServiceResponseBuilder {
	
	private ConsumeMessageServiceResponseBuilder() {

        super();
    }

    /**
     * Creates a complete JSON response object for consume Message service
     * 
     * @author Badr El Kirdi
     * 
     * @return a complete JSON response object for consume Message service
     * @param message - a message to be converted into JSON format
     * */
    public static ConsumeMessageResponseBodyDefinition buildDataResponse(
        final Integer message,
        final String sessionId) {
    	
    	final ConsumeMessageResponseElementDefinition element = ConsumeMessageResponseElementDefinition.create(message, sessionId);

        final ConsumeMessageResponseBodyDefinition responseBody =
        		ConsumeMessageResponseBodyDefinition.create(element);

        return responseBody;
    }

}
