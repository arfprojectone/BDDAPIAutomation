Feature: User Endpoint

  Scenario: [Positive Case][Create User] Success Create User
    Given I have a valid user payload
    When I send a request to create a new user
    Then the response status should be 200

  Scenario: [Positive Case][Login] Success Login with Valid Credentials
    Given I have a valid username "valid.username" and password "valid.password"
    When I send a request to login
    Then the response status should be 200

  Scenario: [Negative Case][Login] Failed Login Cause Invalid Method
    Given I have a valid username "valid.username" and password "valid.password"
    When I send a request to login with PUT method
    Then the response status should be 405

  Scenario: [Positive Case][Get User by Username] Get User By Username with Valid Username
    Given I have a valid username "string"
    When I send a request to get the user details
    Then the response status should be 200
    And the response should contain username "string"

  Scenario: [Negative Case][Get User by Username] Get User By Username with Invalid Username
    Given I have a valid username "testing"
    When I send a request to get the user details
    Then the response status should be 404
    And the response should contain message "User not found"

  Scenario: [Positive Case][Delete User] Success Delete Existing User
    Given I have an existing username "string"
    When I send a request to delete the user
    Then the response status should be 200

  Scenario: [Negative Case][Delete User] Failed Delete User Not Found
    Given I have an not found username "invalidUsername"
    When I send a request to delete the user
    Then the response status should be 404

  Scenario: [Positive Case][Update User] Success Update Existing User
    Given I have an existing username "string"
    And I have a valid update payload
    When I send a request to update the user
    Then the response status should be 200

  Scenario: [Negative Case][Update User] Failed Update Cause id is Maximum Digit
    Given I have an existing username "string"
    And have a invalid update payload, id maximum digit
    When I send a request to update the user
    Then the response status should be 500
    And the response should contain message "something bad happened"

  Scenario: [Negative Case][Update User] Failed Update Cause Invalid Method
    Given I have an existing username "string"
    And I have a valid update payload
    When I send a request to update the user with POST method
    Then the response status should be 405