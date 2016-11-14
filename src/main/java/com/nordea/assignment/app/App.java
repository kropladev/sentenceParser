package com.nordea.assignment.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

/**
 * main method placeholder
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    private static ApplicationContext ctx;
    private static AppRunner runner;

    public static void main( String[] args )  {

        createApplicationContext();
        retrieveApplicationRunnerBean();
        runApplication();
    }

    private static void createApplicationContext() {
        try {
            LOG.debug("Creating spring application context with spring boot (without banner)");

            ctx = new SpringApplicationBuilder()
                    .sources(App.class)
                    .bannerMode(Banner.Mode.OFF)
                    .run();

        } catch (Exception e){
            LOG.error("Exception while creating spring context. ", e);
        }
    }

    private static void retrieveApplicationRunnerBean() {
        try {
            LOG.debug("Getting runner bean from context");

            runner = (AppRunner) ctx.getBean("appRunner");

        } catch (BeansException be){
            LOG.error("Exception while getting runner bean from context. ", be);
        }
    }

    private static void runApplication() {
        try {
            LOG.debug("starting spring context with spring-boot");

            runner.runApplication();

        } catch (NullPointerException ne){
            LOG.error("Exception starting application. ", ne);
        }
    }
}
