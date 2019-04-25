package gt.edu.umg.ingenieria.sistemas.parcial2.factura.controller;

import gt.edu.umg.ingenieria.sistemas.core.parcial2.core.model.CabeceraFacturaEntity;
import gt.edu.umg.ingenieria.sistemas.parcial2.factura.service.FacturaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cabeceraFactura")
public class CabeceraFacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping("/buscarTodas")
    public List<CabeceraFacturaEntity> buscarTodas() {
        return this.facturaService.buscarTodasCabecerasFactura();
    }

    @PostMapping("/crearFactura")
    public CabeceraFacturaEntity create(@RequestBody CabeceraFacturaEntity entity) {
        return this.facturaService.crearCabeceraFactura(entity);
    }

    @GetMapping("/buscarPorNit")
    public List<CabeceraFacturaEntity> findAllByNit(@RequestParam(name = "nit", required = true) String nit, @RequestParam(name = "order", required = true) String order) {
        return this.facturaService.encotrarCabeceraFacturaNit(nit, order);
    }
}
