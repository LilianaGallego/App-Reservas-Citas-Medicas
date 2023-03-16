package co.com.sofka.usecase.paciente.agendarcita;


import co.com.sofka.model.agenda.AgendaSemanal;
import co.com.sofka.model.agenda.events.DisponibilidadDefinida;
import co.com.sofka.model.paciente.Paciente;
import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.model.paciente.values.*;
import co.com.sofka.usecase.agenda.actualizardisponibilidad.ActualizarDisponibilidadUseCase;
import co.com.sofka.usecase.generic.UseCaseForCommand;
import co.com.sofka.usecase.generic.commands.paciente.cita.AgendarCitaCommand;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AgendarCitaUseCase extends UseCaseForCommand<AgendarCitaCommand> {
    private final DomainEventRepository repository;
    private final EventBus bus;
    private final ActualizarDisponibilidadUseCase useCase;

    public AgendarCitaUseCase(DomainEventRepository repository, EventBus bus, ActualizarDisponibilidadUseCase useCase) {
        this.repository = repository;
        this.bus = bus;
        this.useCase = useCase;
    }

    @Override
    public Flux<DomainEvent> apply(Mono<AgendarCitaCommand> agendarCitaCommandMono) {
        return agendarCitaCommandMono.flatMapMany(command -> {
            return repository.buscarPorId(command.getPacienteId())
                    .collectList()
                    .flatMapMany(events -> {

                        return repository.existePorFecha(command.getFecha(), command.getHora())
                                .flatMapMany(exist -> {


                                    Paciente paciente = Paciente.from(PacienteId.of(command.getPacienteId()), events);

                                    if (exist) {
                                        paciente.agendarCita(CitaId.of(command.getCitaId()),
                                                new Fecha(command.getFecha()),
                                                new Hora(command.getHora()),
                                                new Estado(command.getEstado()));
                                        //useCase.apply(command.getFecha(),command.getHora());
                                        repository.buscarPorId(command.getAgendaId()).subscribe(
                                                event2 -> {
                                                    boolean disponibilidadDefinida = event2.type.equals("liliana.gallego.disponibilidaddefinida");
                                                    if (disponibilidadDefinida) {

                                                        DisponibilidadDefinida definida = (DisponibilidadDefinida) event2;

                                                        if (definida.getFecha().equals(command.getFecha()) && definida.getHoras().contains(command.getHora())) {
                                                            var index = definida.getHoras().indexOf(command.getHora());

                                                            repository.ActualizarHoraDisponible(command.getHora(), command.getFecha()).subscribe();
                                                        }
                                                    }

                                                }
                                        );

                                        return Flux.fromIterable(paciente.getUncommittedChanges())

                                                .map(event -> {
                                                    bus.publish(event);
                                                    return event;
                                                }).flatMap(event -> {
                                                    return repository.guardarEvento(event);
                                                });
                                    } else {
                                        return Mono.error(new RuntimeException("la hora "+ command.getHora()+ " no esta disponible en la fecha:  " + command.getFecha()));
                                    }
                                });


                    });

        });
    }
}