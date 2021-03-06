package game.util;

import game.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MultiStageTimer extends Timer
{
	private int[] time;
	private boolean[] current;
	
	public MultiStageTimer(int[] time)
	{
		super(true, time[0]);
		this.time = time;
		current = new boolean[time.length];
		current[0] = true;
	}
	public MultiStageTimer(int[] time, int start)
	{
		super(true, time[0]);
		this.time = time;
		current = new boolean[time.length];
		current[start] = true;
	}
	public void update(GameContainer gc, StateBasedGame sbg, World world, int delta) throws SlickException
	{
		counter -= delta;
		if(counter <= 0)
		{
			for(int i = 0; i < current.length;i++)
			{
				if(i == current.length - 1 && current[i])
				{
					current[i] = false;
					current[0] = true;
					counter = time[i];
					break;
				}
				if(current[i])
				{
					current[i] = false;
					current[i + 1] = true;
					counter = time[i];
					break;
				}
				
			}
		}
	}
	public boolean isCurrent(int i)
	{
		return current[i];
	}

}
