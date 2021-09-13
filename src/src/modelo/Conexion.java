
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Conexion {
    public static final String url="jdbc:mysql://localhost:3306/coorddb";
    public static final String usuario="root";
    public static final String clave="1976";
    public static final String driver="com.mysql.jdbc.Driver";
    
    private Connection conn=null;
    
    
    public Connection getConexion(){
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn=DriverManager.getConnection(this.url, this.usuario,this.clave);
            //JOptionPane.showMessageDialog(null,"Conectado!!!");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null,"NOOO Conectado!!!");
        }
        return conn;
    }
}
