package co.com.sofka.usecase.generic.commands;


import co.com.sofka.model.paciente.entities.Cita;
import co.com.sofka.usecase.generic.Command;

import java.util.List;

public class CrearPacienteCommand extends Command {
    private String pacienteId;
    private String nombres;
    private String apellidos;
    private String celular;
    private String correo;
    private List<Cita> citas;

    public CrearPacienteCommand() {
    }


    public CrearPacienteCommand(String pacienteId, String nombres, String apellidos, String celular, String correo, List<Cita> citas) {
        this.pacienteId = pacienteId;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.correo = correo;
        this.citas = citas;
    }

    public List<Cita> getCitas() {
        return citas;
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
