package co.com.sofka.api;

import co.com.sofka.model.paciente.generic.DomainEvent;
import co.com.sofka.usecase.crearpaciente.CrearPacienteUseCase;
import co.com.sofka.usecase.generic.commands.CrearPacienteCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RestController {
    @Bean
    public RouterFunction<ServerResponse> crearPaciente(CrearPacienteUseCase useCase){

        return route(
                POST("/crear/paciente").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase
                                .apply(request.bodyToMono(CrearPacienteCommand.class)),
                                DomainEvent.class))
        );
    }

   /* @Bean
    public RouterFunction<ServerResponse> addComment(AddCommentUseCase useCase){

        return route(
                POST("/add/comment").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase
                                .apply(request.bodyToMono(AddCommentCommand.class)),
                                DomainEvent.class))
        );
    }*/

}
