package com.github.sxpersxnic.tbz.m320.data.model;

import com.github.sxpersxnic.tbz.m320.model.User;
import com.github.sxpersxnic.tbz.m320.util.DataUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@Order(10)
class UserTest {

    @Test
    @Order(1)
    public void constructor_hasEmptyConstructor() {
        Class<?> userClass = User.class;
        boolean doesUserClassHaveEmptyConstructor = Arrays.stream(userClass.getConstructors())
                .anyMatch(constructor -> constructor.getParameterCount() == 0);

        assertTrue(doesUserClassHaveEmptyConstructor);
    }

    @Test
    @Order(9)
    public void class_isAnnotatedWithEntity() {
        assertNotNull(User.class.getDeclaredAnnotation(Entity.class));
    }

    @Test
    @Order(2)
    public void idField_doesExist() {
        assertDoesNotThrow(() -> User.class.getDeclaredField("id"));
    }

    @Test
    @Order(10)
    public void idField_isAnnotatedWithId() {
        try {
            assertNotNull(User.class
                    .getDeclaredField("id")
                    .getDeclaredAnnotation(Id.class)
            );
        } catch (NoSuchFieldException e) {
            fail();
        }
    }

    @Test
    @Order(11)
    public void idField_isAnnotatedWithGeneratedValue() {
        try {
            assertNotNull(User.class
                    .getDeclaredField("id")
                    .getDeclaredAnnotation(GeneratedValue.class)
            );
        } catch (NoSuchFieldException e) {
            fail();
        }
    }

    @Test
    @Order(11)
    public void idFieldGeneratedValueAnnotation_isStrategyUUID() {
        try {
            assertEquals(GenerationType.UUID, User.class
                    .getDeclaredField("id")
                    .getDeclaredAnnotation(GeneratedValue.class)
                    .strategy()
            );
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(3)
    public void emailField_doesExist() {
        assertDoesNotThrow(() -> User.class.getDeclaredField("email"));
    }

    @Test
    @Order(4)
    public void passwordHashField_doesExist() {
        assertDoesNotThrow(() -> User.class.getDeclaredField("password"));
    }

    @Test
    @Order(5)
    public void assignedRolesField_doesExist() {
        assertDoesNotThrow(() -> User.class.getDeclaredField("assignedRoles"));
    }

    @Test
    @Order(12)
    public void assignedRolesFieldCardinality_isManyToMany() {
        try {
            assertNotNull(User.class
                    .getDeclaredField("assignedRoles")
                    .getDeclaredAnnotation(ManyToMany.class)
            );
        } catch (NoSuchFieldException e) {
            fail();
        }
    }

    @Test
    @Order(12)
    public void assignedRolesField_hasJoinTable() {
        try {
            assertNotNull(User.class
                    .getDeclaredField("assignedRoles")
                    .getDeclaredAnnotation(JoinTable.class)
            );
        } catch (NoSuchFieldException e) {
            fail();
        }
    }

    @Test
    @Order(12)
    public void assignedRolesFieldJoinTable_nameIsUserRole() {
        try {
            assertEquals("user_role", User.class
                    .getDeclaredField("assignedRoles")
                    .getDeclaredAnnotation(JoinTable.class)
                    .name()
            );
        } catch (NoSuchFieldException e) {
            fail();
        }
    }

    @Test
    @Order(12)
    public void assignedRolesFieldJoinTable_joinColumnsIsUserId() {
        try {
            JoinColumn[] joinColumns = User.class
                    .getDeclaredField("assignedRoles")
                    .getDeclaredAnnotation(JoinTable.class)
                    .joinColumns();
            assertEquals(1, joinColumns.length);
            assertEquals("user_id", joinColumns[0].name());
        } catch (NoSuchFieldException e) {
            fail();
        }
    }

    @Test
    @Order(12)
    public void assignedRolesFieldJoinTable_inverseJoinColumnsIsRoleId() {
        try {
            JoinColumn[] joinColumns = User.class
                    .getDeclaredField("assignedRoles")
                    .getDeclaredAnnotation(JoinTable.class)
                    .inverseJoinColumns();
            assertEquals(1, joinColumns.length);
            assertEquals("role_id", joinColumns[0].name());
        } catch (NoSuchFieldException e) {
            fail();
        }
    }

    @Test
    @Order(6)
    public void equalsMethod_isDeclared() {
        assertDoesNotThrow(() -> User.class.getDeclaredMethod("equals", Object.class));
    }

    @Test
    @Order(6)
    public void equalsMethod_comparesNotOnlyId() {
        User user1 = DataUtil.getTestUser();
        User user2 = DataUtil.getTestUser();

        user1.setEmail("NotSameEmail@foo.bar");
        user1.setPassword("NotSamePassword");

        assertNotEquals(user1, user2);
    }

    @Test
    @Order(7)
    public void hashCodeMethod_isDeclared() {
        assertDoesNotThrow(() -> User.class.getDeclaredMethod("hashCode"));
    }

    @Test
    @Order(7)
    public void hashCodeMethod_hashesId() {
        User user1 = DataUtil.getTestUser();
        User user2 = DataUtil.getTestUser();

        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @ParameterizedTest
    @CsvSource(value = {"getId", "getEmail", "getPassword", "getAssignedRoles"})
    @Order(8)
    public void checkGetter_doExist(String getterName) {
        assertDoesNotThrow(() -> User.class.getDeclaredMethod(getterName));
    }

    @ParameterizedTest
    @CsvSource(value = {"setId, java.util.UUID", "setEmail, java.lang.String", "setPassword, java.lang.String", "setAssignedRoles, java.util.Set"})
    @Order(8)
    public void checkSetter_doExist(String setterName, String parameterClassName) {
        assertDoesNotThrow(() -> User.class.getDeclaredMethod(setterName, Class.forName(parameterClassName)));
    }

}
