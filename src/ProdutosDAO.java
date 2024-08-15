
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
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }    
        
}

