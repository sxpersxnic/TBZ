package com.github.sxpersxnic.tbz.m320.util;

import com.github.sxpersxnic.tbz.m320.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class DataUtil {

    public static User getTestUser() {
        return getTestUsers().getFirst();
    }

    public static List<User> getTestUsers() {
        List<User> userList = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            User user = new User();
            user.setId(testUUID(i));
            user.setEmail("user" + i + "@foo.bar");
            user.setPassword("password" + i);

            Role role = new Role();
            Profile profile = new Profile("user" + i);

            role.setId(testUUID(3));
            role.setName("USER");
            user.setAssignedRoles(new HashSet<>());
            user.getAssignedRoles().add(role);
            user.getProfiles().add(profile);
            userList.add(user);
        }
        return userList;
    }

    public static Role getTestRole() {
        return getTestRoles().getFirst();
    }

    public static List<Role> getTestRoles() {
        List<Role> roles = new ArrayList<>();

        int roleId = 1;

        for (String roleName : List.of("MANAGER", "ADMIN", "USER")) {
            Role role = new Role();
            role.setName(roleName);
            role.setId(testUUID(roleId++));
            roles.add(role);
        }
        return roles;
    }

    public static Profile getTestProfile() {
        return getTestProfiles().getFirst();
    }

    public static List<Profile> getTestProfiles() {
        List<Profile> profiles = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            Profile profile = new Profile();

            profile.setId(testUUID(i));
            profile.setUsername("user" + i);
            profile.setProfilePicture("profile" + i + ".jpg");
            profile.setCreatedAt(LocalDateTime.of(2025, 1, 29, 0, 0, 0));

            //? If tests fail maybe add values to relations
            profile.setUser(new User());
            profile.setAnswers(new HashSet<>());
            profile.setQuestions(new HashSet<>());

            profiles.add(profile);
        }
        return profiles;
    }

    public static Option getTestOption() {
        Question question = getTestQuestion();
        return getTestOptions(question).getFirst();
    }

    public static List<Option> getTestOptions(Question question) {
        List<Option> options = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            Option option = new Option();
            option.setId(testUUID(i));
            option.setAnswers(new HashSet<>());
            option.setQuestion(question);
            option.setAnswerCount(0);
            option.setContent("Option " + i);
            option.setCreatedAt(LocalDateTime.of(2025, 1, 29, 0, 0, 0));
            options.add(option);
        }
        return options;
    }

    public static Question getTestQuestion() {
        return getTestQuestions().getFirst();
    }

    public static List<Question> getTestQuestions() {
        List<Question> questions = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            Question question = new Question();
            question.setId(testUUID(i));
            question.setProfile(new Profile("user" + i));
            question.setContent("Question " + i);
            question.setDescription("Description " + i);
            question.setOptions(new HashSet<>());
            question.getOptions().addAll(getTestOptions(question));
            question.setCreatedAt(LocalDateTime.of(2025, 1, 29, 0, 0, 0));
            questions.add(question);
        }
        return questions;
    }

    public static Answer getTestAnswer() {
        return getTestAnswers().getFirst();
    }

    public static List<Answer> getTestAnswers() {
        List<Answer> answers = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            Answer answer = new Answer();
            Option option = new Option();
            option.setAnswers(new HashSet<>());

            answer.setId(testUUID(i));
            answer.setOption(option);
            answer.setProfile(new Profile("user" + i));
            answer.setCreatedAt(LocalDateTime.of(2025, 1, 29, 0, 0, 0));
            option.getAnswers().add(answer);
            answers.add(answer);
        }
        return answers;
    }

    public static UUID testUUID(int number) {
        StringBuilder uuid = new StringBuilder();
        for (int i = 0; i <= 35; i++) {
            if (i == 8 || i == 13 || i == 18 || i == 23) {
                uuid.append("-");
            } else if (i == 14) {
                uuid.append("4");
            } else if (i == 19) {
                uuid.append("8");
            } else {
                uuid.append(number);
            }
        }
        return UUID.fromString(uuid.toString());
    }
}
