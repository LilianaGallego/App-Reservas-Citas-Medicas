package co.com.sofka.usecase.paciente.cancelarcita;

import co.com.sofka.usecase.agenda.definirdisponibilidad.DefinirDisponibilidadUseCase;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CancelarCitaUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Mock
    private EventBus bus;

    private CancelarCitaUseCase useCase;


    @BeforeEach
    void setUp(){
        useCase = new CancelarCitaUseCase(repository, bus);
    }

}