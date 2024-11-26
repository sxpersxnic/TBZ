public class Question {
    String Question;
    String Answer1;
    String Answer2;
    String Answer3;
    String Answer4;
    int CorrectAnswer;
    public Question(String Question, String Answer1, String Answer2, String Answer3, String Answer4, String CorrectAnswer)
    {
        this.Question = Question;
        this.Answer1 = Answer1;
        this.Answer2 = Answer2;
        this.Answer3 = Answer3;
        this.Answer4 = Answer4;
        this.CorrectAnswer = Integer.parseInt(CorrectAnswer);
    }
}
