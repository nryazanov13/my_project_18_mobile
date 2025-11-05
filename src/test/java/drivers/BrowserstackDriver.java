package drivers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserstackDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        // Отключаем проблемные функции для мобильных тестов
        Configuration.pageLoadStrategy = "none";
        Configuration.pageLoadTimeout = 0;
        Configuration.timeout = 30000;

        // Отключаем скриншоты и page source для мобильных тестов
        Configuration.screenshots = false;
        Configuration.savePageSource = false;

        MutableCapabilities caps = new MutableCapabilities();

        // Appium capabilities
        caps.setCapability("platformName", "android");
        caps.setCapability("appium:deviceName", "Samsung Galaxy S22 Ultra");
        caps.setCapability("appium:platformVersion", "12.0");
        caps.setCapability("appium:app", "bs://sample.app");
        caps.setCapability("appium:automationName", "uiautomator2");

        // BrowserStack options
        Map<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("userName", "nikitariazanov_lF4iT7");
        browserstackOptions.put("accessKey", "PVppp2V8F2ZqZd2hpduU");
        browserstackOptions.put("projectName", "First Java Project");
        browserstackOptions.put("buildName", "browserstack-build-1");
        browserstackOptions.put("sessionName", "first_test");
        browserstackOptions.put("local", false);

        caps.setCapability("bstack:options", browserstackOptions);

        try {
            return new RemoteWebDriver(
                    new URL("https://hub.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}