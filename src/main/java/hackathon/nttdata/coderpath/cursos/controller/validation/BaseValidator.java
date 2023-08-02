package hackathon.nttdata.coderpath.cursos.controller.validation;

import reactor.core.publisher.Mono;

public interface BaseValidator<T> {
	
	Mono<T> validate(T t);

}
