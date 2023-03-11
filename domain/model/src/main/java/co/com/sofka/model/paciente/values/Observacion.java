package co.com.sofka.model.paciente.values;

import co.com.sofka.model.generic.ValueObject;

public class Observacion implements ValueObject<String> {
    private String observacion;

    public Observacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String value() {
        return observacion;
    }

}
