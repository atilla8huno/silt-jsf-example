function abrirJanela(pagina, largura, altura) {

	var top = (screen.height / 2) - (altura / 2);
	var left = (screen.width / 2) - (largura / 2);
	var pars = "resizable=yes,scrollbars=yes,status=no,toolbar=no,menubar=no,"
			+ "titlebar=no,location=no,directories=no,top=" + top + ",left="
			+ left + ",width=" + largura + ",height=" + altura;

	var janela = window.open(pagina, 'relatório', pars);

	janela.focus();
}

function abrirPopupRelatorio() {
	var altura = 600;
	var largura = 800;

	abrirJanela('VisualizaRelatorio', largura, altura);
}

function fecharPopup() {
	Windows.close('popup');
}

PrimeFaces.locales['pt_BR'] = {
	closeText : 'Fechar',
	prevText : 'Anterior',
	nextText : 'Próximo',
	currentText : 'Começo',
	monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro' ],
	monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
	dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado' ],
	dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb' ],
	dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
	weekHeader : 'Semana',
	firstDay : 1,
	isRTL : false,
	showMonthAfterYear : false,
	yearSuffix : '',
	timeOnlyTitle : 'Apenas Horas',
	timeText : 'Tempo',
	hourText : 'Hora',
	minuteText : 'Minuto',
	secondText : 'Segundo',
	ampm : false,
	month : 'Mês',
	week : 'Semana',
	day : 'Dia',
	allDayText : 'Todo Dia'
};