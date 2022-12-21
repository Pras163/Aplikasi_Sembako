
package tampilan;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import koneksi.Koneksi;

/**
 *
 * @author TOSHIBA DC
 */
public final class  user extends javax.swing.JInternalFrame {
    Connection conn = Koneksi.getConnection();
    private DefaultTableModel tab;
    private String SQL;

    /**
     * Creates new form barang
     */
    public user() {
        initComponents();
//        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
        bersih();
        this.Tampil();
        DISABLE();
        tambah.setVisible(true);
        simpan.setVisible(false);
    }
    private void bersih(){
        txtid.setText("");
        txtuser.setText("");
        pass.setText("");
        nama.setText("");
        
      
    } 
 
    
 
    
    
private void ENABLE() {
        txtid.setEnabled(true);
        txtuser.setEnabled(true);
        pass.setEnabled(true);
        nama.setEnabled(true);
        tambah.setEnabled(false);
        simpan.setEnabled(true);
        batal.setEnabled(false);
        ubah.setEnabled(false);
        batal.setEnabled(true);
    }
    
    private void DISABLE() {
        txtid.setEnabled(false);
        txtuser.setEnabled(false);
        pass.setEnabled(false);
        nama.setEnabled(false);
        tambah.setEnabled(true);
        simpan.setEnabled(false);
        batal.setEnabled(false);
        ubah.setEnabled(false);
        batal.setEnabled(false);
    }

    public void Tampil() {
        tab = new DefaultTableModel();
        tab.addColumn("ID User");
        tab.addColumn("Username");
        tab.addColumn("Password");
        tab.addColumn("Nama User");
        tabeluser.setModel(tab);
        try {
            java.sql.Statement stmt = conn.createStatement();
            SQL = "SELECT * FROM login";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                tab.addRow(new Object[]{
                    res.getString("id_user"),
                    res.getString("username"),
                    res.getString("password"),
                    res.getString("Nama"),
                    
                });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void DATA_TABEL(){
        int baris = tabeluser.getSelectedRow();
            txtid.setText(tab.getValueAt(baris, 0).toString());
            txtuser.setText(tab.getValueAt(baris, 1).toString());
            pass.setText(tab.getValueAt(baris, 2).toString());
            nama.setText(tab.getValueAt(baris, 3).toString());
            DISABLE();
            tambah.setEnabled(false);
            batal.setEnabled(true);
            ubah.setEnabled(true);
            batal.setEnabled(true);
            ubah.setVisible(true);
            update.setVisible(false);
    }
    
    public void TAMBAH(){
        bersih();
        txtid.requestFocus();
        ENABLE();
        tambah.setVisible(false);
        simpan.setVisible(true);
    }
    
    public void SIMPAN(){
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO login (id_user,username, password, Nama) VALUES (?,?,?,?)");
            stmt.setString(1, txtid.getText());
            stmt.setString(2, txtuser.getText());
            stmt.setString(3, pass.getText());
            stmt.setString(4, nama.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            Tampil();
            bersih();
            DISABLE();
            simpan.setVisible(false);
            tambah.setVisible(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
       
    public void UBAH(){
        ENABLE();
        tambah.setEnabled(false);
        ubah.setEnabled(true);
        batal.setEnabled(false);
        update.setEnabled(true);
        simpan.setEnabled(false);
        batal.setEnabled(true);
        update.setVisible(true);
        ubah.setVisible(false);
    }
    
    public void HAPUS(){
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data tersebut?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == 0) {
            try {
                java.sql.PreparedStatement stmt = conn.prepareStatement("DELETE FROM login WHERE id_user ='" + txtid.getText() + "'");
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                Tampil();
                bersih();
                txtid.requestFocus();
                DISABLE();
                simpan.setVisible(false);
                tambah.setVisible(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
       
    public void UPDATE(){
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE login SET Nama=?, username=?, password=?, WHERE id_user=?");
            stmt.setString(1,  txtid.getText());
            stmt.setString(2, txtuser.getText());
            stmt.setString(3, pass.getText());
            stmt.setString(4, nama.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            Tampil();
            DISABLE();
            ubah.setVisible(true);
            tambah.setVisible(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void BATAL(){
        bersih();
        DISABLE();
        tambah.setVisible(true);
        simpan.setVisible(false);
        update.setVisible(false);
        ubah.setVisible(true);
        this.Tampil();
    }
    
    public void CARI(){
        tab = new DefaultTableModel();
        tab.addColumn("ID User");
        tab.addColumn("Username");
        tab.addColumn("Password");
        tab.addColumn("Nama User");
        tabeluser.setModel(tab);
            try {
                java.sql.Statement stmt = conn.createStatement();
                SQL = "SELECT * FROM login WHERE id_user LIKE '%" + txtcari.getText() + "%' OR " + "Nama LIKE '%" + txtcari.getText() + "%'";
                System.out.println(SQL);
                java.sql.ResultSet res = stmt.executeQuery(SQL);
                while (res.next()){
                    tab.addRow(new Object[]{
                    res.getString("id_user"),
                    res.getString("username"),
                    res.getString("password"),
                    res.getString("Nama"),
                });
                }
            } catch(SQLException e){
                System.out.println(e.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        txtuser = new javax.swing.JTextField();
        pass = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        tambah = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        ubah = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        hapus1 = new javax.swing.JButton();
        update = new javax.swing.JButton();
        keluar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabeluser = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1160, 570));
        setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1160, 570));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setToolTipText("");
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Master Pengguna");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 440, -1));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 440, 10));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1160, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)), "Silakan Input Data", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(153, 153, 153))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("ID Pengguna   :");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Nama               :");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Username        :");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Password        :");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        nama.setBorder(null);
        jPanel3.add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 470, 30));

        txtid.setBorder(null);
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });
        jPanel3.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 430, 30));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 580, -1));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 580, 10));

        txtuser.setBorder(null);
        jPanel3.add(txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 480, 30));

        pass.setBorder(null);
        jPanel3.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 470, 30));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 570, 10));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 570, 10));

        tambah.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Tambah.png"))); // NOI18N
        tambah.setText("TAMBAH");
        tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tambahMouseEntered(evt);
            }
        });
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });
        jPanel3.add(tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 110, 40));

        simpan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Simpan.png"))); // NOI18N
        simpan.setText("SIMPAN");
        simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                simpanMouseEntered(evt);
            }
        });
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });
        jPanel3.add(simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 110, 40));

        ubah.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Edit.png"))); // NOI18N
        ubah.setText("UBAH");
        ubah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ubahMouseEntered(evt);
            }
        });
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });
        jPanel3.add(ubah, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 110, 40));

        batal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Hapus2.png"))); // NOI18N
        batal.setText("BATAL");
        batal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                batalMouseEntered(evt);
            }
        });
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });
        jPanel3.add(batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 110, 40));

        hapus1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        hapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/trash.png"))); // NOI18N
        hapus1.setText("HAPUS");
        hapus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hapus1MouseEntered(evt);
            }
        });
        hapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapus1ActionPerformed(evt);
            }
        });
        jPanel3.add(hapus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 110, 40));

        update.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Edit.png"))); // NOI18N
        update.setText("UPDATE");
        update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateMouseEntered(evt);
            }
        });
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jPanel3.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 110, 40));

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
        jPanel3.add(keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 110, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 630, 260));

        tabeluser.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabeluser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabeluser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeluserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabeluser);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, 620, 110));
        jPanel1.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 200, -1));

        btncari.setText("Cari");
        jPanel1.add(btncari, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 380, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
      TAMBAH();
      
    }//GEN-LAST:event_tambahActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
     SIMPAN();
    }//GEN-LAST:event_simpanActionPerformed

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        // TODO add your handling code here:
    UBAH();
    }//GEN-LAST:event_ubahActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
     BATAL();
    }//GEN-LAST:event_batalActionPerformed

    private void tambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahMouseEntered
        // TODO add your handling code here:
         tambah.setBackground(new Color(102,204,255));
        tambah.setForeground(Color.black);
    }//GEN-LAST:event_tambahMouseEntered

    private void simpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanMouseEntered
        // TODO add your handling code here:
         simpan.setBackground(new Color(102,204,255));
        simpan.setForeground(Color.black);
    }//GEN-LAST:event_simpanMouseEntered

    private void ubahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ubahMouseEntered
        // TODO add your handling code here:
         ubah.setBackground(new Color(102,204,255));
        ubah.setForeground(Color.black);
    }//GEN-LAST:event_ubahMouseEntered

    private void batalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalMouseEntered
        // TODO add your handling code here:
         batal.setBackground(new Color(102,204,255));
        batal.setForeground(Color.black);
    }//GEN-LAST:event_batalMouseEntered

    private void hapus1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapus1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_hapus1MouseEntered

    private void hapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapus1ActionPerformed
        // TODO add your handling code here:
        HAPUS();
    }//GEN-LAST:event_hapus1ActionPerformed

    private void updateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_updateMouseEntered

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
        UPDATE();
    }//GEN-LAST:event_updateActionPerformed

    private void tabeluserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeluserMouseClicked
        // TODO add your handling code here:
        DATA_TABEL();
    }//GEN-LAST:event_tabeluserMouseClicked

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void keluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keluarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_keluarMouseEntered

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_keluarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batal;
    private javax.swing.JButton btncari;
    private javax.swing.JButton hapus1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JButton keluar;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField pass;
    private javax.swing.JButton simpan;
    private javax.swing.JTable tabeluser;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtuser;
    private javax.swing.JButton ubah;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
 
}
