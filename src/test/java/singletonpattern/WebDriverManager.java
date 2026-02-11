package singletonpattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverManager {

    private static WebDriverManager instance = null;
    private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    private WebDriverManager() {}

    public static WebDriverManager getInstance(String browser) {
        if (instance == null) {
           synchronized (WebDriverManager.class) {
               if (instance == null) {
                   instance = new WebDriverManager();
               }
           }
        }

        if (threadLocal.get() == null) {
            instance.initialiseDriver(browser);
        }

        return instance;
    }

    private void initialiseDriver(String browser) {
        switch (browser) {
            case "chrome":
                threadLocal.set(new ChromeDriver());
                break;

            default:
                throw new IllegalArgumentException("Unsupported Browser : " + browser);
        }
    }

    public WebDriver getDriver() {
        return threadLocal.get();
    }

    public static void quitBrowser() {
        if (threadLocal.get() != null) {
            threadLocal.get().quit();
            threadLocal.remove();
        }
    }
}
