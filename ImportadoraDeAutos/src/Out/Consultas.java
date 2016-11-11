/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Out;

import Modelo.Balance;
import Modelo.Departamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miler
 */
public class Consultas {
    
    
  ConexionBD con = new ConexionBD();
    Connection cn = con.conexion();
    
    
    public  DefaultTableModel detalleHerramienta (String herra){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Diponible");
        modelo.addColumn("Nombre");
        modelo.addColumn("apellido");
       
        
        
        String sql = "SELECT EstadoHerrammienta.Estado, Empleado.Nombre, Empleado.Apellidos FROM Herramienta, EstadoHerrammienta, Herramienta_Empleado, Empleado WHERE Herramienta.Herramienta = '"+herra+"' and Herramienta.estado = EstadoHerrammienta.idEstadoHerramienta and Herramienta_Empleado.Herramienta = Herramienta.idHerramienta and Herramienta_Empleado.empleado = Empleado.CIEmpleado";
        String datos[] = new String [3];
        Statement st;
        try{
                st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    datos[2] = rs.getString(3);
                   
                    modelo.addRow(datos);
                }
                
        } catch (SQLException ex) {
            Logger.getLogger(InterfazVistas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
               
    }
    public  DefaultTableModel  pedidos(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID/ Pedido ");
        modelo.addColumn("Proveedora");
        modelo.addColumn("Fecha");
        modelo.addColumn("Modelo");
        
        
        String sql = "select Pedido.idPedido, Proveedor.Nombre , Pedido.fecha, Modelo.modelo from Pedido, DetallePedido, Vehiculo, Modelo, Proveedor where Pedido.idPedido = DetallePedido.Pedido and DetallePedido.Vehiculo = Vehiculo.nroMotor and Modelo.idModelo = Vehiculo.modelo and Proveedor.idProveedor = Pedido.proveedora";
              
        String datos[] = new String [4];
        Statement st;
        try{
                st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    modelo.addRow(datos);
                }
                
        } catch (SQLException ex) {
            Logger.getLogger(InterfazVistas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
               
    }
    
    
    
    
    
    public void reg(String ci,String nombre ,String apellidos ,String direccion )
    {
     try {
            PreparedStatement pps = cn.prepareStatement("INSERT INTO cliente (Nombre,dirección,crédito) VALUES (?,?,?)");
            pps.setString(1, ci);
            pps.setString(2, nombre);
            pps.setString(3, apellidos);

            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos Guardados");
            

        } catch (SQLException ex) {
            Logger.getLogger(InterfazVistas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       
    
    
    public ArrayList departementos(){
        
        
        ArrayList<String> departamento;
        departamento = new ArrayList();
        try {
            PreparedStatement departamentos = cn.prepareStatement("SELECT Departamento FROM Departamento");
            ResultSet deps = departamentos.executeQuery();
            while (deps.next()) {
                departamento.add(deps.getString("Departamento"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(InterfazVistas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departamento;
        
        
       
    }
    
    
    
    public ArrayList Herramientas(){
        
        
        ArrayList<String> Herramienta;
        Herramienta = new ArrayList();
        try {
            PreparedStatement Herramientas = cn.prepareStatement("SELECT Herramienta FROM Herramienta");
            ResultSet deps = Herramientas.executeQuery();
            while (deps.next()) {
                Herramienta.add(deps.getString("Herramienta"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(InterfazVistas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Herramienta;
        
        
       
    }
    
    public Balance retornarBalancePorIdDepartamento(int idDepartamento){
        Balance balance = new Balance();
        String queryTemplate = "SELECT * FROM Balance WHERE idDepartamento = %d";
        String query = String.format(queryTemplate, idDepartamento);
        
        try {
            PreparedStatement consulta = cn.prepareStatement(query);
            ResultSet deps = consulta.executeQuery();
            while (deps.next()) {
                balance.setNroBalance(Integer.parseInt(deps.getString("nroBalance")));
                balance.setIdDepartamento(Integer.parseInt(deps.getString("idDepartamento")));
                balance.setIngreso(Long.parseLong(deps.getString("ingreso")));
                balance.setEgreso(Long.parseLong(deps.getString("egreso")));
                balance.setDeudas(Long.parseLong(deps.getString("deudas")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(InterfazVistas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return balance;
    }
    
    
     public ArrayList<Departamento> recuperarDepartamentos() {
        ArrayList<Departamento> departamentos;
        departamentos = new ArrayList();
        try {
            PreparedStatement consulta = cn.prepareStatement("SELECT * FROM Departamento");
            ResultSet deps = consulta.executeQuery();
            while (deps.next()) {
                Departamento departamento = new Departamento();
                departamento.setId(Integer.parseInt(deps.getString("ID")));
                departamento.setDepartamento(deps.getString("Departamento"));
                departamentos.add(departamento);
            }

        } catch (SQLException ex) {
            Logger.getLogger(InterfazVistas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departamentos;
    }
    
    
     public  DefaultTableModel obtenerVentas (String nroDetalle){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID/ ventas");
        modelo.addColumn("Comprador");
        modelo.addColumn("Modelo de Auto");
        modelo.addColumn("Tipo de venta");
        
        
        String sql = "select nroDetalle, C.ciComprador , auto, tipoVenta from DetalleVenta D , Comprador C where D.ciComprador = C.ciComprador and nroDetalle = "+nroDetalle;
        
        String datos[] = new String [4];
        Statement st;
        try{
                st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    datos[0] = rs.getString(1);
                    datos[1] = rs.getString(2);
                    datos[2] = rs.getString(3);
                    datos[3] = rs.getString(4);
                    modelo.addRow(datos);
                }
                
        } catch (SQLException ex) {
            Logger.getLogger(InterfazVistas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
               
    }
    
    
}
