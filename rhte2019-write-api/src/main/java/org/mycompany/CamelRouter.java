package org.mycompany;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static org.apache.camel.model.rest.RestParamType.body;
import static org.apache.camel.model.rest.RestParamType.path;

/**
 * A simple Camel REST DSL route with Swagger API documentation.
 */
@Component
public class CamelRouter extends RouteBuilder {
	@Autowired
	private Environment env;

	@Value("${camel.component.servlet.mapping.context-path}")
	private String contextPath;

	@Override
	public void configure() throws Exception {

		// @formatter:off

		// this can also be configured in application.properties
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json)
				.dataFormatProperty("prettyPrint", "true").enableCORS(true).port(env.getProperty("server.port", "8080"))
				.contextPath(contextPath.substring(0, contextPath.length() - 2))
				// turn on swagger api-doc
				.apiContextPath("/api-doc").apiProperty("api.title", "Customer API")
				.apiProperty("api.version", "1.0.0");

		rest("/customers").description("Customer REST service").consumes("application/json")
				.produces("application/json")

				/*
				 * .get().description("Find all customers").outType(Customer[].class).
				 * responseMessage().code(200)
				 * .message("All customers successfully returned").endResponseMessage()
				 * .to("bean:customerService?method=findCustomers")
				 */

				// GET all customers
				.get().description("Find all customers").to("direct:get-customers")

				// GET customer by ID
				.get("/{id}").description("Find customer by ID").outType(Customers.class).param().name("id").type(path)
				.description("The ID of the customer").dataType("integer").endParam().responseMessage().code(200)
				.message("Customer successfully returned").endResponseMessage()
				.to("bean:customerService?method=findCustomer(${header.id})")

				// PUT (update) customer
				.put("/{id}").description("Update a customer").type(Customers.class).param().name("id").type(path)
				.description("The ID of the customer to update").dataType("integer").endParam().param().name("body")
				.type(body).description("The customer to update").endParam().responseMessage().code(204)
				.message("Customer successfully updated").endResponseMessage().to("direct:update-customer");

		from("direct:update-customer").to("bean:customerService?method=updateCustomer")
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(204)).setBody(constant(""));

		/*
		 * from("direct:get-customers").
		 * to("sql:select * from customers?dataSource=#dataSource").log("#${body}");
		 */

		/*
		 * from("direct:get-customer").to(
		 * "bean:customerService?method=findCustomers(1002)")
		 * .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));
		 */

		from("direct:get-customers").to("bean:customerService?method=findCustomers")
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));

		// @formatter:on
	}

}