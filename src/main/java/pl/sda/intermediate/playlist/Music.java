package pl.sda.intermediate.playlist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Music extends PlaylistElement{
    private String title;
    private String author;

    @Override
    public String toString() {
        return "Music{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    String play() {
        return toString();
    }
}
