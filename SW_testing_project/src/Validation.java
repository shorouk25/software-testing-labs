import java.util.Set;

public class Validation {
    public static String validateUserId(String userId, Set<String> existingIds) {
        if (userId == null || userId.isEmpty())
            return "ERROR: User ID {" + userId + "} is wrong";
        // user id ends by a digit or a number (whether it is 9 or not is enforced in this condition)//
        if (!userId.matches("^(\\d{9}|\\d{8}[A-Za-z])$"))
            return "ERROR: User ID {" + userId + "} is wrong";
        // check uniqueness
        if(existingIds.contains(userId))
            return "ERROR: User ID {" + userId + "} is wrong";
        // If all validations pass --> return null (no error)
        return null;
    }

    public static String validateUserName(String userName) {

        // Must not be empty
        if (userName == null || userName.isEmpty()) {
            return "ERROR: User Name {" + userName + "} is wrong";
        }

        // Must not start with a space
        if (userName.startsWith(" ")) {
            return "ERROR: User Name {" + userName + "} is wrong";
        }

        // Alphabetic characters + spaces only
        if (!userName.matches("[A-Za-z ]+")) {
            return "ERROR: User Name {" + userName + "} is wrong";
        }

        // If all validations pass --> return null (no error)
        return null;
    }
}
