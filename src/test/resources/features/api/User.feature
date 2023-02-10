Feature: Operations about user
  #[
  #{
  #"id": 0,
  #"username": "string",
  #"firstName": "string",
  #"lastName": "string",
  #"email": "string",
  #"password": "string",
  #"phone": "string",
  #"userStatus": 0
  #}
  #]
  @Smoke
  Scenario Template: Create user object with given input array
    Given create the endpoint with the "<path_param1>" ,"<path_param2>" and "<query_param>"
    And  save the response from the "<sectionName>" API with data "<id>","<username>","<firstname>","<lastname>","<email>","<password>","<phone>","<userStatus>"
    Then verify the actual response and expected one are the same in the "<sectionName>"
    Examples:
      | path_param1 | path_param2   | query_param | sectionName | id | username | firstname | lastname | email      | password | phone         | userStatus |
      |user         |createWithArray|             | createUser  |12  |farukYucel|faruk      | yucel    |f@gmail.com |f123      | 07453245      |    1       |
      |user         |createWithArray|             | createUser  |13  |harunYucel|harun      | yucel    |h@gmail.com |h123      | 002353245     |    0       |


