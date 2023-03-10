package co.com.sofka.usecase.generic.gateways;



import co.com.sofka.model.paciente.events.cita.CitaAgendada;
import co.com.sofka.model.paciente.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DomainEventRepository {
    Flux<DomainEvent> findById(String aggregateId);
    Mono<DomainEvent> saveEvent(DomainEvent event);
    Flux<DomainEvent> findByCitaId(String citaId);
   // Mono<DomainEvent> save(DomainEvent event);




}
