package br.gov.go.saude.silt.corp.escolaridade.servico;

import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.corp.escolaridade.entidade.Escolaridade;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persistencia Escolaridade.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class EscolaridadeDAO extends DAO<Escolaridade> {
	
	private static final long serialVersionUID = 1L;
}
