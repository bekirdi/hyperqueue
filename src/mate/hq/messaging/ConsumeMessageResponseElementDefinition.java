package mate.hq.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "hyper_queue")
@XmlType(propOrder={"message" , "session_id"})
public final class ConsumeMessageResponseElementDefinition {
	
	@XmlElement(name = "message", required = true)
    private final Integer message;
	
	@XmlElement(name = "session_id", required = true)
    private final String sessionId;

    private ConsumeMessageResponseElementDefinition() {

        this(null, null);
    }

    private ConsumeMessageResponseElementDefinition(
        final Integer pMessage, final String pSessionId) {

        super();
        this.message = pMessage;
        this.sessionId = pSessionId;
    }

    public static ConsumeMessageResponseElementDefinition create(
        final Integer message, final String sessionId) {

        return new ConsumeMessageResponseElementDefinition(message, sessionId);
    }

}
