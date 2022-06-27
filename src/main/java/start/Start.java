package start;

import java.sql.SQLException;
import java.util.logging.Logger;

import presentation.Controller;
import presentation.MainView;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());


	public static void main(String[] args) throws SQLException {
		MainView view = new MainView();
		Controller c = new Controller(view);
	}

}
