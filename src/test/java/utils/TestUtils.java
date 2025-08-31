package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class TestUtils {

    private final WebDriver driver;

    public TestUtils(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitVisible(By locator, long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitClickable(By locator, long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void fluentUntil(By locator, long timeoutSec, long pollMillis) {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSec))
                .pollingEvery(Duration.ofMillis(pollMillis))
                .ignoring(NoSuchElementException.class)
                .until((Function<WebDriver, Boolean>) drv -> drv.findElement(locator).isDisplayed());
    }

 // inside TestUtils
    public void click(By locator) {
        WebElement element = waitClickable(locator, 15);
   // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", element);
    }
    
    public static void click(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        // Remove interfering iframe ads if present
        try {
            ((JavascriptExecutor) driver).executeScript(
                "var iframe = document.getElementById('aswift_3'); if(iframe) iframe.remove();"
            );
        } catch (Exception e) {
            // iframe not present, ignore
        }

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        // Click the element
        element.click();
    }

    public void type(By locator, String text) {
        WebElement el = waitVisible(locator, 15);
        el.clear();
        el.sendKeys(text);
    }

    public void selectByVisibleText(By locator, String text) {
        WebElement el = waitVisible(locator, 15);
        new Select(el).selectByVisibleText(text);
    }

    public void hover(By locator) {
        int attempts = 0;
        boolean hovered = false;

        while (attempts < 3 && !hovered) {  // retry up to 3 times
            try {
                WebElement el = waitVisible(locator, 15);   // wait until visible
                scrollIntoView(el);                         // scroll to element
                new Actions(driver).moveToElement(el).perform();  // hover
                hovered = true;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                // retry after a short wait
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {}
                attempts++;
            }
        }

        if (!hovered) {
            throw new RuntimeException("Unable to hover on element: " + locator);
        }
    }


    public String getText(By locator) {
        return waitVisible(locator, 15).getText().trim();
    }

    public boolean isDisplayed(By locator) {
        try {
            return waitVisible(locator, 10).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static byte[] takeScreenshot(WebDriver driver, String scenarioName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dirPath = "target/screenshots";
        try {
            Files.createDirectories(Path.of(dirPath));
            String safeName = scenarioName.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
            String fileName = safeName + "_" + DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").format(LocalDateTime.now()) + ".png";
            File dest = new File(dirPath + "/" + fileName);
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
