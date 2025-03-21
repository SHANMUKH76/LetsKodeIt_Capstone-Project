@AllCourses
Feature: Course Filtering and Search Functionality

	Background: Given I am on the All Courses page
	
	@category
  Scenario Outline: Filter courses by category
    When I select "<category>" from the category dropdown
    Then I should see "<expected_heading>" displayed on the page

    Examples:
      | category                | expected_heading                 |
      | All                     | All Courses                      |
      | Sotfware Testing        | Category : Sotfware Testing      |
      | Software Development    | Category : Software Development  |
      | Test Automation         | Category : Test Automation       |
      | Python                  | Category : Python                |
      | Selenium WebDriver      | Category : Selenium WebDriver    |
      | API Automation          | Category : API Automation        |
      | JavaScript              | Category : JavaScript            |
      | Cypress                 | Category : Cypress               |

	@search
  Scenario Outline: Search courses by keyword
    When I enter "<search_keyword>" in the search course field and click search
    Then I should see a course titled "<expected_course>" displayed on the page

    Examples:
      | search_keyword | expected_course                    |
      | Java          | Java Step By Step For Testers       |
      | Selenium      | Selenium WebDriver Automation       |
      | API          	| API Testing Automation              |
