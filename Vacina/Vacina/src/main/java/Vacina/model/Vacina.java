package Vacina.model;

public class Vacina {
    private Integer id;
    private String nome;

    public Vacina (Integer id, String nome){
        this.id = id;
        this.nome = nome;
    }
    public Vacina ( String nome){

        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



}
