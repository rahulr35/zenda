package com.util;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:${user.dir}/src/main/resources/config/.properties"})
public interface ConfigReader extends Config {

    @Key("baseUrl")
    String baseUrl();


}
