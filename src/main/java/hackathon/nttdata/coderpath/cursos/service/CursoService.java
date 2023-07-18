package hackathon.nttdata.coderpath.cursos.service;

import java.util.Map;

import hackathon.nttdata.coderpath.cursos.documents.Cursos;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CursoService {	
	Mono<Cursos> findById(String id);

	Flux<Cursos> findAlls();

	Mono<Cursos> saves(Cursos document);

	Mono<Void> delete(Cursos document);

	Map<String, Object> balanceadorTest();

}
