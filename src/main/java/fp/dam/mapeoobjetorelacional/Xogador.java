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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author manuel
 */
// Creamos unha relación 1 a moitos.
@Entity
@Table(name = "Xogador")
public class Xogador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "nome")
    public String nome;
    @Column(name = "dorsal")
    public int dorsal;
    @ManyToOne
    @JoinColumn(name = "idEquipo")
    private Equipo equipo;

    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "xogadores")
    private Set<Posicion> posicions;

    public Xogador() {
    }

    public Xogador(String nome, int dorsal, Equipo equipo) {
        this.nome = nome;
        this.dorsal = dorsal;
        this.equipo = equipo;
        this.posicions = new HashSet<>();
    }
    
    public void addPosicion(Posicion posicion){
        this.posicions.add(posicion);
    }

}
