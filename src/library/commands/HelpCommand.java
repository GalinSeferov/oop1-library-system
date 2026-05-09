package library.commands;

public class HelpCommand implements Command {
    @Override
    public String execute(String[] args) {
        return "The following commands are supported:\n" +
                "open <file>     opens <file>\n" +
                "close           closes currently opened file\n" +
                "save            saves the currently open file\n" +
                "save as <file>  saves the currently open file in <file>\n" +
                "login                          logs in to the system\n" +
                "logout                         logs out from the system\n" +
                "books all                      shows all books\n" +
                "books view                     shows all books\n" +
                "books info <id>                shows book details\n" +
                "books find <criteria> <term>  finds books by title, author, or tag\n" +
                "books sort <criteria> [asc|desc]  sorts books by title, author, year, or rating\n" +
                "books add                      adds a new book (admin only)\n" +
                "books remove <id>              removes a book (admin only)\n" +
                "users add                      adds a new user (admin only)\n" +
                "users remove <username>        removes a user (admin only)\n" +
                "help            prints this information\n" +
                "exit            exists the program";
    }
}
