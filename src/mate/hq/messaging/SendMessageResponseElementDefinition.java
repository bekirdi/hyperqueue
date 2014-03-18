package mate.hq.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "hyper_queue")
@XmlType(propOrder = { "message" })
public final class SendMessageResponseElementDefinition {
	
	@XmlElement(name = "message", required = true)
    private final String message;

    private SendMessageResponseElementDefinition() {

        this(null);
    }

    private SendMessageResponseElementDefinition(
        final String pMessage) {

        super();
        this.message = pMessage;
    }

    public static SendMessageResponseElementDefinition create(
        final String message) {

        return new SendMessageResponseElementDefinition(message);
    }
}
