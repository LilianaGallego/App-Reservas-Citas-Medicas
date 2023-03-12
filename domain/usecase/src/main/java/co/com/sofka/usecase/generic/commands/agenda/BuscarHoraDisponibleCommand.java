package co.com.sofka.usecase.generic.commands.agenda;

import co.com.sofka.usecase.generic.Command;

public class BuscarHoraDisponibleCommand extends Command {

    private String diaId;
    private String hora;

    public BuscarHoraDisponibleCommand() {
    }

    public BuscarHoraDisponibleCommand(String diaId, String hora) {
        this.diaId = diaId;
        this.hora = hora;
    }

    public String getDiaId() {
        return diaId;
    }

    public void setDiaId(String diaId) {
        this.diaId = diaId;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
