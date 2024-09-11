package com.ecommerce.product.configuration;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/* we will create all class and object whihc we need again and again we can use this configuration bean in any class by dependency injection
  * we can use class object without configure here but we have to create all object again and again
  * so we can use this configuration bean to create object once and use it anywhere in the project
  * */
@Configuration
public class ApplicationConfigurationProduct {

    // create object of rest template and use it anywhere in the project by dependency injection
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }



}
