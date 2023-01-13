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
		String name = "Test";
		TaxPayer taxPayer = TaxPayer.newBuilder()
		.setName(name)
		.setDocument("Valor do Documento")
		.setSituation(true)
		.build();
		
		for( int i = 0 ; i < 10; i++) {
			taxPayer.put("name", name + " " + i);
			service.send(new ProducerRecord<String,TaxPayer>(TOPIC, taxPayer));
			System.out.printf(":::: Send event: ", taxPayer);
			Thread.sleep(500l);
		}

	}

}
