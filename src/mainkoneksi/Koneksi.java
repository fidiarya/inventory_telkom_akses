
package mainkoneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {
    public Connection koneksi;
    private Object DriveManager;
    public Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil koneksi");
        } catch (ClassNotFoundException ex){
            System.out.println("eror koneksi bos : " +ex);
        }
        String url ="jdbc:mysql://localhost:3306/db_ta";
        try {
            koneksi = DriverManager.getConnection(url,"root","");
            System.out.println("Berhasil Koneksi Database");
        } catch (SQLException ex){
            System.out.println("Gagal koneksi Database bos : "+ex);
        }   
        return koneksi;
    }
}
