package ua.sustav.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.sustav.DataBaseCVException;
import ua.sustav.model.Contact;
import ua.sustav.model.ContactType;
import ua.sustav.model.Resume;
import ua.sustav.model.Section;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by SUSTAVOV on 16.09.2017.
 */
class ArrayStorageTest {
    private static ArrayStorage arrayStorage = new ArrayStorage();
    private Resume R1, R2, R3;

    @BeforeAll
    static void initAll() {

    }

    @BeforeEach
    void setUp() {
        R1 = new Resume("Sustavov Anton", "Kiev");
        R1.addContact(new Contact(ContactType.TELEPHON, "23232323"));
        R1.addSection(new Section());
        R2 = new Resume("Mahatma", "India");
        R2.addContact(new Contact(ContactType.TELEPHON, "23232323"));
        R2.addSection(new Section());
        R3 = new Resume("Hitler", "Austria");
        R3.addContact(new Contact(ContactType.TELEPHON, "23232323"));
        R3.addSection(new Section());
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
        assertEquals(0, arrayStorage.size());
        assertThrows(DataBaseCVException.class, () -> arrayStorage.load(R1.getUuid()));
        assertThrows(DataBaseCVException.class, () -> arrayStorage.load(R2.getUuid()));
        assertThrows(DataBaseCVException.class, () -> arrayStorage.load(R3.getUuid()));
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
        assertThrows(DataBaseCVException.class, () -> arrayStorage.load("xxxxxx"));
    }

    @Test()
    void delete() {
        arrayStorage.delete(R2.getUuid());
        assertEquals(2, arrayStorage.size());
        assertThrows(DataBaseCVException.class, () -> arrayStorage.load(R2.getUuid()));
        assertEquals(R3, arrayStorage.array[1]);
    }

    @Test
    void getAllSorted() {
        Resume[] src = new Resume[]{R3, R2, R1};
        Collection<Resume> sorted = arrayStorage.getAllSorted();
        assertArrayEquals(src, sorted.toArray());
    }

    @Test
    void size() {
        assertEquals(3, arrayStorage.size());
    }

}