package co.com.sofka.model.paciente.values;


import co.com.sofka.model.paciente.generic.ValueObject;

public class Anexo implements ValueObject<String> {
    private String anexo;

    public Anexo(String anexo) {
        this.anexo = anexo;
    }

    @Override
    public String value(){
        return anexo;
    }


}