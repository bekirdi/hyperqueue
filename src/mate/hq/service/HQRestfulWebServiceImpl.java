package mate.hq.service;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import mate.hq.AbstractHQRestfulWebService;
import mate.hq.jms.Broker;
import mate.hq.jms.Consumer;
import mate.hq.jms.Producer;
import mate.hq.messaging.ControlSectionDefinition;
import mate.hq.messaging.ServiceResponseDefinition;
import mate.hq.service.responsebuilders.ConsumeMessageServiceResponseBuilder;
import mate.hq.service.responsebuilders.SendMessageServiceResponseBuilder;
import mate.hq.validation.GenericValidationIssue;
import mate.hq.validation.ValidationIssue;
import mate.hq.validation.util.HQValidationBusinessRules;

@Path("/queue")
@Produces("application/json")
public class HQRestfulWebServiceImpl
    extends AbstractHQRestfulWebService
    implements HQRestFulWebService {
	
	private static final Logger LOGGER = Logger.getLogger(HQRestfulWebServiceImpl.class);
    private static final Broker broker = new Broker();
	
	@Context
	private HttpServletRequest request;

    public HQRestfulWebServiceImpl() {

        super();
    }

    /**
     * @author Badr El Kirdi
     *
     * @return A JSON response contaning message
     *
     * @param message - a string representing the message sending to Topic
     * */
    @Override
    @POST 
    //@RolesAllowed("user_hq")
    @Path("{topicName}")
    public final ServiceResponseDefinition sendMessage(
    		@PathParam("topicName") final String topicName,
    		@QueryParam("message") final String message) {

        try {
     
        	final Set<ValidationIssue> validationIssues = new HashSet<ValidationIssue>();
        	
        	// example for validation
        	if(message == null)
        	{
        		validationIssues.add(GenericValidationIssue.create(HQValidationBusinessRules.MESSAGE_EMPTY));
        	}
        	
        	if (!validationIssues.isEmpty()) {
                final ControlSectionDefinition control = APIResponseBuilder.prepareErrorResponse(validationIssues);
                return ServiceResponseDefinition.create(control);
            }
        	
        	Producer producer = Producer.create(broker, topicName, Integer.parseInt(message));
        	producer.putMessage();

            // Create the response stub and fill it with data.
            return ServiceResponseDefinition.create(APIResponseBuilder.buildSuccessControlResponse(), SendMessageServiceResponseBuilder.buildDataResponse(message));
        } catch (final Exception exception) {
        	HQRestfulWebServiceImpl.LOGGER.error("An error occured while invoking send message service",
                exception);
            final ControlSectionDefinition control =
                APIResponseBuilder.prepareGeneralExceptionErrorResponse(exception.toString());
            return ServiceResponseDefinition.create(control);
        }
    }

    /**
     * @author Badr El Kirdi
     *
     * @return A JSON response containing session id
     *
     * */
    @Override
    @GET 
   // @RolesAllowed("user_hq")
    @Path("{topicName}")
    public final ServiceResponseDefinition consumeMessage(
    		@PathParam("topicName") final String topicName) {
    	
    	try {
    		
    		final String sessionId = request.getSession().getId();
    		Consumer consumer = Consumer.create(topicName, request.getSession().getId(), broker);
    		Integer message = consumer.getMessage();

    		return ServiceResponseDefinition.create(APIResponseBuilder.buildSuccessControlResponse(), ConsumeMessageServiceResponseBuilder.buildDataResponse(message, sessionId));
		} catch (final Exception exception) {
			 HQRestfulWebServiceImpl.LOGGER.error("An error occured while invoking consume message service",
			 exception);
		  final ControlSectionDefinition control =
		      APIResponseBuilder.prepareGeneralExceptionErrorResponse(exception.toString());
		  return ServiceResponseDefinition.create(control);
		}
    }   
}
