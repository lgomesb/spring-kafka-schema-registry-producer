package pt.com.poc.schemaregistry.kafkaproducer.config;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import pt.com.poc.schemaregistry.kafkaproducer.config.properties.KafkaProperties;
import pt.com.poc.schemaregistry.kafkaproducer.model.avroschema.TaxPayer;

@Configuration
public class MessagingConfigTaxPayer implements MessagingConfigPort<TaxPayer> {

     @Autowired
     private KafkaProperties kafkaProperties;

    @Bean(name = "taxpayerProducer")
    @Override
    public KafkaProducer<String, TaxPayer> configureProducer() {
        
        Properties properties = new Properties();
		
        properties.put(ProducerConfig. BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        properties.put(ProducerConfig.ACKS_CONFIG, kafkaProperties.getAcksConfig());
        properties.put(ProducerConfig.RETRIES_CONFIG, kafkaProperties.getRetriesConfig());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProperties.getKeySerializer());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaProperties.getValueSerializer());
        properties.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, kafkaProperties.getSchemaRegistryUrl());
        
        return new KafkaProducer<String, TaxPayer>(properties);
    }
    
}
