@Login
Feature: User Login and Forgotpassword functionality testing

@LoginWithInvalidCred
  Scenario Outline: Login Attempt with Invalid Credentials
    Given User arrives on Login page
    When User provides username "<email>" and password "<password>"
    And User presses Login button
    Then User should receive an error message "<errorMessage>"

    Examples:
      | email               | password      | errorMessage                                     |
      | Demo.example.com    | Password123   | The email must be a valid email address.         |
      | shanmukh@gmail.com  | wrongPass     | Incorrect login details                          |
      |                     | Password123   | The email field is required.                     |
      | Demo@example.com    |               | The password field is required.                  |

  @ForgotPassword
  Scenario Outline: Reset Password Using Forgot Password Feature
    Given User arrives on Login page
    When User initiates password reset
    Then User should land on Forgot Password page
    When User inputs "<Email>" for password reset
    Then User confirms password reset request

    Examples:
      | Email                 | Outcome                                        |
      | invalidEmail@gmail.com| We cannot find a user with that e-mail address |

 # @Login
 # Scenario: Login with Valid Credentials
 #   Given User is back on Sign-In page
 #   When User provide username "Shanmukh59@gmail.com" and password "P@ssword123"
 #   And User press Login button
 #   Then User should be logged in successfully
 #   And User should see the "My Courses" page