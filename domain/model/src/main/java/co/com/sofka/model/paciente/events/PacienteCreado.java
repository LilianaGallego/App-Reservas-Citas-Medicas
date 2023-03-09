package co.com.sofka.model.paciente.events;

import co.com.sofka.model.paciente.generic.DomainEvent;

public class PacienteCreado extends DomainEvent {
    private String nombres;
    private String apellidos;
    private String celular;
    private String correo;


    public PacienteCreado() {
        super("liliana.gallego.pacientecreado");
    }

    public PacienteCreado(String nombres,
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
