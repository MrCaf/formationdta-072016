package dta.pizzeria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.Stockage;

@Repository
@Transactional
public class StockageSpringJdbc implements StockageSpring {

	private JdbcTemplate jdbcTemplate;
	private RowMapper<Pizza> rowMapper = (ResultSet resultat, int arg1) -> new Pizza(resultat.getString("reference"), resultat.getString("libelle"), resultat.getDouble("prix"), CategoriePizza.valueOf(resultat.getString("categorie")), resultat.getString("urlImage"));
	
	@Autowired
	public StockageSpringJdbc(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	/*
	@Override
	public Collection<Pizza> findAll() {

		String sql = "SELECT * FROM PIZZA";
		return this.jdbcTemplate.query(sql, new RowMapper<Pizza>() {
			@Override
			public Pizza mapRow(ResultSet resultat, int arg1) throws SQLException {
				Pizza p = new Pizza(resultat.getString("reference"), resultat.getString("libelle"), resultat.getDouble("prix"), CategoriePizza.valueOf(resultat.getString("categorie")), resultat.getString("urlImage"));
				return p;
			}
		});
	}
	*/
	
	@Override	//utilisation d'une lambda
	public Collection<Pizza> findAll() {

		String sql = "SELECT * FROM pizza";
		return this.jdbcTemplate.query(sql, rowMapper);
	
	}

	@Override
	public Pizza find(String code) {
		
		String sql = "SELECT * FROM pizza WHERE pizza.reference = ?";
		return this.jdbcTemplate.queryForObject(sql, rowMapper, code);
		
	}

	@Override
	@Transactional(rollbackFor= Exception.class)
	public void save(Pizza newItem) {
		
		String sql = "INSERT INTO pizza (libelle,reference,prix,urlImage,categorie) VALUES (?,?,?,?,?)";
		this.jdbcTemplate.update(sql, newItem.getNom(), newItem.getCode(), newItem.getPrix(), newItem.getUrlImage(), newItem.getCategorie().toString());
		
	}

	@Override
	public void update(Pizza editItem, String code) {

		String sql = "UPDATE pizza SET libelle = ?, reference = ?, prix = ?, urlImage = ?, categorie = ? WHERE reference = ?";
		this.jdbcTemplate.update(sql, editItem.getNom(), editItem.getCode(), editItem.getPrix(), editItem.getUrlImage(), editItem.getCategorie(), code);
		
	}

	@Override
	public void delete(String ancienCode) {

		String sql = "DELETE FROM pizza WHERE reference = ?";
		this.jdbcTemplate.update(sql, ancienCode);
		
	}

	@Override @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor= Exception.class)
	public void importPizza(List<Pizza> listImport) {
		
		listImport.forEach(p -> save(p));
		
	}
	
	
	
}
