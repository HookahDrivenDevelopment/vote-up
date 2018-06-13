package hookahdrivendevelopment.vote_up.types;

public class Answer {
    public String text = "";
    public int voted = 0;

    public Answer(String text) {
        this.text = text;
        this.voted = 0;
    }
}
