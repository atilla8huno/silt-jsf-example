package br.gov.go.saude.silt.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.primefaces.model.SortOrder;

/**
 * Classe para Filtro de Pesquisa.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 226 $ $Author: claudiocosta $ $Date: 2013-11-06 15:10:29 -0200 (Qua, 06 Nov 2013) $
 * @category Filtro
 */
public class FiltroPesquisa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer posicaoPrimeiraLinha;
	private Integer maximoPorPagina;
	private Set<String> camposOrdenacaoPesquisa;
	private SortOrder ordernarAscOuDesc;
	private Map<String, Object> campos;

	public FiltroPesquisa() {
		campos = new HashMap<String, Object>();
		camposOrdenacaoPesquisa = new LinkedHashSet<String>();
	}

	public Integer getPosicaoPrimeiraLinha() {
		return posicaoPrimeiraLinha;
	}

	public void setPosicaoPrimeiraLinha(Integer posicaoPrimeiraLinha) {
		this.posicaoPrimeiraLinha = posicaoPrimeiraLinha;
	}

	public Integer getMaximoPorPagina() {
		return maximoPorPagina;
	}

	public void setMaximoPorPagina(Integer maximoPorPagina) {
		this.maximoPorPagina = maximoPorPagina;
	}

	public Set<String> getCamposOrdenacaoPesquisa() {
		return camposOrdenacaoPesquisa;
	}

	public void setCamposOrdenacaoPesquisa(Set<String> camposOrdenacaoPesquisa) {
		this.camposOrdenacaoPesquisa = camposOrdenacaoPesquisa;
	}

	public SortOrder getOrdernarAscOuDesc() {
		return ordernarAscOuDesc;
	}

	public void setOrdernarAscOuDesc(SortOrder ordernarAscOuDesc) {
		this.ordernarAscOuDesc = ordernarAscOuDesc;
	}

	public Map<String, Object> getCampos() {
		return campos;
	}

	public void setCampos(Map<String, Object> campos) {
		this.campos = campos;
	}
}
