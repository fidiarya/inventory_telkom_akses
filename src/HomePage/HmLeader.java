
package HomePage;

import GUI.LD_Pemakaian;
import GUI.LD_Permintaan;
import GUI.LD_PermintaanApprove;
import GUI.X_About;
import GUI.X_Help;
import Sistem.Login;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import mainkoneksi.Koneksi;


public class HmLeader extends javax.swing.JFrame {
    private final Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    Statement st;
    ResultSet rs; 

    
    public HmLeader() {
        initComponents();
        notif();
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
            String sql = "SELECT * FROM permintaan WHERE status='Approve'";
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

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btLogout = new javax.swing.JButton();
        txUser = new javax.swing.JLabel();
        btAbout = new javax.swing.JLabel();
        btHelp = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btPemakaian = new javax.swing.JButton();
        btPermintaan = new javax.swing.JButton();
        btPermintaan1 = new javax.swing.JButton();
        jpNotif = new javax.swing.JPanel();
        txJum = new javax.swing.JLabel();

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
        jPanel2.add(txUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 470, 30));

        btAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_about.png"))); // NOI18N
        btAbout.setToolTipText("Tentang");
        btAbout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAboutMouseClicked(evt);
            }
        });
        jPanel2.add(btAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 10, -1, -1));

        btHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_help.png"))); // NOI18N
        btHelp.setToolTipText("Bantuan");
        btHelp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btHelpMouseClicked(evt);
            }
        });
        jPanel2.add(btHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, -1, -1));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 1210, 50));

        jPanel3.setBackground(new java.awt.Color(255, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("di");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Production");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 180, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 26)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Selamat Datang");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Halaman Leader");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 180, 40));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 260, 470));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_telkom.png"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 600, -1, -1));

        btPemakaian.setBackground(new java.awt.Color(255, 0, 0));
        btPemakaian.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btPemakaian.setForeground(new java.awt.Color(255, 255, 255));
        btPemakaian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_output.png"))); // NOI18N
        btPemakaian.setText("Pemakaian");
        btPemakaian.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPemakaian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPemakaian.setRequestFocusEnabled(false);
        btPemakaian.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btPemakaian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPemakaianActionPerformed(evt);
            }
        });
        jPanel4.add(btPemakaian, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 190, 210, 150));

        btPermintaan.setBackground(new java.awt.Color(255, 0, 0));
        btPermintaan.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btPermintaan.setForeground(new java.awt.Color(255, 255, 255));
        btPermintaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_input.png"))); // NOI18N
        btPermintaan.setText("Permintaan");
        btPermintaan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPermintaan.setFocusPainted(false);
        btPermintaan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPermintaan.setRequestFocusEnabled(false);
        btPermintaan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btPermintaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPermintaanActionPerformed(evt);
            }
        });
        jPanel4.add(btPermintaan, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 210, 150));

        btPermintaan1.setBackground(new java.awt.Color(255, 0, 0));
        btPermintaan1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btPermintaan1.setForeground(new java.awt.Color(255, 255, 255));
        btPermintaan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/list.png"))); // NOI18N
        btPermintaan1.setText("Di Approve");
        btPermintaan1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btPermintaan1.setFocusPainted(false);
        btPermintaan1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPermintaan1.setRequestFocusEnabled(false);
        btPermintaan1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btPermintaan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPermintaan1ActionPerformed(evt);
            }
        });
        jPanel4.add(btPermintaan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, 210, 150));

        jpNotif.setBackground(new java.awt.Color(51, 204, 0));
        jpNotif.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txJum.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txJum.setForeground(new java.awt.Color(255, 255, 255));
        txJum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txJum.setText("0");
        txJum.setToolTipText("Permintaan material");
        txJum.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jpNotif.add(txJum, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        jPanel4.add(jpNotif, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 160, 40, 30));

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

    private void btPermintaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPermintaanActionPerformed
        // TODO add your handling code here:
        LD_Permintaan per = new LD_Permintaan();
        per.setVisible(true);
        per.txNmLeader.setText(txUser.getText());
    }//GEN-LAST:event_btPermintaanActionPerformed

    private void btAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAboutMouseClicked
        // TODO add your handling code here:
        X_About ab = new X_About();
        ab.setVisible(true);
    }//GEN-LAST:event_btAboutMouseClicked

    private void btHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btHelpMouseClicked
        // TODO add your handling code here:
        X_Help hp = new X_Help();
        hp.setVisible(true);
    }//GEN-LAST:event_btHelpMouseClicked

    private void btPemakaianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPemakaianActionPerformed
        // TODO add your handling code here:
        LD_Pemakaian ldp = new LD_Pemakaian();
        ldp.setVisible(true);
    }//GEN-LAST:event_btPemakaianActionPerformed

    private void btPermintaan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPermintaan1ActionPerformed
        // TODO add your handling code here:
        LD_PermintaanApprove lpa = new LD_PermintaanApprove();
        lpa.setVisible(true);
        lpa.Isi = txUser.getText();
    }//GEN-LAST:event_btPermintaan1ActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HmLeader().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btAbout;
    private javax.swing.JLabel btHelp;
    private javax.swing.JButton btLogout;
    private javax.swing.JButton btPemakaian;
    private javax.swing.JButton btPermintaan;
    private javax.swing.JButton btPermintaan1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jpNotif;
    private javax.swing.JLabel txJum;
    private javax.swing.JLabel txUser;
    // End of variables declaration//GEN-END:variables
}
