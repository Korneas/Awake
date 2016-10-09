package awake;

import java.util.Comparator;

public class ComparadorOrden implements Comparator<Elemento>{

	@Override
	public int compare(Elemento o1, Elemento o2) {
		int n1 = o1.getNumero();
		int n2 = o2.getNumero();
		
		return n1-n2;
	}

}
