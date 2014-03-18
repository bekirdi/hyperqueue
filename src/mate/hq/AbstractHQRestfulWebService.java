package mate.hq;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.apache.cxf.annotations.GZIP;

@Consumes
@Produces
@GZIP
public class AbstractHQRestfulWebService 
extends AbstractHQWebService {

	public AbstractHQRestfulWebService() {
	
	    super();
	}
}
