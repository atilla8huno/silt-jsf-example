package br.gov.go.saude.silt.corp.log_pessoa.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.go.saude.silt.corp.pessoa.entidade.Pessoa;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Classe de entidade LogPessoa.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 247 $ $Author: atillabarros $ $Date: 2013-11-25 11:15:43 -0200 (Seg, 25 Nov 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "log_pessoa", schema = "corp")
public class LogPessoa extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "logp_id", nullable = false)
	private Long id;

	@Column(name = "logp_data")
	private Date data;

	@ManyToOne
	@JoinColumn(name = "logp_pess_iden")
	private Pessoa pessoa;

	@Column(name = "logp_responsavel_id")
	private Long codigoResponsavel;

	@Column(name = "logp_tipo_log")
	private Integer tipoLog;

	public LogPessoa() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Long getCodigoResponsavel() {
		return codigoResponsavel;
	}

	public void setCodigoResponsavel(Long codigoResponsavel) {
		this.codigoResponsavel = codigoResponsavel;
	}

	public Integer getTipoLog() {
		return tipoLog;
	}

	public void setTipoLog(Integer tipoLog) {
		this.tipoLog = tipoLog;
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
		LogPessoa other = (LogPessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}