package br.gov.go.saude.silt.util.enumerador;

import java.util.List;

import br.gov.go.saude.arquitetura.enumerador.iface.IEnumDominio;
import br.gov.go.saude.arquitetura.util.EnumeradorUtil;

/**
 * @author Átilla Barros
 * @version $Rev: 192 $ $Author: atillabarros $ $Date: 2013-09-24 18:25:05 -0300 (Ter, 24 Set 2013) $
 * @category Enum
 */
public enum SimNaoEnum implements IEnumDominio<Long> {

	SIM(4L, "SIM", "SIM", false),
	NAO(3L, "NÃO", "NÃO", false),
	IGNORADO(5L, "IGNORADO", "IGNORADO", false);

	private Long id;
	private String descricao;
	private String conteudo;
	private boolean excluido;

	private SimNaoEnum(Long id, String descricao, String conteudo, boolean excluido) {
		this.id = id;
		this.descricao = descricao;
		this.conteudo = conteudo;
		this.excluido = excluido;
	}

	public List<SimNaoEnum> getListaSimNaoEnum() {
		return EnumeradorUtil.getDominiosAtivos(SimNaoEnum.class);
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	@Override
	public String getConteudo() {
		return conteudo;
	}

	@Override
	public boolean isExcluido() {
		return excluido;
	}
}
