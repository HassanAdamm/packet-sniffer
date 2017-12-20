import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.packet.Packet;
import jpcap.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import java.util.List;

public class sniffer extends javax.swing.JFrame {

    public sniffer() {
        initComponents();
        captureButton.setEnabled(false);
        stopButton.setEnabled(false);
        saveButton.setEnabled(false);
        filter_options.setEnabled(false);
    }
    //Globals
    public static NetworkInterface[] NETWORK_INTERFACES;
    public static JpcapCaptor CAP;
    jpcap_thread THREAD;
    public static int INDEX = 0;
    public static int flag = 0;
    public static int COUNTER = 0;
    boolean CaptureState = false;
    public static int No = 0;

    JpcapWriter writer = null;
    List<Packet> packetList = new ArrayList<>();

    //HEX-View two functions.
    public static String toHexadecimal(String text) throws UnsupportedEncodingException {
        byte[] myBytes = text.getBytes("UTF-8");

        return DatatypeConverter.printHexBinary(myBytes);
    }

    public static String customizeHexa(String text) {

        String out;
        out = text.replaceAll("(.{32})", "$1\n");
        return out.replaceAll("..(?!$)", "$0 ");
    }

    public void CapturePackets() {

        THREAD = new jpcap_thread() {

            public Object construct() {

                try {

                    CAP = JpcapCaptor.openDevice(NETWORK_INTERFACES[INDEX], 65535, false, 20);
                    //writer = JpcapWriter.openDumpFile(CAP, "captureddata");
                    if ("UDP".equals(filter_options.getSelectedItem().toString())) {
                        CAP.setFilter("udp", true);
                    } else if ("TCP".equals(filter_options.getSelectedItem().toString())) {
                        CAP.setFilter("tcp", true);
                    } else if ("ICMP".equals(filter_options.getSelectedItem().toString())) {
                        CAP.setFilter("icmp", true);
                    }

                    while (CaptureState) {

                        CAP.processPacket(1, new PacketContents());
                        packetList.add(CAP.getPacket());
                    }
                    CAP.close();

                } catch (Exception e) {
                    System.out.print(e);
                }
                return 0;
            }

            public void finished() {
                this.interrupt();
            }
        };

        THREAD.start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jToolBar1 = new javax.swing.JToolBar();
        listButton = new java.awt.Button();
        jLabel1 = new javax.swing.JLabel();
        filter_options = new javax.swing.JComboBox<>();
        captureButton = new java.awt.Button();
        stopButton = new java.awt.Button();
        saveButton = new java.awt.Button();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        jMenu2.setText("File");
        jMenuBar2.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("jMenu4");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ZARA Packet Sniffer");
        setName("ZARA Packet Sniffer"); // NOI18N

        jToolBar1.setRollover(true);

        listButton.setActionCommand("List Interfaces");
        listButton.setBackground(new java.awt.Color(0, 0, 102));
        listButton.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        listButton.setForeground(new java.awt.Color(255, 255, 255));
        listButton.setLabel("List Interfaces");
        listButton.setPreferredSize(new java.awt.Dimension(90, 26));
        listButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(listButton);

        jLabel1.setText(" Filter");
        jToolBar1.add(jLabel1);

        filter_options.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "TCP", "UDP", "ICMP" }));
        filter_options.setPreferredSize(new java.awt.Dimension(320, 24));
        filter_options.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_optionsActionPerformed(evt);
            }
        });
        jToolBar1.add(filter_options);

        captureButton.setBackground(new java.awt.Color(0, 204, 0));
        captureButton.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        captureButton.setLabel("Capture");
        captureButton.setPreferredSize(new java.awt.Dimension(83, 24));
        captureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                captureButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(captureButton);

        stopButton.setBackground(new java.awt.Color(255, 0, 51));
        stopButton.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        stopButton.setLabel("Stop");
        stopButton.setPreferredSize(new java.awt.Dimension(83, 24));
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(stopButton);

        saveButton.setLabel("Save");
        saveButton.setPreferredSize(new java.awt.Dimension(83, 24));
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(saveButton);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Length", "Source", "Destination", "Protocol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setRowHeight(20);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable1);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel2.setText("Packet info:");

        jLabel3.setText("Hex view:");
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        Object obj = jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0);
        if (PacketContents.rowList.get((int) obj)[4] == "TCP") {

            jTextArea1.setText("Packet No: " + PacketContents.rowList.get((int) obj)[0]
                    + "\nSeq No: " + PacketContents.rowList.get((int) obj)[10]
                    + "\nProtocol: " + PacketContents.rowList.get((int) obj)[4]
                    + "\nSource IP: " + PacketContents.rowList.get((int) obj)[2]
                    + "\nDist IP: " + PacketContents.rowList.get((int) obj)[3]
                    + "\nLength: " + PacketContents.rowList.get((int) obj)[1]
                    + "\nSource Port: " + PacketContents.rowList.get((int) obj)[5]
                    + "\nDist Port: " + PacketContents.rowList.get((int) obj)[6]
                    + "\nAck: " + PacketContents.rowList.get((int) obj)[7]
                    + "\nAck No: " + PacketContents.rowList.get((int) obj)[8]
                    + "\nSequence No: " + PacketContents.rowList.get((int) obj)[10]
                    //+ "\nOffset: " + PacketContents.rowList.get((int) obj)[11]
                    + "\nHeader: " + PacketContents.rowList.get((int) obj)[12]
                    + "\nData: " + PacketContents.rowList.get((int) obj)[9]
            );

            try {
                jTextArea2.setText(customizeHexa(toHexadecimal(jTextArea1.getText())));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(sniffer.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (PacketContents.rowList.get((int) obj)[4] == "UDP") {
            jTextArea1.setText("Packet No: " + PacketContents.rowList.get((int) obj)[0]
                    + "\nProtocol:" + PacketContents.rowList.get((int) obj)[4]
                    + "\nSource IP: " + PacketContents.rowList.get((int) obj)[2]
                    + "\nDist IP: " + PacketContents.rowList.get((int) obj)[3]
                    + "\nLength: " + PacketContents.rowList.get((int) obj)[1]
                    + "\nSource Port: " + PacketContents.rowList.get((int) obj)[5]
                    + "\nDist Port: " + PacketContents.rowList.get((int) obj)[6]
                    + "\nOffset: " + PacketContents.rowList.get((int) obj)[8]
                    + "\nHeader: " + PacketContents.rowList.get((int) obj)[9]
                    + "\nData: " + PacketContents.rowList.get((int) obj)[7]
            );

            try {
                jTextArea2.setText(customizeHexa(toHexadecimal(jTextArea1.getText())));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(sniffer.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (PacketContents.rowList.get((int) obj)[4] == "ICMP") {
            jTextArea1.setText("Packet No: " + PacketContents.rowList.get((int) obj)[0]
                    + "\nProtocol:" + PacketContents.rowList.get((int) obj)[4]
                    + "\nSource IP: " + PacketContents.rowList.get((int) obj)[2]
                    + "\nDist IP: " + PacketContents.rowList.get((int) obj)[3]
                    + "\nLength: " + PacketContents.rowList.get((int) obj)[1]
                    + "\nChecksum: " + PacketContents.rowList.get((int) obj)[5]
                    + "\nHeader: " + PacketContents.rowList.get((int) obj)[6]
                    + "\nOffset: " + PacketContents.rowList.get((int) obj)[7]
                    + "\nOriginate TimeStamp: " + PacketContents.rowList.get((int) obj)[8] + "bits"
                    + "\nRecieve TimeStamp: " + PacketContents.rowList.get((int) obj)[9] + "bits"
                    + "\nTransmit TimeStamp: " + PacketContents.rowList.get((int) obj)[10] + "bits"
                    + "\nData: " + PacketContents.rowList.get((int) obj)[11]
            );

            try {
                jTextArea2.setText(customizeHexa(toHexadecimal(jTextArea1.getText())));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(sniffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void captureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_captureButtonActionPerformed

        CaptureState = true;
        CapturePackets();
        saveButton.setEnabled(false);
        filter_options.setEnabled(false);
        listButton.setEnabled(false);
    }//GEN-LAST:event_captureButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        // TODO add your handling code here:
        CaptureState = false;
        THREAD.finished();
        saveButton.setEnabled(true);
        filter_options.setEnabled(true);
        listButton.setEnabled(true);
    }//GEN-LAST:event_stopButtonActionPerformed

    private void listButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listButtonActionPerformed
        // TODO add your handling code here:
        InterfacesWindow nw = new InterfacesWindow();
    }//GEN-LAST:event_listButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed

        THREAD = new jpcap_thread() {
            public Object construct() {

                writer = null;
                try {
                    CAP = JpcapCaptor.openDevice(NETWORK_INTERFACES[INDEX], 65535, false, 20);

                    writer = JpcapWriter.openDumpFile(CAP, "captured_data.txt");
                } catch (IOException ex) {
                    Logger.getLogger(sniffer.class.getName()).log(Level.SEVERE, null, ex);
                }

                for (int i = 0; i < No; i++) {
                    writer.writePacket(packetList.get(i));
                }

                return 0;
            }

            public void finished() {
                this.interrupt();
            }
        };

        THREAD.start();


    }//GEN-LAST:event_saveButtonActionPerformed

    private void filter_optionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_optionsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filter_optionsActionPerformed

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
            java.util.logging.Logger.getLogger(sniffer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sniffer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sniffer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sniffer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sniffer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static java.awt.Button captureButton;
    public static javax.swing.JComboBox<String> filter_options;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JToolBar jToolBar1;
    public static java.awt.Button listButton;
    public static java.awt.Button saveButton;
    public static java.awt.Button stopButton;
    // End of variables declaration//GEN-END:variables
}
