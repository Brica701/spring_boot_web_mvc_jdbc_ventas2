package org.iesvdm.service;

import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.dto.ClienteDTO;
import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ComercialService {

    @Autowired
    private ComercialDAO comercialDAO;

    @Autowired
    private PedidoDAO pedidoDAO;

    public List<Comercial> listAll() {
        return comercialDAO.getAll();
    }

    public Comercial one(Integer id) {
        Optional<Comercial> optCom = comercialDAO.find(id);
        return optCom.orElse(null);
    }

    public void newComercial(Comercial comercial) {
        comercialDAO.create(comercial);
    }

    public void replaceComercial(Comercial comercial) {
        comercialDAO.update(comercial);
    }

    public void deleteComercial(int id) {
        comercialDAO.delete(id);
    }

    public List<Pedido> getPedidosByComercial(int comercialId) {
        return pedidoDAO.findByComercialId(comercialId);
    }

    public ComercialDTO obtenerEstadisticasPedidos(int comercialId) {
        List<Pedido> pedidos = pedidoDAO.findByComercialId(comercialId);

        int totalPedidos = pedidos.size();
        double mediaPedidos = pedidos.stream()
                .mapToDouble(Pedido::getTotal)
                .average()
                .orElse(0.0);

        return ComercialDTO.builder()
                .totalPedidos(totalPedidos)
                .mediaPedidos(mediaPedidos)
                .build();
    }

    public List<ClienteDTO> getClientesOrdenadosPorCuantia() {
        // Este método debe calcular la cuantía total por cliente, ordenarlos y devolverlos como ClienteDTO
        // Simulación temporal
        return new ArrayList<>(); // Sustituir con la lógica real
    }
}
