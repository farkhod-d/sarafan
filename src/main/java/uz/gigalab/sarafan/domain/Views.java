package uz.gigalab.sarafan.domain;

public final class Views {

    public interface Id {}
    public interface IdName extends Id {}

    public interface Full extends IdName {}
    public interface FullComment extends Full {}
}
