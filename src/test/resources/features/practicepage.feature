@PracticePage
Feature: Practice Page Functionality

Background:
    Given I am on the Practice Page
    
	
	@RadioButtons
  Scenario: Verify Radio Button Functionality
    When I select and verify the following radio buttons:
      | BMW   |
      | Benz  |
      | Honda |
    Then only one radio button should be selected at a time
	
	@CheckBox
  Scenario: Verify Checkbox Functionality

    When I select the following checkboxes:
      | BMW   |
      | Benz  |
      | Honda |
    Then the following checkboxes should be selected:
      | BMW   |
      | Benz  |
      | Honda |
	
	@Dropdown
  Scenario: Verify Dropdown Functionality
   
    When I select the following values from the dropdown:
      | BMW   |
      | Benz  |
      | Honda |
    Then the following values should be selected in the dropdown:
      | BMW   |
      | Benz  |
      | Honda |
	
	@MultipleSelect
  Scenario: Verify Multiple Select Functionality
    
    When I select the following values from the multiple select:
      | Apple  |
      | Orange |
      | Peach  |
    Then the following values should be selected in the multiple select:
      | Apple  |
      | Orange |
      | Peach  |
     
    
    @AutoSuggest
   Scenario: Verify Auto Suggest Functionality
  	
    When I type "Java" in the auto-suggest field
    Then the following suggestions should appear:
      | Selenium WebDriver Java |
      | Appium Java             |
      | Java                    |
    When I select "Selenium WebDriver Java" from the suggestions
    Then the input field should contain "Selenium WebDriver Java"
    
  @NewWindow  
  Scenario: Verify New Window Functionality
    
    When I click the "Open Window" button
    Then a new window should open
    And I switch to the new window
    And the title of the new window should be "All Courses"
	
	@NewTab
  Scenario: Verify New Tab Functionality
   
    When I click "Open Tab" button
    Then a new tab should open
    And I switch back to practice page tab
    And the title of the new tab should be "Practice Page"
	
	@WindowSwitching
  Scenario: Verify Window Switching Functionality
   
    When I open 2 new windows
    And I switch between windows
    Then all windows should be navigable
    
	@EnabledOrDisabled
  Scenario: Verify Enabled/Disabled Field Functionality
    When I disable the input field
    Then the input field should be disabled
    When I enable the input field
    Then the input field should be enabled

	@HideorShow
  Scenario: Verify Hide/Show Functionality
   
    When I hide the textbox
    Then the textbox should be hidden
    When I show the textbox
    Then the textbox should be visible

	@Alert
  Scenario: Verify Alert Functionality
    
    When I enter "Shanmukh" in the alert field and click the alert button
    Then an alert with the message "Hello Shanmukh, share this practice page and share your knowledge" should appear
    
	@MouseHover
  Scenario: Verify Mouse Hover Functionality
    When I hover over the "Mouse Hover" button
    Then the following options should appear:
      | Top    |
      | Reload |
   
  @WebTable
  Scenario: Verify Web Table Functionality
    When I check the course "Selenium WebDriver With Java"
    Then the price should be "35"
    
    