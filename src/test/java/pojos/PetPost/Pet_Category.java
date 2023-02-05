package pojos.PetPost;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet_Category {
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
    private int id;
    private String name;


}
