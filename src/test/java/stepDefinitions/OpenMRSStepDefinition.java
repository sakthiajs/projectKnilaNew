package stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

import baseUtils.ScreenshotUtils;
import baseUtils.WaitUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageElements.OpenMRSPageElements;
import baseUtils.DriverManager;

public class OpenMRSStepDefinition extends BaseSteps{

	
	 OpenMRSPageElements elements;
	    private static final Logger logger = LogManager.getLogger(OpenMRSStepDefinition.class);
	    Select select;

double expectedHeight = 175.0;
double expectedWeight = 65.0;
double expectedBMI = Math.round((expectedWeight / Math.pow(expectedHeight / 100.0, 2)) * 10.0) / 10.0;

private String deletedPatientID; 

String homeWindow;
Alert alert;

	    public OpenMRSStepDefinition(OpenMRSPageElements elements) {
	        this.elements = elements;
	    }

	    @Given("I am on the login page")
	    public void i_am_on_the_login_page() {
	        logger.info("Navigating to login page: " + elements.URL);
	        driver.get(elements.URL);
	    }

	   
	    @When("I enter username {string} and password {string}")
	    public void i_enter_username_and_password(String username, String password) {
	        logger.info("Entering credentials: username = " + username);
	        driver.findElement(elements.userName).sendKeys(username);
	        driver.findElement(elements.password).sendKeys(password);
	        driver.findElement(elements.location).click();
	        driver.findElement(elements.loginButton).click();
	    }

	    @Then("I should be redirected to the dashboard")
	    public void i_should_be_redirected_to_the_dashboard() {
	        String expectedUrl = "https://o2.openmrs.org/openmrs/referenceapplication/home.page";
	        logger.info("Waiting for redirect to dashboard...");
	        boolean redirected = WaitUtils.waitForUrlToContain(driver, "home.page");

	        String actualUrl = driver.getCurrentUrl();

	        try {
	            assertEquals(actualUrl, expectedUrl, "User should be on the dashboard");
	            logger.info("Redirect successful. Current URL: " + actualUrl);
	        } catch (AssertionError e) {
	            logger.error("Redirect failed. Expected: " + expectedUrl + ", but was: " + actualUrl);
	            ScreenshotUtils.takeScreenshot(driver, "redirect_failure");
	            throw e;
	        }
	    }
	

@Given("Clicking the {string} menu")
public void clicking_the_menu(String menu) {
    logger.info("Navigating to Patient Registration page...");
    driver.findElement(elements.registerPatient).click();
    
    
}

    @When("I enter patient details")
    public void i_enter_patient_details() throws InterruptedException {
        logger.info("Filling out patient registration form...");
  
        //WaitUtils.waitForElementVisible(driver, elements.nameLabel, 10).click();

        WebElement givenName = WaitUtils.waitForElementVisible(driver, elements.givenName, 10);
        givenName.sendKeys("Sakthivel");

        WebElement familyName = driver.findElement(elements.familyName);
        familyName.click();
        familyName.sendKeys("Aj");

        driver.findElement(elements.genderLabel).click();
        WebElement genderDropdown = driver.findElement(elements.genderField);
        Select select = new Select(genderDropdown);
        select.selectByValue("M");

        driver.findElement(elements.birthdateLabel).click();
        driver.findElement(elements.birthdateDay).sendKeys("05");

        WebElement birthMonthDropdown = driver.findElement(elements.birthdateMonth);
        select = new Select(birthMonthDropdown);
        select.selectByValue("6");

        driver.findElement(elements.birthdateYear).sendKeys("1997");

        driver.findElement(elements.addressLabel).click();
        driver.findElement(elements.addressField1).sendKeys("Guga Street");
        driver.findElement(elements.cityVillage).sendKeys("Thirukumaran Nagar");
        driver.findElement(elements.stateProvince).sendKeys("TN");
        driver.findElement(elements.country).sendKeys("IND");
        driver.findElement(elements.postalCode).sendKeys("641057");

        driver.findElement(elements.phoneNumberLabel).click();
        driver.findElement(elements.phoneNumberField).sendKeys("6677556765");

        driver.findElement(elements.relativesLabel).click();
        WebElement relationshipDropdown = driver.findElement(elements.relationshipType);
        select = new Select(relationshipDropdown);
        select.selectByVisibleText("Parent");

        driver.findElement(elements.personName).sendKeys("Ram");

        driver.findElement(elements.confirmationLabel).click();
        logger.info("Form filled. Moving to confirmation page.");
    }
    
    @Then("I should confirm patient information")
    public void i_should_confirm_patient_information() {
        logger.info("Verifying patient confirmation details...");

        try {
            // Name
            WaitUtils.waitForElementVisible(driver, elements.confirmName, 5);
            String fullNameText = driver.findElement(elements.confirmName).getText();  
            String displayedName = fullNameText.replace("Name: ", "").trim();
            logger.info("Displayed Name: " + displayedName);
            Assert.assertEquals(displayedName, "Sakthivel, Aj", "Name Mismatch");

            // Gender
            WaitUtils.waitForElementVisible(driver, elements.confirmGender, 5);
            String fullGenderText = driver.findElement(elements.confirmGender).getText();  
            String displayedGender = fullGenderText.replace("Gender: ", "").trim();
            logger.info("Displayed Gender: " + displayedGender);
            Assert.assertEquals(displayedGender, "Male", "Gender Mismatch");

            // Birthdate
            WaitUtils.waitForElementVisible(driver, elements.confirmBirthdate, 5);
            String fullBirthdateText = driver.findElement(elements.confirmBirthdate).getText();  
            String displayedBirthDate = fullBirthdateText.replace("Birthdate: ", "").trim();
            logger.info("Displayed Birthdate: " + displayedBirthDate);
            Assert.assertEquals(displayedBirthDate, "05, June, 1997", "BirthDate Mismatch");

            // Address
            WaitUtils.waitForElementVisible(driver, elements.confirmAddress, 5);
            String fullAddressText = driver.findElement(elements.confirmAddress).getText();  
            String displayedAddress = fullAddressText.replace("Address: ", "").trim();
            logger.info("Displayed Address: " + displayedAddress);
            Assert.assertEquals(displayedAddress, "Guga Street, Thirukumaran Nagar, TN, IND, 641057", "Address Mismatch");

            // Phone Number
            WaitUtils.waitForElementVisible(driver, elements.confirmPhoneNumber, 5);
            String fullPhoneText = driver.findElement(elements.confirmPhoneNumber).getText();  
            String displayedPhoneNumber = fullPhoneText.replace("Phone Number: ", "").trim();
            logger.info("Displayed Phone Number: " + displayedPhoneNumber);
            Assert.assertEquals(displayedPhoneNumber, "6677556765", "Phone Number Mismatch");

            logger.info("All confirmation details matched successfully.");
            

        } catch (AssertionError e) {
            logger.error("Patient confirmation mismatch detected: " + e.getMessage());
            ScreenshotUtils.takeScreenshot(driver, "patient_confirmation_mismatch");
            throw e;
        } catch (Exception e) {
            logger.error("Exception while verifying patient confirmation: " + e.getMessage());
            ScreenshotUtils.takeScreenshot(driver, "patient_confirmation_exception");
            throw new RuntimeException(e);
        }
    }

@Then("I should be redirected to patient details page")
public void i_should_be_redirected_to_patient_details_page() {
    logger.info("Submitting patient registration...");
    driver.findElement(elements.confirmButton).click();
    

    String expectedUrlPart = "patient.page?patientId=";

    try {
        logger.info("Waiting for redirect to patient details page...");
        boolean redirected = WaitUtils.waitForUrlToContain(driver, expectedUrlPart);
        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(redirected && actualUrl.contains(expectedUrlPart),
                "Redirection to patient details page failed");

        logger.info("Redirect successful. Current URL: " + actualUrl);

    } catch (AssertionError e) {
        logger.error("Redirect failed. URL did not contain expected part: " + expectedUrlPart);
        ScreenshotUtils.takeScreenshot(driver, "redirect_failure");
        throw e;
    }
}

@Then("The system should calculate the age correctly")
public void The_system_should_calculate_the_age_correctly() {
    logger.info("Verifying calculated age...");

    try {
    	WaitUtils.waitForElementVisible(driver, elements.calculatedAge, 5);
    	String fullAgeText = driver.findElement(elements.calculatedAge).getText().trim();
    	logger.info("Full Age Text: " + fullAgeText);

    	// Extract just the numeric part (e.g., 27 from "27 year(s) ( 05.Jun.1997)")
    	String numericPart = fullAgeText.split(" ")[0]; // Splits at the space
    	int calculatedAge = Integer.parseInt(numericPart);

    	LocalDate birthdate = LocalDate.of(1997, 6, 5);
    	LocalDate today = LocalDate.now();
    	int expectedAge = Period.between(birthdate, today).getYears();

    	Assert.assertEquals(calculatedAge, expectedAge, "Age is not calculated correctly.");
    	logger.info("Age calculated correctly: " + calculatedAge);

    } catch (AssertionError e) {
        logger.error("Age mismatch. Expected: " + e.getMessage());
        ScreenshotUtils.takeScreenshot(driver, "age_calculation_error");
        throw e;
    } catch (Exception e) {
        logger.error("Exception during age verification: " + e.getMessage());
        ScreenshotUtils.takeScreenshot(driver, "age_verification_exception");
        throw new RuntimeException(e);
    }
}
@Given("Start visit and confirm visit")
public void start_visit_and_confirm_visit() 
{
    driver.findElement(elements.startVisit).click();
    driver.findElement(elements.confirmVisitButton).click();
}

@When("I click on the {string} menu")
public void i_click_on_the_menu1(String string) {
	WaitUtils.waitForElementVisible(driver, elements.addAttachment, 10).click();
	
}

@When("I upload a file")
public void i_upload_a_file() {
    logger.info("Uploading file using Robot from Desktop...");

    
    String filePath = System.getProperty("user.home") + "\\Downloads\\test-image.jpg";

    try {
       
        WebElement uploadTrigger = WaitUtils.waitForElementClickable(driver, elements.dropAFileField, 10);
        uploadTrigger.click();

        
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        logger.info("File uploaded successfully: " + filePath);

    } catch (Exception e) {
        logger.error("Upload failed: " + e.getMessage());
        ScreenshotUtils.takeScreenshot(driver, "robot_upload_failure");
        throw new RuntimeException(e);
    }
    
   WaitUtils.waitForElementVisible(driver, elements.attatchmentCaption, 6).sendKeys("AjFile");
    WaitUtils.waitForElementVisible(driver, elements.uploadButton, 7).click();
    
    
}

@Then("I should see a success message for the attachment")
public void i_should_see_a_success_message_for_the_attachment() {
    try {
        
        WebElement toaster = WaitUtils.waitForElementVisible(driver, elements.toasterMessage, 10); // 10 seconds timeout

        
        String actualMessage = toaster.getText();
        logger.info("Toaster message displayed: " + actualMessage);

        
        String expectedMessage = "The attachment was successfully uploaded";

        
        Assert.assertTrue(actualMessage.contains(expectedMessage), 
            "Expected success message but found: " + actualMessage);
        WaitUtils.waitForElementClickable(driver, elements.backPatientDetails, 6).click();

    } catch (Exception e) {
        logger.error("Toaster message not displayed or incorrect: " + e.getMessage());
        ScreenshotUtils.takeScreenshot(driver, "attachment_upload_toast_failure");
        throw new AssertionError("Toaster message validation failed", e);
    }
}




@Given("Start Visit Again")
public void start_visit_again() {
    try {
        WebElement startVisitBtn = WaitUtils.waitForElementClickable(driver, elements.startVisit, 10);
        startVisitBtn.click();
        WebElement confirmBtn = WaitUtils.waitForElementClickable(driver, elements.confirmVisitButton, 10);
        confirmBtn.click();
        logger.info("Visit started successfully.");
    } catch (Exception e) {
        logger.error("Failed to start visit: " + e.getMessage());
        ScreenshotUtils.takeScreenshot(driver, "start_visit_error");
        throw new RuntimeException("Visit could not be started", e);
    }
}

@When("Click on {string} menu")
public void click_on_capture_vitals_menu(String menu) {
    try {
        WebElement captureVitalsMenu = WaitUtils.waitForElementClickable(driver, elements.captureVitals, 10);
        captureVitalsMenu.click();
        logger.info("Clicked on menu: " + menu);
    } catch (Exception e) {
        logger.error("Failed to click on menu: " + e.getMessage());
        ScreenshotUtils.takeScreenshot(driver, "click_capture_vitals_menu_error");
        throw new RuntimeException("Could not click menu", e);
    }
}

@When("I enter vitals data")
public void i_enter_vitals_data() {
    try {
        WaitUtils.waitForElementVisible(driver, elements.heightLabel, 10).click();
        WaitUtils.waitForElementVisible(driver, elements.heightField, 10).sendKeys(String.valueOf((int) expectedHeight));

        WaitUtils.waitForElementVisible(driver, elements.weightLabel, 10).click();
        WaitUtils.waitForElementVisible(driver, elements.weightField, 10).sendKeys(String.valueOf((int) expectedWeight));

        logger.info("Entered height and weight successfully.");

    } catch (Exception e) {
        logger.error("Failed to enter vitals: " + e.getMessage());
        ScreenshotUtils.takeScreenshot(driver, "enter_vitals_error");
        throw new RuntimeException("Vitals input failed", e);
    }
}

@Then("I should verify the BMI calculation and save the form")
public void i_should_verify_the_bmi_calculation_and_save_the_form() {
    try {
        WaitUtils.waitForElementVisible(driver, elements.calclatedBMILabel, 10).click();

        String bmiText = WaitUtils.waitForElementVisible(driver, elements.confirmBMIValue, 10).getText().trim();
        double actualBMI = Double.parseDouble(bmiText);

        Assert.assertEquals(actualBMI, expectedBMI, "BMI calculation mismatch");
        logger.info("BMI is calculated correctly: " + actualBMI);

        WaitUtils.waitForElementClickable(driver, elements.saveForm, 10).click();
        WaitUtils.waitForElementClickable(driver, elements.saveButton, 10).click();
        logger.info("Form saved successfully.");

    } catch (Exception e) {
        logger.error("BMI verification or form submission failed: " + e.getMessage());
        ScreenshotUtils.takeScreenshot(driver, "bmi_save_error");
        throw new RuntimeException("BMI validation or save failed", e);
    }
}

@Then("Click End Visit and redirect to patient details page")
public void click_end_visit_and_redirect_to_patient_details_page() {
    try {
        WaitUtils.waitForElementClickable(driver, elements.endVisit, 10).click();
        WaitUtils.waitForElementClickable(driver, elements.confirmEndVisit, 10).click();
        logger.info("Clicked End Visit.");

        WaitUtils.waitForElementClickable(driver, elements.backPatientDetails, 6).click();

        boolean redirected = WaitUtils.waitForUrlToContain(driver, "patient.page?patientId", 10);
        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(redirected && currentUrl.contains("patient.page?patientId"),
                "Redirection to patient details page failed.");
        logger.info("Redirected to patient details page: " + currentUrl);

    } catch (Exception e) {
        logger.error("End Visit or redirection failed: " + e.getMessage());
        ScreenshotUtils.takeScreenshot(driver, "end_visit_redirection_error");
        throw new AssertionError("End Visit and redirect failed", e);
    }
}

@And("Verify height and weight and BMI")
public void verify_height_weight_and_bmi() {
    try {
        String actualHeight = WaitUtils.waitForElementVisible(driver, elements.compareHeight, 10).getText().replaceAll("[^\\d.]", "");
        String actualWeight = WaitUtils.waitForElementVisible(driver, elements.compareWeight, 10).getText().replaceAll("[^\\d.]", "");
        String actualBMI = WaitUtils.waitForElementVisible(driver, elements.compareBMI, 10).getText().replaceAll("[^\\d.]", "");

        Assert.assertEquals(Double.parseDouble(actualHeight), expectedHeight, "Height mismatch");
        Assert.assertEquals(Double.parseDouble(actualWeight), expectedWeight, "Weight mismatch");
        Assert.assertEquals(Double.parseDouble(actualBMI), expectedBMI, "BMI mismatch");

        logger.info("Height, Weight, and BMI are all displayed correctly.");

    } catch (Exception e) {
        logger.error("Vitals verification on patient details page failed: " + e.getMessage());
        ScreenshotUtils.takeScreenshot(driver, "vitals_verification_error");
        throw new AssertionError("Vitals (height/weight/BMI) check failed", e);
    }
}

@Given("Recent visit has two entries")
public void recent_visit_has_two_entries() {
    try {
        WaitUtils.waitForElementVisible(driver, elements.confirmRecentVisit2, 10);
        logger.info("Recent visit with two entries is visible as expected.");
    } catch (Exception e) {
        logger.error("Recent visit with two entries is not visible: " + e.getMessage());
        ScreenshotUtils.takeScreenshot(driver, "recent_visit_not_visible");
        throw new AssertionError("Recent visit with two entries not found", e);
    }
}


@When("I select two visits to merge")
public void i_select_two_visits_to_merge() {
    try {
        driver.findElement(elements.mergeVisits).click();
        logger.info("Clicked on Merge Visits.");

        WaitUtils.waitForElementVisible(driver, elements.visitCheckbox1, 10).click();
        logger.info("Selected first visit checkbox.");

        WaitUtils.waitForElementVisible(driver, elements.visitCheckbox2, 10).click();
        logger.info("Selected second visit checkbox.");

        WaitUtils.waitForElementVisible(driver, elements.mergeSelectedVisitButton, 10).click();
        logger.info("Clicked on Merge Selected Visits button.");

        WaitUtils.waitForElementVisible(driver, elements.returnButton, 10).click();
        logger.info("Clicked on Return button after merging visits.");
        
    } catch (Exception e) {
        logger.error("Failed to merge visits: " + e.getMessage());
        ScreenshotUtils.takeScreenshot(driver, "merge_visits_error");
        throw new AssertionError("Visit merge process failed", e);
    }
}


@Then("I should see the visits merged")
public void i_should_see_the_visits_merged() {
    try {
        WaitUtils.waitForElementVisible(driver, elements.confirmMergedVisitsTag, 10);
        logger.info("Visits have been successfully merged and are visible on the page.");
    } catch (Exception e) {
        logger.error("Merged visits confirmation tag is not visible: " + e.getMessage());
        ScreenshotUtils.takeScreenshot(driver, "merged_visits_not_visible");
        throw new AssertionError("Merged visits tag not found on the page", e);
    }
}

@Given("I am on the patient details page")
public void i_am_on_the_patient_details_page1() {
    logger.info("Patient registration...");
    

    String expectedUrlPart = "patient.page?patientId=";

    try {
        logger.info("Waiting for redirect to patient details page...");
        boolean redirected = WaitUtils.waitForUrlToContain(driver, expectedUrlPart);
        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(redirected && actualUrl.contains(expectedUrlPart),
                "Redirection to patient details page failed");

        logger.info("Redirect successful. Current URL: " + actualUrl);

    } catch (AssertionError e) {
        logger.error("Redirect failed. URL did not contain expected part: " + expectedUrlPart);
        ScreenshotUtils.takeScreenshot(driver, "redirect_failure");
        throw e;
    }
}

@When("Click on the {string} menu")
public void click_on_the_menu(String string)
{
	driver.findElement(elements.addPastVisit).click();
	
}

@Then("I should verify the future date is not clickable in the date picker")
public void i_should_verify_the_future_date_is_not_clickable_in_the_date_picker() 
{
	try {
        
        WaitUtils.waitForElementVisible(driver, elements.addPastVisitButton, 10).click();
        logger.info("Clicked on Add Past Visit.");

        
        WebElement datePicker = WaitUtils.waitForElementVisible(driver, elements.datePickerWidget, 10);
        logger.info("Datepicker is visible.");

        
        LocalDate today = LocalDate.now();
        
        List<WebElement> selectableDates = driver.findElements(elements.futureDateSelector);

        boolean futureDateClickable = false;

        for (WebElement dateElement : selectableDates) {
            String dateText = dateElement.getText().trim();
            if (!dateText.matches("\\d+")) continue;

            int day = Integer.parseInt(dateText);
            String displayedMonthYear = driver.findElement(elements.datePickerMonthHeader).getText().trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
            YearMonth yearMonth = YearMonth.parse(displayedMonthYear, formatter);
            LocalDate fullDate = yearMonth.atDay(day);

            if (fullDate.isAfter(today)) {
                futureDateClickable = true;
                logger.warn("Future date is clickable: " + fullDate);
                break;
            }
        }

        if (futureDateClickable) {
            throw new AssertionError("A future date is selectable in the datepicker.");
        } else {
            logger.info("All future dates are correctly disabled in the datepicker.");
        }

        
        WaitUtils.waitForElementVisible(driver, elements.cancelDateButton, 10).click();
        logger.info("Clicked on Cancel to close the datepicker.");

    } catch (Exception e) {
        logger.error("Datepicker validation failed: " + e.getMessage());
        ScreenshotUtils.takeScreenshot(driver, "datepicker_future_date_error");
        throw new AssertionError("Failed to verify datepicker future date restriction", e);
    }
}
@Given("I am on patient details page")
public void i_am_on_patient_details_page() {
    logger.info("Patient registration...");

    String expectedUrlPart = "patient.page?patientId=";

    try {
        logger.info("Waiting for redirect to patient details page...");
        boolean redirected = WaitUtils.waitForUrlToContain(driver, expectedUrlPart);
        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(redirected && actualUrl.contains(expectedUrlPart),
                "Redirection to patient details page failed");

        logger.info("Redirect successful. Current URL: " + actualUrl);

    } catch (AssertionError e) {
        logger.error("Redirect failed. URL did not contain expected part: " + expectedUrlPart);
        ScreenshotUtils.takeScreenshot(driver, "redirect_failure");
        throw e;
    }

    WebElement patientIDElement = driver.findElement(elements.patientId);
    deletedPatientID = patientIDElement.getText(); 
    logger.info("Captured Patient ID: " + deletedPatientID);
}

@When("I click on {string}")
public void i_click_on(String buttonName) {
    try {
        
        driver.findElement(elements.deletePatient).click();
        logger.info("Clicked on delete patient button.");

        
        WaitUtils.waitForElementVisible(driver, elements.deleteReason, 10).sendKeys("Test");
        logger.info("Entered reason for deletion.");

        
        driver.findElement(elements.confirmDelete).click();
        logger.info("Clicked confirm delete.");

        
        WebElement toaster = WaitUtils.waitForElementVisible(driver, elements.deletedToasterMessage, 10);
        Assert.assertTrue(toaster.isDisplayed(), "Toaster message not displayed after deletion.");
        logger.info("Toaster message displayed after deleting the patient.");

    } catch (Exception e) {
        logger.error("Failed during patient delete flow: " + e.getMessage());
        ScreenshotUtils.takeScreenshot(driver, "delete_patient_error");
        throw new AssertionError("Failed to verify toaster message after deleting patient", e);
    }
}


@Then("I should verify the patient is deleted and not visible in the search results")
public void i_should_verify_the_patient_is_deleted_and_not_visible_in_the_search_results() {
    try {
        WebElement searchBox = WaitUtils.waitForElementVisible(driver, elements.searchPatient, 10);
        searchBox.clear();
        searchBox.sendKeys(deletedPatientID); 
        logger.info("Searched for deleted patient ID: " + deletedPatientID);

        WebElement noRecordMessage = WaitUtils.waitForElementVisible(driver, elements.noRecordsBar, 10);
        Assert.assertTrue(noRecordMessage.isDisplayed(), "Deleted patient is still visible in search results.");
        logger.info("Verified patient is deleted and not in search results.");

    } catch (Exception e) {
        logger.error("Patient deletion verification failed: " + e.getMessage());
        ScreenshotUtils.takeScreenshot(driver, "deleted_patient_still_visible");
        throw new AssertionError("Deleted patient is still visible in search results", e);
    }
}

@AfterClass
public static void tearDown() 
{
	DriverManager.quitDriver();
}

}
