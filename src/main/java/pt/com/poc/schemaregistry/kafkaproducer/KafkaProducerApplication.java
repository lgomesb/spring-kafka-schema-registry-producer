package pt.com.poc.schemaregistry.kafkaproducer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pt.com.poc.schemaregistry.kafkaproducer.model.avroschema.TaxPayer;
import pt.com.poc.schemaregistry.kafkaproducer.service.KafkaProducerService;

@SpringBootApplication
public class KafkaProducerApplication implements CommandLineRunner {

	private static final String TOPIC = "taxpayer-avro-topic";

	@Autowired(required = true)
	private KafkaProducerService service;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		TaxPayer taxPayer = TaxPayer.newBuilder()
		.setName("Nome Exemplo")
		.setDocument("Valor do Documento")
		.setSituation(true)
		.build();

		service.send(new ProducerRecord<String,TaxPayer>(TOPIC, taxPayer));

	}

}
