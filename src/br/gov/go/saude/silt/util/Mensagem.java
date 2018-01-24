package br.gov.go.saude.silt.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Properties;

/**
 * @author Átilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Mensagem
 */
public class Mensagem {
	/** GERAL **/
	public static final String MSG_FILTRO_PESQUISA_VAZIO = "negocio.geral.filtroPesquisaVazio";
	public static final String MSG_OBJETO_NULO = "negocio.erroInterno.objetoNulo";
	public static final String MSG_CAMPO_VAZIO = "negocio.geral.campoVazio";
	public static final String MSG_CAMPO_OBRIGATORIO = "campoObrigatorio";
	public static final String MSG_INCLUSAO = "controle.inclusao.sucesso";
	public static final String MSG_EXCLUSAO = "controle.exclusao.sucesso";
	public static final String MSG_ALTERACAO = "controle.alteracao.sucesso";
	public static final String MSG_ERRO_GERAL = "erro.geral";
	public static final String MSG_ERRO_PERSISTENCIA = "erro.persistencia";
	public static final String MSG_ERRO_RELATORIO = "erro.relatorio";
	public static final String MSG_ERRO_CONVERSOR = "erro.conversor";
	public static final String MSG_CPF_INVALIDO = "negocio.geral.cpfInvalido";
	public static final String MSG_CADASTRO_EXISTENTE = "negocio.geral.cadastroExistente";
	public static final String MSG_DATA_NASCIMENTO_INVALIDA = "negocio.geral.dataNascimentoInvalida";
	public static final String MSG_CPF_OBRIGATORIO = "negocio.geral.cpfObrigatorio";
	public static final String MSG_PERMISSAO_SALVAR = "negocio.permissao.salvar";
	public static final String MSG_PERMISSAO_ALTERAR = "negocio.permissao.alterar";
	public static final String MSG_PERMISSAO_EXCLUIR = "negocio.permissao.excluir";
	public static final String MSG_PERMISSAO_PESQUISAR = "negocio.permissao.pesquisar";
	public static final String MSG_ENDERECO_INCLUSAO = "controle.endereco.inclusao.sucesso";
	public static final String MSG_ENDERECO_EXCLUSAO = "controle.endereco.exclusao.sucesso";
	public static final String MSG_CONTATO_INCLUSAO = "controle.contato.inclusao.sucesso";
	public static final String MSG_CONTATO_EXCLUSAO = "controle.contato.exclusao.sucesso";
	public static final String MSG_CADASTRO_INCOMPLETO = "negocio.geral.cadastroIncompleto";
	public static final String MSG_ERRO_AUTENTICACAO = "negocio.geral.erroAutenticacao";
	public static final String MSG_ERRO_LOCALIZAR_USUARIO = "negocio.geral.erroLocalizarUsuario";
	public static final String MSG_NENHUM_REGISTRO = "nenhumRegistro";
	
	/** CASO NOTIFICACAO **/
	public static final String MSG_CASO_NOTIFICACAO_CADASTRO = "negocio.casoNotificacao.cadastro";
	public static final String MSG_CASO_NOTIFICACAO_ALTERACAO = "negocio.casoNotificacao.alteracao";
	public static final String MSG_CASO_NOTIFICACAO_EXCLUSAO = "negocio.casoNotificacao.exclusao";
	public static final String MSG_CASO_NOTIFICACAO_RETORNO = "negocio.casoNotificacao.retorno";
	public static final String MSG_CASO_NOTIFICACAO_ENCERRAMENTO = "negocio.casoNotificacao.encerramento";
	public static final String MSG_CASO_NOTIFICACAO_ERRO_GERAR_NUMERO = "negocio.casoNotificacao.erroGerarNumero";

	private static Properties properties = null;

	public static String get(String alias, Object... args) {
		if (alias == null) {
			return Mensagem.get("erro.geral", String.valueOf(Calendar.getInstance().getTime()));
		}

		if (properties == null) {
			properties = new Properties();

			try {
				properties.load(Mensagem.class.getClassLoader().getResourceAsStream("messages.properties"));
			} catch (IOException e) {
				throw new RuntimeException("Erro na carga do arquivo de mensagens", e);
			}
		}

		try {
			String mensagem = properties.getProperty(alias);

			if (mensagem == null) {
				mensagem = Mensagem.get("erro.geral", alias);
			} else {
				if (args.length > 0) {
					mensagem = MessageFormat.format(mensagem, (Object[]) args);
				}
			}
			return mensagem;
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Erro na formatação da mensagem", e);
		}
	}
}
