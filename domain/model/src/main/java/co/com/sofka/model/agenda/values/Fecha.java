package co.com.sofka.model.agenda.values;

import co.com.sofka.model.agenda.generic.ValueObject;


public class Fecha implements ValueObject<String> {
    private String value;

    public Fecha(String value) {
        this.value = value;
    }

    @Override
    public String value(){
        return value;
    }


}
