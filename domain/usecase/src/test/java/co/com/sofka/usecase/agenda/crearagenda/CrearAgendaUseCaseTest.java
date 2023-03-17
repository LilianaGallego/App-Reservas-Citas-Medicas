package co.com.sofka.usecase.agenda.crearagenda;

import co.com.sofka.model.agenda.events.AgendaCreada;
import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.usecase.generic.commands.agenda.CrearAgendaCommand;
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

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class CrearAgendaUseCaseTest {
    @Mock
    private DomainEventRepository repository;

    @Mock
    private EventBus bus;

    private CrearAgendaUseCase useCase;


    @BeforeEach
    void setUp(){
        useCase = new CrearAgendaUseCase(repository, bus);
    }

    @Test
    void successfulScenario() {
        //Arrange
        final String AGGREGATE_ID = "test-id";

        String horas = "\"8:00\", \"9:00\", \"10:00\",\"14:00\",\"15:00\", \"16:00\", \"17:00\",\"18:00\"";
        CrearAgendaCommand crearAgendaCommand = new CrearAgendaCommand(AGGREGATE_ID,"2");
       AgendaCreada event = new AgendaCreada(
                "2",
                "23-03-2023",
                "23-03-2023",
                "miercoles",
                Collections.singletonList(horas)
        );
        event.setAggregateRootId(AGGREGATE_ID);

        Mockito.when(repository.guardarEvento(ArgumentMatchers.any(AgendaCreada.class))).thenAnswer(
                invocationOnMock -> Mono.just(invocationOnMock.getArgument(0))
        );

        Mockito.doAnswer(i -> null).when(bus).publish(ArgumentMatchers.any(DomainEvent.class));

        //Act
        Flux<DomainEvent> result = useCase.apply(Mono.just(crearAgendaCommand));

        StepVerifier.create(result)
                //Assert
                .expectNextMatches(event1 ->{
                    AgendaCreada agendaCreada = (AgendaCreada) event1;
                    Assertions.assertEquals(agendaCreada.getSemana(), event.getSemana());
                    return event1.aggregateRootId().equals(event.aggregateRootId()); })
                .verifyComplete();
    }
}