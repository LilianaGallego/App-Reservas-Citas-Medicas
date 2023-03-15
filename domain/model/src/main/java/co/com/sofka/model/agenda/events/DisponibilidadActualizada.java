package co.com.sofka.model.agenda.events;

import co.com.sofka.model.generic.DomainEvent;

import java.util.List;

public class DisponibilidadActualizada extends DomainEvent {
    private String diaId;
    private String fecha;
    private String nombre;
    private List<String> horas;

    public DisponibilidadActualizada() {
        super("liliana.gallego.disponibilidadactualizada");
    }

    public DisponibilidadActualizada(String fecha, List<String>  horas) {
        super("liliana.gallego.disponibilidadactualizada");
        this.fecha = fecha;
        this.horas = horas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDiaId() {
        return diaId;
    }

    public void setDiaId(String diaId) {
        this.diaId = diaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getHoras() {
        return horas;
    }

    public void setHoras(List<String> horas) {
        this.horas = horas;
    }
}
