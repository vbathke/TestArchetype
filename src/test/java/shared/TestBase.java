package test.java.shared;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.internal.AssumptionViolatedException;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


public abstract class TestBase {
	protected boolean acceptNextAlert = true;
	protected StringBuffer verificationErrors = new StringBuffer();
	public Wait<WebDriver> wait;
	protected static Properties testProperties;
    protected static String testeAtual = "";
    protected static String classeAtual = "";
    private static String status = "";
    @Rule public TestName testname = new TestName();	

	@Before
	public void setUp() throws Exception {
		wait = new FluentWait<WebDriver>(getDriver()).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
	}
	
	protected static WebDriver getDriver() {
		return FabricaWebDriver.getDriver();
	}
	
	@After
	public void tearDown() throws IOException, ClassNotFoundException, SQLException, InterruptedException {
	    classeAtual = this.getClass().getName();
	    testeAtual = this.getClass().getName()+"."+testname.getMethodName();
	    File scrFile = ((TakesScreenshot)FabricaWebDriver.getDriver()).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(scrFile, new File("target/screenshots/"+testeAtual+".png"));
	    
		if (FabricaWebDriver.properties.getProperty("fecharbrowser").equals("true")) {
			FabricaWebDriver.getDriver().quit();
			FabricaWebDriver.killDriver();
		}
	}
 }