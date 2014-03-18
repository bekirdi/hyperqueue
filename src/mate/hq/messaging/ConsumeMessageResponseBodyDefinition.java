package mate.hq.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "hyper_queue")
@XmlType(propOrder = { "message" })
public final class ConsumeMessageResponseBodyDefinition implements ResponseBodyDefinition {
	
	@XmlElement(name = "message", required = true)
    private final ConsumeMessageResponseElementDefinition message;
	
    /**
     * Need this for JSON serialisation
     */
    private ConsumeMessageResponseBodyDefinition() {

        this(null);
    }

    private ConsumeMessageResponseBodyDefinition(
        final ConsumeMessageResponseElementDefinition pMessage) {

        super();
        this.message = pMessage;
    }

    public static ConsumeMessageResponseBodyDefinition create(
        final ConsumeMessageResponseElementDefinition message) {

        return new ConsumeMessageResponseBodyDefinition(message);
    }

}
