import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.grid.BoundedGrid;
import java.util.ArrayList;
import java.awt.Color;
import java.lang.Thread;
/**
 * Simulates game of Life in Java
 * 
 * @author Evan Honeysett
 * @version 1.0
 */
public class GameOfLife
{
    private int gen;
    private BoundedGrid<Actor> grid;
    private boolean alive;
    private int amount;
    private ActorWorld game;
    private ArrayList<Location> removed = new ArrayList<Location>();
    private ArrayList<Location> added = new ArrayList<Location>();

    /**
     * GameOfLife Constructor
     *
     * @param numOfGenerations A parameter
     */
    public GameOfLife(int numOfGenerations)
    {
        gen = numOfGenerations;
        grid = new BoundedGrid<Actor>(10,10);
        this.grid.put(new Location(2,5), new Actor());
        this.grid.put(new Location(2,6), new Actor());
        this.grid.put(new Location(3,5), new Actor());
        this.grid.put(new Location(3,6), new Actor());
        this.grid.put(new Location(0,3), new Actor());
        this.grid.put(new Location(0,4), new Actor());
        this.grid.put(new Location(1,3), new Actor());
        this.grid.put(new Location(1,4), new Actor());
        game = new ActorWorld(this.grid);
        game.show();
    }

    public void execute()
    throws InterruptedException
    {
        for(int g = 0;g<=this.gen;g++)
        {
            for(int c = 0;c<10;c++)
            {
                for(int r = 0;r<10;r++)
                {
                    ArrayList<Actor> neigh = grid.getNeighbors(new Location(r,c));
                    for (Actor neigh1 : neigh)
                    {
                        if (neigh1.getColor() == Color.BLUE)
                        {
                            this.amount++;
                        }
                    }

                    if(amount == 0 || amount == 1 || amount > 3)
                    {
                        removed.add(new Location(r,c));
                    }
                    else if (amount == 3)
                    {
                        //this.grid.put(new Location(r,c), new Actor());
                        added.add(new Location(r,c));
                    }

                    this.amount = 0;
                }
            }
            for (Location loc : removed)
            {
                this.grid.remove(loc);
            }
            for (Location loc2 : added)
            {
                this.grid.put(loc2, new Actor());
            }
            game.show();
        }

    }
}
