Feature: Direct messaging on twitter

  Scenario: Two users who are followers of each other can send direct messages
    Given Joe, Julie are on twitter
    And Julie is a follower of Joe
    And Joe is a follower of Julie
    When Joe sends a DM to Julie
    Then Julie should see Joe’s message

  Scenario: A user cannot send a direct message to someone who is not a follower
    Given Joe, Julie are on twitter
    And Julie is NOT following Joe
    And Joe is a follower of Julie
    When Joe sends a DM to Julie
    Then Joe should get an error response
    And Julie should not receive Joe’s message