
@Shoppingcart
Feature: Shopping Cart Functionality

	@AddItem
  Scenario: Add item to cart and check total
    Given User is on the shopping page
    When User adds a garment to the cart
    Then Cart total should match the garment price 

	@RemoveFromCart
  Scenario: Remove an item from the cart
    When User removes the default garment from the cart
    Then The item should be removed successfully  
	
	@checkout
  Scenario: Proceed to checkout
    When User clicks on checkout
    Then User should reach the checkout page 
