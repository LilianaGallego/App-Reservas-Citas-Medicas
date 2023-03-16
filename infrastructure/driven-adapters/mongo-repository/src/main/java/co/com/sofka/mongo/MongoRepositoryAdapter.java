package co.com.sofka.mongo;

import co.com.sofka.usecase.agenda.model.DisponibilidadModel;
import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.mongo.data.StoredEvent;
import co.com.sofka.serializer.JSONMapper;
import co.com.sofka.usecase.generic.commands.agenda.DefinirDisponibilidadCommand;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Date;
@Repository
public class MongoRepositoryAdapter implements DomainEventRepository {

    private final ReactiveMongoTemplate template;

    private final JSONMapper eventSerializer;

    public MongoRepositoryAdapter(ReactiveMongoTemplate template, JSONMapper eventSerializer) {
        this.template = template;
        this.eventSerializer = eventSerializer;
    }


    @Override
    public Flux<DomainEvent> buscarPorId(String aggregateId) {
        var query = new Query(Criteria.where("aggregateRootId").is(aggregateId));
        return template.find(query, StoredEvent.class)
                .sort(Comparator.comparing(event -> event.getOccurredOn()))
                .map(storeEvent -> storeEvent.deserializeEvent(eventSerializer));
    }

    @Override
    public Mono<Boolean> existeDiaId(String diaId) {
        var query = new Query(Criteria.where("diaId").is(diaId));
        return template.exists(query, DomainEvent.class, "disponibilidadModel");

    }

    public Mono<Boolean> existePorFecha(String fecha, String hora) {
        var query = new Query(Criteria.where("fecha").is(fecha).and("horas").is(hora));

        return template.exists(query, DomainEvent.class, "disponibilidadModel");

    }

    @Override
    public Mono<Boolean> existePorPacienteId(String aggregateRootId) {
        var query = new Query(Criteria.where("aggregateRootId").is(aggregateRootId));

        return template.exists(query, StoredEvent.class);

    }

    @Override
    public Mono <DisponibilidadModel> ActualizarHoraDisponible(String hora, String fecha){

        var query = new Query(Criteria.where("fecha").is(fecha).and("horas").is(hora));
        Update update = new Update().set("horas.$","NO DISPONIBLE");
        System.out.println(update);
       return  template.findAndModify(
               query,update, FindAndModifyOptions.options().returnNew(true),
               DisponibilidadModel.class,  "disponibilidadModel");

    }

    @Override
    public Flux<DisponibilidadModel> listarDisponibilidad() {
        return template.findAll(DisponibilidadModel.class, "disponibilidadModel");
    }

    @Override
    public Mono<DomainEvent> guardarEvento(DomainEvent event) {
        StoredEvent eventStored = new StoredEvent();
        eventStored.setAggregateRootId(event.aggregateRootId());
        eventStored.setTypeName(event.getClass().getTypeName());
        eventStored.setOccurredOn(new Date());
        eventStored.setEventBody(StoredEvent.wrapEvent(event, eventSerializer));
        return template.save(eventStored)
                .map(storeEvent -> storeEvent.deserializeEvent(eventSerializer));

    }

    @Override
    public Mono<DisponibilidadModel> guardarDisponibilidad(DefinirDisponibilidadCommand command) {
        DisponibilidadModel model = new DisponibilidadModel(command);
        return template.save(model);
    }

    @Override
    public Flux<DomainEvent> buscarPorDiaId(String diaId) {
        var query = new Query(Criteria.where("diaId").is(diaId));

        return template.find(query, DomainEvent.class, "disponibilidadDefinida");

    }


}