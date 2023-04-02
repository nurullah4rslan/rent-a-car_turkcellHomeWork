package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.MaintenanceService;
import kodlama.io.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.requests.post.FilterMaintenancePost;
import kodlama.io.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllMaintenanceResponce;
import kodlama.io.rentacar.business.dto.responses.get.GetMaintenanceResponce;
import kodlama.io.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.Maintenance;
import kodlama.io.rentacar.entities.enums.State;
import kodlama.io.rentacar.repository.CarRepository;
import kodlama.io.rentacar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class MaintenanceManager implements MaintenanceService {
    private final CarRepository carRepository;
    private final MaintenanceRepository repository;
    private final ModelMapper mapper;
    @Override
    public List<GetAllMaintenanceResponce> getAll(FilterMaintenancePost post) {
        if(post.isFilter()) {
            List<Car> cars = carRepository.findAll();
            List<GetAllMaintenanceResponce> response = cars
                    .stream().filter((car) -> !car.getState().equals(State.MAINTENANCE))
                    .map(car -> mapper.map(car, GetAllMaintenanceResponce.class))
                    .toList();
            checkIfGetAllSize(response.size());
            return response;
        }else {
            List<Car> cars = carRepository.findAll();
            List<GetAllMaintenanceResponce> response = cars
                    .stream()
                    .map(car -> mapper.map(car, GetAllMaintenanceResponce.class))
                    .toList();
            checkIfGetAllSize(response.size());
            return response;
        }
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {

        checkIfCreateMaintenance(request.getCarId());
        Maintenance maintenance = mapper.map(request,Maintenance.class);
        maintenance.setId(0);
        repository.save(maintenance);
        CreateMaintenanceResponse response = mapper.map(maintenance,CreateMaintenanceResponse.class);
        Car car = carRepository.findById(request.getCarId()).orElseThrow();
        car.setState(State.MAINTENANCE);
        carRepository.save(car);
        return response;
    }

    @Override
    public GetMaintenanceResponce getById(int id) {
        checkIfMaintenanceExits(id);
        Maintenance maintenance = repository.findById(id).orElseThrow();
        GetMaintenanceResponce response = mapper.map(maintenance,GetMaintenanceResponce.class);
        return response;
    }


    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        Maintenance maintenance = mapper.map(request,Maintenance.class);
        Maintenance maintenance1 = repository.findById(id).orElseThrow();
        maintenance.setId(id);
        maintenance.setLocalTime(maintenance1.getLocalTime());
        repository.save(maintenance);
        UpdateMaintenanceResponse response = mapper.map(maintenance,UpdateMaintenanceResponse.class);
        Car car = carRepository.findById(request.getCarId()).orElseThrow();
        car.setState(State.AVAILABLE);
        carRepository.save(car);

        return response;
    }

    //! Business rules
    private void checkIfMaintenanceExits(int id){
        if(!repository.existsById(id))
            throw new RuntimeException("Araba mevcut değil");
    }
    private void checkIfGetAllSize(int size){
        if (size==0)throw new RuntimeException("Araba bulunmamaktadır.");
    }
    private void checkIfCreateMaintenance(int id){
        Car car = carRepository.findById(id).orElseThrow();
        if (car.getState().equals(State.MAINTENANCE)){
            throw  new RuntimeException("Bakımdaki aracı tekrardan bakıma gönderemezsiniz.");
        } else if (car.getState().equals(State.RENTED)) {
            throw  new RuntimeException("Kiralamadaki araç bakıma gönderilemez");
        }
    }
    private void checkIfCarState(State state,State state2){
        if(state.equals(State.MAINTENANCE) && state2.equals(State.MAINTENANCE))throw  new RuntimeException("Bakımdaki ürün bakıma gönderilemez");
        else if(state.equals(State.RENTED) && state2.equals(State.MAINTENANCE))throw new RuntimeException("Kirada olan araba bakıma gidemez.");
    }
}
