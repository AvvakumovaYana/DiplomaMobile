package config.mobile.credentials;

import org.aeonbits.owner.Config;

@Config.Sources({"file:browserstack_credentials.properties"})
public interface BrowserstackCredentials extends Config {
    String browserstackUsername();

    String browserstackPassword();
}
