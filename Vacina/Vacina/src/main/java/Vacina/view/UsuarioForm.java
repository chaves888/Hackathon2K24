package Vacina.view;

import Vacina.model.Idoso;
import Vacina.model.Usuario;
import Vacina.model.Vacina;
import Vacina.service.IdosoService;
import Vacina.service.UsuarioService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class UsuarioForm extends JFrame{
    private UsuarioService service;
    private JLabel labelId;
    private JTextField campoId;
    private JLabel labelNome;
    private JTextField campoNome;
    private JLabel labelEmail;
    private JTextField campoEmail;
    private JLabel labelLogin;
    private JTextField campoLogin;
    private JLabel labelSenha;
    private JPasswordField campoSenha;
    private JLabel labelAtivo;
    private JTextField campoAtivo;
    private JButton botaoSalvar;
    private JButton botaoCancelar;
    private JButton botaoDeletar;
    private JButton botaoVoltarUsuario;
    private JTable tabelaUsuario;

    public UsuarioForm() {

        service = new UsuarioService();

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

        labelNome = new JLabel("Nome:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(labelNome, constraints);

        campoNome = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(campoNome, constraints);

        labelEmail = new JLabel("E-mail");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(labelEmail, constraints);

        campoEmail = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(campoEmail, constraints);

        labelLogin = new JLabel("Login");
        constraints.gridx = 0;
        constraints.gridy = 3;
        painelEntrada.add(labelLogin, constraints);

        campoLogin = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        painelEntrada.add(campoLogin, constraints);

        labelSenha = new JLabel("Senha");
        constraints.gridx = 0;
        constraints.gridy = 4;
        painelEntrada.add(labelSenha, constraints);

        campoSenha = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 4;
        painelEntrada.add(campoSenha, constraints);

        labelAtivo = new JLabel("Ativo");
        constraints.gridx = 0;
        constraints.gridy = 5;
        painelEntrada.add(labelAtivo, constraints);

        campoAtivo = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 5;
        painelEntrada.add(campoAtivo, constraints);


        botaoVoltarUsuario = new JButton("Voltar");
        botaoVoltarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 7;
        painelEntrada.add(botaoVoltarUsuario, constraints);

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

        tabelaUsuario = new JTable();
        tabelaUsuario.setModel(carregarDadosUsuario());
        tabelaUsuario.getSelectionModel().addListSelectionListener(e -> selecionarUsuario(e));
        tabelaUsuario.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(tabelaUsuario);
        painelSaida.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(painelEntrada, BorderLayout.NORTH);
        getContentPane().add(painelSaida, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private DefaultTableModel carregarDadosUsuario() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("E-mail");
        model.addColumn("Login");
        model.addColumn("Senha");
        model.addColumn("Ativo");

        service.listarUsuario().forEach(usuario -> model.addRow(new Object[]{
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getAtivo(),
                })
        );
        return model;
    }

    private void limparCampos() {
        campoNome.setText("");
        campoId.setText("");
        campoEmail.setText("");
        campoLogin.setText("");
        campoSenha.setText("");
        campoAtivo.setText("");

    }

    private void executarAcaoDoBotao() {
        if (validarCampoVazio()) {
            service.salvar(construirUsuario());
            limparCampos();
            tabelaUsuario.setModel(carregarDadosUsuario());
        }
        ;
    }

    private void deletar() {
        service.excluir(construirUsuario());
        limparCampos();
        tabelaUsuario.setModel(carregarDadosUsuario());
    }

    private Usuario construirUsuario() {
        return campoId.getText().isEmpty()
                ? new Usuario(
                campoNome.getText(),
                campoEmail.getText(),
                campoLogin.getText(),
                campoSenha.getText(),
                campoAtivo.getText()

        )
                : new Usuario(
                parseInt(campoId.getText()),
                campoNome.getText(),
                campoEmail.getText(),
                campoLogin.getText(),
                campoSenha.getText(),
                campoAtivo.getText()
        );
    }

    private void selecionarUsuario(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = tabelaUsuario.getSelectedRow();
            if (selectedRow != -1) {
                var id = (Integer) tabelaUsuario.getValueAt(selectedRow, 0);
                campoId.setText(id.toString());
                var nome = (String) tabelaUsuario.getValueAt(selectedRow, 1);
                campoNome.setText(nome);
                var email = (String) tabelaUsuario.getValueAt(selectedRow, 2);
                campoEmail.setText(email);
                var login = (String) tabelaUsuario.getValueAt(selectedRow, 3);
                campoLogin.setText(login);
                var senha = (String) tabelaUsuario.getValueAt(selectedRow, 4);
                campoSenha.setText(senha);
                var ativo = (String) tabelaUsuario.getValueAt(selectedRow, 5);
                campoAtivo.setText(ativo);
            }
        }
    }

    private boolean validarCampoVazio() {
        if (campoNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome está vazio!", "Campo Inválido", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}


