package co.com.sofka.usecase.paciente.crearpaciente;

import co.com.sofka.model.paciente.Paciente;
import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.model.paciente.values.*;
import co.com.sofka.usecase.generic.UseCaseForCommand;
import co.com.sofka.usecase.generic.commands.paciente.paciente.CrearPacienteCommand;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CrearPacienteUseCase extends UseCaseForCommand<CrearPacienteCommand> {
    private final DomainEventRepository repository;
    private final EventBus bus;

    public CrearPacienteUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<CrearPacienteCommand> crearPacienteCommandMono) {
        return crearPacienteCommandMono.flatMapMany(command ->

                repository.existByIdPaciente(command.getPacienteId())


                        .flatMapMany(aBoolean -> {
                            System.out.println(aBoolean);
                            if (!aBoolean) {
                                Paciente paciente = new Paciente(PacienteId.of(command.getPacienteId()),
                                        new Nombres(command.getNombres()),
                                        new Apellidos(command.getApellidos()),
                                        new Celular(command.getCelular()),
                                        new Correo(command.getCorreo()));

                                return Flux.fromIterable(paciente.getUncommittedChanges())
                                        .flatMap(event -> {
                                            return repository.saveEvent((DomainEvent) event);
                                        }).flatMap(event -> {

                                            return repository.save((DomainEvent) event);
                                        })
                                        .map(event -> {
                                            bus.publish(event);
                                            return event;
                                        });
                            } else {
                                return Mono.error(new RuntimeException("El Paciente ya existe"));
                            }
                        })
        );
    }
}