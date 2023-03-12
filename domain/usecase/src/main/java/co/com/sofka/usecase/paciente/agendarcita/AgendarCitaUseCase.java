package co.com.sofka.usecase.paciente.agendarcita;


import co.com.sofka.model.agenda.AgendaSemanal;
import co.com.sofka.model.paciente.Paciente;
import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.model.paciente.values.*;
import co.com.sofka.usecase.generic.UseCaseForCommand;
import co.com.sofka.usecase.generic.commands.paciente.cita.AgendarCitaCommand;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AgendarCitaUseCase extends UseCaseForCommand<AgendarCitaCommand> {
    private final DomainEventRepository repository;
    private final EventBus bus;

    public AgendarCitaUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AgendarCitaCommand> agendarCitaCommandMono) {
        return agendarCitaCommandMono.flatMapMany(command -> {
            return repository.buscarPorId(command.getPacienteId())
                    .collectList()
                    .flatMapMany(events -> {

                        return repository.existeDiaId(command.getFecha())
                                .flatMapMany(exist -> {
                                    System.out.println(exist);

                                    Paciente paciente = Paciente.from(PacienteId.of(command.getPacienteId()), events);

                                    if (exist) {
                                        paciente.agendarCita(CitaId.of(command.getCitaId()),
                                                new Fecha(command.getFecha()),
                                                new Hora(command.getHora()),
                                                new Estado(command.getEstado()));

                                        return Flux.fromIterable(paciente.getUncommittedChanges())

                                                .map(event -> {
                                                    bus.publish(event);
                                                    return event;
                                                }).flatMap(event -> {
                                                    return repository.guardarEvento(event);
                                                }).flatMap(event -> {

                                                    return repository.guardar((DomainEvent) event);
                                                });
                                    } else {
                                        return Mono.error(new RuntimeException("No existe la hora en la fecha:  " + command.getHora()));
                                    }
                                });


                    });

        });
    }
}