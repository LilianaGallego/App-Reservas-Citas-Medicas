package co.com.sofka.usecase.agenda.definirdisponibilidad;

import co.com.sofka.model.agenda.AgendaSemanal;
import co.com.sofka.model.agenda.events.AgendaCreada;
import co.com.sofka.model.agenda.events.DisponibilidadDefinida;
import co.com.sofka.model.agenda.values.AgendaId;
import co.com.sofka.model.agenda.values.DiaId;
import co.com.sofka.model.agenda.values.Fecha;
import co.com.sofka.model.agenda.values.Nombre;
import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.usecase.agenda.crearagenda.CrearAgendaUseCase;
import co.com.sofka.usecase.generic.commands.agenda.CrearAgendaCommand;
import co.com.sofka.usecase.generic.commands.agenda.DefinirDisponibilidadCommand;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class DefinirDisponibilidadUseCaseTest {
    @Mock
    private DomainEventRepository repository;

    @Mock
    private EventBus bus;

    private DefinirDisponibilidadUseCase useCase;


    @BeforeEach
    void setUp(){
        useCase = new DefinirDisponibilidadUseCase(repository, bus);
    }

    @Test
    void successfulScenario() {
        //Arrange
        final String AGGREGATE_ID = "test-id";


        List<String> horas = Collections.singletonList("\"8:00\", \"9:00\", \"10:00\",\"14:00\",\"15:00\", \"16:00\", \"17:00\",\"18:00\"");
        List<String> dias = new ArrayList<>();
        AgendaCreada event2 = new AgendaCreada(
                "2",
                "23-03-2023",
                "23-03-2023",
                "miercoles",
                horas
        );

        event2.setAggregateRootId(AGGREGATE_ID);

        //AgendaSemanal agendaSemanal = AgendaSemanal.from(AgendaId.of(AGGREGATE_ID), (List<DomainEvent>) event2);

        DiaId diaId = DiaId.of("123");
        Fecha fecha = new Fecha("23-03-2023");
        Nombre nombre = new Nombre("miercoles");
        DefinirDisponibilidadCommand command =
                new DefinirDisponibilidadCommand(
                        AGGREGATE_ID,diaId.value(),fecha.value(),
                        nombre.value(),
                        horas);

        dias.add(0, String.valueOf(command));
        /*agendaSemanal.definirDisponibilidad(diaId,
                fecha,
                nombre,
                horas);*/
        DisponibilidadDefinida event = new DisponibilidadDefinida(
                "23-03-2023",
                "23-03-2023",
                "miercoles",
                horas
        );


        event.setAggregateRootId(AGGREGATE_ID);
        Mockito.when(repository.buscarPorId(command.getAgendaId())).thenReturn(Flux.just());
        Mockito.when(repository.existeDiaId(command.getFecha())).thenReturn(Mono.just(false));

        Mockito.when(repository.guardarEvento(ArgumentMatchers.any(DisponibilidadDefinida.class))).thenAnswer(
                invocationOnMock -> Mono.just(invocationOnMock.getArgument(0))
        );



        Mockito.doAnswer(i -> null).when(bus).publish(ArgumentMatchers.any(DomainEvent.class));

        //Act
        Flux<DomainEvent> result = useCase.apply(Mono.just(command));

        Duration duration = StepVerifier.create(result)
                //Assert
                .expectNextMatches(event1 -> {
                    DisponibilidadDefinida definida = (DisponibilidadDefinida) event1;
                    Assertions.assertEquals(definida.getDiaId(), command.getDiaId());

                    return true;
                })
                .verifyComplete();
    }

}