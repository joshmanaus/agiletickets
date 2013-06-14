package br.com.caelum.agiletickets.models;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Weeks;

public enum Periodicidade {
	DIARIA {
		@Override
		public int getIntervalo(LocalDate inicio, LocalDate fim) {
			return Days.daysBetween(inicio, fim ).getDays();
		}
	}, 
	SEMANAL {
		@Override
		public int getIntervalo(LocalDate inicio, LocalDate fim) {
			return Weeks.weeksBetween(inicio, fim ).getWeeks();
		}
	};

	public abstract int getIntervalo(LocalDate inicio, LocalDate fim);
	
	
}
