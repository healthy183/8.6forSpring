import java.util.ArrayList;
import java.util.List;


public class Test {

	public static void main(String[] args) {
		
		List list =  new ArrayList();
		list.add(1);list.add(2);list.add(3);
		
		
		
		List list2 = new ArrayList();
		list2.add(1);list2.add(2);list2.add(4);
		//list2.removeAll(list);
		//System.out.println("ĞÂÔöµÄ"+list2);
		//list.retainAll(list2);
	
		list.removeAll(list2);
		System.out.println(list);
	}
	
	
}
