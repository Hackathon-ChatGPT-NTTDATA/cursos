package hackathon.nttdata.coderpath.cursos.service.cursosrouter;


import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import hackathon.nttdata.coderpath.cursos.controller.handler.ExamenesHandler;


@Configuration
public class RouterConfig {
	
	
	@Bean
    public RouterFunction<ServerResponse> rutas(ExamenesHandler handler){
        return  route(GET("/webclient"), handler::listar)
        		.andRoute(GET("/webclient/{id}"), handler::getOne)
        		.andRoute(POST("/webclient/create-examenes"), handler::save)
        		.andRoute(PUT("/webclient/update-examenes/{id}"), handler::update)
        		.andRoute(DELETE("/webclient/delete-examenes/{id}"), handler::delete);
    }

}
