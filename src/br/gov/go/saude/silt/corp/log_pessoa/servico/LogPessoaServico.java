package br.gov.go.saude.silt.corp.log_pessoa.servico;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.arquitetura.util.AplicacaoUtil;
import br.gov.go.saude.arquitetura.util.AutenticacaoUtil;
import br.gov.go.saude.corp.enumerador.EnumTipoLog;
import br.gov.go.saude.silt.corp.log_pessoa.entidade.LogPessoa;
import br.gov.go.saude.silt.corp.pessoa.entidade.Pessoa;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de serviço LogPessoa.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class LogPessoaServico extends Servico<LogPessoa> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LogPessoaDAO dao;

	@Override
	protected DAO<LogPessoa> getDAO() {
		return dao;
	}

	/**
	 * Metodo realiza a insercao de logs para o tipos de pessoas e acoes existentes.
	 * 
	 * @param pessoa
	 * @param idUsuarioSistema
	 * @param tipo
	 * @throws ServicoException
	 * @throws DAOException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void incluir(Pessoa pessoa, EnumTipoLog tipo) throws ServicoException {
		try {
			if (pessoa != null && tipo != null) {
				if (AplicacaoUtil.getRequest() != null) {
					LogPessoa log = new LogPessoa();
					log.setCodigoResponsavel(Long.valueOf(AutenticacaoUtil.getAutenticacao().getIdUsuario()));
					log.setData(new Date());
					log.setPessoa(pessoa);
					log.setTipoLog(tipo.getId());
					super.incluir(log);
				}
			}
		} catch (Exception e) {
			throw new ServicoException(e);
		}

	}

	public static LogPessoaServico getInstancia() {
		return (LogPessoaServico) SpringContainer.getInstancia().getBean("logPessoaServico");
	}

}
