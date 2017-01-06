package com.kropla.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kropla on 11.11.16.
 * Needed just for enabling scaning of the whole package in search for spring components.
 */
@Configuration
@ComponentScan("com.kropla")
public class AppConfig {}