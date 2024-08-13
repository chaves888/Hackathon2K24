package Vacina.view;

import Vacina.model.Historico;
import Vacina.service.HistoricoService;
import Vacina.service.IdosoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class HistoricoView extends JFrame {
    private JTable tabelaHostorico;
    private HistoricoService service;

    public HistoricoView(){
    service = new HistoricoService();

        setTitle("Historico do idoso");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(500, 600);

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(5, 5, 5, 5);
        JPanel painelSaida = new JPanel(new BorderLayout());

        tabelaHostorico = new JTable();
        tabelaHostorico.setModel(carregarDadosHistorico());
       // tabelaHostorico.getSelectionModel().addListSelectionListener(e -> selecionarHistorico(e));
        tabelaHostorico.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(tabelaHostorico);
        painelSaida.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(painelEntrada, BorderLayout.NORTH);
        getContentPane().add(painelSaida, BorderLayout.CENTER);

        setLocationRelativeTo(null);

}
    private DefaultTableModel carregarDadosHistorico() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Observação");


        service.listarHistorico().forEach(historico -> model.addRow(new Object[]{
                        historico.getNomeIdoso(),
                        historico.getObservacao(),
                })
        );
        return model;
    }
}


