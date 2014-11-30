package gameObjects;

import settings.Configs;
import board.*;

/**
 * @author sukhenka
 *
 */
public class JovianBattleCruiser 
	extends JovianWarship{

	public JovianBattleCruiser(Sector sector, Ship ship) {
		super(sector, ship);
		label = Configs.JBC;
	}

	@Override
	public void bumped(SpaceObject object) {
		
	}

	@Override
	public void bump(SpaceObject object) {
		
	}
}
