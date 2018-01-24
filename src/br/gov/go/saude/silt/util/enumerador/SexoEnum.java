package br.gov.go.saude.silt.util.enumerador;

import java.util.List;

import br.gov.go.saude.arquitetura.enumerador.iface.IEnumDominio;
import br.gov.go.saude.arquitetura.util.EnumeradorUtil;

/**
 * @author Átilla Barros
 * @version $Rev: 252 $ $Author: atillabarros $ $Date: 2013-11-26 14:54:10 -0200 (Ter, 26 Nov 2013) $
 * @category Enum
 */
public enum SexoEnum implements IEnumDominio<Long> {

	MASCULINO(20L, "MASCULINO", "MASCULINO", "M", false),
	FEMININO(21L, "FEMININO", "FEMININO", "F", false),
	IGNORADO(5L, "IGNORADO", "IGNORADO", "I", false);

	private Long id;
	private String descricao;
	private String conteudo;
	private String sigla;
	private boolean excluido;

	private SexoEnum(Long id, String descricao, String conteudo, String sigla, boolean excluido) {
		this.id = id;
		this.descricao = descricao;
		this.conteudo = conteudo;
		this.sigla = sigla;
		this.excluido = excluido;
	}

	public List<SexoEnum> getListaSimNaoEnum() {
		return EnumeradorUtil.getDominiosAtivos(SexoEnum.class);
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

	public String getSigla() {
		return sigla;
	}
}
