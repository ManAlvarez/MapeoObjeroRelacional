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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author manuel
 */
//Creacion de relación moitos a moitos.
@Entity
@Table(name = "Posicion")
public class Posicion implements Serializable {

    public static final int PORTEIRO = 0;
    public static final int DEFENSA = 1;
    public static final int CENTROCAMPISTA = 2;
    public static final int DELANTERO = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "linea")
    private int linea;
    @Column(name = "nomePosicion")
    private String nomePosicion;

    @ManyToMany(cascade = {CascadeType.ALL}) // "ManyToMany" Insicamos a Hibernate que estamos nunha relación moitos a moitos.
    // Para poder realizar os joins.
    // "name" é o nome da tábla da relación n..n.
    // "joinColumns" contén o nome da columna da táboa creada para a relación que é a que utiliza como clave foránea con esta clase.
    // "inverseJoinColumn" contén o nome da columna da táboa creada para a relación que é utilizada como clave foránea da outra clase, 
    // nestae caso da clase xogador.
    @JoinTable(name = "PosicionXogador", joinColumns = {
        @JoinColumn(name = "IdPosicion")}, inverseJoinColumns = {
        @JoinColumn(name = "IdXogador")})
    private Set<Xogador> xogadores;

    public Posicion() {
    }

    public Posicion(int linea, String nomePosicion) {
        this.linea = linea;
        this.nomePosicion = nomePosicion;
        this.xogadores = new HashSet<>();
    }

    public void addXogadores(Xogador xogador) {
        this.xogadores.add(xogador);
    }

}
