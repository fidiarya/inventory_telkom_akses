
package HomePage;

import GUI.X_About;
import GUI.X_Help;
import Sistem.Login;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mainkoneksi.Koneksi;


public class HmManager extends javax.swing.JFrame {
    private final Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    Statement st ;
    ResultSet rs;
    String id;
    String StatusApp="Approve", StatusNot="Not Approve";
    
    public HmManager() {
        initComponents();
        iconLogo();
        notif();
        dataTabel();
        btDefalut();
    }
    
    public void iconLogo(){
        Image icon = Toolkit.getDefaultToolkit().getImage("src/IMG/icon/iconWindow.png");
        setIconImage(icon);
    }
    public JLabel getUser() {
        return txUser;
    }

    public void setUser(JLabel txUser) {
        this.txUser = txUser;
    }
    
    public void notif (){
        jpNotif.setVisible(false);
        try {
            st=conn.createStatement();
            String sql = "SELECT * FROM permintaan WHERE status='di ajukan'";
            rs = st.executeQuery(sql);
            if (rs.last()) {
                int total= rs.getRow();
                rs.beforeFirst();
                
                txJum.setText(Integer.toString(total));
                if (total>0) {
                jpNotif.setVisible(true);
            }
            }
            
        } catch (Exception e) {
        }
    }
    
    public void btDefalut(){
        btApprove.setEnabled(false);
        btNot.setEnabled(false);
    }
    
    protected void dataTabel(){
        Object [] baris = {"id","Tanggal","id Material","Nama Material","Qty","Satuan","Teknisi","Leader","Status"};
        tabmode = new DefaultTableModel(null, baris);
        tbDataPermint.setModel(tabmode);
        String sql="select*from permintaan WHERE status='di ajukan'";
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
        int bar = tbDataPermint.getSelectedRow();
        id = tabmode.getValueAt(bar, 0).toString();
        btApprove.setEnabled(true);
        btNot.setEnabled(true);
    }
    
    public void ApproveX(){
        int ok = JOptionPane.showConfirmDialog(rootPane, "Anda yakin ingin mengApprove data?","Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok==0) {
            try {
            String sql ="update permintaan set status=? WHERE id_permintaan='"+id+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, StatusApp);
            stat.executeUpdate();
            stat.close();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil di Approve");
            dataTabel();
            notif();
            btDefalut();
        } catch (Exception e) {
            System.err.print(e);
            System.out.println(e);
        }
        }
    }
    
    public void notApproveX(){
        int ok = JOptionPane.showConfirmDialog(rootPane, "Anda yakin ingin NOT APPROVE data?","Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok==0) {
            try {
            String sql ="update permintaan set status=? WHERE id_permintaan='"+id+"'";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, StatusNot);
            stat.executeUpdate();
            stat.close();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil di NOT APPROVE");
            dataTabel();
            notif();
            btDefalut();
        } catch (Exception e) {
            System.err.print(e);
            System.out.println(e);
        }
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btLogout = new javax.swing.JButton();
        txUser = new javax.swing.JLabel();
        btHelp = new javax.swing.JLabel();
        btAbout = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDataPermint = new javax.swing.JTable();
        btNot = new javax.swing.JButton();
        btApprove = new javax.swing.JButton();
        jpNotif = new javax.swing.JPanel();
        txJum = new javax.swing.JLabel();
        icon_notif = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/user_red.png"))); // NOI18N
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btLogout.setBackground(new java.awt.Color(255, 255, 255));
        btLogout.setText("keluar");
        btLogout.setToolTipText("keluar");
        btLogout.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btLogout.setBorderPainted(false);
        btLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btLogout.setFocusable(false);
        btLogout.setRequestFocusEnabled(false);
        btLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogoutActionPerformed(evt);
            }
        });
        jPanel2.add(btLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 70, 30));

        txUser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txUser.setForeground(new java.awt.Color(255, 255, 255));
        txUser.setText("Nama Pengguna");
        jPanel2.add(txUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 380, 30));

        btHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_help.png"))); // NOI18N
        btHelp.setToolTipText("Bantuan");
        btHelp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btHelpMouseClicked(evt);
            }
        });
        jPanel2.add(btHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, -1, -1));

        btAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_about.png"))); // NOI18N
        btAbout.setToolTipText("Tentang");
        btAbout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAboutMouseClicked(evt);
            }
        });
        jPanel2.add(btAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 10, -1, -1));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 1210, 50));

        jPanel3.setBackground(new java.awt.Color(255, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("di");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Halaman Manager");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 26)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Selamat Datang");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 260, 470));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_telkom.png"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 600, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Data Permintaan Material");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, -1, -1));

        tbDataPermint.setModel(new javax.swing.table.DefaultTableModel(
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
        tbDataPermint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDataPermintMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDataPermint);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 850, 290));

        btNot.setBackground(new java.awt.Color(255, 0, 0));
        btNot.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btNot.setForeground(new java.awt.Color(255, 255, 255));
        btNot.setText("Not Approve");
        btNot.setToolTipText("Approve permintaan");
        btNot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btNot.setFocusable(false);
        btNot.setRequestFocusEnabled(false);
        btNot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNotActionPerformed(evt);
            }
        });
        jPanel4.add(btNot, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 540, 130, 40));

        btApprove.setBackground(new java.awt.Color(0, 153, 51));
        btApprove.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btApprove.setForeground(new java.awt.Color(255, 255, 255));
        btApprove.setText("Approve");
        btApprove.setToolTipText("Approve permintaan");
        btApprove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btApprove.setFocusable(false);
        btApprove.setRequestFocusEnabled(false);
        btApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btApproveActionPerformed(evt);
            }
        });
        jPanel4.add(btApprove, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 540, 130, 40));

        jpNotif.setBackground(new java.awt.Color(0, 51, 204));
        jpNotif.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txJum.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txJum.setForeground(new java.awt.Color(255, 255, 255));
        txJum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txJum.setText("0");
        txJum.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jpNotif.add(txJum, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 20, 20));

        jPanel4.add(jpNotif, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 120, 20, 20));

        icon_notif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/notif.png"))); // NOI18N
        icon_notif.setToolTipText("Permintaan Material");
        jPanel4.add(icon_notif, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 120, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogoutActionPerformed
        // TODO add your handling code here:
        dispose();
        Login lg = new Login();
        lg.setVisible(true);
    }//GEN-LAST:event_btLogoutActionPerformed

    private void btHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btHelpMouseClicked
        // TODO add your handling code here:
        X_Help hp = new X_Help();
        hp.setVisible(true);
    }//GEN-LAST:event_btHelpMouseClicked

    private void btAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAboutMouseClicked
        // TODO add your handling code here:
        X_About ab = new X_About();
        ab.setVisible(true);
    }//GEN-LAST:event_btAboutMouseClicked

    private void btApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btApproveActionPerformed
        // TODO add your handling code here:
        ApproveX();
    }//GEN-LAST:event_btApproveActionPerformed

    private void btNotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNotActionPerformed
        // TODO add your handling code here:
        notApproveX();
    }//GEN-LAST:event_btNotActionPerformed

    private void tbDataPermintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDataPermintMouseClicked
        // TODO add your handling code here:
        tabelKlik();
    }//GEN-LAST:event_tbDataPermintMouseClicked

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HmManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btAbout;
    private javax.swing.JButton btApprove;
    private javax.swing.JLabel btHelp;
    private javax.swing.JButton btLogout;
    private javax.swing.JButton btNot;
    private javax.swing.JLabel icon_notif;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpNotif;
    private javax.swing.JTable tbDataPermint;
    private javax.swing.JLabel txJum;
    private javax.swing.JLabel txUser;
    // End of variables declaration//GEN-END:variables
}
