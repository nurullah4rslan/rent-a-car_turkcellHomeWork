package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.BrandServices;
import kodlama.io.rentacar.entities.Brand;
import kodlama.io.rentacar.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandManager implements BrandServices {
    private final BrandRepository repository;

    public BrandManager(BrandRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Brand> getAll() {
        checkIfGetAllSize(repository.findAll().size());
        return repository.findAll();
    }

    @Override
    public Brand add(Brand brand) {
        return repository.save(brand);
    }

    @Override
    public Brand getById(int id) {
        checkIfBrandExits(id);
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void delete(int id) {
        checkIfBrandExits(id);
        repository.deleteById(id);
    }

    @Override
    public Brand update(int id, Brand brand) {
        brand.setId(id);
        return repository.save(brand);
    }

    //! Business rules
    private void checkIfBrandExits(int id){
        if(!repository.existsById(id))
            throw new RuntimeException("Marka mevcut değil");
    }
    private void checkIfGetAllSize(int size){
        if (size==0)throw new RuntimeException("Marka bulunmamaktadır.");
    }
}
