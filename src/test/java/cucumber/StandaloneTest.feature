@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

	Background:
	Given I Landed On EcommercePage

  @Regression
  Scenario Outline: Positive test of submitting an order
    Given Logged In With Username <username> and Password <password>
    When I Add Product <productName> To Cart
    And Checkout <productName> and Submit The Order
    Then "THANKYOU FOR THE ORDER." Message Is Displayed On ConfirmationPage

    Examples: 
      | username  										| password 		| productName  |
      | piyush@rahulshettyacademy.com | Welcome#123 | ZARA COAT 3 |
