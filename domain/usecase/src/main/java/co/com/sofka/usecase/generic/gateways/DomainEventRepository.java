package co.com.sofka.usecase.generic.gateways;


import co.com.sofka.model.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DomainEventRepository {
    Flux<DomainEvent> buscarPorId(String aggregateId);

    Mono<DomainEvent> guardarEvento(DomainEvent event);
    Flux<DomainEvent> buscarPorDiaId(String diaId);
    Mono<Boolean> existeDiaId(String diaId);
    Mono<Boolean> existePorPacienteId(String pacienteId);
    Mono<DomainEvent> guardar(DomainEvent event);
    Mono<Boolean> existePorFecha(String fecha, String hora);
    Mono<Boolean> ActualizarHoraDisponible(String fecha, String hora);






}
