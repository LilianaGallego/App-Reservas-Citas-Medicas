package co.com.sofka.usecase.paciente.listarrevisiones;

import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;


public class ListarRevisionesUseCase {

    private final DomainEventRepository repository;

    public ListarRevisionesUseCase(DomainEventRepository repository) {
        this.repository = repository;
    }


    public Flux<DomainEvent> apply(String id) {
        System.out.println(repository.findById(id));
        return repository.findById(id);

    }
}
