package com.nordea.assignment.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.Arrays;

@EnableAutoConfiguration
@SpringBootApplication
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) throws IOException {
        //ApplicationContext ctx =  SpringApplication.run(App.class, args);
        ApplicationContext ctx =  new SpringApplicationBuilder().sources(App.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
        logBeans(ctx);
        AppRunner runner = (AppRunner) ctx.getBean("appRunner");
     //   runner.runApplication();
        runner.runApplication(args);

    }

    private static void logBeans(ApplicationContext ctx) {
        LOG.debug("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            LOG.debug(beanName);
        }
    }
}
