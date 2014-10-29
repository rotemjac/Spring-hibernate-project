package my.pack.dataAccessTier.domain.subfacilities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import my.pack.dataAccessTier.domain.subfacilities.Area;

@Generated("EclipseLink-2.0.2.v20100323-r6872 @ Wed Jul 24 15:26:23 IDT 2013")
@StaticMetamodel(Land_Portion.class)
public class Land_Portion_ { 

	public static volatile SingularAttribute<Land_Portion, Area> area;
	public static volatile SingularAttribute<Land_Portion, Integer> land_portion_db_id;
	public static volatile SingularAttribute<Land_Portion, Character> need_to_fix;
	public static volatile SingularAttribute<Land_Portion, String> land_portion_id;
	public static volatile ListAttribute<Land_Portion, String> list_of_problems;
	public static volatile ListAttribute<Land_Portion, String> animals_id;

}