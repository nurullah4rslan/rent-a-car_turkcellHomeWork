package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.BrandServices;
import kodlama.io.rentacar.entities.concretes.Brand;
import kodlama.io.rentacar.repository.abstracts.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandManager implements BrandServices {
    BrandRepository repository;

    public BrandManager(BrandRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Brand> getAll() {
        if (repository.getAll().size()==0)throw new RuntimeException("Kayıtlı Marka yok!");
        return repository.getAll();
    }

    @Override
    public Brand add(Brand brand) {
        return repository.add(brand);
    }

    @Override
    public Brand getById(int id) {
        return repository.getById(id);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Brand update(int id, Brand brand) {
        return repository.update(id,brand);
    }
}
