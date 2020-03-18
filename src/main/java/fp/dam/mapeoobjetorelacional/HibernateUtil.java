/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fp.dam.mapeoobjetorelacional;

import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author manuel
 */
// Debemos configurar Hibernate para poder usalo.
// Cramos a clase HibernateUtil.
//-> Configuramos Hibernate.
//-> Engadimos cales son as clases que se van a mapear.
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    /**
     * Este método devolve a sesión para poder facer operacións coa base de
     * datos.
     *
     */
    public static SessionFactory getSessionFactory() {
        // Se a sesión non se creou, creamola.
        if (sessionFactory == null) {
            try {
                Configuration configuracion = new Configuration();

                // Engadimos as propiedades.
                Properties propiedades = new Properties();

                // Indicamos o conector da base de datos que vamos a usar.
                propiedades.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");

                // Indicamos a localización da base de datos que vamos a utilizar.
                propiedades.put(Environment.URL, "jdbc:mysql://192.168.56.101:3306/hibernate");

                //Indicamos o usuario da base de datos con cal nos vamos conectar e o seu contrasinal
                propiedades.put(Environment.USER, "userhibernate");
                propiedades.put(Environment.PASS, "abc123.");

                //Indicamos o dialecto que ten que usar Hibernate 
                propiedades.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                //Indicamos que se as táboas todas se borren e se volvan crear
                propiedades.put(Environment.HBM2DDL_AUTO, "create-drop");

                //Indicamos que se mostre as operacións SQL que Hibernate leva a cabo
                propiedades.put(Environment.SHOW_SQL, "true");
                configuracion.setProperties(propiedades);

                //Engaidmos aquelas clases nas que queremos facer persistencia
                configuracion.addAnnotatedClass(Equipo.class);
                configuracion.addAnnotatedClass(Entrenador.class);
                configuracion.addAnnotatedClass(Xogador.class);
                configuracion.addAnnotatedClass(Posicion.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuracion.getProperties()).build();
                sessionFactory = configuracion.buildSessionFactory(serviceRegistry);

            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;

    }
}
