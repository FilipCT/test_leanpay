Feature: TST-2 - Vendor API: Credit Calculation

  Description: Testing Leanpay Credit Calculation service.
  Both positive and negative test cases are covered

  @backend
  Scenario Outline: 1. Test vendor API when sending valid vendor api key
    Given user prepares credit calculation request with data: "<vendorApiKey>",<amount>,<term>
    When user sends credit calculation request
    Then valid credit calculation response should be returned

    Examples:
      | vendorApiKey | amount | term |
      | fdsafda      | 900    | 6    |

  Scenario Outline: 2. Test vendor API when sending incorrect vendor api key
    Given user prepares credit calculation request with data: "<vendorApiKey>",<amount>,<term>
    When user sends credit calculation request
    Then valid error code <errorCode> and error message "<errorMessage>" should be returned

    Examples:
      | vendorApiKey  | amount | term | errorCode | errorMessage              |
      | 1111-test-111 | 900.0  | 6    | 400       | VENDOR_API_VENDOR_UNKNOWN |

  Scenario Outline: 3. Test vendor API when sending incorrect amount - less than required
    Given user prepares credit calculation request with data: "<vendorApiKey>",<amount>,<term>
    When user sends credit calculation request
    Then valid error code <errorCode> and error message "<errorMessage>" should be returned

    Examples:
      | vendorApiKey                         | amount | term | errorCode | errorMessage                   |
      | 600c2f6c-8b3c-4972-ba61-d667dc4d1d2b | 1      | 6    | 400       | VENDOR_API_MIN_AMOUNT_EXCEEDED |

  Scenario Outline: 4. Test vendor API when sending incorrect amount - greater than required
    Given user prepares credit calculation request with data: "<vendorApiKey>",<amount>,<term>
    When user sends credit calculation request
    Then valid error code <errorCode> and error message "<errorMessage>" should be returned

    Examples:
      | vendorApiKey                         | amount | term | errorCode | errorMessage                   |
      | 600c2f6c-8b3c-4972-ba61-d667dc4d1d2b | 10000  | 6    | 400       | VENDOR_API_MAX_AMOUNT_EXCEEDED |

  Scenario Outline: 5. Test vendor API when sending incorrect term
    Given user prepares credit calculation request with data: "<vendorApiKey>",<amount>,<term>
    When user sends credit calculation request
    Then valid error code <errorCode> and error message "<errorMessage>" should be returned

    Examples:
      | vendorApiKey                         | amount | term | errorCode | errorMessage            |
      | 600c2f6c-8b3c-4972-ba61-d667dc4d1d2b | 900.0  | 2    | 400       | VENDOR_API_TERM_UNKNOWN |