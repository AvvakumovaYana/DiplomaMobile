package config.mobile;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({"classpath:mobile/${device}.properties"})
public interface AppiumConfig extends Config {
    String url();

    String platformName();

    String deviceName();

    String automationName();
}
