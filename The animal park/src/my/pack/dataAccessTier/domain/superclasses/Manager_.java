package my.pack.dataAccessTier.domain.superclasses;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import my.pack.dataAccessTier.domain.superclasses.Worker;

@Generated("EclipseLink-2.0.2.v20100323-r6872 @ Wed Jul 24 15:26:23 IDT 2013")
@StaticMetamodel(Manager.class)
public abstract class Manager_ extends Worker_ {

	public static volatile SingularAttribute<Manager, Long> office_number;
	public static volatile SingularAttribute<Manager, Integer> number_of_workers;
	public static volatile ListAttribute<Manager, Worker> workers_list;

}