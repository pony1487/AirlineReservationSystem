### Using API
```
curl -v --user <someUser>:<somePassword> http://localhost:9090/booking  --request POST --header "Content-Type: application/json" --data '{"flightId":"3", "customerId":"2"}'
```