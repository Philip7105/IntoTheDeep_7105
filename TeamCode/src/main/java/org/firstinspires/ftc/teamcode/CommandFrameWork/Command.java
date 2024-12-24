package org.firstinspires.ftc.teamcode.CommandFrameWork;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Command {
	protected Command nextCommand = null;
	protected ArrayList<Subsystem> dependencies = new ArrayList<>();

	public Command(Subsystem... subsystems) {
		Collections.addAll(dependencies, subsystems);
	}

	public Command getNext() {
		return nextCommand;
	}

	public void setNext(Command command) {
		nextCommand = command;
	}

	public Command addNext(Command command) {
		Command commandNode = this;
		while (commandNode.getNext() != null)
			commandNode = commandNode.getNext();

		commandNode.setNext(command);

		return this;
	}

	public ArrayList<Subsystem> getDependencies() {
		return dependencies;
	}

	public abstract void init();

	public abstract void periodic();

	public abstract boolean completed();

	public abstract void shutdown();
}
