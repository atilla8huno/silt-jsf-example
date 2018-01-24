package br.gov.go.saude.silt.corp.categoria.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.categoria.entidade.Categoria;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico Categoria.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service("categoriaServico")
public class CategoriaServico extends Servico<Categoria> {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoriaDAO dao;

	@Override
	protected DAO<Categoria> getDAO() {
		return dao;
	}

	public static CategoriaServico getInstancia() {
		return (CategoriaServico) SpringContainer.getInstancia().getBean("categoriaServico");
	}

}
