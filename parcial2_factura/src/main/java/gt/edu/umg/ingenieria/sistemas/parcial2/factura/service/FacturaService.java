package gt.edu.umg.ingenieria.sistemas.parcial2.factura.service;

import gt.edu.umg.ingenieria.sistemas.core.parcial2.core.model.CabeceraFacturaEntity;
import gt.edu.umg.ingenieria.sistemas.core.parcial2.core.model.DetalleFacturaEntity;
import gt.edu.umg.ingenieria.sistemas.parcial2.factura.dao.CabeceraFacturaRepository;
import gt.edu.umg.ingenieria.sistemas.parcial2.factura.dao.DetalleFacturaRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaService {

    @Autowired
    private CabeceraFacturaRepository cabeceraFacturaRepository;

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    public List<CabeceraFacturaEntity> buscarTodasCabecerasFactura() {
        return (List<CabeceraFacturaEntity>) this.cabeceraFacturaRepository.findAll();
    }

    public List<DetalleFacturaEntity> buscarTodosDetallesFactura() {
        return (List<DetalleFacturaEntity>) this.detalleFacturaRepository.findAll();
    }

    public List<DetalleFacturaEntity> buscarTodosDetallesFactura(Long idCabeceraFactura) {
        return this.detalleFacturaRepository.findByHeader(idCabeceraFactura);
    }

    public CabeceraFacturaEntity crearCabeceraFactura(CabeceraFacturaEntity cabeceraFacturaEntity) {
        String[] words = cabeceraFacturaEntity.getClientName().split(" ");

        for (int i = 0; i < words.length; i++)
        {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }

        cabeceraFacturaEntity.setClientName(String.join(" ", words));
        return cabeceraFacturaRepository.save(cabeceraFacturaEntity);
    }

    public DetalleFacturaEntity crearDetalleFactura(DetalleFacturaEntity detalleFacturaEntity, long header) {
        detalleFacturaEntity.setHeader(header);
        return detalleFacturaRepository.save(detalleFacturaEntity);
    }

    public List<CabeceraFacturaEntity> encotrarCabeceraFacturaNit(String nit, String order){
        List<CabeceraFacturaEntity> cabeceraFacturaEntityList = (List<CabeceraFacturaEntity>) this.cabeceraFacturaRepository.findAll();
        cabeceraFacturaEntityList = cabeceraFacturaEntityList.stream()
                .filter(a -> Objects.equals(a.getNit(), nit))
                .collect(Collectors.toList());
        cabeceraFacturaEntityList.sort(Comparator.comparing(CabeceraFacturaEntity::getNit));
        return cabeceraFacturaEntityList;

    }

}
