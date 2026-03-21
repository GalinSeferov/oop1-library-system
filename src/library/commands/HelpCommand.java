package library.commands;

public class HelpCommand implements Command {
    @Override
    public String execute(String[] args) {
        return "The following commands are supported:\n" +
                "open <file>     opens <file>\n" +
                "close           closes currently opened file\n" +
                "save            saves the currently open file\n" +
                "save as <file>  saves the currently open file in <file>\n" +
                "help            prints this information\n" +
                "exit            exists the program";
    }
}
