package com.techelevator;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class ExcelsiorCLI {
	
	private MenuLogicHandler mlh;
	private Controller controller;
	
	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior-venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		ExcelsiorCLI application = new ExcelsiorCLI(dataSource);
		application.run();
		
	}

	public ExcelsiorCLI(DataSource dataSource) {
		mlh = new MenuLogicHandler();
		controller = new Controller(dataSource);
	}

	public void run() {
		
		while(!mlh.isFinished()) {
			mlh.mainMenuLogic(controller.getListOfVenues());
			
			if (!mlh.isReset()) {
				mlh.availableSpaces(controller.getAvailableSpaces(mlh.getReservation()), controller.getListOfVenues());
				
				if (!mlh.isReset()) {
					controller.saveReservation(mlh.getReservation());
					
					if (!mlh.isReset()) {
						mlh.getReservationConfirmation(controller.getReservationID(mlh.getReservation()));
					}
				}
			}	
		}
	}
}
