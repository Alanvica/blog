/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.dao;

import com.emergentes.modelo.post;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Villalba
 */
public class PostDAOImpl extends ConexionDB implements PostDAO {

    @Override
    public void insert(post post) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("insert into posts(fecha, titulo, contenido) values(?, ?, ?)");
            ps.setString(1, post.getFecha());
            ps.setString(2, post.getTitulo());
            ps.setString(3, post.getContenido());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(post post) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("update posts set fecha=?, titulo=?, contenido=?");
            ps.setString(1, post.getFecha());
            ps.setString(2, post.getTitulo());
            ps.setString(3, post.getContenido());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void delete(int id) throws Exception {
        
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("delete from posts where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public post getById(int id) throws Exception {
        post po = new post();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("select * from posts where id = ? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (true) {
                po.setId(rs.getInt("id"));
                po.setFecha(rs.getString("fecha"));
                po.setTitulo(rs.getString("titulo"));
                po.setContenido(rs.getString("contenido"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return po;
    }

    @Override
    public List<post> getAll() throws Exception {
        List<post> lista = null;
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("select * from posts");
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<post>();
            while (rs.next()) {                
                post po = new post();
                po.setId(rs.getInt("id"));
                po.setFecha(rs.getString("fecha"));
                po.setTitulo(rs.getString("titulo"));
                po.setContenido(rs.getString("contenido"));
                lista.add(po);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }

}
