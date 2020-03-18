/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp.dam.mapeoobjetorelacional;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author manuel
 */
// Vamos a gardar un obxecto da clase Equipo.
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Creamos un entrenador
        Entrenador entrenador = new Entrenador(1, "Nome1", "Apelidos1", 50);

        //Creamos un equipo
        Equipo equipo = new Equipo(1, "San Clemente", "Santiago", 1000, entrenador);

        //Creamos as posicions
        Posicion porteiro = new Posicion(Posicion.PORTEIRO, "Porteiro");
        Posicion central = new Posicion(Posicion.DEFENSA, "Central");
        Posicion lateral = new Posicion(Posicion.DEFENSA, "Lateral");
        Posicion interior = new Posicion(Posicion.CENTROCAMPISTA, "Interior");

        //Creamos os xogadores
        Xogador xogador1 = new Xogador("nome1", 1, equipo);
        xogador1.addPosicion(porteiro);
        porteiro.addXogadores(xogador1);
        equipo.addXogador(xogador1);

        Xogador xogador2 = new Xogador("nome2", 2, equipo);
        xogador1.addPosicion(lateral);
        lateral.addXogadores(xogador2);
        xogador1.addPosicion(central);
        central.addXogadores(xogador2);
        equipo.addXogador(xogador2);

        Xogador xogador3 = new Xogador("nome3", 3, equipo);
        xogador3.addPosicion(central);
        central.addXogadores(xogador3);
        xogador3.addPosicion(interior);
        interior.addXogadores(xogador3);
        equipo.addXogador(xogador3);

        Transaction transaccion = null;

        try {
            // Collemos a sesión de Hibernate.
            Session session = HibernateUtil.getSessionFactory().openSession();

            // Comezamos unha transacción.
            transaccion = session.beginTransaction();

            // Gardamos o equipo.
            session.save(equipo);

            // Facemos un commit da transacción.
            transaccion.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }

        //Consultas Hibernate
        try {
            //Collemos a sesión de Hibernate
            Session session = HibernateUtil.getSessionFactory().openSession();

            //Facemos unha consulta
            org.hibernate.query.Query q1 = session.createQuery("SELECT x FROM Xogador x");
            List<Xogador> xogadores1 = q1.list();
            for (Xogador xogador : xogadores1) {
                System.out.println(xogador.toString());
            }

            System.out.println("---------------------");

            //Tamén podemos escoller recuperar só algún parámetro
            org.hibernate.query.Query q2 = session.createQuery("SELECT x.nome,x.dorsal FROM Xogador x");
            List<Object[]> xogadores2 = q2.list();
            for (Object[] xogador : xogadores2) {
                System.out.println(xogador[0] + " - Dorsal: " + xogador[1]);
            }

            System.out.println("---------------------");

            //Consulta con só un resultado
            org.hibernate.query.Query q3 = session.createQuery("SELECT x FROM Xogador x WHERE dorsal=1");
            Xogador xogadorAux = (Xogador) q3.uniqueResult();
            System.out.println(xogadorAux.toString());

            System.out.println("---------------------");

            //Paginacion
            int tamPagina = 1;
            //Primare pagína es la 0
            int paginaMostrar = 1;
            org.hibernate.query.Query q4 = session.createQuery("SELECT x FROM Xogador x ORDER BY dorsal");
            q4.setMaxResults(tamPagina);
            q4.setFirstResult(tamPagina * paginaMostrar);
            List<Xogador> xogadores4 = q4.list();
            for (Xogador xogador : xogadores4) {
                System.out.println(xogador.toString());
            }

            //Numero de paginas
            org.hibernate.query.Query q4_2 = session.createQuery("SELECT count(*) FROM Xogador x");
            long numObj = (Long) q4_2.uniqueResult();
            int numPaginas = (int) Math.ceil((double) numObj / (double) tamPagina);
            System.out.println("Numero de paginas: " + numPaginas);

        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

}
