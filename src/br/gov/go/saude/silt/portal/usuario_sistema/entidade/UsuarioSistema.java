package br.gov.go.saude.silt.portal.usuario_sistema.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Classe de Entidade UsuarioSistema.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "usuarios_sistema", schema = "portal")
public class UsuarioSistema extends Entidade {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "usua_codg")
	private String login;

	@Temporal(TemporalType.DATE)
	@Column(name = "usua_data_ativacao")
	private Date dataAtivacao;

	@Column(name = "usua_email")
	private String email;

	@Column(name = "usua_info_senha")
	private String infoSenha;

	@Column(name = "usua_nome")
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usua_pess_id")
	private PessoaFisica pessoaFisica;

	@Column(name = "usua_stat")
	private String status;
	
	@Column(name = "usua_id")
	private Long idUsuario;

	public UsuarioSistema() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Date getDataAtivacao() {
		return dataAtivacao;
	}

	public void setDataAtivacao(Date dataAtivacao) {
		this.dataAtivacao = dataAtivacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInfoSenha() {
		return infoSenha;
	}

	public void setInfoSenha(String infoSenha) {
		this.infoSenha = infoSenha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		if (pessoaFisica == null) {
			pessoaFisica = new PessoaFisica();
		}
		this.pessoaFisica = pessoaFisica;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public Long getId() {
		return Long.valueOf(login);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		UsuarioSistema other = (UsuarioSistema) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
}