package m320.projekt.lib.constants;

import java.util.regex.Pattern;

public class Regex {
    /**
     * The regular expression pattern for validating email addresses.
     *
     * <p>This pattern checks that:
     * <ul>
     * <li>There is only one "@" character.</li>
     * <li>There are no consecutive dots.</li>
     * <li>The local part (before "@") is 1-64 characters long, consisting of letters, digits, and allowed special characters.</li>
     * <li>The domain part (after "@") is a valid domain with at least one dot, and optional second-level domain.</li>
     * </ul>
     */
        public static final String EMAIL_REGEX = "^(?!.*@.*@)(?!.*\\.\\.)(^[a-zA-Z0-9._%+-]{1,64}@)([a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(?:\\.[a-zA-Z]{2,255})?)$";

    /**
     * The regular expression pattern for validating usernames.
     *
     * <p>This pattern checks that:
     * <ul>
     * <li>The username starts with a letter.</li>
     * <li>The username is 3-255 characters long.</li>
     * <li>The username consists of letters, digits, and underscores.</li>
     * </ul>
     */

    public static final String USERNAME_REGEX = "^[A-Za-z0-9_-]\\w{3,255}$";

    /**
     * The regular expression pattern for validating passwords.
     *
     * <p>This pattern checks that:
     * <ul>
     * <li>The password is at least 8 characters long.</li>
     * <li>The password contains at least one lowercase letter.</li>
     * <li>The password contains at least one uppercase letter.</li>
     * <li>The password contains at least one digit.</li>
     * <li>The password contains at least one special character from the set @#$%^&*!.</li>
     * </ul>
     */
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*!])[A-Za-z\\d@#$%^&*!]{8,}$";

    /**
     * The compiled pattern for validating email addresses.
     * <p>This pattern checks that:
     * <ul>
     * <li>There is only one "@" character.</li>
     * <li>There are no consecutive dots.</li>
     * <li>The local part (before "@") is 1-64 characters long, consisting of letters, digits, and allowed special characters.</li>
     * <li>The domain part (after "@") is a valid domain with at least one dot, and optional second-level domain.</li>
     * </ul>
     *
     * See: {@link Pattern}
     */
    public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * The compiled pattern for validating usernames.
     * <p>This pattern checks that:
     * <ul>
     * <li>The username starts with a letter.</li>
     * <li>The username is 2-256 characters long.</li>
     * <li>The username consists of letters, digits, and underscores.</li>
     * </ul>
     *
     * See: {@link Pattern}
     */
    public static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME_REGEX);

    /**
     * The compiled pattern for validating passwords.
     *
     * <p>This pattern checks that:
     * <ul>
     * <li>The password is at least 8 characters long.</li>
     * <li>The password contains at least one lowercase letter.</li>
     * <li>The password contains at least one uppercase letter.</li>
     * <li>The password contains at least one digit.</li>
     * <li>The password contains at least one special character from the set @#$%^&*!.</li>
     * </ul>
     *
     * See: {@link Pattern}
     */
    public static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
}