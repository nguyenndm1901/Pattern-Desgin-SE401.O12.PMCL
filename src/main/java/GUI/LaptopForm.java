/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Builder.LaptopBuilder;
import Entities.Laptop;
import Enum.LaptopOS;

import Database.Connection;
import Factory.LaptopFactory;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Nguyen
 */
public class LaptopForm extends javax.swing.JFrame {

    /**
     * Creates new form LaptopForm
     */
    public LaptopForm() {
        initComponents();
        initTable();
        initComboBox();
    }

    public MongoCollection<Document> laptopList() {
        return Connection.getDatabase().getCollection("laptop");
    }

    private static String randomCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        int length = 10;

        StringBuilder randomCode = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i< length; i++) {
            int index = random.nextInt(characters.length());
            randomCode.append(characters.charAt(index));
        }

        return randomCode.toString();
    }

    private boolean check(String code) {
        MongoCollection<Document> collection = laptopList();
        Document document = new Document("code", code);
        try (MongoCursor<Document> cursor = collection.find(document).iterator()) {
            return cursor.hasNext();
        }
    }
    
    public void initTable() {
        MongoCollection<Document> collection = laptopList();
        DefaultTableModel model = (DefaultTableModel) tblLaptop.getModel();
        model.setRowCount(0);

        FindIterable<Document> documents = collection.find();

        for (Document document : documents) {
            Object[] row = {
                    document.getString("code"),
                    document.getString("name"),
                    document.getString("brand"),
                    document.getString("processor"),
                    document.getString("memory"),
                    document.getString("storage"),
                    document.getInteger("price"),
            };
            model.addRow(row);
        }

        tblLaptop.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tblLaptop.getSelectedRow();
                    if (selectedRow != -1) {
                        populateFieldsFromSelectedRow(selectedRow);
                    }
                }
            }
        });
    }

    private void populateFieldsFromSelectedRow(int selectedRow) {
        DefaultTableModel model = (DefaultTableModel) tblLaptop.getModel();
        txtCode.setText(model.getValueAt(selectedRow,0).toString());
        txtName.setText(model.getValueAt(selectedRow,1).toString());
        txtBrand.setText(model.getValueAt(selectedRow,2).toString());
        cbOS.setSelectedItem(determineOSFromProcessor(model.getValueAt(selectedRow,3).toString()));
        cbProcessor.setSelectedItem(model.getValueAt(selectedRow,3).toString());
        txtMemory.setText(model.getValueAt(selectedRow,4).toString());
        txtStorage.setText(model.getValueAt(selectedRow,5).toString());
        txtPrice.setText(model.getValueAt(selectedRow,6).toString());
    }

    private void initComboBox() {
        cbOS.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updateProcessorComboBox((String) cbOS.getSelectedItem());
                }
            }
        });
    }

    private void updateProcessorComboBox(String selectedOS) {
        cbProcessor.removeAllItems();
        MongoCollection<Document> collection = Connection.getDatabase().getCollection("part");
        FindIterable<Document> documents = collection.find(new Document("os", selectedOS));
        MongoCursor<Document> cursor = documents.iterator();
        Set<String> uniqueProcessorValues = new HashSet<>();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            if (document.containsKey("name")) {
                Object processorValue = document.get("name");
                uniqueProcessorValues.add(String.valueOf(processorValue));
            }
        }
        for (String value : uniqueProcessorValues) {
            cbProcessor.addItem(value);
        }
    }

    private String determineOSFromProcessor(String processor) {
        if (processor.contains("Intel") || processor.contains("AMD")) return "Windows";
        else if (processor.contains("Apple")) return "MacOS";
        else return "";
    }

//    private void initComboBox() {
//        Set<String> uniqueFieldValues = getUniqueFiledValues("part", "name");
//
//        for (String value : uniqueFieldValues) {
//            cbProcessor.addItem(value);
//        }
//    }

//    private Set<String> getUniqueFiledValues(String collectionName, String fieldName) {
//        Set<String> uniqueValues = new HashSet<>();
//        MongoCollection<Document> collection = Connection.getDatabase().getCollection(collectionName);
//        FindIterable<Document> documents = collection.find();
//        MongoCursor<Document> cursor = documents.iterator();
//
//        while (cursor.hasNext()) {
//            Document document = cursor.next();
//            if (document.containsKey(fieldName)) {
//                Object fieldValue = document.get(fieldName);
//                uniqueValues.add(String.valueOf(fieldValue));
//            }
//        }
//        return uniqueValues;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblLaptop = new javax.swing.JTable();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtCode = new javax.swing.JTextField();
        txtBrand = new javax.swing.JTextField();
        txtMemory = new javax.swing.JTextField();
        txtStorage = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        btnAddOK = new javax.swing.JButton();
        btnUpdateOK = new javax.swing.JButton();
        btnDeleteOK = new javax.swing.JButton();
        cbProcessor = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbOS = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuReceipt = new javax.swing.JMenu();
        menuStatistic = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblLaptop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name", "Brand", "Processor", "Memory", "Storage", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLaptop.setColumnSelectionAllowed(true);
        tblLaptop.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblLaptop);
        tblLaptop.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        lblSearch.setText("Search:");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jPanel1.setEnabled(false);
        jPanel1.setEnabled(false);

        jLabel1.setText("Code:");

        jLabel2.setText("Name:");

        jLabel3.setText("Brand:");

        jLabel4.setText("Processor:");

        jLabel5.setText("Memory:");

        jLabel6.setText("Storage:");

        jLabel7.setText("Price:");

        txtName.setEnabled(false);

        txtCode.setEnabled(false);

        txtBrand.setEnabled(false);

        txtMemory.setEnabled(false);

        txtStorage.setEnabled(false);

        txtPrice.setEnabled(false);

        btnAddOK.setText("OK");
        btnAddOK.setEnabled(false);
        btnAddOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddOKActionPerformed(evt);
            }
        });

        btnUpdateOK.setText("OK");
        btnUpdateOK.setEnabled(false);
        btnUpdateOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateOKActionPerformed(evt);
            }
        });

        btnDeleteOK.setText("OK");
        btnDeleteOK.setEnabled(false);
        btnDeleteOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteOKActionPerformed(evt);
            }
        });

        cbProcessor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        cbProcessor.setEnabled(false);
        cbProcessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProcessorActionPerformed(evt);
            }
        });

        jLabel8.setText("OS");

        cbOS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Windows", "MacOS" }));
        cbOS.setEnabled(false);
        cbOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAddOK, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnUpdateOK, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeleteOK, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                            .addComponent(txtPrice)
                            .addComponent(txtStorage)
                            .addComponent(txtMemory)
                            .addComponent(txtBrand, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                            .addComponent(txtCode, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                            .addComponent(cbProcessor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbOS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbOS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbProcessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMemory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtStorage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddOK)
                    .addComponent(btnUpdateOK)
                    .addComponent(btnDeleteOK))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        menuReceipt.setText("Receipt");
        jMenuBar1.add(menuReceipt);

        menuStatistic.setText("Statistic");
        jMenuBar1.add(menuStatistic);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblSearch)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSearch)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearch)
                            .addComponent(btnClear))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnUpdate)
                            .addComponent(btnDelete))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        tblLaptop.setAutoCreateRowSorter(true);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblLaptop.getModel());
        tblLaptop.setRowSorter(sorter);
        String text = txtSearch.getText();
        if (text.length() == 0) sorter.setRowFilter(null);
        else sorter.setRowFilter(RowFilter.regexFilter(text));
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        tblLaptop.setAutoCreateRowSorter(true);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblLaptop.getModel());
        tblLaptop.setRowSorter(sorter);
        txtSearch.setText("");
        sorter.setRowFilter(null);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        jPanel1.setEnabled(true);
        txtCode.setEnabled(true);
        txtCode.setEditable(false);
        txtName.setEnabled(true);
        txtBrand.setEnabled(true);
        cbOS.setEnabled(true);
        cbProcessor.setEnabled(true);
        txtMemory.setEnabled(true);
        txtStorage.setEnabled(true);
        txtPrice.setEnabled(true);
        btnAddOK.setEnabled(true);
        btnUpdateOK.setEnabled(false);
        btnDeleteOK.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        String code;
        do {
            code = randomCode();
        } while (check(code));
        txtCode.setText(code);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        jPanel1.setEnabled(true);
        txtCode.setEnabled(true);
        txtCode.setEditable(false);
        txtName.setEnabled(true);
        txtBrand.setEnabled(true);
        cbOS.setEnabled(true);
        cbProcessor.setEnabled(true);
        txtMemory.setEnabled(true);
        txtStorage.setEnabled(true);
        txtPrice.setEnabled(true);
        btnAddOK.setEnabled(false);
        btnUpdateOK.setEnabled(true);
        btnDeleteOK.setEnabled(false);
        btnAdd.setEnabled(false);
        btnDelete.setEnabled(false);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        jPanel1.setEnabled(true);
        txtCode.setEnabled(true);
        txtCode.setEditable(false);
        txtName.setEnabled(false);
        txtBrand.setEnabled(false);
        cbOS.setEnabled(false);
        cbProcessor.setEnabled(false);
        txtMemory.setEnabled(false);
        txtStorage.setEnabled(false);
        txtPrice.setEnabled(false);
        btnAddOK.setEnabled(false);
        btnUpdateOK.setEnabled(false);
        btnDeleteOK.setEnabled(true);
        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(false);
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddOKActionPerformed
        String code = txtCode.getText();
        String name = txtName.getText();
        LaptopOS os = LaptopOS.valueOf(Objects.requireNonNull(cbOS.getSelectedItem()).toString());
        String brand = txtBrand.getText();
        String processor = Objects.requireNonNull(cbProcessor.getSelectedItem()).toString();
        String memory = txtMemory.getText();
        String storage = txtStorage.getText();
        int price = Integer.parseInt(txtPrice.getText());

        LaptopBuilder laptopBuilder = LaptopFactory.createLaptop(os);
        Laptop laptop = laptopBuilder
                .setCode(code).setName(name)
                .setBrand(brand).setProcessor(processor)
                .setMemory(memory).setStorage(storage)
                .setPrice(price).build();

        MongoCollection<Document> laptopCollection = Connection.getDatabase().getCollection("laptop");

        Document laptopDocument = new Document()
                .append("code", laptop.getCode())
                .append("name", laptop.getName())
                .append("brand", laptop.getBrand())
                .append("processor", laptop.getProcessor())
                .append("memory", laptop.getMemory())
                .append("storage", laptop.getStorage())
                .append("price", laptop.getPrice());

        laptopCollection.insertOne(laptopDocument);

        initTable();
        clearState();

    }//GEN-LAST:event_btnAddOKActionPerformed

    private void btnUpdateOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateOKActionPerformed
        String code = txtCode.getText();
        String name = txtName.getText();
        LaptopOS os = LaptopOS.valueOf(Objects.requireNonNull(cbOS.getSelectedItem()).toString());
        String brand = txtBrand.getText();
        String processor = Objects.requireNonNull(cbProcessor.getSelectedItem()).toString();
        String memory = txtMemory.getText();
        String storage = txtStorage.getText();
        int price = Integer.parseInt(txtPrice.getText());

        LaptopBuilder laptopBuilder = LaptopFactory.createLaptop(os);
        Laptop laptop = laptopBuilder
                .setCode(code).setName(name)
                .setBrand(brand).setProcessor(processor)
                .setMemory(memory).setStorage(storage)
                .setPrice(price).build();

        MongoCollection<Document> laptopCollection = Connection.getDatabase().getCollection("laptop");

        Document laptopDocument = new Document()
                .append("code", laptop.getCode())
                .append("name", laptop.getName())
                .append("brand", laptop.getBrand())
                .append("processor", laptop.getProcessor())
                .append("memory", laptop.getMemory())
                .append("storage", laptop.getStorage())
                .append("price", laptop.getPrice());

        laptopCollection.replaceOne(Filters.eq("code", laptop.getCode()), laptopDocument, new UpdateOptions().upsert(true));

        initTable();
        clearState();
    }//GEN-LAST:event_btnUpdateOKActionPerformed

    private void btnDeleteOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteOKActionPerformed
        String code = txtCode.getText();

        MongoCollection<Document> collection = Connection.getDatabase().getCollection("laptop");

        collection.deleteOne(Filters.eq("code", code));

        initTable();
        clearState();
    }//GEN-LAST:event_btnDeleteOKActionPerformed

    private void cbProcessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProcessorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbProcessorActionPerformed

    private void cbOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbOSActionPerformed

    private void clearState() {
        jPanel1.setEnabled(false);
        txtCode.setEnabled(false);
        txtCode.setText("");
        txtName.setEnabled(false);
        txtName.setText("");
        cbOS.setSelectedIndex(0);
        cbOS.setEnabled(false);
        txtBrand.setEnabled(false);
        txtBrand.setText("");
        cbProcessor.setSelectedIndex(0);
        cbProcessor.setEnabled(false);
        txtMemory.setEnabled(false);
        txtMemory.setText("");
        txtStorage.setEnabled(false);
        txtStorage.setText("");
        txtPrice.setEnabled(false);
        txtPrice.setText("");
        btnAddOK.setEnabled(false);
        btnUpdateOK.setEnabled(false);
        btnDeleteOK.setEnabled(false);
        btnAdd.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LaptopForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LaptopForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LaptopForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LaptopForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LaptopForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddOK;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteOK;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateOK;
    private javax.swing.JComboBox<String> cbOS;
    private javax.swing.JComboBox<String> cbProcessor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JMenu menuReceipt;
    private javax.swing.JMenu menuStatistic;
    private javax.swing.JTable tblLaptop;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtMemory;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStorage;
    // End of variables declaration//GEN-END:variables
}
