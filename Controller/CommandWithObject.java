package Controller;

public interface CommandWithObject extends Commandable {
    boolean check(String arg);
    String whyFailed();
}
