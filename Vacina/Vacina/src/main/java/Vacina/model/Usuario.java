package Vacina.model;

public class Usuario {

    private Integer id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private String ativo;

    public Usuario(Integer id, String nome, String email, String login, String senha, String ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
}

    public Usuario(String nome, String email, String login, String senha, String ativo) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
}
