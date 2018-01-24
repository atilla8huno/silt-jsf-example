package br.gov.go.saude.silt.condicao_risco.servico;

import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.condicao_risco.entidade.CondicaoRisco;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * @author Átilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category DAO
 */
@Repository
public class CondicaoRiscoDAO extends DAO<CondicaoRisco> {

	private static final long serialVersionUID = 1L;
}
