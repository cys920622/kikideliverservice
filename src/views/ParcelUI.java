package views;

import controllerBeans.ParcelBean;
import entityClasses.Parcel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by stellafang. on 2016-03-26.
 */
public class ParcelUI extends JPanel{
    private JTextField pIDField = new JTextField(6);
    private JTextField lengthField = new JTextField(10);
    private JTextField widthField = new JTextField(10);
    private JTextField heightField = new JTextField(10);
    private JTextField weightField = new JTextField(10);
    private JTextField dIDField = new JTextField(6);
    private JTextField cIDField = new JTextField(6);
    private JTextField next_cIDField = new JTextField(15);

    private JButton createButton = new JButton("New...");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
    private JButton firstButton = new JButton("First");
    private JButton lastButton = new JButton("Last");
    private JButton nextButton = new JButton("Next");
    private JButton previousButton = new JButton("Previous");

    private int dID = new Random().nextInt((999999-0) +1);

    private ParcelBean bean = new ParcelBean();

    //takes in rand int that is the same as Delivery's dID
    public ParcelUI() {
        setBorder(new TitledBorder(
                new EtchedBorder(), "Parcel details"));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);


        pIDField.setText(String.valueOf(0));
        lengthField.setText(String.valueOf(0.0));
        widthField.setText(String.valueOf(0.0));
        heightField.setText(String.valueOf(0.0));
        weightField.setText(String.valueOf(0.0));
        dIDField.setText(String.valueOf(0));
        cIDField.setText("");
        next_cIDField.setText("");


    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        panel.add(createButton);
        createButton.addActionListener(new ButtonHandler());
        panel.add(updateButton);
        updateButton.addActionListener(new ButtonHandler());
        panel.add(deleteButton);
        deleteButton.addActionListener(new ButtonHandler());
        panel.add(firstButton);
        firstButton.addActionListener(new ButtonHandler());
        panel.add(lastButton);
        lastButton.addActionListener(new ButtonHandler());
        panel.add(nextButton);
        nextButton.addActionListener(new ButtonHandler());
        panel.add(previousButton);
        previousButton.addActionListener(new ButtonHandler());
        return panel;
    }

    private JPanel initFields() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(new JLabel("Parcel ID"), "align label");
        panel.add(pIDField, "wrap");
        panel.add(new JLabel("Length"), "align label");
        panel.add(lengthField, "wrap");
        panel.add(new JLabel("Width"), "align label");
        panel.add(widthField, "wrap");
        panel.add(new JLabel("Height"), "align label");
        panel.add(heightField, "wrap");
        panel.add(new JLabel("Weight"), "align label");
        panel.add(weightField, "wrap");
        panel.add(new JLabel("Delivery ID"), "align label");
        panel.add(dIDField, "wrap");
        panel.add(new JLabel("Center ID"), "align label");
        panel.add(cIDField, "wrap");
        panel.add(new JLabel("Next Center ID"), "align label");
        panel.add(next_cIDField, "wrap");
        return panel;
    }

    private Parcel getFieldData() {
        Parcel p = new Parcel();
        p.setpID(Integer.parseInt(pIDField.getText()));
        p.setLength(Float.parseFloat(lengthField.getText()));
        p.setWidth(Float.parseFloat(weightField.getText()));
        p.setHeight(Float.parseFloat(heightField.getText()));
        p.setWeight(Float.parseFloat(weightField.getText()));
        p.setdID(Integer.parseInt(dIDField.getText()));
        p.setcID(cIDField.getText());
        p.setNextcID(next_cIDField.getText());
        return p;
    }

    private void setFieldData(Parcel p) {
        pIDField.setText(String.valueOf(p.getpID()));
        lengthField.setText(String.valueOf(p.getLength()));
        widthField.setText(String.valueOf(p.getWidth()));
        heightField.setText(String.valueOf(p.getHeight()));
        weightField.setText(String.valueOf(p.getWeight()));
        dIDField.setText(String.valueOf(p.getdID()));
        cIDField.setText(p.getcID());
        next_cIDField.setText(p.getNextcID());
    }

    private boolean isEmptyFieldData() {
        return (pIDField.getText().trim().isEmpty()
                && lengthField.getText().trim().isEmpty()
                && widthField.getText().trim().isEmpty()
                && heightField.getText().trim().isEmpty()
                && weightField.getText().trim().isEmpty()
                && dIDField.getText().trim().isEmpty()
                && cIDField.getText().trim().isEmpty()
                && next_cIDField.getText().trim().isEmpty());
    }

    public void setdID(int dID) {
        this.dID = dID;
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Parcel p = getFieldData();
            switch (e.getActionCommand()) {
                case "Save":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot create empty record");
                        break;
                    }
                    if (bean.create(p, p.getcID(), p.getNextcID()) != null) {
                        JOptionPane.showMessageDialog(null,
                                "New Parcel " + String.valueOf(p.getpID()) +
                                        " was created for Delivery " + String.valueOf(p.getdID())
                                        + " was created.");
                        createButton.setText("New...");
                        break;
                    }
                    else {
                        JOptionPane.showMessageDialog(null,
                                "invalid Center ID was submitted");
                        break;
                    }
                case "New...":
                    p.setpID(new Random().nextInt((999999-0) +1));
                    p.setLength(0);
                    p.setWidth(0);
                    p.setHeight(0);
                    p.setWeight(0);
                    p.setdID(dID);
                    p.setcID("");
                    p.setNextcID("");
                    setFieldData(p);
                    createButton.setText("Save");
                    break;

                case "Update":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Can't update empty record");
                        break;
                    }
                    if (bean.update(p, p.getcID(), p.getNextcID()) != null) {
                        JOptionPane.showMessageDialog(null,
                                "Parcel " + String.valueOf(p.getpID()) +
                                        " for Delivery " + String.valueOf(p.getdID())
                                        + " was updated.");
                        break;
                    }
                    else {
                        JOptionPane.showMessageDialog(null,
                                "invalid Center ID was submitted");
                        break;
                    }
                case "Delete":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Can't delete empty record");
                        break;
                    }
                    else {
                        p = bean.getCurrent();
                        bean.delete();
                        JOptionPane.showMessageDialog(null,
                                "Parcel " + String.valueOf(p.getpID()) +
                                        " for Delivery " + String.valueOf(p.getdID())
                                        + " was deleted.");
                        break;
                    }
                case "First":
                    setFieldData(bean.moveFirst());
                    break;
                case "Last":
                    setFieldData(bean.moveLast());
                    break;
                case "Next":
                    setFieldData(bean.moveNext());
                    break;
                case "Previous":
                    setFieldData(bean.movePrevious());
                default:
                    JOptionPane.showMessageDialog(null,
                            "Invaild command");
            }
        }
    }
}
