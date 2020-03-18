/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp.dam.mapeoobjetorelacional;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author manuel
 */
// Creamos unha relación 1 a 1.
@Entity
@Table(name = "Entrenador")
public class Entrenador implements Serializable {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "apelidos")
    private String apelidos;
    @Column(name = "idade")
    private int idade;

    public Entrenador() {
    }

    public Entrenador(int id, String nome, String apelidos, int idade) {
        this.id = id;
        this.nome = nome;
        this.apelidos = apelidos;
        this.idade = idade;
    }
}
