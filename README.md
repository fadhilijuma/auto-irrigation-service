# Auto Irrigation Service

----

Auto Irrigation Service is a service that integrates plots of land in Egypt to sensors spread out across the plots of land. I implemented an almost similar system based on MQTT and Apache Kafka receiving notifications events from POS terminals. I gave a talk about it at the London Kafka Summit. The solution is about using MQTT and Apache Kafka to run devices that run where internet is very minimal, which is the case with sensors. They mostly operate in fields where we have limited internet. https://www.confluent.io/resources/kafka-summit-2020/mqtt-and-apache-kafka-the-solution-to-poor-internet-connectivity-in-africa/


## Architecture

Apache Kafka is the central data plane which acts as the mediator af all communication and events. We have an MQTT Broker cluster
that receives and wires message events and notifications from and to sensors across the land. 

All messages from and to MQTT Broker are send to Apache Kafka. 


## NB:
- MQTT Cluster and Kafka Cluster has not been implemented. We only run a minimal Kafka instance in Kraft Mode on docker i.e. without Zookeeper and Postgres database. 
- Sensors communication with MQTT has not been implemented.

----

## Assumptions in our architecture
These are the assumptions:
- > We will have an atmometer sensor to measure the evatranspiration of the plots. Evapotranspiration is the measure of the rate of water evaporation into the atmosphere from the soil surface, evaporation from the capillary fringe of the groundwater table, and evaporation from water bodies on land. We need to know and estimate the rate of water evaporation from the soil to determine the amount of time the water will stay in the soil before we need to irrigate.
- > We will have a moisture content sensor to give us the moisture content of the soil at a particular time. These readings could be configured to transmit at a certain interval.
- > We will have an irrigation sensor. This sensor will receive notifications from MQTT Broker to start irrigating the land.
  

---

<img src="https://github.com/fadhilijuma/auto-irrigation-service/raw/main/drawio.png" >

---
## Schema Architecture
We are making 2 major assumptions in the schema. 

- We are assuming that a certain type of crop can be planted in many plots *one crop to many plots*.

- We are also assuming that there could be many sensors in a plot of land as explained earlier. 

---

<img src="https://github.com/fadhilijuma/auto-irrigation-service/raw/main/schema.png" >


---

## Services Breakdown 
- *API Service*


  This service is used to configure the plots, crops and the sensors.


- *cron-jobs*


  This is a scheduled and periodic service that runs an *SQL VIEW* to obtain the plot id , the sensor id and the amount of water to be irrigated. 
  
```sql
CREATE OR REPLACE VIEW slots_view AS
SELECT   row_number() over (order by (select 1)) as id,p.id as plot_id, sensor_id, (cast(c.ideal_moisture_content as int) - cast(p.current_moisture_content as int)) as volume
FROM  plots p
          INNER JOIN  crops  c
                      ON      c.id = p.crop_id
WHERE p.current_moisture_content < c.ideal_moisture_content;
```
This view calculates the amount of water to be irrigated by getting the difference between the crop ideal moisture content and the current moisture content.


- *sensor service*

This service reads irrigation events posted to Kafka by the scheduled cron jobs and then sends these events to the sensor. In an ideal production environment, we would configure the Kafka Broker to wire events directly to MQTT. Sensors would then read events from MQTT topics of interest.


- *notification service*

This service listens to events posted on the *notification topic* and should ideally then send this notification to a provider like Twilio.


## Production

In production, we would configure the MQTT Broker Cluster to wire events from the sensors to Kafka Broker cluster directly. We would also need a way to synchronise the jobs if we have to run then in a cluster. 