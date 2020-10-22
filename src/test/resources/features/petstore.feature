Feature: DEMO PET STORE

  Scenario Outline: get pet <status>
    When Get <status> pets
    Then Validate pet status as <status>

    Examples:
      | status    |
      | available |
      | sold      |


  Scenario: create pet
    When Create New Pet with Random Id
      | name | photoUrls             |
      | Tom  | Tom's wonderfulPhotos |
    Then Validate The Pet X is created
    When Update The Pet X status as sold
    Then Validate The Pet X has status sold
    When Delete The Pet X

