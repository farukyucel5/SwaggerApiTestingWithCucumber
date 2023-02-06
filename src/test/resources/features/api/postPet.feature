Feature:pet store api testing

  @Api
  Scenario Outline:check if  post-a-pet,update-a-pet,finds-by-status api functions properly
    Given  create the endpoint with the "<path_param1>" ,"<path_param2>" and "<query_param>"
    And  save the response from the "<sectionName>" API
    Then verify the expected response and the actual response are the same as each other in "<sectionName>"
    Examples:
      |path_param1   |path_param2 | sectionName   |query_param|
      |pet           |            |post-a-pet     |           |
      |pet           |            |update-a-pet   |           |
      |pet           |findByStatus|finds-by-status|available  |
      |pet           |555         |getById        |           |







