package Vacina.view;





import Vacina.main;
import Vacina.model.Vacina;
import Vacina.service.VacinaService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.remainderUnsigned;


public class Vacinaform extends JFrame {

    private VacinaService service;
    private JLabel labelId;
    private JTextField campoId;
    private JLabel labelNomeVacina;
    private JTextField campoNomeVacina;
    private JButton botaoSalvar;
    private JButton botaoCancelar;
    private JButton botaoDeletar;
    private JTable tabelaVacina;
//    private JButton botaoListar;
    private JButton botaoVoltarVacina;


    public Vacinaform() {

        service = new VacinaService();

        setTitle("Vacina");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(500, 550);

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

//        labelId = new JLabel("ID:");
//        constraints.gridx = 0;
//        constraints.gridy = 0;
//        painelEntrada.add(labelId, constraints);

        campoId = new JTextField(20);
        campoId.setEnabled(false);
        campoId.setVisible(false);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painelEntrada.add(campoId, constraints);

        labelNomeVacina = new JLabel("Vacina:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(labelNomeVacina, constraints);

        campoNomeVacina = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(campoNomeVacina, constraints);

        botaoVoltarVacina = new JButton("Voltar");
        botaoVoltarVacina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fechar o formulário de Vacina
                dispose();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(botaoVoltarVacina, constraints);

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(e -> limparCampos());
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(botaoCancelar, constraints);

        botaoDeletar = new JButton("Deletar");
        botaoDeletar.addActionListener(e -> deletar());
        constraints.gridx = 2;
        constraints.gridy = 2;
        painelEntrada.add(botaoDeletar, constraints);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> executarAcaoDoBotao());
        constraints.gridx = 3;
        constraints.gridy = 2;
        painelEntrada.add(botaoSalvar, constraints);



        JPanel painelSaida = new JPanel(new BorderLayout());

        tabelaVacina = new JTable();
        tabelaVacina.setModel(carregarDadosVacina());
        tabelaVacina.getSelectionModel().addListSelectionListener(e -> selecionarVacina(e));
        tabelaVacina.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(tabelaVacina);
        painelSaida.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(painelEntrada, BorderLayout.NORTH);
        getContentPane().add(painelSaida, BorderLayout.CENTER);

        setLocationRelativeTo(null);


    }

    private DefaultTableModel carregarDadosVacina() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");


        service.listarVacina().forEach(vacina -> model.addRow(new Object[]{
                        vacina.getId(),
                        vacina.getNome(),
                })
        );
        return model;
    }

    private void limparCampos() {
        campoNomeVacina.setText("");
        campoId.setText("");
    }

    private void executarAcaoDoBotao() {
        if (validarCampoVazio()) {
            service.salvar(construirVacina());
            limparCampos();
            tabelaVacina.setModel(carregarDadosVacina());
        }
    }

    private void deletar() {
        service.excluir(construirVacina());
        limparCampos();
        tabelaVacina.setModel(carregarDadosVacina());
    }

    private Vacina construirVacina() {
        return campoId.getText().isEmpty()
                ? new Vacina(
                campoNomeVacina.getText()
        )
                : new Vacina(
                parseInt(campoId.getText()),
                campoNomeVacina.getText());
    }

    private void selecionarVacina(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = tabelaVacina.getSelectedRow();
            if (selectedRow != -1) {
                var id = (Integer) tabelaVacina.getValueAt(selectedRow, 0);
                campoId.setText(id.toString());
                var nome = (String) tabelaVacina.getValueAt(selectedRow, 1);
                campoNomeVacina.setText(nome);

            }
        }
    }
    private boolean validarCampoVazio() {
        if (campoNomeVacina.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo vacina está vazio!", "Campo Inválido", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}
