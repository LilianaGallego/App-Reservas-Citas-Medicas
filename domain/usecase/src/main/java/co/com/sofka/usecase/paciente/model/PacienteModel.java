package co.com.sofka.usecase.paciente.model;

import co.com.sofka.usecase.generic.commands.paciente.paciente.CrearPacienteCommand;

import java.util.List;

public class PacienteModel {

    private String pacienteId;
    private String nombres;
    private String apellidos;
    private String celular;
    private String correo;
    private List<String> citas;

    public PacienteModel() {
    }

    public PacienteModel(CrearPacienteCommand command) {
        this.pacienteId = command.getPacienteId();
        this.nombres = command.getNombres();
        this.apellidos = command.getApellidos();
        this.celular = command.getCelular();
        this.correo = command.getCelular();
        this.citas = command.getCitas();
    }

    public PacienteModel(String pacienteId, String nombres, String apellidos, String celular, String correo, List<String> citas) {
        this.pacienteId = pacienteId;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.correo = correo;
        this.citas = citas;
    }
}
