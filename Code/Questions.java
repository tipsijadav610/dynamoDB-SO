package Lab07.assignment;

import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Questions")
public class Questions {
	private Integer qid;
	private Integer user_id;
	private String question_text;
	private String timestamp;
	private String tags;
	
	@DynamoDBHashKey(attributeName = "qid")
	public Integer getqid() {
		return qid;
	}

    public void setqid(Integer qid) {
        this.qid = qid;
    }

    @DynamoDBAttribute(attributeName = "user_id")
	public Integer getuser_id() {
		return user_id;
	}

    public void setuser_id(Integer user_id) {
        this.user_id = user_id;
    }
     
    @DynamoDBAttribute(attributeName = "question_text")
    public String getquestion_text() {
        return question_text;
    }

    public void setquestion_text(String question_text) {
        this.question_text = question_text;
    }

    @DynamoDBAttribute(attributeName = "timestamp")
    public String gettimestamp() {
        return timestamp;
    }

    public void settimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @DynamoDBAttribute(attributeName = "tags")
    public String gettags() {
        return tags;
    }

    public void settags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "qid=" + qid + ", user_id=" + user_id + ", question_text=" + question_text + ", timestamp=" + timestamp + ", tags=" + tags;
    }
}