package co.com.sofka.model.paciente;
import co.com.sofka.model.paciente.entities.Cita;
import co.com.sofka.model.paciente.entities.HistoriaMedica;
import co.com.sofka.model.paciente.entities.Revision;
import co.com.sofka.model.paciente.events.CitaAgendada;
import co.com.sofka.model.paciente.events.PacienteCreado;
import co.com.sofka.model.paciente.generic.AggregateRoot;
import co.com.sofka.model.paciente.generic.DomainEvent;
import co.com.sofka.model.paciente.values.*;

import java.util.List;
import java.util.Objects;


public class Paciente extends AggregateRoot<PacienteId> {
    protected Nombres nombres;
    protected Apellidos apellidos;
    protected Celular celular;
    protected Correo correo;
    protected HistoriaMedica historiaMedica;
    protected List<Revision> revisiones;
    protected List<Cita> citas;

    public Paciente(PacienteId id,
                    Nombres nombres,
                    Apellidos apellidos,
                    Celular celular,
                    Correo correo
                    ) {
        super(id);
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.correo = correo;
        subscribe(new PacienteChange(this));
        appendChange(new PacienteCreado(
                nombres.value(),
                apellidos.value(),
                celular.value(),
                correo.value()
               )).apply();
    }

    private Paciente (PacienteId pacienteId){
        super(pacienteId);
        subscribe(new PacienteChange(this));
    }

    public static  Paciente from(PacienteId pacienteId, List<DomainEvent> events){
        Paciente paciente = new Paciente(pacienteId);
        events.forEach(event -> paciente.applyEvent(event));
        return paciente;
    }

    public void AgendarCita(CitaId citaId, Fecha fecha, Hora hora, Estado estado){
        Objects.requireNonNull(citaId);
        Objects.requireNonNull(fecha);
        Objects.requireNonNull(hora);
        Objects.requireNonNull(estado);
        appendChange( new CitaAgendada(fecha.value(), hora.value(), estado.value()  )).apply();
    }


}