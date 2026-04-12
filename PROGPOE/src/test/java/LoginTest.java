import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit 4 test suite for the Login class.
 * Tests cover: checkUserName(), checkPasswordComplexity(),
 * checkCellPhoneNumber(), registerUser(), loginUser(),
 * and returnLoginStatus().
 */
public class LoginTest {

    // ─────────────────────────────────────────────
    // checkUserName()
    // ─────────────────────────────────────────────

    @Test
    public void testCheckUserName_ValidUsername() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pass1@word", "+27831234567");
        assertTrue(user.checkUserName());
    }

    @Test
    public void testCheckUserName_NoUnderscore() {
        Login user = new Login("Kyle", "Smith", "kyle1", "Pass1@word", "+27831234567");
        assertFalse(user.checkUserName());
    }

    @Test
    public void testCheckUserName_TooLong() {
        // "kyle_1" is 6 chars — exceeds max 5
        Login user = new Login("Kyle", "Smith", "kyle_1", "Pass1@word", "+27831234567");
        assertFalse(user.checkUserName());
    }

    @Test
    public void testCheckUserName_MultipleUnderscores() {
        Login user = new Login("Kyle", "Smith", "k__1", "Pass1@word", "+27831234567");
        assertFalse(user.checkUserName());
    }

    @Test
    public void testCheckUserName_NullUsername() {
        Login user = new Login("Kyle", "Smith", null, "Pass1@word", "+27831234567");
        assertFalse(user.checkUserName());
    }

    // ─────────────────────────────────────────────
    // checkPasswordComplexity()
    // ─────────────────────────────────────────────

    @Test
    public void testCheckPasswordComplexity_ValidPassword() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pass1@word", "+27831234567");
        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordComplexity_TooShort() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pa1@", "+27831234567");
        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordComplexity_NoUpperCase() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "pass1@word", "+27831234567");
        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordComplexity_NoDigit() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pass@word", "+27831234567");
        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordComplexity_NoSpecialChar() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Password1", "+27831234567");
        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordComplexity_NullPassword() {
        Login user = new Login("Kyle", "Smith", "kyl_1", null, "+27831234567");
        assertFalse(user.checkPasswordComplexity());
    }

    // ─────────────────────────────────────────────
    // checkCellPhoneNumber()
    // ─────────────────────────────────────────────

    @Test
    public void testCheckCellPhoneNumber_ValidNumber() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pass1@word", "+27831234567");
        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testCheckCellPhoneNumber_MissingPlusCode() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pass1@word", "0831234567");
        assertFalse(user.checkCellPhoneNumber());
    }

    @Test
    public void testCheckCellPhoneNumber_TooShort() {
        // Only 8 digits after +27 instead of 9
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pass1@word", "+2783123456");
        assertFalse(user.checkCellPhoneNumber());
    }

    @Test
    public void testCheckCellPhoneNumber_TooLong() {
        // 10 digits after +27
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pass1@word", "+278312345678");
        assertFalse(user.checkCellPhoneNumber());
    }

    @Test
    public void testCheckCellPhoneNumber_NullNumber() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pass1@word", null);
        assertFalse(user.checkCellPhoneNumber());
    }

    // ─────────────────────────────────────────────
    // registerUser()
    // ─────────────────────────────────────────────

    @Test
    public void testRegisterUser_AllValid() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pass1@word", "+27831234567");
        assertEquals("Registration successful.", user.registerUser());
    }

    @Test
    public void testRegisterUser_InvalidUsername() {
        Login user = new Login("Kyle", "Smith", "kyle123", "Pass1@word", "+27831234567");
        assertTrue(user.registerUser().contains("Username is not correctly formatted"));
    }

    @Test
    public void testRegisterUser_InvalidPassword() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "password", "+27831234567");
        assertTrue(user.registerUser().contains("Password is not correctly formatted"));
    }

    @Test
    public void testRegisterUser_InvalidCellNumber() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pass1@word", "0831234567");
        assertTrue(user.registerUser().contains("Cell phone number incorrectly formatted"));
    }

    // ─────────────────────────────────────────────
    // loginUser()
    // ─────────────────────────────────────────────

    @Test
    public void testLoginUser_CorrectCredentials() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pass1@word", "+27831234567");
        assertTrue(user.loginUser("kyl_1", "Pass1@word"));
    }

    @Test
    public void testLoginUser_WrongPassword() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pass1@word", "+27831234567");
        assertFalse(user.loginUser("kyl_1", "wrongpass"));
    }

    @Test
    public void testLoginUser_WrongUsername() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pass1@word", "+27831234567");
        assertFalse(user.loginUser("wrong_", "Pass1@word"));
    }

    @Test
    public void testLoginUser_BothWrong() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pass1@word", "+27831234567");
        assertFalse(user.loginUser("abc_x", "badpass1"));
    }

    // ─────────────────────────────────────────────
    // returnLoginStatus()
    // ─────────────────────────────────────────────

    @Test
    public void testReturnLoginStatus_Success() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pass1@word", "+27831234567");
        assertEquals("Welcome Kyle Smith, it is great to see you.",
                user.returnLoginStatus("kyl_1", "Pass1@word"));
    }

    @Test
    public void testReturnLoginStatus_Failure() {
        Login user = new Login("Kyle", "Smith", "kyl_1", "Pass1@word", "+27831234567");
        assertEquals("Username or password incorrect, please try again.",
                user.returnLoginStatus("kyl_1", "wrongpass"));
    }
}