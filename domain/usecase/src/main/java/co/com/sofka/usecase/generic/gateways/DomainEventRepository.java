package co.com.sofka.usecase.generic.gateways;


import co.com.sofka.model.agenda.events.DisponibilidadDefinida;
import co.com.sofka.model.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.management.Query;

public interface DomainEventRepository {
    Flux<DomainEvent> buscarPorId(String aggregateId);

    Mono<DisponibilidadDefinida> ActualizarHoraDisponible(Query query);

    Mono<DomainEvent> guardarEvento(DomainEvent event);
    Flux<DomainEvent> buscarPorDiaId(String diaId);
    Mono<Boolean> existeDiaId(String diaId);
    Mono<Boolean> existePorPacienteId(String aggregateRootId);
    Mono<DomainEvent> guardar(DomainEvent event);
    Mono<Boolean> existePorFecha(String fecha, String hora);
    Mono<DisponibilidadDefinida> ActualizarHoraDisponible(String fecha, String hora);






}
