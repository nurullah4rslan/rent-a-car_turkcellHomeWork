package kodlama.io.rentacar.business.dto.responses.update;

import kodlama.io.rentacar.entities.Brand;
import kodlama.io.rentacar.entities.Model;
import kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateModelResponse {
    private int id;
    private String name;
    private int brand_id;
}
