package com.todo.service;

import java.util.List;
import java.util.Set;

import com.todo.model.Item;
import com.todo.model.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.dao.TodoListDAO;

@Service
@Transactional
public class TodoListServiceImpl implements TodoListService {

	@Autowired
	private TodoListDAO todoListDAO;

	@Override
	@Transactional
	public void addList(TodoList todoList) {
		todoListDAO.addList(todoList);
	}

	@Override
	@Transactional
	public List<TodoList> getAllList() {
		return todoListDAO.getAllList();
	}

	@Override
	@Transactional
	public void deleteList(Integer listId) {
		todoListDAO.deleteList(listId);
	}

	@Override
	public TodoList getList(int listId) {
		return todoListDAO.getList(listId);
	}

	@Override
	public TodoList updateList(TodoList todoList) {
		return todoListDAO.updateList(todoList);
	}

	@Override
    public TodoList markList(Integer listId,Boolean mark) {
	    TodoList list = getList(listId);
	    list.setCompleted(mark);
	    updateList(list);
	    return list;
    }

    // Implementation for items //
    @Override
    public Set<Item> getAllItems(Integer listId) {
        TodoList list = getList(listId);
        return list.getItemList();
    }

    @Override
    public TodoList addItem(Integer listId, Item item) {
        return todoListDAO.addItem(listId,item);
    }

    @Override
    public TodoList removeItem(Integer listId, Integer id) {
        return todoListDAO.removeItem(listId,id);
    }

    @Override
    public TodoList markItem(Integer listId, Integer id) {
        return todoListDAO.markItem(listId,id);
    }

    public void setTodoListDAO(TodoListDAO todoListDAO) {
		this.todoListDAO = todoListDAO;
	}

}
