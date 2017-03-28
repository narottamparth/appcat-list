package com.todo.dao;

import java.util.List;

import com.todo.model.Item;
import com.todo.model.TodoList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TodoListDAOImpl implements TodoListDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addList(TodoList todoList) {
		sessionFactory.getCurrentSession().saveOrUpdate(todoList);

	}

	@Override
	public List<TodoList> getAllList() {

		return sessionFactory.getCurrentSession().createCriteria(TodoList.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
	}

	@Override
	public void deleteList(Integer listId) {
		TodoList todoList = (TodoList) sessionFactory.getCurrentSession().load(
				TodoList.class, listId);
		if (null != todoList) {
			this.sessionFactory.getCurrentSession().delete(todoList);
		}

	}

	@Override
	public TodoList getList(int listId) {
		return (TodoList) sessionFactory.getCurrentSession().get(
				TodoList.class, listId);
	}

	@Override
	public TodoList updateList(TodoList todoList) {
		sessionFactory.getCurrentSession().update(todoList);
		return todoList;
	}

    @Override
	public TodoList addItem(Integer listId , Item item) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        TodoList list = (TodoList) session.get(TodoList.class,listId);
        list.getItemList().add(item);
        session.saveOrUpdate(list);
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public TodoList removeItem(Integer listId , Integer itemId) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        TodoList list = (TodoList) session.get(TodoList.class,listId);
        Item item = (Item) session.get(Item.class,itemId);
        list.getItemList().remove(item);
        session.saveOrUpdate(list);
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public TodoList markItem(Integer listId , Integer itemId) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        TodoList list = (TodoList) session.get(TodoList.class,listId);
        Item item = (Item) session.get(Item.class,itemId);
        list.getItemList().remove(item);
        item.setCompleted(true);
        list.getItemList().add(item);
        session.saveOrUpdate(list);
        session.saveOrUpdate(item);
        session.getTransaction().commit();
        session.close();
        return list;
    }

}