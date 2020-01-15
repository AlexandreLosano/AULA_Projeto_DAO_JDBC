package aplicacao;

import java.util.Date;

import modelo.entidade.Departamento;
import modelo.entidade.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		Departamento obj = new Departamento(1, "Books");
		Vendedor vend= new Vendedor(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
		System.out.println(obj);
		System.out.println(vend);
		
	}

}
