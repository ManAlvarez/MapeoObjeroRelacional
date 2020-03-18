/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp.dam.mapeoobjetorelacional;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author manuel
 */
// Las clases deben de tener un costructor publico sin ningún tipo de argumentos.
// Implementar la interface "Serializable".
// Especificar con anotacions como se gardará esta na clase en táboas do modelo relacional.
@Entity
@Table(name = "Equipo")
public class Equipo implements Serializable {

    @Id // Indica que é a clave principal da taboa.
    @Column(name = "id") // Indica o nome da columna donde se gardará ese atributo.
    private int id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "numeroSocios")
    private int numeroSocios;

    // Engadir ao noso equipo un entrenador, que é un obxecto.
    // "OneToOne" Indica que a relación e 1 a 1 entre dúas táboas.
    // "CascadeType" Indica o valor en cascada. Asi Hibernate sabrá como actuar cando se realicen
    // operacións de gardar, borrar, ler , actualizar....
    // "ALL" Indica que debemos realizar a mesma operación en Equipo que en Entrenador.
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn // Indica que a relción entre as dúas táboas se realiza mediante clave primaria.
    private Entrenador entrenador;

    // "OneToMany" Indica que para este atributo estamos do lado un a moitos.
    // "mappedBy" E o nome da clase da relación moitos (neste caso xogadores) que actua como clase foránea nesa clase.
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
    private Set<Xogador> xogadores;

    public Equipo() {
    }

    public Equipo(int id, String nome, String cidade, int numeroSocios, Entrenador entrenador) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.numeroSocios = numeroSocios;
        this.entrenador = entrenador;
        this.xogadores = new HashSet<>();
    }

    public void addXogador(Xogador xogador) {
        this.xogadores.add(xogador);
    }

}
