package br.gov.go.saude.silt.util.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.gov.go.saude.silt.util.FiltroPesquisa;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe realiza a paginacao de uma determinada lista de objetos a ser exibida em um componete dataTable que tenha paginacao.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Utilitario
 */
public class ListaPaginada<T extends Entidade> extends LazyDataModel<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<T> lista;

	private Servico<T> servico;

	private FiltroPesquisa filtroPesquisa;

	public ListaPaginada(Servico<T> servico) {
		super();
		this.servico = servico;
		lista = new ArrayList<T>();
		filtroPesquisa = new FiltroPesquisa();
	}

	/**
	 * Este método limpa os campos e a lista com resultados.
	 */
	public void limpar() {
		getFiltroPesquisa().getCampos().clear();
		getFiltroPesquisa().getCamposOrdenacaoPesquisa().clear();
		getLista().clear();
	}

	/**
	 * Este método adiciona um filtro no Map de filtros.
	 * 
	 * @param String chave
	 * @param Object valor
	 */
	public void addFiltro(String chave, Object valor) {
		getFiltroPesquisa().getCampos().put(chave, valor);
	}

	/**
	 * Este método adiciona um campo no List de campos para ordenacao da pesquisa a ser exibida.
	 * 
	 * @param String campo
	 */
	public void addOrdenacao(String campo) {
		getFiltroPesquisa().getCamposOrdenacaoPesquisa().add(campo);
	}

	/**
	 * Metodo realiza a consulta para o componente dataTable. Nas consultas e levado em conta os filtros presente na tela tanto para a paginacao quanto a acao
	 * de clicar no botao pesquisar.
	 */
	@Override
	@SuppressWarnings("all")
	public List<T> load(int posicaoPrimeiraLinha, int maximoPorPagina, String ordenarPeloCampo, SortOrder ordernarAscOuDesc, Map<String, String> filtros) {
		try {
			Long resultado = 0L;
			if (!filtroPesquisa.getCampos().isEmpty()) {
				// seta as informacoes para realizar a paginacao
				filtroPesquisa.setPosicaoPrimeiraLinha(posicaoPrimeiraLinha);
				filtroPesquisa.setMaximoPorPagina(maximoPorPagina);

				// Realiza a consulta do numero de linhas antes para evitar uma segunda consulta para o preenchimento da lista quando o resultado for 0.
				resultado = getServico().getNumeroLinhas(filtroPesquisa.getCampos());

				if (resultado > 0) {
					lista = getServico().consultarPorFiltro(filtroPesquisa);
					// Previne solicitar uma pesquisa com filtro e estar a partir da 2ª paginacao no dataTable
					if (lista.isEmpty()) {
						filtroPesquisa.setPosicaoPrimeiraLinha(0);
						lista = getServico().consultarPorFiltro(filtroPesquisa);
					}
				} else {
					getLista().clear();
				}
				// quantidade a ser exibida em cada página
				setPageSize(maximoPorPagina);
			}
			// total encontrado no banco de dados,
			setRowCount(resultado.intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public T getRowData(String rowKey) {
		for (T entidade : lista) {
			if (rowKey.equals(String.valueOf(entidade.getId()))) {
				return entidade;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(T entidade) {
		return entidade.getId();
	}

	@Override
	public void setRowIndex(int rowIndex) {
		// solução para evitar ArithmeticException
		if (rowIndex == -1 || getPageSize() == 0) {
			super.setRowIndex(-1);
		} else {
			super.setRowIndex(rowIndex % getPageSize());
		}
	}

	private Servico<T> getServico() {
		return servico;
	}

	public List<T> getLista() {
		if (lista == null) {
			lista = new ArrayList<T>();
		}
		return lista;
	}

	public void setLista(List<T> lista) {
		this.lista = lista;
	}

	public FiltroPesquisa getFiltroPesquisa() {
		if (filtroPesquisa == null) {
			filtroPesquisa = new FiltroPesquisa();
		}
		return filtroPesquisa;
	}

	public void setFiltroPesquisa(FiltroPesquisa filtroPesquisa) {
		this.filtroPesquisa = filtroPesquisa;
	}
}
