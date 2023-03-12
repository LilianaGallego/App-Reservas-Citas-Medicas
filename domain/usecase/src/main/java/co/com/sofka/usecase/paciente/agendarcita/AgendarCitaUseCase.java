package co.com.sofka.usecase.paciente.agendarcita;


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
            return repository.findById(command.getPacienteId())
                    .collectList()
                    .flatMapMany(events -> {

                        return repository.existByFecha(command.getFecha())
                                .flatMapMany(exist -> {
                                    Paciente paciente = Paciente.from(PacienteId.of(command.getPacienteId()), events);

                                    if (exist) {
                                        return repository.existByHora(command.getHora())
                                                .flatMapMany(
                                                        aBoolean -> {
                                                            System.out.println(aBoolean);
                                                            if (aBoolean) {
                                                                paciente.agendarCita(CitaId.of(command.getCitaId()),
                                                                        new Fecha(command.getFecha()),
                                                                        new Hora(command.getHora()),
                                                                        new Estado(command.getEstado()));

                                                                return Flux.fromIterable(paciente.getUncommittedChanges())

                                                                        .map(event -> {
                                                                            bus.publish(event);
                                                                            return event;
                                                                        }).flatMap(event -> {
                                                                            return repository.saveEvent(event);
                                                                        }).flatMap(event -> {

                                                                            return repository.save((DomainEvent) event);
                                                                        });
                                                            } else {
                                                                return Mono.error(new RuntimeException("No existe la hora en la fecha:  " + command.getHora()));
                                                            }
                                                        });

                                    } else {
                                        return Mono.error(new RuntimeException("No existe el dia"));
                                    }


                                });
                    });

        });
    }
}