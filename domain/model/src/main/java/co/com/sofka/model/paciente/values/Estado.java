package co.com.sofka.model.paciente.values;

import co.com.sofka.model.paciente.generic.ValueObject;


public class Estado implements ValueObject<String> {

    private String estado;

    public Estado(String estado) {
        this.estado = estado;
    }

    @Override
    public String value() {
        return estado;
    }
}