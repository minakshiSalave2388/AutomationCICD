
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce Page

  @tag2
  Scenario Outline: Positive Test of submitting the order
    Given Loggin with username <name> and password <password>
    When I add product <productName> from cart
    And Checkout <productName> and submit the order
    Then <message> message is displayed on confirmation page

    Examples: 
      | name  						| password 				| productName| message |
      | rahulshetty@gmail.com |     IamKing@000 | ZARA COAT 3|THANKYOU FOR THE ORDER.|
    
