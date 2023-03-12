package co.com.sofka.usecase.generic.gateways;


import co.com.sofka.model.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DomainEventRepository {
    Flux<DomainEvent> findById(String aggregateId);
    Mono<DomainEvent> saveEvent(DomainEvent event);
    Flux<DomainEvent> findByCitaId(String citaId);
    Mono<Boolean> existById(String diaId);
    Mono<Boolean> existByIdPaciente(String aggregateRootId);
    Mono<DomainEvent> save(DomainEvent event);




}
