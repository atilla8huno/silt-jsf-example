package br.gov.go.saude.silt.util.enumerador;

import java.util.List;

import br.gov.go.saude.arquitetura.enumerador.iface.IEnumDominio;
import br.gov.go.saude.arquitetura.util.EnumeradorUtil;

/**
 * @author Átilla Barros
 * @version $Rev: 203 $ $Author: atillabarros $ $Date: 2013-10-02 17:42:34 -0300 (Qua, 02 Out 2013) $
 * @category Enum
 */
public enum TipoEntradaEnum implements IEnumDominio<Long> {

	NOVO_TRATAMENTO(25L, "NOVO TRATAMENTO", "NOVO TRATAMENTO", false), 
	TRANSFERENCIA(26L, "TRANSFERENCIA", "TRANSFERÊNCIA", false), 
	OUTROS_REINGRESSOS(27L, "OUTROS REINGRESSOS", "OUTROS REINGRESSOS", false);

	private Long id;
	private String descricao;
	private String conteudo;
	private boolean excluido;

	private TipoEntradaEnum(Long id, String descricao, String conteudo, boolean excluido) {
		this.id = id;
		this.descricao = descricao;
		this.conteudo = conteudo;
		this.excluido = excluido;
	}

	public List<TipoEntradaEnum> getListaRaioXEnum() {
		return EnumeradorUtil.getDominiosAtivos(TipoEntradaEnum.class);
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
