package kodlama.io.rentacar.repository.concretes;

import kodlama.io.rentacar.entities.concretes.Brand;
import kodlama.io.rentacar.repository.abstracts.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class InMemoryBrandRepository implements BrandRepository {
    List<Brand> brands;
    @Autowired
    public InMemoryBrandRepository() {
        brands =new ArrayList<>();
        brands.add(new Brand(1,"Mercedes"));
        brands.add(new Brand(2,"BMW"));
        brands.add(new Brand(3,"Audi"));
        brands.add(new Brand(4,"Volvo"));
        brands.add(new Brand(5,"Renault"));
    }

    @Override
    public List<Brand> getAll() {
        return brands;
    }
    @Override
    public Brand getById(int id) {
        for (Brand brand : brands) {
            if(brand.getId()==id){
                return brand;
            }
        }
        return null;
    }

    @Override
    public Brand add(Brand brand) {
        brands.add(brand);
        return brand;
    }

    @Override
    public Brand update(int id, Brand brand) {
        for (Brand brands : brands) {
            if(brands.getId()==id){
                brands.setName(brand.getName());
                return brands;
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        for (Brand brand : brands) {
            if(brand.getId()==id){
                brands.remove(brand);
            }
        }
    }

}
