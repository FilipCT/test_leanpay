Feature: TST-1 - Test Checkout-Purchase Flow

  Description: Testing initial Leanpay checkout-purchase flow - positive

  @frontend
  Scenario Outline: 1. Test initial checkout flow
    ## Choose item to buy
    Given user access checkout page
    And user chooses item 2 from the list to buy
    When user clicks BuyWithLeanPay button
    Then valid checkout page is opened

    ## Choose installment options
    And user chooses installment option: <monthlyInstallment>, <paymentDate>
    And user clicks on confirm button
    And valid page for providing phone number is opened

    ## Enter mobile phone number
    And user selects <countryCode> country code
    And user enters phone number: <phoneNumber> and clicks confirmation button
    And valid page for providing pin code is opened

    ## Enter verification code
    And user enters pin: <pinCode> and clicks confirmation button
    And valid page for providing personal data is opened

    ## Enter personal data
    And user enters personal data and clicks on confirmation button
      | firstName | lastName | email            | postCode         | region    | street         | houseNumber | gender | dateOfBirth | placeOfBirth | countryOfBirth | taxNumber |
      | Janez     | Novak    | test@leanpay.com | 1000 - Ljubljana | Ljubljana | Abramova Ulica | 55          | Moški  | 01/01/1990  | Ljubljana    | Slovenija      | 85225885  |
    And valid page for providing personal security code is opened
    And user enters personal security pin: <securityPinCode> and clicks confirmation button
    And valid page for providing financial data is opened

    ## Enter financial data
    And user enters financial data
      | monthlyEarnings | creditLiabilities | typeOfEmployment  | familyMembers | transactionNumber   |
      | 1000            | 150               | Polni delovni čas | 2             | SI56010000000100090 |
    And valid page for reviewing financial data is opened

    ## Review and confirm financial data
    And user reviews financial data and click on confirmation button
    And check if the correct credit amount is approved

    ## Credit agreement confirmation
    And check if the credit agreement page is opened
    And user confirms credit agreement

    ## Successful credit agreement
    And page for announcing successful credit agreement is opened

    Examples:
      | monthlyInstallment | paymentDate     | countryCode | phoneNumber | pinCode | securityPinCode |
      | 6 months           | 17.in the month | SRB         | 73587373    | 5533    | 959595          |
      #| 12 months          | 7.in the month  | SRB         | 73587374    | 5533    | 959595          |