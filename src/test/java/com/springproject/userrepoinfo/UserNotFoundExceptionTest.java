package com.springproject.userrepoinfo;

import com.springproject.userrepoinfo.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {
        String expectedMessage = "User not found!";
        UserNotFoundException exception = new UserNotFoundException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testDefaultConstructorMessageIsNull() {
        UserNotFoundException exception = new UserNotFoundException(null);

        assertNull(exception.getMessage());
    }
}
