Feature: Leanpay Checkout Process


  Scenario: Test basic call to checkout service
    When user sends checkout request
    Then valid response should be returned