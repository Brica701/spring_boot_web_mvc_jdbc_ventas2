package org.iesvdm.dao;

import java.util.List;
import java.util.Optional;

import org.iesvdm.modelo.Comercial;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@AllArgsConstructor
public class ComercialDAOImpl implements ComercialDAO {

	private JdbcTemplate jdbcTemplate;

	@Override
	public void create(Comercial comercial) {
		String sql = "INSERT INTO comercial (nombre, apellido1, apellido2, comision) VALUES (?, ?, ?, ?)";
		int rows = jdbcTemplate.update(sql, comercial.getNombre(), comercial.getApellido1(), comercial.getApellido2(), comercial.getComision());
		log.info("Insertado Comercial: {} | Filas afectadas: {}", comercial, rows);
	}

	@Override
	public List<Comercial> getAll() {
		List<Comercial> listComercial = jdbcTemplate.query(
				"SELECT * FROM comercial",
				(rs, rowNum) -> new Comercial(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("apellido1"),
						rs.getString("apellido2"),
						rs.getFloat("comision"),
						null // Se deja en null la lista de pedidos por ahora
				)
		);
		log.info("Devueltos {} registros.", listComercial.size());
		return listComercial;
	}

	@Override
	public Optional<Comercial> find(int id) {
		String sql = "SELECT * FROM comercial WHERE id = ?";
		return jdbcTemplate.query(
				sql,
				new Object[]{id},  // Se pasa el ID correctamente
				(rs, rowNum) -> new Comercial(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("apellido1"),
						rs.getString("apellido2"),
						rs.getFloat("comision"),
						null // Se deja en null la lista de pedidos por ahora
				)
		).stream().findFirst();
	}

	@Override
	public void update(Comercial comercial) {
		String sql = "UPDATE comercial SET nombre = ?, apellido1 = ?, apellido2 = ?, comision = ? WHERE id = ?";
		int rows = jdbcTemplate.update(sql, comercial.getNombre(), comercial.getApellido1(), comercial.getApellido2(), comercial.getComision(), comercial.getId());
		log.info("Actualizado Comercial: {} | Filas afectadas: {}", comercial, rows);
	}

	@Override
	public void delete(int id) {  // Cambié de 'long' a 'int'
		String sql = "DELETE FROM comercial WHERE id = ?";
		int rows = jdbcTemplate.update(sql, id);
		log.info("Comercial con id {} eliminado. Filas afectadas: {}", id, rows);
	}
}
