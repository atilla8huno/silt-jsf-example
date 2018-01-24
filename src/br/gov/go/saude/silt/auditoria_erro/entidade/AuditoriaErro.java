package br.gov.go.saude.silt.auditoria_erro.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa AuditoriaErro
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "auditoria_erro", schema = "silt")
public class AuditoriaErro extends Entidade {

	public static String AUDIT_SEPARATOR = ";";

	public static String MENSAGEM_PERSISTENCIA = "Persistência";
	public static String MENSAGEM_NEGOCIO = "Negócio";
	public static String MENSAGEM_RELATORIO = "Relatório";
	public static String MENSAGEM_GERAL = "Geral";

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "aerr_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "aerr_data_cadastro", nullable = false)
	private Date dataCadastro;

	@Column(name = "aerr_excecao")
	private String excecao;

	@Column(name = "aerr_mensagem", nullable = false)
	private String mensagem;

	@Column(name = "aerr_pagina", nullable = false)
	private String pagina;

	@Column(name = "aerr_codigo", nullable = false)
	private String codigo;

	@ManyToOne
	@JoinColumn(name = "aerr_uest_id")
	private UsuarioEstabelecimento usuarioEstabelecimento;

	public AuditoriaErro() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getExcecao() {
		return excecao;
	}

	public void setExcecao(String excecao) {
		this.excecao = excecao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public UsuarioEstabelecimento getUsuarioEstabelecimento() {
		return usuarioEstabelecimento;
	}

	public void setUsuarioEstabelecimento(UsuarioEstabelecimento usuarioEstabelecimento) {
		this.usuarioEstabelecimento = usuarioEstabelecimento;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
		AuditoriaErro other = (AuditoriaErro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}