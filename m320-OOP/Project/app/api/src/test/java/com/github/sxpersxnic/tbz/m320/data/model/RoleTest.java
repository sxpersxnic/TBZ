package com.github.sxpersxnic.tbz.m320.data.model;

import com.github.sxpersxnic.tbz.m320.model.Role;
import com.github.sxpersxnic.tbz.m320.util.DataUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@Order(10)
class RoleTest {

    @Test
    @Order(1)
    public void constructor_hasEmptyConstructor() {
        Class<?> roleClass = Role.class;
        boolean doesRoleClassHaveEmptyConstructor = Arrays.stream(roleClass.getConstructors())
                .anyMatch(constructor -> constructor.getParameterCount() == 0);

        assertTrue(doesRoleClassHaveEmptyConstructor);
    }

    @Test
    @Order(9)
    public void class_isAnnotatedWithEntity() {
        assertNotNull(Role.class.getDeclaredAnnotation(Entity.class));
    }

    @Test
    @Order(2)
    public void idField_doesExist() {
        assertDoesNotThrow(() -> Role.class.getDeclaredField("id"));
    }

    @Test
    @Order(10)
    public void idField_isAnnotatedWithId() {
        try {
            assertNotNull(Role.class
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
            assertNotNull(Role.class
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
            assertEquals(GenerationType.UUID, Role.class
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
    public void nameField_doesExist() {
        assertDoesNotThrow(() -> Role.class.getDeclaredField("name"));
    }

    @Test
    @Order(4)
    public void assignedUsersField_doesExist() {
        assertDoesNotThrow(() -> Role.class.getDeclaredField("assignedUsers"));
    }

    @Test
    @Order(12)
    public void assignedUsersFieldCardinality_isManyToMany() {
        try {
            assertNotNull(Role.class
                    .getDeclaredField("assignedUsers")
                    .getDeclaredAnnotation(ManyToMany.class)
            );
        } catch (NoSuchFieldException e) {
            fail();
        }
    }

    @Test
    @Order(12)
    public void assignedUsersFieldManyToManyAnnotation_isMappedByAssignedRoles() {
        try {
            assertEquals("assignedRoles", Role.class
                    .getDeclaredField("assignedUsers")
                    .getDeclaredAnnotation(ManyToMany.class)
                    .mappedBy()
            );
        } catch (NoSuchFieldException e) {
            fail();
        }
    }

    @Test
    @Order(6)
    public void equalsMethod_isDeclared() {
        assertDoesNotThrow(() -> Role.class.getDeclaredMethod("equals", Object.class));
    }

    @Test
    @Order(6)
    public void equalsMethod_comparesNotOnlyId() {
        Role role1 = DataUtil.getTestRole();
        Role role2 = DataUtil.getTestRole();

        role1.setName("ADMIN");

        assertNotEquals(role1, role2);
    }

    @Test
    @Order(7)
    public void hashCodeMethod_isDeclared() {
        assertDoesNotThrow(() -> Role.class.getDeclaredMethod("hashCode"));
    }

    @Test
    @Order(7)
    public void hashCodeMethod_hashesId() {
        Role role1 = DataUtil.getTestRole();
        Role role2 = DataUtil.getTestRole();

        assertEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    @Order(9)
    public void doesImplementGrantedAuthority() {
        assertEquals(1, Role.class.getInterfaces().length);
        assertEquals(Role.class.getInterfaces()[0], GrantedAuthority.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"getAuthority", "getId", "getName", "getAssignedUsers"})
    @Order(10)
    public void checkGetter_doExist(String getterName) {
        assertDoesNotThrow(() -> Role.class.getDeclaredMethod(getterName));
    }

    @ParameterizedTest
    @CsvSource(value = {"setId, java.util.UUID", "setName, java.lang.String", "setAssignedUsers, java.util.Set"})
    @Order(10)
    public void checkSetter_doExist(String setterName, String parameterClassName) {
        assertDoesNotThrow(() -> Role.class.getDeclaredMethod(setterName, Class.forName(parameterClassName)));
    }

}
