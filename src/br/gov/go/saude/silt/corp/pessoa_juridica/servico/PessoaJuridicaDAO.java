package br.gov.go.saude.silt.corp.pessoa_juridica.servico;

import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.corp.pessoa_juridica.entidade.PessoaJuridica;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persistencia PessoaJuridica.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class PessoaJuridicaDAO extends DAO<PessoaJuridica> {

	private static final long serialVersionUID = 1L;
}
