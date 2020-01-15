package model.dao;

import db.DB;
import model.dao.impl.VendedorDaoImlJDBC;

public class FabricaDao {

	public static VendedorDao criarVendedorDao() {
		return new VendedorDaoImlJDBC(DB.conectar());
	}
	
}
