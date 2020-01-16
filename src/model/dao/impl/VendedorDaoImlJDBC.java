package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"insert into vendedor\r\n" + 
					"(Nome, Email, Aniversario, Salario, DepartamentoId)\r\n" + 
					"Values\r\n" + 
					"(?,?,?,?,?)", 
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getAniversario().getTime()));
			st.setDouble(4, obj.getSalario());
			st.setInt(5, obj.getDepartamento().getId());
			
			int linhasafetadas = st.executeUpdate();
			
			if (linhasafetadas>0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.fecharResultSet(rs);
			}
			else {
				throw new DbException("Nenhuma linha afetada");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.fecharStatement(st);
		}
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
					+"from vendedor v inner join  departamento dp "
					+"on v.DepartamentoId = dp.DepId "
					+"where v.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Departamento dep = chamarDepartamento(rs);
				Vendedor vend = chamarVendedor(rs, dep); 
				return vend;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.fecharResultSet(rs);
			DB.fecharStatement(st);
		}
	}

	private Vendedor chamarVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor vend = new Vendedor();
		vend.setId(rs.getInt("Id"));
		vend.setNome(rs.getString("Nome"));
		vend.setEmail(rs.getString("Email"));
		vend.setSalario(rs.getDouble("Salario"));
		vend.setAniversario(rs.getDate("Aniversario"));
		vend.setDepartamento(dep);
		return vend;
	}

	private Departamento chamarDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
			dep.setId(rs.getInt("DepId"));
			dep.setName(rs.getString("DepNome"));
		return dep;
	}

	@Override
	public List<Vendedor> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select * "
					+"from vendedor v "
					+"inner join  departamento dp on v.DepartamentoId = dp.DepId "
					+"order by nome");
			rs = st.executeQuery();
			
			List<Vendedor> list = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) {
				
				Departamento dep = map.get(rs.getInt("DepId"));
				
				if(dep == null) {
					dep = chamarDepartamento(rs);
					map.put(rs.getInt("DepId"), dep);
				}
				
				Vendedor vend = chamarVendedor(rs, dep); 
				list.add(vend);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.fecharResultSet(rs);
			DB.fecharStatement(st);
		}
	}

	@Override
	public List<Vendedor> findDep(Departamento departameto) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select * "
					+"from vendedor v "
					+"inner join  departamento dp on v.DepartamentoId = dp.DepId "
					+"where dp.DepId = ? "
					+"order by nome");
			st.setInt(1, departameto.getId());
			rs = st.executeQuery();
			
			List<Vendedor> list = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while (rs.next()) {
				
				Departamento dep = map.get(rs.getInt("DepId"));
				
				if(dep == null) {
					dep = chamarDepartamento(rs);
					map.put(rs.getInt("DepId"), dep);
				}
				
				Vendedor vend = chamarVendedor(rs, dep); 
				list.add(vend);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.fecharResultSet(rs);
			DB.fecharStatement(st);
		}
	}
}
