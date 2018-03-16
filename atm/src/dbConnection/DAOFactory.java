package dbConnection;

public class DAOFactory {

	public static DAOInter getUserDAOFactory()
	{
		return new DAOImple();
	}

}
