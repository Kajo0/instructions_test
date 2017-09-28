package instructions.instructions.data;

import lombok.AllArgsConstructor;
import lombok.Builder;

@lombok.Data
@Builder
@AllArgsConstructor(staticName = "of")
public class Data {

    private String code;

}
