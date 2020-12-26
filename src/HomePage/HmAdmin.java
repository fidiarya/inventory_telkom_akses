
package HomePage;

import GUI.AD_InputMaterial;
import GUI.AD_InputVendor;
import GUI.AD_Penerimaan;
import GUI.AD_Pengeluaran;
import GUI.X_About;
import GUI.X_Help;
import Sistem.Login;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import mainkoneksi.Koneksi;


public class HmAdmin extends javax.swing.JFrame {
    private final Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    Statement st;
    ResultSet rs;        
    
    public HmAdmin() {
        initComponents();
        dataTabel();
        notif();
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
    
    public void dataTabel(){
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbStock = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jpNotif = new javax.swing.JPanel();
        txJum = new javax.swing.JLabel();
        btMKel = new javax.swing.JButton();
        btMMas = new javax.swing.JButton();
        btTambah = new javax.swing.JButton();
        btTambahVendor = new javax.swing.JButton();
        btRefresh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("  Halaman Admin");
        setIconImages(null);
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
        btLogout.setFocusPainted(false);
        btLogout.setRequestFocusEnabled(false);
        btLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogoutActionPerformed(evt);
            }
        });
        jPanel2.add(btLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 70, 30));

        txUser.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        txUser.setForeground(new java.awt.Color(255, 255, 255));
        txUser.setText("Nama Pengguna");
        jPanel2.add(txUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 470, 30));

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
        jLabel4.setText("Halaman Admin");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 26)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Selamat Datang");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 260, 470));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_telkom.png"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 600, -1, -1));

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
        jScrollPane1.setViewportView(tbStock);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 410, 670, 190));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Info Stock Material ");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 380, -1, -1));

        jpNotif.setBackground(new java.awt.Color(51, 204, 0));
        jpNotif.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txJum.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txJum.setForeground(new java.awt.Color(255, 255, 255));
        txJum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txJum.setText("0");
        txJum.setToolTipText("Permintaan material");
        txJum.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jpNotif.add(txJum, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 30));

        jPanel4.add(jpNotif, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 160, 40, 30));

        btMKel.setBackground(new java.awt.Color(255, 0, 0));
        btMKel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btMKel.setForeground(new java.awt.Color(255, 255, 255));
        btMKel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_output.png"))); // NOI18N
        btMKel.setText("Material Keluar");
        btMKel.setToolTipText("Olah material keluar");
        btMKel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btMKel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btMKel.setRequestFocusEnabled(false);
        btMKel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btMKel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMKelActionPerformed(evt);
            }
        });
        jPanel4.add(btMKel, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 190, 210, 150));

        btMMas.setBackground(new java.awt.Color(255, 0, 0));
        btMMas.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        btMMas.setForeground(new java.awt.Color(255, 255, 255));
        btMMas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon_input.png"))); // NOI18N
        btMMas.setText("Material Masuk");
        btMMas.setToolTipText("Input material masuk");
        btMMas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btMMas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btMMas.setRequestFocusEnabled(false);
        btMMas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btMMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMMasActionPerformed(evt);
            }
        });
        jPanel4.add(btMMas, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 210, 150));

        btTambah.setBackground(new java.awt.Color(255, 0, 0));
        btTambah.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btTambah.setForeground(new java.awt.Color(255, 255, 255));
        btTambah.setText("+");
        btTambah.setToolTipText("Tambah data material");
        btTambah.setBorderPainted(false);
        btTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btTambah.setFocusable(false);
        btTambah.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btTambah.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btTambah.setRequestFocusEnabled(false);
        btTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahActionPerformed(evt);
            }
        });
        jPanel4.add(btTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 370, 30, 30));

        btTambahVendor.setBackground(new java.awt.Color(255, 0, 0));
        btTambahVendor.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btTambahVendor.setForeground(new java.awt.Color(255, 255, 255));
        btTambahVendor.setText("+");
        btTambahVendor.setToolTipText("Tambah data vendor");
        btTambahVendor.setBorderPainted(false);
        btTambahVendor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btTambahVendor.setFocusable(false);
        btTambahVendor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btTambahVendor.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btTambahVendor.setRequestFocusEnabled(false);
        btTambahVendor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahVendorActionPerformed(evt);
            }
        });
        jPanel4.add(btTambahVendor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 370, 30, 30));

        btRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icon/refresh.png"))); // NOI18N
        btRefresh.setToolTipText("Refresh");
        btRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btRefreshMouseClicked(evt);
            }
        });
        jPanel4.add(btRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 120, -1, -1));

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

    private void btMKelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMKelActionPerformed
        // TODO add your handling code here:
        AD_Pengeluaran ak = new AD_Pengeluaran();
        ak.Isi = (txUser.getText());
        ak.setVisible(true);
    }//GEN-LAST:event_btMKelActionPerformed

    private void btTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahActionPerformed
        // TODO add your handling code here:
        AD_InputMaterial ai = new AD_InputMaterial();
        ai.Isi = (txUser.getText());
        ai.setVisible(true);
    }//GEN-LAST:event_btTambahActionPerformed

    private void btMMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMMasActionPerformed
        // masuk
        AD_Penerimaan ap = new AD_Penerimaan();
        ap.setVisible(true);
    }//GEN-LAST:event_btMMasActionPerformed

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

    private void btTambahVendorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahVendorActionPerformed
        // TODO add your handling code here:
        AD_InputVendor aiv = new AD_InputVendor();
        aiv.setVisible(true);
    }//GEN-LAST:event_btTambahVendorActionPerformed

    private void btRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btRefreshMouseClicked
        // TODO add your handling code here:
        dataTabel();
        notif();
    }//GEN-LAST:event_btRefreshMouseClicked

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HmAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btAbout;
    private javax.swing.JLabel btHelp;
    private javax.swing.JButton btLogout;
    private javax.swing.JButton btMKel;
    private javax.swing.JButton btMMas;
    private javax.swing.JLabel btRefresh;
    private javax.swing.JButton btTambah;
    private javax.swing.JButton btTambahVendor;
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
    private javax.swing.JTable tbStock;
    private javax.swing.JLabel txJum;
    public javax.swing.JLabel txUser;
    // End of variables declaration//GEN-END:variables
}
