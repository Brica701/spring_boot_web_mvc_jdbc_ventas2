package org.iesvdm.modelo;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comercial {

	private int id;

	@NotNull(message = "{comercial.nombre.notnull}")
	@Size(max = 30, message = "{comercial.nombre.size}")
	private String nombre;

	@NotNull(message = "{comercial.apellido1.notnull}")
	@Size(max = 30, message = "{comercial.apellido1.size}")
	private String apellido1;

	@Size(max = 30, message = "{comercial.apellido2.size}")
	private String apellido2;

	@DecimalMin(value = "0.276", message = "{comercial.comision.min}")
	@DecimalMax(value = "0.946", message = "{comercial.comision.max}")
	private BigDecimal comision;

	private List<Pedido> pedidos;

	public Comercial(int id, String nombre, String apellido1, String apellido2, float comision, Object pedidos) {
	}
}
