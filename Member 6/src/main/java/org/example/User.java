package org.example;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Immutable User data object.
 *
 * Represents one user parsed from users.txt:
 * - id: integer user id
 * - name: user's name
 * - likedMovieIds: insertion-ordered set of liked movie ids
 */
public final class User {
    private final int id;
    private final String name;
    private final Set<Integer> likedMovieIds;

    public User(int id, String name, Set<Integer> likedMovieIds) {
        this.id = id;
        this.name = name != null ? name.trim() : "";
        this.likedMovieIds = likedMovieIds != null
                ? new LinkedHashSet<>(likedMovieIds)
                : new LinkedHashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns an unmodifiable set preserving the insertion order of parsed IDs.
     */
    public Set<Integer> getLikedMovieIds() {
        return Collections.unmodifiableSet(likedMovieIds);
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + '\'' + ", likedMovieIds=" + likedMovieIds + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(likedMovieIds, user.likedMovieIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, likedMovieIds);
    }
}