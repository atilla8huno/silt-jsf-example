package br.gov.go.saude.silt.corp.fonetico_pessoa.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.corp.enumerador.EnumTipoFoneticos;
import br.gov.go.saude.silt.corp.fonetico_pessoa.entidade.FoneticoPessoa;
import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.util.Fonetica;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de serviço FoneticoPessoa.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class FoneticoPessoaServico extends Servico<FoneticoPessoa> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private FoneticoPessoaDAO dao;

	/**
	 * Método inclui fonetização de uma pessoa física utilizando os atributos nome e nomeMae.
	 * 
	 * @param entidade
	 * @throws DAOException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void incluirFonetizacaoPessoaFisica(PessoaFisica entidade) throws ServicoException {
		try {
			if (entidade.getNome() != null && !"".equalsIgnoreCase(entidade.getNome())) {
				dao.incluirFonemas(entidade.getPessoa(), Fonetica.gerarFonemas(entidade.getNome()), EnumTipoFoneticos.NOMEPESSOA.getTipo());
			}

			if ((entidade.getNomeMae() != null) && (!"".equalsIgnoreCase(entidade.getNomeMae()))) {
				dao.incluirFonemas(entidade.getPessoa(), Fonetica.gerarFonemas(entidade.getNomeMae()), EnumTipoFoneticos.NOMEMAE.getTipo());
			}

		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	@Override
	protected DAO<FoneticoPessoa> getDAO() {
		return dao;
	}

	public static FoneticoPessoaServico getInstancia() {
		return (FoneticoPessoaServico) SpringContainer.getInstancia().getBean("foneticoPessoaServico");
	}

}
