package co.com.sofka.model.paciente.events.cita;

import co.com.sofka.model.paciente.generic.DomainEvent;

public class CitaAgendada extends DomainEvent {

    private String id;
    private String fecha;
    private String hora;
    private String estado;

    public CitaAgendada() {
        super("liliana.gallego.citaagendada");
    }

    public CitaAgendada(String id,String fecha, String hora, String estado) {
        super("liliana.gallego.citaagendada");
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }
}
