URI /gm/v1
Get /gm/v1/movies
Response Body:
{
"title":"Titanic",
"rating":4.0
},
{
"title":"Speed",
"rating":5.0
}
Get /movies/{title}
Response Body:
{
"title":"Titanic",
"rating":4.0
}
Post /movies
Response Body:
{
"title":"Speed",
"rating":5.0
}
Put /movies
Response Body:
{
"title":"Speed",
"rating":5.0
}
Put /movies/{title}
Response Body:
{
"title":"Speed",
"rating":5.0
}