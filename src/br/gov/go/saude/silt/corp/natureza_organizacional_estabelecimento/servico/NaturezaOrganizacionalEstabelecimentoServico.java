package br.gov.go.saude.silt.corp.natureza_organizacional_estabelecimento.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.natureza_organizacional_estabelecimento.entidade.NaturezaOrganizacionalEstabelecimento;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico NaturezaOrganizacionalEstabelecimento.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class NaturezaOrganizacionalEstabelecimentoServico extends Servico<NaturezaOrganizacionalEstabelecimento> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private NaturezaOrganizacionalEstabelecimentoDAO dao;

	@Override
	protected DAO<NaturezaOrganizacionalEstabelecimento> getDAO() {
		return dao;
	}

	public static NaturezaOrganizacionalEstabelecimentoServico getInstancia() {
		return (NaturezaOrganizacionalEstabelecimentoServico) SpringContainer.getInstancia().getBean("naturezaOrganizacionalEstabelecimentoServico");
	}

}
