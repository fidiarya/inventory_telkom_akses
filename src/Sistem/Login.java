
package Sistem;

import HomePage.HmAdmin;
import HomePage.HmCommerce;
import HomePage.HmLeader;
import HomePage.HmManager;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import mainkoneksi.Koneksi;


public class Login extends javax.swing.JFrame {
    private final Connection conn = new Koneksi().connect();

    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txUser = new javax.swing.JTextField();
        btLog = new javax.swing.JButton();
        exit = new javax.swing.JLabel();
        txPwd = new javax.swing.JPasswordField();
        gambar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Password");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Username");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        txUser.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txUserActionPerformed(evt);
            }
        });
        jPanel1.add(txUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 260, -1));

        btLog.setBackground(new java.awt.Color(255, 255, 255));
        btLog.setText("Login");
        btLog.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogActionPerformed(evt);
            }
        });
        jPanel1.add(btLog, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 80, -1));

        exit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit.setText("X");
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        jPanel1.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 20, 20));

        txPwd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txPwdKeyPressed(evt);
            }
        });
        jPanel1.add(txPwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 260, -1));

        gambar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/login.jpg"))); // NOI18N
        jPanel1.add(gambar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 360));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txUserActionPerformed

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitMouseClicked

    private void btLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogActionPerformed
        // TODO add your handling code here:
        loginPS();
    }//GEN-LAST:event_btLogActionPerformed

    private void txPwdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPwdKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()== KeyEvent.VK_ENTER) {
            loginPS();
        }
    }//GEN-LAST:event_txPwdKeyPressed

    private void loginPS(){
    try {
        if (txUser.getText().equals("") && txPwd.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf Username dan Password Tidak boleh kosong",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            txUser.requestFocus();
        } else if (txUser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf Username Tidak boleh kosong",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            txUser.requestFocus();
        } else if (txPwd.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf Password Tidak boleh kosong",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            txPwd.requestFocus();
        } else {
            PreparedStatement ps = null;
            ps = conn.prepareStatement("SELECT * FROM tb_user  WHERE "
                    + "username='"+txUser.getText()+"' AND "
                    + "password='"+txPwd.getText()+"'");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(this, "Selamat Datang " +rs.getString("nama")+"!");
                if (rs.getString("level").equals("admin")) {
                    HmAdmin am = new HmAdmin ();
                    am.getUser().setText(rs.getString("nama"));
                    this.dispose();
                    am.setVisible(true);
                } else if (rs.getString("level").equals("manager")){
                    HmManager mng = new HmManager ();
                    mng.getUser().setText(rs.getString("nama"));
                    this.dispose();
                    mng.setVisible(true);
                } else if (rs.getString("level").equals("leader")){
                    HmLeader ld = new HmLeader ();
                    ld.getUser().setText(rs.getString("nama"));
                    this.dispose();
                    ld.setVisible(true);
                } else if(rs.getString("level").equals("commerce")) {
                    HmCommerce cm = new HmCommerce();
                    cm.getUser().setText(rs.getString("nama"));
                    this.dispose();
                    cm.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username dan Password Tidak Sesuai", 
                        "Login", JOptionPane.ERROR_MESSAGE);
                txUser.setText("");
                txPwd.setText("");
                txUser.requestFocus();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLog;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel gambar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txPwd;
    private javax.swing.JTextField txUser;
    // End of variables declaration//GEN-END:variables
}
