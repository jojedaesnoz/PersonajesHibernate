package pojos;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personajes")
public class Personaje implements Comparable<Personaje> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "vida")
    private int vida;

    @OneToMany(mappedBy = "personaje", cascade = CascadeType.DETACH)
    private List<Movimiento> movimientos;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "personaje_arma",
            joinColumns = {@JoinColumn(name = "id_personaje")},
            inverseJoinColumns = {@JoinColumn(name = "id_arma")})
    private List<Arma> armas;

    {
        armas = new ArrayList<>();
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

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public List<Arma> getArmas() {
        return armas;
    }

    public void setArmas(List<Arma> armas) {
        this.armas = armas;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Personaje))
            return false;
        return ((Personaje) obj).getId() == this.id;
    }

    @Override
    public int compareTo(Personaje o) {
        return nombre.compareTo(o.getNombre());
    }
}
