Feature:pet store api testing

  @Api
  Scenario:check if the post-a-pet api functions properly
    Given  create the swagger api endpoint with the path parameter "pet"
    And  save the response from the API
    Then verify that the expected response and the actual response are the same as each other
