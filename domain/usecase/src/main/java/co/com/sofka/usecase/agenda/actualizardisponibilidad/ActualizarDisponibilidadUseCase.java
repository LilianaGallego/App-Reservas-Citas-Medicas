package co.com.sofka.usecase.agenda.actualizardisponibilidad;

import co.com.sofka.model.agenda.AgendaSemanal;
import co.com.sofka.model.agenda.Dia;
import co.com.sofka.model.agenda.events.DisponibilidadDefinida;
import co.com.sofka.model.agenda.values.AgendaId;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Mono;

public class ActualizarDisponibilidadUseCase  {
    private final DomainEventRepository repository;
    private final EventBus bus;

    public ActualizarDisponibilidadUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }


    public Mono<DisponibilidadDefinida> apply(String fecha, String hora) {
        return repository.buscarPorDiaId(fecha).collectList()
                .flatMapMany(
                        domainEvents -> {
                            repository.ActualizarHoraDisponible(fecha,hora)
                                    .flatMapMany(
                                            domainEvents2->{
                                                AgendaSemanal agendaSemanal = AgendaSemanal.from(AgendaId.of(domainEvents2.aggregateRootId()),domainEvents);
                                                agendaSemanal.actualizarDisponibilidad(domainEvents2.getFecha(), domainEvents2.getHoras().toString());
                                                return agendaSemanal;

                                                ////revisar si se debe crear el comando OJO
                                            }
                                    );
                        return null;}
                );


    }
}
