package ru.sber.EducationManagementSystem.exception;

/**
 * Класс исключение
 * Программа выбрасывает это исключение, если:
 * Не найден объект в БД
 */
public class ItemNotFoundRestException extends RuntimeException {

    public ItemNotFoundRestException() {
        super();
    }

    public ItemNotFoundRestException(String message) {
        super(message);
    }

    public ItemNotFoundRestException(String message, Throwable cause) {
        super(message, cause);
    }
}
