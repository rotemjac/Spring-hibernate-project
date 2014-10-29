package my.pack.dataAccessTier.domain.subfacilities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import my.pack.dataAccessTier.domain.subfacilities.Area;
import my.pack.dataAccessTier.domain.subfacilities.Camera;
import my.pack.dataAccessTier.domain.subfacilities.Fence;
import my.pack.dataAccessTier.domain.subfacilities.Gate;

@Generated("EclipseLink-2.0.2.v20100323-r6872 @ Wed Jul 24 15:26:23 IDT 2013")
@StaticMetamodel(Logistic_facilities.class)
public class Logistic_facilities_ { 

	public static volatile ListAttribute<Logistic_facilities, Area> areas;
	public static volatile ListAttribute<Logistic_facilities, Fence> fences;
	public static volatile ListAttribute<Logistic_facilities, Gate> gates;
	public static volatile ListAttribute<Logistic_facilities, Camera> cameras;

}