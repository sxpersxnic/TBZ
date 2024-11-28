#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <string>
#include <cstdlib>
#include <ctime>
#include "Question.h"

int main() {
    std::ifstream questionFile("questions.txt");
    if (!questionFile) {
        std::cerr << "File could not be opened!" << std::endl;
        return 1;
    }

    std::vector<Question> questionList;
    std::vector<int> usedQuestions;
    std::string line;
    int score = 0;
    int randomIndex;
    std::srand(std::time(0));

    while (std::getline(questionFile, line)) {
        std::istringstream iss(line);
        std::string part;
        std::vector<std::string> parts;

        while (std::getline(iss, part, ',')) {
            parts.push_back(part);
        }

        if (parts.size() != 6) {
            std::cerr << "Error parsing this line: " << line << std::endl;
            continue;
        }

        try {
            std::stoi(parts[5]);
        } catch (const std::invalid_argument&) {
            std::cerr << "Error parsing this line: " << line << std::endl;
            continue;
        }

        Question nextQuestion(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
        questionList.push_back(nextQuestion);
    }

    std::cout << "Welcome Player! Enter your name: ";
    std::string userName;
    std::getline(std::cin, userName);

    for (int i = 0; i < 5; ++i) {
        do {
            randomIndex = std::rand() % questionList.size();
        } while (std::find(usedQuestions.begin(), usedQuestions.end(), randomIndex) != usedQuestions.end());

        usedQuestions.push_back(randomIndex);

        Question q = questionList[randomIndex];

        std::cout << "\n";
        q.display();

        int answer;
        std::cout << "Your answer: ";
        std::cin >> answer;

        if (answer == q.getCorrectAnswer()) {
            std::cout << "\nCorrect!" << std::endl;
            score += 1;
        } else {
            std::cout << "\nWrong!" << std::endl;
        }
    }

    std::cout << "Congratulations " << userName << ", your Score is: " << score << "/5" << std::endl;

    return 0;
}
