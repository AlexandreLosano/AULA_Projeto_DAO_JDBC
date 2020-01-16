package model.dao;

import java.util.List;

import modelo.entidade.Departamento;
import modelo.entidade.Vendedor;

public interface VendedorDao {

	void insert(Vendedor obj);
	void update(Vendedor obj);
	void deleteById(Integer id);
	Vendedor findById (Integer id);
	List<Vendedor> findAll();
	List<Vendedor> findDep(Departamento departameto);
		
}
