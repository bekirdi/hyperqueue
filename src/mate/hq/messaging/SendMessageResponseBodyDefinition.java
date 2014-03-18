package mate.hq.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import mate.hq.messaging.ResponseBodyDefinition;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "hyper_queue")
@XmlType(propOrder = { "message" })
public final class SendMessageResponseBodyDefinition 
implements ResponseBodyDefinition {
	
	@XmlElement(name = "message", required = true)
    private final SendMessageResponseElementDefinition message;

    /**
     * Need this for JSON serialisation
     */
    private SendMessageResponseBodyDefinition() {

        this(null);
    }

    private SendMessageResponseBodyDefinition(
        final SendMessageResponseElementDefinition pMessage) {

        super();
        this.message = pMessage;
    }

    public static SendMessageResponseBodyDefinition create(
        final SendMessageResponseElementDefinition message) {

        return new SendMessageResponseBodyDefinition(message);
    }
}
