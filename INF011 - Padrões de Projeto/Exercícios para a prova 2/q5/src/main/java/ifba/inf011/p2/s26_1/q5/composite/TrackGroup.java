package ifba.inf011.p2.s26_1.q5.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Composite
public class TrackGroup implements TrackComponent {

    private String nome;
    private List<TrackComponent> tracks;

    public TrackGroup(String nome) {
        this.nome = nome;
        this.tracks = new ArrayList<TrackComponent>();
    }

    public String getNome() {
        return this.nome;
    }

    public void adicionar(TrackComponent track) {
        this.tracks.add(track);
    }

    public void remover(TrackComponent track) {
        this.tracks.remove(track);
    }

    public List<TrackComponent> getFilhos() {
        return Collections.unmodifiableList(this.tracks);
    }

    @Override
    public int getDuracao() {
        
        int maior = 0;

        for (TrackComponent track : this.tracks) {
            maior = Math.max(maior, track.getDuracao());
        }

        return maior;
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();

        sb.append("[TrackGroup] ")
          .append(this.nome)
          .append(" | duração: ")
          .append(this.getDuracao())
          .append("s")
          .append("\n");

        for (TrackComponent track : this.tracks) {
            sb.append(track.render()).append("\n");
        }

        return sb.toString();
    }
}