package pl.sda.intermediate.playlist;

import org.apache.commons.lang3.RandomUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Playlist extends PlaylistElement {
    private String name;
    private Playmode playmode;
    private List<PlaylistElement> elements;


    @Override
    String play() {

        if (!elements.isEmpty()) {
            return "Pusta playlista";
        }

        String result = "";


        if (playmode == Playmode.SEQUENTIAL) {
            result = playElements();
        }


        if (playmode == Playmode.SHUFFLE) {
            Collections.shuffle(elements);
            result = playElements();


            return elements.stream()
                    .sorted((a, b) -> RandomUtils.nextInt() % 2 == 0 ? -1 : 1)
                    .map(e -> e.play())
                    .collect(Collectors.joining(", ", "", "."));


        }

        if (playmode == Playmode.REPEAT) {
            IntStream.range(1, 11).forEach(e -> playElements());
        }

        System.out.println(result);
    }

    private String playElements() {
        String result;
        result = elements.stream()
                .map(e -> e.play())
                .collect(Collectors.joining(", ", "", "."));
        return result;
    }


}
