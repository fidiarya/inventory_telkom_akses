
package GUI;

import HomePage.HmAdmin;
import Sistem.makePreview;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
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


public class CO_Rekon extends javax.swing.JFrame {
    private final Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    Statement st ;
    ResultSet rs;
    makePreview mp = new makePreview();
    public String Isi = null;
    String idPakai,idMaterial,nmMaterial,QtyPakai,SatPakai,idKeluar,QtyKeluar,SatKeluar,tglRekon,Status;
    String StatusY="Rekon", StatusN="Tidak Rekon";

    
    public CO_Rekon() {
        initComponents();
        dataTabel2();
        dataTabel();
        btRekon.setEnabled(false);
        Tanggal();
    }
    
    public void Tanggal() {
      Date HariSekarang = new Date( );
      SimpleDateFormat ft = new SimpleDateFormat ("dd MMMM yyyy");
      tglRekon = ft.format(HariSekarang);
    }
    
     protected void dataTabel(){
        Object [] baris = {"ID Pakai","Tanggal Pakai","id Material","Nama Material","Qty Pakai","Satuan","ID Keluar","Tanggal Keluar","Qty Keluar","Satuan"};
        tabmode = new DefaultTableModel(null, baris);
        tbPemakaian.setModel(tabmode);
        String sql="select*from pengeluaran, pemakaian where pengeluaran.id_pengeluaran = pemakaian.id_pengeluaran and pemakaian.status='pakai'";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
            String a = hasil.getString("pemakaian.id_pemakaian");
            String b = hasil.getString("pemakaian.tgl_pemakaian");
            String c = hasil.getString("pemakaian.id_material");
            String d = hasil.getString("pemakaian.nm_material");
            String f = hasil.getString("pemakaian.qty");
            String g = hasil.getString("pemakaian.satuan");
            String h = hasil.getString("pengeluaran.id_pengeluaran");
            String i = hasil.getString("pengeluaran.tgl_pengeluaran");
            String j = hasil.getString("pengeluaran.qty");
            String k = hasil.getString("pengeluaran.satuan");
           
            String[]data = {a,b,c,d,f,g,h,i,j,k};
            tabmode.addRow(data);
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }
     
     protected void dataTabel2(){
        Object [] baris = {"ID Pakai","ID Material","Nama Material","Qty Pakai","Satuan","ID Keluar","Qty Keluar","Satuan","Tanggal Rekon","Status"};
        tabmode = new DefaultTableModel(null, baris);
        tbRekon.setModel(tabmode);
        String sql="select*from rekon";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()){
            String a = hasil.getString("id_pakai");
            String b = hasil.getString("id_material");
            String c = hasil.getString("nm_material");
            String d = hasil.getString("qty_pakai");
            String f = hasil.getString("sat_pakai");
            String g = hasil.getString("id_keluar");
            String h = hasil.getString("qty_keluar");
            String i = hasil.getString("sat_keluar");
            String j = hasil.getString("tgl_rekon");
            String k = hasil.getString("status");
           
            String[]data = {a,b,c,d,f,g,h,i,j,k};
            tabmode.addRow(data);
            }
        }catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void tabelKlik(){
        int bar = tbPemakaian.getSelectedRow();
        idPakai = tabmode.getValueAt(bar, 0).toString();
        idMaterial = tabmode.getValueAt(bar, 2).toString();
        nmMaterial = (tabmode.getValueAt(bar, 3).toString());
        QtyPakai = tabmode.getValueAt(bar, 4).toString();
        SatPakai = tabmode.getValueAt(bar, 5).toString();
        idKeluar = tabmode.getValueAt(bar, 6).toString();
        QtyKeluar = tabmode.getValueAt(bar, 8).toString();
        SatKeluar = tabmode.getValueAt(bar, 9).toString();
        btRekon.setEnabled(true);
    } 
    
    public void Update2(){
        try {
            String sql = "update pemakaian set status=? where id_pemakaian='"+idPakai+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
                    stat.setString(1, StatusY);
                    stat.executeUpdate();
                    stat.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, e);
                }
    }
    
    public void Update1(){
        try {
                    String sql = "update pemakaian set status=? where id_pemakaian='"+idPakai+"'";
                    PreparedStatement stat = conn.prepareStatement(sql);
                    stat.setString(1, StatusN);
                    stat.executeUpdate();
                    stat.close();
                    JOptionPane.showMessageDialog(rootPane, "Data gagal di Rekon dan akan di kembalikan ke leader");
                    dataTabel2();
                    dataTabel();
                    btRekon.setEnabled(false);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "Data gagal di Rekon atau sudah di Rekon ");
                    btRekon.setEnabled(false);
                }
    }
    
    public void Simpan(){
        
        String sql = "insert into rekon values (?,?,?,?,?,?,?,?,?,?)";
        int ok = JOptionPane.showConfirmDialog(rootPane, "Apakah anda yakin merekon material keluar "
                + "dengan id material "+idMaterial+" dengan qty pakai = "+QtyPakai+" dan qty keluar = "+QtyKeluar+" "
                + "","Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok==0){
            if (QtyPakai.equals(QtyKeluar)) {
                try {
                    PreparedStatement stat = conn.prepareStatement(sql);
                    stat.setString(1,idPakai);
                    stat.setString(2,idMaterial);
                    stat.setString(3,nmMaterial);
                    stat.setString(4,QtyPakai);
                    stat.setString(5,SatPakai);
                    stat.setString(6,idKeluar);
                    stat.setString(7,QtyKeluar);
                    stat.setString(8,SatKeluar);
                    stat.setString(9,tglRekon);
                    stat.setString(10,StatusY);
                    stat.executeUpdate();
                    stat.close();
                    Update2();
                    dataTabel2();
                    dataTabel();
                    btRekon.setEnabled(false);
                    JOptionPane.showMessageDialog(rootPane, "Data Berhasil di Rekon");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(rootPane, "Data gagal di Rekon atau sudah di Rekon ");
                    btRekon.setEnabled(false);
                }
        
            } else  {
                Update1();
            }
        }
    }
    
    public void Cari(){
        int row=tbPemakaian.getRowCount();
        for (int x=0;x<row;x++){
            tabmode.removeRow(0);
        }
        try{
            ResultSet rs=conn.createStatement().executeQuery("Select pemakaian.id_pemakaian, pemakaian.tgl_pemakaian, "
                    + "pemakaian.id_material, pemakaian.nm_material, pemakaian.qty, pemakaian.satuan, pengeluaran.id_pengeluaran, "
                    + "pengeluaran.tgl_pengeluaran, pengeluaran.qty, pengeluaran.satuan from "
                    + "pemakaian, pengeluaran where pemakaian.id_pengeluaran=pengeluaran.id_pengeluaran AND "
                    + "pemakaian.nm_material like '%"+txCari.getText()+"%' or "
                    + "pemakaian.id_material like '%"+txCari.getText()+"%' or "
                    + "pemakaian.id_pemakaian like '%"+txCari.getText()+"%' or "
                    + "pengeluaran.id_pengeluaran like '%"+txCari.getText()+"%' or "
                    + "pemakaian.tgl_pemakaian like '%"+txCari.getText()+"%' or "
                    + "pengeluaran.tgl_pengeluaran like '%"+txCari.getText()+"%'");
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)};
                tabmode.addRow(data);
            }
        }catch (SQLException ex){
            Logger.getLogger(CO_Rekon.class.getName()).log(Level.SEVERE, null, ex);  
        }
    }
    
    public void Cari2(){
        dataTabel2();
        int row=tbRekon.getRowCount();
        for (int x=0;x<row;x++){
            tabmode.removeRow(0);
        }
        try{
            ResultSet rs=conn.createStatement().executeQuery("Select * from "
                    + "rekon where nm_material like '%"+txCari2.getText()+"%' or "
                    + "id_material like '%"+txCari2.getText()+"%' or "
                    + "id_pakai like '%"+txCari2.getText()+"%' or "
                    + "tgl_rekon like '%"+txCari2.getText()+"%' or "
                    + "status like '%"+txCari2.getText()+"%' ");
            while(rs.next()){
                String data[]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)};
                tabmode.addRow(data);
            }
        }catch (SQLException ex){
            Logger.getLogger(CO_Rekon.class.getName()).log(Level.SEVERE, null, ex);  
        }
        dataTabel();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        window = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btCari = new javax.swing.JLabel();
        txCari = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPemakaian = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbRekon = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        txCari2 = new javax.swing.JTextField();
        btCari2 = new javax.swing.JLabel();
        btRekon = new javax.swing.JButton();
        txTglA = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        txTglB = new com.toedter.calendar.JDateChooser();
        btCetak = new javax.swing.JButton();

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
        window.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 20, 20));

        jPanel1.add(window, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Rekon Material");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, -1, -1));

        btCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon/search.png"))); // NOI18N
        btCari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btCariMouseClicked(evt);
            }
        });
        jPanel1.add(btCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, -1, -1));

        txCari.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txCari.setToolTipText("pencarian");
        txCari.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txCariActionPerformed(evt);
            }
        });
        jPanel1.add(txCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 140, -1));

        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 140, -1));

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
        jScrollPane2.setViewportView(tbPemakaian);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 870, 200));

        jLabel1.setText("Data Pemakaian Material :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 210, -1));

        jLabel4.setText("Data Rekon :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 140, -1));

        tbRekon.setModel(new javax.swing.table.DefaultTableModel(
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
        tbRekon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRekonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbRekon);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 870, 190));

        jSeparator2.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 380, 140, -1));

        txCari2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txCari2.setToolTipText("pencarian");
        txCari2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txCari2ActionPerformed(evt);
            }
        });
        jPanel1.add(txCari2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 360, 140, -1));

        btCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon/search.png"))); // NOI18N
        btCari2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCari2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btCari2MouseClicked(evt);
            }
        });
        jPanel1.add(btCari2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 360, -1, -1));

        btRekon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btRekon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon/checklist.png"))); // NOI18N
        btRekon.setText("Rekon");
        btRekon.setToolTipText("Rekon material keluar dan pemakaian");
        btRekon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btRekon.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btRekon.setIconTextGap(10);
        btRekon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRekonActionPerformed(evt);
            }
        });
        jPanel1.add(btRekon, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 610, 120, 40));

        txTglA.setToolTipText("dari tanggal");
        txTglA.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(txTglA, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 100, -1));

        jLabel16.setText("s/d");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 110, -1, 20));

        txTglB.setToolTipText("sampai tanggal");
        txTglB.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(txTglB, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, 100, -1));

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
        jPanel1.add(btCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 80, 90, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 690));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbRekonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRekonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbRekonMouseClicked

    private void tbPemakaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemakaianMouseClicked
        // TODO add your handling code here:
        tabelKlik();
    }//GEN-LAST:event_tbPemakaianMouseClicked

    private void txCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txCariActionPerformed
        // TODO add your handling code here:
        Cari();
    }//GEN-LAST:event_txCariActionPerformed

    private void btCariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCariMouseClicked
        // cari
        Cari();
    }//GEN-LAST:event_btCariMouseClicked

    private void windowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_windowMousePressed
        // window hold
        x=evt.getX();
        y=evt.getY();
    }//GEN-LAST:event_windowMousePressed

    private void windowMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_windowMouseDragged
        // window drag
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_windowMouseDragged

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        // keluar
        dispose();
        HmAdmin ha = new HmAdmin();
    }//GEN-LAST:event_exitMouseClicked

    private void txCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txCari2ActionPerformed
        // TODO add your handling code here:
        Cari2();
    }//GEN-LAST:event_txCari2ActionPerformed

    private void btCari2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCari2MouseClicked
        // TODO add your handling code here:
        Cari2();
    }//GEN-LAST:event_btCari2MouseClicked

    private void btRekonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRekonActionPerformed
        // TODO add your handling code here:
        Simpan();
    }//GEN-LAST:event_btRekonActionPerformed

    private void btCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCetakActionPerformed
        // cetak
        String tagA = String.valueOf(txTglA.getDate());
        String tagB = String.valueOf(txTglB.getDate());
        if (tagA.equals("null") ||  tagB.equals("null")) {
            JOptionPane.showMessageDialog(rootPane, "Pilih tanggal yang akan di cetak!");
        } else {
            makePreview("Rekon");
        }
    }//GEN-LAST:event_btCetakActionPerformed
    
    int x,y;
   
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CO_Rekon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btCari;
    private javax.swing.JLabel btCari2;
    private javax.swing.JButton btCetak;
    private javax.swing.JButton btRekon;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tbPemakaian;
    private javax.swing.JTable tbRekon;
    private javax.swing.JTextField txCari;
    private javax.swing.JTextField txCari2;
    private com.toedter.calendar.JDateChooser txTglA;
    private com.toedter.calendar.JDateChooser txTglB;
    private javax.swing.JPanel window;
    // End of variables declaration//GEN-END:variables
public void makePreview (String vName){
        String tagA = String.valueOf(new SimpleDateFormat("dd MMMM yyyy").format(txTglA.getDate()));
        String tagB = String.valueOf(new SimpleDateFormat("dd MMMM yyyy").format(txTglB.getDate()));
        try {
            String KopLaporan = getClass().getResource("/IMG/icon_telkom.png").toString();
            HmAdmin hm = new HmAdmin();
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
