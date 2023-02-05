Feature:pet store api testing

  @Api
  Scenario Outline:check if the post-a-pet api functions properly
    Given  create the swagger api endpoint with the path parameter "<path_parameter>"
    And  save the response from the "<sectionName>" API
    Then verify that the expected response and the actual response are the same as each other
    Examples:
      |path_parameter| sectionName |
      |pet           |post-a-pet   |
      |pet           |update-a-pet |



