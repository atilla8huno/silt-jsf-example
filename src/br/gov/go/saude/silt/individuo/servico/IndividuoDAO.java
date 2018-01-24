package br.gov.go.saude.silt.individuo.servico;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.individuo.entidade.Individuo;
import br.gov.go.saude.silt.util.FiltroPesquisa;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * @author Átilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category DAO
 */
@Repository("individuoDAO")
public class IndividuoDAO extends DAO<Individuo> {

	private static final long serialVersionUID = 1L;

	/**
	 * Este método recebe uma PessoaFisica como argumento e pesquisa na tabela Individuo
	 * 
	 * @param PessoaFisica pessoa
	 * @return Individuo
	 * @throws DAOException
	 */
	protected Individuo consultarPorPessoaFisica(PessoaFisica pessoa) throws DAOException {
		try {
			Criteria criteria = createCriteria().createAlias("pessoaFisica", "pessoa").add(Restrictions.eq("pessoa.id", pessoa.getId()));

			// Adiciona o filtro por Estabelecimento
			addRestricaoCriteriaPorEstabelecimento(criteria);

			return (Individuo) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Este método recebe um CPF como argumento e pesquisa na tabela Individuo
	 * 
	 * @param Long cpf
	 * @return Individuo
	 * @throws DAOException
	 */
	protected Individuo consultarPorCPF(Long cpf) throws DAOException {
		try {
			Criteria criteria = createCriteria().createAlias("pessoaFisica", "pessoa").add(Restrictions.eq("pessoa.cpf", cpf));

			// Adiciona o filtro por Estabelecimento
			addRestricaoCriteriaPorEstabelecimento(criteria);

			return (Individuo) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Este método recebe um Nome como argumento e pesquisa na tabela Individuo
	 * 
	 * @param String nome
	 * @return List<Individuo>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	public List<Individuo> consultarPorNome(String nome) throws DAOException {
		try {
			Criteria criteria = createCriteria().createAlias("pessoaFisica", "pessoa");

			if (nome != null && !nome.isEmpty()) {
				criteria.add(Restrictions.ilike("pessoa.nome", "%" + nome + "%"));
			}

			// Adiciona o filtro por Estabelecimento
			addRestricaoCriteriaPorEstabelecimento(criteria);

			return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Este método realiza a consulta de Individuos, podendo ser paginada ou nao.
	 * 
	 * @param FiltroPesquisa filtroPesquisa
	 * @return List<Individuo>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected List<Individuo> consultarPorFiltro(FiltroPesquisa filtro) throws DAOException {
		try {
			if (filtro != null) {
				Criteria criteria = createCriteria().createAlias("pessoaFisica", "pessoa");

				criteria.add(Restrictions.eq("excluido", Boolean.FALSE));
				criteria.add(Restrictions.eq("pessoa.exclusao", Boolean.FALSE));

				if (filtro.getPosicaoPrimeiraLinha() != null) {
					criteria.setFirstResult(filtro.getPosicaoPrimeiraLinha());
				}

				if (filtro.getMaximoPorPagina() != null) {
					criteria.setMaxResults(filtro.getMaximoPorPagina());
				}

				Date dataInicio = null, dataFim = null;
				for (Map.Entry<String, Object> item : filtro.getCampos().entrySet()) {
					if (item != null && item.getValue() != null) {

						if (item.getValue() instanceof String) {
							criteria.add(Restrictions.ilike(item.getKey(), "%" + item.getValue() + "%"));

						} else if (item.getValue() instanceof Long) {
							criteria.add(Restrictions.eq(item.getKey(), item.getValue()));

						} else if (item.getValue() instanceof Date && item.getKey().equals("dataNascimentoInicio")) {
							dataInicio = (Date) item.getValue();

						} else if (item.getValue() instanceof Date && item.getKey().equals("dataNascimentoFim")) {
							dataFim = (Date) item.getValue();

						} else if (item.getValue() instanceof Estabelecimento && item.getKey().equals("estabelecimento")) {
							criteria.createAlias("estabelecimento", "est");
							criteria.add(Restrictions.eq("est.id", ((Estabelecimento) item.getValue()).getId()));
						}
					}
				}

				if ((dataInicio != null && dataFim != null) && (dataInicio.before(dataFim) || dataFim.equals(dataInicio))) {
					criteria.add(Restrictions.between("pessoa.dataNascimento", dataInicio, dataFim));
				}

				if (filtro.getCamposOrdenacaoPesquisa() != null && !filtro.getCamposOrdenacaoPesquisa().isEmpty()) {
					for (String item : filtro.getCamposOrdenacaoPesquisa()) {
						criteria.addOrder(Order.asc(item));
					}
				}

				// Adiciona o filtro por Estabelecimento
				addRestricaoCriteriaPorEstabelecimento(criteria);

				return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			}
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Retorna a quantidade de linhas de uma determinada consulta.
	 * 
	 * @param Map<String, Object> filtro
	 * @return Long
	 * @throws DAOException
	 */
	@Override
	protected Long getNumeroLinhas(Map<String, Object> filtro) throws DAOException {
		try {
			Criteria criteria = createCriteria().createAlias("pessoaFisica", "pessoa");

			criteria.add(Restrictions.eq("excluido", Boolean.FALSE));
			criteria.add(Restrictions.eq("pessoa.exclusao", Boolean.FALSE));

			Date dataInicio = null, dataFim = null;
			for (Map.Entry<String, Object> item : filtro.entrySet()) {
				if (item != null && item.getValue() != null) {

					if (item.getValue() instanceof String) {
						criteria.add(Restrictions.ilike(item.getKey(), "%" + item.getValue() + "%"));

					} else if (item.getValue() instanceof Long) {
						criteria.add(Restrictions.eq(item.getKey(), item.getValue()));

					} else if (item.getValue() instanceof Date && item.getKey().equals("dataNascimentoInicio")) {
						dataInicio = (Date) item.getValue();

					} else if (item.getValue() instanceof Date && item.getKey().equals("dataNascimentoFim")) {
						dataFim = (Date) item.getValue();

					} else if (item.getValue() instanceof Estabelecimento && item.getKey().equals("estabelecimento")) {
						criteria.createAlias("estabelecimento", "est");
						criteria.add(Restrictions.eq("est.id", ((Estabelecimento) item.getValue()).getId()));
					}
				}
			}

			if ((dataInicio != null && dataFim != null) && (dataInicio.before(dataFim) || dataFim.equals(dataInicio))) {
				criteria.add(Restrictions.between("pessoa.dataNascimento", dataInicio, dataFim));
			}

			criteria.setProjection(Projections.rowCount());

			// Adiciona o filtro por Estabelecimento
			addRestricaoCriteriaPorEstabelecimento(criteria);

			return (Long) criteria.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
