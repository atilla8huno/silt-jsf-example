package br.gov.go.saude.silt.util.enumerador;

import java.util.List;

import br.gov.go.saude.arquitetura.enumerador.iface.IEnumDominio;
import br.gov.go.saude.arquitetura.util.EnumeradorUtil;

/**
 * @author Átilla Barros
 * @version $Rev: 184 $ $Author: atillabarros $ $Date: 2013-09-23 13:35:16 -0300 (Seg, 23 Set 2013) $
 * @category Enum
 */
public enum RaioXEnum implements IEnumDominio<Long> {

	NORMAL(15L, "NORMAL", "NORMAL", false), 
	OUTRA_DOENCA(16L, "OUTRA DOENÇA", "OUTRA DOENÇA", false), 
	TB_CICATRICIAL(17L, "TB CICATRICIAL (SEQUELA)", "TB CICATRICIAL (SEQUELA)", false), 
	NAO_REALIZADO(18L, "NÃO REALIZADO", "NÃO REALIZADO", false);

	private Long id;
	private String descricao;
	private String conteudo;
	private boolean excluido;

	private RaioXEnum(Long id, String descricao, String conteudo, boolean excluido) {
		this.id = id;
		this.descricao = descricao;
		this.conteudo = conteudo;
		this.excluido = excluido;
	}

	public List<RaioXEnum> getListaRaioXEnum() {
		return EnumeradorUtil.getDominiosAtivos(RaioXEnum.class);
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
