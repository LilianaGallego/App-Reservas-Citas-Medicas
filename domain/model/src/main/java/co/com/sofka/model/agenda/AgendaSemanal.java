package co.com.sofka.model.agenda;

import co.com.sofka.model.agenda.events.AgendaCreada;
import co.com.sofka.model.agenda.events.DisponibilidadActualizada;
import co.com.sofka.model.agenda.events.DisponibilidadDefinida;
import co.com.sofka.model.agenda.values.*;
import co.com.sofka.model.generic.AggregateRoot;
import co.com.sofka.model.generic.DomainEvent;
import co.com.sofka.model.paciente.entities.Cita;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AgendaSemanal extends AggregateRoot<AgendaId> {
    protected Semana semana;
    protected List<Dia> dias;
    protected List<Cita> citas;


    public AgendaSemanal(AgendaId id,
                  Semana semana, DiaId diaId, Fecha fecha, Nombre nombre,  List<Hora> horas
    ) {
        super(id);
        //this.semana = semana;
        subscribe(new AgendaChange(this));
        List<String> horasDia = new ArrayList<>();
        horas.stream().map(x-> x).forEach(hora ->horasDia.add(hora.toString()) );
        appendChange(new AgendaCreada(
                semana.value(), diaId.value(),fecha.value(),nombre.value(),horasDia)).apply();
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


    public void definirDisponibilidad(DiaId diaId, Fecha fecha, Nombre nombre, List<String> horas) {
        Objects.requireNonNull(diaId);
        Objects.requireNonNull(fecha);
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(horas);
        List<String> horasDia = new ArrayList<>();
        horas.stream().map(x-> x).forEach(hora ->horasDia.add(hora.toString()) );
        appendChange(new DisponibilidadDefinida(diaId.value(), fecha.value(), nombre.value(), horasDia)).apply();
    }

    public void actualizarDisponibilidad(String fecha, List<String> horas) {
        Objects.requireNonNull(fecha);
        Objects.requireNonNull(horas);
        appendChange(new DisponibilidadActualizada(fecha, horas)).apply();
    }
}