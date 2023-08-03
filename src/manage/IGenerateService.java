package manage.impl;

public interface IGenerateService<E> {
    void display();
    void create();
    void update();
    E findById();
    void delete();
}
