package view;

import controller.Controller;

public class RunExample extends Command {
	private Controller myController;
	
	public RunExample(String key, String desc, Controller myController){
		super(key, desc);
		this.myController = myController;
	}
	
	@Override
	public void execute() {
		try{
			myController.allStep();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}