package Vacina.view;

import Vacina.model.Agendamento;
import Vacina.service.AgendamentoService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.text.MaskFormatter;

import static java.lang.Integer.parseInt;

public class AgendamentoForm extends JFrame {
    private AgendamentoService service;
    private JLabel labelId;
    private JTextField campoId;
    private JLabel labelNomeAgente;
    private JTextField campoNomeAgente;
    private JLabel labelIdoso;
    private JTextField campoIdoso;
    private JLabel labelVacina;
    private JTextField campoVacina;
    private JLabel labelAtendimento;
    private JFormattedTextField campoAtendimento;

    private JLabel labelStatus;
    private JTextField campoStatus;
    private JButton botaoSalvar;
    private JButton botaoCancelar;
    private JButton botaoDeletar;
    private JButton botaoVoltarAgendamento;
    private JTable tabelaAgendamento;

    public AgendamentoForm() {
        service = new AgendamentoService();

        setTitle("Agendamento");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600, 550);

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        campoId = new JTextField(20);
        campoId.setEnabled(false);
        campoId.setVisible(false);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painelEntrada.add(campoId, constraints);

        labelNomeAgente = new JLabel("Nome do Idoso:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(labelNomeAgente, constraints);

        campoNomeAgente = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(campoNomeAgente, constraints);

        labelIdoso = new JLabel("Nome do Agente:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(labelIdoso, constraints);

        campoIdoso = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(campoIdoso, constraints);

        labelVacina = new JLabel("Vacina:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        painelEntrada.add(labelVacina, constraints);

        campoVacina = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        painelEntrada.add(campoVacina, constraints);

        labelAtendimento = new JLabel("Dia de Atendimento:");
        constraints.gridx = 0;
        constraints.gridy = 4;
        painelEntrada.add(labelAtendimento, constraints);

        try {
            campoAtendimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        campoAtendimento.setColumns(20);
        constraints.gridx = 1;
        constraints.gridy = 4;
        painelEntrada.add(campoAtendimento, constraints);

        labelStatus = new JLabel("Status:");
        constraints.gridx = 0;
        constraints.gridy = 5;
        painelEntrada.add(labelStatus, constraints);

        campoStatus = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 5;
        painelEntrada.add(campoStatus, constraints);

        botaoVoltarAgendamento = new JButton("Voltar");
        botaoVoltarAgendamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 6;
        painelEntrada.add(botaoVoltarAgendamento, constraints);

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(e -> limparCampos());
        constraints.gridx = 1;
        constraints.gridy = 6;
        painelEntrada.add(botaoCancelar, constraints);

        botaoDeletar = new JButton("Deletar");
        botaoDeletar.addActionListener(e -> deletar());
        constraints.gridx = 2;
        constraints.gridy = 6;
        painelEntrada.add(botaoDeletar, constraints);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> executarAcaoDoBotao());
        constraints.gridx = 3;
        constraints.gridy = 6;
        painelEntrada.add(botaoSalvar, constraints);

        JPanel painelSaida = new JPanel(new BorderLayout());

        tabelaAgendamento = new JTable();
        tabelaAgendamento.setModel(carregarAtendimento());
        tabelaAgendamento.getSelectionModel().addListSelectionListener(e -> selecionarAgendamento(e));
        tabelaAgendamento.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(tabelaAgendamento);
        painelSaida.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(painelEntrada, BorderLayout.NORTH);
        getContentPane().add(painelSaida, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private DefaultTableModel carregarAtendimento() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Agente");
        model.addColumn("Idoso");
        model.addColumn("Vacina");
        model.addColumn("Data Atendimento");
        model.addColumn("Status");



        service.listarAgendamento().forEach(agendamento -> model.addRow(new Object[]{
                agendamento.getId(),
                agendamento.getIdoso(),
                agendamento.getAgente(),
                agendamento.getVacina(),
                agendamento.getAtendimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                agendamento.getStatus()
        }));
        return model;
    }

    private void limparCampos() {
        campoIdoso.setText("");
        campoAtendimento.setText("");
        campoNomeAgente.setText("");
        campoVacina.setText("");
        campoId.setText("");
        campoStatus.setText("");
    }

    private boolean executarAcaoDoBotao() {
        if (validarCampoVazio()) {
            String dataFormatada = campoAtendimento.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            try {
                LocalDate dataConvertida = LocalDate.parse(dataFormatada, formatter);
                Agendamento agendamento = construirAgendamento(dataConvertida);
                service.salvar(agendamento);
                limparCampos();
                tabelaAgendamento.setModel(carregarAtendimento());
                return true;
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, "Data inválida!", "Erro de Formato de Data", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false;
    }

    private void deletar() {
        service.excluir(construirAgendamento(null));
        limparCampos();
        tabelaAgendamento.setModel(carregarAtendimento());
    }

    private Agendamento construirAgendamento(LocalDate dataAtendimento) {
        if (campoId.getText().isEmpty()) {
            return new Agendamento(
                    null,
                    campoIdoso.getText(),
                    campoNomeAgente.getText(),
                    campoVacina.getText(),
                    dataAtendimento,
                    campoStatus.getText()

            );
        } else {
            return new Agendamento(
                    parseInt(campoId.getText()),
                    campoIdoso.getText(),
                    campoNomeAgente.getText(),
                    campoVacina.getText(),
                    dataAtendimento,
                    campoStatus.getText()
            );
        }
    }

    private void selecionarAgendamento(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = tabelaAgendamento.getSelectedRow();
            if (selectedRow != -1) {
                var id = (Integer) tabelaAgendamento.getValueAt(selectedRow, 0);
                campoId.setText(id.toString());
                var idoso = (String) tabelaAgendamento.getValueAt(selectedRow, 1);
                campoIdoso.setText(idoso);
                var agente = (String) tabelaAgendamento.getValueAt(selectedRow, 2);
                campoNomeAgente.setText(agente);
                var vacina = (String) tabelaAgendamento.getValueAt(selectedRow, 3);
                campoVacina.setText(vacina);
                var atendimento = (String) tabelaAgendamento.getValueAt(selectedRow, 4);
                campoAtendimento.setText(atendimento);
                var status = (String) tabelaAgendamento.getValueAt(selectedRow, 5);
                campoStatus.setText(status);
            }
        }
    }

    private boolean validarCampoVazio() {
        boolean isValid = true;

        if (campoNomeAgente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo do idoso está vazio!", "Campo Inválido", JOptionPane.ERROR_MESSAGE);
            isValid = false;
        }

        if (campoVacina.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo da vacina está vazio!", "Campo Inválido", JOptionPane.ERROR_MESSAGE);
            isValid = false;
        }

        return isValid;
    }



}
