package co.com.sofka.model.paciente.events.cita;

import co.com.sofka.model.generic.DomainEvent;

public class CitaAgendada extends DomainEvent {

    private String id;
    private String correo;
    private String fecha;
    private String hora;
    private String estado;

    public CitaAgendada() {
        super("liliana.gallego.citaagendada");
    }

    public CitaAgendada(String id,String correo,String fecha, String hora, String estado) {
        super("liliana.gallego.citaagendada");
        this.id = id;
        this.correo = correo;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
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
