package dominio;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Agendamento.class)
public abstract class Agendamento_ {

	public static volatile SingularAttribute<Agendamento, String> observacao;
	public static volatile SingularAttribute<Agendamento, Timestamp> data;
	public static volatile SingularAttribute<Agendamento, String> cpf;
	public static volatile SingularAttribute<Agendamento, String> nome;
	public static volatile SingularAttribute<Agendamento, Integer> id;

}

