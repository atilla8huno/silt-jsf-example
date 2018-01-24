package br.gov.go.saude.silt.caso_notificacao.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.gov.go.saude.arquitetura.util.EnumeradorUtil;
import br.gov.go.saude.silt.condicao_risco.entidade.CondicaoRisco;
import br.gov.go.saude.silt.dose.entidade.Dose;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.historico_caso_notificacao.entidade.HistoricoCasoNotificacao;
import br.gov.go.saude.silt.historico_transferencia.entidade.HistoricoTransferencia;
import br.gov.go.saude.silt.indicacao_tratamento.entidade.IndicacaoTratamento;
import br.gov.go.saude.silt.individuo.entidade.Individuo;
import br.gov.go.saude.silt.situacao.entidade.Situacao;
import br.gov.go.saude.silt.solicitacao_transferencia.entidade.SolicitacaoTransferencia;
import br.gov.go.saude.silt.tipo_tratamento.entidade.TipoTratamento;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.util.enumerador.BaciloCgEnum;
import br.gov.go.saude.silt.util.enumerador.ExameEnum;
import br.gov.go.saude.silt.util.enumerador.QuantidadeDoseEnum;
import br.gov.go.saude.silt.util.enumerador.RaioXEnum;
import br.gov.go.saude.silt.util.enumerador.SimNaoEnum;
import br.gov.go.saude.silt.util.enumerador.StatusEnum;
import br.gov.go.saude.silt.util.enumerador.TipoDrogaEnum;
import br.gov.go.saude.silt.util.enumerador.TipoEntradaEnum;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "caso_notificacao", schema = "silt")
@SuppressWarnings("all")
public class CasoNotificacao extends Entidade {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cnot_id")
	private Long id;

	@Transient
	private ExameEnum baciloNegativa;

	@Column(name = "cnot_dsil_id_bacilo_negativa")
	private Long idBaciloNegativa;

	@Transient
	private TipoEntradaEnum tipoEntrada;

	@Column(name = "cnot_dsil_id_tipo_entrada")
	private Long idTipoEntrada;

	@Transient
	private BaciloCgEnum baciloCg;

	@Column(name = "cnot_dsil_id_bacilo_cg")
	private Long idBaciloCg;

	@Column(name = "cnot_codigo_sinan_contato")
	private Integer codigoSinanContato;

	@Column(name = "cnot_contato_indice")
	private boolean contatoIndice;

	@Transient
	private SimNaoEnum conversaoTuberculinicaRecente;

	@Column(name = "cnot_dsil_id_conversao_tuberculinica_recente")
	private Long idConversaoTuberculinicaRecente;

	@Column(name = "cnot_crm_medico_responsavel")
	private String crmMedicoResponsavel;

	@Transient
	private ExameEnum culturaNegativa;

	@Column(name = "cnot_dsil_id_cultura_negativa")
	private Long idCulturaNegativa;

	@Temporal(TemporalType.DATE)
	@Column(name = "cnot_data_cadastro")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name = "cnot_data_inicio_tratamento", nullable = false)
	private Date dataInicioTratamento;

	@Temporal(TemporalType.DATE)
	@Column(name = "cnot_data_fim_tratamento")
	private Date dataFimTratamento;

	@Temporal(TemporalType.DATE)
	@Column(name = "cnot_data_notificacao", nullable = false)
	private Date dataNotificacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "cnot_data_teste_tuberculinico")
	private Date dataTesteTuberculinico;

	@Column(name = "cnot_febre")
	private Boolean febre;

	@Column(name = "cnot_nome_contato")
	private String nomeContato;

	@Column(name = "cnot_nome_medico_responsavel")
	private String nomeMedicoResponsavel;

	@Column(name = "cnot_observacao")
	private String observacao;

	@Column(name = "cnot_quantidade_dose", nullable = false)
	private Integer quantidadeDose;

	@Transient
	private QuantidadeDoseEnum quantidadeDoseEnum;

	@Transient
	private RaioXEnum raioXTorax;

	@Column(name = "cnot_dsil_id_raio_x_torax", nullable = false)
	private Long idRaioXTorax;

	@Column(name = "cnot_resultado_teste_tuberculinico")
	private String resultadoTesteTuberculinico;

	@Transient
	private StatusEnum status;

	@Column(name = "cnot_dsil_id_status", nullable = false)
	private Long idStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "cnot_tipo_droga", nullable = false)
	private TipoDrogaEnum tipoDroga;

	@Column(name = "cnot_tosse")
	private Boolean tosse;

	@Column(name = "cnot_numero_caso_notificacao")
	private String numeroCasoNotificacao;

	@Column(name = "cnot_tratamento_previo_tb")
	private Boolean tratamentoPrevioTb;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cnot_esta_id", nullable = false)
	private Estabelecimento estabelecimento;

	@ManyToOne
	@JoinColumn(name = "cnot_indi_id", nullable = false, insertable = true, updatable = true)
	private Individuo individuo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cnot_situ_id", nullable = false)
	private Situacao situacao;

	@ManyToOne
	@JoinColumn(name = "cnot_ttra_id", nullable = false)
	private TipoTratamento tipoTratamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cnot_uest_id", nullable = false)
	private UsuarioEstabelecimento usuarioEstabelecimento;

	@Column(name = "cnot_excluido", nullable = false)
	private boolean excluido;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinTable(name = "caso_notificacao_condicao_risco", schema = "silt", 
		joinColumns = {
			@JoinColumn(name = "cncr_cnot_id")
		}, inverseJoinColumns = {
			@JoinColumn(name = "cncr_cris_id")
		})
	private List<CondicaoRisco> condicoesRiscos;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinTable(name = "caso_notificacao_indicacao_tratamento", schema = "silt",
		joinColumns = {
			@JoinColumn(name = "cnit_cnot_id")
		}, inverseJoinColumns = {
			@JoinColumn(name = "cnit_itra_id")
		})
	private List<IndicacaoTratamento> indicacoesTratamentos;

	@OneToMany(mappedBy = "casoNotificacao", fetch = FetchType.LAZY)
	private List<Dose> doses;

	@OneToMany(mappedBy = "casoNotificacao")
	private List<HistoricoCasoNotificacao> historicoCasoNotificacoes;

	@OneToMany(mappedBy = "casoNotificacao")
	private List<HistoricoTransferencia> historicoTransferencias;

	@OneToMany(mappedBy = "casoNotificacao")
	private List<SolicitacaoTransferencia> solicitacaoTransferencias;

	public CasoNotificacao() { }

	public boolean isAtivo() {
		return getStatus().equals(StatusEnum.ATIVO);
	}

	public boolean isEncerrado() {
		return getStatus().equals(StatusEnum.ENCERRADO);
	}

	private Long getIdBaciloNegativa() {
		idBaciloNegativa = getBaciloNegativa().getId();
		return idBaciloNegativa;
	}

	private void setIdBaciloNegativa(Long idBaciloNegativa) {
		this.idBaciloNegativa = idBaciloNegativa;
		baciloNegativa = EnumeradorUtil.getConstante(ExameEnum.class, idBaciloNegativa);
	}

	public TipoEntradaEnum getTipoEntrada() {
		if (tipoEntrada == null && idTipoEntrada != null) {
			tipoEntrada = EnumeradorUtil.getConstante(TipoEntradaEnum.class, idTipoEntrada);
		}
		return tipoEntrada;
	}

	public void setTipoEntrada(TipoEntradaEnum tipoEntrada) {
		this.tipoEntrada = tipoEntrada;
		idTipoEntrada = tipoEntrada.getId();
	}

	private Long getIdTipoEntrada() {
		if (tipoEntrada != null) {
			idTipoEntrada = getBaciloNegativa().getId();
		}
		return idBaciloNegativa;
	}

	private void setIdTipoEntrada(Long idTipoEntrada) {
		this.idTipoEntrada = idTipoEntrada;
		tipoEntrada = EnumeradorUtil.getConstante(TipoEntradaEnum.class, idTipoEntrada);
	}

	private Long getIdBaciloCg() {
		idBaciloCg = getBaciloCg().getId();
		return idBaciloCg;
	}

	private void setIdBaciloCg(Long idBaciloCg) {
		this.idBaciloCg = idBaciloCg;
		baciloCg = EnumeradorUtil.getConstante(BaciloCgEnum.class, idBaciloCg);
	}

	private Long getIdConversaoTuberculinicaRecente() {
		idConversaoTuberculinicaRecente = getConversaoTuberculinicaRecente().getId();
		return idConversaoTuberculinicaRecente;
	}

	private void setIdConversaoTuberculinicaRecente(Long idConversaoTuberculinicaRecente) {
		this.idConversaoTuberculinicaRecente = idConversaoTuberculinicaRecente;
		conversaoTuberculinicaRecente = EnumeradorUtil.getConstante(SimNaoEnum.class, idConversaoTuberculinicaRecente);
	}

	private Long getIdRaioXTorax() {
		idRaioXTorax = getRaioXTorax().getId();
		return idRaioXTorax;
	}

	private void setIdRaioXTorax(Long idRaioXTorax) {
		this.idRaioXTorax = idRaioXTorax;
		raioXTorax = EnumeradorUtil.getConstante(RaioXEnum.class, idRaioXTorax);
	}

	private Long getIdStatus() {
		idStatus = getStatus().getId();
		return idStatus;
	}

	private void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
		status = EnumeradorUtil.getConstante(StatusEnum.class, idStatus);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estabelecimento getEstabelecimento() {
		if (estabelecimento == null) {
			estabelecimento = new Estabelecimento();
		}
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Individuo getIndividuo() {
		if (individuo == null) {
			individuo = new Individuo();
		}
		return individuo;
	}

	public void setIndividuo(Individuo individuo) {
		this.individuo = individuo;
	}

	public Situacao getSituacao() {
		return this.situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public TipoTratamento getTipoTratamento() {
		return this.tipoTratamento;
	}

	public void setTipoTratamento(TipoTratamento tipoTratamento) {
		this.tipoTratamento = tipoTratamento;
	}

	public UsuarioEstabelecimento getUsuarioEstabelecimento() {
		return this.usuarioEstabelecimento;
	}

	public void setUsuarioEstabelecimento(UsuarioEstabelecimento usuarioEstabelecimento) {
		this.usuarioEstabelecimento = usuarioEstabelecimento;
	}

	public List<CondicaoRisco> getCondicoesRiscos() {
		if (condicoesRiscos == null) {
			condicoesRiscos = new ArrayList<CondicaoRisco>();
		}
		return condicoesRiscos;
	}

	public void setCondicoesRiscos(List<CondicaoRisco> condicoesRiscos) {
		this.condicoesRiscos = condicoesRiscos;
	}

	public List<IndicacaoTratamento> getIndicacoesTratamentos() {
		if (indicacoesTratamentos == null) {
			indicacoesTratamentos = new ArrayList<IndicacaoTratamento>();
		}
		return indicacoesTratamentos;
	}

	public void setIndicacoesTratamentos(List<IndicacaoTratamento> indicacoesTratamentos) {
		this.indicacoesTratamentos = indicacoesTratamentos;
	}

	public List<Dose> getDoses() {
		return this.doses;
	}

	public void setDoses(List<Dose> doses) {
		this.doses = doses;
	}

	public List<HistoricoCasoNotificacao> getHistoricoCasoNotificacoes() {
		return historicoCasoNotificacoes;
	}

	public void setHistoricoCasoNotificacoes(List<HistoricoCasoNotificacao> historicoCasoNotificacoes) {
		this.historicoCasoNotificacoes = historicoCasoNotificacoes;
	}

	public List<HistoricoTransferencia> getHistoricoTransferencias() {
		return this.historicoTransferencias;
	}

	public void setHistoricoTransferencias(List<HistoricoTransferencia> historicoTransferencias) {
		this.historicoTransferencias = historicoTransferencias;
	}

	public List<SolicitacaoTransferencia> getSolicitacaoTransferencias() {
		return this.solicitacaoTransferencias;
	}

	public void setSolicitacaoTransferencias(List<SolicitacaoTransferencia> solicitacaoTransferencias) {
		this.solicitacaoTransferencias = solicitacaoTransferencias;
	}

	public BaciloCgEnum getBaciloCg() {
		if (baciloCg == null && idBaciloCg != null) {
			baciloCg = EnumeradorUtil.getConstante(BaciloCgEnum.class, idBaciloCg);
		}
		return baciloCg;
	}

	public void setBaciloCg(BaciloCgEnum baciloCg) {
		this.baciloCg = baciloCg;
		idBaciloCg = baciloCg.getId();
	}

	public Integer getCodigoSinanContato() {
		return codigoSinanContato;
	}

	public void setCodigoSinanContato(Integer codigoSinanContato) {
		this.codigoSinanContato = codigoSinanContato;
	}

	public String getCrmMedicoResponsavel() {
		return crmMedicoResponsavel;
	}

	public void setCrmMedicoResponsavel(String crmMedicoResponsavel) {
		this.crmMedicoResponsavel = crmMedicoResponsavel;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataInicioTratamento() {
		return dataInicioTratamento;
	}

	public void setDataInicioTratamento(Date dataInicioTratamento) {
		this.dataInicioTratamento = dataInicioTratamento;
	}

	public Date getDataNotificacao() {
		return dataNotificacao;
	}

	public void setDataNotificacao(Date dataNotificacao) {
		this.dataNotificacao = dataNotificacao;
	}

	public Date getDataTesteTuberculinico() {
		return dataTesteTuberculinico;
	}

	public void setDataTesteTuberculinico(Date dataTesteTuberculinico) {
		this.dataTesteTuberculinico = dataTesteTuberculinico;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getNomeMedicoResponsavel() {
		return nomeMedicoResponsavel;
	}

	public void setNomeMedicoResponsavel(String nomeMedicoResponsavel) {
		this.nomeMedicoResponsavel = nomeMedicoResponsavel;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getQuantidadeDose() {
		return quantidadeDose;
	}

	public void setQuantidadeDose(Integer quantidadeDose) {
		this.quantidadeDose = quantidadeDose;
		quantidadeDoseEnum = QuantidadeDoseEnum.getConstante(String.valueOf(quantidadeDose));
	}

	public QuantidadeDoseEnum getQuantidadeDoseEnum() {
		if (quantidadeDoseEnum == null && quantidadeDose != null) {
			quantidadeDoseEnum = QuantidadeDoseEnum.getConstante(String.valueOf(quantidadeDose));
		}
		return quantidadeDoseEnum;
	}

	public void setQuantidadeDoseEnum(QuantidadeDoseEnum quantidadeDoseEnum) {
		this.quantidadeDoseEnum = quantidadeDoseEnum;
		quantidadeDose = Integer.valueOf(quantidadeDoseEnum.getValue());
	}

	public String getResultadoTesteTuberculinico() {
		return resultadoTesteTuberculinico;
	}

	public void setResultadoTesteTuberculinico(String resultadoTesteTuberculinico) {
		this.resultadoTesteTuberculinico = resultadoTesteTuberculinico;
	}

	public ExameEnum getBaciloNegativa() {
		if (baciloNegativa == null && idBaciloNegativa != null) {
			baciloNegativa = EnumeradorUtil.getConstante(ExameEnum.class, idBaciloNegativa);
		}
		return baciloNegativa;
	}

	public void setBaciloNegativa(ExameEnum baciloNegativa) {
		this.baciloNegativa = baciloNegativa;
		idBaciloNegativa = baciloNegativa.getId();
	}

	public boolean isContatoIndice() {
		return contatoIndice;
	}

	public void setContatoIndice(boolean contatoIndice) {
		this.contatoIndice = contatoIndice;
	}

	public SimNaoEnum getConversaoTuberculinicaRecente() {
		if (conversaoTuberculinicaRecente == null && idConversaoTuberculinicaRecente != null) {
			conversaoTuberculinicaRecente = EnumeradorUtil.getConstante(SimNaoEnum.class, idConversaoTuberculinicaRecente);
		}
		return conversaoTuberculinicaRecente;
	}

	public void setConversaoTuberculinicaRecente(SimNaoEnum conversaoTuberculinicaRecente) {
		this.conversaoTuberculinicaRecente = conversaoTuberculinicaRecente;
		idConversaoTuberculinicaRecente = conversaoTuberculinicaRecente.getId();
	}

	public ExameEnum getCulturaNegativa() {
		if (culturaNegativa == null && idCulturaNegativa != null) {
			culturaNegativa = EnumeradorUtil.getConstante(ExameEnum.class, idCulturaNegativa);
		}
		return culturaNegativa;
	}

	public void setCulturaNegativa(ExameEnum culturaNegativa) {
		this.culturaNegativa = culturaNegativa;
		idCulturaNegativa = culturaNegativa.getId();
	}

	private Long getIdCulturaNegativa() {
		idCulturaNegativa = getCulturaNegativa().getId();
		return idCulturaNegativa;
	}

	private void setIdCulturaNegativa(Long idCulturaNegativa) {
		this.idCulturaNegativa = idCulturaNegativa;
		culturaNegativa = EnumeradorUtil.getConstante(ExameEnum.class, idCulturaNegativa);
	}

	public Boolean getFebre() {
		return febre;
	}

	public void setFebre(Boolean febre) {
		this.febre = febre;
	}

	public RaioXEnum getRaioXTorax() {
		if (raioXTorax == null && idRaioXTorax != null) {
			raioXTorax = EnumeradorUtil.getConstante(RaioXEnum.class, idRaioXTorax);
		}
		return raioXTorax;
	}

	public void setRaioXTorax(RaioXEnum raioXTorax) {
		this.raioXTorax = raioXTorax;
		idRaioXTorax = raioXTorax.getId();
	}

	public StatusEnum getStatus() {
		if (status == null && idStatus != null) {
			status = EnumeradorUtil.getConstante(StatusEnum.class, idStatus);
		}
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
		this.idStatus = status.getId();
	}

	public Boolean getTosse() {
		return tosse;
	}

	public void setTosse(Boolean tosse) {
		this.tosse = tosse;
	}

	public Boolean getTratamentoPrevioTb() {
		return tratamentoPrevioTb;
	}

	public void setTratamentoPrevioTb(Boolean tratamentoPrevioTb) {
		this.tratamentoPrevioTb = tratamentoPrevioTb;
	}

	public boolean isExcluido() {
		return excluido;
	}

	@Override
	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public String getNumeroCasoNotificacao() {
		return numeroCasoNotificacao;
	}

	public void setNumeroCasoNotificacao(String numeroCasoNotificacao) {
		this.numeroCasoNotificacao = numeroCasoNotificacao;
	}

	public Date getDataFimTratamento() {
		return dataFimTratamento;
	}

	public void setDataFimTratamento(Date dataFimTratamento) {
		this.dataFimTratamento = dataFimTratamento;
	}

	public TipoDrogaEnum getTipoDroga() {
		return tipoDroga;
	}

	public void setTipoDroga(TipoDrogaEnum tipoDroga) {
		this.tipoDroga = tipoDroga;
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
		CasoNotificacao other = (CasoNotificacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}