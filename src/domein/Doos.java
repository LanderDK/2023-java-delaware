package domein;

import java.io.Serializable;
import java.util.List;

public interface Doos {

	int getId();

	String getNaam();

	Type getType();

	Status getStatus();

	String getAfmetingen();

	double getPrijs();

}
