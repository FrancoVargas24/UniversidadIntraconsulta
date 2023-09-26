package ar.unlam.materia;

public class Nota {

	private Integer valor;
	
	public Nota () {
		this.valor=0;
	}

	public Integer getValor() {
		// TODO Auto-generated method stub
		return this.valor;
	}

	public void asignarValor(Integer valor) {
	
		if(valor >=1 && valor<=10)
		     this.valor=valor;
	}
	

	

}
