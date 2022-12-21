
package tampilan;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.Koneksi;

/**
 *
 * @author TOSHIBA DC
 */
public final class supplier extends javax.swing.JInternalFrame {
Connection conn = Koneksi.getConnection();
    private DefaultTableModel tab;
    private String SQL;
    
    /**
     * Creates new form barang
     */
    public supplier() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
         tabelsuplier.getTableHeader().setFont(new Font("Tohome",Font.BOLD,12));
        tabelsuplier.getTableHeader().setOpaque(false);
        tabelsuplier.setRowHeight(25);
        tampil();
        bersih();
        DISABLE();
        tambah.setVisible(true);
        simpan.setVisible(false);
    }
private void bersih(){
        id_sup.setText("");
        nama_sup.setText("");
        email_sup.setText("");
        tlpn_sup.setText("");
        alamat_sup.setText("");
       
    }

private void ENABLE() {
        id_sup.setEnabled(true);
        nama_sup.setEnabled(true);
        email_sup.setEnabled(true);
        tlpn_sup.setEnabled(true);
        alamat_sup.setEnabled(true);
        tambah.setEnabled(false);
        simpan.setEnabled(true);
        update.setEnabled(false);
        batal.setEnabled(true);
        ubah.setEnabled(false);
    }
    
    private void DISABLE() {
        id_sup.setEnabled(false);
        nama_sup.setEnabled(false);
        email_sup.setEnabled(false);
        tlpn_sup.setEnabled(false);
        alamat_sup.setEnabled(false);
        tambah.setEnabled(true);
        simpan.setEnabled(false);
        batal.setEnabled(false);
        update.setEnabled(false);
        ubah.setEnabled(false);
    }
    
    public void tampil(){
       tab = new DefaultTableModel();
        tab.addColumn("ID Suplier");
        tab.addColumn("Nama Suplier");
        tab.addColumn("Email Suplier");
        tab.addColumn("No Telpon");
        tab.addColumn("Alamat");
        tabelsuplier.setModel(tab);
        try {
            java.sql.Statement stmt = conn.createStatement();
            SQL = "SELECT * FROM suplier";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                tab.addRow(new Object[]{
                    res.getString("id_suplier"),
                    res.getString("nama_suplier"),
                    res.getString("email_suplier"),
                    res.getString("no_telpon"),
                    res.getString("alamat"),
                    
                });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    public void data_tabel(){
        int baris = tabelsuplier.getSelectedRow();
            id_sup.setText(tab.getValueAt(baris, 0).toString());
            nama_sup.setText(tab.getValueAt(baris, 1).toString());
            email_sup.setText(tab.getValueAt(baris, 2).toString());
            tlpn_sup.setText(tab.getValueAt(baris, 3).toString());
            alamat_sup.setText(tab.getValueAt(baris, 4).toString());
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
        id_sup.requestFocus();
        ENABLE();
        tambah.setVisible(false);
        simpan.setVisible(true);
        
    }
    
     public void SIMPAN(){
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO suplier (id_suplier, nama_suplier, email_suplier, no_telpon, alamat) VALUES (?,?,?,?,?)");
            stmt.setString(1, id_sup.getText());
            stmt.setString(2, nama_sup.getText());
            stmt.setString(3, email_sup.getText());
            stmt.setString(4, tlpn_sup.getText());
            stmt.setString(5, alamat_sup.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            tampil();
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
     
    public void hapus(){
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data tersebut?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == 0) {
            try {
                java.sql.PreparedStatement stmt = conn.prepareStatement("DELETE FROM suplier WHERE id_suplier ='" + id_sup.getText() + "'");
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                tampil();
                bersih();
                id_sup.requestFocus();
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
            PreparedStatement stmt = conn.prepareStatement("UPDATE admin SET nama_suplier=?, email_suplier=?, no_telpon=?, alamat=?, WHERE id_suplier=?");
            stmt.setString(1, id_sup.getText());
            stmt.setString(2, nama_sup.getText());
            stmt.setString(3, email_sup.getText());
            stmt.setString(4, tlpn_sup.getText());
            stmt.setString(5, alamat_sup.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            tampil();
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
        this.tampil();
    }
    
    public void CARI(){
        tab = new DefaultTableModel();
        tab.addColumn("ID Suplier");
        tab.addColumn("Nama Suplier");
        tab.addColumn("Email Suplier");
        tab.addColumn("No Telpon");
        tab.addColumn("Alamat");
        tabelsuplier.setModel(tab);
            try {
                java.sql.Statement stmt = conn.createStatement();
                SQL = "SELECT * FROM suplier WHERE id_suplier LIKE '%" + id_sup.getText() + "%' OR " + "nama_suplier LIKE '%" + txtcari.getText() + "%'";
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        tlpn_sup = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamat_sup = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelsuplier = new javax.swing.JTable();
        txtcari = new javax.swing.JTextField();
        cari = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        nama_sup = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        email_sup = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        id_sup = new javax.swing.JTextField();
        tambah = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        ubah = new javax.swing.JButton();
        update = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        hapusBT1 = new javax.swing.JButton();
        keluar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1160, 570));
        setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1160, 570));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Master Supplier");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 390, -1));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 420, 10));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1160, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)), "Silakan Input Data", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(0, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("No Telpon  :");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText(" Alamat      :");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 390, -1));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 390, -1));

        tlpn_sup.setBorder(null);
        jPanel3.add(tlpn_sup, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 310, 30));

        alamat_sup.setColumns(20);
        alamat_sup.setRows(5);
        alamat_sup.setBorder(null);
        jScrollPane1.setViewportView(alamat_sup);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 310, 60));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 420, 150));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)), "Table Data Supplier", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelsuplier.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelsuplier.setAutoscrolls(false);
        tabelsuplier.setGridColor(new java.awt.Color(102, 102, 255));
        tabelsuplier.setRowHeight(25);
        tabelsuplier.setSelectionBackground(new java.awt.Color(0, 204, 204));
        tabelsuplier.setShowVerticalLines(false);
        tabelsuplier.getTableHeader().setReorderingAllowed(false);
        tabelsuplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelsuplierMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelsuplier);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 720, 160));
        jPanel5.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 240, 31));

        cari.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Cari2.png"))); // NOI18N
        cari.setText("CARI");
        cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cariMouseEntered(evt);
            }
        });
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        jPanel5.add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 100, 30));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 900, 230));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)), "Silakan Input Data", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Id Supplier      :");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Nama               :");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Email                :");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 100, -1));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 390, -1));

        nama_sup.setBorder(null);
        jPanel6.add(nama_sup, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 290, 30));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 390, -1));

        email_sup.setBorder(null);
        jPanel6.add(email_sup, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 290, 30));

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 390, -1));

        id_sup.setBorder(null);
        jPanel6.add(id_sup, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 290, 30));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 430, 150));

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
        jPanel1.add(tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 110, 40));

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
        jPanel1.add(simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 110, 40));

        ubah.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Edit.png"))); // NOI18N
        ubah.setText("EDIT");
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
        jPanel1.add(ubah, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 110, 40));

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
        jPanel1.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 110, 40));

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
        jPanel1.add(batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 110, 40));

        hapusBT1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        hapusBT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/trash.png"))); // NOI18N
        hapusBT1.setText("HAPUS");
        hapusBT1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hapusBT1MouseEntered(evt);
            }
        });
        hapusBT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBT1ActionPerformed(evt);
            }
        });
        jPanel1.add(hapusBT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 110, 40));

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
        jPanel1.add(keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, 110, 40));

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

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        SIMPAN();
    }//GEN-LAST:event_simpanActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
         TAMBAH();                       
    }//GEN-LAST:event_tambahActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
       UPDATE();
    }//GEN-LAST:event_updateActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
       BATAL();
    }//GEN-LAST:event_batalActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
       CARI();
    }//GEN-LAST:event_cariActionPerformed

    private void tabelsuplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelsuplierMouseClicked
        // TODO add your handling code here:
        data_tabel();
        int baris = tabelsuplier.getSelectedRow();
        id_sup.setText(tabelsuplier.getModel().getValueAt(baris, 0).toString());
        nama_sup.setText(tabelsuplier.getModel().getValueAt(baris, 1).toString());
        email_sup.setText(tabelsuplier.getModel().getValueAt(baris, 2).toString());
        tlpn_sup.setText(tabelsuplier.getModel().getValueAt(baris, 3).toString());
        alamat_sup.setText(tabelsuplier.getModel().getValueAt(baris, 4).toString());
        batal.setEnabled(true);
        update.setEnabled(true);
    }//GEN-LAST:event_tabelsuplierMouseClicked

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

    private void updateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseEntered
        // TODO add your handling code here:
         update.setBackground(new Color(102,204,255));
        update.setForeground(Color.black);
    }//GEN-LAST:event_updateMouseEntered

    private void batalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalMouseEntered
        // TODO add your handling code here:
        
         batal.setBackground(new Color(102,204,255));
        batal.setForeground(Color.black);
    }//GEN-LAST:event_batalMouseEntered

    private void cariMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cariMouseEntered
        // TODO add your handling code here:
         cari.setBackground(new Color(102,204,255));
        cari.setForeground(Color.black);
    }//GEN-LAST:event_cariMouseEntered

    private void ubahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ubahMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ubahMouseEntered

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
        // TODO add your handling code here:
        UBAH();
    }//GEN-LAST:event_ubahActionPerformed

    private void hapusBT1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusBT1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_hapusBT1MouseEntered

    private void hapusBT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBT1ActionPerformed
        // TODO add your handling code here:
        hapus();
    }//GEN-LAST:event_hapusBT1ActionPerformed

    private void keluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keluarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_keluarMouseEntered

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_keluarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat_sup;
    private javax.swing.JButton batal;
    private javax.swing.JButton cari;
    private javax.swing.JTextField email_sup;
    private javax.swing.JButton hapusBT1;
    private javax.swing.JTextField id_sup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JButton keluar;
    private javax.swing.JTextField nama_sup;
    private javax.swing.JButton simpan;
    private javax.swing.JTable tabelsuplier;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField tlpn_sup;
    private javax.swing.JTextField txtcari;
    private javax.swing.JButton ubah;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables

}
