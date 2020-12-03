
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


public class AD_Pengeluaran extends javax.swing.JFrame {
    private final Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    Statement st ;
    ResultSet rs;
    makePreview mp = new makePreview();
    public String Isi = null;
    String id,Tanggal,idPermintaan,idMaterial,nmMaterial,Qty,Satuan,Teknisi,nmAdmin,Status;
    String newStatus="Keluar";

    
    public AD_Pengeluaran() {
        initComponents();
        dataTabel();
        autoNomor();
        btKonfirmasi.setEnabled(false);
    }
    
     protected void dataTabel(){
        Object [] baris = {"id","Tanggal","id Material","Nama Material","Qty","Satuan","Teknisi","Leader","Status"};
        tabmode = new DefaultTableModel(null, baris);
        tbPermintaanKel.setModel(tabmode);
        String sql="select*from permintaan where status='Approve'";
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
             String sql ="SELECT * FROM pengeluaran order by id_pengeluaran desc";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             if (rs.next()) {
                 String idg = rs.getString("id_pengeluaran").substring(3);
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
                 id = "OUT"+Nol+AN;
             } else {
                 id = "OUT0001";
             }
         } catch (Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
     }
    
    public void tabelKlik(){
        int bar = tbPermintaanKel.getSelectedRow();
        idPermintaan = tabmode.getValueAt(bar, 0).toString();
        Tanggal = tabmode.getValueAt(bar, 1).toString();
        idMaterial = tabmode.getValueAt(bar, 2).toString();
        idMaterial = tabmode.getValueAt(bar, 2).toString();
        nmMaterial = (tabmode.getValueAt(bar, 3).toString());
        Qty = tabmode.getValueAt(bar, 4).toString();
        Satuan = tabmode.getValueAt(bar, 5).toString();
        Teknisi = tabmode.getValueAt(bar, 6).toString();
        Status = tabmode.getValueAt(bar, 8).toString();
        btKonfirmasi.setEnabled(true);
    }
    
    public void delStock(){
        try {
            String sql ="UPDATE tb_material SET qty=? WHERE id_material='"+idMaterial+"'";
            String sql2="SELECT * FROM tb_material WHERE id_material='"+idMaterial+"'";
            int a=0;
            st=conn.createStatement();
            rs=st.executeQuery(sql2);
            while(rs.next()){
                a = Integer.valueOf(rs.getString("qty"));
            }
            int b = Integer.valueOf(Qty);
            int hasil =  a-b;
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, Integer.toString(hasil));
            stat.executeUpdate();
            stat.close();
        } catch (Exception e) {
            System.out.println("eror bos di : "+e);
        }
    }
    
    public void UpdateStatus(){
        String sql = "UPDATE permintaan SET status=? WHERE id_permintaan='"+idPermintaan+"'";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, newStatus);
            stat.executeUpdate();
            stat.close();
        } catch (Exception ex) {
            System.err.print(ex);
        }
    }
    
    public void Simpan(){
        String sql = "insert into pengeluaran values (?,?,?,?,?,?,?,?,?)";
        int ok = JOptionPane.showConfirmDialog(rootPane, "Anda yakin mengkonfirmasi material keluar dengan id permintaan "+idPermintaan+" dan total material "+Qty+"?","Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok==0){
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1,id);
            stat.setString(2,Tanggal);
            stat.setString(3,idPermintaan);
            stat.setString(4,idMaterial);
            stat.setString(5,nmMaterial);
            stat.setString(6,Qty);
            stat.setString(7,Satuan);
            stat.setString(8,Teknisi);
            stat.setString(9, Isi);

            stat.executeUpdate();
            stat.close();
            delStock();
            UpdateStatus();
            btKonfirmasi.setEnabled(false);
            JOptionPane.showConfirmDialog(rootPane, "Data Material keluar berhasil di konfirmasi, Lanjut cetak?");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Data gagal disimpan "+e);
            btKonfirmasi.setEnabled(false);
        }
        }
    }
    
    public void Cari(){
        int row=tbPermintaanKel.getRowCount();
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
            Logger.getLogger(AD_Pengeluaran.class.getName()).log(Level.SEVERE, null, ex);  
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
        tbPermintaanKel = new javax.swing.JTable();
        btCari = new javax.swing.JLabel();
        txCari = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btKonfirmasi = new javax.swing.JButton();

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
        window.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 20, 20));

        jPanel1.add(window, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Permintaan Material Keluar");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, -1, -1));

        tbPermintaanKel.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPermintaanKel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPermintaanKelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPermintaanKel);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 740, 280));

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

        btKonfirmasi.setText("Konfirmasi dan Cetak");
        btKonfirmasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKonfirmasiActionPerformed(evt);
            }
        });
        jPanel1.add(btKonfirmasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(573, 443, 170, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 560));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        // keluar
        dispose();
        HmAdmin ha = new HmAdmin();
        ha.notif();
        ha.dataTabel();
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

    private void tbPermintaanKelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPermintaanKelMouseClicked
        // tabel klik
        tabelKlik();
    }//GEN-LAST:event_tbPermintaanKelMouseClicked

    private void btCariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCariMouseClicked
        // cari
        Cari();
    }//GEN-LAST:event_btCariMouseClicked

    private void txCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txCariActionPerformed
        // TODO add your handling code here:
        Cari();
    }//GEN-LAST:event_txCariActionPerformed

    private void btKonfirmasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKonfirmasiActionPerformed
        // TODO add your handling code here:
        Simpan();
        makePreview("Pengeluaran");
        autoNomor();
    }//GEN-LAST:event_btKonfirmasiActionPerformed

   
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AD_Pengeluaran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btCari;
    private javax.swing.JButton btKonfirmasi;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbPermintaanKel;
    private javax.swing.JTextField txCari;
    private javax.swing.JPanel window;
    // End of variables declaration//GEN-END:variables
public void makePreview (String vName){
        try {
            String KopLaporan = getClass().getResource("/IMG/icon_telkom.png").toString();
            HmAdmin hm = new HmAdmin();
            String locFile = "src/report/";
            String namaFile = locFile + vName + ".jasper";
            Connection conn = new Koneksi().connect();
            HashMap parameter = new HashMap();
            parameter.put("Logo", KopLaporan);
            parameter.put("txUser", Isi);
            parameter.put("ido", id);
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
