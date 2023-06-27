package hackathon.nttdata.coderpath.cursos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void publishMessage(String topicName, String message) {

		kafkaTemplate.send(topicName, "CODER PATH", message);
		System.out.println("Message published -->" + message);
	}
}
