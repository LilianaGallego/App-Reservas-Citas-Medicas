package co.com.sofka.model.agenda;

import co.com.sofka.model.agenda.events.AgendaCreada;
import co.com.sofka.model.agenda.events.DisponibilidadDefinida;
import co.com.sofka.model.agenda.values.*;
import co.com.sofka.model.generic.AggregateRoot;
import co.com.sofka.model.generic.DomainEvent;

import java.util.List;
import java.util.Objects;

public class AgendaSemanal extends AggregateRoot<AgendaId> {
    protected Semana semana;
    protected List<Dia> dias;


    public AgendaSemanal(AgendaId id,
                  Semana semana
    ) {
        super(id);
        this.semana = semana;
        subscribe(new AgendaChange(this));
        appendChange(new AgendaCreada(
                semana.value()
        )).apply();
    }

    private AgendaSemanal(AgendaId agendaId) {
        super(agendaId);
        subscribe(new AgendaChange(this));
    }

    public static AgendaSemanal from(AgendaId agendaId, List<DomainEvent> events) {
        AgendaSemanal agenda = new AgendaSemanal(agendaId);
        events.forEach(event -> agenda.applyEvent(event));
        return agenda;
    }


    public void definirDisponibilidad(DiaId diaId, Fecha fecha, Nombre nombre, Hora hora, Disponible disponible) {
        Objects.requireNonNull(diaId);
        Objects.requireNonNull(fecha);
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(hora);
        Objects.requireNonNull(disponible);

        appendChange(new DisponibilidadDefinida(diaId.value(), fecha.value(), nombre.value(), hora.value(), disponible.value())).apply();
    }
}