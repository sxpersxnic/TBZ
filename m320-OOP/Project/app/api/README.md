# API

## Pre-Development UML

```mermaid
---
title: Pre-Development UML
---
classDiagram
    
    namespace API {
        class Application
        class Profiles
        class Questions
        class Options
        class Answers
    }
    class Application {
        +main(String[] args)$
    }
    
    namespace Questions {
        class Question {
            -UUID id
        }
    }
    
    namespace Profiles {
        class Profile {
            -UUID id
        }
    }
    
    namespace Answers {
        class Answer {
            -UUID id
        }
    }
    
    namespace Options {
        class Option {
            -UUID id
        }
    }
    
    Application --> Profiles
    Application --> Questions
    Application --> Options
    Application --> Answers
    
    

```

## Past-Development UML

```mermaid
```

## Sequence Diagram

```mermaid
---
title: Create Question (POST /questions)
---
sequenceDiagram
    participant Client
    participant QuestionController
    participant QuestionService
    participant QuestionMapper
    participant QuestionRepository
    participant ProfileService
    participant OptionService
    
    Client->>+QuestionController: POST /questions
    QuestionController->>+QuestionMapper: fromDTO(dto)
    QuestionMapper-->>-QuestionController: Question
    QuestionController->>+QuestionService: create(question)
    QuestionService->>Question: setTotalAnswerCount(0)
    QuestionService->>Question: setCreatedAt(ZonedDateTime.now())
    QuestionService->>+QuestionRepository: existsByContentAndProfile
    alt exists
     QuestionService->>QuestionController: Throw EntityExistsException
     break
        QuestionController->>Client: ResponseEntity.status(HttpStatus.CONFLICT).body("Question already exists")
     end
    end
    alt Size of options is less than 2
        QuestionService->>QuestionController: Throw FailedValidationException
        break
            QuestionController->>Client: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Question must have at least 2 options")
        end
    end
    QuestionService->>+ProfileService: findById(question.getProfile().getId())
    ProfileService-->>-QuestionService: Profile
    QuestionService->>Question: setProfile(Profile)
    loop For each option
        QuestionService->>+OptionService: create(option)
        OptionService-->>-QuestionService: Option
    end
    QuestionService->>+QuestionRepository: save(question)
    QuestionRepository-->>-QuestionService: Question
    QuestionService-->>-QuestionController: Question
    QuestionController->>+QuestionMapper: toDTO(saved)
    QuestionMapper-->>-QuestionController: QuestionDTO
    QuestionController-->>-Client: ResponseEntity.status(HttpStatus.CREATED).body(QuestionDTO)
```