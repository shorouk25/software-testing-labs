public class UserNameValidator {

    public static String validate(String userName) {

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
