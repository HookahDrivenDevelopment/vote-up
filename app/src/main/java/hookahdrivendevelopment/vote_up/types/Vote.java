package hookahdrivendevelopment.vote_up.types;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by timur on 27/05/2018.
 */

public class Vote {
    public String author = "author";
    public Date created;
    public Date updated;
    public String status = "status";
    public String note = "note";
    public String type = "type";
    public String content = "";
    int voted = 0;
    List<Answer> answers;

    public Vote() {
        answers = new ArrayList<Answer>();
    }

}
