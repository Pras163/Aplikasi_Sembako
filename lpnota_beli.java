/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author EJP
 */
public class lpnota_beli extends javax.swing.JFrame {

    /**
     * Creates new form lpnota
     */
    public lpnota_beli() {
        initComponents();
        Locale locale = new Locale ("id","ID");
        Locale.setDefault(locale);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        cetakBT = new javax.swing.JButton();
        no_transaksi = new javax.swing.JTextField();
        keluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)), "Cetak Nota Pembelian", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Nomo Transaksi      :");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 310, 10));

        cetakBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cetakBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Print.png"))); // NOI18N
        cetakBT.setText("CETAK");
        cetakBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cetakBTMouseEntered(evt);
            }
        });
        cetakBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakBTActionPerformed(evt);
            }
        });
        jPanel3.add(cetakBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 140, 40));
        jPanel3.add(no_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 180, 30));

        keluar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Keluar2.png"))); // NOI18N
        keluar.setText("KELUAR");
        keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                keluarMouseEntered(evt);
            }
        });
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });
        jPanel3.add(keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 140, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cetakBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetakBTMouseEntered
        // TODO add your handling code here:
        cetakBT.setBackground(new Color(102,204,255));
        cetakBT.setForeground(Color.black);
    }//GEN-LAST:event_cetakBTMouseEntered

    private void cetakBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakBTActionPerformed
        // TODO add your handling code here:
        try {
            String NamaFile = "src/laporan/kwitansi_beli.jasper";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/db_sembako","root","");
            HashMap param = new HashMap();
            param.put("notabeli",no_transaksi.getText());
            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, koneksi);
            JasperViewer.viewReport(JPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat dicetak!","Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cetakBTActionPerformed

    private void keluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keluarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_keluarMouseEntered

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_keluarActionPerformed

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
            java.util.logging.Logger.getLogger(lpnota_beli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(lpnota_beli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(lpnota_beli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(lpnota_beli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new lpnota_beli().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cetakBT;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton keluar;
    private javax.swing.JTextField no_transaksi;
    // End of variables declaration//GEN-END:variables
}