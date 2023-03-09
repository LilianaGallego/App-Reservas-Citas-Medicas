package co.com.sofka.usecase.crearpaciente;

import co.com.sofka.model.paciente.Paciente;
import co.com.sofka.model.paciente.generic.DomainEvent;
import co.com.sofka.model.paciente.values.*;
import co.com.sofka.usecase.generic.UseCaseForCommand;
import co.com.sofka.usecase.generic.commands.CrearPacienteCommand;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

public class CrearPacienteUseCase extends UseCaseForCommand<CrearPacienteCommand> {
    private final DomainEventRepository repository;
    private final EventBus bus;

    public CrearPacienteUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CrearPacienteCommand> crearPacienteCommandMono) {
        return crearPacienteCommandMono.flatMapIterable(command -> {
                    Paciente paciente = new Paciente(PacienteId.of(command.getPacienteId()),
                            new Nombres(command.getNombres()),
                            new Apellidos(command.getApellidos()),
                            new Celular(command.getCelular()),
                            new Correo(command.getCorreo()));

                    return paciente.getUncommittedChanges();
                }).flatMap(event -> {
                    return repository.saveEvent(event);
                })
                .map(event -> {
                    bus.publish(event);
                    return event;
                });
    }
}