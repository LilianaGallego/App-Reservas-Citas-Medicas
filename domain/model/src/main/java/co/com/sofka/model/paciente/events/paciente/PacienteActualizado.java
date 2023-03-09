package co.com.sofka.model.paciente.events.paciente;

import co.com.sofka.model.paciente.generic.DomainEvent;

public class PacienteActualizado extends DomainEvent {
    private String nombres;
    private String apellidos;
    private String celular;
    private String correo;


    public PacienteActualizado() {
        super("liliana.gallego.pacientecreado");
    }

    public PacienteActualizado(String nombres,
                          String apellidos,
                          String celular,
                          String correo) {
        super("liliana.gallego.pacientecreado");
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.correo = correo;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public String getCorreo() {
        return correo;
    }


}
