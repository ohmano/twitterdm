package hu.meza.aao;

public class NoScenarioContext implements ScenarioContext {
	
	public <T> T getSubject() {
		throw new NullObjecException();
	}

	
	public <T> void setSubject(T subject) {

	}

	
	public Action getLastAction() {
		throw new NullObjecException();
	}

	
	public void setLastAction(Action action) {

	}

	
	public Actor getLastActor() {
		throw new NullObjecException();
	}

	
	public void setLastActor(Actor actor) {

	}

	
	public void clean() {

	}
}
