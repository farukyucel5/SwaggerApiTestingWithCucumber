Feature:pet api testing

  @Api
  Scenario Outline:check if  post-a-pet and update-a-pet features function properly
    Given  create the endpoint with the "<path_param1>" ,"<path_param2>" and "<query_param>"
    And  save the response from the "<sectionName>" API with data "<name>","<id>","<categoryId>","<categoryName>","<tagId>","<tagName>","<photoUrl>","<status>"
    Then verify the actual response and expected one are the same in the "<sectionName>"
    Examples:
      |path_param1   |path_param2 | sectionName   |query_param|id           |categoryId|name       |categoryName |tagId|tagName |photoUrl|status     |
      |pet           |            |post-a-pet     |           |456          |23        |Sword      |dog          |25   |Kangal  |        | sold      |
      |pet           |            |post-a-pet     |           |457          |24        |Honey      |cat          |33   |british |        | available |
      |pet           |            |post-a-pet     |           |212          |25        |DesertRose |camel1       |45   |Arabic1 |        | pending   |
      |pet           |            |post-a-pet     |           |214748364700 |25        |DesertRose |camel2       |45   |Arabic2 |        | pending   |
      |pet           |            |update-a-pet   |           |456          |5         |Kilic      |dog          |25   |Kangal  |        | available |
      |pet           |            |update-a-pet   |           |457          |24        |Honey      |cat          |33   |british |        | sold      |


  @Api
  Scenario Outline:check if findsByStatus features function properly
    Given  create the endpoint with the "<path_param1>" ,"<path_param2>" and "<query_param>"
    And  save the response from the "<sectionName>" API with data "<name>","<id>","<categoryId>","<categoryName>","<tagId>","<tagName>","<photoUrl>","<status>"
    Then verify the actual response and expected one are the same in the "<sectionName>"
    Examples:
      |path_param1   |path_param2  | sectionName   |query_param|id           |categoryId|name       |categoryName |tagId|tagName |photoUrl|status     |
      |pet           |findByStatus |find-a-pet     |available  |             |          |           |             |     |        |        |           |






