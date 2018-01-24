package br.gov.go.saude.silt.estabelecimento.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class EstabelecimentoServico extends Servico<Estabelecimento> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EstabelecimentoDAO dao;

	/**
	 * Este método recebe uma entidade por parâmetro e a persiste no banco de dados.
	 * 
	 * @param entidade
	 * @throws ServicoException
	 * @return entidade
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public Estabelecimento incluir(Estabelecimento entidade) throws ServicoException {
		entidade.setExcluido(Boolean.FALSE);
		return super.incluir(entidade);
	}

	/**
	 * Este metodo retorna uma lista de Estabelecimento de acordo com o nomeFantasia informado com limite de 10 itens.
	 * 
	 * @param nomeFantasia
	 * @return List<Estabelecimento>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Estabelecimento> consultarPorNomeFantasia(String nomeFantasia) throws ServicoException {
		try {
			return dao.consultarPorNomeFantasia(nomeFantasia);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Metodo retorna uma lista de apenas estabelecimento que sejam nucleos.
	 * 
	 * @param nomeFantasia
	 * @return Estabelecimento
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Estabelecimento> consultarEstabelecimentoNucleoPorNomeFantasia(String nomeFantasia) throws ServicoException {
		try {
			return dao.consultarEstabelecimentoNucleoPorNomeFantasia(nomeFantasia);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Metodo retorna uma lista dos ids dos estabelecimentos que o usuario esta relacionado, para realizar os filtros de pesquisa das listagens. Dessa forma se
	 * garante que o usuario nao vera dados que nao fazem parte do seu estabelecimento.
	 * 
	 * @return
	 */
	public List<Long> consultarIdEstabelecimentos(UsuarioEstabelecimento usuario) throws ServicoException {
		try {
			return dao.consultarIdEstabelecimentos(usuario);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	@Override
	protected DAO<Estabelecimento> getDAO() {
		return dao;
	}

	public static EstabelecimentoServico getInstancia() {
		return (EstabelecimentoServico) SpringContainer.getInstancia().getBean("estabelecimentoServico");
	}
}
