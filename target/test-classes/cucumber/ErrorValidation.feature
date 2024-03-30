
Feature: Error validation
  I want to use this template for my feature file


  @tag2
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then I verify the <status> in step

    Examples: 
      | name  | password | message  |
      | name1 |     5 | Incorrect email or password |
    
