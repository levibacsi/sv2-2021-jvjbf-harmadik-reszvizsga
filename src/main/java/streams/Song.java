package streams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Song {
    private String title;
    private int length;
    private List<String> performers;
    private LocalDate released;

    public Song(String title, int length, List<String> performers, LocalDate released) {
        this.title = title;
        this.length = length;
        this.performers = new ArrayList<>(performers);
        this.released = released;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public List<String> getPerformers() {
        return new ArrayList<>(performers);
    }

    public LocalDate getRelease() {
        return released;
    }
}
