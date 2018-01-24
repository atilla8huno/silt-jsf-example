package br.gov.go.saude.silt.util.enumerador;

import java.util.List;

import br.gov.go.saude.arquitetura.enumerador.iface.IEnumDominio;
import br.gov.go.saude.arquitetura.util.EnumeradorUtil;

/**
 * @author Átilla Barros
 * @version $Rev: 192 $ $Author: atillabarros $ $Date: 2013-09-24 18:25:05 -0300 (Ter, 24 Set 2013) $
 * @category Enum
 */
public enum BaciloCgEnum implements IEnumDominio<Long> {

	SIM_MENOS_DE_DOIS_ANOS(1L, "SIM, HÁ MENOS DE DOIS ANOS", "SIM, HÁ MENOS DE DOIS ANOS", false),
	SIM_MAIS_DE_DOIS_ANOS(2L, "SIM, HÁ MAIS DE DOIS ANOS", "SIM, HÁ MAIS DE DOIS ANOS", false),
	NAO(3L, "NÃO", "NÃO", false),
	IGNORADO(5L, "IGNORADO", "IGNORADO", false);

	private Long id;
	private String descricao;
	private String conteudo;
	private boolean excluido;
	
	private BaciloCgEnum(Long id, String descricao, String conteudo, boolean excluido) {
		this.id = id;
		this.descricao = descricao;
		this.conteudo = conteudo;
		this.excluido = excluido;
	}

	public List<BaciloCgEnum> getListaBaciloCgEnum() {
		return EnumeradorUtil.getDominiosAtivos(BaciloCgEnum.class);
	}
	
	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getConteudo() {
		return conteudo;
	}

	public boolean isExcluido() {
		return excluido;
	}
}
