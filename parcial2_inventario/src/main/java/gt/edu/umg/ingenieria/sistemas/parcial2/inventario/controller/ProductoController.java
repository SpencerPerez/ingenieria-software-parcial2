package gt.edu.umg.ingenieria.sistemas.parcial2.inventario.controller;

import gt.edu.umg.ingenieria.sistemas.core.parcial2.core.model.ProductoEntity;
import gt.edu.umg.ingenieria.sistemas.parcial2.inventario.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/buscarTodos")
    public List<ProductoEntity> buscarTodos() {
        return this.productoService.buscarTodos();
    }

    @PostMapping("/registrarProducto")
    public ProductoEntity create(@RequestBody ProductoEntity entity) {
        return this.productoService.registrarProducto(entity);
    }

    @PutMapping("/actualizarStock/{id}/incrementar/{amount}")
    public String updateStockAdd(@PathVariable long id, @PathVariable int amount) {
        return this.productoService.incrementarStock(id, amount);
    }

    @PutMapping("/actualizarStock/{id}/decrementar/{amount}")
    public String updateStockSub(@PathVariable long id, @PathVariable int amount) {
        return this.productoService.quitarStock(id, amount);
    }

}
