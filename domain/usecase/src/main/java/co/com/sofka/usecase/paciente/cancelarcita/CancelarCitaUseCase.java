package co.com.sofka.usecase.paciente.cancelarcita;

import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Mono;

public class CancelarCitaUseCase {


    private final DomainEventRepository repository;

    private final EventBus bus;

    public CancelarCitaUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }


    public Mono<Void> apply(String idCita) {
        return repository.buscarCitaporId(idCita)
                .map(
                        event -> {

                            repository.eliminarCita(idCita).subscribe();

                            repository.CambiarHoraADisponible(event.getHora(), event.getFecha()).subscribe();

                            return Mono.empty();
                        }).then();
    }


}
