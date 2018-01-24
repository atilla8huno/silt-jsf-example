package br.gov.go.saude.silt.auditoria_erro.servico;

import java.util.Calendar;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.auditoria_erro.entidade.AuditoriaErro;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.usuario_estabelecimento.servico.UsuarioEstabelecimentoServico;
import br.gov.go.saude.silt.util.AuditLevel;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service("auditoriaErroServico")
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class AuditoriaErroServico extends Servico<AuditoriaErro> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AuditoriaErroDAO dao;
	@Autowired
	private UsuarioEstabelecimentoServico usuarioEstabelecimentoServico;
	
	private static Logger log = Logger.getRootLogger();
	
	@Override
	protected DAO<AuditoriaErro> getDAO() {
		return dao;
	}

	/**
	 * Este método recebe o código, mensagem e descrição da exceção lançada (Exception), e registra a auditoria no banco de dados.
	 * 
	 * @param	String codigo
	 * @param	String excecao
	 * @param	String mensagem
	 */
	public void audit(String codigo, String mensagem, String excecao) {
		try {
			UsuarioEstabelecimento usuario = usuarioEstabelecimentoServico.consultarPorUsuarioSistema(getUsuarioSessao());
			String pagina = FacesContext.getCurrentInstance() != null ? FacesContext.getCurrentInstance().getViewRoot().getViewId() : "caso_teste";
			AuditoriaErro auditoria = new AuditoriaErro();
			auditoria.setCodigo(codigo);
			auditoria.setUsuarioEstabelecimento(usuario);
			auditoria.setDataCadastro(Calendar.getInstance().getTime());
			auditoria.setMensagem(mensagem);
			auditoria.setPagina(pagina);
			auditoria.setExcecao(excecao);

			StringBuilder auditMessage = new StringBuilder();

			auditMessage.append(usuario.getId()).append(AuditoriaErro.AUDIT_SEPARATOR);
	        auditMessage.append(pagina).append(AuditoriaErro.AUDIT_SEPARATOR);
	        auditMessage.append(codigo).append(AuditoriaErro.AUDIT_SEPARATOR);
	        auditMessage.append(mensagem);

			dao.salvarOuAtualizar(auditoria);
			log.log(AuditLevel.AUDIT, auditMessage.toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}
	
	public static AuditoriaErroServico getInstancia() {
		return (AuditoriaErroServico) SpringContainer.getInstancia().getBean("auditoriaErroServico");
	}
}
