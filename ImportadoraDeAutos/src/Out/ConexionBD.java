package Out;

import java.sql.*;

public class ConexionBD {
    Connection cn;
    Statement st;
    
    public Connection conexion(){
      try{ 
          //Class.forName("org.gjt.mysql.Driver");
        Class.forName("com.mysql.jdbc.Driver");
       cn = DriverManager.getConnection("jdbc:mysql://sql8.freesqldatabase.com:3306/sql8143375","sql8143375","GWjCMTvx8L");
       //cn = DriverManager.getConnection("jdbc:mysql://127.5.54.2:3306","admingRN4WV4","sis");
       System.out.println("Se hizo la conexion exitosa");
       
      }catch(Exception e){
          System.out.println(e.getMessage());
      }return cn;
    }    
    
    Statement createStatement(){
        throw new UnsupportedOperationException("No soportado");
    }
}


