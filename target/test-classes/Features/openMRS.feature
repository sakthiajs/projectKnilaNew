@Test
Feature: Login feature for Oprnmrs
Scenario Outline: Redirect to dashboard after successful login
Given I am on the login page
When I enter username "<username>" and password "<password>"
Then I should be redirected to the dashboard

Examples:
      | username   | password   |
      | Admin      | Admin123   |


Scenario: Register a new patient
Given Clicking the "Register a patient" menu
When I enter patient details
Then I should confirm patient information
And I should be redirected to patient details page
And The system should calculate the age correctly

Scenario: Attach a file to patient record
Given Start visit and confirm visit
When I click on the "Attachment" menu 
And I upload a file
Then I should see a success message for the attachment

Scenario: Capture vitals and BMI calculation
Given Attachment section has attachment
And Recent Visit has one entry
And End Visit and Start Visit Again
When Click on "Capture Vitals" menu
And I enter vitals data
Then I should verify the BMI calculation and save the form
Then Click End Visit and redirect to patient details page
And Verify height and weight and BMI

Scenario: Merge two visits for the same patient
Given Recent visit has two entries
When I select two visits to merge
Then I should see the visits merged

Scenario: Add a past visit and verify the date picker
Given I am on the patient details page
When Click on the "Add Past Visit" menu
Then I should verify the future date is not clickable in the date picker

Scenario: Delete a patient and verify deletion
Given I am on patient details page 
When I click on "Delete Patient"
Then I should verify the patient is deleted and not visible in the search results 




