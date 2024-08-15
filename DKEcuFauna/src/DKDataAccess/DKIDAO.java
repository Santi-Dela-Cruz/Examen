package DKDataAccess;

import java.util.List;

public interface DKIDAO<T> {

    public boolean dkCreate(T entity) throws Exception;

    public List<T> dkReadAll() throws Exception;

    public boolean dkUpdate(T entity) throws Exception;

    public boolean dkDelete(int id) throws Exception;

    public T dkReadBy(Integer id) throws Exception;

}
