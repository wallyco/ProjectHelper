package BotDataJPX;

import java.util.Objects;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.Player;

import Task.Task;

public class Deforester implements Task{
//TODO Add consume
	private GameObject tree;
	private String treeName;
	private Player player = Players.localPlayer();
	
	public Deforester() {}
	
	public Deforester(String name) {
		this.treeName = name;
	}

	@Override
	public boolean execute() {
		if(accept()) {
			woodcut();
		}
		
		return false;
	}

	@Override
	public double fatigueRate() {
		return 0.3;
	}
	
	private boolean accept() {
		if(Dialogues.canContinue())
			Dialogues.continueDialogue();
		
		if(player.isAnimating() 
				|| player.isMoving()) {
			MethodProvider.sleep(100);
			if(player.isAnimating()
					|| player.isMoving())
				return false;
		}
		
		return true;
	}
	
	private boolean woodcut() {
		setTree(tree);
		if(tree != null) {
			MethodProvider.sleepUntil(() -> tree.interact("Chop down"), 2500);
			MethodProvider.sleep(50);
			return true;
		}
		return false;
	}

	public GameObject getTree() {
		return tree;
	}

	public void setTree(GameObject tree) {
		tree = GameObjects.closest(t -> t != null && t.getName().equals(treeName));
		if(tree != null) {
			this.tree = tree;
		}
	}

	@Override
	public String toString() {
		return "Deforester [treeName=" + treeName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(treeName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deforester other = (Deforester) obj;
		return Objects.equals(treeName, other.treeName);
	}
	
}
