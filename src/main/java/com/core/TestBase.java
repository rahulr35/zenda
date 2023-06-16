package com.core;

import com.util.ConfigReader;
import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    private static final Logger log = LoggerFactory.getLogger(TestBase.class);
    protected static ConfigReader config;


    @BeforeSuite
    public static void setup(){
        config = getConfig();
        log.info("Before Suite || Setup has been completed");

    }

    @BeforeClass
    public static void classSetup(){

        log.info("All methods have been setup");
    }

    /**
     * Singleton class implementation for ConfigReader class
     * @return ConfigReader with all property values from all the files
     * ".properties" files mentioned in ConfigReader interface
     */
    public static ConfigReader getConfig() {
        if(config == null){
            config = ConfigFactory.create(ConfigReader.class);
        }
        return config;
    }

}
