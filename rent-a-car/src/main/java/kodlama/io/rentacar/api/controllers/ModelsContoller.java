package kodlama.io.rentacar.api.controllers;

import kodlama.io.rentacar.business.abstracts.ModelServices;
import kodlama.io.rentacar.business.dto.requests.create.CreateModelRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateModelRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateModelResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllModelsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetModelResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateModelResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Models")
public class ModelsContoller {
    private final ModelServices services;

    public ModelsContoller(ModelServices service) {
        this.services = service;
    }
    @GetMapping("/getAll")
    public List<GetAllModelsResponse> findAll(){
        return services.getAll();
    }

    @GetMapping("/getId/{id}")
    public GetModelResponse getById(@PathVariable int id) {
        return services.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateModelResponse add(@RequestBody CreateModelRequest request) {
        return services.add(request);
    }

    @PutMapping("/update/{id}")
    public UpdateModelResponse update(@PathVariable int id, @RequestBody UpdateModelRequest request) {
        return services.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        services.delete(id);
    }
}
