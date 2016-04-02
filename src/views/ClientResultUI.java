package views;

import controllerBeans.ClientLoginBean;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by danielchoi on 2016-04-02.
 */
public class ClientResultUI extends JPanel {
    private JFrame parent;
    private ClientLoginBean bean;
    private JTable resultTable;
    private int selectedDelivery = 0;
    private int cID;
    private JButton paymentButton = new JButton("Payment info");
    public ClientResultUI(JFrame f, ClientLoginBean bean, int cID) {
        parent = f;
        this.bean = bean;
        setLayout(new BorderLayout());
        setBorder(new TitledBorder(
                new EtchedBorder(), "Query results"));
        resultTable = bean.getClientIDQueryAsJTable(cID);
        JScrollPane tablePane = new JScrollPane(resultTable);
        add(tablePane);
        paymentButton.addActionListener(new ButtonHandler());
        add(paymentButton, BorderLayout.SOUTH);
        resultTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) {
                    System.out.println(resultTable.getValueAt(resultTable.getSelectedRow(), 0).toString());
                    selectedDelivery = Integer.parseInt(resultTable.getValueAt(resultTable.getSelectedRow(), 0).toString());
                }
            }
        });
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Fetch payment info":
                    if (selectedDelivery == 0) {
                        JOptionPane.showMessageDialog(null,
                                "Please select a delivery");
                    } else {
                        JFrame child = new JFrame();
                        child.add(bean.getPaymentAsPanel(selectedDelivery));
                        child.pack();
                        child.setVisible(true);
                    }
            }

        }
    }

    public int getTableWidth() {
        return resultTable.getPreferredSize().width+20;
    }
}
