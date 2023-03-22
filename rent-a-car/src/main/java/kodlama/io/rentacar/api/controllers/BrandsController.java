package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.business.abstracts.BrandServices;
import kodlama.io.rentacar.entities.concretes.Brand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandsController {
private BrandServices services;

    public BrandsController(BrandServices service) {
        this.services = service;
    }
    @GetMapping("/getAll")
    public List<Brand> findAll(){
        return services.getAll();
    }

    @GetMapping("/getId/{id}")
    public Brand getById(@PathVariable int id) {
        return services.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Brand add(@RequestBody Brand brand) {
        return services.add(brand);
    }

    @PutMapping("/update/{id}")
    public Brand update(@PathVariable int id, @RequestBody Brand brand) {
        return services.update(id, brand);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        services.delete(id);
    }
}
