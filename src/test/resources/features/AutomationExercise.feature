@e2e
Feature: End to End Purchase Flow - data-driven from feature file

  Scenario Outline: Create account, add products, checkout, pay, and delete account
    Given User launches the browser and navigates to "<baseUrl>"
    When User clicks Signup Login
    Then New User Signup is visible
    When User enters signup name "<name>" and email "<email>"
    And User clicks Signup button
    Then Enter Account Information should be visible
    When User fills account details with password "<password>" day "<day>" month "<month>" year "<year>"
    And User selects newsletters
    And User fills address "<firstName>" "<lastName>" "<company>" "<address1>" "<address2>" "<country>" "<state>" "<city>" "<zipcode>" "<mobile>"
    And User clicks Create Account
    Then Account Created should be visible
    When User clicks Continue after account creation
    Then User should be logged in as "<name>"
    When User opens Products
    And User add products to cart
    And User clicks View Cart
    When User proceeds to checkout
    Then Address details and order review should be visible
    When User enters comment "<comment>" and places order
    And User enters payment details name "<cardName>" card "<cardNumber>" cvc "<cvc>" month "<expMonth>" year "<expYear>"
    And User clicks Pay and Confirm
    Then Order success message should be visible
    When User deletes account
    Then Account Deleted should be visible and continue

    Examples:
      | baseUrl                       | name     | email                         | password  | day | month | year | firstName | lastName | company | address1        | address2 | country | state | city  | zipcode | mobile     | comment                 | cardName   | cardNumber       | cvc | expMonth | expYear |
      | http://automationexercise.com | AnkurQA  | ankur.qa+<random>@example.com | P@ss1234  | 10  | June  | 1990 | Ankur     | Rana     | Dubai Company  | 123 Test Street | Apt 10   | India   | Delhi | Delhi | 110001 | 9999999999 | Please deliver between 9-6 | Ankur Rana | 4111111111111111 | 123 | 12       | 2030    |
