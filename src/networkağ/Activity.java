
package networkağ;

import java.util.Date;

public class Activity{
    private long activityTime;
    private String content;
    private User user;


    public Activity(String content,User user) {
        this.content = content;
        this.user = user;
        this.activityTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityTime=" + getActivityTime() +
                ", content='" + content + '\'' +
                '}';
    }

    public Date getActivityTime(){
        return new Date(activityTime);
    }

    public long getActivitytime(){
        return activityTime;
    }

    public String getContent(){
        return content;
    }

    public User getUser(){
        return user;
    }
}
