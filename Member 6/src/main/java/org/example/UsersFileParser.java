package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public final class UsersFileParser {

    private UsersFileParser() { /* static utility */ }

    public static List<User> parseUsers(Path file) throws IOException {
        List<User> users = new ArrayList<>();
        Set<Integer> seenIds = new HashSet<>();

        try (BufferedReader br = Files.newBufferedReader(file)) {
            String line1;
            while ((line1 = readNextMeaningfulLine(br)) != null) {
                // parse first line: "name,id"
                int commaIndex = line1.indexOf(',');
                if (commaIndex < 0) {
                    throw new IOException("Invalid user header (expected 'name,id'): \"" + line1 + "\"");
                }

                String name = line1.substring(0, commaIndex).trim();
                String idPart = line1.substring(commaIndex + 1).trim();

                if (name.isEmpty()) {
                    throw new IOException("Empty user name in line: \"" + line1 + "\"");
                }

                int userId;
                try {
                    userId = Integer.parseInt(idPart);
                } catch (NumberFormatException e) {
                    throw new IOException("Invalid user id (not an integer) in line: \"" + line1 + "\"");
                }

                if (seenIds.contains(userId)) {
                    throw new IOException("Duplicate user id found: " + userId);
                }

                // read second line (may be null or empty). An empty or commented second line means no likes.
                String line2 = br.readLine();
                if (line2 == null) {
                    line2 = "";
                } else {
                    line2 = line2.trim();
                    if (line2.isEmpty() || line2.startsWith("#")) {
                        line2 = "";
                    }
                }

                Set<Integer> liked = parseLikedIdsStrict(line2);
                users.add(new User(userId, name, liked));
                seenIds.add(userId);
            }
        }

        return users;
    }

    /**
     * Parse into a map keyed by user id (preserves file order using LinkedHashMap).
     *
     * @param file path to users.txt
     * @return LinkedHashMap of userId -> User
     * @throws IOException on malformed input
     */
    public static Map<Integer, User> parseUsersToMap(Path file) throws IOException {
        List<User> list = parseUsers(file);
        Map<Integer, User> map = new LinkedHashMap<>();
        for (User u : list) {
            map.put(u.getId(), u);
        }
        return map;
    }

    // Helper: read next non-blank, non-comment line or null if EOF
    private static String readNextMeaningfulLine(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            if (line.startsWith("#")) continue;
            return line;
        }
        return null;
    }

    // Strict parsing of liked ids: empty string -> empty set; otherwise each token must be integer
    private static Set<Integer> parseLikedIdsStrict(String line) throws IOException {
        Set<Integer> result = new LinkedHashSet<>();
        if (line == null || line.isEmpty()) return result;

        String[] tokens = line.split(",");
        for (String t : tokens) {
            String tok = t.trim();
            if (tok.isEmpty()) continue; // allow stray commas
            try {
                int mid = Integer.parseInt(tok);
                result.add(mid);
            } catch (NumberFormatException e) {
                throw new IOException("Invalid movie id (not an integer) in liked-movies line: \"" + line + "\"");
            }
        }
        return result;
    }

    // Simple CLI for manual testing:
    public static void main(String[] args) {
        Path p = args.length > 0 ? Paths.get(args[0]) : Paths.get("users.txt");
        try {
            List<User> users = parseUsers(p);
            System.out.println("Parsed " + users.size() + " user(s):");
            for (User u : users) {
                System.out.println(u);
            }
        } catch (IOException e) {
            System.err.println("Failed to parse users file: " + e.getMessage());
        }
    }
}