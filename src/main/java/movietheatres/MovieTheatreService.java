package movietheatres;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;

public class MovieTheatreService {
    private Map<String, List<Movie>> shows = new LinkedHashMap<>();

    public void readFromFile(Path path) {
        try {
            Scanner scanner = new Scanner(path);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] temp = line.split("-");
                String cinema = temp[0];
                String[] titleAndTime = temp[1].split(";");
                String title = titleAndTime[0];
                LocalTime startTime = LocalTime.parse(titleAndTime[1]);
                shows.putIfAbsent(cinema, new LinkedList<>());
                shows.get(cinema).add(new Movie(title, startTime));
            }
            for (List<Movie> m : shows.values()) {
                m.sort(Comparator.comparing(Movie::getStartTime));
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("File not found", ioe);
        }
    }

    public Map<String, List<Movie>> getShows() {
        return Map.copyOf(shows);

    }

    public List<String> findMovie(String title) {
        return shows.entrySet().stream()
                .filter(e -> e.getValue().stream().anyMatch(m -> m.getTitle().equals(title)))
                .map(Map.Entry::getKey)
                .toList();

    }

    public LocalTime findLatestShow(String title) {
        return shows.entrySet().stream()
                .flatMap(e -> e.getValue().stream())
                .filter(m -> m.getTitle().equals(title))
                .map(Movie::getStartTime)
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalArgumentException("Movie " + title + " not found."));
    }
}

