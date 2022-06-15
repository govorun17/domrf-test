package org.example;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;

import java.util.Scanner;

public class MyRouteBuilder extends RouteBuilder {

    public static final String PATH = "C:/CamelTest";

    public void configure() {

        from("jetty://http://localhost:7000/json")
                .choice()
                .when(header("token").isEqualTo("SECRET"))
                .to("direct:valid")
                .otherwise()
                .to("direct:nonValid");

        from("direct:valid")
                .process(exchange -> {
                    System.out.print("Print k: ");
                    Scanner console = new Scanner( System.in );
                    Integer k = console.nextInt();
                    exchange.getIn().setHeader("k", k);
                })
                .unmarshal().json(JsonLibrary.Jackson, MyObject[].class)
                .process(exchange -> log.info("Length of array : " + ((Object[]) exchange.getIn().getBody()).length))
                .bean(MyHelper.class, "analiseJson")
                .marshal(new BindyCsvDataFormat(MyObject.class))
                .to("file:"+PATH+"?fileName=result.csv")
                .setBody(simple("OK"));


        from("direct:nonValid")
                .log("FORBIDDEN")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant("403"))
                .setBody(constant("FORBIDDEN"));
    }

}
