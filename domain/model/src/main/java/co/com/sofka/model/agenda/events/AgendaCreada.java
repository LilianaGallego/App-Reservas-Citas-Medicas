package co.com.sofka.model.agenda.events;

import co.com.sofka.model.agenda.values.DiaId;
import co.com.sofka.model.agenda.values.Fecha;
import co.com.sofka.model.agenda.values.Hora;
import co.com.sofka.model.agenda.values.Nombre;
import co.com.sofka.model.generic.DomainEvent;

import java.util.List;

public class AgendaCreada extends DomainEvent {
    private String semana;
    private String id;
    private String fecha;
    private String nombre;
    private List<String> horas;

    public AgendaCreada() {
        super("liliana.gallego.agendacreada");
    }

    public AgendaCreada(String semana,String id, String fecha, String nombre, List<String> horas) {
        super("liliana.gallego.agendacreada");
        this.semana = semana;
            this.id = id;

            this.fecha = fecha;
            this.nombre = nombre;
            this.horas = horas;
    }

    public String getSemana() {
        return semana;
    }
}
