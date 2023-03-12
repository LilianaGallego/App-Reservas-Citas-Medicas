package co.com.sofka.usecase.agenda.buscarhoradisponible;


import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.usecase.generic.UseCaseForCommand;
import co.com.sofka.usecase.generic.commands.agenda.BuscarHoraDisponibleCommand;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BuscarHoraDisponibleUseCase extends UseCaseForCommand<BuscarHoraDisponibleCommand> {
    private final DomainEventRepository repository;
    private final EventBus bus;

    public BuscarHoraDisponibleUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<BuscarHoraDisponibleCommand> buscarHoraDisponibleCommandMono) {
        return buscarHoraDisponibleCommandMono.flatMapIterable(command->{
                    return (Iterable<? extends DomainEvent>) repository.
                            existById(command.getDiaId());
        })
                .map(event -> {
                bus.publish(event);
                return event;
        });
    }
}
