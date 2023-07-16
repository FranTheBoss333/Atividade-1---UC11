/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    static Connection conn;
    PreparedStatement prep;
    static Statement stmt;
    static ResultSet resultset;
    static ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto) throws SQLException{
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement("insert into produtos (nome,valor,status) values (?,?,?)");
        prep.setString(1,produto.getNome());
        prep.setInt(2,produto.getValor());
        prep.setString(3,produto.getStatus());
        prep.executeUpdate();
    }
    
    public static ArrayList<ProdutosDTO> listarProdutos() throws SQLException{
        conn = new conectaDAO().connectDB();
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from produtos");
        while(rs.next())
        {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            int valor = rs.getInt("valor");
            String status = rs.getString("status");
            ProdutosDTO produto = new ProdutosDTO();     
            produto.setId(id);
            produto.setNome(nome);
            produto.setValor(valor);
            produto.setStatus(status);
            listagem.add(produto);
        }
        return listagem;
    }
    
    public void venderProduto(int id) throws SQLException
    {
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement("update produtos set status='Vendido' where id=?");
        prep.setInt(1, id);
        prep.executeUpdate();
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() throws SQLException
    {
        conn = new conectaDAO().connectDB();
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from produtos where status='Vendido'");
        while(rs.next())
        {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            int valor = rs.getInt("valor");
            String status = rs.getString("status");
            ProdutosDTO produto = new ProdutosDTO();     
            produto.setId(id);
            produto.setNome(nome);
            produto.setValor(valor);
            produto.setStatus(status);
            listagem.add(produto);
        }
        return listagem;
    }
      
}

