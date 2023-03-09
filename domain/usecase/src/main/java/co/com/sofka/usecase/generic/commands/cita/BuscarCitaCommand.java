package co.com.sofka.usecase.generic.commands.cita;

import co.com.sofka.usecase.generic.Command;

public class BuscarCitaCommand extends Command {
    private String citaId;

    public BuscarCitaCommand(String citaId) {
        this.citaId = citaId;
    }

    public String getCitaId() {
        return citaId;
    }

}
