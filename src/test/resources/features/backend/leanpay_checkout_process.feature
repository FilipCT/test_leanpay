Feature: TST-1 - Leanpay Checkout Process Flow

  Description: Testing initial Leanpay checkout process flow.
  Covered services: Request Token, Checkout Payment Data, Request Pin, Authenticate, Session Info

  Scenario Outline: 1. Test basic checkout process flow
    ## Request token service
    Given user prepares request token request
    When user sends request token request
    Then valid request token response should be returned
    And extract request token from response

    # Checkout payment-data service
    And user prepares checkout payment request
    And user send checkout payment request
    And valid payment response should be returned

    # Checkout request-pin service
    And user prepares request-pin request using phoneNumber: <phoneNumber>
    And user sends request-pin request
    And valid request-pin response should be returned

    # Authenticate service
    And user prepares authenticate request using data <userName>,<password>
    And user sends authenticate request
    And valid authenticate response should be returned
    And extract idToken from authenticate response
    
    # Session-Info service
    And user prepares session info request
    And user sends session-info request
    And valid session-info response should be returned

    # Check personal details service
    And user prepares check personal details request
    And user sends check personal details request
    And valid check personal details response should be returned

    Examples:
      | phoneNumber    | userName       | password |
      | 38673573573573 | 38673573573573 | 5533     |