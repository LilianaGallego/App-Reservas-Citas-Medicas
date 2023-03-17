package co.com.sofka.usecase.paciente.crearpaciente;

import co.com.sofka.model.agenda.events.AgendaCreada;
import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.model.paciente.entities.Cita;
import co.com.sofka.model.paciente.events.paciente.PacienteCreado;
import co.com.sofka.usecase.agenda.crearagenda.CrearAgendaUseCase;
import co.com.sofka.usecase.generic.commands.agenda.CrearAgendaCommand;
import co.com.sofka.usecase.generic.commands.paciente.paciente.CrearPacienteCommand;
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
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CrearPacienteUseCaseTest {
    @Mock
    private DomainEventRepository repository;

    @Mock
    private EventBus bus;

    private CrearPacienteUseCase useCase;


    @BeforeEach
    void setUp(){
        useCase = new CrearPacienteUseCase(repository, bus);
    }

    @Test
    void successfulScenario() {
        //Arrange
        final String AGGREGATE_ID = "test-id";

        List<String> horas = Collections.singletonList("\"8:00\", \"9:00\", \"10:00\",\"14:00\",\"15:00\", \"16:00\", \"17:00\",\"18:00\"");
        CrearPacienteCommand command =
                new CrearPacienteCommand(
                        AGGREGATE_ID,
                        "Liliana",
                        "Gallego",
                        "3388384",
                        "lilianagallegom@gmail.com",horas);

        PacienteCreado event = new PacienteCreado(
                "Liliana",
                "Gallego",
                "3388384",
                "lilianagallegom@gmail.com"
        );
        event.setAggregateRootId(AGGREGATE_ID);
        Mockito.when(repository.existePorPacienteId(command.getPacienteId())).thenReturn(Mono.just(false));

        Mockito.when(repository.guardarEvento(ArgumentMatchers.any(PacienteCreado.class))).thenAnswer(
                invocationOnMock -> Mono.just(invocationOnMock.getArgument(0))
        );

        Mockito.doAnswer(i -> null).when(bus).publish(ArgumentMatchers.any(DomainEvent.class));

        //Act
        Flux<DomainEvent> result = useCase.apply(Mono.just(command));

        StepVerifier.create(result)
                //Assert
                .expectNextMatches(event1 ->{
                    PacienteCreado pacienteCreado = (PacienteCreado) event1;
                    Assertions.assertEquals(pacienteCreado.getNombres(), event.getNombres());
                    return event1.aggregateRootId().equals(event.aggregateRootId()); })
                .verifyComplete();
    }

}