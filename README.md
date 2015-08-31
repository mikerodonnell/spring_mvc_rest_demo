
simple GET:
curl -X GET --verbose "http://localhost:8080/spring_mvc_rest_demo/recipe/c5515b57-55e9-4f37-963b-dc73ec399fb8"


does not exist ERROR GET:
curl -X GET --verbose "http://localhost:8080/spring_mvc_rest_demo/recipe/yayayayayayayya"



test create where already exists:
curl -X PUT --verbose --header "Content-Type: application/json" --data '{"recipeName":"soylent"}' "http://localhost:8080/spring_mvc_rest_demo/recipe/create"


valid simple create:
curl -X PUT --verbose --header "Content-Type: application/json" --data '{"recipeName":"bread", "prepMinutes":1, "cookMinutes":3, "ingredientName1":"flour", "amountUnit1":"cup", "amount1":3 }' "http://localhost:8080/spring_mvc_rest_demo/recipe/create"



valid more complicated create:
curl -X PUT --verbose --header "Content-Type: application/json" --data '{"recipeName":"bread", "ingredientName1":"flour", "amountUnit1":"cup", "amount1":3, "ingredientName2":"banana", "amountUnit2":"pile", "amount2":4, "ingredientName3":"eggs", "amountUnit3":"handful", "amount3":77 }' "http://localhost:8080/spring_mvc_rest_demo/recipe/create"




valid simple update (compare result with stub values):
curl -X PUT --verbose --header "Content-Type: application/json" --data '{ "prepMinutes": 99 }' "http://localhost:8080/spring_mvc_rest_demo/recipe/update/c5515b57-55e9-4f37-963b-dc73ec399fb8"



ingredients Search GET with results:
curl -X GET --verbose "http://localhost:8080/spring_mvc_rest_demo/recipe/search/ham"


valid but no results ingredients Search:
curl -X GET --verbose "http://localhost:8080/spring_mvc_rest_demo/recipe/search/cheetos"

