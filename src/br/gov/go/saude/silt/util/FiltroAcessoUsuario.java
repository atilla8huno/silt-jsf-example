package br.gov.go.saude.silt.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import br.gov.go.saude.arquitetura.bean.Autenticacao;
import br.gov.go.saude.arquitetura.controle.servlet.Autenticador;
import br.gov.go.saude.silt.estabelecimento.servico.EstabelecimentoServico;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.usuario_estabelecimento.servico.UsuarioEstabelecimentoServico;

/**
 * Classe de Filtro para acesso do usuario ao sistema. Procura localizar os dados do usuarioEstabelecimento e seta-los na sessao. Dados esses necessarios para
 * filtrar todas as pesquisas por estabelecimento dentro do SILT.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Filter
 */
public class FiltroAcessoUsuario implements Filter {

	@Autowired
	private UsuarioEstabelecimentoServico usuarioEstabelecimentoServico;
	@Autowired
	private EstabelecimentoServico estabelecimentoServico;

	@Override
	public void destroy() { }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;

		try {
			HttpSession session = httpReq.getSession(true);
			// Pega os dados do usuario setados pelo portal
			Autenticacao autenticacao = (Autenticacao) session.getAttribute("autenticacao");

			if (autenticacao == null) {
				Autenticador.encaminharPaginaErroAutenticacao(httpReq, httpResp, Mensagem.MSG_ERRO_AUTENTICACAO);
			} else {
				// Pesquisa os dados de usuarioEstabelecimento necessarios para o acesso ao SILT.
				UsuarioEstabelecimento usuarioEstabelecimento = usuarioEstabelecimentoServico.consultarPorUsuarioSistema(autenticacao.getUsuario());
				
				if (usuarioEstabelecimento == null) {
					Autenticador.encaminharPaginaErroAutenticacao(httpReq, httpResp, Mensagem.MSG_CADASTRO_INCOMPLETO);
				} else {
					session.setAttribute("usuarioEstabelecimento", usuarioEstabelecimento);
					session.setAttribute("listaEstabelecimentosLiberados", estabelecimentoServico.consultarIdEstabelecimentos(usuarioEstabelecimento));
					
					chain.doFilter(request, response);
				}
			}
		} catch (Exception erro) {
			erro.printStackTrace();
			Autenticador.encaminharPaginaErroAutenticacao(httpReq, httpResp, Mensagem.MSG_ERRO_LOCALIZAR_USUARIO);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException { }

}
