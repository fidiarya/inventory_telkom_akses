
package GUI;

import Sistem.makePreview;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mainkoneksi.Koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class LD_PermintaanApprove extends javax.swing.JFrame {
    private final Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    Statement st ;
    ResultSet rs;
    makePreview mp = new makePreview();
    public String Isi = null;
    String idx = "";

    
    public LD_PermintaanApprove() {
        initComponents();
        dataTabel();
    }
    
    protected void dataTabel(){
        Object [] baris = {"id","Tanggal","id Material","Nama Material","Qty","Satuan","Teknisi","Leader","Status"};
        tabmode = new DefaultTableModel(null, baris);
        tbPermintaanApprove.setModel(tabmode);
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
    
    
    public void tabelKlik(){
        String newStatus="";
        int bar = tbPermintaanApprove.getSelectedRow();
        idx = tabmode.getValueAt(bar, 0).toString();
        System.out.println(idx);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        window = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPermintaanApprove = new javax.swing.JTable();
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
        window.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 10, 20, 20));

        jPanel1.add(window, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Permintaan Material Approve");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, -1));

        tbPermintaanApprove.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPermintaanApprove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPermintaanApproveMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbPermintaanApprove);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 870, 260));

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
        jPanel1.add(btCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 100, 90, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 560));

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

    private void tbPermintaanApproveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPermintaanApproveMouseClicked
        // tabel klik
        tabelKlik();
    }//GEN-LAST:event_tbPermintaanApproveMouseClicked

    private void btCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCetakActionPerformed
        // cetak
        if (idx.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Pilih Data yang akan di cetak!");
        } else {
            makePreview("FormPermintaan");
        }
        dataTabel();
    }//GEN-LAST:event_btCetakActionPerformed

   
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LD_PermintaanApprove().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCetak;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbPermintaanApprove;
    private javax.swing.JPanel window;
    // End of variables declaration//GEN-END:variables
public void makePreview (String vName){
        try {
            String KopLaporan = getClass().getResource("/IMG/icon_telkom.png").toString();
            String locFile = "src/report/";
            String namaFile = locFile + vName + ".jasper";
            Connection conn = new Koneksi().connect();
            HashMap parameter = new HashMap();
            parameter.put("Logo", KopLaporan);
            parameter.put("txId", idx);
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
