package pt.com.poc.schemaregistry.kafkaproducer.service;

import org.apache.kafka.clients.producer.ProducerRecord;

public interface KafkaProducer {
    
    public void send(ProducerRecord<String,TaxPayerer> record);
}
