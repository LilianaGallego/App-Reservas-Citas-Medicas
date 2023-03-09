package co.com.sofka.model.paciente;

import co.com.sofka.model.paciente.entities.HistoriaMedica;
import co.com.sofka.model.paciente.events.PacienteCreado;
import co.com.sofka.model.paciente.generic.EventChange;
import co.com.sofka.model.paciente.values.Apellidos;
import co.com.sofka.model.paciente.values.Celular;
import co.com.sofka.model.paciente.values.Correo;
import co.com.sofka.model.paciente.values.Nombres;

import java.util.ArrayList;

public class PacienteChange extends EventChange {

    public PacienteChange(Paciente paciente){

        apply((PacienteCreado event)-> {
            paciente.nombres = new Nombres(event.getNombres());
            paciente.apellidos = new Apellidos(event.getApellidos());
            paciente.celular = new Celular(event.getCelular());
            paciente.correo = new Correo(event.getCorreo());
            //paciente.historiaMedica = new
            //paciente.revisiones = new ArrayList<>();

            ///paciente.citas = new ArrayList<>();


        });

        /*apply((CommentAdded event)-> {
            Comment comment = new Comment(CommentId.of(event.getId()),
                    new Author(event.getAuthor()),
                    new Content(event.getContent()));
            post.comments.add(comment);
        });*/

    }
}
