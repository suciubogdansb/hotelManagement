package View;

public class ExitCommand implements Command{
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public String Display() {
        return "Exit";
    }
}
