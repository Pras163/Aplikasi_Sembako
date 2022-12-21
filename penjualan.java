
package tampilan;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author TOSHIBA DC
 */
public class penjualan extends javax.swing.JInternalFrame {
//  String nama = Login.getU_nama();
private DefaultTableModel TabModel;




PreparedStatement pst = null;



    Connection conn;
    Statement st;
    ResultSet rs;
   
    /**
     * Creates new form barang
     */
    public penjualan() {
        initComponents();
        
//        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
//         txtpembeli.setText(nama);
    siapIsi(false);
   
    
        tombolNormal();
        tgl();
        Locale locale = new Locale ("id","ID");
        Locale.setDefault(locale);
       
        subtotTF.setVisible(false);
        Object header[]={"Kode Barang","Nama Barang","Ukuran","Harga Barang","jumlah","subtotal"};
        TabModel=new DefaultTableModel(null, header);
    }
    
    public void tampilUser(String user){
        txtpembeli.setText(user);
    }
    
    public String Kobe, Naba,Ukur,HargaJual,Stok;

    public String Koba() {
        return Kobe;
    }

    ;
    public String Namaba() {
        return Naba;
    }

    ;
    public String Ukuran() {
        return Ukur;
    }

    ;
    public String Harga() {
        return HargaJual;
    }

    ;
    
    
    
    public String stock() {
        return Stok;
    }

   
private void bersih(){
        no_transaksi.setText("");
        txtpembeli.setText("");
         stok_brg.setText("");
        kode_brg.setText("");
        nm_brg.setText("");
        hjual.setText("");
        jumlah_brg.setText("");
        subtotTF.setText("0");
        total_byr.setText("0");
        ubayar.setText("0");
        ukembali.setText("0");
    }

    private void siapIsi(boolean a){;
        no_transaksi.setEnabled(a);
        txtpembeli.setEnabled(a);
        kode_brg.setEnabled(a);
        nm_brg.setEnabled(a);
        hjual.setEnabled(a);
        jumlah_brg.setEnabled(a);
        total_byr.setEnabled(a);
        ubayar.setEnabled(a);
        ukembali.setEnabled(a);
    }
    
    private void tombolNormal(){
        tambahBT.setEnabled(true);
        simpanBT.setEnabled(false);
        tbhBrgBT.setEnabled(false);
        krgBrgBT.setEnabled(false);
        cariBrgBT.setEnabled(false);
    }
    
      private void tgl(){
    Date date = new Date();
        tgl_transaksi.setDate(date);
}
      
    
    private void otomatis(){
        try {
            setKoneksi();
            String sql="select right (no_transaksi,2)+1 from tb_penjualan";
            ResultSet rs=st.executeQuery(sql);
            if(rs.next()){
                rs.last();
                String no=rs.getString(1);
                while (no.length()<3){
                    no="0"+no;
                    no_transaksi.setText("T"+no);}
                }
            else
            {
                no_transaksi.setText("T001"); 
        }
        } catch (Exception e) 
        {
        }
    } 
    private void cekstock(){
        try{
            setKoneksi();
            rs=st.executeQuery("SELECT *from barang where kode_barang='" + k.getText() + "'");
            
            if (rs.next());
            k.setText(rs.getString("kode_barang"));
            
            p.setText(rs.getString("stok"));
             } catch (Exception e) {
            
             }
          finally{
                
            }
        Integer a = Integer.parseInt(p.getText());
        Integer b = Integer.parseInt(j.getText());
        Integer c = a + b;
        stok_brg.setText(String.valueOf(c));
        
            try{
            setKoneksi();
            String sql="update barang set stok='"+stok_brg.getText()+"' where kode_barang='"+k.getText()+"'";
            st.executeUpdate(sql);
           
        } 
        catch(Exception e){
        
        }
        

        }
    
    public void ambildata() {
        Integer a = Integer.parseInt(stok_brg.getText());
        Integer b = Integer.parseInt(jumlah_brg.getText());
        Integer c = a - b;
        stok_brg.setText(String.valueOf(c));
        
            try{
            setKoneksi();
            String sql="update barang set stok='"+stok_brg.getText()+"' where kode_barang='"+kode_brg.getText()+"'";
            st.executeUpdate(sql);
           
        } 
        catch(Exception e){
        
        }
       finally{
                
            }
        
        try {
            tabelpenjualan.setModel(TabModel);
                String kolom1 = kode_brg.getText();
                String kolom2 = nm_brg.getText();
                String kolom3 = ukurTF.getText();
                String kolom4 = hjual.getText();
                String kolom5 = jumlah_brg.getText();
                String kolom6 = subtotTF.getText();
                String[] kolom = {kolom1, kolom2, kolom3, kolom4, kolom5,kolom6};
                TabModel.addRow(kolom);
          }
          catch (Exception ex) {
              JOptionPane.showMessageDialog(null, "Data gagal disimpan");
          }     
    }
    
    private void simpan(){
        setKoneksi();
        try {
            Date skrg=new Date();
            SimpleDateFormat frm=new SimpleDateFormat("yyyy-MM-dd");
            String tgl=frm.format(skrg); 
            
            int t = tabelpenjualan.getRowCount();
            for(int i=0;i<t;i++)    
            {
            String kdbrng=tabelpenjualan.getValueAt(i, 0).toString();
            String nmbrng=tabelpenjualan.getValueAt(i, 1).toString();
             String ukur=tabelpenjualan.getValueAt(i, 2).toString();
            
            int harga= Integer.parseInt(tabelpenjualan.getValueAt(i, 3).toString());
            int jml= Integer.parseInt(tabelpenjualan.getValueAt(i, 4).toString());
            int subtot= Integer.parseInt(tabelpenjualan.getValueAt(i, 5).toString());
         
            String sql ="insert into tb_penjualan values('"+no_transaksi.getText()+"','"+tgl+"','"+txtpembeli.getText()+"','"
                    +kdbrng+"','"+nmbrng+"','"+ukur+"','"+harga+"','"+jml+"','"+subtot+"','"+total_byr.getText()+"','"+ubayar.getText()+"','"+ukembali.getText()+"')";
            
             st.executeUpdate(sql);
//             JOptionPane.showMessageDialog(null, "Simpan Transaksi Penjualan Berhasil");
            }         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Simpan Transaksi Penjualan Gagal");
        }
    }
    
    
    private void hapusdatadaritabel() {
        int a = tabelpenjualan.getSelectedRow();
        if(a == -1){
        }TabModel.removeRow(a);
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
        total_byr = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        kode_brg = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        txtpembeli = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        hjual = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        nm_brg = new javax.swing.JTextField();
        jumlah_brg = new javax.swing.JTextField();
        no_transaksi = new javax.swing.JTextField();
        tgl_transaksi = new com.toedter.calendar.JDateChooser();
        tbhBrgBT = new javax.swing.JButton();
        krgBrgBT = new javax.swing.JButton();
        cariBrgBT = new javax.swing.JButton();
        p = new javax.swing.JTextField();
        stok_brg = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelpenjualan = new javax.swing.JTable();
        ukembali = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ubayar = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        simpanBT = new javax.swing.JButton();
        tambahBT = new javax.swing.JButton();
        ukurTF = new javax.swing.JTextField();
        k = new javax.swing.JTextField();
        j = new javax.swing.JTextField();
        subtotTF = new javax.swing.JTextField();
        keluar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1160, 570));
        setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1160, 700));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setToolTipText("");
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Transaksi Penjualan");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 480, -1));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 490, 10));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1160, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)), "Silahkan Transaksi Penjualan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        total_byr.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        total_byr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_byr.setText("0");
        total_byr.setBorder(null);
        total_byr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_byrActionPerformed(evt);
            }
        });
        total_byr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                total_byrKeyPressed(evt);
            }
        });
        jPanel3.add(total_byr, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 260, 50));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("No Transaksi          :");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Tanggal Transaksi  :");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Pembeli                 :");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 200, 200, 10));

        kode_brg.setBorder(null);
        kode_brg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kode_brgKeyReleased(evt);
            }
        });
        jPanel3.add(kode_brg, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 140, 30));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 380, 10));

        txtpembeli.setBorder(null);
        jPanel3.add(txtpembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 250, 30));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 380, 10));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Jumlah");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, -1, -1));

        hjual.setBorder(null);
        jPanel3.add(hjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 230, 30));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 380, 10));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Stok :");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 190, 10));
        jPanel3.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 230, 10));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Nama Barang");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, -1));
        jPanel3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, 230, 10));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Harga Jual");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, -1, -1));

        nm_brg.setBorder(null);
        jPanel3.add(nm_brg, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 230, 30));

        jumlah_brg.setBorder(null);
        jumlah_brg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jumlah_brgKeyPressed(evt);
            }
        });
        jPanel3.add(jumlah_brg, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 170, 120, 30));

        no_transaksi.setBorder(null);
        jPanel3.add(no_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 250, 30));

        tgl_transaksi.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tgl_transaksiPropertyChange(evt);
            }
        });
        jPanel3.add(tgl_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 250, 30));

        tbhBrgBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tbhBrgBT.setText("+");
        tbhBrgBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbhBrgBTMouseEntered(evt);
            }
        });
        tbhBrgBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbhBrgBTActionPerformed(evt);
            }
        });
        jPanel3.add(tbhBrgBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 170, -1, 30));

        krgBrgBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        krgBrgBT.setText("-");
        krgBrgBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                krgBrgBTMouseEntered(evt);
            }
        });
        krgBrgBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                krgBrgBTActionPerformed(evt);
            }
        });
        jPanel3.add(krgBrgBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 170, -1, 30));

        cariBrgBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Cari2.png"))); // NOI18N
        cariBrgBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cariBrgBTMouseEntered(evt);
            }
        });
        cariBrgBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariBrgBTActionPerformed(evt);
            }
        });
        jPanel3.add(cariBrgBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, -1, 30));
        jPanel3.add(p, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 10, 0));

        stok_brg.setBorder(null);
        jPanel3.add(stok_brg, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 110, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Kode Barang");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 990, 240));

        jTextField6.setBorder(null);
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 230, 30));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)), "Keranjang Belanja", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N

        tabelpenjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelpenjualan.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tabelpenjualan.setRowHeight(25);
        tabelpenjualan.setRowSelectionAllowed(false);
        tabelpenjualan.setSelectionBackground(new java.awt.Color(0, 153, 153));
        tabelpenjualan.setShowVerticalLines(false);
        tabelpenjualan.getTableHeader().setReorderingAllowed(false);
        tabelpenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpenjualanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelpenjualan);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 990, 140));

        ukembali.setBorder(null);
        ukembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ukembaliActionPerformed(evt);
            }
        });
        jPanel1.add(ukembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 510, 210, 30));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 540, 260, 10));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Bayar    :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 480, 60, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Kembali :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 520, -1, -1));

        ubayar.setBorder(null);
        ubayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubayarActionPerformed(evt);
            }
        });
        ubayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ubayarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ubayarKeyReleased(evt);
            }
        });
        jPanel1.add(ubayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 470, 210, 30));
        jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 500, 260, 10));

        simpanBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        simpanBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Print.png"))); // NOI18N
        simpanBT.setText("SIMPAN");
        simpanBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                simpanBTMouseEntered(evt);
            }
        });
        simpanBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanBTActionPerformed(evt);
            }
        });
        jPanel1.add(simpanBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 480, 110, 40));

        tambahBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tambahBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Tambah.png"))); // NOI18N
        tambahBT.setText("TAMBAH");
        tambahBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tambahBTMouseEntered(evt);
            }
        });
        tambahBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahBTActionPerformed(evt);
            }
        });
        jPanel1.add(tambahBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, 110, 40));
        jPanel1.add(ukurTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 500, 0, 30));
        jPanel1.add(k, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 490, -1, 0));
        jPanel1.add(j, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 490, 0, 0));

        subtotTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subtotTFActionPerformed(evt);
            }
        });
        jPanel1.add(subtotTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(714, 550, -1, 0));

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
        jPanel1.add(keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 480, 110, 40));

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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ukembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ukembaliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ukembaliActionPerformed

    private void ubayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubayarActionPerformed

    private void tbhBrgBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbhBrgBTActionPerformed
        // TODO add your handling code here:
        
        int harga=Integer.parseInt(hjual.getText());
        int jml=Integer.parseInt(jumlah_brg.getText());
        int stok=Integer.parseInt(stok_brg.getText());
        int total=Integer.parseInt(total_byr.getText());
        
        if(jml>stok){
             JOptionPane.showMessageDialog(null, "Stok barang tidak mencukupi");
        }else{
                 
            int subtot=harga*jml;
            subtotTF.setText(Integer.toString(subtot));
            
            int totbay=total+(harga*jml);
            total_byr.setText(Integer.toString(totbay));
            
            ambildata();
            
        kode_brg.setText("");
        nm_brg.setText("");
        hjual.setText("");
        jumlah_brg.setText("");
        
        }
    }//GEN-LAST:event_tbhBrgBTActionPerformed

    private void simpanBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanBTActionPerformed
        // TODO add your handling code here:
        if(no_transaksi.getText().equals("") || txtpembeli.getText().equals("") || ubayar.getText().equals("0") || ukembali.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lengkapi inputan penjualan barang");
        } else{
            simpan();  
            int pesan=JOptionPane.showConfirmDialog(null, "Cetak Kwitansi Nota","Cetak",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
               
            if(pesan==JOptionPane.YES_OPTION){
                   cetak_nota();
               }else {
                   JOptionPane.showMessageDialog(null, "Simpan Transaksi Berhasil");
               }
            bersih();
        }
    }//GEN-LAST:event_simpanBTActionPerformed

    private void total_byrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_byrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_byrActionPerformed

    private void cariBrgBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariBrgBTActionPerformed
        // TODO add your handling code here:
         boolean closable = true;
         brg fDB = new  brg(null, closable);
        fDB.fAB = this;
        fDB.setVisible(true);
        fDB.setResizable(true);
       
        kode_brg.setText(Kobe);
         nm_brg.setText(Naba);
          ukurTF.setText(Ukur);
          hjual.setText(HargaJual);
           stok_brg.setText(Stok);
        
           
    }//GEN-LAST:event_cariBrgBTActionPerformed

    private void krgBrgBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_krgBrgBTActionPerformed
        // TODO add your handling code here:
         cekstock();
         int baris = tabelpenjualan.getSelectedRow();
        int qty=Integer.parseInt(tabelpenjualan.getModel().getValueAt(baris, 4).toString());
        int total=Integer.parseInt(total_byr.getText());
        int harga=Integer.parseInt(tabelpenjualan.getModel().getValueAt(baris, 3).toString());
        
        int totbay=total-(harga*qty);
        total_byr.setText(Integer.toString(totbay)); 
        hapusdatadaritabel();
    }//GEN-LAST:event_krgBrgBTActionPerformed

    private void tabelpenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpenjualanMouseClicked
        // TODO add your handling code here:
         tabelpenjualan.setRowSelectionAllowed(true);
        int a = tabelpenjualan.getSelectedRow();
        String kolom1 = tabelpenjualan.getValueAt(a,0).toString();
        String kolom2 = tabelpenjualan.getValueAt(a,1).toString();
        String kolom3 = tabelpenjualan.getValueAt(a,2).toString();
        String kolom4 = tabelpenjualan.getValueAt(a,3).toString();
        String kolom5 = tabelpenjualan.getValueAt(a,4).toString();
        String kolom6 = tabelpenjualan.getValueAt(a,5).toString();
         k.setText(tabelpenjualan.getModel().getValueAt(a, 00).toString());
          j.setText(tabelpenjualan.getModel().getValueAt(a, 4).toString());
        
    }//GEN-LAST:event_tabelpenjualanMouseClicked

    private void total_byrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total_byrKeyPressed
        // TODO add your handling code here:
         char karakter = evt.getKeyChar();
        if (!(Character.isDigit(karakter) || karakter==KeyEvent.VK_BACK_SPACE))
        {
            evt.consume();
        }
    }//GEN-LAST:event_total_byrKeyPressed

    private void jumlah_brgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlah_brgKeyPressed
        // TODO add your handling code here:
         char karakter = evt.getKeyChar();
        if (!(Character.isDigit(karakter) || karakter==KeyEvent.VK_BACK_SPACE))
        {
            evt.consume();
        }
    }//GEN-LAST:event_jumlah_brgKeyPressed

    private void ubayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ubayarKeyReleased
        // TODO add your handling code here:
         
    }//GEN-LAST:event_ubayarKeyReleased

    private void tambahBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBTActionPerformed
        // TODO add your handling code here:
        if(tambahBT.getText().equalsIgnoreCase("TAMBAH")){
            tambahBT.setText("BERSIH");
            siapIsi(true);
            bersih();
            otomatis();
            txtpembeli.requestFocus();
            no_transaksi.setEnabled(false);
            simpanBT.setEnabled(true);
            tbhBrgBT.setEnabled(true);
            krgBrgBT.setEnabled(true);
            cariBrgBT.setEnabled(true);
        }else{
            bersih();
            siapIsi(false);
            tambahBT.setText("Tambah");
            TabModel.getDataVector().removeAllElements();
            TabModel.fireTableDataChanged();
            tombolNormal();
        }
        
    }//GEN-LAST:event_tambahBTActionPerformed

    private void ubayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ubayarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int total=Integer.parseInt(total_byr.getText());
            int bayar=Integer.parseInt(ubayar.getText());
            if(bayar<total){
                JOptionPane.showMessageDialog(null, "Jumlah bayar tidak mencukupi");
                ubayar.requestFocus();
            } else{
                int kembali=bayar-total;
                ukembali.setText(Integer.toString(kembali));
            }
        }
    }//GEN-LAST:event_ubayarKeyPressed

    private void tgl_transaksiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tgl_transaksiPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_transaksiPropertyChange

    private void kode_brgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_brgKeyReleased
        // TODO add your handling code here:
        try{
            setKoneksi();
            rs=st.executeQuery("SELECT *from barang where kode_barang='" + kode_brg.getText() + "'");
            
            if (rs.next());
            kode_brg.setText(rs.getString("kode_barang"));
            
            p.setText(rs.getString("stok"));
             } catch (Exception e) {
            
             }
    }//GEN-LAST:event_kode_brgKeyReleased

    private void cariBrgBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cariBrgBTMouseEntered
        // TODO add your handling code here:
         cariBrgBT.setBackground(new Color(102,204,255));
        cariBrgBT.setForeground(Color.black);
    }//GEN-LAST:event_cariBrgBTMouseEntered

    private void tbhBrgBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbhBrgBTMouseEntered
        // TODO add your handling code here:
         tbhBrgBT.setBackground(new Color(102,204,255));
         tbhBrgBT.setForeground(Color.black);
    }//GEN-LAST:event_tbhBrgBTMouseEntered

    private void krgBrgBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_krgBrgBTMouseEntered
        // TODO add your handling code here:
         krgBrgBT.setBackground(new Color(102,204,255));
         krgBrgBT.setForeground(Color.black);
         
    }//GEN-LAST:event_krgBrgBTMouseEntered

    private void tambahBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahBTMouseEntered
        // TODO add your handling code here:
        
          tambahBT.setBackground(new Color(102,204,255));
         tambahBT.setForeground(Color.black);
        
    }//GEN-LAST:event_tambahBTMouseEntered

    private void simpanBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanBTMouseEntered
        // TODO add your handling code here:
        
          simpanBT.setBackground(new Color(102,204,255));
         simpanBT.setForeground(Color.black);
    }//GEN-LAST:event_simpanBTMouseEntered

    private void subtotTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subtotTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subtotTFActionPerformed

    private void keluarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keluarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_keluarMouseEntered

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_keluarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cariBrgBT;
    private javax.swing.JTextField hjual;
    private javax.swing.JTextField j;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jumlah_brg;
    private javax.swing.JTextField k;
    private javax.swing.JButton keluar;
    private javax.swing.JTextField kode_brg;
    private javax.swing.JButton krgBrgBT;
    private javax.swing.JTextField nm_brg;
    private javax.swing.JTextField no_transaksi;
    private javax.swing.JTextField p;
    private javax.swing.JButton simpanBT;
    private javax.swing.JTextField stok_brg;
    private javax.swing.JTextField subtotTF;
    private javax.swing.JTable tabelpenjualan;
    private javax.swing.JButton tambahBT;
    private javax.swing.JButton tbhBrgBT;
    private com.toedter.calendar.JDateChooser tgl_transaksi;
    private javax.swing.JTextField total_byr;
    private javax.swing.JTextField txtpembeli;
    private javax.swing.JTextField ubayar;
    private javax.swing.JTextField ukembali;
    private javax.swing.JTextField ukurTF;
    // End of variables declaration//GEN-END:variables
public Connection setKoneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/db_sembako","root","");
            st=conn.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Koneksi Gagal :" +e);
        }
       return conn; 
    }

    
    void cetak_nota(){
        try {
            String NamaFile = "src/laporan/kwitansi_jual.jasper";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/db_sembako","root","");
            HashMap param = new HashMap();
            param.put("notajual",no_transaksi.getText());
            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, koneksi);
            JasperViewer.viewReport(JPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat dicetak!","Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
    }

}




