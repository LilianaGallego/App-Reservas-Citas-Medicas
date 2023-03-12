package co.com.sofka.usecase.agenda.crearagenda;

import co.com.sofka.model.agenda.AgendaSemanal;
import co.com.sofka.model.agenda.values.*;
import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.usecase.generic.UseCaseForCommand;
import co.com.sofka.usecase.generic.commands.agenda.CrearAgendaCommand;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class CrearAgendaUseCase extends UseCaseForCommand<CrearAgendaCommand> {
    private final DomainEventRepository repository;
    private final EventBus bus;

    public CrearAgendaUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CrearAgendaCommand> crearAgendaCommandMono) {
        return crearAgendaCommandMono.flatMapIterable(command -> {

                    AgendaSemanal agenda = new AgendaSemanal(AgendaId.of(command.getAgendaId()),
                            new Semana(command.getSemana()),
                            DiaId.of(command.getDiaId()),
                            new Fecha(command.getFecha()),
                            new Nombre(command.getNombre()),
                            new ArrayList<Hora>()) ;
                    return agenda.getUncommittedChanges();
                })
                .map(event -> {
                    bus.publish(event);
                    return event;
                }).flatMap(event -> {

                    return repository.saveEvent(event);
                }).flatMap(event -> {

                    return repository.save((DomainEvent) event);
                });

    }
}