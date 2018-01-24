package br.gov.go.saude.silt.corp.pessoa.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.pessoa.entidade.Pessoa;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de serviço Pessoa.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class PessoaServico extends Servico<Pessoa> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PessoaDAO dao;

	@Override
	protected DAO<Pessoa> getDAO() {
		return dao;
	}

}
