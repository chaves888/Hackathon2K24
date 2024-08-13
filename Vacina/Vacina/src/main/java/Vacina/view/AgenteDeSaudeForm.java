package Vacina.view;


import Vacina.model.AgenteDeSaude;
import Vacina.service.AgenteDeSaudeService;
import Vacina.model.Idoso;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import static java.lang.Integer.parseInt;

public class AgenteDeSaudeForm extends JFrame {

    private AgenteDeSaudeService service;
    private JLabel labelId;
    private JTextField campoId;
    private JLabel labelNomeAgente;
    private JTextField campoNomeAgente;
    private JLabel labelFuncao;
    private JTextField campoFuncao;
    private JLabel labelDiaAtendimento;
    private JTextField campoDiaAtendimento;
    private JButton botaoSalvar;
    private JButton botaoCancelar;
    private JButton botaoDeletar;
    private JButton botaoVoltarAgente;
    private JTable tabelaAgente;

    public AgenteDeSaudeForm() {
        service = new AgenteDeSaudeService();

        setTitle("Agente de Saude");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(550, 550);

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);


        campoId = new JTextField(20);
        campoId.setEnabled(false);
        campoId.setVisible(false);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painelEntrada.add(campoId, constraints);

        labelNomeAgente = new JLabel("Nome do Agente:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(labelNomeAgente, constraints);

        campoNomeAgente = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(campoNomeAgente, constraints);

        botaoVoltarAgente = new JButton("Voltar");
        botaoVoltarAgente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fechar o formulário de Agendamento
                dispose();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 3;
        painelEntrada.add(botaoVoltarAgente, constraints);

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(e -> limparCampos());
        constraints.gridx = 1;
        constraints.gridy = 3;
        painelEntrada.add(botaoCancelar, constraints);

        botaoDeletar = new JButton("Deletar");
        botaoDeletar.addActionListener(e -> deletar());
        constraints.gridx = 2;
        constraints.gridy = 3;
        painelEntrada.add(botaoDeletar, constraints);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> executarAcaoDoBotao());
        constraints.gridx = 3;
        constraints.gridy = 3;
        painelEntrada.add(botaoSalvar, constraints);


        JPanel painelSaida = new JPanel(new BorderLayout());

        tabelaAgente = new JTable();
        tabelaAgente.setModel(carregarDadosAgente());
        tabelaAgente.getSelectionModel().addListSelectionListener(e -> selecionarAgente(e));
        tabelaAgente.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(tabelaAgente);
        painelSaida.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(painelEntrada, BorderLayout.NORTH);
        getContentPane().add(painelSaida, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private DefaultTableModel carregarDadosAgente() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");


        service.listarAgente().forEach(agente -> model.addRow(new Object[]{
                        agente.getId(),
                        agente.getNome()
                })
        );
        return model;
    }

    private void limparCampos() {
        campoNomeAgente.setText("");
        campoId.setText("");
    }

    private void executarAcaoDoBotao() {
        if (validarCampoVazio()) {
            service.salvar(construirAgente());
            limparCampos();
            tabelaAgente.setModel(carregarDadosAgente());
        };

    }

    private void deletar() {
        service.excluir(construirAgente());
        limparCampos();
        tabelaAgente.setModel(carregarDadosAgente());
    }

    private AgenteDeSaude construirAgente() {
        return campoId.getText().isEmpty()
                ? new AgenteDeSaude(
                campoNomeAgente.getText()
        )
                : new AgenteDeSaude(
                parseInt(campoId.getText()),
                campoNomeAgente.getText());
    }

    private void selecionarAgente(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = tabelaAgente.getSelectedRow();
            if (selectedRow != -1) {
                var id = (Integer) tabelaAgente.getValueAt(selectedRow, 0);
                campoId.setText(id.toString());
                var nome = (String) tabelaAgente.getValueAt(selectedRow, 1);
                campoNomeAgente.setText(nome);
            }
        }
    }

    private boolean validarCampoVazio() {
        if (campoNomeAgente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo nome está vazio!", "Campo Inválido", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
