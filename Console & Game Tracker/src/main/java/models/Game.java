package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "games")
public class Game {

    private int id;
    private String title;
    private Genre genre;
    private List<Console> consolesAvailableFor;
    private List<Owner> owners;
    private Console hostConsole;

    public Game(){

    }

    public Game(String title, Genre genre, Console hostConsole) {
        this.title = title;
        this.genre = genre;
        this.consolesAvailableFor = new ArrayList<Console>();
        this.owners = new ArrayList<Owner>();
        this.hostConsole = hostConsole;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

   @Enumerated(value = EnumType.STRING)
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @ManyToMany
    @JoinTable(name = "console_game",
            joinColumns = {@JoinColumn(name = "game_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "console_id", nullable = false, updatable = false)})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public List<Console> getConsolesAvailableFor() {
        return consolesAvailableFor;
    }
    public void setConsolesAvailableFor(List<Console> consoles) {
        this.consolesAvailableFor = consoles;
    }

    public void addConsole(Console console){
        this.consolesAvailableFor.add(console);
    }

    @OneToMany(mappedBy = "favouriteGame", fetch = FetchType.LAZY)
    public List<Owner> getOwners() {
        return owners;
    }
    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "console_id", nullable = false)
    public Console getHostConsole() {
        return hostConsole;
    }
    public void setHostConsole(Console hostConsole) {
        this.hostConsole = hostConsole;
    }
}
