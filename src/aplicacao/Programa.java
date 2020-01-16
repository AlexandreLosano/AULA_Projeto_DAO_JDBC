package aplicacao;



import java.util.List;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import modelo.entidade.Departamento;
import modelo.entidade.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		VendedorDao vendedorDAo = FabricaDao.criarVendedorDao();
		
		System.out.println("==== teste 1: vendedor por ID");
		Vendedor vend = vendedorDAo.findById(3);
		System.out.println(vend);
		
		System.out.println("\n==== teste 2: dep por ID");
		Departamento dep1 = new Departamento(2, null);
		List<Vendedor> list = vendedorDAo.findDep(dep1);
		for (Vendedor obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n==== teste 3: Tudo");
		list = vendedorDAo.findAll();
		for (Vendedor obj : list) {
			System.out.println(obj);
		}
	}

}
