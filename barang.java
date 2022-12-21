
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


public final class  barang extends javax.swing.JInternalFrame {
    private DefaultTableModel tab;
    private Connection conn = Koneksi.getConnection();;
//    Statement st;
//    ResultSet rs;
    private String SQL;

    /**
     * Creates new form barang
     */
    public barang() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null); 
        tb_barang.getTableHeader().setFont(new Font("Tohome",Font.BOLD,12));
        tb_barang.getTableHeader().setOpaque(false);
        tb_barang.setRowHeight(25);
        otomatis();
        this.tampil();
        DISABLE();
        tambah.setVisible(true);
        simpan.setVisible(false);
        
 
    
        
    }
private void bersih(){
        kode.setText("");        
        namabrg.setSelectedItem("");
         ukuran.setSelectedItem("");
         beli.setText("");
         jual.setText("");
        stok.setText("");
       
       
    }

    private void ENABLE() {
        kode.setEnabled(true);
        namabrg.setEnabled(true);
        ukuran.setEnabled(true);
        beli.setEnabled(true);
        jual.setEnabled(true);
        stok.setEnabled(true);
        tambah.setEnabled(false);
        simpan.setEnabled(true);
        update.setEnabled(true);
        edit.setEnabled(true);
        hapus.setEnabled(false);
        batal.setEnabled(true);
    }
    
    private void DISABLE() {
        kode.setEnabled(false);
        namabrg.setEnabled(false);
        ukuran.setEnabled(false);
        beli.setEnabled(false);
        jual.setEnabled(false);
        stok.setEnabled(false);
        tambah.setEnabled(true);
        simpan.setEnabled(false);
        update.setEnabled(false);
        edit.setEnabled(false);
        hapus.setEnabled(false);
        batal.setEnabled(false);
    }
    
    
    
    private void otomatis(){
      try {
            SQL = "SELECT MAX(RIGHT(kode_barang,4)) AS NO FROM barang";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                if (rs.first() == false) {
                    kode.setText("BRG-001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int Nomorbrg = no.length();
                    for (int j = 0; j < 4 - Nomorbrg; j++) {
                        no = "0" + no;
                    }
                    kode.setText("BRG-" + no);
                }
            }
            rs.close();
            st.close();
            }  catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "ERROR: \n" + e.toString(),
                    "Kesalahan", JOptionPane.WARNING_MESSAGE);
        }
     }
    

    
   public void tampil(){
       tab = new DefaultTableModel();
       tab.addColumn("Kode Barang");
        tab.addColumn("Nama Barang");
        tab.addColumn("Ukuran");
        tab.addColumn("Harga Beli");
        tab.addColumn("Harga Jual");
        tab.addColumn("Stok");
        tb_barang.setModel(tab);
        try {
            java.sql.Statement stmt = conn.createStatement();
            SQL = "SELECT * FROM barang";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                tab.addRow(new Object[]{
                    res.getString("kode_barang"),
                    res.getString("nm_barang"),
                    res.getString("ukuran"),
                    res.getString("harga_beli"),
                    res.getString("harga_jual"),
                    res.getString("stok")
                });
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
            }
    public void data_tabel(){
        int baris = tb_barang.getSelectedRow();
            kode.setText(tab.getValueAt(baris, 0).toString());
            namabrg.setSelectedItem(tab.getValueAt(baris, 1).toString());
            ukuran.setSelectedItem(tab.getValueAt(baris, 2).toString());
            beli.setText(tab.getValueAt(baris, 3).toString());
            jual.setText(tab.getValueAt(baris, 4).toString());
            stok.setText(tab.getValueAt(baris, 5).toString());
            DISABLE();
            tambah.setEnabled(false);
            hapus.setEnabled(true);
            edit.setEnabled(true);
            batal.setEnabled(true);
            edit.setVisible(true);
            update.setVisible(false);
            
    }
    public void btn_tambah(){
        bersih();
        kode.requestFocus();
        ENABLE();
        tambah.setVisible(false);
        simpan.setVisible(true);
    }
    
    public void btn_simpan(){
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO barang(kode_barang, nm_barang, ukuran, harga_beli, harga_jual, stok) VALUES (?,?,?,?,?,?)");
            stmt.setString(1, kode.getText());
            stmt.setString(2, (String) namabrg.getSelectedItem());
            stmt.setString(3, (String) ukuran.getSelectedItem());
            stmt.setString(4, beli.getText());
            stmt.setString(5, jual.getText());
            stmt.setString(6, stok.getText());
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
    
    public void btn_edit(){
        ENABLE();
        tambah.setEnabled(false);
        edit.setEnabled(true);
        hapus.setEnabled(false);
        update.setEnabled(true);
        simpan.setEnabled(false);
        batal.setEnabled(true);
        edit.setVisible(false);
        update.setVisible(true);
    }
    
    public void btn_hapus(){
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data tersebut?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == 0) {
            try {
                java.sql.PreparedStatement stmt = conn.prepareStatement("DELETE FROM barang WHERE kode_barang ='" + kode.getText() + "'");
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                tampil();
                bersih();
                kode.requestFocus();
                DISABLE();
                update.setVisible(false);
                tambah.setVisible(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void btn_update(){
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE barang SET nm_barang=?, ukuran=?, harga_beli=?, harga_jual=?, stok=?  WHERE kode_barang=?");
            stmt.setString(1, (String) namabrg.getSelectedItem());
            stmt.setString(2, (String) ukuran.getSelectedItem());
            stmt.setString(3, beli.getText());
            stmt.setString(4, jual.getText());
            stmt.setString(5, stok.getText());
            stmt.setString(6, kode.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            tampil();
            DISABLE();
            edit.setVisible(true);
            tambah.setVisible(false);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void btn_batal(){
        bersih();
        DISABLE();
        tambah.setVisible(true);
        simpan.setVisible(false);
        update.setVisible(false);
        edit.setVisible(true);
        this.tampil();
    }
    
    public void btn_cari(){
        tab = new DefaultTableModel();
        tab.addColumn("Kode Barang");
        tab.addColumn("Nama Barang");
        tab.addColumn("Ukuran");
        tab.addColumn("Harga Beli");
        tab.addColumn("Harga Jual");
        tab.addColumn("Stok");
        tb_barang.setModel(tab);
            try {
                java.sql.Statement stmt = conn.createStatement();
                SQL = "SELECT * FROM barang WHERE kode_barang LIKE '%" + cari.getText() + "%' OR " + "nm_barang LIKE '%" + cari.getText() + "%'";
                System.out.println(SQL);
                java.sql.ResultSet res = stmt.executeQuery(SQL);
                while (res.next()){
                    tab.addRow(new Object[]{
                    res.getString("kode_barang"),
                    res.getString("nm_barang"),
                    res.getString("ukuran"),
                    res.getString("harga_beli"),
                    res.getString("harga_jual"),
                    res.getString("stok")
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
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jual = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        kode = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        ukuran = new javax.swing.JComboBox<>();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        stok = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        beli = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        namabrg = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_barang = new javax.swing.JTable();
        cari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        tambah = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        update = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        KELUAR = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1160, 570));
        setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1160, 570));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Master Barang");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 390, -1));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 410, 10));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1160, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)), "Silakan Input Data", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Stok                :");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Kode Barang   :");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Nama Barang  :");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Ukuran            :");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Harga Jual       :");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jual.setBorder(null);
        jPanel3.add(jual, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 240, 30));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 340, 10));

        kode.setBorder(null);
        kode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeActionPerformed(evt);
            }
        });
        jPanel3.add(kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 240, 30));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 340, 10));

        ukuran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----Pilih Ukuran-----", "None", "Besar", "Sedang", "Liter", "Bks" }));
        ukuran.setActionCommand("1 Meter\n2 Meter\n3 Meter");
        jPanel3.add(ukuran, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 240, 30));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 340, -1));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 340, 10));

        stok.setBorder(null);
        jPanel3.add(stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 240, 30));
        jPanel3.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 340, 10));

        beli.setBorder(null);
        beli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beliActionPerformed(evt);
            }
        });
        jPanel3.add(beli, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 240, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Harga Beli        :");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));
        jPanel3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 340, 10));

        namabrg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----Pilih Barang----", "Beras", "Indomie Goreng", "Indomie Soto", "Indomie Kari Ayam", "Indomie Ayam Bawang", "Indomie Rendang", "Mie Sedap Goreng", "Mie Sedap Soto", "Telor", "Aqua Botol 1500 ML", "Aqua Botol 600 ML", "GG Filter 12", "GG Signature 12", "Sampoerna Mild 16", "Surya 16", "Samsu Kretek 12", "Dunhill 16", " ", " ", " " }));
        namabrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namabrgActionPerformed(evt);
            }
        });
        jPanel3.add(namabrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 240, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 390, 290));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)), "Table Data Barang", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(102, 102, 102)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tb_barang.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_barang.setGridColor(new java.awt.Color(102, 102, 255));
        tb_barang.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tb_barang.setRowHeight(25);
        tb_barang.setSelectionBackground(new java.awt.Color(0, 153, 153));
        tb_barang.setShowVerticalLines(false);
        tb_barang.getTableHeader().setReorderingAllowed(false);
        tb_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_barangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_barang);
        tb_barang.getAccessibleContext().setAccessibleName("");

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 80, 668, 190));
        jPanel4.add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 220, 30));

        btncari.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btncari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Cari2.png"))); // NOI18N
        btncari.setText("CARI");
        btncari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btncariMouseEntered(evt);
            }
        });
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });
        jPanel4.add(btncari, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, -1, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 680, 290));

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
        jPanel1.add(tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 390, 110, 40));

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
        jPanel1.add(simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 390, 110, 40));

        hapus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/trash.png"))); // NOI18N
        hapus.setText("HAPUS");
        hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hapusMouseEntered(evt);
            }
        });
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        jPanel1.add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 390, 110, 40));

        edit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Edit.png"))); // NOI18N
        edit.setText("EDIT");
        edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editMouseEntered(evt);
            }
        });
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        jPanel1.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 390, 110, 40));

        update.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
        jPanel1.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 390, 110, 40));

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
        jPanel1.add(batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 390, 110, 40));

        KELUAR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        KELUAR.setText("KELUAR");
        KELUAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KELUARActionPerformed(evt);
            }
        });
        jPanel1.add(KELUAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 390, 100, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodeActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        btn_tambah();
        otomatis();
    }//GEN-LAST:event_tambahActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
        btn_update();
    }//GEN-LAST:event_updateActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        btn_edit();
    }//GEN-LAST:event_editActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
        btn_hapus();
    }//GEN-LAST:event_hapusActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // TODO add your handling code here:
         btn_cari();
    }//GEN-LAST:event_btncariActionPerformed

    private void tb_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_barangMouseClicked
        data_tabel();
    }//GEN-LAST:event_tb_barangMouseClicked

    private void beliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_beliActionPerformed

    private void namabrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namabrgActionPerformed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_namabrgActionPerformed

    private void tambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahMouseEntered
        // TODO add your handling code here:
         tambah.setBackground(new Color(102,204,255));
        tambah.setForeground(Color.black);
    }//GEN-LAST:event_tambahMouseEntered

    private void updateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseEntered
        // TODO add your handling code here:
         update.setBackground(new Color(102,204,255));
        update.setForeground(Color.black);
    }//GEN-LAST:event_updateMouseEntered

    private void editMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseEntered
        // TODO add your handling code here:
//         edit.setBackground(new Color(102,204,255));
//        edit.setForeground(Color.black);
    }//GEN-LAST:event_editMouseEntered

    private void hapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusMouseEntered
        // TODO add your handling code here:
         hapus.setBackground(new Color(0,153,153));
        hapus.setForeground(Color.black);
    }//GEN-LAST:event_hapusMouseEntered

    private void btncariMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncariMouseEntered
        // TODO add your handling code here:
         tambah.setBackground(new Color(102,204,255));
        tambah.setForeground(Color.black);
    }//GEN-LAST:event_btncariMouseEntered

    private void batalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalMouseEntered
        // TODO add our handling code here:
        
    }//GEN-LAST:event_batalMouseEntered

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        // TODO add your handling code here:
        btn_batal();
    }//GEN-LAST:event_batalActionPerformed

    private void simpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_simpanMouseEntered

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        btn_simpan();
        
    }//GEN-LAST:event_simpanActionPerformed

    private void KELUARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KELUARActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_KELUARActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton KELUAR;
    private javax.swing.JButton batal;
    private javax.swing.JTextField beli;
    private javax.swing.JButton btncari;
    private javax.swing.JTextField cari;
    private javax.swing.JButton edit;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField jual;
    private javax.swing.JTextField kode;
    private javax.swing.JComboBox<String> namabrg;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField stok;
    private javax.swing.JButton tambah;
    private javax.swing.JTable tb_barang;
    private javax.swing.JComboBox<String> ukuran;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
//public Connection setKoneksi(){
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn=DriverManager.getConnection("jdbc:mysql://localhost/papantulis","root","");
////            st=conn.createStatement();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null,"Koneksi Gagal :" +e);
//        }
//       return conn; 
//    }


}
