package co.com.sofka.model.agenda.events;

import co.com.sofka.model.agenda.values.Disponible;
import co.com.sofka.model.generic.DomainEvent;

import java.util.Collection;
import java.util.List;

public class DisponibilidadDefinida extends DomainEvent {
    private String diaId;
    private String fecha;
    private String nombre;
    private List<String> horas;

    public DisponibilidadDefinida() {
        super("liliana.gallego.disponibilidaddefinida");
    }

    public DisponibilidadDefinida(String diaId, String fecha, String nombre, List<String> horas) {
        super("liliana.gallego.disponibilidaddefinida");
        this.diaId = diaId;
        this.fecha = fecha;
        this.nombre = nombre;
        this.horas = horas;
    }



    public String getFecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDiaId() {
        return diaId;
    }

    public List<String> getHoras() {
        return horas;
    }
}
