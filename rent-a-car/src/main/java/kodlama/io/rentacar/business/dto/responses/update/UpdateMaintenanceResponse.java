package kodlama.io.rentacar.business.dto.responses.update;

import jakarta.persistence.ManyToOne;
import kodlama.io.rentacar.entities.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMaintenanceResponse {
    private int id;
    private int carId;
    private Date localTime;
    private Date exitTime;
    private String description;
}
