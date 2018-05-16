package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Annuncio.class)
public abstract class Annuncio_ {

	public static volatile SingularAttribute<Annuncio, String> descrizione;
	public static volatile SingularAttribute<Annuncio, String> titolo;
	public static volatile SingularAttribute<Annuncio, Integer> creatoreID;
	public static volatile SingularAttribute<Annuncio, String> foto;
	public static volatile SingularAttribute<Annuncio, Integer> ultimaModifica;
	public static volatile SingularAttribute<Annuncio, CategoriaAnnuncio> categoria;
	public static volatile SingularAttribute<Annuncio, Float> prezzo;
	public static volatile SingularAttribute<Annuncio, Integer> quantità;
	public static volatile SingularAttribute<Annuncio, Integer> id;
	public static volatile SingularAttribute<Annuncio, StatoAnnuncio> state;

}

