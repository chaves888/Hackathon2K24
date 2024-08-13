package Vacina.view;

import Vacina.service.AgendamentoService;
import Vacina.service.HistoricoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AgendamentoView extends JFrame {
    private JTable tabelaAgendamenmto;
    private AgendamentoService service;

    public AgendamentoView(){
        service = new AgendamentoService();

        setTitle("Agendados");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(500, 600);


        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(5, 5, 5, 5);
        JPanel painelSaida = new JPanel(new BorderLayout());

        tabelaAgendamenmto = new JTable();
        tabelaAgendamenmto.setModel(carregarDadosAgendamento());
        tabelaAgendamenmto.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(tabelaAgendamenmto);
        painelSaida.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(painelEntrada, BorderLayout.NORTH);
        getContentPane().add(painelSaida, BorderLayout.CENTER);

        setLocationRelativeTo(null);

    }
    private DefaultTableModel carregarDadosAgendamento() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Agente");
        model.addColumn("Vacina");
        model.addColumn("Aplicação");
        model.addColumn("status");

        service.listarAgendamento().forEach(agendamento -> model.addRow(new Object[]{
                        agendamento.getAgente(),
                        agendamento.getIdoso(),
                        agendamento.getVacina(),
                        agendamento.getAtendimento(),
                        agendamento.getStatus()

                })
        );
        return model;
    }
}

