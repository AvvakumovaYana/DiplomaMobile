package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:common.properties"})
public interface CommonConfig extends Config {
    String apiUri();
}
