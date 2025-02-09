package org.rundeck.tests.functional.selenium.pages

import groovy.transform.CompileStatic
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.rundeck.util.container.SeleniumContext

import java.time.Duration

/**
 * Base type for page object model
 */
@CompileStatic
abstract class BasePage {
    final SeleniumContext context

    /**
     * Create a new page
     * @param context
     */
    BasePage(final SeleniumContext context) {
        this.context = context
        this.context.driver.manage().window().setSize(new Dimension(1200,1050))
    }
    abstract String getLoadPath()
    /**
     * Go to the page and validate
     */
    void go(){
        if(loadPath){
            driver.get(context.client.baseUrl + loadPath)
            validatePage()
        }
    }
    /**
     * Validate the page is loaded
     */
    void validatePage() {

    }
    void waitUntilPageLoaded() {
        if(loadPath) {
            new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.urlToBe(context.client.baseUrl + loadPath)
            )
        }
    }


    WebDriver getDriver() {
        context.driver
    }

    WebElement el(By by) {
        context.driver.findElement(by)
    }

    List<WebElement> els(By by) {
        context.driver.findElements(by)
    }
}
