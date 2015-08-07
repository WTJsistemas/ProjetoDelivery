package br.com.delivery.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.delivery.dao.UsuarioDAO;
import br.com.delivery.domain.Usuario;

@ManagedBean(name = "MBUsuario")
@ViewScoped
public class UsuarioBean {
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario Usuario) {
		this.usuario = Usuario;
	}

	private ListDataModel<Usuario> itens;

	public ListDataModel<Usuario> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Usuario> itens) {
		this.itens = itens;
	}

	@PostConstruct
	public void prepararPesquisa() {
		UsuarioDAO fdao = new UsuarioDAO();
		ArrayList<Usuario> lista;
		try {
			lista = fdao.listar();
			itens = new ListDataModel<Usuario>(lista);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void prepararNovo(){
		usuario = new Usuario();
	}

	public void novo() {
		UsuarioDAO dao = new UsuarioDAO();
		try {
			dao.salvar(usuario);
			ArrayList<Usuario> lista = dao.listar();
			itens = new ListDataModel<Usuario>(lista);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
