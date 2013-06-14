package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EspetaculoTest {

	private Espetaculo espetaculo;

	@Before
	public void inicializa() {
		espetaculo = new Espetaculo();
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {

		espetaculo.getSessoes().add(sessaoComIngressosSobrando(1));
		espetaculo.getSessoes().add(sessaoComIngressosSobrando(3));
		espetaculo.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(espetaculo.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {

		espetaculo.getSessoes().add(sessaoComIngressosSobrando(1));
		espetaculo.getSessoes().add(sessaoComIngressosSobrando(3));
		espetaculo.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(espetaculo.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {

		espetaculo.getSessoes().add(sessaoComIngressosSobrando(1));
		espetaculo.getSessoes().add(sessaoComIngressosSobrando(3));
		espetaculo.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(espetaculo.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {

		espetaculo.getSessoes().add(sessaoComIngressosSobrando(3));
		espetaculo.getSessoes().add(sessaoComIngressosSobrando(3));
		espetaculo.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(espetaculo.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {

		espetaculo.getSessoes().add(sessaoComIngressosSobrando(3));
		espetaculo.getSessoes().add(sessaoComIngressosSobrando(3));
		espetaculo.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(espetaculo.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {

		espetaculo.getSessoes().add(sessaoComIngressosSobrando(2));
		espetaculo.getSessoes().add(sessaoComIngressosSobrando(2));
		espetaculo.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(espetaculo.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}

	@Test
	public void criar_Espetaculo_No_Periodo_De_Um_Dia_Com_Uma_Sessao_Diaria() {

		espetaculo.criaSessoes(new DateTime().toLocalDate(), new DateTime()
				.toLocalDate().plusDays(1), new DateTime().toLocalTime(),
				Periodicidade.DIARIA);

		Assert.assertEquals(1, espetaculo.getSessoes().size());

	}

	@Test
	public void criar_Espetaculo_Com_Duas_Sessoes_Diarias() {
		espetaculo.criaSessoes(new DateTime().toLocalDate(), new DateTime()
				.toLocalDate().plusDays(2), new DateTime().toLocalTime(),
				Periodicidade.DIARIA);

		Assert.assertEquals(2, espetaculo.getSessoes().size());
	}

	@Test
	public void criar_Espetaculo_No_Periodo_De_Uma_Semana_Com_Uma_Sessao_Semanal()
			throws Exception {
		espetaculo.criaSessoes(new DateTime().toLocalDate(), new DateTime()
				.toLocalDate().plusWeeks(1), new DateTime().toLocalTime(),
				Periodicidade.SEMANAL);

		Assert.assertEquals(1, espetaculo.getSessoes().size());
	}

	@Test
	public void criar_Espetaculo_No_Periodo_De_Duas_Semanas_Com_Duas_Sessoes_Semanal()
			throws Exception {
		espetaculo.criaSessoes(new DateTime().toLocalDate(), new DateTime()
				.toLocalDate().plusWeeks(2), new DateTime().toLocalTime(),
				Periodicidade.SEMANAL);

		Assert.assertEquals(2, espetaculo.getSessoes().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_Deve_Aceitar_Data_Fim_Inferior_A_Data_Inicio()
			throws Exception {
		DateTime dateTime = new DateTime();
		DateTime minusDays = dateTime.minusDays(2);
		espetaculo.criaSessoes(dateTime.toLocalDate(), minusDays.toLocalDate(),
				null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_Deve_Aceitar_Data_Fim_Inferior_A_Data_Atual()
			throws Exception {
		DateTime dateTime = new DateTime();
		DateTime minusDays = dateTime.minusDays(2);
		espetaculo.criaSessoes(dateTime.toLocalDate(), minusDays.toLocalDate(),
				null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_Deve_Aceitar_Data_Inicio_Inferior_A_Data_Atual()
			throws Exception {
		DateTime dateTime = new DateTime().minusDays(2);
		DateTime minusDays = new DateTime();
		espetaculo.criaSessoes(dateTime.toLocalDate(), minusDays.toLocalDate(),
				null, null);
	}

	@Test
	public void validar_Conteudo_Sessao_Diaria() throws Exception {

		LocalDate dataInicio = new DateTime().toLocalDate();
		LocalDate dataFim = dataInicio.plusDays(2);
		LocalTime horario = new DateTime().toLocalTime();
		List<Sessao> sessoes = espetaculo.criaSessoes(dataInicio, dataFim, horario,
				Periodicidade.DIARIA);
		Sessao primeira = new Sessao(espetaculo, dataInicio.toDateTime(horario));
		Sessao segunda = new Sessao(espetaculo, dataFim.toDateTime(horario));
		
		Assert.assertThat(sessoes, Matchers.contains(primeira, segunda));
	}

}
