package co.com.sofka.usecase.paciente.crearrevison;

import co.com.sofka.model.paciente.Paciente;
import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.model.paciente.values.*;
import co.com.sofka.usecase.generic.UseCaseForCommand;
import co.com.sofka.usecase.generic.commands.paciente.Revision.CrearRevisionCommand;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class CrearRevisonUseCase extends UseCaseForCommand<CrearRevisionCommand> {
    private final DomainEventRepository repository;
    private final EventBus bus;

    public CrearRevisonUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CrearRevisionCommand> crearRevisionCommandMono) {

        return crearRevisionCommandMono.flatMapMany(command -> repository.findById(command.getPacienteId())
                .collectList()
                .flatMapIterable(events -> {
                    Paciente paciente = Paciente.from(PacienteId.of(command.getPacienteId()), events);

                    paciente.crearRevision(RevisionId.of(command.getRevisionId()),

                            new Fecha(command.getFecha()),
                            new Observacion(command.getObservacion()));

                    return paciente.getUncommittedChanges();
                }).map(event -> {
                    bus.publish(event);
                    return event;
                }).flatMap(event -> {
                    return repository.saveEvent(event);
                })
        );

    }









}