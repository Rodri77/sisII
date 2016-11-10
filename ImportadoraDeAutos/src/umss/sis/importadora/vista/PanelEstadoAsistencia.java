/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umss.sis.importadora.vista;

import Out.ConexionBD;
import Out.InterfazVistas;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author rodrigo
 */
public class PanelEstadoAsistencia extends JPanel {

    private JLabel titulo;
    private JScrollPane scroll;
    private JTable asistenciaEmpleados;
    private JPanel filtroDepartamento;
    private JComboBox departamentosCB;
    private ConexionBD con = new ConexionBD();
    private Connection cn = con.conexion();

    public PanelEstadoAsistencia() {
        init();
        fillTable();
        load();
    }

    private void init() {
        titulo = new JLabel("Estado De Asistencia De Empleados");
        filtroDepartamento = new JPanel(new GridLayout(1, 3));
        departamentosCB = new JComboBox(llenarComboBox().toArray());
        departamentosCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                departamentoSeleccionado(departamentosCB.getSelectedItem().toString());
            }

        });
        filtroDepartamento.add(departamentosCB);
    }

    private void load() {
        add(Box.createRigidArea(new Dimension(400, 40)));
        add(titulo, BorderLayout.PAGE_START);
        add(filtroDepartamento);
        add(Box.createRigidArea(new Dimension(400, 40)));
        add(scroll, BorderLayout.CENTER);
    }

    private void fillTable() {
        String[] columnNames = {"Nombre",
            "CI",
            "Cargo",
            "Estado Asistencia",};

        Object[][] data = {{"Miler", "123456",
            "Scrum Team", "Puntual"},
        {"German", "234567",
            "Scrum Team", "Atrasado"},
        {"Javier", "345678",
            "Scrum Team", "Puntual"},
        {"Luis", "456789",
            "Product Owner", "Atrasado"},
        {"Miguel", "246810",
            "Scrum Team", "Puntual"},
        {"Rodrigo", "135790",
            "Scrum Master", "Puntual"}};
        asistenciaEmpleados = new JTable(data, columnNames);
        asistenciaEmpleados.setFillsViewportHeight(true);
        scroll = new JScrollPane(asistenciaEmpleados);
        scroll.setPreferredSize(new Dimension(500, 150));
    }

    private ArrayList<String> llenarComboBox() {
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

//    private ArrayList<String> llenarTablaEstados(ResultSet empleado) {
//        ArrayList<String> empleados;
//        //empleados = empleado.;
//        
//        return empleados;
//    }

    private ResultSet departamentoSeleccionado(String content) {
        System.out.println(content + "seleccionado");
        ResultSet emps = null;
//        try {
//            PreparedStatement empleados = cn.prepareStatement("SELECT CIEmpleado, Nombre \n"
//                                                            + "FROM Empleado\n"
//                                                            + "WHERE IdDepartamento = (SELECT ID \n"
//                                                                                    + "FROM Departamento\n"
//                                                                                    + "WHERE Departamento = )");
//            emps = empleados.executeQuery();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(InterfazVistas.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return emps;
    }
}
