package co.com.sofka.model.agenda.values;

import co.com.sofka.model.generic.ValueObject;


public class Hora implements ValueObject<String> {
    private String value;

    public Hora(String value) {
        this.value = value;
    }

    @Override
    public String value(){
        return value;
    }

}
