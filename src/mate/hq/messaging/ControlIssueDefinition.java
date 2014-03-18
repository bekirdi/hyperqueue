package mate.hq.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//if the error appears during the service execution, 
//this class will be used to describe the errors

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "control_issue_section")
@XmlType(propOrder = { "severity", "code", "message" })
public final class ControlIssueDefinition {

    @XmlElement(name = "severity", required = true)
    private final String severity;

    @XmlElement(name = "code", required = true)
    private final String code;

    @XmlElement(name = "message", required = true)
    private final String message;

    /**
     * Need this for JSON serialisation
     */
    private ControlIssueDefinition() {

        this(null, null, null);
    }

    private ControlIssueDefinition(final String pSeverity, final String pCode, final String pMessage) {

        super();
        this.severity = pSeverity;
        this.code = pCode;
        this.message = pMessage;
    }

    public static ControlIssueDefinition create(
        final String severity,
        final String code,
        final String message) {

        return new ControlIssueDefinition(severity, code, message);
    }
}
