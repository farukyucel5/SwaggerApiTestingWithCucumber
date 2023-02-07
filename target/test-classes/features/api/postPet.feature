Feature:pet store api testing

  @Api
  Scenario Outline:check if  post-a-pet,update-a-pet,finds-by-status api functions properly
    Given  create the endpoint with the "<path_param1>" ,"<path_param2>" and "<query_param>"
    And  save the response from the "<sectionName>" API with data "<name>","<id>","<categoryId>","<categoryName>","<tagId>","<tagName>","<photoUrl>","<status>"
    Then verify the actual response and expected one with data "<sectionName>"
    Examples:
      |path_param1   |path_param2 | sectionName   |query_param|id           |categoryId|name       |categoryName |tagId|tagName |photoUrl|status     |
      |pet           |            |post-a-pet     |           |456          |23        |Sword      |dog          |25   |Kangal  |        | sold      |
      |pet           |            |post-a-pet     |           |457          |24        |Honey      |cat          |33   |british |        | available |
      |pet           |            |post-a-pet     |           |212          |25        |DesertRose |camel1       |45   |Arabic1 |        | pending   |
      |pet           |            |post-a-pet     |           |214748364700 |25        |DesertRose |camel2       |45   |Arabic2 |        | pending   |






