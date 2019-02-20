package pojos;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "armas")
public class Arma implements Comparable<Arma> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ataque")
    private int ataque;

    public enum Rareza {
        COMUN, EXCEPCIONAL, EPICA, LEGENDARIA;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "rareza")
    private Rareza rareza;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "armas")
    private List<Personaje> personajes;

    {
        personajes = new ArrayList<>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public Rareza getRareza() {
        return rareza;
    }

    public void setRareza(Rareza rareza) {
        this.rareza = rareza;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    @Override
    public String toString() {
        return nombre;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Arma))
            return false;
        return ((Arma) obj).getId() == this.id;
    }

    @Override
    public int compareTo(Arma o) {
        return this.nombre.compareTo(o.nombre);
    }
}
