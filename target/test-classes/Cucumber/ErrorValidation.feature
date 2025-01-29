
@tag
Feature: Error validation
  I want to use this template for my feature file

 

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce page	
    When Logged in with userName <userName> and password <password>
    Then "Incorrect email  password." message is displayed

     Examples: 
      | userName  			             |	password |
      | learnseleniumv@gmail.com | Learn@9 | 

