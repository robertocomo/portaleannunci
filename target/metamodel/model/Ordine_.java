package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ordine.class)
public abstract class Ordine_ {

	public static volatile SingularAttribute<Ordine, Integer> idConsomatore;
	public static volatile SingularAttribute<Ordine, Float> prezzoComplessivo;
	public static volatile SingularAttribute<Ordine, Integer> idOrdine;
	public static volatile SingularAttribute<Ordine, Integer> idAnnuncio;
	public static volatile SingularAttribute<Ordine, Float> prezzo;
	public static volatile SingularAttribute<Ordine, Integer> idIndirizzoSpedizione;
	public static volatile SingularAttribute<Ordine, Integer> quantità;
	public static volatile SingularAttribute<Ordine, Boolean> isShipped;
	public static volatile SingularAttribute<Ordine, String> paymentTransaction;
	public static volatile SingularAttribute<Ordine, String> tracking;
	public static volatile SingularAttribute<Ordine, Integer> idProduttore;
	public static volatile SingularAttribute<Ordine, String> timestamp;

}

