package co.com.sofka.usecase.buscarcita;
import co.com.sofka.model.paciente.Paciente;
import co.com.sofka.model.paciente.entities.Cita;
import co.com.sofka.model.paciente.generic.DomainEvent;
import co.com.sofka.model.paciente.values.*;
import co.com.sofka.usecase.generic.UseCaseForCommand;
import co.com.sofka.usecase.generic.commands.cita.BuscarCitaCommand;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BuscarCitaUseCase extends UseCaseForCommand<BuscarCitaCommand> {

    private final DomainEventRepository repository;
    private final EventBus bus;

    public BuscarCitaUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }


    @Override
    public Flux<DomainEvent> apply(Mono<BuscarCitaCommand> buscarCitaCommandMono) {
        return buscarCitaCommandMono.flatMapIterable((buscarCitaCommand -> repository.findByCitaId(buscarCitaCommand.getCitaId()).toIterable()

        ));
    }
}
