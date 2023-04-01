package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.business.abstracts.CarServices;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Cars")
public class CarsController {
    private final CarServices services;

    public CarsController(CarServices service) {
        this.services = service;
    }
    @GetMapping("/getAll")
    public List<GetAllCarsResponse> findAll(){
        return services.getAll();
    }

    @GetMapping("/getId/{id}")
    public GetCarResponse getById(@PathVariable int id) {
        return services.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarResponse add(@RequestBody CreateCarRequest request) {
        return services.add(request);
    }

    @PutMapping("/update/{id}")
    public UpdateCarResponse update(@PathVariable int id, @RequestBody UpdateCarRequest request) {
        return services.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        services.delete(id);
    }
}
