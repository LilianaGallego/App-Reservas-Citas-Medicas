package co.com.sofka.model.agenda.events;

import co.com.sofka.model.generic.DomainEvent;

public class DisponibilidadActualizada extends DomainEvent {
    private String fecha;
    private String hora;

    public DisponibilidadActualizada() {
        super("liliana.gallego.disponibilidadactualizada");
    }

    public DisponibilidadActualizada(String fecha, String hora) {
        super("liliana.gallego.disponibilidadactualizada");
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
