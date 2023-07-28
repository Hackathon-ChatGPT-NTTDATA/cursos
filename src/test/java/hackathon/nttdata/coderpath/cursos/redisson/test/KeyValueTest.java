package hackathon.nttdata.coderpath.cursos.redisson.test;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBucketReactive;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class KeyValueTest extends BaseTest {

	@Test
	public void keyValueAccessTest() {

		RBucketReactive<String> bucket = this.client.getBucket("alumno:1:name");
		Mono<Void> set = bucket.set("carlo");
		Mono<Void> get = bucket.get()
				.doOnNext(System.out::println)
				.then();
		
		StepVerifier.create(set.concatWith(get))
			.verifyComplete();
	}
}
