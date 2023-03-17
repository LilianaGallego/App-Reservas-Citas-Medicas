package co.com.sofka.api;

import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.usecase.agenda.crearagenda.CrearAgendaUseCase;
import co.com.sofka.usecase.agenda.definirdisponibilidad.DefinirDisponibilidadUseCase;
import co.com.sofka.usecase.agenda.listardisponibilidad.ListarDisponibilidadUseCase;
import co.com.sofka.usecase.agenda.model.DisponibilidadModel;
import co.com.sofka.usecase.generic.commands.agenda.CrearAgendaCommand;
import co.com.sofka.usecase.generic.commands.agenda.DefinirDisponibilidadCommand;
import co.com.sofka.usecase.paciente.agendarcita.AgendarCitaUseCase;
import co.com.sofka.usecase.paciente.cancelarcita.CancelarCitaUseCase;
import co.com.sofka.usecase.paciente.crearpaciente.CrearPacienteUseCase;
import co.com.sofka.usecase.paciente.crearrevison.CrearRevisonUseCase;
import co.com.sofka.usecase.generic.commands.paciente.cita.AgendarCitaCommand;
import co.com.sofka.usecase.generic.commands.paciente.paciente.CrearPacienteCommand;
import co.com.sofka.usecase.generic.commands.paciente.Revision.CrearRevisionCommand;
import co.com.sofka.usecase.paciente.listarrevisiones.ListarRevisionesUseCase;
import co.com.sofka.usecase.paciente.model.CitaModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
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

   @Bean
    public RouterFunction<ServerResponse> agendarCita(AgendarCitaUseCase useCase){

        return route(
                POST("/agendar/cita").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase
                                .apply(request.bodyToMono(AgendarCitaCommand.class)),
                                DomainEvent.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> crearRevison(CrearRevisonUseCase useCase){

        return route(
                POST("/crear/revision").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase
                                        .apply(request.bodyToMono(CrearRevisionCommand.class)),
                                DomainEvent.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> crearAgenda(CrearAgendaUseCase useCase){

        return route(
                POST("/crear/agenda").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase
                                        .apply(request.bodyToMono(CrearAgendaCommand.class)),
                                DomainEvent.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> definirDisponibilidad(DefinirDisponibilidadUseCase useCase){

        return route(
                POST("/definir/disponibilidad").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase
                                        .apply(request.bodyToMono(DefinirDisponibilidadCommand.class)),
                                DomainEvent.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> buscarHistorial(ListarRevisionesUseCase useCase){

        return route(
                GET("/buscarHistorial/{pacienteId}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("pacienteId")),
                                DomainEvent.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> listarDisponibilidad(ListarDisponibilidadUseCase useCase){

        return route(
                GET("/listarDisponibilidad").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(),
                                DisponibilidadModel.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> cancelarCita(CancelarCitaUseCase useCase){

        return route(
                DELETE("/cancelarCita/{citaId}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("citaId")),
                                Void.class))
        );
    }

    /*@Bean
    public RouterFunction<ServerResponse> buscarCita(BuscarCitaUseCase useCase){

        return route(
                GET("/buscarCita/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(
                                        request.bodyToMono(BuscarCitaCommand.class)),
                                DomainEvent.class))
        );
    }*/

}
