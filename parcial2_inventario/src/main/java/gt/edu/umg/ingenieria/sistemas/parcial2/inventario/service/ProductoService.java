package gt.edu.umg.ingenieria.sistemas.parcial2.inventario.service;

import gt.edu.umg.ingenieria.sistemas.core.parcial2.core.model.ProductoEntity;
import gt.edu.umg.ingenieria.sistemas.parcial2.inventario.dao.ProductoRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    public List<ProductoEntity> buscarTodos() {
    List<ProductoEntity> list = (List<ProductoEntity>) this.productoRepository.findAll();
        list.sort(Comparator.comparing(ProductoEntity::getName));
        return list;
    }
    public ProductoEntity registrarProducto(ProductoEntity entity) {
        entity.setName(
                entity.getName().substring(0, 1).toUpperCase() + entity.getName().substring(1).toLowerCase()
        );
        return productoRepository.save(entity);
    }
    public String incrementarStock(long id, int amount) {
        ProductoEntity entity = productoRepository.findById(id).get();
        entity.setStock(entity.getStock() + amount);
        return "Stock actualizado: [" + entity.getName() + " " + entity.getStock() + "]";
    }
    public String quitarStock(long id, int amount) {
        ProductoEntity entity = productoRepository.findById(id).get();
        entity.setStock(entity.getStock() - amount);
        return "Stock actualizado: [" + entity.getName() + " " + entity.getStock() + "]";
    }
}
