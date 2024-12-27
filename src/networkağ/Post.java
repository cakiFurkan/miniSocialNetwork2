
package networkağ;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Stack;

public class Post extends Activity implements Comparable<Post>{
    private final LocalDateTime time = LocalDateTime.now();
    private final LocalDate date = LocalDate.now();
    public static Stack<Post> activity = new Stack<>();

    public Post(String content, User user) {
        super(content, user);
    }

    @Override
    public String toString() {
        return "Post{" +
                "content='" + super.getContent() + '\'' +
                ", postTime='" + super.getActivityTime() + '\'' +
                ", user=" + super.getUser().getName() +'\n' +
                '}';
    }

    @Override
    public int compareTo(Post other) {
        return Long.compare(this.getActivitytime(),other.getActivitytime());
    }
}
