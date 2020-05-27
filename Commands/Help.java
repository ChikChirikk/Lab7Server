package Commands;

import Controller.CommandWithoutArg;

/**
 * show all commands
 *
 * @author Polina
 */

public class Help implements CommandWithoutArg {
    String name = "help";

    public String getName() {
        return name;
    }

    /**
     * @param arg ignore this
     * @return
     */
    @Override
    public Object execute(Object arg) {
        return "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (с сохранением в файл)\n" +
                "remove_first : удалить первый элемент из коллекции\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "history : вывести последние 5 команд (без их аргументов)\n" +
                "count_less_than_impact_speed impactSpeed : вывести количество элементов, значение поля impactSpeed которых меньше заданного\n" +
                "print_ascending : вывести элементы коллекции в порядке возрастания";

    }

}