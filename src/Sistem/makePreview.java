
package Sistem;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import mainkoneksi.Koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class makePreview {
    public void makePreview (String vName){
        try {
            String KopLaporan = getClass().getResource("/IMG/icon_telkom.png").toString();
//            String User = getClass().getResource("txUser.getText()").toString();
            String locFile = "src/report/";
            String namaFile = locFile + vName + ".jasper";
            Connection conn = new Koneksi().connect();
            HashMap parameter = new HashMap();
            parameter.put("Logo", KopLaporan);
//            parameter.put("txUser", User);

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
