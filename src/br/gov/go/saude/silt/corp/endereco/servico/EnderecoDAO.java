package br.gov.go.saude.silt.corp.endereco.servico;

import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.corp.endereco.entidade.Endereco;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persistencia Endereco.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class EnderecoDAO extends DAO<Endereco> {

	private static final long serialVersionUID = 1L;
}
