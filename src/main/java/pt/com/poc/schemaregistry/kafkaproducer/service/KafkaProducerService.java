package pt.com.poc.schemaregistry.kafkaproducer.service;

import org.apache.kafka.clients.producer.ProducerRecord;

import pt.com.poc.schemaregistry.kafkaproducer.model.avroschema.TaxPayer;

public interface KafkaProducerService {
    public void send(ProducerRecord<String, TaxPayer> record);
}
