package br.gov.go.saude.silt.corp.estado_civil.servico;

import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.corp.estado_civil.entidade.EstadoCivil;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persistencia EstadoCivil.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class EstadoCivilDAO extends DAO<EstadoCivil> {

	private static final long serialVersionUID = 1L;
}
