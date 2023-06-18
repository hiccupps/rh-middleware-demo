package org.mycompany;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder {

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
		 .to("direct:say-hello")
     	 .to("direct:produce-to-sds-kafka");


     from("direct:say-hello")
	  	.routeId("greetStranger")
	  	.setBody(simple("Hello Samsung!!"));

	 from("direct:produce-to-sds-kafka")
			 .routeId("KafkaProducerGreetingRoute")
			 .setBody(simple("Hello Kafka !"))
			 .log("Sednign Message to Kafka : ${body}")
			 .to("kafka:{{topic}}?brokers={{broker}}");

	 from("kafka:{{topic}}?brokers={{broker}}")
			 .routeId("KafkaConsumerGreetingRoute")
			 .log("Message received from Kafka : ${body}")
			 .log("    on the topic ${headers[kafka.TOPIC]}")
			 .log("    on the partition ${headers[kafka.PARTITION]}")
			 .log("    with the offset ${headers[kafka.OFFSET]}")
			 .log("    with the key ${headers[kafka.KEY]}");






	 }

}
