package org.mycompany;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

//@Component
public class MyRoute1 extends RouteBuilder {

	 @Override
	    public void configure() throws Exception {

		 restConfiguration()
				 .enableCORS(true)
				 .component("jetty")
				 .host("0.0.0.0")
				 .port(8988)
				 .bindingMode(RestBindingMode.json);

		 rest()
				 .get("/hello-sds")
				 .to("direct:hello-sds");


		 from("direct:hello-sds")
				 .routeId("GreetingSamsungSDSRoute")
				 .log("got a message");

    /* from("timer://foo?repeatCount=1")
	  	.routeId("greetStranger")
	  	.log("------ > Hello Samsung!!");*/


	 }

}
