package hookahdrivendevelopment.vote_up.types;

import java.nio.ByteBuffer;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.*;

/**
 * Created by timur on 27/05/2018.
 */

public class Vote {
    public long id;
    public String title = "title";
    public String author = "author";
    public Date created;
    public Date updated;
    public String status = "status";
    public String note = "note";
    public String type = "type";
    public String content = "";
    public int voted = 0;
    public List<Answer> answers;

    public Vote() {
        id = currentTimeMillis();
        answers = new ArrayList<Answer>();
    }

    public Vote(String title) {
        this();
        this.title = title;
    }

    public static ArrayList<Vote> createVotes() {
        ArrayList<Vote> votes = new ArrayList<Vote>();

        for (int i = 0; i < 10; i++) {
            Vote vote = new Vote("title " + i);

            for (int j = 0; j < 4; ++j) {
                vote.answers.add(new Answer("answer " + j));
            }

            votes.add(vote);
        }

        return votes;
    }

}
