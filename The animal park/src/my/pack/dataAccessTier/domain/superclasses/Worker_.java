package my.pack.dataAccessTier.domain.superclasses;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import my.pack.dataAccessTier.domain.subfacilities.Address;

@Generated("EclipseLink-2.0.2.v20100323-r6872 @ Wed Jul 24 15:26:23 IDT 2013")
@StaticMetamodel(Worker.class)
public abstract class Worker_ { 

	public static volatile SingularAttribute<Worker, Long> id;
	public static volatile SingularAttribute<Worker, String> first_name;
	public static volatile SingularAttribute<Worker, Address> address;
	public static volatile SingularAttribute<Worker, Integer> age;
	public static volatile SingularAttribute<Worker, String> last_name;
	public static volatile SingularAttribute<Worker, Character> gender;
	public static volatile SingularAttribute<Worker, Long> cell_number;
	public static volatile SingularAttribute<Worker, Integer> salary;
	public static volatile SingularAttribute<Worker, Integer> worker_db_id;

}