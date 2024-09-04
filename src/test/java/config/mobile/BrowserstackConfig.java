package config.mobile;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:mobile/browserstack.properties"})
public interface BrowserstackConfig extends Config {
    String url();

    String platformVersion();

    String deviceName();

    String app();

    String projectName();

    String buildName();

    String name();
}
