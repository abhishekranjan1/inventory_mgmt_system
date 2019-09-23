package com.example.inventory_mgmt_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static final Contact DEFAULT_CONTACT= new Contact("Insta_Demo","http://inventory-management-system.com","abhishek.ranjan123.@gmail.com");
	private static final Set<String> DEFAULT_PRODUCERS_AND_CONSUMERS = new HashSet<String>(Arrays.asList("application/json","application/xml"));
	
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.produces(DEFAULT_PRODUCERS_AND_CONSUMERS)
				.consumes(DEFAULT_PRODUCERS_AND_CONSUMERS);
	}

	private ApiInfo apiInfo() {
	final StringVendorExtension vendorExtension = new StringVendorExtension("","");
	@SuppressWarnings("rawtypes")
	final 
	
	Collection<VendorExtension> vendorExtensions= new ArrayList<>();
	vendorExtensions.add(vendorExtension);
	
	return new ApiInfo(
			"API Documentation","Api Documentation","1.0",
			"urn:tos",DEFAULT_CONTACT,
			"Apache 2.0","http://www.apache.org/licenses/LICENSE-2.0",vendorExtensions);
	}
	
}
