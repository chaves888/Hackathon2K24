package Vacina.model;

import java.time.LocalDate;

public class Agendamento {


    private Integer id;
    private String agente;
    private String idoso;
    private String vacina;
    private LocalDate atendimento;

    private String status;



    public Agendamento (Integer id, String agente, String idoso, String vacina, LocalDate atendimento, String status) {
        this.id = id;
        this.agente = agente;
        this.idoso = idoso;
        this.vacina = vacina;
        this.atendimento = atendimento;
        this.status = status;
    }
//    public Agendamento( String agente, String idoso, String vacina, LocalDate atendimento, String status) {
//        this.agente = agente;
//        this.idoso = idoso;
//        this.vacina = vacina;
//        this.atendimento = atendimento;
//        this.status = status;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public String getIdoso() {
        return idoso;
    }

    public void setIdoso(String idoso) {
        this.idoso = idoso;
    }

    public String getVacina() {
        return vacina;
    }

    public void setVacina(String vacina) {
        this.vacina = vacina;
    }

    public LocalDate getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(LocalDate atendimento) {
        this.atendimento = atendimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
