 @HomePage
Feature: Verify Home Page


  Scenario: Validate the Home Page and Navigate to Sign-In Page
    Given User navigates to "https://www.letskodeit.com/home"
    And Page title should be "Home Page"
    And User should see the following elements:
      | HOME       |
      | ALL COURSES|
      | INTERVIEW  |
      | SUPPORT    |
      | BLOG       |
      | PRACTICE   |
    Then User navigates to Sign-In page