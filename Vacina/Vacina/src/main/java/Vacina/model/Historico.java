package Vacina.model;

import java.time.LocalDate;

public class Historico {


    private String nomeIdoso;
    private String Observacao;

    public Historico(String nomeIdoso, String observacao) {
        this.nomeIdoso = nomeIdoso;
        Observacao = observacao;
    }





    public String getNomeIdoso() {
        return nomeIdoso;
    }

    public void setNomeIdoso(String nomeIdoso) {
        this.nomeIdoso = nomeIdoso;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String observacao) {
        Observacao = observacao;
    }


}
