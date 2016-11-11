package dataBase;

public interface DataAccessObject <T> {
	public boolean create(T entidad) throws Exception;
	public boolean search(T entidad) throws Exception;
	public boolean update(T entidad) throws Exception;
	public boolean delete(T entidad) throws Exception;
	public void close() throws Exception;
}
