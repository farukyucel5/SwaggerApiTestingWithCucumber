package pojos.Pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagsInnerBody {
    /*
    {
      "id": 0,
      "name": "string"
    }
     */

    private int id;
    private String name;
}
