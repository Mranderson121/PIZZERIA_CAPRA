package com.pizzeria.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pizzeria.service.IngredientiService;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

    private final Bus bus;

    public WebServiceConfig(Bus bus) {
        this.bus = bus;
    }

    @Bean
    public Endpoint endpoint(IngredientiService service) {
        EndpointImpl endpoint = new EndpointImpl(bus, service);
        endpoint.publish("/IngredientService");
        return endpoint;
    }
}
