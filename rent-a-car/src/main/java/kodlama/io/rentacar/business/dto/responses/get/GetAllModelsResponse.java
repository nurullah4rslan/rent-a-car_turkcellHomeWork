package kodlama.io.rentacar.business.dto.responses.get;

import kodlama.io.rentacar.entities.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllModelsResponse {
    private int id;
    private String name;
    private int brandId;
}
