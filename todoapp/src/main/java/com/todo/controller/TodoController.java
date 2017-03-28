package com.todo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.todo.model.Item;
import com.todo.model.TodoList;
import com.todo.service.TodoListService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodoController {

	private static final Logger logger = Logger
			.getLogger(TodoController.class);

	@Autowired
	private TodoListService todoListService;

	@RequestMapping(value = "/list/all")
	public @ResponseBody List<TodoList> listLists() throws IOException {
		List<TodoList> listTodoList = todoListService.getAllList();
		return listTodoList;
	}

	@RequestMapping(value = "/list/add", method = RequestMethod.POST)
	public @ResponseBody String addList(@RequestBody TodoList todoList) {
        if(todoList != null) {
            todoListService.addList(todoList);
            return "List added successfully!";
        } else {
            return "Either list is null or List with same id already present!";
        }

	}

	@RequestMapping(value = "/list/remove/{listId}", method = RequestMethod.GET)
	public @ResponseBody String deleteList(@PathVariable Integer listId) {
		try {
			todoListService.deleteList(listId);
		} catch (Exception e) {
			return "Error in removing lsit";
		}
		return "List removed successfully";
	}

	@RequestMapping(value = "/list/toggle/{listId}/{boolMark}", method = RequestMethod.GET)
	public @ResponseBody TodoList markList(@PathVariable Integer listId, @PathVariable Boolean boolMark) {
		TodoList list = todoListService.markList(listId,boolMark);
		return list;
	}

	/*// .............. Items Controller ...............................//
    @RequestMapping(value = "/item/all/{listId}")
    public @ResponseBody Set<Item> listItems(Integer listId) throws IOException {
        Set<Item> itemList = todoListService.getAllItems(listId);
        return itemList;
    }

    @RequestMapping(value = "/item/add/{listId}")
    public @ResponseBody TodoList addItem(@PathVariable Integer listId,Item item) throws IOException {
        return todoListService.addItem(listId,item);
    }

    @RequestMapping(value = "/item/remove/{listId}/{itemId}")
    public @ResponseBody TodoList removeItem(@PathVariable Integer listId,@PathVariable Integer itemId) throws IOException {
        return todoListService.removeItem(listId,itemId);

    }

    @RequestMapping(value = "/item/mark/{listId}/{itemId}")
    public @ResponseBody TodoList markItem(@PathVariable Integer listId,@PathVariable Integer itemId) throws IOException {
        return todoListService.markItem(listId,itemId);

    }*/

}