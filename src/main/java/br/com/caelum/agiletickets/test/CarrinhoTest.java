package br.com.caelum.agiletickets.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.agiletickets.models.Carrinho;
import br.com.caelum.agiletickets.models.Item;

public class CarrinhoTest {
	
	
	@Test
	public void deveDevolverValorDeUmItem(){
		Carrinho c = new Carrinho(new Item(10.0));
		
		Double v = c.getMaiorValor();
		
		Assert.assertEquals(10.0, v, 0.001);
		
	}
	
	@Test
	public void deveDevolverValorComMaisDeUmItem(){
		Carrinho c = new Carrinho(new Item(5.0));
		
		c.adiciona(new Item(20.0));
		
		Double v = c.getMaiorValor();
		
		Assert.assertEquals(20.0, v, 0.001);
		
	}

}
