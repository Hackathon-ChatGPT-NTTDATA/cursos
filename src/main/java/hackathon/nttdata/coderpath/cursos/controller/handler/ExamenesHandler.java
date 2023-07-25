package hackathon.nttdata.coderpath.cursos.controller.handler;

import static org.springframework.http.MediaType.*;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;


import hackathon.nttdata.coderpath.cursos.documents.dtowebclient.Examenes;

import org.springframework.web.reactive.function.server.ServerRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


@Component
@Slf4j
@RequiredArgsConstructor
public class ExamenesHandler {
	
	private final ObjectValidator objectValidator;
	
	@Autowired	
	private ServerResponse service;

	public Mono<ServerResponse> listar1 (ServerRequest request) {

		return ServerResponse
				.ok()
				.contentType(APPLICATION_JSON_UTF8)
				.body(service.findAll(), Examenes.class);
	}

	public Mono<ServerResponse> getOnes(ServerRequest request) {

		String id = request.pathVariable("id");

		return service.findExamenesById(id).flatMap(c -> ServerResponse
				.ok()
				.contentType(APPLICATION_JSON_UTF8)
				.syncBody(c)
				.switchIfEmpty(ServerResponse.notFound()
				.build()));
	}
	
	  public Mono<ServerResponse> save1(ServerRequest request) {
	        Mono<Examenes> dtoMono = request.bodyToMono(Examenes.class)
	        		.doOnNext(objectValidator::validate);
	        return dtoMono.flatMap(productDto -> ServerResponse
	        		.ok()
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.body(service.save1(productDto), Examenes.class));
	    }

	    public Mono<ServerResponse> update1(ServerRequest request) {
	        String id = request.pathVariable("id");
	        Mono<Examenes> dtoMono = request.bodyToMono(Examenes.class)
	        		.doOnNext(objectValidator::validate);
	        return dtoMono.flatMap(c -> ServerResponse
	        		.ok()
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.body(service.update1(c, id), Examenes.class));
	    }

	    public Mono<ServerResponse> delete(ServerRequest request) {
	    	String id = request.pathVariable("id");
	        return ServerResponse
	        		.ok()
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.body(service.delete(id), Examenes.class);
	    }	private ExamenService service;

	public Mono<ServerResponse> listar(ServerRequest request) {

		return ServerResponse
				.ok()
				.contentType(APPLICATION_JSON_UTF8)
				.body(service.findAll(), Examenes.class);
	}

	public Mono<ServerResponse> getOne(ServerRequest request) {

		String id = request.pathVariable("id");

		return service.findExamesById(id).flatMap(c -> ServerResponse
				.ok()
				.contentType(APPLICATION_JSON_UTF8)
				.syncBody(c)
				.switchIfEmpty(ServerResponse.notFound()
				.build()));
	}
	
	  public Mono<ServerResponse> save(ServerRequest request) {
	        Mono<Examenes> dtoMono = request.bodyToMono(Examenes.class)
	        		.doOnNext(objectValidator::validate);
	        return dtoMono.flatMap(productDto -> ServerResponse
	        		.ok()
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.body(service.save(productDto), Examenes.class));
	    }

	    public Mono<ServerResponse> update1(ServerRequest request) {
	        String id = request.pathVariable("id");
	        Mono<Examenes> dtoMono = request.bodyToMono(Examenes.class)
	        		.doOnNext(objectValidator::validate);
	        return dtoMono.flatMap(c -> ServerResponse
	        		.ok()
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.body(service.update(c, id), Examenes.class));
	    }

	    public Mono<ServerResponse> delete1(ServerRequest request) {
	    	String id = request.pathVariable("id");
	        return ServerResponse
	        		.ok()
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.body(service.delete(id), Examenes.class);
	    }
						
	}
	
		
	
	

}
