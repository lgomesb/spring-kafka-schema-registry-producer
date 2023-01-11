package pt.com.poc.schemaregistry.kafkaproducer.service.impl;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pt.com.poc.schemaregistry.kafkaproducer.model.avroschema.TaxPayer;
import pt.com.poc.schemaregistry.kafkaproducer.service.KafkaProducerService;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    @Autowired(required = true)
	@Qualifier(value = "taxpayerProducer")
	private KafkaProducer<String, TaxPayer> producer;

    @Override
    public void send(ProducerRecord<String, TaxPayer> record) {
        producer.send(record);
		producer.flush();
		producer.close();
        
    }
    
}
