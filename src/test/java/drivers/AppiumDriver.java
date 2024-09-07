package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.mobile.AppiumConfig;
import config.mobile.ApplicationConfig;
import io.appium.java_client.android.AndroidDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriver implements WebDriverProvider {
    private static final AppiumConfig deviceConfig = ConfigFactory.create(AppiumConfig.class);
    private static final ApplicationConfig applicationConfig = ConfigFactory.create(ApplicationConfig.class);

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        System.out.println("app: " + applicationConfig.app());
        System.out.println("Url: " + deviceConfig.url());

        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("platformName", deviceConfig.platformName());
        caps.setCapability("appium:deviceName", deviceConfig.deviceName());
        caps.setCapability("appium:automationName", deviceConfig.automationName());
        //caps.setCapability("appium:noReset",true);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(applicationConfig.app()).getFile());
        caps.setCapability("appium:app", file.getAbsolutePath());

        caps.setCapability("appium:appPackage", applicationConfig.appPackage());
        caps.setCapability("appium:appActivity", applicationConfig.appActivity());
        caps.setCapability("appium:adbExecTimeout", 120000);

        System.out.println("APP: " + caps.getCapability("appium:app"));

        try {
            return new AndroidDriver(new URL(deviceConfig.url()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
