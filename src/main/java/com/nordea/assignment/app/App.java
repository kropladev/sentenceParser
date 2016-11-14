package com.nordea.assignment.app;

import com.nordea.assignment.app.runner.AppRunnable;
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
    private static AppRunnable runner;

    public static void main( String[] args )  {

        createApplicationContext();
        retrieveApplicationRunnerBean();
        runApplication();
    }

    private static void createApplicationContext() {
        try {
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
            runner = (AppRunnable) ctx.getBean("singleThreadAppRunner");

        } catch (BeansException be){
            LOG.error("Exception while getting runner bean from context. ", be);
        }
    }

    private static void runApplication() {
        try {
            runner.runApplication();

        } catch (NullPointerException ne){
            LOG.error("Exception starting application. ", ne);
        }
    }
}
