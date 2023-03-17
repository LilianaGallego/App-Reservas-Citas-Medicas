package co.com.sofka.usecase.agenda.definirdisponibilidad;


import co.com.sofka.model.agenda.AgendaSemanal;
import co.com.sofka.model.agenda.values.*;
import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.usecase.generic.UseCaseForCommand;;
import co.com.sofka.usecase.generic.commands.agenda.DefinirDisponibilidadCommand;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Flushable;


public class DefinirDisponibilidadUseCase extends UseCaseForCommand<DefinirDisponibilidadCommand> {
    private final DomainEventRepository repository;
    private final EventBus bus;

    public DefinirDisponibilidadUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<DefinirDisponibilidadCommand> definirDisponibilidadCommandMono) {
        return definirDisponibilidadCommandMono.flatMapMany(command -> {
            return repository.buscarPorId(command.getAgendaId())
                    .collectList()
                    .flatMapMany(domainEvents -> {
                        return repository.existeDiaId(command.getFecha())
                                .flatMapMany(exist -> {
                                    if (!exist) {

                                        AgendaSemanal agendaSemanal = AgendaSemanal.from(AgendaId.of(command.getAgendaId()), domainEvents);
                                        agendaSemanal.definirDisponibilidad(DiaId.of(command.getFecha()),
                                                new Fecha(command.getFecha()),
                                                new Nombre(command.getNombre()),
                                                command.getHoras());
                                        repository.guardarDisponibilidad(command).subscribe();
                                        return Flux.fromIterable(agendaSemanal.getUncommittedChanges())

                                                .map(event -> {
                                                    bus.publish(event);
                                                    return event;
                                                }).flatMap(event -> {
                                                    return repository.guardarEvento(event);
                                                });

                                    } else {
                                        return Mono.error(new RuntimeException("ya  existe el dia con la fecha: "+ command.getFecha()));
                                    }
                                });
                    });

        });
    }
}