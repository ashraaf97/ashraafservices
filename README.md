# Microservice & Distributed System

A distributed system project demonstrate the following:

- Spring boot (microservice)
- Spring Eureka Server (Discovery Service)
- Zipkin (tracer)
- Docker (Container)
- Minikube (Local kubernetes)
- RabbitMq (Message Queue)

Flow:
1) API send to customer service (+ save customer to  database)
2) Customer Service Request to fraud service (+ save customer to  database)
3) respond back to customer service
4) customer service send message to rabbitMQ queue 
5) RabbitMq send to Notification Service (+ save customer to  database)
