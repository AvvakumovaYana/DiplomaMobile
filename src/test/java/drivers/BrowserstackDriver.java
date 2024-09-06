package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.mobile.BrowserstackConfig;
import config.mobile.credentials.BrowserstackCredentials;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {
    private static final BrowserstackConfig browserstackConfig = ConfigFactory.create(BrowserstackConfig.class);
    private static final BrowserstackCredentials browserstackCredentials = ConfigFactory.create(BrowserstackCredentials.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("browserstack.user", browserstackCredentials.browserstackUsername());
        caps.setCapability("browserstack.key", browserstackCredentials.browserstackPassword());

        caps.setCapability("app", browserstackConfig.app());

        caps.setCapability("device", browserstackConfig.deviceName());
        caps.setCapability("os_version", browserstackConfig.platformVersion());

        caps.setCapability("project", browserstackConfig.projectName());
        caps.setCapability("build", browserstackConfig.buildName());
        caps.setCapability("name", browserstackConfig.name());
        caps.setCapability("url", browserstackConfig.url());

        System.out.println("APP: " + browserstackConfig.app());

        try {
            return new RemoteWebDriver(new URL(browserstackConfig.url()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
