package co.com.sofka.usecase.agenda.actualizardisponibilidad;

import co.com.sofka.model.agenda.AgendaSemanal;
import co.com.sofka.model.agenda.events.DisponibilidadDefinida;
import co.com.sofka.model.agenda.values.AgendaId;
import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.usecase.generic.gateways.DomainEventRepository;
import co.com.sofka.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class ActualizarDisponibilidadUseCase {
    private final DomainEventRepository repository;
    private final EventBus bus;

    public ActualizarDisponibilidadUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }


    /*public Flux<Object> apply(String fecha, String hora, String agendaId) {
        return   repository.buscarPorId(agendaId).collectList()
                .flatMapIterable(
                        events -> {
                            //events.stream().collect(Collectors.toList());
                            //modificacion(events,fecha,hora,agendaId);
                            ///events.contains()
                            List<DomainEvent> nuevaLista = new ArrayList<>();
                            for (int i = 1; i < events.size(); i++) {
                                boolean disponibilidadDefinida = events.get(i).type.equals("liliana.gallego.disponibilidaddefinida");
                                if (disponibilidadDefinida) {
                                    DisponibilidadDefinida definida = (DisponibilidadDefinida) events.get(i);
                                    if (definida.getFecha().equals(fecha)) {
                                        var index = definida.getHoras().indexOf(hora);
                                        System.out.println(index);
                                        String nuevaHora = definida.getHoras().set(index, "NO");
                                        String nuevaFecha = definida.getFecha();

                                        System.out.println(definida.getHoras());
                                        AgendaSemanal agendaSemanal = AgendaSemanal.from(AgendaId.of(agendaId), events);
                                        agendaSemanal.actualizarDisponibilidad(nuevaFecha, definida.getHoras());
                                        nuevaLista.addAll(agendaSemanal.getUncommittedChanges());


                                    } else {
                                        return Flux.error(new IllegalAccessException("No existe ...."));

                                    }

                                } else {
                                    return Flux.error(new IllegalAccessException("No exite ...."));

                                }

                            }
                            return Flux.fromIterable(nuevaLista).flatMap(
                                    event -> repository.guardarEvento(event).doOnNext(bus::publish)
                            );//Flux.error(new Throwable("No exite ...."));
                        }
       // return Flux.fromIterable(agendaSemanal.getUncommittedChanges())
                );

                /*.flatMap(event -> {
                    return repository.guardarEvento((DomainEvent) event);
                })
                .map(event -> {
                    bus.publish(event);
                    return event;
                });



    }*/


    /*public Flux<DomainEvent> modificacion(List<DomainEvent> events, String fecha, String hora, String agendaId){
        for (int i = 1; i < events.size(); i++) {
            boolean disponibilidadDefinida = events.get(i).type.equals("liliana.gallego.disponibilidaddefinida");
            if (disponibilidadDefinida) {
                DisponibilidadDefinida definida = (DisponibilidadDefinida) events.get(i);
                if (definida.getFecha().equals(fecha)) {
                    var index = definida.getHoras().indexOf(hora);
                    System.out.println(index);
                    String nuevaHora = definida.getHoras().set(index, "NO");
                    String nuevaFecha = definida.getFecha();

                    System.out.println(definida.getHoras());
                    AgendaSemanal agendaSemanal = AgendaSemanal.from(AgendaId.of(agendaId), events);
                    agendaSemanal.actualizarDisponibilidad(nuevaFecha, definida.getHoras());
                    return Flux.fromIterable(agendaSemanal.getUncommittedChanges())
                            .flatMap(event -> {
                                return repository.guardarEvento((DomainEvent) event);
                            })
                            .map(event -> {
                                bus.publish(event);
                                return event;
                            });



                } else {
                    return Mono.error(new Throwable("No exite ...."));

                }

            } else {
                return Mono.error(new Throwable("No exite ...."));

            }
        }
    }*/
}
