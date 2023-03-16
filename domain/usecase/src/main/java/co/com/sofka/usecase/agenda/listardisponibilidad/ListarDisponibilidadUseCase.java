package co.com.sofka.usecase.agenda.listardisponibilidad;

import co.com.sofka.usecase.agenda.model.DisponibilidadModel;
import co.com.sofka.usecase.generic.commands.agenda.DefinirDisponibilidadCommand;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import reactor.core.publisher.Flux;

public class ListarDisponibilidadUseCase {

    private final DomainEventRepository repository;

    public ListarDisponibilidadUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }


    public Flux<DisponibilidadModel> apply() {
        return repository.listarDisponibilidad();

    }
}
