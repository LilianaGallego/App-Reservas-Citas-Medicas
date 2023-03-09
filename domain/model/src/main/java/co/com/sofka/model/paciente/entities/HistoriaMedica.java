package co.com.sofka.model.paciente.entities;

import co.com.sofka.model.paciente.generic.Entity;
import co.com.sofka.model.paciente.values.Anexo;
import co.com.sofka.model.paciente.values.HistoriaMedicaId;

public class HistoriaMedica extends Entity<HistoriaMedicaId> {
    private Anexo anexo;

    public HistoriaMedica(HistoriaMedicaId id, Anexo anexo) {
        super(id);
        this.anexo = anexo;
    }

    public Anexo anexo(){
        return anexo;
    }


    public Anexo getAnexo() {
        return anexo;
    }
}
