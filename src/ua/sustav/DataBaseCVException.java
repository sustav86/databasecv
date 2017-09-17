package ua.sustav;

import ua.sustav.model.Resume;

/**
 * Created by SUSTAVOV on 16.09.2017.
 */
public class DataBaseCVException extends RuntimeException {

    private String uuid;
    private Resume resume;

    public DataBaseCVException(String message) {
        super(message);
    }

    public DataBaseCVException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBaseCVException(String message, Resume resume) {
        super(message);
        this.resume = resume;
    }

    public DataBaseCVException(String message, Resume resume, Throwable cause) {
        super(message, cause);
        this.resume = resume;
    }

    public DataBaseCVException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

}
