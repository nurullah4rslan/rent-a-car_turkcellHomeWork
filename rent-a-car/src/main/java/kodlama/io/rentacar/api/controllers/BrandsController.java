package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.business.abstracts.BrandServices;
import kodlama.io.rentacar.business.dto.requests.create.CreateBrandRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateBrandRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateBrandResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllBrandsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetBrandResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateBrandResponse;
import kodlama.io.rentacar.entities.Brand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
private final BrandServices services;

    public BrandsController(BrandServices service) {
        this.services = service;
    }
    @GetMapping("/getAll")
    public List<GetAllBrandsResponse> findAll(){
        return services.getAll();
    }

    @GetMapping("/getId/{id}")
    public GetBrandResponse getById(@PathVariable int id) {
        return services.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse add(@RequestBody CreateBrandRequest request) {
        return services.add(request);
    }

    @PutMapping("/update/{id}")
    public UpdateBrandResponse update(@PathVariable int id, @RequestBody UpdateBrandRequest request) {
        return services.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        services.delete(id);
    }
}
