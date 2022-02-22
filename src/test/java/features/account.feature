Feature: Create and login user

  Scenario Outline: User creates a new account
    Given User is on a main page
    When User provides his email address
    And User fills the registration form with his personal data: <firstName>,<lastName>,email,<password>,<day>,<month>,<year>
    And User provides address data:<firstName>,<lastName>,<address>,<city>,<state>,<postalcode>,<country>,<phoneNumber>,<aliasAddress>
    Then User has successfully created an account as a <firstName>,<lastName>
    And User can login to his account at any time with email and <password>
    Examples:
      | firstName | lastName |  password | day | month | year | address | city    | state    | postalcode |   country    | phoneNumber   | aliasAddress |
      |     Bart  |    P     |   Test123  |  10 |  5    | 2000 | Brook   |  Duluth | Minnesota|    00000    | United States|     1234567987|    Home      |
