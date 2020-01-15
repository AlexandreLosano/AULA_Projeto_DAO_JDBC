package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import modelo.entidade.Departamento;
import modelo.entidade.Vendedor;

public class VendedorDaoImlJDBC implements VendedorDao{

	private Connection conn;
	
	public VendedorDaoImlJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select * "
					+"from vendedor v "
					+"inner join  departamento dp "
					+"on v.DepartamentoId = dp.DepId "
					+"where v.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Departamento dep = new Departamento();
					dep.setId(rs.getInt("DepId"));
					dep.setName(rs.getString("DepNome"));
				Vendedor vend = new Vendedor();
					vend.setId(rs.getInt("Id"));
					vend.setNome(rs.getString("Nome"));
					vend.setEmail(rs.getString("Email"));
					vend.setSalario(rs.getDouble("Salario"));
					vend.setAniversario(rs.getDate("Aniversario"));
					vend.setDepartamento(dep);
				return vend;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.fecharResultSet(rs);
			DB.desconectar();
		}
	}

	@Override
	public List<Vendedor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
