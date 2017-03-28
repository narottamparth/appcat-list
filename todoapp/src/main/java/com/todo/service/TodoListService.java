package com.todo.service;

import java.util.List;
import java.util.Set;

import com.todo.model.Item;
import com.todo.model.TodoList;

public interface TodoListService {
	
	public void addList(TodoList todoList);

	public List<TodoList> getAllList();

	public void deleteList(Integer listId);

	public TodoList getList(int listId);

	public TodoList updateList(TodoList todoList);

	public TodoList markList(Integer listId,Boolean mark);

	/*// --------- Items services ------------//

	public Set<Item> getAllItems(Integer listId);

	public TodoList addItem(Integer listId , Item item);

	public TodoList removeItem(Integer listId , Integer id);

	public TodoList markItem(Integer listId , Integer id);*/
}
