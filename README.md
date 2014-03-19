HyperQueue
==========
Backend :
- RestfulWebService using Jersey
	- Send Message to queue
	- Consume Message from queue
- Concurrent FIFO Queue Messaging
	- Broker
	- Consumer
	- Producer
- Communication under SSL
- Serialization JSON Response 
- Validator to generate comprehensive errors.

Frontend :
- Web Interface to call java rest services by using :
	- HTML5
	- JQuery
	- Bootstrap
	- AJAX
	
REST:
- SendMessage    "POST"
		Request  : https://localhost:4430/hyperqueue/rest/queue/{topic_name}?message={message}
		Response : {
						"control": {
							"result": "success"
						},
						"data": {
							"message": "256"
						}
					}
		Response :  {
						"control": {
							"result": "failed",
							"issues": [
								{
									"severity": "FATAL",
									"code": "0x00100002",
									"message": "Request failed due to problems on the server"
								}
							]
						},
						"data": null
					}
- ConsumeMessage  "GET"
		Request  : https://localhost:4430/hyperqueue/rest/queue/{topic_name}
		Response : {
						"control": {
							"result": "success"
						},
						"data": {
							"message": 45,
							"session_id": "DEF0B1FCF13A89D8D68E03A57B48DC0D.jvm1"
						}
					}
	
Requirement : 
				- JDK7
					- Download and install JDK7 : http://docs.oracle.com/javase/7/docs/webnotes/install/
				- Tomcat (but not the version 7.0.52 Released because they are a bug with REST, I'm used 7.0.47)
					- Download Eclipse for Java Developper : https://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/keplerr
				- JAVA IDE (Eclipse)
					- Download Tomcat : http://olex.openlogic.com/packages/tomcat/7.0.47


How to configure and deploy project :

1. Launch Eclipse import the project as dynamic web project
2. Go to file $TOMCAT_PATH\conf\server.xml

	Change Connector element by : This modification is very important!!! 
	because the web.xml and REST client is configured to use port 4430 and SSL Communication	
		
			<Connector port="4430" protocol="HTTP/1.1" SSLEnabled="true"
               scheme="https" secure="true"
               clientAuth="false" sslProtocol="TLS"
               keystoreFile="$PROJECT_PATH\hyperqueue\SSL\mate1.jks" keystorePass="Mate1"/>

   
3. In  the same file change Engine Element and Host Element by :

			<Engine name="Standalone" defaultHost="hq.company.com" jvmRoute="jvm1">
			
			<Host name="localhost"  appBase="webapps"   
			unpackWARs="true" autoDeploy="true"
			xmlValidation="false" xmlNamespaceAware="false">
			
	If you have a problem with redirection you can use this alternative solution :
			Windows : Open file c:\windows\system32\drivers\etc\hosts 
			Linux : Open file c:\windows\system32\drivers\etc\hosts
			
			Add 127.0.0.1 hq.company.com
			
			Windows : run command in Terminal sudo killall -HUP mDNSResponder
			Linux : run command in cmd ipconfig /flushdns

4. Export WAR on $TOMCAT_PATH\webapps\
5. Execute command ./startup from $TOMCAT_PATH\bin\
6. Open https://hq.company.com:4430/hyperqueue/ to access to the Browser Interface:EXTRA

ENJOY !!

Version 1.1 :
Soon as possible
- Add Integration test, unit test
- Add performance test by using JMeter
- Add communication with mysql, Cassandra and HBase 
- Configure the service to can deploy dynamiccaly it in the cloud 




