package pojos.PetPost;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PetExpectedBody {
     /*
    {
  "id": 12,
  "category": {
    "id": 445,
    "name": "sword-tring"
  },
  "name": "doggie",
  "photoUrls": [
    "string"
  ],
  "tags": [
    {
      "id": 0,
      "name": "string"
    }
  ],
  "status": "available"
}
     */

    private Long id;

    private Pet_Category category;

    private String name;

    private String[] photoUrls;

    private TagsInnerBody[] tags;

    private String status;
}

