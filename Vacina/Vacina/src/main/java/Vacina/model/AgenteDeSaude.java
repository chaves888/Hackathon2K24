package Vacina.model;

public class AgenteDeSaude {

    private Integer id;
    private String nome;

    public AgenteDeSaude (Integer id, String nome){
        this.id = id;
        this.nome = nome;

    }

    public AgenteDeSaude ( String nome){
        this.nome = nome;

}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
