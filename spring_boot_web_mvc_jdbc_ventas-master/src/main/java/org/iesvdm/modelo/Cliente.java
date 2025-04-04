package org.iesvdm.modelo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

//La anotación @Data de lombok proporcionará el código de:
//getters/setters, toString, equals y hashCode
//propio de los objetos POJOS o tipo Beans
@Data
@AllArgsConstructor
public class Cliente {

	private long id;

	@NotNull(message = "{cliente.nombre.notnull}")
	@Size(max = 30, message = "{cliente.nombre.size}")
	private String nombre;

	@NotNull(message = "{cliente.apellido1.notnull}")
	@Size(max = 30, message = "{cliente.apellido1.size}")
	private String apellido1;

	@Size(max = 30, message = "{cliente.apellido2.size}")
	private String apellido2;

	@NotNull(message = "{cliente.ciudad.notnull}")
	@Size(max = 50, message = "{cliente.ciudad.size}")
	private String ciudad;

	@Min(value = 100, message = "{cliente.categoria.min}")
	@Max(value = 1000, message = "{cliente.categoria.max}")
	private int categoria;
}