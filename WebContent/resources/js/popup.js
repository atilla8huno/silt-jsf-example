function abrirJanela(pagina, largura, altura) {

	var top = (screen.height / 2) - (altura / 2);
	var left = (screen.width / 2) - (largura / 2);
	var pars = "resizable=yes,scrollbars=yes,status=no,toolbar=no,menubar=no,"
			+ "titlebar=no,location=no,directories=no,top=" + top + ",left="
			+ left + ",width=" + largura + ",height=" + altura;

	var janela = window.open(pagina, 'relat�rio', pars);

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
	nextText : 'Pr�ximo',
	currentText : 'Come�o',
	monthNames : [ 'Janeiro', 'Fevereiro', 'Mar�o', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro' ],
	monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
	dayNames : [ 'Domingo', 'Segunda', 'Ter�a', 'Quarta', 'Quinta', 'Sexta', 'S�bado' ],
	dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'S�b' ],
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
	month : 'M�s',
	week : 'Semana',
	day : 'Dia',
	allDayText : 'Todo Dia'
};