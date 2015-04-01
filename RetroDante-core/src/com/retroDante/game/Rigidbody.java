package com.retroDante.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Rigidbody {
	
	protected Vector2 m_position = new Vector2(0,0);
	protected Vector2 m_velocity = new Vector2(0,0);
	protected Vector2 m_dimension = new Vector2(32,32);
	protected Rectangle m_collider = new Rectangle(0,0,m_dimension.x, m_dimension.y);
	
	private List<Force> m_tabForce = new ArrayList<Force>();
	private List<Force> m_tabConstantForce = new ArrayList<Force>();
	private boolean m_blockCursor;
	private boolean m_isSolid;
	
	
	//constructeurs et factories : 
	
	//default : blockCursor == true && isSolid == true
	Rigidbody()
	{
		setBlockCursor(true);
		setIsSolid(true);
	}
	
	/**
	 * Cette fonction est une factory renvoyant un nouveau rigidbody avec : 
	 * isSolid = true, blockCursor = false, et une force de gravitée.
	 * 
	 * Utile pour la plupart des éléments non "static" du jeu (joueur, ennemis...)
	 * 
	 */
	static Rigidbody SolidBody()
	{
		Rigidbody r = new Rigidbody();
		r.setIsSolid(true);
		r.addConstantForce(Force.Gravity());
		
		return r;
	}
	
	/**
	 * Cette fonction est une factory renvoyant un nouveau rigidbody avec : 
	 * isSolid = true, blockCursor = false
	 * 
	 * Utile pour la plupart des éléments "static" du jeu (plateformes, sol, fond, ...)
	 * 
	 */
	static Rigidbody StaticBody()
	{
		Rigidbody r = new Rigidbody();
		r.setIsSolid(true);
		
		return r;
	}
	
	/**
	 * Cette fonction fixe les paramétre de rigidbody pour qu'il devienne un SolidBody :
	 * isSolid = true, blockCursor = false, et une force de gravitée.
	 * 
	 * Utile pour la plupart des éléments non "static" du jeu (joueur, ennemis...)
	 * 
	 */
	public void makeSolidBody()
	{
		this.setBlockCursor(false);
		this.setIsSolid(true);
		this.addConstantForce(Force.Gravity());
	}
	
	/**
	 * Cette fonction fixe les paramétre de rigidbody pour qu'il devienne un SolidBody :
	 * isSolid = true, blockCursor = false
	 * 
	 * Utile pour la plupart des éléments "static" du jeu (plateformes, sol, fond, ...)
	 * 
	 */
	public void makeStaticBody()
	{
		this.setBlockCursor(false);
		this.setIsSolid(true);
	}
	
	
	
	//getters et setters : 
	
	public void setCollider(Rectangle collider)
	{
		m_collider = collider;
	}
	
	public Rectangle getCollider()
	{
		return m_collider;
	}

	public boolean getIsSolid() 
	{
		return m_isSolid;
	}

	public void setIsSolid(boolean m_isSolid) 
	{
		this.m_isSolid = m_isSolid;
	}

	public boolean getBlockCursor() 
	{
		return m_blockCursor;
	}

	public void setBlockCursor(boolean m_blockCursor) 
	{
		this.m_blockCursor = m_blockCursor;
	}
	
	public void addForce(Force newForce)
	{
		m_tabForce.add(newForce);
	}
	public void addConstantForce(Force newForce)
	{
		m_tabConstantForce.add(newForce);
	}
	
	public Vector2 getForceResult()
	{
		
		int nbOfForces = m_tabForce.size() + m_tabConstantForce.size();
		if(nbOfForces == 0)
			return Vector2.Zero;
		
		float resultant_x = 0.f;
		float resultant_y = 0.f;
		
		for(Force f : m_tabForce)
		{
			resultant_x += f.getIntensity() * f.getDirection().x;
			resultant_y += f.getIntensity() * f.getDirection().y;
		}
		
		for(Force f : m_tabConstantForce)
		{
			resultant_x += f.getIntensity() * f.getDirection().x;
			resultant_y += f.getIntensity() * f.getDirection().y;
		}
		
		
		return new Vector2( (resultant_x / (float)nbOfForces), (resultant_y / (float)nbOfForces) );
	}
	
	public void updateForces(float deltaTime)
	{
		Iterator<Force> it = m_tabForce.iterator();
		while(it.hasNext())
		{
			System.out.println("coucou");
			Force f = it.next();
			if(!f.update(deltaTime))
			{
				it.remove();
			}
		}
	}
	
	//Fonctions de collision :
		
		/**
		 * 
		 * @param other : autre rigidbody 
		 * @return renvoi true s'il y a collision 
		 */
		public boolean collideWith(Rigidbody other)
		{
			if(m_collider.overlaps(other.getCollider()))
				return true;
			else 
				return false;
			
		}
		
		/**
		 * 
		 * @param other : point à tester 
		 * @return renvoi true si le point est dans le rigidbody
		 */
		
		public boolean collideWith(Vector2 point)
		{
			if(m_collider.contains(point))
				return true;
			else 
				return false;
		}
		
		/**
		 * 
		 * @param other : autre rigidbody 
		 * @return renvoi true si l'autre rigidbody est dans le premier
		 */
		public boolean contains(Rigidbody other)
		{
			if(m_collider.contains(other.getCollider()))
				return true;
			else 
				return false;
			
		}
		
		/**
		 * 
		 * @param other : autre rigidbody 
		 * @return renvoi true si le deuxieme rigidbody contient le premier
		 */
		public boolean containedIn(Rigidbody other)
		{
			if(other.getCollider().contains(this.m_collider))
				return true;
			else 
				return false;
			
		}
		
		/**
		 * 
		 * @param other : point à tester 
		 * @return renvoi true si le point est dans le rigidbody
		 */
		public boolean contains(Vector2 point)
		{
			if(m_collider.contains(point))
				return true;
			else 
				return false;
		}
	
	
}
