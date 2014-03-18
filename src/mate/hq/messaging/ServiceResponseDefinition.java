
package mate.hq.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElements;

//this is a general service response, containing the control section

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "service_response")
@XmlType(propOrder = { "control", "data" })
public final class ServiceResponseDefinition {

    @XmlElement(name = "control", required = true)
    private final ControlSectionDefinition control;

   @XmlElements({
        @XmlElement(name = "data", required = false, type=SendMessageResponseBodyDefinition.class),
        @XmlElement(name = "data", required = false, type=ConsumeMessageResponseBodyDefinition.class)
    })
    private final ResponseBodyDefinition data;

    private ServiceResponseDefinition() {

        this(null, null);
    }

    private ServiceResponseDefinition(final ControlSectionDefinition pControl, final ResponseBodyDefinition pData) {

        super();
        this.control = pControl;
        this.data = pData;
    }

    private ServiceResponseDefinition(final ControlSectionDefinition pControl) {

        super();
        this.control = pControl;
        this.data = null;
    }

    public static ServiceResponseDefinition create(
        final ControlSectionDefinition control,
        final ResponseBodyDefinition data) {

        return new ServiceResponseDefinition(control, data);
    }

    public static ServiceResponseDefinition create(
        final ControlSectionDefinition control) {

        return new ServiceResponseDefinition(control);
    }
}
