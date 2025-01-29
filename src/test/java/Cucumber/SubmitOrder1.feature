

Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file
  
 Background: 
 Given I landed on Ecommerce page	
 @Regression
 Scenario Outline: Positive test of submitting order
 
 Given Logged in with userName <userName> and password <password>
 When I add product <productName> to cart
 And checkout <productName> and submit the order
 Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage
 


    Examples: 
      | userName  			             |	password | productName  |
      | learnseleniumv@gmail.com | Learn@999 | ADIDAS ORIGINAL |  
