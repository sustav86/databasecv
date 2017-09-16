package ua.sustav.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.sustav.model.Contact;
import ua.sustav.model.ContactType;
import ua.sustav.model.Resume;
import ua.sustav.model.Section;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by SUSTAVOV on 16.09.2017.
 */
class ArrayStorageTest {
    private static ArrayStorage arrayStorage = new ArrayStorage();
    private static Resume R1, R2, R3;

    @BeforeAll
    static void initAll() {
        R1 = new Resume("Sustavov Anton", "Kiev");
        R1.addContact(new Contact(ContactType.TELEPHON, "23232323"));
        R1.addSection(new Section());
        R2 = new Resume("Mahatma", "India");
        R2.addContact(new Contact(ContactType.TELEPHON, "23232323"));
        R2.addSection(new Section());
        R3 = new Resume("Hitler", "Austria");
        R3.addContact(new Contact(ContactType.TELEPHON, "23232323"));
        R3.addSection(new Section());
    }

    @BeforeEach
    void setUp() {
        arrayStorage.clear();
        arrayStorage.save(R1);
        arrayStorage.save(R2);
        arrayStorage.save(R3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void clear() {
        arrayStorage.clear();
        assertEquals(null, arrayStorage.load(R1.getUuid()));
        assertEquals(null, arrayStorage.load(R2.getUuid()));
        assertEquals(null, arrayStorage.load(R3.getUuid()));
    }

    @Test
    void save() {
        Resume testResume = new Resume("Rihana", "USA");
        arrayStorage.save(testResume);
        assertEquals(testResume, arrayStorage.array[arrayStorage.size() - 1]);
        assertEquals(testResume, arrayStorage.load(testResume.getUuid()));
    }

    @Test
    void update() {
        R1.setHomePage("DOMA");
        arrayStorage.update(R1);
        assertEquals("DOMA", arrayStorage.load(R1.getUuid()).getHomePage());
    }

    @Test
    void load() {
        assertEquals(R3, arrayStorage.load(R3.getUuid()));
        assertEquals(null, arrayStorage.load("xxxxxx"));
    }

    @Test
    void delete() {
//        assertEquals(R3, arrayStorage.array[1]);
        arrayStorage.delete(R2.getUuid());
        assertEquals(2, arrayStorage.size());
        assertEquals(null, arrayStorage.load(R2.getUuid()));
        assertEquals(R3, arrayStorage.array[1]);
    }

    @Test
    void getAllSorted() {
        arrayStorage.getAllSorted();
        assertEquals(R3, arrayStorage.array[0]);
    }

    @Test
    void size() {
        assertEquals(3, arrayStorage.size());
    }

}