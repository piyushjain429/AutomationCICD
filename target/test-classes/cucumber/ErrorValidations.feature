@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag2
  Scenario Outline: Title of your scenario outline
    Given I Landed On EcommercePage
    When Logged In With Username <username> and Password <password>
    Then "Incorrect email or password." Message Is Displayed

    Examples: 
      | username  										| password 		|
      | piyush@rahulshettyacademy.com | Welcome#	  |
