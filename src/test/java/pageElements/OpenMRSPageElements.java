package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenMRSPageElements {
		
	public String URL = "https://demo.openmrs.org/openmrs/login.htm";
	public By userName = By.id("username");
	public By password = By.id("password");
	public By location = By.id("Laboratory");
	public By loginButton = By.id("loginButton");
	public By registerPatient = By.xpath("//i[@class = 'icon-user']//parent::a");
	public By nameLabel = By.xpath("//span[text() = 'Name']");
	public By givenName = By.name("givenName");
	public By familyName = By.name("familyName");
	public By genderLabel = By.id("genderLabel");
	public By genderField = By.xpath("//select[@id='gender-field']");
	public By birthdateLabel = By.id("birthdateLabel");
	public By birthdateDay = By.id("birthdateDay-field");
	public By birthdateMonth = By.id("birthdateMonth-field");
	public By birthdateYear = By.id("birthdateYear-field");
	public By addressLabel = By.xpath("//span[text() = 'Address']");
	public By addressField1 = By.id("address1");
	public By addressField2 = By.id("address2");
	public By cityVillage = By.id("cityVillage");
	public By stateProvince = By.id("stateProvince");
	public By country = By.id("country");
	public By postalCode = By.id("postalCode");
	public By phoneNumberLabel = By.xpath("//span[text() = 'Phone Number']");
	public By phoneNumberField = By.name("phoneNumber");
	public By relativesLabel = By.xpath("//span[text() = 'Relatives']");
	public By relationshipType= By.id("relationship_type");
	public By personName = By.xpath("//input[@placeholder = 'Person Name']");
	public By confirmationLabel = By.id("confirmation_label");
	public By confirmName = By.xpath("//span[text()='Name: ']/parent::p");
	public By confirmGender = By.xpath("//span[text()='Gender: ']/parent::p");
	public By confirmBirthdate = By.xpath("//span[text()='Birthdate: ']/parent::p");
	public By confirmAddress = By.xpath("//span[text()='Address: ']/parent::p");
	public By confirmPhoneNumber = By.xpath("//span[text()='Phone Number: ']/parent::p");
	public By confirmButton = By.id("submit");
	public By calculatedAge = By.xpath("//div[@class='gender-age col-auto']/span[2]");
	public By startVisit = By.xpath("//div[contains(text(), 'Start Visit')]");
	public By confirmVisitButton = By.xpath("//button[@id= 'start-visit-with-visittype-confirm']");
	public By addAttachment = By.id("attachments.attachments.visitActions.default");
	public By dropAFileField = By.xpath("//div[text() = 'Click or drop a file here.']");
	public By attatchmentCaption = By.xpath("//textarea[@placeholder='Enter a caption']");
	public By uploadButton = By.xpath("//button[@class='confirm ng-binding']");
	public By toasterMessage = By.xpath("//*[contains(text(), 'successfully')]");
	public By backPatientDetails = By.xpath("//i[@class='icon-home small']//following::li/a");
	public By confirmAttachment = By.xpath("//*[contains(text(), 'AjFile')]");
	public By recentVisitLabel = By.xpath("//*[contains(text(), 'RECENT VISITS')]");
	public By recentVisitEntry =   By.xpath("//*[contains(text(), 'RECENT VISITS')]//following::a[1]");
	public By confirmRecentVisit = By.xpath("//*[contains(text(), 'RECENT VISITS')]//following::a[1]");
	public By confirmAttachmentTag = By.xpath("//div[text() = 'Attachment Upload']");
	public By endVisit = By.xpath("(//div[contains(text(), 'End Visit')])[1]");
	public By confirmEndVisit = By.className("confirmright");//check
	public By captureVitals = By.xpath("//a[text() = ' Capture Vitals']");
	public By heightLabel = By.xpath("//span[text()='Height (cm)']");
	public By heightField = By.xpath("//span[@id='height']");
	public By weightLabel = By.xpath("//span[text()='Weight (kg)']");
	public By weightField = By.xpath("//span[@id='weight']");
	public By calclatedBMILabel = By.xpath("//span[text()='(Calculated) BMI']");
	public By confirmBMIValue = By.xpath("//span[@id='calculated-bmi']");
	public By saveForm = By.xpath("//a[@id='save-form']");
	public By saveButton = By.xpath("//button[@type='submit']");
	public By compareHeight = By.xpath("//span[@id = 'height']//child::span[1]");
	public By compareWeight = By.xpath("//span[@id = 'weight']//child::span[1]");
	public By compareBMI = By.xpath("//span[@id = 'calculated-bmi']");
	public By confirmRecentVisit2 = By.xpath("//*[contains(text(), 'RECENT VISITS')]//following::a[2]");
	public By confirmVitalsTag = By.xpath("//div[contains(text(), 'Vitals')]");
	public By mergeVisits = By.xpath("//div[contains(text(), ' Merge Visits')]");
	public By visitCheckbox1 = By.xpath("(//*[@id='active-visits']//input[1])[1]");
	public By visitCheckbox2 = By.xpath("(//*[@id='active-visits']//input[1])[2]");
	public By mergeSelectedVisitButton = By.id("mergeVisitsBtn");
	public By returnButton = By.xpath("//input[@value='Return']");
	public By confirmMergedVisitsTag = By.xpath("//div[(text() = 'Vitals, Attachment Upload')]");
	public By addPastVisit = By.xpath("//div[contains(text(), 'Add Past Visit')]");
	public By datePicker = By.xpath("(//div[@class= 'datetimepicker-days']//parent::table)[1]");
	public By addPastVisitButton = By.id("addPastVisit"); 
	public By datePickerWidget = By.cssSelector("table.table-condensed");
	public By datePickerMonthHeader = By.cssSelector("th.switch");
	public By futureDateSelector = By.cssSelector("td.day:not(.disabled)");
	public By cancelDateButton = By.xpath("//div[@id='retrospective-visit-creation-dialog']//button[1]");
	public By patientId = By.xpath("//em[text()='Patient ID']/following-sibling::span");
	public By deletePatient = By.xpath("//div[contains(text(), 'Delete Patient')]");
	public By deleteReason = By.id("delete-reason");
	public By confirmDelete = By.xpath("//input[@id = 'delete-reason']//following::button[@class = 'confirm right']");
	public By deletedToasterMessage = By.xpath("//*[contains(@class,'dz-hidden-input') and contains(text(), 'successfully')]");
	public By searchPatient = By.id("patient-search");
	public By noRecordsBar = By.xpath("//td[contains(text(), 'No matching records found')]");


}

