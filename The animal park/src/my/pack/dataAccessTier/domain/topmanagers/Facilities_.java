package my.pack.dataAccessTier.domain.topmanagers;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import my.pack.dataAccessTier.domain.subfacilities.Economic_facilities;
import my.pack.dataAccessTier.domain.subfacilities.Logistic_facilities;

@Generated("EclipseLink-2.0.2.v20100323-r6872 @ Wed Jul 24 15:26:22 IDT 2013")
@StaticMetamodel(Facilities.class)
public class Facilities_ { 

	public static volatile SingularAttribute<Facilities, Integer> db_id;
	public static volatile SingularAttribute<Facilities, Economic_facilities> eco_fac;
	public static volatile SingularAttribute<Facilities, Logistic_facilities> log_fac;

}