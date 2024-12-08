package com.learnerhub.testcases;

import com.framework.config.ConfigurationManager;
import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectHooks;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC003_PostProperty extends ProjectHooks {

    private String ownerLoginButton = "(//button[text()='Login'])[1]";
    private String username = "//input[@name='email']";
    private String password = "password";
    private String postProperty = "//*[text()='Post Property']";
    private String title = "//input[@name='title']";
    private String description = "//textarea[@name='description']";
    private String price = "price";
    private String categoryDropDown = "//input[@name='category']/parent::div[1]";
    private String categoryOption = "//li[text()='House']";
    private String area = "area";
    private String floors = "floors";
    private String facing = "//input[@name='facing']/parent::div";
    private String facingOption = "//li[text()='East']";
    private String location = "//input[@name='location']/parent::div";
    private String locationOption = "//li[text()='Nallu']";
    private String streetName = "//input[@name='streetName']";
    private String uploadImage = "//input[@type='file']";
    private String post = "//button[text()='Post']";
    private String titleToDispllay = "House for Rent";
    @BeforeTest
    public void setValues() {
        testcaseName = "Post Property";
        testDescription ="Post a new Property";
        authors="Mohan";
        category ="Smoke";
    }

    @Test
    public void runPostProperty() {
        click(locateElement(Locators.XPATH, ownerLoginButton));
        reportStep("Owner Login clicked successfully","pass",true);
        clearAndType(locateElement(Locators.XPATH, username), ConfigurationManager.configuration().appUserName());
        reportStep("Username entered successfully","pass",true);
        clearAndType(locateElement(Locators.ID, password), ConfigurationManager.configuration().appPassword());
        reportStep("password entered successfully","pass",true);
        click(locateElement(Locators.XPATH, ownerLoginButton));
        reportStep("Login clicked successfully","pass",true);
        click(locateElement(Locators.XPATH, postProperty));
        reportStep("Post Property clicked successfully","pass",true);
        clearAndType(locateElement(Locators.XPATH, title), titleToDispllay);
        reportStep("Title entered successfully","pass",true);
        clearAndType(locateElement(Locators.XPATH, description), "Spacious 3-bedroom house available for rent with all amenities.");
        reportStep("Description entered successfully","pass",true);
        clearAndType(locateElement(Locators.NAME, price), "20000");
        reportStep("Price entered successfully","pass",true);
        click(locateElement(Locators.XPATH, categoryDropDown));
        reportStep("Category Dropdown clicked successfully","pass",true);
        click(locateElement(Locators.XPATH, categoryOption));
        reportStep("Category Option clicked successfully","pass",true);
        clearAndType(locateElement(Locators.NAME, area), "1200");
        reportStep("Area entered successfully","pass",true);
        clearAndType(locateElement(Locators.NAME, floors), "2");
        reportStep("Floor entered successfully","pass",true);
        click(locateElement(Locators.XPATH, facing));
        reportStep("Facing clicked successfully","pass",true);
        click(locateElement(Locators.XPATH, facingOption));
        reportStep("Facing Option clicked successfully","pass",true);
        click(locateElement(Locators.XPATH, location));
        reportStep("Location clicked successfully","pass",true);
        click(locateElement(Locators.XPATH, locationOption));
        reportStep("Location Option clicked successfully","pass",true);
        clearAndType(locateElement(Locators.XPATH, streetName), "Bay Avenue, Mumbai");
        reportStep("ScreenName entered successfully","pass",true);
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\house.jpg";

        clearAndType(locateElement(Locators.XPATH, uploadImage),filePath);
        reportStep("File Uploaded successfully","pass",true);
        click(locateElement(Locators.XPATH, post));
        reportStep("Property submitted successfully","pass",true);
        String pageSource = getDriver().getPageSource();
        Assert.assertTrue(pageSource.contains(titleToDispllay), "'House for Rent' is not present in the page source.");

        // Print success message
        reportStep("'House for Rent' is present on the screen.","pass",true);
    }


}
