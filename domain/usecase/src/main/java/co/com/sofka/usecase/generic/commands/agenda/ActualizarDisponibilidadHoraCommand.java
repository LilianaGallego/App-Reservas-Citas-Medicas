package co.com.sofka.usecase.generic.commands.agenda;

import co.com.sofka.usecase.generic.Command;

import java.util.List;

public class ActualizarDisponibilidadHoraCommand extends Command {
    private String agendaId;
    private String diaId;
    private String fecha;
    private String nombre;
    private List<String> horas;


    public ActualizarDisponibilidadHoraCommand() {
    }

    public ActualizarDisponibilidadHoraCommand(


            List<String> horasModificadas)
    {
        this.horas =  horasModificadas;

    }


}
