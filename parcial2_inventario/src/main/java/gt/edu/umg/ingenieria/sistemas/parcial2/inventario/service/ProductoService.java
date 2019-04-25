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

    public ProductoEntity registrarProducto(ProductoEntity productoEntity) {
        return productoRepository.save(productoEntity);
    }

    public ProductoEntity incrementarStock(long id, int amount) {
        ProductoEntity productoEntity = productoRepository.findById(id).get();
        productoEntity.setStock(productoEntity.getStock() + amount);
        return productoRepository.save(productoEntity);
    }

    public ProductoEntity quitarStock(long id, int amount) {
        ProductoEntity productoEntity = productoRepository.findById(id).get();
        productoEntity.setStock(productoEntity.getStock() - amount);
        return productoRepository.save(productoEntity);
    }
}
