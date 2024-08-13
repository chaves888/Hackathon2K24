package Vacina;



import Vacina.view.*;

import java.awt.*;

import javax.swing.*;

public class main {
    public static void main(String[] args) {

        agendamentoNotificação();

        JFrame frame = new JFrame("Formulário");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon appIcon = new ImageIcon("C:/xampp/htdocs/Hackathon2K24/admin/img/icone.png");
        frame.setIconImage(appIcon.getImage());

        JButton idosoButton = new JButton("Cadastrar/Alterar Idoso");
        JButton usuarioButton = new JButton("Cadastrar/Alterar Usuario");
        JButton agenteButton = new JButton("Cadastrar/Alterar Agente");
        JButton vacinaButton = new JButton("Cadastrar/Alterar Vacina");
        JButton agendamentoButton = new JButton("Cadastrar/Alterar Agendamento");
        JButton historicoButton = new JButton("Historico");
        JButton agendaButton = new JButton("Agenda");

        Dimension buttonSize = new Dimension(250, 50); // Ajuste conforme necessário
        idosoButton.setPreferredSize(buttonSize);
        usuarioButton.setPreferredSize(buttonSize);
        agenteButton.setPreferredSize(buttonSize);
        vacinaButton.setPreferredSize(buttonSize);
        agendamentoButton.setPreferredSize(buttonSize);
        historicoButton.setPreferredSize(buttonSize);
        agendaButton.setPreferredSize(buttonSize);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(37, 98, 101));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0); // Espaçamento entre os botões
        gbc.anchor = GridBagConstraints.CENTER;

        buttonPanel.add(idosoButton, gbc);
        buttonPanel.add(usuarioButton, gbc);
        buttonPanel.add(agenteButton, gbc);
        buttonPanel.add(vacinaButton, gbc);
        buttonPanel.add(agendamentoButton, gbc);
        buttonPanel.add(historicoButton, gbc);
        buttonPanel.add(agendaButton, gbc);

        idosoButton.addActionListener(e -> idosoINvocar());
        usuarioButton.addActionListener(e -> usuarioINvocar());
        agenteButton.addActionListener(e -> agenteINvocar());
        vacinaButton.addActionListener(e -> vacinaINvocar());
        agendamentoButton.addActionListener(e -> agemdamentoINvocar());
        historicoButton.addActionListener(e -> historicoINvocar());
        agendaButton.addActionListener(e -> agendamentoNotificação());

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void agenteINvocar() {
        SwingUtilities.invokeLater(() -> {
            var form = new AgenteDeSaudeForm();
            form.setVisible(true);
        });
    }
    public static void idosoINvocar() {
        SwingUtilities.invokeLater(() -> {
            var form = new IdosoForm();
            form.setVisible(true);
        });


    }
    public static void vacinaINvocar() {
        SwingUtilities.invokeLater(() -> {
            var form = new Vacinaform();
            form.setVisible(true);
        });


    }

    public static void agemdamentoINvocar() {
        SwingUtilities.invokeLater(() -> {
            var form = new AgendamentoForm();
            form.setVisible(true);
        });
    }

        public static void historicoINvocar() {
            SwingUtilities.invokeLater(() -> {
                var form = new HistoricoView();
                form.setVisible(true);
            });

    }

    public static void agendamentoNotificação() {
        SwingUtilities.invokeLater(() -> {
            var form = new AgendamentoView();
            form.setVisible(true);
        });

    }

    public static void usuarioINvocar() {
        SwingUtilities.invokeLater(() -> {
            var form = new UsuarioForm();
            form.setVisible(true);
        });

    }
}


