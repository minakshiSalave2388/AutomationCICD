
Feature: Error validation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When Loggin with username <name> and password <password>
    Then <message> message is dispalyed

    Examples: 
      | name  | password | message  |
      | anshika@gmail.com |     Iamking@0 | Incorrect email or password. |
    
