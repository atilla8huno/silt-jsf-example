package br.gov.go.saude.silt.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import br.gov.go.saude.arquitetura.util.AplicacaoUtil;
import br.gov.go.saude.arquitetura.util.AutenticacaoUtil;
import br.gov.go.saude.silt.util.enumerador.PermissaoEnum;
import br.gov.go.saude.silt.util.exception.PermissaoException;

/**
 * @author �tilla Barros
 * @version $Rev: 191 $ $Author: claudiocosta $ $Date: 2013-09-24 15:37:44 -0300 (Ter, 24 Set 2013) $
 * @category Aspect
 */
@Aspect
public class PermissaoAspect {

	/**
	 * Este m�todo verifica as permiss�es do usu�rio de acordo com permiss�o recebida por argumento.
	 * 
	 * @param PermissaoEnum permissao
	 * @throws PermissaoException
	 */
	@Before(value = "execution(@br.gov.go.saude.silt.util.Permissao * *(..)) && @annotation(permissao)", argNames = "permissao")
	public void verificarPermissoesDoUsuario(JoinPoint point, Permissao permissao) throws PermissaoException {
		//Verifica se nao esta sendo realizado uma rotina de teste unitario
		if (AplicacaoUtil.getRequest() != null) {
			/* Verifica se o usu�rio logado possui permiss�o para inclus�o */
			if (PermissaoEnum.INCLUIR.equals(permissao.permissao())) {
				verificarPermissaoInclusao();
			}

			/* Verifica se o usu�rio logado possui permiss�o para consulta */
			else if (PermissaoEnum.CONSULTAR.equals(permissao.permissao())) {
				verificarPermissaoConsulta();
			}

			/* Verifica se o usu�rio logado possui permiss�o para altera��o */
			else if (PermissaoEnum.ALTERAR.equals(permissao.permissao())) {
				verificarPermissaoAlteracao();
			}

			/* Verifica se o usu�rio logado possui permiss�o para exclus�o */
			else if (PermissaoEnum.EXCLUIR.equals(permissao.permissao())) {
				verificarPermissaoExclusao();
			}
		}
	}

	/**
	 * Verifica se o usu�rio logado tem permiss�o de consulta
	 * 
	 * @throws PermissaoException
	 */
	private void verificarPermissaoConsulta() throws PermissaoException {
		if (!AutenticacaoUtil.getPermissaoConsulta()) {
			throw new PermissaoException(Mensagem.get(Mensagem.MSG_PERMISSAO_PESQUISAR));
		}
	}

	/**
	 * Verifica se o usu�rio logado tem permiss�o de inclus�o
	 * 
	 * @throws PermissaoException
	 */
	private void verificarPermissaoInclusao() throws PermissaoException {
		if (!AutenticacaoUtil.getPermissaoInclusao()) {
			throw new PermissaoException(Mensagem.get(Mensagem.MSG_PERMISSAO_SALVAR));
		}
	}

	/**
	 * Verifica s o usu�rio logado tem permiss�o de altera��o
	 * 
	 * @throws PermissaoException
	 */
	private void verificarPermissaoAlteracao() throws PermissaoException {
		if (!AutenticacaoUtil.getPermissaoAlteracao()) {
			throw new PermissaoException(Mensagem.get(Mensagem.MSG_PERMISSAO_ALTERAR));
		}
	}

	/**
	 * Verifica s o usu�rio logado tem permiss�o de exclus�o
	 * 
	 * @throws PermissaoException
	 */
	private void verificarPermissaoExclusao() throws PermissaoException {
		if (!AutenticacaoUtil.getPermissaoExclusao()) {
			throw new PermissaoException(Mensagem.get(Mensagem.MSG_PERMISSAO_EXCLUIR));
		}
	}
}
