package config.mobile;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:mobile/application.properties"})
public interface ApplicationConfig extends Config {
    String app();

    String appPackage();

    String appActivity();
}
