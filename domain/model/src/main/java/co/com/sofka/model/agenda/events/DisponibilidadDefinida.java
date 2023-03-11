package co.com.sofka.model.agenda.events;

import co.com.sofka.model.agenda.values.Disponible;
import co.com.sofka.model.generic.DomainEvent;

import java.util.Collection;

public class DisponibilidadDefinida extends DomainEvent {
    private String diaId;
    private String fecha;
    private String nombre;
    private String hora;
    private String disponible;

    public DisponibilidadDefinida() {
        super("liliana.gallego.disponibilidaddefinida");
    }

    public DisponibilidadDefinida(String diaId, String fecha, String nombre, String hora, String disponible) {
        super("liliana.gallego.disponibilidaddefinida");
        this.diaId = diaId;
        this.fecha = fecha;
        this.nombre = nombre;
        this.hora = hora;
        this.disponible = disponible;
    }

    public String getId() {
        return diaId;
    }

    public String getFecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHora() {
        return hora;
    }

    public String getDisponible() {
        return disponible;
    }


}
