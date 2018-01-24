package br.gov.go.saude.silt.corp.pessoa_juridica.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.pessoa_juridica.entidade.PessoaJuridica;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico PessoaJuridica.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class PessoaJuridicaServico extends Servico<PessoaJuridica> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PessoaJuridicaDAO dao;

	@Override
	protected DAO<PessoaJuridica> getDAO() {
		return dao;
	}

	public static PessoaJuridicaServico getInstancia() {
		return (PessoaJuridicaServico) SpringContainer.getInstancia().getBean("pessoaJuridicaServico");
	}

}
