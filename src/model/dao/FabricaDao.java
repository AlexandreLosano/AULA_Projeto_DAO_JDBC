package model.dao;

import model.dao.impl.VendedorDaoImlJDBC;

public class FabricaDao {

	public static VendedorDao criarVendedorDao() {
		return new VendedorDaoImlJDBC();
	}
	
}
