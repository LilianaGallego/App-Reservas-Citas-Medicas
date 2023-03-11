package co.com.sofka.model.paciente;

import co.com.sofka.model.paciente.entities.Cita;
import co.com.sofka.model.paciente.entities.Revision;
import co.com.sofka.model.paciente.events.cita.CitaAgendada;
import co.com.sofka.model.paciente.events.paciente.PacienteCreado;
import co.com.sofka.model.paciente.events.revision.RevisionCreada;
import co.com.sofka.model.generic.EventChange;
import co.com.sofka.model.paciente.values.*;

import java.util.ArrayList;

public class PacienteChange extends EventChange {

    public PacienteChange(Paciente paciente){

        apply((PacienteCreado event)-> {
            paciente.nombres = new Nombres(event.getNombres());
            paciente.apellidos = new Apellidos(event.getApellidos());
            paciente.celular = new Celular(event.getCelular());
            paciente.correo = new Correo(event.getCorreo());
            paciente.citas = new ArrayList<>();
            paciente.revisiones = new ArrayList<>();



        });

        apply((CitaAgendada event)-> {
            Cita cita = new Cita(CitaId.of(event.getId()),
                    new Fecha(event.getFecha()),
                    new Hora(event.getHora()),
                    new Estado(event.getEstado()));
            paciente.citas.add(cita);
        });



        apply((RevisionCreada event)-> {
            Revision revision = new Revision(RevisionId.of(event.getId()),
                    new Fecha(event.getFecha()),
                    new Observacion(event.getObservacion())
            );
            paciente.revisiones.add(revision);
        });
        }
}
