package co.com.sofka.usecase.paciente.actualizarcorreopaciente;


import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.model.paciente.Paciente;
import co.com.sofka.model.paciente.values.*;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import co.com.sofka.usecase.paciente.model.PacienteModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class ActualizarCorreoPacienteUseCase {
    private final DomainEventRepository repository;

    private final EventBus bus;

    public ActualizarCorreoPacienteUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    public Mono<DomainEvent> apply(String idPaciente, String correo) {
        return (Mono<DomainEvent>) repository.existePorPacienteId(idPaciente).subscribe(
                aBoolean -> {
                    if (aBoolean){
                        repository.actualizarCorreo(idPaciente,correo).subscribe();
                    }
                }

        );
    }
}
