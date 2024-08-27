# Set Up Kafka in Local using Docker

## Set up broker and zookeeper

- **start/stop for kafka using required configuration file**

```
docker-compose -f docker-compose-core.yml -p core up -d

docker-compose -f docker-compose-core.yml -p core down -d
```

## Producer and Consume the Messages

- Let's going to the container by running the below command.

```
docker exec -it kafka1 bash
```


- Create a Kafka topic using the **kafka-topics** command.   
```
kafka-topics.sh --bootstrap-server localhost:9092 \
             --create \
             --topic t-hello \
             --replication-factor 1 \
             --partitions 1
```


### List the topics in a cluster

```
kafka-topics.sh --bootstrap-server localhost:9092 --list
```

### Describe topic

- Command to describe all the Kafka topics.

```
 docker exec --interactive --tty kafka kafka-topics.sh \
             --bootstrap-server kafka:9092 
             --describe
```

- Command to describe a specific Kafka topic.

```
kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic t-hello
```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
