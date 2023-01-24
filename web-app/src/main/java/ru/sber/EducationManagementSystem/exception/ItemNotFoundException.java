package ru.sber.EducationManagementSystem.exception;

/**
 * Класс исключение
 * Программа выбрасывает это исключение, если:
 * Не найден объект в БД
 */
public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException() {
        super();
    }

    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
