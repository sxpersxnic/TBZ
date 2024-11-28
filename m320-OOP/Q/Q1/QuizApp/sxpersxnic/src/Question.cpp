#include <string>
#include <vector>

class Question {
  private:
    std::vector<std::string> questionAndAnswers;
    int correctAnswer;
  public:
    Question(const std::string& question, const std::string& answer1, const std::string& answer2, const std::string& answer3, const std::string& answer4, const std::string& correct)
      : questionAndAnswers{question, answer1, answer2, answer3, answer4}, correctAnswer(std::stoi(correct)) {}

    std::string getQuestion() const {
      return questionAndAnswer[0];
    }
  
    std::vector<std::string> getAnswers() const {
        return std::vector<std::string>(questionAndAnswers.begin() + 1, questionAndAnswers.end());
    }
  
    int getCorrectAnswer() const {
        return correctAnswer;
    }
  
    void setQuestion(const std::string& q) {
        questionAndAnswers[0] = q;
    }
  
    void setAnswers(const std::string& a1, const std::string& a2, const std::string& a3, const std::string& a4) {
        questionAndAnswers[1] = a1;
        questionAndAnswers[2] = a2;
        questionAndAnswers[3] = a3;
        questionAndAnswers[4] = a4;
    }
  
    void setCorrectAnswer(int correct) {
        correctAnswer = correct;
    }
  
    void display() const {
        std::cout << "Question: " << questionAndAnswers[0] << std::endl;
        for (size_t i = 1; i < questionAndAnswers.size(); ++i) {
            std::cout << i << ". " << questionAndAnswers[i] << std::endl;
        }
    }
};
