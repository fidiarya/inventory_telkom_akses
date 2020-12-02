
package GUI;

import Sistem.makePreview;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mainkoneksi.Koneksi;


public class LD_Permintaan extends javax.swing.JFrame {
    private final Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    Statement st ;
    ResultSet rs;
    makePreview mp = new makePreview();
    public String Isi = null;
    String Status="di ajukan";

    
    public LD_Permintaan() {
        initComponents();
        dataTabel();
        isiComboMaterial();
        autoNomor();
    }
    
    protected void dataTabel(){
        Object [] baris = {"id","Tanggal","id Material","Nama Material","Qty","Satuan","Teknisi","Leader","Status"};
        tabmode = new DefaultTableModel(null, baris);
        tbPermintaan.setModel(tabmode);
        String sql="select*from permintaan";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
            String id = hasil.getString("id_permintaan");
            String tgl = hasil.getString("tgl_permintaan");
            String idm = hasil.getString("id_material");
            String nm = hasil.getString("nm_material");
            String qty = hasil.getString("qty");
            String sat = hasil.getString("satuan");
            String tek = hasil.getString("nm_teknisi");
            String led = hasil.getString("nm_leader");
            String st = hasil.getString("status");
           
            String[]data = {id,tgl,idm,nm,qty,sat,tek,led,st};
            tabmode.addRow(data);
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void autoNomor(){
         try{
             String sql ="SELECT * FROM permintaan order by id_permintaan desc";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             if (rs.next()) {
                 String idg = rs.getString("id_permintaan").substring(3);
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
                 txidPermintaan.setText("PER"+Nol+AN);
             } else {
                 txidPermintaan.setText("PER0001");
             }
         } catch (Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
     }
    
    public void Kosong(){
        txidPermintaan.setText("");
        txTgl.setDate(null);
        cbidMaterial.setSelectedIndex(0);
        txNmMaterial.setText("");
        txQty.setText("");
        cbSat.setSelectedIndex(0);
        txNmTeknisi.setText("");
        txNmLeader.setText("");
        dataTabel();
        autoNomor();
    }
    
    public void tabelKlik(){
        String newStatus="";
        int bar = tbPermintaan.getSelectedRow();
        txidPermintaan.setText(tabmode.getValueAt(bar, 0).toString());
        String date = String.valueOf(tabmode.getValueAt(bar, 1).toString());
        java.util.Date tanggal = null;
        try {
        tanggal = new SimpleDateFormat("dd MMMM yyyy").parse(date);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        txTgl.setDate(tanggal);
        cbidMaterial.setSelectedItem(String.valueOf(tabmode.getValueAt(bar, 2).toString()));
        txNmMaterial.setText(tabmode.getValueAt(bar, 3).toString());
        txQty.setText(tabmode.getValueAt(bar, 4).toString());
        cbSat.setSelectedItem(String.valueOf(tabmode.getValueAt(bar, 5).toString()));
        txNmTeknisi.setText(tabmode.getValueAt(bar, 6).toString());
        txNmLeader.setText(tabmode.getValueAt(bar, 7).toString());
        newStatus = tabmode.getValueAt(bar, 8).toString();
        
        if (newStatus.equals(Status)) {
            btEdit.setEnabled(true);
        } else {
            btEdit.setEnabled(false);
        }
    }
    
    public void isiComboMaterial(){
        try {
             String sql ="SELECT * FROM tb_material";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             while(rs.next()){
                 cbidMaterial.addItem(rs.getString("id_material"));
             }
             rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "gagal "+e);
        }
    }
    
    public void comboKlikMaterial(){
        try {
            String sql ="SELECT * FROM tb_material where id_material='"+String.valueOf(cbidMaterial.getSelectedItem())+"'";
            st=conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                txNmMaterial.setText(rs.getString("nm_material"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }
    
    public void Simpan(){
        String sql = "insert into permintaan values (?,?,?,?,?,?,?,?,?)";
        try {
            if (txidPermintaan.getText().equals("")||txNmMaterial.getText().equals("")||
                    txNmTeknisi.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,txidPermintaan.getText());
            stat.setString(2, new SimpleDateFormat("dd MMMM yyyy").format(txTgl.getDate()));
            stat.setString(3,String.valueOf(cbidMaterial.getSelectedItem()));
            stat.setString(4,txNmMaterial.getText());
            stat.setString(5,txQty.getText());
            stat.setString(6,String.valueOf(cbSat.getSelectedItem()));
            stat.setString(7,txNmTeknisi.getText());
            stat.setString(8,txNmLeader.getText());
            stat.setString(9,Status);

            stat.executeUpdate();
            stat.close();
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
            String sql ="update permintaan set tgl_permintaan=?, id_material=?, nm_material=?, qty=?, "
                    + "satuan=?, nm_teknisi=?, nm_leader=?, status=? where id_permintaan='"+txidPermintaan.getText()+"'";
            if (txidPermintaan.getText().equals("")||txNmMaterial.getText().equals("")||
                    txNmTeknisi.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
            } else {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, new SimpleDateFormat("dd MMMM yyyy").format(txTgl.getDate()));
            stat.setString(2,String.valueOf(cbidMaterial.getSelectedItem()));
            stat.setString(3,txNmMaterial.getText());
            stat.setString(4,txQty.getText());
            stat.setString(5,String.valueOf(cbSat.getSelectedItem()));
            stat.setString(6,txNmTeknisi.getText());
            stat.setString(7,txNmLeader.getText());
            stat.setString(8,Status);
            stat.executeUpdate();
            stat.close();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil diubah");
            Kosong();
            } 
        }catch (SQLException e){
            JOptionPane.showMessageDialog(rootPane, "Data gagal di ubah "+e);
        }
    }
    
    public void Hapus(){
        if (txidPermintaan.getText().equals("")||txNmMaterial.getText().equals("")||
                    txNmTeknisi.getText().equals("")) { 
                JOptionPane.showMessageDialog(rootPane, "Maaf kolom tidak boleh kosong!");
                return;
        } else {
            int ok = JOptionPane.showConfirmDialog(rootPane, "Anda yakin ingin menghapus data?","Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            String sql = "delete from permintaan where id_permintaan='"+txidPermintaan.getText()+"'";
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
        int row=tbPermintaan.getRowCount();
        for (int x=0;x<row;x++){
            tabmode.removeRow(0);
        }
        try{
            ResultSet rs=conn.createStatement().executeQuery("Select * from "
                    + "permintaan where nm_material like '%"+txCari.getText()+"%' or "
                    + "id_material like '%"+txCari.getText()+"%' or "
                    + "id_permintaan like '%"+txCari.getText()+"%' or "
                    + "tgl_permintaan like '%"+txCari.getText()+"%' or "
                    + "nm_teknisi like '%"+txCari.getText()+"%' or "
                    + "nm_leader like '%"+txCari.getText()+"%'");
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)};
                tabmode.addRow(data);
            }
        }catch (SQLException ex){
            Logger.getLogger(LD_Permintaan.class.getName()).log(Level.SEVERE, null, ex);  
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
        tbPermintaan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txQty = new javax.swing.JTextField();
        txNmTeknisi = new javax.swing.JTextField();
        cbSat = new javax.swing.JComboBox();
        txNmMaterial = new javax.swing.JTextField();
        btHapus = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btBatal = new javax.swing.JButton();
        txTgl = new com.toedter.calendar.JDateChooser();
        txidPermintaan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbidMaterial = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        txNmLeader = new javax.swing.JTextField();
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
        window.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 10, 20, 20));

        jPanel1.add(window, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Permintaan Material");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, -1));

        tbPermintaan.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPermintaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPermintaanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPermintaan);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 640, 390));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Permintaan "));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Satuan");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 70, -1));

        jLabel4.setText("id Permintaan ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 100, -1));

        jLabel5.setText("Nama Material ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 90, -1));

        jLabel6.setText("Nama Teknisi ");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 80, -1));

        jLabel7.setText("Quantity");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 90, -1));

        txQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txQtyActionPerformed(evt);
            }
        });
        jPanel2.add(txQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 50, -1));

        txNmTeknisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNmTeknisiActionPerformed(evt);
            }
        });
        jPanel2.add(txNmTeknisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 130, -1));

        cbSat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "pcs", "box", "lusin", "karton", " " }));
        jPanel2.add(cbSat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 70, -1));

        txNmMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNmMaterialActionPerformed(evt);
            }
        });
        jPanel2.add(txNmMaterial, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 130, -1));

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
        jPanel2.add(btHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 90, 30));

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
        jPanel2.add(btSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 90, 30));

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
        jPanel2.add(btEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 90, 30));

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
        jPanel2.add(btBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 90, 30));

        txTgl.setToolTipText("tanggal");
        txTgl.setDateFormatString("dd MMMM yyyy");
        txTgl.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jPanel2.add(txTgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 130, -1));

        txidPermintaan.setEditable(false);
        txidPermintaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txidPermintaanActionPerformed(evt);
            }
        });
        jPanel2.add(txidPermintaan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 130, -1));

        jLabel9.setText("id Material ");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 100, -1));

        jLabel10.setText("Tanggal");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 100, -1));

        cbidMaterial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- piih -" }));
        cbidMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbidMaterialActionPerformed(evt);
            }
        });
        jPanel2.add(cbidMaterial, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 100, -1));

        jLabel11.setText("Nama Leader ");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 80, -1));

        txNmLeader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNmLeaderActionPerformed(evt);
            }
        });
        jPanel2.add(txNmLeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 130, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 130, 280, 390));

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 560));

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

    private void txNmTeknisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNmTeknisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNmTeknisiActionPerformed

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

    private void tbPermintaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPermintaanMouseClicked
        // tabel klik
        tabelKlik();
    }//GEN-LAST:event_tbPermintaanMouseClicked

    private void btBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBatalActionPerformed
        // batal
        Kosong();
        btEdit.setEnabled(true);
    }//GEN-LAST:event_btBatalActionPerformed

    private void btCariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCariMouseClicked
        // cari
        Cari();
    }//GEN-LAST:event_btCariMouseClicked

    private void txCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txCariActionPerformed
        // TODO add your handling code here:
        Cari();
    }//GEN-LAST:event_txCariActionPerformed

    private void txidPermintaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txidPermintaanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txidPermintaanActionPerformed

    private void cbidMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbidMaterialActionPerformed
        // TODO add your handling code here:
        comboKlikMaterial();
    }//GEN-LAST:event_cbidMaterialActionPerformed

    private void txNmLeaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNmLeaderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNmLeaderActionPerformed

   
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LD_Permintaan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBatal;
    private javax.swing.JLabel btCari;
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btSave;
    private javax.swing.JComboBox cbSat;
    private javax.swing.JComboBox cbidMaterial;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbPermintaan;
    private javax.swing.JTextField txCari;
    private javax.swing.JTextField txNmLeader;
    private javax.swing.JTextField txNmMaterial;
    private javax.swing.JTextField txNmTeknisi;
    private javax.swing.JTextField txQty;
    private com.toedter.calendar.JDateChooser txTgl;
    private javax.swing.JTextField txidPermintaan;
    private javax.swing.JPanel window;
    // End of variables declaration//GEN-END:variables

}
