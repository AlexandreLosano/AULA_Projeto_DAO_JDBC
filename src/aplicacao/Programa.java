package aplicacao;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import modelo.entidade.Departamento;
import modelo.entidade.Vendedor;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
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
		
		System.out.println("\n==== teste 4: insert");
		Vendedor novoVendedor = new Vendedor(null, "Greg", "greg@gamil", new Date(), 4000.00, dep1);
		vendedorDAo.insert(novoVendedor);
		System.out.println("Feito, novo ID: " + novoVendedor.getId());
		
		System.out.println("\n==== teste 8: UpDate");
		vend = vendedorDAo.findById(1);
		vend.setNome("Martha Waine");
		vendedorDAo.update(vend);
		System.out.println("Feito, atualizacao");
		
		System.out.println("\n==== teste 8: DELETE");
		System.out.println("Entre com o ID");
		int id = sc.nextInt();
		vendedorDAo.deleteById(id);
		System.out.println("Feio!!!");
		
		sc.close();
	}

}
