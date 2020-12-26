
package HomePage;

import GUI.CO_Rekon;
import GUI.X_About;
import GUI.X_Help;
import Sistem.Login;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JLabel;


public class HmCommerce extends javax.swing.JFrame {

    
    public HmCommerce() {
        initComponents();
        iconLogo();
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
        btRekon = new javax.swing.JButton();

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
        jPanel2.add(txUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 500, 30));

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
        jLabel4.setText("Halaman Commerce");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 26)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Selamat Datang");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 260, 470));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_telkom.png"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 600, -1, -1));

        btRekon.setBackground(new java.awt.Color(255, 0, 0));
        btRekon.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btRekon.setForeground(new java.awt.Color(255, 255, 255));
        btRekon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_input.png"))); // NOI18N
        btRekon.setText("Rekon Material");
        btRekon.setToolTipText("Input material masuk");
        btRekon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btRekon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btRekon.setRequestFocusEnabled(false);
        btRekon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btRekon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRekonActionPerformed(evt);
            }
        });
        jPanel4.add(btRekon, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 210, 150));

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

    private void btRekonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRekonActionPerformed
        // masuk
        CO_Rekon cr = new CO_Rekon();
        cr.setVisible(true);
        cr.Isi=txUser.getText();
    }//GEN-LAST:event_btRekonActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HmCommerce().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btAbout;
    private javax.swing.JLabel btHelp;
    private javax.swing.JButton btLogout;
    private javax.swing.JButton btRekon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel txUser;
    // End of variables declaration//GEN-END:variables
}
