package pl.edu.wszib.bryan.air.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "pl.edu.wszib.bryan.air.controllers",
        "pl.edu.wszib.bryan.air.services.impl",
        "pl.edu.wszib.bryan.air.session"
})
public class TestConfiguration {

}
