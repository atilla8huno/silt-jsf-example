package br.gov.go.saude.silt.util;

import java.util.List;

import javax.servlet.http.HttpSession;

import br.gov.go.saude.arquitetura.util.AplicacaoUtil;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;

public class AutenticacaoSilt {

	/**
	 * Construtor privado para evitar criar instância
	 */
	private AutenticacaoSilt() {
	}

	/**
	 * Retorna o objeto usuarioEstabelecimento que esta logado.
	 * 
	 * @return
	 */
	public static UsuarioEstabelecimento getUsuarioEstabelecimento() {
		HttpSession sessao = AplicacaoUtil.getRequest().getSession(true);
		if (sessao != null) {
			return (UsuarioEstabelecimento) sessao.getAttribute("usuarioEstabelecimento");
		}
		return null;
	}

	/**
	 * Retorna uma lista dos estabelecimentos que o usuario logado pode acessar.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Long> getListaEstabelecimentosLiberados() {
		HttpSession sessao = AplicacaoUtil.getRequest().getSession(true);
		if (sessao != null) {
			return (List<Long>) sessao.getAttribute("listaEstabelecimentosLiberados");
		}
		return null;
	}

}
