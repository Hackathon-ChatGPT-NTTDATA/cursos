package hackathon.nttdata.coderpath.cursos.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

	@KafkaListener(topics = "orders", groupId = "coder_path")
	public void consume(String message,
			@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
	        @Header(KafkaHeaders.OFFSET) int offset) {
		System.out.println("message received: " + message);
		System.out.println("partition: " + partition + " offset: " + offset + " key: " + key);
	
	}

}
