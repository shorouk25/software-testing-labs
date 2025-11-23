import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Create a set of existing IDs
        Set<String> existingIds = new HashSet<>();
        existingIds.add("123456789");
        existingIds.add("12345678A");
        existingIds.add("987654321");

        // Test different cases
        String[] testIds = {
                null,
                "",
                "123",          // invalid format
                "123456789",    // valid
                "12345678A",    // valid
                "12345678Z",    // invalid (not in existingIds)
                "98765432B"     // invalid format
        };

        for (String id : testIds) {
            String result = Validation.validateUserId(id, existingIds);
            if (result == null) {
                System.out.println(id + " ---> VALID");
            } else {
                System.out.println(id + " ---> " + result);
            }
        }

        String name = "SaRaH   TaReK";
        String error = Validation.validateUserName(name);

        if (error != null) {
            System.out.println(error);
        }
        else {
            System.out.println("{" + name + "} is a valid name");
        }
    }
}
