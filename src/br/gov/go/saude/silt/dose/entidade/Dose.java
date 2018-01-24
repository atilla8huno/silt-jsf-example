package br.gov.go.saude.silt.dose.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "dose", schema = "silt")
public class Dose extends Entidade {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dose_id")
	private Long id;

	@Column(name = "dose_observacao")
	private String observacao;

	@Column(name = "dose_quantidade", nullable = false)
	private Integer quantidade;

	@Column(name = "dose_excluido", nullable = false)
	private boolean excluido;

	@Column(name = "dose_data_cadastro", nullable = false)
	private Date dataCadastro;

	@Column(name = "dose_data_entrega", nullable = false)
	private Date dataEntrega;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dose_cnot_id", nullable = false)
	private CasoNotificacao casoNotificacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dose_esta_id", nullable = false)
	private Estabelecimento estabelecimento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dose_uest_id", nullable = false)
	private UsuarioEstabelecimento usuarioEstabelecimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public CasoNotificacao getCasoNotificacao() {
		return this.casoNotificacao;
	}

	public void setCasoNotificacao(CasoNotificacao casoNotificacao) {
		this.casoNotificacao = casoNotificacao;
	}

	public Estabelecimento getEstabelecimento() {
		return this.estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public boolean isExcluido() {
		return excluido;
	}

	@Override
	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public UsuarioEstabelecimento getUsuarioEstabelecimento() {
		return usuarioEstabelecimento;
	}

	public void setUsuarioEstabelecimento(UsuarioEstabelecimento usuarioEstabelecimento) {
		this.usuarioEstabelecimento = usuarioEstabelecimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dose other = (Dose) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}