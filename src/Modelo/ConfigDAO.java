/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author eljos
 */
public class ConfigDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarConfig(Config conf){
        String sql = "INSERT INTO config (id ,ruc, nombre, telefono, direccion, mensaje) VALUES (?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, conf.getId());
            ps.setString(2, conf.getRuc());
            ps.setString(3, conf.getNombre());
            ps.setString(4, conf.getTelefono());
            ps.setString(5, conf.getDireccion());
            ps.setString(6, conf.getMensaje());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    
    
    public List ListarConfig(){
       List<Config> ListaCl = new ArrayList();
       String sql = "SELECT * FROM config WHERE id=1";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Config conf = new Config();
               conf.setId(rs.getInt("id"));
               conf.setRuc(rs.getString("ruc"));
               conf.setNombre(rs.getString("nombre"));
               conf.setTelefono(rs.getString("telefono"));
               conf.setDireccion(rs.getString("direccion"));
               conf.setMensaje(rs.getString("mensaje"));
               ListaCl.add(conf);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return ListaCl;
   }
    
    
    
    public boolean ModificarConfig(Config conf){
       String sql = "UPDATE config SET ruc=?, nombre=?, telefono=?, direccion=?, mensaje=? WHERE id=1";
       try {
           ps = con.prepareStatement(sql);   
           ps.setString(1, conf.getRuc());
           ps.setString(2, conf.getNombre());
           ps.setString(3, conf.getTelefono());
           ps.setString(4, conf.getDireccion());
           ps.setString(5, conf.getMensaje());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }finally{
           try {
               con.close();
           } catch (SQLException e) {
               System.out.println(e.toString());
           }
       }
   }
}
