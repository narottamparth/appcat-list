package com.todo.dao;

import java.util.List;

import com.todo.model.Item;
import com.todo.model.TodoList;

public interface TodoListDAO {

	public void addList(TodoList todoList);

	public List<TodoList> getAllList();

	public void deleteList(Integer listId);

	public TodoList updateList(TodoList todoList);

	public TodoList getList(int listId);

	public TodoList addItem(Integer listId , Item item);

    public TodoList removeItem(Integer listId , Integer itemId);

    public TodoList markItem(Integer listId , Integer itemId);
}
