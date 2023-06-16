package controller;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Bodega;
import model.Campo;
import model.Entrada;
import model.Vid;
import model.TipoVid.tipoVid;

public class Controller {

	// creamos la configuracion de nuestro hibernate para poder abrir una sesion
	// para conectarnos con la base de datos
	static Configuration configuracion = new Configuration().configure();
	static SessionFactory sFactory = configuracion.buildSessionFactory();
	static Session sesion = sFactory.openSession();

	// con este metodo creamos todos hacemos todas las transacciones de los objetos
	// que vayamos a crear mas adelante
	public static void transaccionObjeto(Object objeto) {

		try {
			// como vemos, creamos una transaccion
			Transaction transaccion = sesion.beginTransaction();
			// guardamos el objeto pasado por el metodo
			sesion.saveOrUpdate(objeto);
			// y hacemos commit de la transaccione
			transaccion.commit();
		} catch (Exception e) {
			System.out.println("Error al guardar!");
		}

	}

	public static void init() {

		// recogemos todas las instucciones que que hay en la tabla Entrada en la base
		// de datos proporcionada por el ejercicio
		ArrayList<Entrada> listaEntradas = (ArrayList<Entrada>) sesion.createQuery("FROM Entrada", Entrada.class)
				.getResultList();

		// ejecutamos el siguiente metodo que ejecutará diferente código dependiendo de
		// las instrucciones que reciba
		realizarInstrucciones(listaEntradas);
	}

	private static void realizarInstrucciones(ArrayList<Entrada> listaEntradas) {

		// creamos tanto la Bodega como el Campo para despues ir sobreescribiendolos
		// dependiendo de las instrucciones que vayamos leyendo
		Bodega ultimaBodega = new Bodega();
		Campo ultimoCampo = new Campo();

		// recorremos la lista con las entradas que antes hemos recibido de las
		for (Entrada e : listaEntradas) {

			// separamos las instrucciones que hemos recibido anteriormente con nuestra
			// "createQuery()"
			// para esto usamos la funcion "split" que ya he usado varias veces antes
			String[] instrucciones = e.getInstruccion().split(" ");

			// dependiendo de la instruccion que recibamos, el programa hará una cosa u otra
			switch (instrucciones[0]) {
			case "B":
				// com el enunciado nos pide que tenemos que guardar la ultima bodega, pues
				// guardamos las nuevas vids en el objeto creado anteriormente creado como
				// "ultimaBodega"
				Bodega nuevaBodega = new Bodega(e.getId(), instrucciones[1]);
				ultimaBodega = nuevaBodega;

				// guardamos el campo con el metodo de transaccion
				transaccionObjeto(nuevaBodega);
				break;
			case "C":
				// com el enunciado nos pide que tenemos que guardar el ultimo campo, pues
				// guardamos los nuevos campos en el objeto creado anteriormente creado como
				// "ultimoCampo"
				Campo campo = new Campo(e.getId(), ultimaBodega);
				ultimoCampo = campo;

				// guardamos el campo con el metodo de transaccion
				transaccionObjeto(campo);
				break;
			case "V":

				// creamos una variable int para que dependiendo si la Vid es blanca o negra
				// sera 0 o 1
				int tipoDeVid;

				// aqui hacemos el if para saber si es blanca o negra usando el enum creado en
				// "TipoVid"
				if (tipoVid.valueOf(instrucciones[1].toUpperCase()).equals("BLANCA")) {
					tipoDeVid = 0;
				} else {
					tipoDeVid = 1;
				}

				// creamos el nuevo vid con todos los datos que hemos ido almacenando
				Vid vid = new Vid(e.getId(), Integer.parseInt(instrucciones[2]), tipoDeVid, ultimoCampo);

				// tenemos que anadir el nuevo vid a la lista de vids del ultimocampo
				ArrayList<Vid> listaVids = ultimoCampo.getListaVids();
				listaVids.add(vid);

				// tambien se nos pide que se le anada la lista actualizada al ultimo campo
				ultimoCampo.setListaVids(listaVids);

				// guardamos la vid con el metodo de transaccion
				transaccionObjeto(vid);
				break;
			case "#":

				// el ultimoCampo tiene que tener la nueva lista de Vids actualizada por lo que
				// usamos un set que hace un get a la ultima lista del ultimo campo
				ultimoCampo.getBodega().setListaVids(ultimoCampo.getListaVids());

				// entonces recorremos cada vid que haya en la bodega del ultimo campo
				for (Vid v : ultimoCampo.getBodega().getListaVids()) {

					// seteamos la bodega de cada vid con cada bodega que haya en el ultimo campo
					v.setBodega(ultimoCampo.getBodega());

					// guardamos la vid con el metodo de transaccion
					transaccionObjeto(v);
				}
				break;
				
				//quise anadir un metodo para cerrar sesion de hibernate pero me sale error asi que se queda en abierta :p

			}

		}
	}

}
