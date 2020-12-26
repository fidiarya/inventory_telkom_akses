
package GUI;

import HomePage.HmAdmin;
import Sistem.makePreview;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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


public class AD_InputMaterial extends javax.swing.JFrame {
    private final Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    Statement st;
    ResultSet rs;
    makePreview mp = new makePreview();
    public String Isi = null;

    
    public AD_InputMaterial() {
        initComponents();
        dataTabel();
        isiCombo();
    }
    
    protected void dataTabel(){
        Object [] baris = {"id Material","Nama Material","id Vendor","Qty","Satuan"};
        tabmode = new DefaultTableModel(null, baris);
        tbStock.setModel(tabmode);
        String sql="select*from tb_material";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
            String id = hasil.getString("id_material");
            String nm = hasil.getString("nm_material");
            String iv = hasil.getString("id_vendor");
            String qty = hasil.getString("qty");
            String sat = hasil.getString("satuan");
           
            String[]data = {id,nm,iv,qty,sat};
            tabmode.addRow(data);
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void Kosong(){
        txidMaterial.setText("");
        txNmMaterial.setText("");
        cbidVendor.setSelectedIndex(0);
        txNmVendor.setText("");
        txQty.setText("");
        cbSat.setSelectedIndex(0);
        txidMaterial.requestFocus();
        dataTabel();
    }
    
    public void tabelKlik(){
        int bar = tbStock.getSelectedRow();
        txidMaterial.setText(tabmode.getValueAt(bar, 0).toString());
        txNmMaterial.setText(tabmode.getValueAt(bar, 1).toString());
        cbidVendor.setSelectedItem(String.valueOf(tabmode.getValueAt(bar, 2).toString()));
        try {
            String sql ="SELECT * FROM tb_vendor where id_vendor='"+String.valueOf(tabmode.getValueAt(bar, 2).toString())+"'";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                txNmVendor.setText(rs.getString("nm_vendor"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        txQty.setText(tabmode.getValueAt(bar, 3).toString());
        String idsat = String.valueOf(tabmode.getValueAt(bar, 4).toString());
        cbSat.setSelectedItem(idsat);
    }
    
    public void isiCombo(){
        try {
             String sql ="SELECT * FROM tb_vendor";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             while(rs.next()){
                 cbidVendor.addItem(rs.getString("id_vendor"));
             }
             rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "gagal "+e);
        }
    }
    
    public void comboKlik(){
        try {
            String sql ="SELECT * FROM tb_vendor where id_vendor='"+String.valueOf(cbidVendor.getSelectedItem())+"'";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                txNmVendor.setText(rs.getString("nm_vendor"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    public void Simpan(){
        String sql = "insert into tb_material values (?,?,?,?,?)";
        try {
            if (txidMaterial.getText().equals("")||txNmMaterial.getText().equals("")||
                    txNmVendor.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txidMaterial.getText());
            stat.setString(2,txNmMaterial.getText());
            stat.setString(3,String.valueOf(cbidVendor.getSelectedItem()));
            stat.setString(4,txQty.getText());
            stat.setString(5,String.valueOf(cbSat.getSelectedItem()));
            stat.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil disimpan");
            Kosong();
            dataTabel();
            } 
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Data gagal disimpan "+e);
        }
    }
    
    public void Ubah(){
        try{
            String sql ="update tb_material set nm_material=?, id_vendor=?, qty=?, satuan=? where id_material='"+txidMaterial.getText()+"'";
            if (txidMaterial.getText().equals("")||txNmMaterial.getText().equals("")||
                    txNmVendor.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
           stat.setString(1,txNmMaterial.getText());
            stat.setString(2,String.valueOf(cbidVendor.getSelectedItem()));
            stat.setString(3,txQty.getText());
            stat.setString(4,String.valueOf(cbSat.getSelectedItem()));
            stat.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil diubah");
            Kosong();
            stat.close();
            } 
        }catch (SQLException e){
            JOptionPane.showMessageDialog(rootPane, "Data gagal di ubah "+e);
        }
    }
    
    public void Hapus(){
        if (txidMaterial.getText().equals("")||txNmMaterial.getText().equals("")||
                    txNmVendor.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
        } else {
            int ok = JOptionPane.showConfirmDialog(rootPane, "Anda yakin ingin menghapus data?","Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "delete from tb_material where id_material='"+txidMaterial.getText()+"'";
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
        int row=tbStock.getRowCount();
        for (int x=0;x<row;x++){
            tabmode.removeRow(0);
        }
        try{
            ResultSet rs=conn.createStatement().executeQuery("Select * from "
                    + "tb_material where nm_material like '%"+txCari.getText()+"%' or id_material like '%"+txCari.getText()+"%' or "
                    + "id_vendor like '%"+txCari.getText()+"%' or satuan like '%"+txCari.getText()+"%'");
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)};
                tabmode.addRow(data);
            }
        }catch (SQLException ex){
            Logger.getLogger(AD_InputMaterial.class.getName()).log(Level.SEVERE, null, ex);  
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
        tbStock = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txQty = new javax.swing.JTextField();
        txidMaterial = new javax.swing.JTextField();
        txNmVendor = new javax.swing.JTextField();
        cbidVendor = new javax.swing.JComboBox();
        cbSat = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txNmMaterial = new javax.swing.JTextField();
        btHapus = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btBatal = new javax.swing.JButton();
        btCetak = new javax.swing.JButton();
        btCari = new javax.swing.JLabel();
        txCari = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        window.setBackground(new java.awt.Color(255, 0, 0));
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
        window.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 20, 20));

        jPanel1.add(window, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Data Material");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, -1, -1));

        tbStock.setModel(new javax.swing.table.DefaultTableModel(
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
        tbStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbStockMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbStock);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 380, 310));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Input material"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Satuan");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 70, -1));

        jLabel4.setText("id Material ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 70, -1));

        jLabel5.setText("Nama Material ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 90, -1));

        jLabel6.setText("Nama Vendor ");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 80, -1));

        jLabel7.setText("Quantity");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 70, -1));

        txQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txQtyActionPerformed(evt);
            }
        });
        jPanel2.add(txQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 50, -1));

        txidMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txidMaterialActionPerformed(evt);
            }
        });
        jPanel2.add(txidMaterial, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 130, -1));

        txNmVendor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNmVendorActionPerformed(evt);
            }
        });
        jPanel2.add(txNmVendor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 130, -1));

        cbidVendor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- piih -" }));
        cbidVendor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbidVendorActionPerformed(evt);
            }
        });
        jPanel2.add(cbidVendor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 100, -1));

        cbSat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "pcs", "box", "lusin", "karton", "meter" }));
        jPanel2.add(cbSat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 70, -1));

        jLabel8.setText("id Vendor ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 70, -1));

        txNmMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNmMaterialActionPerformed(evt);
            }
        });
        jPanel2.add(txNmMaterial, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 130, -1));

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
        jPanel2.add(btHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 90, 30));

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
        jPanel2.add(btSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 90, 30));

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
        jPanel2.add(btEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 90, 30));

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
        jPanel2.add(btBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 90, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 280, 310));

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
        jPanel1.add(btCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 90, 30));

        btCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon/search.png"))); // NOI18N
        btCari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btCariMouseClicked(evt);
            }
        });
        jPanel1.add(btCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, -1, -1));

        txCari.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txCari.setToolTipText("pencarian");
        txCari.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txCariActionPerformed(evt);
            }
        });
        jPanel1.add(txCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 140, -1));

        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 140, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 470));

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

    private void txQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txQtyActionPerformed

    private void txidMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txidMaterialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txidMaterialActionPerformed

    private void txNmVendorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNmVendorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNmVendorActionPerformed

    private void txNmMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNmMaterialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNmMaterialActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        // simpan
        Simpan();
    }//GEN-LAST:event_btSaveActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        // ubah
        Ubah();
    }//GEN-LAST:event_btEditActionPerformed

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        // hapus
        Hapus();
    }//GEN-LAST:event_btHapusActionPerformed

    private void cbidVendorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbidVendorActionPerformed
        // combo klik
        comboKlik();
    }//GEN-LAST:event_cbidVendorActionPerformed

    private void tbStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbStockMouseClicked
        // tabel klik
        tabelKlik();
    }//GEN-LAST:event_tbStockMouseClicked

    private void btBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatalActionPerformed
        // batal
        Kosong();
    }//GEN-LAST:event_btBatalActionPerformed

    private void btCariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCariMouseClicked
        // cari
        Cari();
    }//GEN-LAST:event_btCariMouseClicked

    private void txCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txCariActionPerformed
        // TODO add your handling code here:
        Cari();
    }//GEN-LAST:event_txCariActionPerformed

    private void btCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCetakActionPerformed
        // cetak
        makePreview("Material");
    }//GEN-LAST:event_btCetakActionPerformed

   
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AD_InputMaterial().setVisible(true);
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
    private javax.swing.JComboBox cbSat;
    private javax.swing.JComboBox cbidVendor;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbStock;
    private javax.swing.JTextField txCari;
    private javax.swing.JTextField txNmMaterial;
    private javax.swing.JTextField txNmVendor;
    private javax.swing.JTextField txQty;
    private javax.swing.JTextField txidMaterial;
    private javax.swing.JPanel window;
    // End of variables declaration//GEN-END:variables
public void makePreview (String vName){
        try {
            String KopLaporan = getClass().getResource("/IMG/icon_telkom.png").toString();
            HmAdmin hm = new HmAdmin();
//            String User = ;
            String locFile = "src/report/";
            String namaFile = locFile + vName + ".jasper";
            Connection conn = new Koneksi().connect();
            HashMap parameter = new HashMap();
            parameter.put("Logo", KopLaporan);
            parameter.put("txUser", Isi);
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
