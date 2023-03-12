package co.com.sofka.usecase.agenda.actualizardisponibilidad;

import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.usecase.generic.UseCaseForCommand;
import co.com.sofka.usecase.generic.commands.agenda.ActualizarDisponibilidadHoraCommand;
import co.com.sofka.usecase.generic.commands.agenda.DefinirDisponibilidadCommand;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ActualizarDisponibilidadUseCase extends UseCaseForCommand<ActualizarDisponibilidadHoraCommand> {
    private final DomainEventRepository repository;
    private final EventBus bus;

    public ActualizarDisponibilidadUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<ActualizarDisponibilidadHoraCommand> actualizarDisponibilidadHoraCommandMono) {
        return null;
    }
}
