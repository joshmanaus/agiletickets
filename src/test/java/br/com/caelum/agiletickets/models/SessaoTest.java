package br.com.caelum.agiletickets.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SessaoTest {

	Sessao sessao;

	@Before
	public void criarSessao() {
		this.sessao = new Sessao();
	}

	@Test
	public void deve_Vender_Ingresso_Se_Quantidade_Disponivel_Maior_Que_Quantidade_Solicitada()
			throws Exception {

		sessao.setTotalIngressos(2);

		Assert.assertTrue(sessao.podeReservar(1));
	}

	@Test
	public void nao_Deve_Vender_Ingresso_Se_Quantidade_Disponivel_Insuficiente()
			throws Exception {
		sessao.setTotalIngressos(2);

		Assert.assertFalse(sessao.podeReservar(3));
	}

	@Test
	public void reservar_Ingressos_Deve_Diminuir_O_Numero_De_Ingressos_Disponiveis()
			throws Exception {
		sessao.setTotalIngressos(5);

		sessao.reserva(3);
		Assert.assertEquals(2, sessao.getIngressosDisponiveis().intValue());
	}

	@Test
	public void reservar_Quantidade_Igual_Disponivel() {
		sessao.setTotalIngressos(2);

		Assert.assertTrue(sessao.podeReservar(2));

	}
}
