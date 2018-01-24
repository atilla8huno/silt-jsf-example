package br.gov.go.saude.silt.corp.pessoa.servico;

import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.corp.pessoa.entidade.Pessoa;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persist�ncia Pessoa.
 * 
 * @author Cl�udio Esp�ndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class PessoaDAO extends DAO<Pessoa> {

	private static final long serialVersionUID = 1L;
}
