package co.com.sofka.usecase.generic.gateways;


import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.usecase.generic.commands.agenda.DefinirDisponibilidadCommand;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DomainEventRepository {
    Flux<DomainEvent> buscarPorId(String aggregateId);
    Mono<DomainEvent> guardarEvento(DomainEvent event);
    Flux<DomainEvent> buscarPorDiaId(String diaId);
    Mono<Boolean> existeDiaId(String diaId);
    Mono<Boolean> existePorPacienteId(String aggregateRootId);
    Mono<DefinirDisponibilidadCommand> guardarDisponibilidad(DefinirDisponibilidadCommand command);
    Mono<Boolean> existePorFecha(String fecha, String hora);
    Mono <DefinirDisponibilidadCommand> ActualizarHoraDisponible(String hora, String fecha);






}
