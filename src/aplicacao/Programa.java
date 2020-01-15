package aplicacao;



import model.dao.FabricaDao;
import model.dao.VendedorDao;

import modelo.entidade.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		VendedorDao vendedorDAo = FabricaDao.criarVendedorDao();
		
		Vendedor vend = vendedorDAo.findById(3);
	
		System.out.println(vend);
	}

}
