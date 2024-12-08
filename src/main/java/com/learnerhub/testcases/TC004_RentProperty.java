package com.learnerhub.testcases;

import com.framework.config.ConfigurationManager;
import com.framework.selenium.api.design.Locators;
import com.framework.testng.api.base.ProjectHooks;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC004_RentProperty extends ProjectHooks {

    private String tenantLoginButton = "//h3[contains(text(),'tenant')]/following::button[text()='Login']";
    private String username = "//input[@name='email']";
    private String password = "password";
    private String loginButton = "//button[text()='Login']";
    private String lowerLimitPrice = "lowerLimit";
    private String upperLimitPrice = "upperLimit";
    private String categoryDropDown = "//select[@id='category']";
    private String Apply = "//button[text()='Apply']";
    private String ownerDetails = "(//button[text()='Owner Details'])[1]/preceding::button[1]";
    private String chooseFirstOption = "//h3[text()='Properties']/following::a[1]";
    private String getTitle = "(//button[text()='Owner Details'])[1]/preceding::h4[1]";
    private String sendEmail = "//button[text()='Send Email']";
    private String confirmButton = "//button[text()='Confirm']";
    @BeforeTest
    public void setValues() {
        testcaseName = "Rent Property";
        testDescription ="Rent a Property";
        authors="Mohan";
        category ="Smoke";

    }

    @Test
    public void runRentProperty() {
        click(locateElement(Locators.XPATH, tenantLoginButton));
        reportStep("Tenant Login clicked successfully","pass",true);
        clearAndType(locateElement(Locators.XPATH, username), ConfigurationManager.configuration().appUserName());
        reportStep("Username entered successfully","pass",true);
        clearAndType(locateElement(Locators.ID, password), ConfigurationManager.configuration().appPassword());
        reportStep("Password entered successfully","pass",true);
        click(locateElement(Locators.XPATH, loginButton));
        reportStep("Login clicked successfully","pass",true);
        selectDropDownUsingText(locateElement(Locators.XPATH, categoryDropDown),"House");
        reportStep("Category dropdown selected successfully","pass",true);
        clearAndType(locateElement(Locators.NAME, lowerLimitPrice), "10000");
        reportStep("Price lowerLimit entered successfully","pass",true);
        clearAndType(locateElement(Locators.NAME, upperLimitPrice), "20000");
        reportStep("Price upperLimit entered successfully","pass",true);
        click(locateElement(Locators.XPATH, Apply));
        reportStep("Apply clicked successfully","pass",true);
        String titleText =  getElementText(locateElement(Locators.XPATH,getTitle));
        click(locateElement(Locators.XPATH, ownerDetails));
        reportStep("Owner Details clicked successfully","pass",true);
//        click(locateElement(Locators.XPATH,chooseFirstOption));
        reportStep("First option clicked successfully","pass",true);
        String ownerTitle = getElementText(locateElement(Locators.XPATH,"//h3[1]"));
        Assert.assertTrue(ownerTitle.contains(titleText), "'House for Rent' is not present in the page.");
        click(locateElement(Locators.XPATH,sendEmail));
        reportStep("Send Email clicked successfully","pass",true);
        click(locateElement(Locators.XPATH,confirmButton));
        reportStep("Confirm Button clicked successfully","pass",true);
        String confirmMessage = getElementText(locateElement(Locators.XPATH,"//div[text()='Email sent successfully!']"));
        //Assert.assertTrue(confirmMessage.contains("Email sent successfully!"), "'Email sent successfully!' is not present in the page.");

    }


}
