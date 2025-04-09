package org.iesvdm.controlador;

import org.iesvdm.dto.ClienteDTO;
import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.iesvdm.service.ComercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ComercialController {

    @Autowired
    private ComercialService comercialService;

    @GetMapping("/comercial")
    public String listar(Model model) {
        List<Comercial> listaComercial = comercialService.listAll();
        model.addAttribute("listaComercial", listaComercial);
        return "comercial";  // Corregido: sin la carpeta 'comercial'
    }

    @GetMapping("/comercial/crear")
    public String crear(Model model) {
        model.addAttribute("comercial", new Comercial());
        return "crear-comercial";  // Corregido: sin la carpeta 'comercial'
    }

    @PostMapping("/comercial/crear")
    public RedirectView submitCrear(@ModelAttribute("comercial") Comercial comercial) {
        comercialService.newComercial(comercial);
        return new RedirectView("/comercial");
    }

    @GetMapping("/comercial/editar/{id}")
    public String editar(Model model, @PathVariable Integer id) {
        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);
        return "editar-comercial";  // Corregido: sin la carpeta 'comercial'
    }

    @PostMapping("/comercial/editar/{id}")
    public RedirectView editarSubmit(@ModelAttribute("comercial") Comercial comercial) {
        comercialService.replaceComercial(comercial);
        return new RedirectView("/comercial");
    }

    @PostMapping("/comercial/borrar/{id}")
    public RedirectView submitBorrar(@PathVariable Integer id) {
        comercialService.deleteComercial(id);
        return new RedirectView("/comercial");
    }

    @GetMapping("/comercial/detalles/{id}")
    public String infoComercial(Model model, @PathVariable int id) {
        Comercial comercial = comercialService.one(id);
        model.addAttribute("comercial", comercial);

        List<Pedido> pedidos = comercialService.getPedidosByComercial(id);
        model.addAttribute("pedidos", pedidos);

        ComercialDTO estadisticas = comercialService.obtenerEstadisticasPedidos(id);
        model.addAttribute("estadisticas", estadisticas);

        Pedido pedidoMax = pedidos.stream()
                .max((p1, p2) -> Double.compare(p1.getTotal(), p2.getTotal()))
                .orElse(null);

        Pedido pedidoMin = pedidos.stream()
                .min((p1, p2) -> Double.compare(p1.getTotal(), p2.getTotal()))
                .orElse(null);

        model.addAttribute("pedidoMax", pedidoMax);
        model.addAttribute("pedidoMin", pedidoMin);

        List<ClienteDTO> clientesOrdenados = comercialService.getClientesOrdenadosPorCuantia();
        model.addAttribute("clientesOrdenados", clientesOrdenados);

        return "detalle-comercial";  // Corregido: sin la carpeta 'comercial'
    }
}
