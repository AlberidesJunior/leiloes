
/**
 *
 * @author Adm
 * @author AlberidesJr
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        try {
            
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("INSERT INTO produtos (nome,valor,status) VALUES(?,?,?)");
            prep.setString(1,produto.getNome());
            prep.setInt(2,produto.getValor() );
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto Cadastrado com sucesso!");            
            
        } catch (SQLException ex) {
            System.out.println("Erro de conexão: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Produto não Cadastrado!");
            //return ex.getErrorCode();
        }
        
    }
    
    public void venderProduto (Integer id){
        
        try {
            
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("UPDATE produtos SET status=\"Vendido\" WHERE id=?");
            prep.setInt(1,id );
            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto Atualizado com sucesso!");            
            
        } catch (SQLException ex) {
            System.out.println("Erro de conexão: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Produto não Cadastrado!");
            //return ex.getErrorCode();
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        String sql = "SELECT * FROM produtos";
        
        try{
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();        
            List<ProdutosDTO> listagem = new ArrayList<>();

            while (resultset.next()){
                    ProdutosDTO produto = new ProdutosDTO();

                    produto.setId(resultset.getInt("id"));
                    produto.setNome(resultset.getString("nome"));
                    produto.setValor(resultset.getInt("valor"));
                    produto.setStatus(resultset.getString("status"));

                    listagem.add(produto);
            }

            return (ArrayList<ProdutosDTO>) listagem;
            
        }catch(SQLException e){
                return null;
        }
    }
    
    public ArrayList<ProdutosDTO> listarVendidos(){
        
        String sql = "SELECT * FROM produtos WHERE status LIKE 'Vendido'";        
        ArrayList<ProdutosDTO> listaVendidos = new ArrayList<>();
        
        try{
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()){
                    ProdutosDTO produtoVendido = new ProdutosDTO();

                    produtoVendido.setId(resultset.getInt("id"));
                    produtoVendido.setNome(resultset.getString("nome"));
                    produtoVendido.setValor(resultset.getInt("valor"));
                    produtoVendido.setStatus(resultset.getString("status"));

                    listaVendidos.add(produtoVendido);
            }
            
            return (ArrayList<ProdutosDTO>) listaVendidos;
            
        }catch(SQLException e){
            return null;
        }
    }
    
}

