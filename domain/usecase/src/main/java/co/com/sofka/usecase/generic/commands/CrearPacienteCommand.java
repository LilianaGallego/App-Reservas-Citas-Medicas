package co.com.sofka.usecase.generic.commands;


import co.com.sofka.usecase.generic.Command;

public class CrearPacienteCommand extends Command {
    private String pacienteId;
    private String nombres;
    private String apellidos;
    private String celular;
    private String correo;

    public CrearPacienteCommand() {
    }

    public CrearPacienteCommand(String pacienteId, String nombres, String apellidos, String celular, String correo) {
        this.pacienteId = pacienteId;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.correo = correo;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(String pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
