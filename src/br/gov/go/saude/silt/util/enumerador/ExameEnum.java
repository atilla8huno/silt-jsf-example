package br.gov.go.saude.silt.util.enumerador;

import java.util.List;

import br.gov.go.saude.arquitetura.enumerador.iface.IEnumDominio;
import br.gov.go.saude.arquitetura.util.EnumeradorUtil;

/**
 * @author Átilla Barros
 * @version $Rev: 203 $ $Author: atillabarros $ $Date: 2013-10-02 17:42:34 -0300 (Qua, 02 Out 2013) $
 * @category Enum
 */
public enum ExameEnum implements IEnumDominio<Long> {

	EXAME_REALIZADO(10L, "SIM, EXAME REALIZADO", "SIM, EXAME REALIZADO", false), 
	EXAME_NAO_REALIZADO(11L, "EXAME NÃO REALIZADO", "EXAME NÃO REALIZADO", false);

	private Long id;
	private String descricao;
	private String conteudo;
	private boolean excluido;
	
	private ExameEnum(Long id, String descricao, String conteudo, boolean excluido) {
		this.id = id;
		this.descricao = descricao;
		this.conteudo = conteudo;
		this.excluido = excluido;
	}
	
	public List<ExameEnum> getListaStatusEnum() {
		return EnumeradorUtil.getDominiosAtivos(ExameEnum.class);
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
