package kodlama.io.rentacar.business.abstracts;

import kodlama.io.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.requests.post.FilterMaintenancePost;
import kodlama.io.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import kodlama.io.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import kodlama.io.rentacar.business.dto.responses.get.GetAllMaintenanceResponce;
import kodlama.io.rentacar.business.dto.responses.get.GetMaintenanceResponce;
import kodlama.io.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;

import java.util.List;

public interface MaintenanceService {
    List<GetAllMaintenanceResponce> getAll(FilterMaintenancePost post);
    CreateMaintenanceResponse add(CreateMaintenanceRequest request);
    GetMaintenanceResponce getById(int id);
    UpdateMaintenanceResponse update(int id , UpdateMaintenanceRequest request);
}
