package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class SharedDriver {

    private static WebDriver webDriver;

    public enum Broswer {
        CHROME,
        FIREFOX,
        IE
    }


    protected static WebDriver getWebDriver(Broswer broswer) {

        switch (broswer){

            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");

                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(options);
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;

            case IE:
                WebDriverManager.iedriver().setup();
                webDriver = new InternetExplorerDriver();
                break;
        }

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        return webDriver;
    }

    protected static void closeDriver(){
        if(webDriver!=null) {
            webDriver.close();
        }
    }


}
