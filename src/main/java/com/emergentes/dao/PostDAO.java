package com.emergentes.dao;

import com.emergentes.modelo.post;
import java.util.List;

/**
 *
 * @author Villalba
 */
public interface PostDAO {
    public void insert(post post) throws Exception;
    public void update(post post) throws Exception;
    public void delete(int id) throws Exception;
    public post getById(int id) throws Exception;
    public List<post> getAll() throws Exception;
}
