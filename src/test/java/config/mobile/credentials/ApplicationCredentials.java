package config.mobile.credentials;

import org.aeonbits.owner.Config;

@Config.Sources({"file:application_credentials.properties"})
public interface ApplicationCredentials extends Config {
    String login();

    String password();
}
