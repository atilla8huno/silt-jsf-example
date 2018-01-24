package br.gov.go.saude.silt.util.enumerador;

import java.util.List;

import br.gov.go.saude.arquitetura.enumerador.iface.IEnumDominio;
import br.gov.go.saude.arquitetura.util.EnumeradorUtil;

/**
 * @author Átilla Barros
 * @version $Rev: 283 $ $Author: claudiocosta $ $Date: 2013-12-19 16:03:21 -0200 (Qui, 19 Dez 2013) $
 * @category Enum
 */
public enum StatusEnum implements IEnumDominio<Long> {

	ATIVO(10L, "ATIVO", "ATIVO", false), ENCERRADO(11L, "ENCERRADO", "ENCERRADO", false), TODOS(12L, "TODOS", "TODOS", false);

	private Long id;
	private String descricao;
	private String conteudo;
	private boolean excluido;

	private StatusEnum(Long id, String descricao, String conteudo, boolean excluido) {
		this.id = id;
		this.descricao = descricao;
		this.conteudo = conteudo;
		this.excluido = excluido;
	}

	public List<StatusEnum> getListaStatusEnum() {
		return EnumeradorUtil.getDominiosAtivos(StatusEnum.class);
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
