package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserstackDriver implements WebDriverProvider {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        String userName = "nikitariazanov_lF4iT7";
        String accessKey = "PVppp2V8F2ZqZd2hpduU";

        caps.setCapability("browserstack.user", userName);
        caps.setCapability("browserstack.key", accessKey);
        caps.setCapability("app", "bs://sample.app");
        caps.setCapability("device", "Google Pixel 7 Pro");
        caps.setCapability("os_version", "13.0");
        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");
        try {
            return new RemoteWebDriver(
                    new URL("https://hub.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}