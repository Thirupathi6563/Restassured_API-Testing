Feature: Validate Place API's

@AddPlaceAPI @regression
Scenario Outline: Verify if place is being succesfully added using AddPlace API
   Given Add place Payload with "<name>" "<language>" "<address>"
   When  User calls "AddPlaceAPI" API with "POST" http request
   Then  The API call got success with status code is 200
   And   The "status" field in the response body is "OK"
   And   The "scope" field in the response body is "APP"
   And   verify place_id created in the maps to "<name>" using "GetPlaceAPI"
   
   
Examples:
  
  | name  | language | address      |
  | Gopal | Telugu   | Peddaraveedu |
 # | Thiru | English  | Hyderabad    |
 
 @DeletePlaceAPI  @regression
Scenario: Verify if Delete place functionality is working fine

   Given Delete place payload
   When  User calls "DeletePlaceAPI" API with "POST" http request
   Then  The API call got success with status code is 200
   And   The "status" field in the response body is "OK"