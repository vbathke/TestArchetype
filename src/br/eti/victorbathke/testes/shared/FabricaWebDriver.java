package br.eti.victorbathke.testes.shared;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FabricaWebDriver {
	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected synchronized WebDriver initialValue() {
			return buildFirefoxDriver();
		}
	};

	public static Properties properties;
	
	protected static WebDriver buildFirefoxDriver() {
		WebDriver driver = null;
		
		properties = new Properties();
		
		try{
			properties.load(new FileInputStream("confDev.properties"));		
		}catch(Exception e){
			try {
				properties.load(new FileInputStream("confRun.properties"));
			}catch(Exception e2){}		
		}
		
		DesiredCapabilities caps;
		if (properties.getProperty("browser").equals("firefox")) {
			caps = DesiredCapabilities.firefox();
			if (properties.getProperty("execucaonuvem").equals("true")) {
				try {
					driver = new RemoteWebDriver(new URL(properties.getProperty("urlnuvem")), caps);
				} catch (MalformedURLException e) {}
			}else{
				driver = new FirefoxDriver();
			}
		}
		
		if (properties.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "res/chromedriver");
			caps = DesiredCapabilities.chrome();
			ChromeOptions cOptions = new ChromeOptions();
			cOptions.addArguments("test-type");
			caps.setCapability(ChromeOptions.CAPABILITY, cOptions);
			if (properties.getProperty("execucaonuvem").equals("true")) {
				try {
					driver = new RemoteWebDriver(new URL(properties.getProperty("urlnuvem")), caps);
				} catch (MalformedURLException e) {}
			}else{
				driver = new ChromeDriver(caps);
			}
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		return driver;
	}

	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	public static void killDriver() {
		threadLocalDriver.remove();
	}

}