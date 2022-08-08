@Scenario
Feature: feature to test the coupon activation  functionality

@Test
  Scenario: Check if correct coupon is activated for the login
    Given User is at the home page
    
     When User clicks on the Coupons icon
    And User selects the coupon in the coupons page
    Then User activate the first coupon