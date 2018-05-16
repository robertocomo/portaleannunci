package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Utente.class)
public abstract class Utente_ {

	public static volatile SingularAttribute<Utente, String> password;
	public static volatile SingularAttribute<Utente, String> cognome;
	public static volatile SingularAttribute<Utente, String> nome;
	public static volatile SingularAttribute<Utente, Integer> idUtente;
	public static volatile SingularAttribute<Utente, String> email;

}

