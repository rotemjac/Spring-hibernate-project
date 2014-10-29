package my.pack.dataAccessTier.domain.subfacilities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import my.pack.dataAccessTier.domain.subfacilities.Animal;
import my.pack.dataAccessTier.domain.subfacilities.Bathroom;
import my.pack.dataAccessTier.domain.subfacilities.Land_Portion;
import my.pack.dataAccessTier.domain.workers.Caregiver;
import my.pack.dataAccessTier.domain.workers.Vet;

@Generated("EclipseLink-2.0.2.v20100323-r6872 @ Wed Jul 24 15:26:23 IDT 2013")
@StaticMetamodel(Area.class)
public class Area_ { 

	public static volatile SingularAttribute<Area, String> in_or_out;
	public static volatile SingularAttribute<Area, Vet> the_vet;
	public static volatile SingularAttribute<Area, Integer> db_id;
	public static volatile SingularAttribute<Area, Caregiver> the_caregiver;
	public static volatile ListAttribute<Area, Animal> animals_in_area;
	public static volatile ListAttribute<Area, Bathroom> bathrooms;
	public static volatile SingularAttribute<Area, String> number;
	public static volatile ListAttribute<Area, Land_Portion> land_portions_in_area;

}