package br.com.delivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.delivery.domain.Usuario;
import br.com.delivery.factory.ConexaoFactory;

public class UsuarioDAO {
	public void salvar(Usuario u) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO usuario ");
		sql.append("(login,senha,tipo) ");
		sql.append("VALUES (?,?,?)");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, u.getLogin());
		comando.setString(2, u.getSenha());
		comando.setInt(3,u.getTipo());
		comando.executeUpdate();

	}
	
	public ArrayList<Usuario> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT login,senha,tipo ");
		sql.append("FROM usuario ");
		sql.append("ORDER BY id_usuario ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		
		while (resultado.next()){
			Usuario u = new Usuario();
			u.setLogin(resultado.getString("login"));
			u.setSenha(resultado.getString("senha"));
			u.setTipo(resultado.getInt("tipo"));
			
			lista.add(u);
		}
		
		return lista;
		
	}

}
