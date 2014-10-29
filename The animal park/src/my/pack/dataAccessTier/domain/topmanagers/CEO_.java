package my.pack.dataAccessTier.domain.topmanagers;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import my.pack.dataAccessTier.domain.subfacilities.Address;
import my.pack.dataAccessTier.domain.superclasses.Manager_;
import my.pack.dataAccessTier.domain.topmanagers.Facilities;

@Generated("EclipseLink-2.0.2.v20100323-r6872 @ Wed Jul 24 15:26:22 IDT 2013")
@StaticMetamodel(CEO.class)
public class CEO_ extends Manager_ {

	public static volatile ListAttribute<CEO, Address> addresses;
	public static volatile SingularAttribute<CEO, Facilities> facilities;

}