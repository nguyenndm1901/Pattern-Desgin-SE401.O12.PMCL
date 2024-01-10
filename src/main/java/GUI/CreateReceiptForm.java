package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import Database.Connection;
import Decorator.ILaptopDecorator;
import Decorator.WarrantyBuyServiceDecorator;
import Entities.*;
import Strategy.CardPaymentStrategy;
import Strategy.CashPaymentStrategy;
import Strategy.IPaymentStrategy;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Nguyen
 */
public class CreateReceiptForm extends javax.swing.JFrame {

    /**
     * Creates new form CreateReceiptForm
     */

    private String staffId;
    private String laptopId;
    private String serviceId;
    private Laptop laptop;
    private Service service;
    private int cartTotal;
    private String customerId;
    private List<String> laptopList = new ArrayList<>();

    private ILaptopDecorator laptopWithService;

    private List<ReceiptDetail> receiptDetails = new ArrayList<>();

    public CreateReceiptForm() {
        initComponents();
        initCBService();
        initCBLaptop();
        initCBStaff();
    }

    public MongoCollection<Document> cartList() {
        return Connection.getDatabase().getCollection("cart");
    }

    public MongoCollection<Document> receiptList() {
        return Connection.getDatabase().getCollection("receipt");
    }

    public MongoCollection<Document> receiptDetailList() {
        return Connection.getDatabase().getCollection("receipt_detail");
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

    private boolean checkCode(String code, String collectionName) {
        MongoCollection<Document> collection = Connection.getDatabase().getCollection(collectionName);
        Document document = new Document("code", code);
        try (MongoCursor<Document> cursor = collection.find(document).iterator()) {
            return cursor.hasNext();
        }
    }

    private void setPurchaseDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(date);
        txtPurchaseDate.setText(strDate);
    }

    private String setCode(String collectionName) {
        String code;
        do {
            code = randomCode();
        } while (checkCode(code, collectionName));
        return code;
    }

    private void initCart() {
        MongoCollection<Document> collection = cartList();
        DefaultTableModel model = (DefaultTableModel) tblCart.getModel();
        model.setRowCount(0);
        FindIterable<Document> documents = collection.find();
        for (Document document : documents) {
            Object[] row = {
                    document.getString("name"),
                    document.getInteger("quantity"),
                    document.getInteger("unitPrice"),
                    document.getString("service"),
                    document.getInteger("total")
            };
            model.addRow(row);
        }
        Document result = collection.aggregate(Arrays.asList(
                Aggregates.group(null, Accumulators.sum("total", "$total"))
        )).first();
        if (result != null) cartTotal = result.getInteger("total");
        else cartTotal = 0;
        txtTotal.setText(String.valueOf(cartTotal));
    }

    private void initCBService() {
        MongoCollection<Document> collection = Connection.getDatabase().getCollection("service");
        FindIterable<Document> documents = collection.find();
        for (Document document : documents) {
            Service service = new Service(
                    document.get("code").toString(),
                    document.get("name").toString(),
                    Integer.parseInt(document.get("warranty").toString()),
                    Integer.parseInt(document.get("price").toString())
                    );
            cbService.addItem(service);
        }
    }

    private void initCBLaptop() {
        MongoCollection<Document> collection = Connection.getDatabase().getCollection("laptop");
        FindIterable<Document> documents = collection.find();
        for (Document document : documents) {
            Laptop laptop = new Laptop(
                    document.get("code").toString(),
                    document.get("name").toString(),
                    document.get("brand").toString(),
                    document.get("processor").toString(),
                    document.get("memory").toString(),
                    document.get("storage").toString(),
                    Integer.parseInt(document.get("warranty").toString()),
                    Integer.parseInt(document.get("price").toString())
            );
            cbLaptop.addItem(laptop);
        }
    }

    private void initCBStaff() {
        MongoCollection<Document> collection = Connection.getDatabase().getCollection("staff");
        FindIterable<Document> documents = collection.find();
        for (Document document : documents) {
            Staff staff = new Staff(
                    document.get("code").toString(),
                    document.get("name").toString(),
                    document.get("phone").toString(),
                    document.get("role").toString()
            );
            cbStaff.addItem(staff);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPurchaseDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbStaff = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbLaptop = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtUnitPrice = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnCheckout = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        btnRemove = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cbService = new javax.swing.JComboBox<>();
        txtWarranty = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbPayment = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnLaptop = new javax.swing.JMenu();
        mnReceipt = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create Receipt");

        jLabel1.setText("Code:");

        txtCode.setEditable(false);

        jLabel2.setText("Purchase Date:");

        txtPurchaseDate.setEditable(false);

        jLabel3.setText("Customer Name:");

        txtCustomerName.setEnabled(false);

        jLabel4.setText("Phone Number:");

        txtPhone.setEnabled(false);

        jLabel5.setText("Staff:");

        cbStaff.setModel(new javax.swing.DefaultComboBoxModel<>(new Staff[] {  }));
        cbStaff.setEnabled(false);
        cbStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStaffActionPerformed(evt);
            }
        });

        jLabel6.setText("Laptop:");

        cbLaptop.setModel(new javax.swing.DefaultComboBoxModel<>(new Laptop[] {  }));
        cbLaptop.setEnabled(false);
        cbLaptop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLaptopActionPerformed(evt);
            }
        });

        jLabel7.setText("Qt:");

        txtQuantity.setText("1");
        txtQuantity.setEnabled(false);

        jLabel8.setText("Unit Price:");

        txtUnitPrice.setEditable(false);
        txtUnitPrice.setEnabled(false);

        btnAdd.setText("Add to Cart");
        btnAdd.setEnabled(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnCheckout.setText("Checkout");
        btnCheckout.setEnabled(false);
        btnCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckoutActionPerformed(evt);
            }
        });

        btnNew.setText("New Receipt");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Product", "Quantity", "Unit Price", "Service", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCart.setColumnSelectionAllowed(true);
        tblCart.setEnabled(false);
        tblCart.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblCart);
        tblCart.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnRemove.setText("Reset Cart");
        btnRemove.setEnabled(false);
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        jLabel12.setText("Total:");

        txtTotal.setEditable(false);
        txtTotal.setEnabled(false);

        jLabel13.setText("Service:");

        cbService.setModel(new javax.swing.DefaultComboBoxModel<>(new Service[] {  }));
        cbService.setEnabled(false);
        cbService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbServiceActionPerformed(evt);
            }
        });

        txtWarranty.setEditable(false);
        txtWarranty.setEnabled(false);

        jLabel14.setText("Warranty:");

        jLabel9.setText("months");

        cbPayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- None --", "Cash", "Card" }));
        cbPayment.setEnabled(false);

        jLabel10.setText("Payment Method:");

        mnLaptop.setText("Laptop");
        mnLaptop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnLaptopActionPerformed(evt);
            }
        });
        jMenuBar1.add(mnLaptop);

        mnReceipt.setText("Receipt");
        jMenuBar1.add(mnReceipt);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel14))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtPurchaseDate))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cbStaff, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbLaptop, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtWarranty)
                                    .addComponent(txtUnitPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbService, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txtCustomerName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPurchaseDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbLaptop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel7))
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(cbService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtWarranty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel9)
                            .addComponent(cbPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        btnNew.setEnabled(false);
        btnAdd.setEnabled(true);
        btnRemove.setEnabled(true);
        btnCheckout.setEnabled(true);
        txtCustomerName.setEnabled(true);
        txtPhone.setEnabled(true);
        cbStaff.setEnabled(true);
        cbLaptop.setEnabled(true);
        cbService.setEnabled(true);
        cbPayment.setEnabled(true);
        txtQuantity.setEnabled(true);
        txtUnitPrice.setEnabled(true);
        txtWarranty.setEnabled(true);
        tblCart.setEnabled(true);
        txtCode.setText(setCode("receipt"));
        setPurchaseDate();


    }//GEN-LAST:event_btnNewActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        MongoCollection<Document> collection = cartList();
        if (collection == null) JOptionPane.showMessageDialog(null, "Please choose at least one laptop");
        if (txtCustomerName.getText().trim().isEmpty()) JOptionPane.showMessageDialog(null, "Please enter customer name");
        if (txtPhone.getText().trim().isEmpty()) JOptionPane.showMessageDialog(null, "Please enter phone number");
        if (Integer.parseInt(txtQuantity.getText()) < 1) JOptionPane.showMessageDialog(null, "Please enter valid quantity");
        if (cbStaff.getSelectedIndex() < 0) JOptionPane.showMessageDialog(null, "Please choose a valid staff");
        if (cbLaptop.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(null, "Please choose a valid laptop");
            return;
        }
        if (cbService.getSelectedIndex() < 0) JOptionPane.showMessageDialog(null, "Please choose a valid service");
        if (!checkInt(txtQuantity.getText())) JOptionPane.showMessageDialog(null, "Please enter valid quantity");
        if (Integer.parseInt(txtQuantity.getText()) < 1) JOptionPane.showMessageDialog(null, "Please enter valid quantity");
        int quantity = Integer.parseInt(txtQuantity.getText());

        laptop = (Laptop) cbLaptop.getSelectedItem();
        laptopWithService = new WarrantyBuyServiceDecorator(laptop, service.getWarranty(), service.getPrice());
        int newPrice = laptopWithService.getPrice();
        int newWarranty = laptopWithService.getWarranty();

        MongoCollection<Document> customers = Connection.getDatabase().getCollection("customer");

        try {
            Document findCustomer = customers.find(new Document("name", txtCustomerName.getText()).append("phone", txtPhone.getText())).first();

            if (findCustomer != null) {
                customerId = findCustomer.getString("code");
            } else {
                customerId = setCode("customer");
                Customer customer = new Customer(customerId, txtCustomerName.getText(), txtPhone.getText());

                Document customerDocument = new Document()
                        .append("code", customer.getCode())
                        .append("name", customer.getName())
                        .append("phone", customer.getPhone());

                customers.replaceOne(
                        Filters.and(
                                Filters.eq("name", customer.getName()),
                                Filters.eq("phone", customer.getPhone())
                        ),
                        customerDocument,
                        new UpdateOptions().upsert(true)
                );
            }
        } catch (MongoException e) {
            // Handle MongoDB-related exceptions
            JOptionPane.showMessageDialog(null, e.getMessage()); // Replace with appropriate error handling
        }


        Cart cart = new Cart(laptop.getName(), quantity, laptop.getPrice(), service.getCode(), service.getName(), newPrice * quantity);
        Document document = new Document()
                .append("name", cart.getName())
                .append("quantity", cart.getQuantity())
                .append("unitPrice", cart.getUnitPrice())
                .append("serviceId", cart.getServiceId())
                .append("service", cart.getService())
                .append("total", cart.getTotal());

        collection.insertOne(document);

        ReceiptDetail detail = new ReceiptDetail(txtCode.getText(), laptop.getCode(), quantity, service.getCode(), newWarranty, cart.getTotal());
        receiptDetails.add(detail);
        laptopList.add(laptop.getCode());


        initCart();
        clearState();
    }//GEN-LAST:event_btnAddActionPerformed

    private void clearState() {
        txtCustomerName.setEnabled(false);
        txtPhone.setEnabled(false);
        cbStaff.setEnabled(false);
        cbLaptop.setSelectedIndex(0);
        txtUnitPrice.setText("");
        cbService.setSelectedIndex(0);
        txtWarranty.setText("");
    }

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        String code = txtCode.getText();
        String purchaseDate = txtPurchaseDate.getText();

        String paymentType = "";

        if (Objects.equals(cbPayment.getSelectedItem(), "-- None --")) JOptionPane.showMessageDialog(null, "Please choose a payment method");
        if (cbPayment.getSelectedItem() == "Cash") {
            IPaymentStrategy paymentStrategy = new CashPaymentStrategy();
            paymentType = paymentStrategy.paymentType();
        }
        if (cbPayment.getSelectedItem() == "Card") {
            IPaymentStrategy paymentStrategy = new CardPaymentStrategy();
            paymentType = paymentStrategy.paymentType();
        }

        Receipt receipt = new Receipt(code, customerId, staffId, purchaseDate, cartTotal, laptopList, paymentType);

        MongoCollection<Document> receiptList = receiptList();
        Document document = new Document()
                .append("code", receipt.getCode())
                .append("customerId", receipt.getCustomerId())
                .append("staffId", receipt.getStaffId())
                .append("purchaseDate", receipt.getPurchaseDate())
                .append("cartTotal", receipt.getTotal())
                .append("laptopList", receipt.getLaptopList())
                .append("paymentType", receipt.getPaymentType());
        receiptList.insertOne(document);

        MongoCollection<Document> detailsList = receiptDetailList();
        List<Document> documents = new ArrayList<>();
        for (ReceiptDetail detail : receiptDetails) {
            Document detailDocument = new Document()
                    .append("receiptId", detail.getReceiptId())
                    .append("productId", detail.getProductId())
                    .append("amount", detail.getAmount())
                    .append("serviceCode", detail.getServiceCode())
                    .append("warrantyTime", detail.getWarrantyTime())
                    .append("total", detail.getTotal());
            documents.add(detailDocument);
        }
        detailsList.insertMany(documents);

        clearCart();
        clearForm();
    }//GEN-LAST:event_btnCheckoutActionPerformed

    private void clearCart() {
        MongoCollection<Document> collection = cartList();
        collection.deleteMany(new Document());
        initCart();
        laptopList = new ArrayList<>();
        receiptDetails = new ArrayList<>();
    }

    private void clearForm() {
        btnNew.setEnabled(true);
        btnAdd.setEnabled(false);
        btnCheckout.setEnabled(false);
        btnRemove.setEnabled(false);
        txtCustomerName.setEnabled(false);
        txtPhone.setEnabled(false);
        cbStaff.setEnabled(false);
        cbLaptop.setEnabled(false);
        cbService.setEnabled(false);
        cbPayment.setEnabled(false);
        txtQuantity.setEnabled(false);
        txtUnitPrice.setEnabled(false);
        txtWarranty.setEnabled(false);
        tblCart.setEnabled(false);

        cbStaff.setSelectedIndex(-1);
        cbLaptop.setSelectedIndex(-1);
        cbService.setSelectedIndex(0);
        cbPayment.setSelectedIndex(0);
        txtCode.setText("");
        txtPurchaseDate.setText("");
        txtCustomerName.setText("");
        txtPhone.setText("");
        txtUnitPrice.setText("");
        txtWarranty.setText("");
    }

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        clearCart();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void cbServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbServiceActionPerformed
        Service selectedService = (Service) cbService.getSelectedItem();
        service = (Service) cbService.getSelectedItem();
        serviceId = selectedService.getCode();
        System.out.println(serviceId);
    }//GEN-LAST:event_cbServiceActionPerformed

    private void cbLaptopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLaptopActionPerformed
        Laptop selectedLaptop = (Laptop) cbLaptop.getSelectedItem();
        laptop = selectedLaptop;
        laptopId = selectedLaptop.getCode();
        txtUnitPrice.setText(String.valueOf(selectedLaptop.getPrice()));
        txtWarranty.setText(String.valueOf(selectedLaptop.getWarranty()));
        System.out.println(laptopId);
    }//GEN-LAST:event_cbLaptopActionPerformed

    private void cbStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStaffActionPerformed
        Staff selectedStaff = (Staff) cbStaff.getSelectedItem();
        staffId = selectedStaff.getCode();
        System.out.println(staffId);
    }//GEN-LAST:event_cbStaffActionPerformed

    private void mnLaptopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnLaptopActionPerformed
        LaptopForm laptopForm = new LaptopForm();
        laptopForm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mnLaptopActionPerformed

    private boolean checkInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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
            java.util.logging.Logger.getLogger(CreateReceiptForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateReceiptForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateReceiptForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateReceiptForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateReceiptForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRemove;
    private javax.swing.JComboBox<Laptop> cbLaptop;
    private javax.swing.JComboBox<String> cbPayment;
    private javax.swing.JComboBox<Service> cbService;
    private javax.swing.JComboBox<Staff> cbStaff;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu mnLaptop;
    private javax.swing.JMenu mnReceipt;
    private javax.swing.JTable tblCart;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPurchaseDate;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUnitPrice;
    private javax.swing.JTextField txtWarranty;
    // End of variables declaration//GEN-END:variables
}
