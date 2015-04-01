package com.retroDante.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Element2D extends Rigidbody implements Drawable{
	
	/*Vec2f m_position = new Vec2f(0,0);
	Vec2f m_velocity = new Vec2f(0,0);
	private Vec2f m_dimension = new Vec2f(32,32);*/
	
	private Texture m_visual;
	private Vector2 m_positionInTileSet = new Vector2(0,0);
	private GameCamera m_observer = null;
	
	
	//constructeurs et factories : 
	
	Element2D()
	{
		String path = "img/image02.png";
        File file = new File(path);
        BufferedImage image;
		try {
			
			image = ImageIO.read(file);
			m_visual = new ImageIcon(image).getImage();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	Element2D(Camera observer)
	{
		m_observer = observer;
		
		String path = "img/image02.png";
        File file = new File(path);
        BufferedImage image;
		try {
			
			image = ImageIO.read(file);
			m_visual = new ImageIcon(image).getImage();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cette fonction est une factory créant un element "static" (n'usant pas la gravitée, mais étant solide) :
	 * isSolid = true, blockCursor = false
	 * 
	 * Utile pour la plupart des éléments "static" du jeu (plateformes, sol, fond, ...)
	 * 
	 */
	public static Element2D StaticElement()
	{
		Element2D newElement = new Element2D();
		newElement.makeStaticBody();
		
		String path = "img/image02.png";
        File file = new File(path);
        BufferedImage image;
		try {
			
			image = ImageIO.read(file);
			newElement.setVisual( new ImageIcon(image).getImage() );
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return newElement;
	}
	
	/**
	 * Cette fonction est une factory créant un element "static" (n'usant pas la gravitée, mais étant solide) :
	 * isSolid = true, blockCursor = false
	 * 
	 * Utile pour la plupart des éléments "static" du jeu (plateformes, sol, fond, ...)
	 * 
	 */
	public static Element2D StaticElement(Camera observer)
	{
		Element2D newElement = new Element2D(observer);
		newElement.makeStaticBody();
		
		String path = "img/image02.png";
        File file = new File(path);
        BufferedImage image;
		try {
			
			image = ImageIO.read(file);
			newElement.setVisual( new ImageIcon(image).getImage());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return newElement;
	}
	
	/**
	 * Cette fonction est une factory créant un element "solide" (usant la gravitée, et étant solide) :
	 * isSolid = true, blockCursor = false, plus gravitée
	 * 
	 */
	public static Element2D SolidElement()
	{
		Element2D newElement = new Element2D();
		newElement.makeSolidBody();
		
		String path = "img/image02.png";
        File file = new File(path);
        BufferedImage image;
		try {
			
			image = ImageIO.read(file);
			newElement.setVisual( new ImageIcon(image).getImage() );
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return newElement;
	}
	
	/**
	 * Cette fonction est une factory créant un element "solide" (usant la gravitée, et étant solide) :
	 * isSolid = true, blockCursor = false, plus gravitée
	 * 
	 */
	public static Element2D SolidElement(Camera observer)
	{
		Element2D newElement = new Element2D(observer);
		newElement.makeSolidBody();
		
		String path = "img/image02.png";
        File file = new File(path);
        BufferedImage image;
		try {
			
			image = ImageIO.read(file);
			newElement.setVisual( new ImageIcon(image).getImage());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return newElement;
	}
	
	
	
	
	//setters et getters : 
	
	public Image getVisual()
	{
		return m_visual;
	}
	
	public void setVisual(Image img)
	{
		m_visual = img;
	}
	
	
	
	
	//updates : 
	public void update(float deltaTime)
	{
		updateForces(deltaTime);
		updateMovement(deltaTime);
	}
	
	public void updateMovement(float deltaTime)
	{
		m_velocity = m_velocity.add(getForceResult());//ajout des forces
		m_velocity = m_velocity.multiply(deltaTime);
		move( m_velocity );

		
		//réinitialisation : 
		m_velocity = Vec2f.Zero();
	}

	public void update(float deltaTime, List<Element2D> others)
	{
		updateForces(deltaTime);
		updateMovement(deltaTime, others);
	}
	
	public void updateMovement(float deltaTime, List<Element2D> others)
	{
		m_velocity = m_velocity.add(getForceResult());//ajout des forces
		m_velocity = m_velocity.multiply(deltaTime);
		
		//collision ? 
		
		//celon x : 
		move( new Vec2f(m_velocity.x, 0.f) );
		//collision ? 
		for(Element2D e : others)
		{
			if(this.collideWith(e))
			{
				move( new Vec2f(-m_velocity.x, 0.f) );
				break;
			}
		}
		//celon y : 
		move( new Vec2f(0.f, m_velocity.y) );
		//collision ? 
		for(Element2D e : others)
		{
			if(this.collideWith(e))
			{
				move( new Vec2f(0.f, -m_velocity.y) );
				break;
			}
		}
		
		//réinitialisation : 
		m_velocity = Vec2f.Zero();
	}
	
	
		
		
	//Transformable : 
	public Vec2f getPosition()
	{
		return m_position;
	}
	
	public void setPosition(Vec2f pos)
	{
		m_position = pos;
	}
	
	public void move(Vec2f deltaPos)
	{
		m_position = m_position.add(deltaPos);
	}
	
	//Drawable : 
	public void draw(SpriteBatch batch)
	{
		
	}
	
}
