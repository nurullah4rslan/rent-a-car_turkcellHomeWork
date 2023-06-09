package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.CarService;
import kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapper mapper;
    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars = repository.findAll();
        List<GetAllCarsResponse> response = cars
                .stream().filter((car)-> !car.getState().equals(State.MAINTENANCE))
                .map(car -> mapper.map(car,GetAllCarsResponse.class))
                .toList();
        checkIfGetAllSize(response.size());
        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        Car car = mapper.map(request,Car.class);
        car.setId(0);
        repository.save(car);
        CreateCarResponse response = mapper.map(car,CreateCarResponse.class);
        return response;
    }

    @Override
    public GetCarResponse getById(int id) {
        checkIfCarExits(id);
        Car car = repository.findById(id).orElseThrow();
        GetCarResponse response = mapper.map(car,GetCarResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfCarExits(id);
        repository.deleteById(id);
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        Car car2 = repository.findById(id).orElseThrow();
        Car car = mapper.map(request,Car.class);
        checkIfCarState(car2.getState(),request.getState());
        car.setId(id);
        repository.save(car);
        UpdateCarResponse response = mapper.map(car,UpdateCarResponse.class);
        return response;
    }

    //! Business rules
    private void checkIfCarExits(int id){
        if(!repository.existsById(id))
            throw new RuntimeException("Araba mevcut değil");
    }
    private void checkIfGetAllSize(int size){
        if (size==0)throw new RuntimeException("Araba bulunmamaktadır.");
    }
    private void checkIfCarState(State state,State state2){
        if(state.equals(State.MAINTENANCE) && state2.equals(State.MAINTENANCE))throw  new RuntimeException("Bakımdaki ürün bakıma gönderilemez");
        else if(state.equals(State.RENTED) && state2.equals(State.MAINTENANCE))throw new RuntimeException("Kirada olan araba bakıma gidemez.");
    }
}
