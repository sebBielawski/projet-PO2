import model.Model;
import view.View;


public final class Program {

	public static void main(String[] args) {
		final Model model = new Model();	
		new View(model);
	}
}
