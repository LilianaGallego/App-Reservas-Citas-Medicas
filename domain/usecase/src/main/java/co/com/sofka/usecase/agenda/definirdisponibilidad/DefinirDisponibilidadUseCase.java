package co.com.sofka.usecase.agenda.definirdisponibilidad;


import co.com.sofka.model.agenda.AgendaSemanal;
import co.com.sofka.model.agenda.values.*;
import co.com.sofka.model.agenda.values.Hora;
import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.usecase.generic.UseCaseForCommand;;
import co.com.sofka.usecase.generic.commands.agenda.DefinirDisponibilidadCommand;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;

public class DefinirDisponibilidadUseCase extends UseCaseForCommand<DefinirDisponibilidadCommand> {
    private final DomainEventRepository repository;
    private final EventBus bus;

    public DefinirDisponibilidadUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<DefinirDisponibilidadCommand> definirDisponibilidadCommandMono) {
        return definirDisponibilidadCommandMono.flatMapMany(command -> repository.findById(command.getAgendaId())
                .collectList()
                .flatMapIterable(events -> {
                    System.out.println("Entra en el caso de uso");
                    AgendaSemanal agenda = AgendaSemanal.from(AgendaId.of(command.getAgendaId()), events);

                    agenda.definirDisponibilidad(DiaId.of(command.getDiaId()),
                            new Fecha(command.getFecha()),
                            new Nombre(command.getNombre()),
                           new Hora(command.getHora()),
                            new Disponible((command.getDisponible())));


                    return agenda.getUncommittedChanges();
                }).map(event -> {
                    bus.publish(event);
                    return event;
                }).flatMap(event -> {
                    return repository.saveEvent(event);
                })
        );

    }
}