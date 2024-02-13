### Using API
```
# Making booking
curl -v --user <someUser>:<somePassword> http://localhost:9090/booking  --request POST --header "Content-Type: application/json" --data '{"flightId":"1", "customerId":"1"}'
```

```
# Reset full flight
curl -v --user apiUser:12345 http://localhost:9090/plane/resetReservedSeats/{flight_number}  --request POST
```

### Running Kafka
```
# Go to Kafka dir
cd /path/to/kafka_2.13-3.6.1

# Start the ZooKeeper service
bin/zookeeper-server-start.sh config/zookeeper.properties

# Open a new terminal and run
bin/kafka-server-start.sh config/server.properties
```