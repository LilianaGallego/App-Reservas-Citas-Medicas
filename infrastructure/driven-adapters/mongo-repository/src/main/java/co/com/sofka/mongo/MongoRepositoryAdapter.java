package co.com.sofka.mongo;

import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.mongo.data.StoredEvent;
import co.com.sofka.serializer.JSONMapper;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    public Flux<DomainEvent> findById(String aggregateId) {
        var query = new Query(Criteria.where("aggregateRootId").is(aggregateId));
        return template.find(query, StoredEvent.class)
                .sort(Comparator.comparing(event -> event.getOccurredOn()))
                .map(storeEvent -> storeEvent.deserializeEvent(eventSerializer));
    }

    @Override
    public Mono<Boolean> existById(String diaId) {
        var query = new Query(Criteria.where("diaId").is(diaId));
        return template.exists(query, DomainEvent.class, "disponibilidadDefinida");

    }

    @Override
    public Mono<Boolean> existByFecha(String fecha) {
        var query = new Query(Criteria.where("fecha").is(fecha));
        System.out.println(query);
        return template.exists(query, StoredEvent.class, "disponibilidadDefinida");

    }

    @Override
    public Mono<Boolean> existByHora(String hora) {

        var query = new Query(Criteria.where("horas").regex(hora));
        return template.exists(query, DomainEvent.class, "disponibilidadDefinida");


    }

    @Override
    public Mono<Boolean> existByIdPaciente(String aggregateRootId) {
        var query = new Query(Criteria.where("aggregateRootId").is(aggregateRootId));
        return template.exists(query, StoredEvent.class);

    }

    @Override
    public Mono<DomainEvent> saveEvent(DomainEvent event) {
        StoredEvent eventStored = new StoredEvent();
        eventStored.setAggregateRootId(event.aggregateRootId());
        eventStored.setTypeName(event.getClass().getTypeName());
        eventStored.setOccurredOn(new Date());
        eventStored.setEventBody(StoredEvent.wrapEvent(event, eventSerializer));
        return template.save(eventStored)
                .map(storeEvent -> storeEvent.deserializeEvent(eventSerializer));

    }

    @Override
    public Mono<DomainEvent> save(DomainEvent event) {
        return template.save(event);
    }

    @Override
    public Flux<DomainEvent> findByDiaId(String diaId) {
        var query = new Query(Criteria.where("eventBody.diaId").is(diaId));
        System.out.println("mongorepo"+template.find(query, StoredEvent.class)
                .sort(Comparator.comparing(event -> event.getOccurredOn()))
                .map(storeEvent -> storeEvent.deserializeEvent(eventSerializer)));
        return template.find(query, StoredEvent.class)
                .sort(Comparator.comparing(event -> event.getOccurredOn()))
                .map(storeEvent -> storeEvent.deserializeEvent(eventSerializer));
    }


}