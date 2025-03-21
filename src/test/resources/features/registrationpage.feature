@Registration
Feature: User Account creation and testing

  @SigninPage
  Scenario: Navigate to Sign-Up Page from Login Page
    Given User is on Login page for registration
    Then User selects Sign-Up option

  @Register
  Scenario Outline: User Registration with Various Scenarios
    Given User should land on Registration page
    When User provides registration details "<FirstName>", "<LastName>", "<Email>", "<Password>", "<ConfirmPassword>"
    And User solves Captcha challenge
    And User submits registration form
    Then User verifies registration outcome "<Outcome>"
    Then User logs out if login is successful

    Examples:
      | FirstName | LastName | Email              | Password      | ConfirmPassword | Outcome                                                 |
      | Shanmukh  | Nakkella | Shanmukh.com       | P@ssword123   | P@ssword123     | Please include an '@' in the email address. 'Shanmukh.com' is missing an '@'. |
      | Shanmukh  | Nakkella | Shanmukh9@gmail.com | P@ssword123   | P@ssword456     | Passwords do not match.                                  |
      |           | Nakkella | Shanmukh@gmail.com | P@ssword123   | P@ssword123     | Please fill out this field.                              |
      | Shanmukh  | Nakkella | Shanmukh56@gmail.com | P@ssword123   | P@ssword123    | My Courses                                              |

  