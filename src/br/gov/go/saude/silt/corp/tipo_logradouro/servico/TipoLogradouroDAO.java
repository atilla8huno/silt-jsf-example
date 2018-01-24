package br.gov.go.saude.silt.corp.tipo_logradouro.servico;

import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.corp.tipo_logradouro.entidade.TipoLogradouro;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persistencia TipoLogradouro.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class TipoLogradouroDAO extends DAO<TipoLogradouro> {

	private static final long serialVersionUID = 1L;
}
