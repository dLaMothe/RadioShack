
public class Ship extends SpaceObject {
/**
 * static Singleton instance
 */
private static Ship instance;

/**
 * Private constructor for singleton
 */
public Ship() {
}

/**
 * Static getter method for retrieving the singleton instance
 */
public static Ship getInstance() {
	if (instance == null) {
		instance = new Ship();
	}
	return instance;
}

@Override
public Position getPosition() {
	// TODO Auto-generated method stub
	return position;
}
}