package deors.demos.testing.selenium.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodesIntegrationTestCase {

    private static final Logger logger = LoggerFactory.getLogger(CodesIntegrationTestCase.class);

    private static boolean RUN_HTMLUNIT;

    private static boolean RUN_IE;

    private static boolean RUN_FIREFOX;

    private static boolean RUN_CHROME;

    private static boolean RUN_OPERA;

    private static boolean RUN_ANDROID;

    private static String SELENIUM_HUB_URL;

    private static String SELENIUM_HUB_URL_ANDROID;

    private static String TARGET_SERVER_URL;

    private static String TARGET_SERVER_URL_ANDROID;

    @BeforeClass
    public static void initEnvironment() {

        RUN_HTMLUNIT = getConfigurationProperty(
            "RUN_HTMLUNIT",
            "test.run.htmlunit",
            true);

        logger.info("running the tests in HtmlUnit: " + RUN_HTMLUNIT);

        RUN_IE = getConfigurationProperty(
            "RUN_IE",
            "test.run.ie",
            false);

        logger.info("running the tests in Internet Explorer: " + RUN_IE);

        RUN_FIREFOX = getConfigurationProperty(
            "RUN_FIREFOX",
            "test.run.firefox",
            false);

        logger.info("running the tests in Firefox: " + RUN_FIREFOX);

        RUN_CHROME = getConfigurationProperty(
            "RUN_CHROME",
            "test.run.chrome",
            false);

        logger.info("running the tests in Chrome: " + RUN_CHROME);

        RUN_OPERA = getConfigurationProperty(
            "RUN_OPERA",
            "test.run.opera",
            false);

        logger.info("running the tests in Opera: " + RUN_OPERA);

        RUN_ANDROID = getConfigurationProperty(
            "RUN_ANDROID",
            "test.run.android",
            false);

        logger.info("running the tests in Android: " + RUN_ANDROID);

        SELENIUM_HUB_URL = getConfigurationProperty(
            "SELENIUM_HUB_URL",
            "test.selenium.hub.url",
            "http://localhost:4444/wd/hub");

        logger.info("using Selenium hub at: " + SELENIUM_HUB_URL);

        SELENIUM_HUB_URL_ANDROID = getConfigurationProperty(
            "SELENIUM_HUB_URL_ANDROID",
            "test.selenium.hub.url.android",
            "http://localhost:4448/wd/hub");

        logger.info("using Selenium hub for Android at: " + SELENIUM_HUB_URL_ANDROID);

        TARGET_SERVER_URL = getConfigurationProperty(
            "TARGET_SERVER_URL",
            "test.target.server.url",
            "http://localhost:57080/deors-demos-testing-selenium");

        logger.info("using target server at: " + TARGET_SERVER_URL);

        TARGET_SERVER_URL_ANDROID = getConfigurationProperty(
            "TARGET_SERVER_URL_ANDROID",
            "test.target.server.url.android",
            "http://localhost:57080/deors-demos-testing-selenium");

        logger.info("using target server for Android at: " + TARGET_SERVER_URL_ANDROID);
    }

    private static String getConfigurationProperty(String envKey, String sysKey, String defValue) {

        String retValue = defValue;
        String envValue = System.getenv(envKey);
        String sysValue = System.getProperty(sysKey);
        // system property prevails over environment variable
        if (sysValue != null) {
            retValue = sysValue;
        } else if (envValue != null) {
            retValue = envValue;
        }
        return retValue;
    }

    private static boolean getConfigurationProperty(String envKey, String sysKey, boolean defValue) {

        boolean retValue = defValue;
        String envValue = System.getenv(envKey);
        String sysValue = System.getProperty(sysKey);
        // system property prevails over environment variable
        if (sysValue != null) {
            retValue = Boolean.parseBoolean(sysValue);
        } else if (envValue != null) {
            retValue = Boolean.parseBoolean(envValue);
        }
        return retValue;
    }

    @Test
    public void testHtmlUnit()
        throws MalformedURLException, IOException {

        Assume.assumeTrue(RUN_HTMLUNIT);
        logger.info("running tests with HtmlUnit");

        WebDriver driver = null;
        try {
            driver = new HtmlUnitDriver();
            testCodesCrud(driver, TARGET_SERVER_URL);
            testCodesError500DuplicateKey(driver, TARGET_SERVER_URL);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    public void testIE()
        throws MalformedURLException, IOException {

        Assume.assumeTrue(RUN_IE);
        logger.info("running tests with Internet Explorer");

        WebDriver driver = null;
        try {
            DesiredCapabilities browser = DesiredCapabilities.internetExplorer();
            driver = new RemoteWebDriver(new URL(SELENIUM_HUB_URL), browser);
            testCodesCrud(driver, TARGET_SERVER_URL);
            testCodesError500DuplicateKey(driver, TARGET_SERVER_URL);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    public void testFirefox()
        throws MalformedURLException, IOException {

        Assume.assumeTrue(RUN_FIREFOX);
        logger.info("running tests with Firefox");

        WebDriver driver = null;
        try {
            DesiredCapabilities browser = DesiredCapabilities.firefox();
            driver = new RemoteWebDriver(new URL(SELENIUM_HUB_URL), browser);
            testCodesCrud(driver, TARGET_SERVER_URL);
            testCodesError500DuplicateKey(driver, TARGET_SERVER_URL);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    public void testChrome()
        throws MalformedURLException, IOException {

        Assume.assumeTrue(RUN_CHROME);
        logger.info("running tests with Chrome");

        WebDriver driver = null;
        try {
            DesiredCapabilities browser = DesiredCapabilities.chrome();
            driver = new RemoteWebDriver(new URL(SELENIUM_HUB_URL), browser);
            testCodesCrud(driver, TARGET_SERVER_URL);
            testCodesError500DuplicateKey(driver, TARGET_SERVER_URL);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    public void testOpera()
        throws MalformedURLException, IOException {

        Assume.assumeTrue(RUN_OPERA);
        logger.info("running tests with Opera");

        WebDriver driver = null;
        try {
            DesiredCapabilities browser = DesiredCapabilities.operaBlink();
            driver = new RemoteWebDriver(new URL(SELENIUM_HUB_URL), browser);
            testCodesCrud(driver, TARGET_SERVER_URL);
            testCodesError500DuplicateKey(driver, TARGET_SERVER_URL);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    public void testAndroid()
        throws MalformedURLException, IOException {

        Assume.assumeTrue(RUN_ANDROID);
        logger.info("running tests with Android");

        /*WebDriver driver = null;
        try {
            driver = new AndroidDriver(new URL(SELENIUM_HUB_URL_ANDROID));
            testCodesCrud(driver, TARGET_SERVER_URL_ANDROID);
            testCodesError500DuplicateKey(driver, TARGET_SERVER_URL);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }*/
    }

    public void testCodesCrud(final WebDriver driver, final String baseUrl) {

        // first entry to view page
        // wait for the application to get fully loaded
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                d.get(baseUrl + "/CodesView.do");
                return d.getTitle().equals("Codes View Page");
            }
        });

        // confirm initial data list
        List<WebElement> codeElems = driver.findElements(By.name("code"));
        List<String> codes = new ArrayList<String>();
        for (WebElement codeElem : codeElems) {
            codes.add(codeElem.getAttribute("value"));
        }

        assertEquals(3, codes.size());
        assertTrue(codes.contains("A"));
        assertTrue(codes.contains("C"));
        assertTrue(codes.contains("D"));

        // get add button
        WebElement addForm = driver.findElement(By.name("add"));
        WebElement addButton = addForm.findElement(By.name("add"));

        // submit to add a new record
        addButton.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().equals("Codes Detail Page");
            }
        });

        WebElement detailForm = driver.findElement(By.name("detail"));
        WebElement codeField = detailForm.findElement(By.name("code"));
        codeField.sendKeys("P");
        WebElement valueField = detailForm.findElement(By.name("value"));
        valueField.sendKeys("postponed");

        // confirm add and return to view page
        WebElement okButton = detailForm.findElement(By.name("ok"));
        okButton.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().equals("Codes View Page");
            }
        });

        // confirm the new record has been added
        codeElems = driver.findElements(By.name("code"));
        codes = new ArrayList<String>();
        for (WebElement codeElem : codeElems) {
            codes.add(codeElem.getAttribute("value"));
        }

        assertEquals(4, codes.size());
        assertTrue(codes.contains("P"));

        List<WebElement> valueElems = driver.findElements(By.name("value"));
        List<String> values = new ArrayList<String>();
        for (WebElement valueElem : valueElems) {
            values.add(valueElem.getAttribute("value"));
        }

        assertTrue(values.contains("postponed"));

        WebElement pForm = driver.findElement(By.name("form_P"));
        WebElement updateButton = pForm.findElement(By.name("update"));

        // go to detail page to update record
        updateButton.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().equals("Codes Detail Page");
            }
        });

        detailForm = driver.findElement(By.name("detail"));
        codeField = detailForm.findElement(By.name("code"));
        valueField = detailForm.findElement(By.name("value"));

        assertEquals("P", codeField.getAttribute("value"));
        assertEquals("postponed", valueField.getAttribute("value"));

        valueField.clear();
        valueField.sendKeys("updated value");

        // confirm update and return to view page
        okButton = detailForm.findElement(By.name("ok"));
        okButton.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().equals("Codes View Page");
            }
        });

        // confirm the record has been updated
        valueElems = driver.findElements(By.name("value"));
        values = new ArrayList<String>();
        for (WebElement valueElem : valueElems) {
            values.add(valueElem.getAttribute("value"));
        }

        assertTrue(values.contains("updated value"));

        pForm = driver.findElement(By.name("form_P"));
        WebElement deleteButton = pForm.findElement(By.name("delete"));

        // go to detail page to delete record
        deleteButton.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().equals("Codes Detail Page");
            }
        });

        detailForm = driver.findElement(By.name("detail"));
        okButton = detailForm.findElement(By.name("ok"));

        // confirm delete and return to view page
        okButton.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().equals("Codes View Page");
            }
        });

        // confirm final data list
        codeElems = driver.findElements(By.name("code"));
        codes = new ArrayList<String>();
        for (WebElement codeElem : codeElems) {
            codes.add(codeElem.getAttribute("value"));
        }

        assertEquals(3, codes.size());
        assertTrue(codes.contains("A"));
        assertTrue(codes.contains("C"));
        assertTrue(codes.contains("D"));
    }

    public void testCodesError500DuplicateKey(final WebDriver driver, final String baseUrl) {

        // first entry to view page
        // wait for the application to get fully loaded
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                d.get(baseUrl + "/CodesView.do");
                return d.getTitle().equals("Codes View Page");
            }
        });

        // get add button
        WebElement addForm = driver.findElement(By.name("add"));
        WebElement addButton = addForm.findElement(By.name("add"));

        // submit to add a new record
        addButton.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().equals("Codes Detail Page");
            }
        });

        WebElement detailForm = driver.findElement(By.name("detail"));
        WebElement codeField = detailForm.findElement(By.name("code"));
        codeField.sendKeys("A");
        WebElement valueField = detailForm.findElement(By.name("value"));
        valueField.sendKeys("active again");

        // confirm add and return to view page
        WebElement okButton = detailForm.findElement(By.name("ok"));
        okButton.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().equals("Codes View Page");
            }
        });

        // check for error returned
        String viewText = driver.getPageSource();

        assertTrue(viewText.contains("ERROR adding new records to Codes table"));
        assertTrue(viewText.contains("could not insert"));
        assertTrue(viewText.contains("entities.CodesImpl"));
    }
}
