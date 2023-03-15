USBCpz΍      
* 7V@         }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              �package co.com.sofka.usecase.paciente.agendarcita;


import co.com.sofka.model.agenda.AgendaSemanal;
import co.com.sofka.model.agenda.events.DisponibilidadDefinida;
import co.com.sofka.model.agenda.values.AgendaId;
import co.com.sofka.model.paciente.Paciente;
import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.model.paciente.values.*;
import co.com.sofka.usecase.agenda.actualizardisponibilidad.ActualizarDisponibilidadUseCase;
import co.com.sofka.usecase.generic.UseCaseForCommand;
import co.com.sofka.usecase.generic.commands.paciente.cita.AgendarCitaCommand;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;;

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
                        String diaId = command.getFecha();
                        String hora = command.getHora();



                        return repository.existePorFecha(diaId, hora)
                                .flatMapMany(
                                        aBoolean -> {
                                            System.out.println(aBoolean);
                                            if (aBoolean) {
                                                //useCase.apply(diaId, hora, command.getAgendaId());
                                                repository.buscarPorId(command.getAgendaId())
                                                        .collectList()
                                                        .subscribe(
                                                                events1 -> {
                                                                    for (int i = 1; i < events.size(); i++) {
                                                                        boolean disponibilidadDefinida = events.get(i).type.equals("liliana.gallego.disponibilidaddefinida");
                                                                        if (disponibilidadDefinida) {
                                                                            DisponibilidadDefinida definida = (DisponibilidadDefinida) events.get(i);
                                                                            if (definida.getFecha().equals(command.getFecha())) {
                                                                                var index = definida.getHoras().indexOf(hora);
                                                                                System.out.println(index);
                                                                                String nuevaHora = definida.getHoras().set(index, "NO");
                                                                                String nuevaFecha = definida.getFecha();

                                                                                System.out.println(definida.getHoras());
                                                                                AgendaSemanal agendaSemanal = AgendaSemanal.from(AgendaId.of(command.getAgendaId()), events);
                                                                                agendaSemanal.actualizarDisponibilidad(nuevaFecha, definida.getHoras());
                                                                                return Flux.fromIterable(agendaSemanal.getUncommittedChanges())
                                                                                        .map(event -> {
                                                                                            bus.publish(event);
                                                                                            return event;
                                                                                        }).flatMap(event -> {
                                                                                            return repository.guardarEvento(event);
                                                                                        });
                                                                                //nuevaLista.addAll(agendaSemanal.getUncommittedChanges());
                                                                               / repository.guardarEvento(agendaSemanal.getUncommittedChanges())
                                                                                        .subscribe();

                                                                            }
                                                                        }

                                                                    }
                                                                }
                                                        );
                                                String nuevahora = command.getHora().toString() + "No disponible";
                                                Paciente paciente = Paciente.from(PacienteId.of(command.getPacienteId()), events);
                                                paciente.agendarCita(CitaId.of(command.getCitaId()),
                                                        new Fecha(command.getFecha()),
                                                        new Hora(command.getHora()),
                                                        new Estado(command.getEstado()));
                                                return Flux.fromIterable(paciente.getUncommittedChanges())

                                                        .map(event -> {
                                                            bus.publish(event);
                                                            return event;
                                                        }).flatMa