
package GUI;

import HomePage.HmAdmin;
import Sistem.makePreview;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mainkoneksi.Koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class LD_Pemakaian extends javax.swing.JFrame {
    private final Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    Statement st ;
    ResultSet rs;
    makePreview mp = new makePreview();
    public String Isi = null;
    

    
    public LD_Pemakaian() {
        initComponents();
        dataTabel();
        autoNomor();
        isiComboKel();
        btEdit.setEnabled(false);
    }
    
     protected void dataTabel(){
        Object [] baris = {"ID","ID Pengeluaran","Tanggal","No. Gangguan","Keluhan","Tindakan","ID Material","Nama Material","ID Pelanggan","Nama Pelanggan","Alamat","Qty","Satuan","Teknisi"};
        tabmode = new DefaultTableModel(null, baris);
        tbPemakaian.setModel(tabmode);
        String sql="select*from pemakaian";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
            String id = hasil.getString("id_pemakaian");
            String idp = hasil.getString("id_pengeluaran");
            String tgl = hasil.getString("tgl_pemakaian");
            String nog = hasil.getString("no_gangguan");
            String klu = hasil.getString("keluhan");
            String tin = hasil.getString("tindakan");
            String idm = hasil.getString("id_material");
            String nm = hasil.getString("nm_material");
            String idpel = hasil.getString("id_pelanggan");
            String nmp = hasil.getString("nm_pelanggan");
            String almt = hasil.getString("alamat");
            String qty = hasil.getString("qty");
            String sat = hasil.getString("satuan");
            String tek = hasil.getString("nm_teknisi");
           
            String[]data = {id,idp,tgl,nog,klu,tin,idm,nm,idpel,nmp,almt,qty,sat,tek};
            tabmode.addRow(data);
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void autoNomor(){
         try{
             String sql ="SELECT * FROM pemakaian order by id_pemakaian desc";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             if (rs.next()) {
                 String idg = rs.getString("id_pemakaian").substring(3);
                 String AN = "" + (Integer.parseInt(idg)+1);
                 String Nol = "";
                if (AN.length()==1) {
                     Nol ="000";
                 }if (AN.length()==2) {
                     Nol ="00";
                 } if (AN.length()==3) {
                     Nol="0";
                 }  if (AN.length()==4) {
                     Nol="";
                 }
                 txId.setText("PEM"+Nol+AN);
             } else {
                 txId.setText("PEM0001");
             }
         } catch (Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
     }
    
    public void tabelKlik(){
        int bar = tbPemakaian.getSelectedRow();
        txId.setText(tabmode.getValueAt(bar, 0).toString());
        cbPengeluaran.setSelectedItem(tabmode.getValueAt(bar, 1).toString());
        String date = String.valueOf(tabmode.getValueAt(bar, 2).toString());
        java.util.Date tanggal = null;
        try {
        tanggal = new SimpleDateFormat("dd MMMM yyyy").parse(date);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        txTgl.setDate(tanggal);
        txNoGg.setText(tabmode.getValueAt(bar, 3).toString());
        txKel.setText(tabmode.getValueAt(bar, 4).toString());
        txTin.setText(tabmode.getValueAt(bar, 5).toString());
        txIdM.setText(tabmode.getValueAt(bar, 6).toString());
        txNmMat.setText(tabmode.getValueAt(bar, 7).toString());
        txidPel.setText(tabmode.getValueAt(bar, 8).toString());
        txnmPel.setText(tabmode.getValueAt(bar, 9).toString());
        txAlamat.setText(tabmode.getValueAt(bar, 10).toString());
        txQty.setText(tabmode.getValueAt(bar, 11).toString());
        cbSat.setSelectedItem(tabmode.getValueAt(bar, 12).toString());
        txTek.setText(tabmode.getValueAt(bar, 13).toString());
        btEdit.setEnabled(true);
    }
    
   public void Kosong(){
        txId.setText("");
        cbPengeluaran.setSelectedItem(0);
        txTgl.setDate(null);
        txNoGg.setText("");
        txKel.setText("");
        txTin.setText("");
        txIdM.setText("");
        txNmMat.setText("");
        txidPel.setText("");
        txnmPel.setText("");
        txAlamat.setText("");
        txQty.setText("");
        cbSat.setSelectedItem(0);
        txTek.setText("");
        dataTabel();
        autoNomor();
    }
   
    public void isiComboKel(){
        try {
             String sql ="SELECT * FROM pengeluaran";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             while(rs.next()){
                 cbPengeluaran.addItem(rs.getString("id_pengeluaran"));
             }
             rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "gagal "+e);
        }
    }
    
    public void comboKlikKel(){
        try {
            String sql ="SELECT * FROM pengeluaran where id_pengeluaran='"+String.valueOf(cbPengeluaran.getSelectedItem())+"'";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                txIdM.setText(rs.getString("id_material"));
                txNmMat.setText(rs.getString("nm_material"));
                txQty.setText(rs.getString("qty"));
                cbSat.setSelectedItem(rs.getString("satuan"));
                txTek.setText(rs.getString("nm_teknisi"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
   
    public void Simpan(){
        String sql = "insert into pemakaian values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            if (txId.getText().equals("")||txNoGg.getText().equals("")||
                    txTek.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txId.getText());
            stat.setString(2, String.valueOf(cbPengeluaran.getSelectedItem()));
            stat.setString(3, new SimpleDateFormat("dd MMMM yyyy").format(txTgl.getDate()));
            stat.setString(4, txNoGg.getText());
            stat.setString(5, txKel.getText());
            stat.setString(6, txTin.getText());
            stat.setString(7, txIdM.getText());
            stat.setString(8, txNmMat.getText());
            stat.setString(9, txidPel.getText());
            stat.setString(10, txnmPel.getText());
            stat.setString(11, txAlamat.getText());
            stat.setString(12, txQty.getText());
            stat.setString(13, String.valueOf(cbSat.getSelectedItem()));
            stat.setString(14, txTek.getText());
            stat.executeUpdate();
            stat.close();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil disimpan");
            Kosong();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Data gagal disimpan "+e);
        }
    }
    
    public void Ubah(){
        try{
            String sql ="update pemakaian set id_pengeluaran=?, tgl_pemakaian=?, no_gangguan=?, "
                    + "keluhan=?, tindakan=?, id_material=?, nm_material=?, id_pelanggan=?, nm_pelanggan=?, alamat=?, qty=?, "
                    + "satuan=?, nm_teknisi=? where id_pemakaian='"+txId.getText()+"'";
            if (txId.getText().equals("")||txNoGg.getText().equals("")||
                    txTek.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,String.valueOf(cbPengeluaran.getSelectedItem()));
            stat.setString(2, new SimpleDateFormat("dd MMMM yyyy").format(txTgl.getDate()));
            stat.setString(3 ,txNoGg.getText());
            stat.setString(4,txKel.getText());
            stat.setString(5,txTin.getText());
            stat.setString(6,txIdM.getText());
            stat.setString(7,txNmMat.getText());
            stat.setString(8,txidPel.getText());
            stat.setString(9,txnmPel.getText());
            stat.setString(10,txAlamat.getText());
            stat.setString(11,txQty.getText());
            stat.setString(12,String.valueOf(cbSat.getSelectedItem()));
            stat.setString(13,txTek.getText());
            stat.executeUpdate();
            stat.close();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil diubah");
            Kosong();
            } 
        }catch (SQLException e){
            JOptionPane.showMessageDialog(rootPane, "Data gagal di ubah "+e);
        }
        btEdit.setEnabled(true);
    }
    
    public void Hapus(){
        if (txId.getText().equals("")||txNoGg.getText().equals("")||
                    txTek.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
        } else {
            int ok = JOptionPane.showConfirmDialog(rootPane, "Anda yakin ingin menghapus data?","Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "delete from pemakaian where id_pemakaian='"+txId.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(rootPane, "Data berhasil di hapus");
                Kosong();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(rootPane, "Data gagal di hapus "+e);
            }
        } else {
            Kosong();
        }
        }
    } 
    
    public void Cari(){
        int row=tbPemakaian.getRowCount();
        for (int x=0;x<row;x++){
            tabmode.removeRow(0);
        }
        try{
            ResultSet rs=conn.createStatement().executeQuery("Select * from "
                    + "pemakaian where id_pengeluaran like '%"+txCari.getText()+"%' or "
                    + "nm_material like '%"+txCari.getText()+"%' or "
                    + "id_material like '%"+txCari.getText()+"%' or "
                    + "no_gangguan like '%"+txCari.getText()+"%' or "
                    + "id_pelanggan like '%"+txCari.getText()+"%' or "
                    + "nm_teknisi like '%"+txCari.getText()+"%' or "
                    + "nm_pelanggan like '%"+txCari.getText()+"%'");
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14)};
                tabmode.addRow(data);
            }
        }catch (SQLException ex){
            Logger.getLogger(LD_Pemakaian.class.getName()).log(Level.SEVERE, null, ex);  
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        window = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPemakaian = new javax.swing.JTable();
        btCari = new javax.swing.JLabel();
        txCari = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        txId = new javax.swing.JTextField();
        txNoGg = new javax.swing.JTextField();
        txKel = new javax.swing.JTextField();
        txIdM = new javax.swing.JTextField();
        txTin = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbPengeluaran = new javax.swing.JComboBox();
        txTgl = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        txidPel = new javax.swing.JTextField();
        txNmMat = new javax.swing.JTextField();
        txnmPel = new javax.swing.JTextField();
        txAlamat = new javax.swing.JTextField();
        txQty = new javax.swing.JTextField();
        txTek = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbSat = new javax.swing.JComboBox();
        btSave = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btBatal = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        btCetak = new javax.swing.JButton();
        txTglB = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        txTglA = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        window.setBackground(new java.awt.Color(255, 0, 0));
        window.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        window.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        window.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                windowMouseDragged(evt);
            }
        });
        window.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                windowMousePressed(evt);
            }
        });
        window.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit.setText("X");
        exit.setToolTipText("keluar");
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        window.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, 20, 20));

        jPanel1.add(window, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Input Pemakaian Material");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, -1, -1));

        tbPemakaian.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPemakaian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemakaianMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPemakaian);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 860, 200));

        btCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon/search.png"))); // NOI18N
        btCari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btCariMouseClicked(evt);
            }
        });
        jPanel1.add(btCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 390, -1, -1));

        txCari.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txCari.setToolTipText("pencarian");
        txCari.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txCariActionPerformed(evt);
            }
        });
        jPanel1.add(txCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, 140, -1));

        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 410, 140, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txId.setEditable(false);
        jPanel2.add(txId, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 140, -1));
        jPanel2.add(txNoGg, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 140, -1));
        jPanel2.add(txKel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 220, -1));
        jPanel2.add(txIdM, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 220, -1));
        jPanel2.add(txTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 220, -1));

        jLabel1.setText("Tanggal");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 110, -1));

        jLabel3.setText("ID Pengeluaran");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 110, -1));

        jLabel4.setText("Keluhan");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 110, -1));

        jLabel5.setText("No. Gangguan");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 110, -1));

        jLabel7.setText("Tindakan");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 110, -1));

        jLabel8.setText("ID Material");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 110, -1));

        jLabel15.setText("ID");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 110, -1));

        cbPengeluaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih -" }));
        cbPengeluaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbPengeluaranMouseClicked(evt);
            }
        });
        cbPengeluaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPengeluaranActionPerformed(evt);
            }
        });
        jPanel2.add(cbPengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 140, -1));

        txTgl.setDateFormatString("dd MMMM yyyy");
        jPanel2.add(txTgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 140, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 410, 240));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(txidPel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 220, -1));
        jPanel4.add(txNmMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 220, -1));
        jPanel4.add(txnmPel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 220, -1));
        jPanel4.add(txAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 220, -1));
        jPanel4.add(txQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 50, -1));
        jPanel4.add(txTek, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 220, -1));

        jLabel13.setText("Satuan");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 110, -1));

        jLabel12.setText("Qty");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 110, -1));

        jLabel9.setText("ID Pelanggan");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 110, -1));

        jLabel10.setText("Nama Pelanggan");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 110, -1));

        jLabel11.setText("Alamat");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 110, -1));

        jLabel14.setText("Teknisi");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 110, -1));

        jLabel6.setText("Nama Material");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 110, -1));

        cbSat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "pcs", "box", "lusin", "karton", " " }));
        jPanel4.add(cbSat, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 70, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 410, 240));

        btSave.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon/save.png"))); // NOI18N
        btSave.setText("Simpan");
        btSave.setToolTipText("simpan");
        btSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btSave.setFocusPainted(false);
        btSave.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btSave.setIconTextGap(8);
        btSave.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 90, 30));

        btEdit.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon/edit.png"))); // NOI18N
        btEdit.setText("Ubah");
        btEdit.setToolTipText("ubah");
        btEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btEdit.setFocusPainted(false);
        btEdit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btEdit.setIconTextGap(8);
        btEdit.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });
        jPanel1.add(btEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 370, 90, 30));

        btBatal.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon/refresh.png"))); // NOI18N
        btBatal.setText("Batal");
        btBatal.setToolTipText("batal");
        btBatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btBatal.setFocusPainted(false);
        btBatal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btBatal.setIconTextGap(8);
        btBatal.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBatalActionPerformed(evt);
            }
        });
        jPanel1.add(btBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 370, 90, 30));

        btHapus.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon/delete.png"))); // NOI18N
        btHapus.setText("Hapus");
        btHapus.setToolTipText("hapus");
        btHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btHapus.setFocusPainted(false);
        btHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btHapus.setIconTextGap(8);
        btHapus.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, 90, 30));

        btCetak.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon/printer.png"))); // NOI18N
        btCetak.setText("Cetak");
        btCetak.setToolTipText("cetak");
        btCetak.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCetak.setFocusPainted(false);
        btCetak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btCetak.setIconTextGap(8);
        btCetak.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCetakActionPerformed(evt);
            }
        });
        jPanel1.add(btCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 70, 90, 30));

        txTglB.setToolTipText("sampai tanggal");
        txTglB.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(txTglB, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, 100, -1));

        jLabel16.setText("s/d");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, -1, 20));

        txTglA.setToolTipText("dari tanggal");
        txTglA.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(txTglA, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 100, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 660));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        // keluar
        dispose();
    }//GEN-LAST:event_exitMouseClicked
    
    int x,y;
    private void windowMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_windowMouseDragged
        // window drag
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_windowMouseDragged

    private void windowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_windowMousePressed
        // window hold
        x=evt.getX();
        y=evt.getY();
    }//GEN-LAST:event_windowMousePressed

    private void tbPemakaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemakaianMouseClicked
        // tabel klik
        tabelKlik();
    }//GEN-LAST:event_tbPemakaianMouseClicked

    private void btCariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCariMouseClicked
        // cari
        Cari();
    }//GEN-LAST:event_btCariMouseClicked

    private void txCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txCariActionPerformed
        // TODO add your handling code here:
        Cari();
    }//GEN-LAST:event_txCariActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        // simpan
        Simpan();
    }//GEN-LAST:event_btSaveActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        // ubah
        Ubah();
    }//GEN-LAST:event_btEditActionPerformed

    private void btBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatalActionPerformed
        // batal
        Kosong();
        btEdit.setEnabled(false);
    }//GEN-LAST:event_btBatalActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        // hapus
        Hapus();
    }//GEN-LAST:event_btHapusActionPerformed

    private void btCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCetakActionPerformed
        // cetak
        String tagA = String.valueOf(txTglA.getDate());
        String tagB = String.valueOf(txTglB.getDate());
        if (tagA.equals("null") ||  tagB.equals("null")) {
            JOptionPane.showMessageDialog(rootPane, "Pilih tanggal yang akan di cetak!");
        } else {
        makePreview("Pemakaian");
        }
        System.out.println("A = "+tagA);
        System.out.println("B = "+tagB);
    }//GEN-LAST:event_btCetakActionPerformed

    private void cbPengeluaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbPengeluaranMouseClicked
        // TODO add your handling code here:
        comboKlikKel();
    }//GEN-LAST:event_cbPengeluaranMouseClicked

    private void cbPengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPengeluaranActionPerformed
        // TODO add your handling code here:
        comboKlikKel();
    }//GEN-LAST:event_cbPengeluaranActionPerformed

   
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LD_Pemakaian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBatal;
    private javax.swing.JLabel btCari;
    private javax.swing.JButton btCetak;
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btSave;
    private javax.swing.JComboBox cbPengeluaran;
    private javax.swing.JComboBox cbSat;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbPemakaian;
    private javax.swing.JTextField txAlamat;
    private javax.swing.JTextField txCari;
    private javax.swing.JTextField txId;
    private javax.swing.JTextField txIdM;
    private javax.swing.JTextField txKel;
    private javax.swing.JTextField txNmMat;
    private javax.swing.JTextField txNoGg;
    private javax.swing.JTextField txQty;
    private javax.swing.JTextField txTek;
    private com.toedter.calendar.JDateChooser txTgl;
    private com.toedter.calendar.JDateChooser txTglA;
    private com.toedter.calendar.JDateChooser txTglB;
    private javax.swing.JTextField txTin;
    private javax.swing.JTextField txidPel;
    private javax.swing.JTextField txnmPel;
    private javax.swing.JPanel window;
    // End of variables declaration//GEN-END:variables
public void makePreview (String vName){
        String tagA = String.valueOf(new SimpleDateFormat("dd MMMM yyyy").format(txTglA.getDate()));
        String tagB = String.valueOf(new SimpleDateFormat("dd MMMM yyyy").format(txTglB.getDate()));
        try {
            String KopLaporan = getClass().getResource("/IMG/icon_telkom.png").toString();
            String locFile = "src/report/";
            String namaFile = locFile + vName + ".jasper";
            Connection conn = new Koneksi().connect();
            HashMap parameter = new HashMap();
            parameter.put("Logo", KopLaporan);
            parameter.put("txUser", Isi);
            parameter.put("tglA", tagA);
            parameter.put("tglB", tagB);
            File report_file = new File (namaFile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conn);
            JasperViewer.viewReport(jasperPrint, false );
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
