package co.com.sofka.usecase.agenda.actualizardisponibilidad;

import co.com.sofka.model.agenda.events.DisponibilidadDefinida;
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
        return repository.ActualizarHoraDisponible(fecha, hora);
    }
}
