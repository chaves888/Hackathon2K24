package Vacina.view;


import Vacina.model.Idoso;
import Vacina.service.IdosoService;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Provider;
import java.time.LocalDate;

import static java.lang.Integer.parseInt;


public class IdosoForm extends JFrame {
    private IdosoService service;
    private JLabel labelId;
    private JTextField campoId;
    private JLabel labelNomeIdoso;
    private JTextField campoNomeIdoso;
    private JLabel labelObservacoes;
    private JTextArea areaObservacoes;
    private JButton botaoSalvar;
    private JButton botaoCancelar;
    private JButton botaoDeletar;
    private JButton botaoVoltarIdoso;
    private JTable tabelaIdoso;


    public IdosoForm() {

        service = new IdosoService();

        setTitle("Cadastro");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(500, 600); // Adjusted height to accommodate new fields

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);


        campoId = new JTextField(20);
        campoId.setEnabled(false);
        campoId.setVisible(false);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painelEntrada.add(campoId, constraints);

        labelNomeIdoso = new JLabel("Nome:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(labelNomeIdoso, constraints);

        campoNomeIdoso = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(campoNomeIdoso, constraints);

        labelObservacoes = new JLabel("Observação");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(labelObservacoes, constraints);

        areaObservacoes = new JTextArea(4, 20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(new JScrollPane(areaObservacoes), constraints);

        botaoVoltarIdoso = new JButton("Voltar");
        botaoVoltarIdoso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fechar o formulário de Idoso
                dispose();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 7;
        painelEntrada.add(botaoVoltarIdoso, constraints);

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(e -> limparCampos());
        constraints.gridx = 1;
        constraints.gridy = 7;
        painelEntrada.add(botaoCancelar, constraints);

        botaoDeletar = new JButton("Deletar");
        botaoDeletar.addActionListener(e -> deletar());
        constraints.gridx = 2;
        constraints.gridy = 7;
        painelEntrada.add(botaoDeletar, constraints);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> executarAcaoDoBotao());
        constraints.gridx = 3;
        constraints.gridy = 7;
        painelEntrada.add(botaoSalvar, constraints);


        JPanel painelSaida = new JPanel(new BorderLayout());

        tabelaIdoso = new JTable();
        tabelaIdoso.setModel(carregarDadosIdoso());
        tabelaIdoso.getSelectionModel().addListSelectionListener(e -> selecionarIdoso(e));
        tabelaIdoso.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(tabelaIdoso);
        painelSaida.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(painelEntrada, BorderLayout.NORTH);
        getContentPane().add(painelSaida, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private DefaultTableModel carregarDadosIdoso() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Observação");

        service.listarIdoso().forEach(idoso -> model.addRow(new Object[]{
                        idoso.getId(),
                        idoso.getNome(),
                        idoso.getObservacao()
                })
        );
        return model;
    }

    private void limparCampos() {
        campoNomeIdoso.setText("");
        campoId.setText("");
        areaObservacoes.setText("");
    }

    private void executarAcaoDoBotao() {
        if (validarCampoVazio()) {
            service.salvar(construiridoso());
            limparCampos();
            tabelaIdoso.setModel(carregarDadosIdoso());
        }
        ;
    }

    private void deletar() {
        service.excluir(construiridoso());
        limparCampos();
        tabelaIdoso.setModel(carregarDadosIdoso());
    }

    private Idoso construiridoso() {
        return campoId.getText().isEmpty()
                ? new Idoso(
                campoNomeIdoso.getText(),
                areaObservacoes.getText()
        )
                : new Idoso(
                parseInt(campoId.getText()),
                campoNomeIdoso.getText(),
                areaObservacoes.getText());
    }

    private void selecionarIdoso(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = tabelaIdoso.getSelectedRow();
            if (selectedRow != -1) {
                var id = (Integer) tabelaIdoso.getValueAt(selectedRow, 0);
                campoId.setText(id.toString());
                var nome = (String) tabelaIdoso.getValueAt(selectedRow, 1);
                campoNomeIdoso.setText(nome);
                var observacao = (String) tabelaIdoso.getValueAt(selectedRow, 2);
                areaObservacoes.setText(observacao);

            }
        }
    }

    private boolean validarCampoVazio() {
        if (campoNomeIdoso.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome está vazio!", "Campo Inválido", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
