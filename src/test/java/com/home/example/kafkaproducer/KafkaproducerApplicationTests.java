package com.home.example.kafkaproducer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EnableKafka
@EmbeddedKafka(brokerProperties = {"listeners=PLAINTEXT://127.0.0.1:9022"})
class KafkaproducerApplicationTests {

	@Test
	void contextLoads() {
	}

}
