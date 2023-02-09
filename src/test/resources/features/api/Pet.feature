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
      |pet           |            |post-a-pet     |           |2147483647   |25        |DesertRose |camel2       |45   |Arabic2 |        | pending   |
      |pet           |            |update-a-pet   |           |456          |5         |Kilic      |dog          |25   |Kangal  |        | available |
      |pet           |            |update-a-pet   |           |457          |24        |Honey      |cat          |33   |british |        | sold      |


  @Api
  Scenario Outline:check if findsByStatus and findById features function properly
    Given  create the endpoint with the "<path_param1>" ,"<path_param2>" and "<query_param>"
    And  save the response from the "<sectionName>" API with data "<name>","<id>","<categoryId>","<categoryName>","<tagId>","<tagName>","<photoUrl>","<status>"
    Then verify the actual response and expected one are the same in the "<sectionName>"
    Examples:
      |path_param1   |path_param2  | sectionName       |query_param    |id           |categoryId|name       |categoryName |tagId|tagName |photoUrl|status     |
      |pet           |findByStatus |find-a-pet         |available      |             |          |           |             |     |        |        |           |
      |pet           |456          |find-a-petById     |find-a-petById |456          |23        |Kilic      |dog          |25   |Kangal  |        | sold      |
      |pet           |457          |find-a-petById     |find-a-petById |457          |24        |Honey      |cat          |33   |british |        | available |
      |pet           |212          |find-a-petById     |find-a-petById |212          |25        |DesertRose |camel1       |45   |Arabic1 |        | pending   |


  @Api
  Scenario Template: Check if deletes-a-pet functions or not
    Given  create the endpoint with the "<path_param1>" ,"<path_param2>" and "<query_param>"
    And  save the response from the "<sectionName>" API with data "<name>","<id>","<categoryId>","<categoryName>","<tagId>","<tagName>","<photoUrl>","<status>"
    Then verify the element is deleted
    Examples:
      | path_param1 | path_param2 | query_param |sectionName |
      |pet          |456          |delete-a-pet |delete-a-pet|
      |pet          |457          |delete-a-pet |delete-a-pet|
      |pet          |212          |delete-a-pet |delete-a-pet|



